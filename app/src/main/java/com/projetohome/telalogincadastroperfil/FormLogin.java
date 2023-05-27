package com.projetohome.telalogincadastroperfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FormLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;
    private EditText edit_email,edit_senha;
    private Button bt_entrar;
    private ProgressBar progressBar;
    String[] mensagens = {"Preencha todos os campos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        IniciarComponents();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            //declarando intenção para fazer linkagem de paginas
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                    snackbar.setTextColor(ColorStateList.valueOf(Color.BLACK));
                    snackbar.show();
                }else {
                    AutenticarUsuario(v);

                }
            }
            });
    }
    //autenticando usuario busca no banco de dados senha/email registrads se for sucesso ele migra desta pagina
    //para a pagina principal de curriculos deixando visvel a progress bar para ter uma animaçã mais bonita
    private void AutenticarUsuario(View view){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();
                        }
                    },3000);
                }else {
                    String erro;

                    try {
                        throw task.getException();
                    }catch (Exception e){
                        erro = "Erro Ao Logar Usuario.";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                    snackbar.setTextColor(ColorStateList.valueOf(Color.BLACK));
                    snackbar.show();
                    //caixa de span/aviso quando dar algo errado ou certo
                }
                }
        });
        }

        private void TelaPrincipal(){
            Intent intent = new Intent(FormLogin.this, MainActivity.class);
            startActivity(intent);
            finish();
            //se o login for efetuado com sucesso ele vai para pagina PRINCIPAL
        }
    private void IniciarComponents() {
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        edit_email = findViewById(R.id.edit_Email);
        edit_senha = findViewById(R.id.edit_Senha);
        bt_entrar = findViewById(R.id.bt_entrar);
        progressBar = findViewById(R.id.progressbar);
    }
    }
