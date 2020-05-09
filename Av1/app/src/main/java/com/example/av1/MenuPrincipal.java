package com.example.av1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity {

    private TextView usuario;
    private Button cdtICMS;
    private Button cdtPedido;
    private Button cdtVenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        usuario = findViewById(R.id.txtUsuario);
        cdtICMS = findViewById(R.id.cdICMS);
        cdtPedido = findViewById(R.id.cdPedidos);
        cdtVenda = findViewById(R.id.cdVendas);

        Bundle usuarioLogado = getIntent().getExtras();

            usuario.setText(usuarioLogado.getString("usuario"));


        cdtICMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this,CadastroICMS.class);
                intent.putExtra("usuario2", usuario.getText().toString());
                startActivity(intent);
            }
        });;
        cdtPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this,CadastroPedido.class);
                intent.putExtra("usuario", usuario.getText().toString());
                startActivity(intent);
            }
        });;
        cdtVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this,CadastroVenda.class);
                intent.putExtra("usuario", usuario.getText().toString());
                startActivity(intent);
            }
        });;
    }
}
