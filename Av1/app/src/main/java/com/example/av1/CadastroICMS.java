package com.example.av1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class CadastroICMS extends AppCompatActivity {



    private AlertDialog.Builder dialog;
    private ListView lista;
    private String[] listaEstados = {"Acre (AC)","Alagoas (AL)","Amapá (AP)","Amazonas (AM)",
            "Bahia (BA)","Ceará (CE)","Distrito Federal (DF)","Espírito Santo (ES)",
            "Goiás (GO)","Maranhão (MA)","Mato Grosso (MT)","Mato Grosso do Sul (MS)",
            "Minas Gerais (MG)","Pará (PA)","Paraíba (PB)","Paraná (PR)","Pernambuco (PE)",
            "Piauí (PI)","Rio de Janeiro (RJ)","Rio Grande do Norte (RN)","Rio Grande do Sul (RS)",
            "Rondônia (RO)","Roraima (RR)","Santa Catarina (SC)","São Paulo (SP)","Sergipe (SE)",
            "Tocantins (TO)"};
    private float[] valorImposto = {2,10,40,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
    private float verificacao;
    private String valorAtual;
    private TextView usuario;
    private Button salvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_i_c_m_s);

        Bundle usuarioLogado2 = getIntent().getExtras();

        salvar = findViewById(R.id.salvar);
        lista = findViewById(R.id.listaEstados);
        usuario = findViewById(R.id.usuario);
        usuario.setText(usuarioLogado2.getString("usuario2"));



        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listaEstados
        );

        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), " Taxa atual:" +valorImposto[position] + "%" , Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                dialog = new AlertDialog.Builder(CadastroICMS.this);
                String mensagem = "";
                mensagem+="Porcentagem atual: "+valorImposto[position] + "%";
                mensagem+="\n ATENÇÃO: Para efetuar a troca do valor,";
                mensagem+="\n digite abaixo e clique em 'Editar valor'";

                dialog.setTitle("Taxa atual ICMS para o estado de "+listaEstados[position] + "%");
                dialog.setMessage(mensagem);

                final EditText novoValor = new EditText(CadastroICMS.this);
                novoValor.setInputType(InputType.TYPE_CLASS_NUMBER);
                dialog.setView(novoValor);

                valorAtual = String.valueOf(valorImposto[position]);
                novoValor.setText(valorAtual);



                dialog.setPositiveButton("Editar valor", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        verificacao = Float.parseFloat(novoValor.getText().toString());

                        if (verificacao > 100.1){
                            Toast.makeText(getApplicationContext(), "Utilize um valor menor que 100!", Toast.LENGTH_LONG).show();

                        } else {
                            valorImposto[position] = verificacao;
                            Toast.makeText(getApplicationContext(), "Taxa do estado de "+ listaEstados[position] + " alterada para "+ valorImposto[position] + "%", Toast.LENGTH_LONG).show();
                        }


                    }
                });

                dialog.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                dialog.setIcon(android.R.drawable.star_on);
                dialog.setCancelable(false);

                dialog.create();
                dialog.show();



                return true;
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroICMS.this, MenuPrincipal.class);
                intent.putExtra("usuario", usuario.getText().toString());

                startActivity(intent);
            }
        });;


    }
}
