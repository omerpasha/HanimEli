package com.example.retropostdeneme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class musteriSiparislerActivity extends AppCompatActivity {

    private PostServiceMusteriSiparisler postServiceMusteriSiparisler;
    private PostServiceSepeteEkleme postServiceSepeteEkleme;
    public String id;
    String urun_sahibi_id[]={};
    String sName[]={};
    String sEmail[]={};
    ListView ListViewLesson;
    String mtitle []={};
    String stitle[]={};
    Bitmap images[]={};//burası decode edilip doldurulcacak
    Urun siparisler[]={};
    byte[] data={};
    String[] dataGonderilicek={};
    public String mid;
    Button sepettekiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteri_siparisler);
        id=getIntent().getStringExtra("id");
        ListViewLesson=findViewById(R.id.listView2);
        System.out.println("iddddddddddddd"+id);
        sepettekiler=findViewById(R.id.sepet_button);
        mid=id;
        sepettekiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("sepettekiler butonu tıklandı");
                id_intent2(id);
            }
        });
        try {
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8085/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postServiceMusteriSiparisler = retrofit.create(PostServiceMusteriSiparisler.class);
            sendPost(id);
            ListViewLesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(musteriSiparislerActivity.this,mtitle[position],Toast.LENGTH_SHORT).show();
                    Urun yeniUrun=new Urun();

                    yeniUrun.encodedImage= dataGonderilicek[position];
                    yeniUrun.urun_sahibi_id=urun_sahibi_id[position];
                    System.out.println("midddd"+mid);
                    yeniUrun.urun_ismi=mtitle[position];
                    String isim=stitle[position];
                    yeniUrun.fiyat=Integer.parseInt(isim);
                    System.out.println("yeniurun"+yeniUrun.fiyat+ yeniUrun.urun_ismi);
                    siparisler = Arrays.copyOf(siparisler, siparisler.length + 1);
                    siparisler[siparisler.length - 1] =yeniUrun;
                    postServiceSepeteEkleme=retrofit.create(PostServiceSepeteEkleme.class);
                    sendPost2(siparisler,mid);
                    System.out.println("yukarısı"+mid);
                    
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void sendPost(String id) {
        PostsMusteriSiparisler postsMusteriSiparisler= new PostsMusteriSiparisler();
        Call<PostsMusteriSiparisler> call = postServiceMusteriSiparisler.sendPosts(postsMusteriSiparisler);
        call.enqueue(new Callback<PostsMusteriSiparisler>() {
            @Override
            public void onResponse(Call<PostsMusteriSiparisler> call, Response<PostsMusteriSiparisler> response) {
                for(int i=0;i<response.body().urunler.length;i++){
                    String deneme []={};
                    int fiyat= response.body().urunler[i].fiyat;
                    dataGonderilicek=Arrays.copyOf(dataGonderilicek, dataGonderilicek.length + 1);
                    dataGonderilicek[dataGonderilicek.length - 1] = response.body().urunler[i].encodedImage;
                    data = Base64.decode(response.body().urunler[i].encodedImage, Base64.DEFAULT);
                    Bitmap bm= BitmapFactory.decodeByteArray(data,0,data.length);
                    images=Arrays.copyOf(images, images.length + 1);
                    images[images.length - 1] = bm;
                    sName = Arrays.copyOf(sName, sName.length + 1);
                    sName[sName.length - 1] = response.body().urunler[i].urun_sahibi_name;
                    urun_sahibi_id = Arrays.copyOf(urun_sahibi_id, urun_sahibi_id.length + 1);
                    urun_sahibi_id[urun_sahibi_id.length - 1] = response.body().urunler[i].urun_sahibi_id;
                    sEmail = Arrays.copyOf(sEmail, sEmail.length + 1);
                    sEmail[sEmail.length - 1] = response.body().urunler[i].urun_sahibi_email;
                    mtitle = Arrays.copyOf(mtitle, mtitle.length + 1);
                    mtitle[mtitle.length - 1] = response.body().urunler[i].urun_ismi;
                    stitle = Arrays.copyOf(stitle, stitle.length + 1);
                    stitle[stitle.length - 1] = Integer.toString(fiyat);
                  adapter Adapter=new adapter(musteriSiparislerActivity.this,mtitle,stitle,sName,sEmail,images);
                    ListViewLesson.setAdapter(Adapter);
                    System.out.println("urunsahibiidmusterisiparisler activity"+response.body().urunler[i].urun_sahibi_id);
                }
            }
            @Override
            public void onFailure(Call<PostsMusteriSiparisler> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println("sorun var response tarafında");
                System.out.println(t.toString());
            }
        });

    }
    private void sendPost2(Urun siparisler[],String id) {
        PostsSepeteEkleme postsSepeteEkleme=new PostsSepeteEkleme();
        Call<PostsSepeteEkleme>call=postServiceSepeteEkleme.sendPosts2(postsSepeteEkleme);
        int fiyat;
        String encoded;
        for(int i=0;i<siparisler.length;i++){
          id=siparisler[i].id;
          fiyat=siparisler[i].fiyat;
            postsSepeteEkleme.setUrun_sahibi_id(siparisler[i].urun_sahibi_id);
            encoded= siparisler[i].encodedImage;
            postsSepeteEkleme.setUrun_sahibi_id(siparisler[i].urun_sahibi_id);
            postsSepeteEkleme.setUrun_siparis_eden_id(mid);
            postsSepeteEkleme.setUrun_sahibi_mail(siparisler[i].urun_sahibi_name);
            postsSepeteEkleme.setUrun_ismi(siparisler[i].urun_ismi);
            postsSepeteEkleme.setEncodedImage(encoded);
            postsSepeteEkleme.setFiyat(siparisler[i].fiyat);

            //System.out.println("sepete ekleme sipariş eden mid"+mid);


        }
         call.enqueue(new Callback<PostsSepeteEkleme>() {
             @Override
             public void onResponse(Call<PostsSepeteEkleme> call, Response<PostsSepeteEkleme> response) {
                 if(response.code()==200)
                     System.out.println("200  döndü");
             }


             @Override
             public void onFailure(Call<PostsSepeteEkleme> call, Throwable t) {
                 Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                 System.out.println("sorun var response tarafında siparisler");
                 System.out.println(t.toString());
             }
         });

    }


    class adapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rDesc[];
        String rName[];
        String rEmail[];
        Bitmap rimg[];

        adapter(Context c,String title[],String desc[],String Name[],String email[],Bitmap images[] ){
            super(c,R.layout.row2 ,R.id.textViewLesson11,title);
            this.context=c;
            this.rTitle=title;
            this.rDesc=desc;
            this.rName=Name;
            this.rEmail=email;
            this.rimg=images;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE );
            View  row=layoutInflater.inflate(R.layout.row2,parent,false);
            ImageView images=row.findViewById(R.id.lessonImage);
            TextView myTitle=row.findViewById(R.id.textViewLesson11);
            TextView myDesc=row.findViewById(R.id.textViewLesson22);
            TextView myName=row.findViewById(R.id.textViewLesson33);
            TextView myEmail=row.findViewById(R.id.textViewLesson44);
            images.setImageBitmap(rimg[position]);
            myTitle.setText(rTitle[position]);
            myDesc.setText(rDesc[position]);
            myName.setText(rName[position]);
            myEmail.setText(rEmail[position]);
            return row;
        }
    }
    private  void id_intent2(String id){
        Intent intent=new Intent(musteriSiparislerActivity.this,sepettekileriListelemeActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}