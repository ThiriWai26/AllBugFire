package com.example.bugfire.holder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsImageGridAdapter;
import com.example.bugfire.model.Feeds;
import com.example.bugfire.rabbitconverter.Rabbit;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class FeedsHolder extends RecyclerView.ViewHolder implements Html.ImageGetter {

    private OnFeedClickListener listener;
    private TextView txName, txTime, txabout;
    private ImageView profile, logo;
    private final static String TAG = "TestImageGetter";
    private RecyclerView recyclerView1;
    private FeedsImageGridAdapter feedsImageGridAdapter;

    public FeedsHolder(@NonNull View itemView, OnFeedClickListener listener) {
        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.tvName);
        txTime = itemView.findViewById(R.id.tvTime);
        txabout = itemView.findViewById(R.id.tvabout);
        profile = itemView.findViewById(R.id.profile);
        logo = itemView.findViewById(R.id.logo);
        recyclerView1 = itemView.findViewById(R.id.recyclerView);

    }

    public static FeedsHolder create(LayoutInflater inflater, ViewGroup parent, FeedsHolder.OnFeedClickListener listener) {
        View view = inflater.inflate(R.layout.layout_feed_item, parent, false);
        return new FeedsHolder(view, listener);
    }

    public void BindData(Feeds feeds) {

        if (feeds.photo != null) {
            Log.e("photo", "null");
            feedsImageGridAdapter = new FeedsImageGridAdapter();
            recyclerView1.setAdapter(feedsImageGridAdapter);
            recyclerView1.setLayoutManager(new GridLayoutManager(itemView.getContext(),2));

            feedsImageGridAdapter.addItem(feeds.photo);
        }
        if (userFont.equals("z")) {
            txName.setText(Rabbit.uni2zg(feeds.name));
            txTime.setText(Rabbit.uni2zg(feeds.date));
            txabout.setText(Rabbit.uni2zg(feeds.content));


        } else {
            txName.setText(Rabbit.zg2uni(feeds.name));
            txTime.setText(Rabbit.zg2uni(feeds.date));
            txabout.setText(Rabbit.zg2uni(feeds.content));
        }

        txabout.setMovementMethod(LinkMovementMethod.getInstance());
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + feeds.categoryPhoto).into(profile);
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + feeds.sourceLogo).into(logo);


    }

    public interface OnFeedClickListener {
        void onPCFeeds(int i);
    }

    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getDrawable(String.valueOf(R.drawable.defaultimage));
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
                CharSequence t = txabout.getText();
                txabout.setText(t);
            }
        }
    }
}

