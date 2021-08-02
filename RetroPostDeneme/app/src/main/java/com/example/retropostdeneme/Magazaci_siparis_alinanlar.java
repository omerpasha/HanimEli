package com.example.retropostdeneme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class Magazaci_siparis_alinanlar extends AppCompatActivity {
    private PostServiceSiparisAlinanlar postServiceSiparisAlinanlar;
    public String id;
    int sepet_total=0;
    double sirketin_kestigi=0;
    String sName[]={};
    String sEmail[]={};
    ListView ListViewLesson;
    TextView toplam_tutar;
    TextView sirket_kesintisi;
    TextView net_kazanc;
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
        setContentView(R.layout.activity_magazaci_siparis_alinanlar);

        id=getIntent().getStringExtra("id");
        ListViewLesson=findViewById(R.id.listView67);
        toplam_tutar=findViewById(R.id.sepet_toplam_tutar_tv2);
        sirket_kesintisi=findViewById(R.id.sirketin_kestigi_tv);
        net_kazanc=findViewById(R.id.net_tv);
        System.out.println("iddddddddddddd"+id);
        sepettekiler=findViewById(R.id.sepet_button);
        mid=id;
        try {
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8085/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postServiceSiparisAlinanlar = retrofit.create(PostServiceSiparisAlinanlar.class);
            sendPost(id);
            ListViewLesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(Magazaci_siparis_alinanlar.this,mtitle[position],Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void sendPost(String id) {
        PostsSiparisAlinanlar postsSiparisAlinanlar= new PostsSiparisAlinanlar();
        postsSiparisAlinanlar.setUrun_sahibi_id(id);
        Call<PostsSiparisAlinanlar> call = postServiceSiparisAlinanlar.sendPosts3(postsSiparisAlinanlar);
        call.enqueue(new Callback<PostsSiparisAlinanlar>() {
            @Override
            public void onResponse(Call<PostsSiparisAlinanlar> call, Response<PostsSiparisAlinanlar> response) {
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

                    sEmail = Arrays.copyOf(sEmail, sEmail.length + 1);
                    sEmail[sEmail.length - 1] = response.body().urunler[i].urun_sahibi_email;

                    mtitle = Arrays.copyOf(mtitle, mtitle.length + 1);
                    mtitle[mtitle.length - 1] = response.body().urunler[i].urun_ismi;
                    stitle = Arrays.copyOf(stitle, stitle.length + 1);
                    stitle[stitle.length - 1] = Integer.toString(fiyat);
                    Magazaci_siparis_alinanlar.adapter Adapter=new Magazaci_siparis_alinanlar.adapter(Magazaci_siparis_alinanlar.this,mtitle,stitle,sName,sEmail,images);
                    ListViewLesson.setAdapter(Adapter);
                    sepet_total+=fiyat;
                    sirketin_kestigi=0.50*response.body().urunler.length;
                }
                toplam_tutar.setText("Toplam Tutar: "+sepet_total+"tl");
                sirket_kesintisi.setText("Şirket Komisyonu :"+sirketin_kestigi);
                net_kazanc.setText("Toplam Net Kazanc :"+(sepet_total-sirketin_kestigi));
            }
            @Override
            public void onFailure(Call<PostsSiparisAlinanlar> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println("sorun var response tarafında");
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
}