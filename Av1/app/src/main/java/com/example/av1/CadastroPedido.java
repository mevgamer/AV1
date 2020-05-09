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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroPedido extends AppCompatActivity {

    private AlertDialog.Builder dialog;
    private TextView status;
    private ListView lista;
    private Switch swStatus;
    private String keyStatus;
    private String[] listaPedidos = {"132","245","646","64523",
            "2345","643543","84564","7845435",
            "345234","5234234","6456456","456457456",
            "456456","456456456","123123","152423","11234",
            "123123123","1231234123","312313","12312312",
            "12312313","123123","123123","12312312","123123",
            "1231233"};
    private float[] valorPedido = {256,10123,402,103,102,101,104,105,106,107,180,910,100,109,108,104,103,102,110,110,120,310,410,105,160,170,810};
    private float verificacao;
    private String valorAtual;
    private Button salvarPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        swStatus = findViewById(R.id.sistema);
<<<<<<< HEAD
        salvarPedido = findViewById(R.id.salvar);
        lista = findViewById(R.id.listaPedidos);
=======
        salvar = findViewById(R.id.salvar);
        lista = findViewById(R.id.listaVendas);
>>>>>>> MatheusDev
        status = findViewById(R.id.status);
        status.setText("Offline");






        swStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            android.R.id.text1,
                            listaPedidos
                    );
                    lista.setAdapter(adaptador);

                    keyStatus = "Online";
                    status.setText(keyStatus);

                    lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                            dialog = new AlertDialog.Builder(CadastroPedido.this);
                            String mensagem = "";
                            mensagem+="Valor atual: "+valorPedido[position] + "%";
                            mensagem+="\n ATENÇÃO: Para efetuar a troca do valor,";
                            mensagem+="\n digite abaixo e clique em 'Editar'";

                            dialog.setTitle("Valor atual do pedido: R$"+listaPedidos[position] + "%");
                            dialog.setMessage(mensagem);

                            final EditText novoValor = new EditText(CadastroPedido.this);
                            novoValor.setInputType(InputType.TYPE_CLASS_NUMBER);
                            dialog.setView(novoValor);

                            valorAtual = String.valueOf(valorPedido[position]);
                            novoValor.setText(valorAtual);



                            dialog.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    verificacao = Float.parseFloat(novoValor.getText().toString());

                                    if (verificacao == 0){
                                        Toast.makeText(getApplicationContext(), "Coloque um valor diferente de '0'!", Toast.LENGTH_LONG).show();

                                    } else {
                                        valorPedido[position] = verificacao;
                                        Toast.makeText(getApplicationContext(), "Valod do produto :  "+ listaPedidos[position] + " alterada para "+ valorPedido[position], Toast.LENGTH_LONG).show();
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

                }else{

                    lista.setEnabled(false);

                    keyStatus = "Offline";
                    status.setText(keyStatus);

                }
            }
        });




    }

}
