package com.mendes.nando.keycloak;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText nomeUsuario;
    EditText senhaUsuario;
    Button logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nomeUsuario = findViewById(R.id.userName);
        senhaUsuario = findViewById(R.id.userPassword);
        logar = findViewById(R.id.btnLogin);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAccessToken();
            }
        });
    }

    public void getAccessToken(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        String password = senhaUsuario.getText().toString();
        String username = nomeUsuario.getText().toString();

        Call<AccessToken> call = service.getAccessToken("Login","4286e487-86ca-4892-b7cf-5268e0129d9e", "client_credentials", "openid", username, password);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()){
                    AccessToken accessToken = response.body();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Erro: noSuccess", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Erro: Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}