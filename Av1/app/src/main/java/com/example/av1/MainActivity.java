package com.example.av1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText senha;
    private Button entrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.cpLogin);
        senha = findViewById(R.id.cpSenha);
        entrar = findViewById(R.id.btEntrar);


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MenuPrincipal.class);
                intent.putExtra("usuario", usuario.getText().toString());

                startActivity(intent);
            }
        });;

    }
}
