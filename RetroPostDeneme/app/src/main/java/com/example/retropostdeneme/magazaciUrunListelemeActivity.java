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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class magazaciUrunListelemeActivity extends AppCompatActivity {
    ListView ListViewLesson;
    Button siparis_alinanlar_button;
    String decodedImage[]={};//bu  imagelerin encoded hali
    String mtitle []={};
    String stitle[]={};
    Bitmap images[]={};
    String yedek_id[]={};
    private PostServiceMagazaciUrunListeleme postsServiceMagazaciUrunListeleme;
    private PostServiceMagazaciListedenUrunSil postsServiceMagazaciListedenUrunSil;
    public String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazaci_urun_listeleme);
        ListViewLesson=findViewById(R.id.listView);
        siparis_alinanlar_button=findViewById(R.id.sepet_button2);
        id=getIntent().getStringExtra("id");
        System.out.println("idt"+id);
        ListViewLesson=findViewById(R.id.listView);
        siparis_alinanlar_button=findViewById(R.id.sepet_button2);
        id=getIntent().getStringExtra("id");
        System.out.println("idt"+id);
        siparis_alinanlar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_intent2(id);
            }
        });

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8085/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postsServiceMagazaciUrunListeleme = retrofit.create(PostServiceMagazaciUrunListeleme.class);
            postsServiceMagazaciListedenUrunSil=retrofit.create(PostServiceMagazaciListedenUrunSil.class);
            sendPost(id);
            ListViewLesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(magazaciUrunListelemeActivity.this,mtitle[position],Toast.LENGTH_SHORT).show();
                    System.out.println("çıkarılacak olan"+mtitle[position]);
                    mtitle=removeMtitleTheElement(mtitle,position);
                    removeMtitleTheElement(decodedImage,position);
                    stitle=removeMtitleTheElement(stitle,position);
                    magazaciUrunListelemeActivity.adapter Adapter=new magazaciUrunListelemeActivity.adapter(magazaciUrunListelemeActivity.this,mtitle,stitle,images);
                    ListViewLesson.setAdapter(Adapter);
                    sendPost2(yedek_id[position]);
                    //System.out.println("yedek id "+yedek_id[position]);
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void sendPost(String id) {
        PostsMagazaciUrunListeleme postsMagazaciUrunListeleme = new PostsMagazaciUrunListeleme();
        postsMagazaciUrunListeleme.setBody("Example");
        postsMagazaciUrunListeleme.setUrun_sahibi_id(id);
        System.out.println("içeridekiii id"+id);
        Call<PostsMagazaciUrunListeleme> call = postsServiceMagazaciUrunListeleme.sendPosts(postsMagazaciUrunListeleme);
       call.enqueue(new Callback<PostsMagazaciUrunListeleme>() {
           @Override
           public void onResponse(Call<PostsMagazaciUrunListeleme> call, Response<PostsMagazaciUrunListeleme> response) {
               for(int i=0;i<response.body().urunler.length;i++){
                   String deneme []={};
                    int fiyat= response.body().urunler[i].fiyat;
                   byte[] data = Base64.decode(response.body().urunler[i].encodedImage, Base64.DEFAULT);
                   Bitmap bm= BitmapFactory.decodeByteArray(data,0,data.length);
                   images=Arrays.copyOf(images, images.length + 1);
                   images[images.length - 1] = bm;
                   mtitle = Arrays.copyOf(mtitle, mtitle.length + 1);
                   mtitle[mtitle.length - 1] = response.body().urunler[i].urun_ismi;
                   stitle = Arrays.copyOf(stitle, stitle.length + 1);
                   stitle[stitle.length - 1] = Integer.toString(fiyat);
                   adapter Adapter=new adapter(magazaciUrunListelemeActivity.this,mtitle,stitle,images);
                   ListViewLesson.setAdapter(Adapter);
                   yedek_id = Arrays.copyOf(yedek_id, yedek_id.length + 1);
                   yedek_id[yedek_id.length - 1] = response.body().urunler[i]._id;
               }
           }
           @Override
           public void onFailure(Call<PostsMagazaciUrunListeleme> call, Throwable t) {
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
        Bitmap rimg[];

        adapter(Context c,String title[],String desc[],Bitmap images[] ){
            super(c,R.layout.row ,R.id.textViewLesson1,title);
            this.context=c;
            this.rTitle=title;
            this.rDesc=desc;
            this.rimg=images;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE );
            View  row=layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images=row.findViewById(R.id.lessonImage);
            TextView myTitle=row.findViewById(R.id.textViewLesson1);
            TextView myDesc=row.findViewById(R.id.textViewLesson2);
            images.setImageBitmap(rimg[position]);
            myTitle.setText(rTitle[position]);
            myDesc.setText(rDesc[position]);
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
    private void sendPost2(String id) {
        PostsMagazaciListedenUrunSil postsMagazaciListedenUrunSil= new PostsMagazaciListedenUrunSil();
        postsMagazaciListedenUrunSil.setId(id);
        Call<PostsMagazaciListedenUrunSil> call = postsServiceMagazaciListedenUrunSil.sendPosts2(postsMagazaciListedenUrunSil);
        call.enqueue(new Callback<PostsMagazaciListedenUrunSil>() {
            @Override
            public void onResponse(Call<PostsMagazaciListedenUrunSil> call, Response<PostsMagazaciListedenUrunSil> response) {
                if(response.code()==400){
                    System.out.println("silme işlemi tamam");
                }

            }

            @Override
            public void onFailure(Call<PostsMagazaciListedenUrunSil> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println("sorun var response tarafında magazaci urun silme");
                System.out.println(t.toString());
            }
        });
    }
    private  void id_intent2(String id){
        Intent intent=new Intent(magazaciUrunListelemeActivity.this,Magazaci_siparis_alinanlar.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}