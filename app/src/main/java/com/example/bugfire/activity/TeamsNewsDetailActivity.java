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
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.model.NewsDetail;
import com.example.bugfire.rabbitconverter.Rabbit;
import com.example.bugfire.response.NewsDetailResponse;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class TeamsNewsDetailActivity extends AppCompatActivity implements Html.ImageGetter {

    private ImageView featurephoto;
    private TextView tvtitle, tvname, tvdate, tvabout;
    private int id;
    private final static String TAG = "TestImageGetter";
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_news_detail);
        initActivity();
    }

    private void initActivity() {

        compositeDisposable = new CompositeDisposable();
        featurephoto = findViewById(R.id.featurephoto);
        tvtitle = findViewById(R.id.tvtitle);
        tvname = findViewById(R.id.tvname);
        tvdate = findViewById(R.id.tvtime);
        tvabout = findViewById(R.id.tvabout);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        Log.e("Id", String.valueOf(id));
        getTeamsNewsDetail();
    }

    private void getTeamsNewsDetail() {

        Disposable subscribe = RetrofitService.getApiEnd().getNewDetail(id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);
    }

    private void handleError(Throwable throwable) {
        Log.e("failure", throwable.toString());
    }

    private void handleResult(NewsDetailResponse newsDetailResponse) {
        Log.e("response", "success");
        NewsDetail newsDetail = newsDetailResponse.newsDetail;
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + newsDetail.featurePhoto).into(featurephoto);
        String categoryName = newsDetail.categoryName.get(0);
        for (int i = 1; i < newsDetail.categoryName.size(); i++) {
            categoryName += "," + newsDetail.categoryName.get(i);
        }
        Spanned spanned = Html.fromHtml(Rabbit.uni2zg(newsDetail.content), TeamsNewsDetailActivity.this, null);
        Spanned spanned1 = Html.fromHtml(Rabbit.zg2uni(newsDetail.content), TeamsNewsDetailActivity.this, null);
        tvabout.setMovementMethod(LinkMovementMethod.getInstance());

        if (userFont.equals("z")) {
            tvtitle.setText(Rabbit.uni2zg(newsDetail.title));
            tvname.setText(Rabbit.uni2zg(categoryName));
            tvdate.setText(Rabbit.uni2zg(newsDetail.date));
            tvabout.setText(spanned);
        } else {
            tvtitle.setText(Rabbit.zg2uni(newsDetail.title));
            tvname.setText(Rabbit.zg2uni(categoryName));
            tvtitle.setText(Rabbit.zg2uni(newsDetail.date));
            tvabout.setText(spanned1);
        }
    }

    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(R.drawable.defaultimage);
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
                mDrawable.setBounds(0, 0, 700, 400);
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = tvabout.getText();
                tvabout.setText(t);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }

}
