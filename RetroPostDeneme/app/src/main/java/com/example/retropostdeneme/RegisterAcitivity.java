package com.example.retropostdeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterAcitivity extends AppCompatActivity {
    private TextView _name;
    private TextView _surname;
    private TextView _password;
    private  TextView _email;
    private Button _registerButton;
   // private CheckBox _musteriCheckBox;
    private CheckBox _magazaCheckBox;
    private String rol;
    private TextView lblresponse;
    private PostService postsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acitivity);
        _name=findViewById(R.id.name);
        _surname=findViewById(R.id.surname);
        _email=findViewById(R.id.email);
        _password=findViewById(R.id.password);
        _registerButton=(Button)findViewById(R.id.button);
        _magazaCheckBox=findViewById(R.id.checkBoxMagaza);
            _registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name=_name.getText().toString();
                    String surname=_surname.getText().toString();
                    String email=_email.getText().toString();
                    String password=_password.getText().toString();

                    if(_magazaCheckBox.isChecked()){
                        rol="magaza";
                    }else{
                        rol="musteri";
                    }


                    try {
                        lblresponse = findViewById(R.id.lblresponse1);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://10.0.2.2:8085/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        postsService = retrofit.create(PostService.class);
                        sendPost(name,surname,email,password,rol);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });


    }






    private void sendPost(String name,String surname,String email,String Password,String rol ) {
        Posts post = new Posts();
        post.setBody("Example");
        post.setName(name);
        post.setSurname(surname);
        post.setEmail(email);
        post.setPassword(Password);
        post.setRol(rol);
        Call<Posts> call = postsService.sendPosts(post);
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                lblresponse.setText(response.body().toString());
                System.out.println(response+"response");

                System.out.println("response..."+response.body());
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }

        });
    }
}