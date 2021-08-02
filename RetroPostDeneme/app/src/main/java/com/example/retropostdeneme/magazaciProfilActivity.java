package com.example.retropostdeneme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class magazaciProfilActivity extends AppCompatActivity {
      /*
        1-)Bu kısımda kamera erişim ve depolama izninin alınması gerek
        2-)Kameradan çekilen görüntü mağazacının profiline gönderilmeli
        3-)alınan encoded image sunucuya kaydedilecek
        4-)kaydedilen ürünmağazacının ürünleri arasına yerleştirilecek
    */
        String Tag="magazaci activity";
        private static final int REQUEST_CODE = 1;
        int request_code=1;
        String encodeImage2;
        private String id;
        ImageView imageView;
        EditText urun_ismi_edittext;
        EditText fiyat_edittext;
        Button urunEkleButtonu;
        Button urunleri_listele;
        String urun_ismi;
        String email;
        String name;
        int fiyat;
        private PostServiceMagazaciProfil postsService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazaci_profil);
        urunEkleButtonu=findViewById(R.id.urunEkle);
        urunleri_listele=findViewById(R.id.urunleri_listele);
        imageView=findViewById(R.id.imageView);
        urun_ismi_edittext=findViewById(R.id.urun_ismi);
        fiyat_edittext=findViewById(R.id.fiyat);
        id=getIntent().getStringExtra("id");
        email=getIntent().getStringExtra("email");
        name=getIntent().getStringExtra("name");

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8085/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postsService = retrofit.create(PostServiceMagazaciProfil.class);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        urunEkleButtonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyPermissions();
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                condition();
                String value= fiyat_edittext.getText().toString();
                fiyat=Integer.parseInt(value);
                urun_ismi=urun_ismi_edittext.getText().toString();
                startActivityForResult(intent,request_code);
            }
        });
        urunleri_listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("xxxid"+id);
                openMagazaciUrunListelemeActivity(id);
            }
        });
    }

    private void verifyPermissions(){
        Log.d(Tag,"verify permission");
        String [] permissions={
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),permissions[0])== PackageManager.PERMISSION_GRANTED &&
           ContextCompat.checkSelfPermission(this.getApplicationContext(),permissions[1])== PackageManager.PERMISSION_GRANTED &&
           ContextCompat.checkSelfPermission(this.getApplicationContext(),permissions[2])== PackageManager.PERMISSION_GRANTED){
           System.out.println("verify içerisinde");
        }
        else{
            ActivityCompat.requestPermissions(magazaciProfilActivity.this,permissions,REQUEST_CODE);
        }
    }
    private void condition(){
        if(ContextCompat.checkSelfPermission(magazaciProfilActivity.this,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(magazaciProfilActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    request_code);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verifyPermissions();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==request_code){
            Bitmap capturedImage=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(capturedImage);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            capturedImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            String encodeImage= android.util.Base64.encodeToString(byteArray, Base64.DEFAULT);
            //System.out.println("encodeeeeee"+encodeImage);
            encodeImage2=encodeImage;
            if(encodeImage2!=null)
                sendPost(id,encodeImage2,urun_ismi,fiyat,name,email);
        }
    }
    private void sendPost(String id,String encodeImage,String urun_ismi,int fiyat,String name,String email) {
        PostsMagazaciProfil post = new PostsMagazaciProfil();
        post.setBody("Example");

        System.out.println("urun sahibi id"+id);
        System.out.println("magazaciSendPostID"+id);
        post.setUrun_sahibi_email(email);
        post.setUrun_sahibi_name(name);
        post.setFiyat(fiyat);
        post.setUrun_sahibi_id(id);
        post.setUrun_ismi(urun_ismi);
        post.setEncodedImage(encodeImage);
        Call<PostsMagazaciProfil> call = postsService.sendPosts(post);
        call.enqueue(new Callback<PostsMagazaciProfil>() {
            @Override
            public void onResponse(Call<PostsMagazaciProfil> call, Response<PostsMagazaciProfil> response) {
                System.out.println("response..."+response.body());
            }
            @Override
            public void onFailure(Call<PostsMagazaciProfil> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void openMagazaciUrunListelemeActivity(String id){
        Intent intent=new Intent(magazaciProfilActivity.this,magazaciUrunListelemeActivity.class);
        intent.putExtra("id",id);
        System.out.println("idddddd"+id);
        startActivity(intent);
    }

}