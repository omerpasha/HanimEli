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
import android.widget.Adapter;
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

public class sepettekileriListelemeActivity extends AppCompatActivity {
    int sepet_total=0;
    Button onay_btn;
    TextView toplam_tutar;
    ListView ListViewLesson;
    String decodedImage[]={};//bu  imagelerin encoded hali
    String mtitle []={};
    String urun_sahibi_id []={};
    String stitle[]={};
    String sName[]={};
    String sEmail[]={};
    Bitmap images[]={};
    String yedek_id[]={};
    private PostServiceSepettekileriListeleme postsServiceSepettekileriListeleme;
    private PostServiceSepettenSil postsServiceSepettenSil;
    private PostServiceSepetOnay postsServiceSepetOnay;
    public String id;

    public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sepettekileri_listeleme);
        ListViewLesson=findViewById(R.id.listView3);
        onay_btn=findViewById(R.id.sepet_onay_button);
        toplam_tutar=findViewById(R.id.sepet_toplam_tutar_tv);
        id=getIntent().getStringExtra("id");
        System.out.println("sepettekilerilistelemeacitvity id "+id);
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8085/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postsServiceSepettekileriListeleme = retrofit.create(PostServiceSepettekileriListeleme.class);
            postsServiceSepettenSil=retrofit.create(PostServiceSepettenSil.class);
            postsServiceSepetOnay=retrofit.create(PostServiceSepetOnay.class);
            sendPost(id);

            onay_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //burada onay_sepet tablosuna müşteri id'si ve ürünleri kaydedilecek
                    System.out.println("geldi mi bakalım "+id);
                    String mId=id;
                    sendPost3(mId);
                    id_intent();//onay activity geçişi için
                }
            });
            ListViewLesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(sepettekileriListelemeActivity.this,mtitle[position],Toast.LENGTH_SHORT).show();
                    System.out.println("ürün sepetten çıarılması için tıklandı");
                    System.out.println("çıkarılacak olan"+mtitle[position]);
                   mtitle=removeMtitleTheElement(mtitle,position);
                    removeMtitleTheElement(decodedImage,position);
                      stitle=removeMtitleTheElement(stitle,position);
                       //sEmail=removeMtitleTheElement(stitle,position);
                    sepettekileriListelemeActivity.adapter Adapter=new sepettekileriListelemeActivity.adapter(sepettekileriListelemeActivity.this,mtitle,stitle,sName,sEmail,images);
                    ListViewLesson.setAdapter(Adapter);
                    sendPost2(yedek_id[position]);
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void sendPost(String id) {
        PostsSepettekileriListeleme postsSepettekileriListeleme= new PostsSepettekileriListeleme();

        Call<PostsSepettekileriListeleme> call = postsServiceSepettekileriListeleme.sendPosts(postsSepettekileriListeleme);
        call.enqueue(new Callback<PostsSepettekileriListeleme>() {
            @Override
            public void onResponse(Call<PostsSepettekileriListeleme> call, Response<PostsSepettekileriListeleme> response) {
                for(int i=0;i<response.body().urunler.length;i++){
                    int fiyat= response.body().urunler[i].fiyat;
                    byte[] data = Base64.decode(response.body().urunler[i].encodedImage, Base64.DEFAULT);
                    Bitmap bm= BitmapFactory.decodeByteArray(data,0,data.length);
                    decodedImage=Arrays.copyOf(decodedImage, decodedImage.length + 1);
                    decodedImage[decodedImage.length - 1] = response.body().urunler[i].encodedImage;
                    images=Arrays.copyOf(images, images.length + 1);
                    images[images.length - 1] = bm;
                    mtitle = Arrays.copyOf(mtitle, mtitle.length + 1);
                    mtitle[mtitle.length - 1] = response.body().urunler[i].urun_ismi;
                    sName = Arrays.copyOf(sName, sName.length + 1);
                    sName[sName.length - 1] = response.body().urunler[i].urun_sahibi_name;
                    sEmail = Arrays.copyOf(sEmail, sEmail.length + 1);
                    sEmail[sEmail.length - 1] = response.body().urunler[i].urun_sahibi_email;
                    urun_sahibi_id = Arrays.copyOf(urun_sahibi_id, urun_sahibi_id.length + 1);
                    urun_sahibi_id[urun_sahibi_id.length - 1] = response.body().urunler[i].urun_sahibi_id;
                    stitle = Arrays.copyOf(stitle, stitle.length + 1);
                    stitle[stitle.length - 1] = Integer.toString(fiyat);
                    yedek_id = Arrays.copyOf(yedek_id, yedek_id.length + 1);
                    yedek_id[yedek_id.length - 1] = response.body().urunler[i]._id;
                    sepet_total+=fiyat;
                    sepettekileriListelemeActivity.adapter Adapter=new sepettekileriListelemeActivity.adapter(sepettekileriListelemeActivity.this,mtitle,stitle,sName,sEmail,images);
                    ListViewLesson.setAdapter(Adapter);
                    System.out.println("sname"+response.body().urunler[i].urun_sahibi_name);
                }
                toplam_tutar.setText("Toplam Tutar: "+sepet_total+"tl");
            }

            @Override
            public void onFailure(Call<PostsSepettekileriListeleme> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println("sorun var response tarafında sepettekileri listeleme");
                System.out.println(t.toString());
            }
        });
    }
    private void sendPost2(String id) {
        PostsSepettenSil postsSepettenSil= new PostsSepettenSil();
        postsSepettenSil.setId(id);
        Call<PostsSepettenSil> call = postsServiceSepettenSil.sendPosts2(postsSepettenSil);
        call.enqueue(new Callback<PostsSepettenSil>() {
            @Override
            public void onResponse(Call<PostsSepettenSil> call, Response<PostsSepettenSil> response) {
                if(response.code()==400){
                    System.out.println("silme işlemi tamam");
                }

            }

            @Override
            public void onFailure(Call<PostsSepettenSil> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println("sorun var response tarafında sepettekileri listeleme");
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
    public static String[] removeMtitleTheElement(String[] arr,
                                         int index)
    {
        System.out.println("burada");

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        String[] anotherArray = new String[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];

        }
        for(int j=0;j<+anotherArray.length;j++)
            System.out.println("kalanlar"+j+anotherArray[j]);

        return anotherArray;
    }

    private  void id_intent(){
        //sepet onaylama activity geçiş intent
        Intent intent=new Intent(sepettekileriListelemeActivity.this,MusteriSepetOnaylama.class);
       // System.out.println("idoo"+id);
        //intent.putExtra("id",id);
        startActivity(intent);
    }
    private void sendPost3(String mId) {
        PostsSepetOnay postsSepetOnay= new PostsSepetOnay();
        Call<PostsSepetOnay> call=null;
        for(int i=0;i<stitle.length;i++){
            //System.out.println("onaylanan sepettekiler"+mtitle[i]);
            postsSepetOnay.setUrun_ismi(mtitle[i]);
            postsSepetOnay.setFiyat(Integer.parseInt(stitle[i]));
            postsSepetOnay.setEncodedImage(decodedImage[i]);
            postsSepetOnay.setUrun_siparis_eden_id(mId);
            postsSepetOnay.setUrun_id(yedek_id[i]);
            postsSepetOnay.setUrun_sahibi_isim(sName[i]);
            postsSepetOnay.setUrun_sahibi_id(urun_sahibi_id[i]);

            System.out.println("ürün ismi sepet onay =>"+mtitle[i]);

            call=postsServiceSepetOnay.sendPosts3(postsSepetOnay);

            call.enqueue(new Callback<PostsSepetOnay>() {
                @Override
                public void onResponse(Call<PostsSepetOnay> call, Response<PostsSepetOnay> response) {
                    if(response.code()==200){
                        System.out.println("sepet onaylama başarılı");
                    }
                }

                @Override
                public void onFailure(Call<PostsSepetOnay> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("sorun var response tarafında sepettekileri listeleme");
                    System.out.println(t.toString());
                }
            });
        }
        }



}