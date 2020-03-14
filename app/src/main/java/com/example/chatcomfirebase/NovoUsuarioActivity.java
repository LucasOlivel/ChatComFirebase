package com.example.chatcomfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class NovoUsuarioActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText senhaEditText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        loginEditText = findViewById(R.id.loginEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void criarNovoUsuario (View view){
        String login = loginEditText.getText().toString();
        String senha = senhaEditText.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(
                login, senha).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(NovoUsuarioActivity.this,
                        getString(R.string.cadastro_sucesso, authResult.getUser().toString()),
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NovoUsuarioActivity.this,
                        getString(R.string.erro_inesperado),
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        })
        ;
    }
}
