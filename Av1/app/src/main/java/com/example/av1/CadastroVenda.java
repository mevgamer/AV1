package com.example.av1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroVenda extends AppCompatActivity {

    private AlertDialog.Builder dialog;
    private ListView lista;
    private String[] listaVendas = {"132","245","646","64523",
            "2345","643543","84564","7845435",
            "345234","5234234","6456456","456457456",
            "456456","456456456","123123","152423","11234",
            "123123123","1231234123","312313","12312312",
            "12312313","123123","123123","12312312","123123",
            "1231233"};
    private float[] valorVenda = {256,10123,402,103,102,101,104,105,106,107,180,910,100,109,108,104,103,102,110,110,120,310,410,105,160,170,810};
    private String[] dataPagamento={"29/03/1995","29/12/1986","17/03/1991","19/09/1993","20/01/1998","21/10/1980","10/07/1996","31/10/1994",
            "04/04/1983","12/02/1996","12/03/1996","25/06/1985","09/03/1998","29/04/1982","30/12/1980","27/08/1999","25/06/1987","22/11/2001",
            "03/06/1980","28/05/1984","14/01/1991","06/05/1996","29/10/1994","31/03/1992","04/01/1984","23/10/1991","14/12/2001"};
    private float verificacao;
    private String valorAtual;
    private Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_venda);

            salvar = findViewById(R.id.btSalvar2);
            lista = findViewById(R.id.listaVendas);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listaVendas
        );
        lista.setAdapter(adaptador);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                dialog = new AlertDialog.Builder(CadastroVenda.this);
                String mensagem = "";
                mensagem+="Valor atual: "+valorVenda[position];
                mensagem+="Data da venda :"+dataPagamento[position];
                mensagem+="\n ATENÇÃO: Para efetuar a troca do valor,";
                mensagem+="\n digite abaixo e clique em 'Editar'";
                mensagem+="\n     ";

                dialog.setTitle("Dados da venda "+ listaVendas[position]);
                dialog.setMessage(mensagem);

                final EditText novoValor = new EditText(CadastroVenda.this);
                novoValor.setInputType(InputType.TYPE_CLASS_NUMBER);
                dialog.setView(novoValor);

                valorAtual = String.valueOf(valorVenda[position]);
                novoValor.setText(valorAtual);



                dialog.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        verificacao = Float.parseFloat(novoValor.getText().toString());

                        if (verificacao == 0){
                            Toast.makeText(getApplicationContext(), "Utilize um valor diferenete de 0", Toast.LENGTH_LONG).show();

                        } else {
                            valorVenda[position] = verificacao;
                            Toast.makeText(getApplicationContext(), "Valor atual : R$" + valorVenda[position]+" do pedido: "+ listaVendas[position]+ "  Alterado com sucesso! ", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent( CadastroVenda.this,MenuPrincipal.class);
                startActivity(intent);
            }
        });

    }
}
