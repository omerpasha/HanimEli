package com.example.retropostdeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
public class LoginAcitivty extends AppCompatActivity {
    private Button _loginButton;
    private String rol="";
    private TextView _email;
    private TextView _password;
    private CheckBox _magazaCheckBox;
    private CheckBox _musteriCheckBox;
    private TextView lblresponse;
    private PostServiceLogin postsServiceLogin;
    private String rol1="musteri";
    private String rol2="magaza";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivty);
        _loginButton=findViewById(R.id.buttonLogin);
        _email=findViewById(R.id.emailLogin);
        _password=findViewById(R.id.passwordLogin);
        _magazaCheckBox=findViewById(R.id.checkBoxMagazaLogin);
        _musteriCheckBox=findViewById(R.id.checkBoxMusteriLogin);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =_email.getText().toString();
                String password=_password.getText().toString();
                if(_magazaCheckBox.isChecked()){
                    rol="magaza";
                }else if(_musteriCheckBox.isChecked()){
                    rol="musteri";
                }else{
                    System.out.println("rolde hata var");
                }
                try {
                    lblresponse = findViewById(R.id.lblresponseLogin);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:8085/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    postsServiceLogin = (PostServiceLogin) retrofit.create(PostServiceLogin.class);
                    sendPost(email,password,rol);
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("eee"+e.toString());
                }
            }
        });
    }
    private void sendPost(final String email, String Password, String rol ) {
        PostsLogin postLogin = new PostsLogin();
        postLogin.setBody("Example");
        postLogin.setEmail(email);
        postLogin.setPassword(Password);
        postLogin.setRol(rol);
        Call<PostsLogin> call = postsServiceLogin.sendPosts(postLogin);
        call.enqueue(new Callback<PostsLogin>() {
            @Override
            public void onResponse(Call<PostsLogin> call, Response<PostsLogin> response) {
                //lblresponse.setText(response.body().toString());
                System.out.println("response..."+response.body());
                if(response.body()!=null){
                    if(response.code()==200){
                        System.out.println("okey döndü");
                        String s=response.body().getRol();
                        System.out.println("ssss"+s);
                        if(s.equals(rol1)){
                           // System.out.println("dönen değer müsteri");
                            id_intent2(response.body().getId());
                        }
                        if(s.equals(rol2)){
                            System.out.println("dhahdahhdahdah "+email);
                            id_intent(response.body().getId(),response.body().getName(),email);
                        }
                    }else{
                        System.out.println("geçerli değil");
                        openErrorActivity();
                    }
                }else{
                    System.out.println("open error");
                    openErrorActivity();
                }
            }
            public void onFailure(Call<PostsLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println("hata var");
            }
        });
    }
    private  void id_intent(String id,String name,String email){
        Intent intent=new Intent(LoginAcitivty.this,magazaciProfilActivity.class);
        System.out.println("idoo"+id);

        intent.putExtra("id",id);
        intent.putExtra("name",name);
        intent.putExtra("email",email);

        startActivity(intent);
    }

    private  void id_intent2(String id){
        Intent intent=new Intent(LoginAcitivty.this,musteriSiparislerActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    public  void openErrorActivity(){
        System.out.println("hatalı sayfa");
       Intent intent=new Intent(this,ErrorActivity.class);
       startActivity(intent);
    }

}