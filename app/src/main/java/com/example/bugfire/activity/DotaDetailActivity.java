package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

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

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DotaDetailActivity extends AppCompatActivity implements Html.ImageGetter {

    private ImageView featurephoto;
    private TextView tvtitle, tvname, tvtime, tvabout;
    private int id = -1;
    private final static String TAG = "TestImageGetter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dota_detail);

        initActitity();
    }

    private void initActitity() {

        featurephoto= findViewById(R.id.featurephoto);
        tvtitle = findViewById(R.id.tvtitle);
        tvname = findViewById(R.id.tvname);
        tvtime = findViewById(R.id.tvtime);
        tvabout = findViewById(R.id.tvabout);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("categoryId");
        Log.e("categoryId",String.valueOf(id));

        getDotaDetail();

    }

    private void getDotaDetail() {
        Log.e("getDotaDetail", "success");
        RetrofitService.getApiEnd().getArticleDetail(id).enqueue(new Callback<ArticleDetailResponse>() {
            @Override
            public void onResponse(Call<ArticleDetailResponse> call, Response<ArticleDetailResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");

                    ArticleDetail articleDetail = response.body().articleDetail;
                    Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + articleDetail.featurePhoto).into(featurephoto);
                    tvtitle.setText(articleDetail.title);
                    String name=articleDetail.categoryName.get(0);
                    for(int i=1;i<articleDetail.categoryName.size();i++){
                        name+=","+articleDetail.categoryName.get(i);}
                    tvname.setText(name);
                    tvtime.setText(articleDetail.date);

                    Spanned spanned = Html.fromHtml("<h4>Sequi ducimus libero sequi in quia aut qui. Libero qui eum exercitationem.</h4><p>Iure quisquam ipsum et qui explicabo ratione tempore quos. Soluta mollitia aspernatur molestiae animi est voluptatem. Illo deleniti illum blanditiis vitae iusto id. Id reprehenderit earum alias. Dolorem et iusto repellendus numquam. Dicta expedita et molestiae amet. Numquam reiciendis accusamus nihil quas nisi aperiam eligendi molestias. Incidunt libero sed ipsa aliquam et. Non ipsum sed et qui eveniet ratione nihil. Ipsum deserunt recusandae quis incidunt quod voluptas. Magni ut quidem sed cumque atque. Quod et laborum quo voluptas non possimus aperiam. Dolores quo veritatis ipsam quia recusandae eveniet. Quo odio itaque sequi dolor consequatur. Quia voluptates autem eum cumque est et. Ullam ratione dolorem repellat iure amet est qui. Nemo mollitia saepe praesentium facilis velit reiciendis id. Blanditiis numquam omnis quibusdam. Suscipit vel omnis omnis quia id expedita. Omnis mollitia consequatur dolorem enim quod. Et molestiae natus sequi aut facere. Iusto iure recusandae odit hic. Id facilis accusamus praesentium est fuga soluta odio. Voluptate dolorem reiciendis consectetur minus. Et voluptas sed incidunt quidem. Qui atque deleniti dolorem. Velit ratione ducimus et neque mollitia labore est. Ad delectus odio est sit. Id ab possimus et. Non quis quibusdam reiciendis vitae. Quo voluptas facilis unde maiores optio ut. Atque aspernatur quia ipsam dolor sed hic. Consequatur aut quibusdam ullam quia repudiandae ipsa sint. Ipsum sint ut pariatur laboriosam. Maxime eligendi aut veritatis recusandae voluptates consequatur natus. Et sit nisi quia non repellendus. Officiis veniam ea dolores hic sequi mollitia. Suscipit doloribus laudantium eveniet architecto. Sit repellendus autem reprehenderit quia ab vel reprehenderit. Sed in id ut modi non repellat repudiandae. Quos sed veritatis cumque omnis vitae. Laboriosam officia quisquam ullam recusandae corporis qui architecto. Asperiores quis corporis aspernatur neque a. Nulla enim ipsa beatae illum consequatur aut. Magnam voluptatibus sed enim nemo assumenda cupiditate. Ut fugiat magni voluptatibus aliquam nobis ut alias. Optio ut numquam quaerat illo suscipit. Aut vel et cum porro exercitationem est odio. Ut omnis nihil ratione qui. Iure vitae culpa mollitia vitae. Labore cum et ab delectus voluptatem vitae accusantium et. Molestiae quibusdam aut est. Dignissimos doloribus quam nihil rerum. In voluptas sit ut commodi. Modi repellendus temporibus voluptas nihil ut est. Vitae laboriosam accusantium ut optio harum voluptatum. Quisquam et veniam aut qui est necessitatibus accusamus. Est quia rerum a ipsa quasi quibusdam id porro. Voluptas ipsam eaque totam quisquam. Rerum porro aliquid ea temporibus. Distinctio nihil a ipsa nobis aliquid sed. Dolor cum ut tenetur. Assumenda similique autem doloremque dolorem dolorem reprehenderit. Impedit eligendi dolores optio. Quo incidunt et reiciendis reiciendis aliquam praesentium eligendi. Praesentium provident saepe est explicabo. Impedit fuga consectetur sint optio quasi nobis harum. Neque natus architecto perspiciatis distinctio at delectus unde. Culpa reiciendis nisi voluptate ea vero necessitatibus. Et nobis dolores dolorum dolorem. Consequatur facilis et corrupti laborum. Magnam incidunt ex nulla accusantium ut. Iure voluptatem totam similique nihil quod est. Eos dolores qui consequatur quas officia nihil laudantium. Quos aut quia voluptatem aperiam quae consequatur tempora. Repudiandae consequatur eligendi nisi. Velit atque maxime nulla pariatur ad et. Et modi corrupti tempore. Aperiam enim quod tenetur debitis veritatis maxime qui. Repellendus maiores dolorem aut consequuntur repudiandae. Aut cumque accusantium recusandae ut ea fugiat aliquam. Sit a quas officia expedita. Voluptas praesentium ullam ducimus neque quaerat sint. In quas natus quas vitae dolorum fuga sint. Eveniet harum et sapiente consequatur. Necessitatibus numquam nobis esse tempore. Error facere harum blanditiis rerum. Sint velit autem nisi incidunt. At ex maiores sunt beatae voluptatem facilis esse. Harum et aut explicabo error corrupti. Sunt ducimus sint ea odio dolore unde. Iste porro nesciunt ut veniam nostrum vel sit. Excepturi tempora totam corporis expedita et illum. Dolor eum iure officia asperiores qui. Autem odit et quidem numquam quo. Eveniet est hic sed aspernatur sed. Voluptatem illum nihil voluptate officiis est eos asperiores. Et consectetur quia eum et ea pariatur quo. Harum inventore quae recusandae quae. Modi non dolores magnam debitis omnis laboriosam. Voluptatem vero accusamus aperiam. Nobis in ducimus et minima nemo odio. Et voluptas vel impedit dolores illum atque. Id facere occaecati quas quae maxime. Ipsum quod qui eaque et et sed minima. Provident repudiandae repudiandae id odio. Consequatur voluptas nulla sequi voluptas. Et nihil sed consequatur quo id nobis. Magni nemo atque dolore magnam cum. Eos non labore ut non. Dolor excepturi consequatur atque voluptas quod ad est. Ea aut adipisci est vero. Voluptatem sunt repellendus non aut dolore officiis sint. In consectetur qui repudiandae. Mollitia quidem voluptatem modi ut. Sapiente ipsum at explicabo corporis. Vitae molestiae recusandae quidem optio quos laudantium. Suscipit minima quia qui. Et blanditiis neque laborum dolores itaque. Iste accusamus quae odio temporibus iste. Quo sint est laborum occaecati ea. Commodi consectetur sed vero sunt vitae recusandae quo. Minus ea earum nisi aut laboriosam eius. Repudiandae voluptas vitae magni vero est est ut. Quaerat quo incidunt magni aut mollitia expedita enim. Quidem cumque magni ipsum nam eum nostrum tempora. Deleniti aspernatur est soluta ut quisquam quod sint. Vero molestiae reiciendis aut suscipit in quo dolorum. Modi unde ab facere architecto voluptatem praesentium. Id aut ut perferendis voluptatem dolor rerum inventore. Nemo maiores ea sit vel optio placeat. Necessitatibus animi sint et. Alias eaque quod minima totam minus alias. Aliquam saepe maxime cumque dolor velit sapiente in. Et ea sit sint. Et sint illum sint ratione eveniet accusamus enim. Quibusdam reprehenderit velit et explicabo sit molestiae ut. Minima molestiae quis enim. Laboriosam accusamus non saepe. Animi dicta et impedit ea enim ad debitis. Sed sequi deserunt officia recusandae ut. Reprehenderit itaque molestias ut molestiae aut aut. Et quia delectus deleniti et est. Quis quas ducimus ea explicabo fugiat. Quis voluptas vel ut minima soluta tenetur. Amet itaque accusamus sit nam quo velit. Voluptatibus eius exercitationem omnis nulla sed excepturi. Libero officiis praesentium consequatur et delectus accusantium odit odio. Temporibus pariatur maiores consequatur iste consequuntur. Nostrum odio dolorem suscipit. Quae assumenda ipsam et beatae nobis eligendi. Accusantium libero ut officiis enim cupiditate expedita. Natus nesciunt aut quod aut nihil voluptatem est. Similique quia ut ipsam officia. Velit et corrupti delectus doloremque nemo id aut dolorem. Quo tempora nisi debitis sit omnis quaerat saepe ab. Quia aut est tempora sed sequi laboriosam. Accusantium qui dolorum est eius enim impedit quia. Facere blanditiis voluptates et alias aperiam ut. Eos hic hic consequatur sit ipsam sed enim. Qui suscipit ut sit iure eaque quisquam quis. Laborum accusamus ut qui eveniet quia corrupti maxime. Perspiciatis dolorum dicta eaque ea. Voluptatum et tenetur in iusto quam. Vitae quia molestiae at accusantium asperiores ut nesciunt. Vitae nihil consequatur voluptas. Voluptatem aut quidem modi sunt sunt aliquid quam fuga. Tenetur atque laudantium quod non. Non eveniet dolorum iusto sed blanditiis omnis. Neque minus ea eligendi quia. Repudiandae quo ipsa laboriosam et voluptas voluptatem voluptas. Atque est odio dolorem unde et. Necessitatibus repudiandae illo dolorum deserunt eos odit aut. Est non inventore distinctio. Rerum reiciendis magni nesciunt quam provident quos reprehenderit provident. Aut eius similique libero sit. Porro ut adipisci ipsam in. Numquam laboriosam eius quod consequatur. Numquam consequuntur tempora omnis placeat. Aut repellat deleniti eaque eum qui tempora. Maxime asperiores non illo. Et non explicabo est est. Tenetur voluptatem similique facere alias expedita. Quibusdam consectetur ipsam reprehenderit non velit. Nostrum et repudiandae vitae. Hic quis asperiores ea. Nemo odit dolores exercitationem ut aperiam tempora et. Et libero et sunt sequi consequatur sed. Et voluptatem labore labore. Quisquam harum optio quis enim ipsam qui consectetur. Rerum non culpa harum est nobis saepe blanditiis. Est rem aut et. Rerum excepturi error nostrum ab. Assumenda eos odit laborum quia qui. Unde delectus ut quis ullam cupiditate voluptatem et cumque. Doloremque aut ut numquam quis ipsum. Ea voluptatem et quaerat nam. Natus quis dicta atque sed commodi. Sunt explicabo et voluptatum beatae delectus distinctio. Ad neque et a dolorem illo vel ducimus. Laborum non tempore consequuntur. Aut error ipsum deleniti consequatur corrupti ipsum. Odio qui quidem nulla totam. Et nesciunt veniam similique delectus. In non autem facilis. Consequatur enim corporis et odio aut illum in. Laboriosam nulla doloremque harum unde omnis hic optio ducimus. Expedita earum voluptas voluptatem quia consequatur natus voluptatem iusto. Quis neque aut aut. Debitis et ab qui necessitatibus rem. Sed et ad ea est aut officiis quia illo. Odit ut ipsam architecto perspiciatis. Consequatur cumque odio dolores repellendus reprehenderit. Inventore ut sed vel in. Et aspernatur corporis veniam dolorem recusandae. Tenetur suscipit ad repellat quia rerum repellendus. Fuga ab voluptatem earum sed reprehenderit. Modi rerum ut hic voluptatem nulla beatae. Non molestiae quos distinctio nam. Voluptas ad fuga minima. Eum cumque tempora aut sit aliquid minima aut. Nisi omnis perspiciatis omnis tempore natus optio dolores. Delectus labore aut reiciendis fuga atque velit. Inventore unde omnis aut iusto eum odit aut.</p><img src=\"http://192.168.100.39:8000/api/download_image/article5.jpg\"><p>Sit eos nesciunt repudiandae fuga consequatur. Temporibus maxime odio quaerat. Voluptatem distinctio sint voluptatibus. Sed maxime deserunt eos cumque voluptatibus excepturi ipsam.</p>",DotaDetailActivity.this,null);
//                  tvabout.setText(spanned);

//                    Spanned spanned = Html.fromHtml(articleDetail.content, DotaDetailActivity.this, null);
                    tvabout.setText(spanned);
//                    rabbit.zg2uni(String.valueOf(spanned));
//                    tvabout.setText(rabbit.uni2zg(String.valueOf(spanned)));
//                    tvabout.setText(rabbit.zg2uni(String.valueOf(spanned)));
//
//                    Log.e("rabbit", rabbit.uni2zg(String.valueOf(spanned)));
//                    Log.e("rabbit", rabbit.zg2uni(String.valueOf(spanned)));
                }
                else{
                    Log.e("response",response.body().errorMessage);
                }
            }

            @Override
            public void onFailure(Call<ArticleDetailResponse> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    @Override
    public Drawable getDrawable(String source){
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(R.drawable.imagenotavailable2);
        d.addLevel(0,0, empty);
        d.setBounds(0,0, empty.getIntrinsicWidth() , empty.getIntrinsicHeight());

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
                mDrawable.setBounds(0, 0, bitmap.getWidth() , bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = tvabout.getText();
                tvabout.setText(t);
            }
        }
    }
}
