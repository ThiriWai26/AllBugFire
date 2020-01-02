package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.model.Article;
import com.example.bugfire.model.ArticleDetail;
import com.example.bugfire.response.ArticleDetailResponse;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PubgDetailActivity extends AppCompatActivity implements Html.ImageGetter {

    private ImageView featurephoto;
    private TextView tvtitle, tvname, tvtime, tvabout;
    private int id = -1;
    private final static String TAG = "TestImageGetter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubg_detail);

        initActivity();
    }

    private void initActivity() {

        featurephoto= findViewById(R.id.featurephoto);
        tvtitle = findViewById(R.id.tvtitle);
        tvname = findViewById(R.id.tvname);
        tvtime = findViewById(R.id.tvtime);
        tvabout = findViewById(R.id.tvabout);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("categoryId");
        Log.e("categoryId",String.valueOf(id));

        getPubgDetail();
    }

    private void getPubgDetail() {
        Log.e("getPubgDetail", "success");
        RetrofitService.getApiEnd().getArticleDetail(id).enqueue(new Callback<ArticleDetailResponse>() {
            @Override
            public void onResponse(Call<ArticleDetailResponse> call, Response<ArticleDetailResponse> response) {
                if (response.isSuccessful()) {
                    Log.e("response", "success");

                    ArticleDetail articleDetail = response.body().articleDetail;
                    Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + articleDetail.featurePhoto).into(featurephoto);
                    tvtitle.setText(articleDetail.title);
                    String name = articleDetail.categoryName.get(0);
                    for(int i=1;i<articleDetail.categoryName.size();i++){
                        name+=","+articleDetail.categoryName.get(i);}
                    tvname.setText(name);
                    tvtime.setText(articleDetail.date);

                    Spanned spanned  = Html.fromHtml("<p class=\" __mm\" style=\"border: 0px; color: rgb(64, 64, 64); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 1.125rem; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\"><b>အာရှမှာ တစ်ခတ်တုန်းက အောင်မြင်မှုတွေ ရှိခဲ့တဲ့ မြန်မာ ဘောလုံး အသင်းဟာ ၂၀၂၂ ကာတာ ကမ္ဘာ့ဖလား ပြိုင်ပွဲ ဝင်ရောက် ယှဉ်ပြိုင်ဖို့ ခြေစစ်ပွဲ ဝင်ရောက်ခဲ့ပြီး အုပ်စု F မှာ ဂျပန်၊ ကာဂျီစတန်၊ တာဂျီကစ္စတန်၊ မွန်ဂိုလီးယားတို့နဲ့ တစ်အုပ်စုတည်း ကျရောက်ခဲ့ပါတယ်။</b></p><p class=\" __mm\" style=\"border: 0px; color: rgb(64, 64, 64); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 1.125rem; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\">မြန်မာအသင်းဟာ အုပ်စု အဖွင့်ပွဲမှာ ကမ္ဘာ့အဆင့် ၁၈၇ ရှိတဲ့ မွန်ဂိုလီးယားကို ၁ဂိုး-ဂိုးမရှိနဲ့ ရှုံးခဲ့ပါတယ်။ အိမ်ရှင်အသင်းက ပထမပိုင်းကတည်းက သွင်းထားတဲ့ ဦးဆောင်ဂိုးကို ပြန်မချေနိုင်ဘဲ ရှုံးခဲ့တာပါ။ မြန်မာဟာ လာမယ့် စက်တင်ဘာ ၁ဝရက်နေ့မှာတော့ ကမ္ဘာ့ဖလား ပြိုင်ပွဲကို ၆ကြိမ်ဆက်တိုက် ယှဉ်ပြိုင်နေတဲ့ နာမည်ကြီး ဂျပန်အသင်းနဲ့ ကစားရမှာပါ။ ဂျပန်အတွက်တော့ ပထမဆုံးပွဲဖြစ်သလို ဥရောပရောက် ကစားသမားတွေ အားလုံးကို ပြန်ခေါ်ထားတာ ဖြစ်ပါတယ်။</p><p class=\" __mm\" style=\"border: 0px; color: rgb(64, 64, 64); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 1.125rem; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\"><img src=\"http:https://ichef.bbci.co.uk/news/624/cpsprodpb/8FD0/productihttps://ichef.bbci.co.uk/news/624/cpsprodpb/8FD0/production/_108661863_jae.jpghttps://ichef.bbci.co.uk/news/624/cpsprodpb/8FD0/production/_108661863_jae.jpgn/_108661863_jae.jpg//\" alt=\"i\" align=\"none\"><br></p><p class=\" __mm\" style=\"border: 0px; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\"><font color=\"#404040\"><span style=\"font-size: 11.25px; font-family: ZawGyi-One !important;\" class=\" __zg\">ျမန္မာနဲ႔ ဂ်ပန္တို႔ဟာ လက္ေ႐ြးစင္ အဆင့္မွာ ပထမဆုံးအႀကိမ္ ဆုံေတြ႕မွာျဖစ္ၿပီး ဂ်ပန္အသင္းေခါင္းေဆာင္ ယိုရွီဒါကေတာ့ သူ႔တို႔အသင္းရဲ႕ အဖြင့္ပြဲမွာ အေကာင္းဆုံး ျပင္ဆင္ထားတယ္လို႔ ဆိုပါတယ္။ ယိုရွီဒါဟာ ပရီးမီးယားလိဂ္ကလပ္ ေဆာက္သမ္တန္ အသင္းမွာ ကစားေနတဲ့ ေနာက္ခံလူ တစ္ဦးျဖစ္ၿပီး သူ႔အျပင္ ဂ်ပန္အသင္းမွာ ဥေရာပေရာက္ ကစားသမား ၁၉ေယာက္ အထိပါဝင္လာတာ ျဖစ္ပါတယ္။</span></font></p><p class=\" __mm\" style=\"border: 0px; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\"><font color=\"#404040\"><span style=\"font-size: 11.25px;\"><br></span></font></p><p class=\" __mm\" style=\"border: 0px; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\"><font color=\"#404040\"><span style=\"font-size: 11.25px; font-family: ZawGyi-One !important;\" class=\" __zg\">ဂ်ပန္အသင္းဟာ ၿပီးခဲ့တဲ့ စက္တင္ဘာ ၆ရက္ေန႔က ျမန္မာႏိုင္ငံကို ေရာက္ရွိလာခဲ့ ၿပီး စေနေန႔က ေလ့က်င့္ခန္း အစီစဥ္မ်ားကို သုဝဏၰ ေလ့က်င့္ေရး ကြင္း-၁မွာ ျပဳလုပ္ခဲ့ၾကပါတယ္။ ျမန္မာနဲ႔ ဂ်ပန္တို႔ဟာ လာမယ့္ စက္တင္ဘာ ၁ဝရက္၊ အဂၤါေန႔၊ ညေန ၆နာရီ ၅ဝမိနစ္မွာ ရန္ကုန္ၿမိဳ႕က သုဝဏၰကြင္းမွာ ယွဥ္ၿပိဳင္ ကစားၾကမွာ ျဖစ္ပါတယ္။</span></font></p><h2 class=\"story-body__crosshead __zg\" style=\"border: 0px; color: rgb(30, 30, 30); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-weight: inherit; line-height: 1.5; margin: 32px 0px 0px; padding: 0px; vertical-align: baseline;\">အုပ်စု F</h2><p class=\" __mm\" style=\"border: 0px; color: rgb(64, 64, 64); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 1.125rem; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\">ဂျပန် ( ကမ္ဘာ့အဆင့် ၃၃)</p><p class=\" __mm\" style=\"border: 0px; color: rgb(64, 64, 64); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 1.125rem; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\">ကာဂျီစတန် ( ကမ္ဘာ့အဆင့် ၉၅)</p><p class=\" __mm\" style=\"border: 0px; color: rgb(64, 64, 64); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 1.125rem; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\">တာဂျီကစ္စတန် ( ကမ္ဘာ့အဆင့် ၁၁၉)</p><p class=\" __mm\" style=\"border: 0px; color: rgb(64, 64, 64); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 1.125rem; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\">မြန်မာ ( ကမ္ဘာ့အဆင့် ၁၃၅)</p><p class=\" __mm\" style=\"border: 0px; color: rgb(64, 64, 64); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 1.125rem; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\">မွန်ဂိုးလီးယား ( ကမ္ဘာ့အဆင့် ၁၈၇)</p><p class=\" __mm\" style=\"border: 0px; color: rgb(64, 64, 64); font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 1.125rem; line-height: 1.55556; margin-top: 18px; margin-bottom: 0px; padding: 0px; vertical-align: baseline;\"><img src=\"https://i.imgur.com/MmBbPQS.jpg\" width=\"624\"><br></p>");
                    tvabout.setText(spanned);

                } else {
                    Log.e("response", response.body().errorMessage);
                }
            }

            @Override
            public void onFailure(Call<ArticleDetailResponse> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
      }

    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(R.drawable.noimageavailabe);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage().execute(source, d);

        return d;
    }

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            Log.d(TAG, "doInBackground " + source);
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG, "onPostExecute drawable " + mDrawable);
            Log.d(TAG, "onPostExecute bitmap " + bitmap);
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = tvabout.getText();
                tvabout.setText(t);
            }
        }
    }
}
