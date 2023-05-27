package com.projetohome.telalogincadastroperfil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_direito;
    private Button button_adm;
    private Button button_tec;
    private Button button_outros;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IniciarComponents();


        button_direito.setOnClickListener(new View.OnClickListener() { //setando o clik da busca declarando a intenção
            @Override
            public void onClick(View v) {

                Intent direito = new Intent(MainActivity.this, CurriculumDireito.class);
                startActivity(direito);

            }
        });

        button_tec.setOnClickListener(new View.OnClickListener(){  //setando o clik da busca declarando a intenção
            @Override
            public void onClick(View d){
                Intent tec = new Intent(MainActivity.this, CurriculumTec.class);
                startActivity(tec);
            }

        });

        button_adm.setOnClickListener(new View.OnClickListener(){ //setando o clik da busca declarando a intenção
            @Override
            public void onClick(View a){
                Intent adm = new Intent(MainActivity.this,CurriculumAdm.class);
                startActivity(adm);
            }
        });

        button_outros.setOnClickListener(new View.OnClickListener(){ //setando o clik da busca declarando a intenção
            @Override
            public void onClick(View b){
                Intent outros = new Intent(MainActivity.this, CurriculumOutros.class);
                startActivity(outros);
            }
        });
            }


    public void IniciarComponents(){ //declarei as ID buscando elas mesma pela ID no layout
        button_direito = findViewById(R.id.button_direito);
        button_adm = findViewById(R.id.button_adm);
        button_tec = findViewById(R.id.button_tec);
        button_outros = findViewById((R.id.button_outros));

    }
        }