package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.DB.TVShows;

public class ItemDetailActivityTV extends AppCompatActivity {

    TextView OriginalName,Overview,Status,CreatedBy,ProductionCompany,FirstAiring,LastAiring,Seasons,VoteAverage;
    public TVShows tv = new TVShows();
    public Boolean isFinished = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_tv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        OriginalName = (TextView)findViewById(R.id.tvTvOriginalName);
        Overview = (TextView)findViewById(R.id.tvTvOverview);
        Status = (TextView)findViewById(R.id.tvTvStatus);
        CreatedBy = (TextView)findViewById(R.id.tvTvCreatedBy);
        ProductionCompany = (TextView)findViewById(R.id.tvTvCreatorCompany);
        FirstAiring = (TextView)findViewById(R.id.tvTvFirstAirDate);
        LastAiring = (TextView)findViewById(R.id.tvTvLastAirDate);
        Seasons = (TextView)findViewById(R.id.tvTvSeasons);
        VoteAverage = (TextView)findViewById(R.id.tvTvVoteAverage);
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFinished == true){
                    tv.save();
                    Snackbar.make(view, "TV Show Archived, You can show this information anytime without internet now.", Snackbar.LENGTH_LONG)
                            .setAction("Got It", null).show();
                }
            }
        });

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        if(Prefs.getBoolean("showTipsAtDetail",true) == true){
            new ShowcaseView.Builder(this)
                    .setContentTitle("Tips")
                    .setContentText("You can save your favourite TV Show or Movie offline, Click this button to save it.")
                    .setTarget(new ViewTarget(R.id.fab,this))
                    .setStyle(10)
                    .hideOnTouchOutside()
                    .withMaterialShowcase()
                    .build();
            Prefs.putBoolean("showTipsAtDetail",false);
        }

        setTitle(getIntent().getStringExtra("title"));

        if(getIntent().getBooleanExtra("archive",false) == true){

            fetchOffline();
        }else{

            showSomeTVShowGoodness();
        }
    }

    private void fetchOffline() {
        Log.d("APPID",getIntent().getStringExtra("id"));
        List<TVShows> ltv = TVShows.find(TVShows.class,"tv_id = ?",getIntent().getStringExtra("id"));
        TVShows myTv = ltv.get(0);

        OriginalName.setText(myTv.getOriginalTitle());
        Overview.setText(myTv.getOverview());
        Status.setText(myTv.getStatus());
        CreatedBy.setText(myTv.getCreatedBy());
        ProductionCompany.setText(myTv.getProductionCompany());
        FirstAiring.setText(myTv.getFirstAiringDate());
        LastAiring.setText(myTv.getLastAiringDate());
        Seasons.setText(myTv.getAmountOfSeasons());
        VoteAverage.setText(myTv.getVoteAverage());

        final CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout2);

        Glide.with(ItemDetailActivityTV.this).load(AppVariable.TMDB_BASEPATH_IMG_ORIGINAL+myTv.getImage()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable d = new BitmapDrawable(getResources(),resource);
                ctl.setBackground(d);
            }
        });
    }

    private void showSomeTVShowGoodness() {
        RequestQueue rq = Volley.newRequestQueue(this);
        String url = "https://api.themoviedb.org/3/tv/" + getIntent().getStringExtra("id") + "?api_key=" + AppVariable.TMDB_APIKEY;
        JsonObjectRequest jObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    tv.setTitle(getIntent().getStringExtra("title"));
                    tv.setTvId(getIntent().getStringExtra("id"));
                    OriginalName.setText(response.getString("original_name"));
                    tv.setOriginalTitle(response.getString("original_name"));
                    Overview.setText(response.getString("overview"));
                    tv.setOverview(response.getString("overview"));
                    Status.setText(response.getString("status"));
                    tv.setStatus(response.getString("status"));
                    try {
                        String mixedCreatedBy = "";
                        JSONArray createdByArray = response.getJSONArray("created_by");
                        for (int i = 0; i < createdByArray.length(); i++) {
                            JSONObject jObj = (JSONObject) createdByArray.get(i);
                            if (i == 0) {
                                mixedCreatedBy = jObj.getString("name");
                            } else {
                                mixedCreatedBy = mixedCreatedBy + ", " + jObj.getString("name");
                            }
                        }
                        CreatedBy.setText(mixedCreatedBy);
                        tv.setCreatedBy(mixedCreatedBy);
                    }catch(Exception ex){
                        CreatedBy.setText("N/A");
                        tv.setCreatedBy("N/A");
                    }

                    try{
                    String mixedCompany = "";
                    JSONArray companyByArray = response.getJSONArray("production_companies");
                    for(int i = 0; i < companyByArray.length(); i++){
                        JSONObject jObj = (JSONObject) companyByArray.get(i);
                        if(i == 0){
                            mixedCompany = jObj.getString("name");
                        }else{
                            mixedCompany = mixedCompany + ", " + jObj.getString("name");
                        }
                    }

                    ProductionCompany.setText(mixedCompany);
                    tv.setProductionCompany(mixedCompany);
                    }catch(Exception ex){

                        ProductionCompany.setText("N/A");
                        tv.setProductionCompany("N/A");
                    }

                    FirstAiring.setText(response.getString("first_air_date"));
                    tv.setFirstAiringDate(response.getString("first_air_date"));
                    LastAiring.setText(response.getString("last_air_date"));
                    tv.setLastAiringDate(response.getString("last_air_date"));
                    try {
                        Integer amountOfSeasons = 0;

                        Integer amountOfTotalEpisodes = 0;
                        JSONArray seasonsByArray = response.getJSONArray("seasons");
                        amountOfSeasons = seasonsByArray.length();
                        for (int i = 0; i < seasonsByArray.length(); i++) {
                            JSONObject jObj = (JSONObject) seasonsByArray.get(i);
                            Integer episodes = jObj.getInt("episode_count");
                            amountOfTotalEpisodes = amountOfTotalEpisodes + episodes;
                        }

                        Seasons.setText(String.format("%s Seasons with %s Total Episodes", String.valueOf(amountOfSeasons), String.valueOf(amountOfTotalEpisodes)));
                        tv.setAmountOfSeasons(String.format("%s Seasons with %s Total Episodes", String.valueOf(amountOfSeasons), String.valueOf(amountOfTotalEpisodes)));
                    }catch (Exception ex){
                        Seasons.setText("N/A");
                        tv.setAmountOfSeasons("N/A");

                    }
                    VoteAverage.setText(response.getString("vote_average"));
                    tv.setVoteAverage("N/A");
                    final CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout2);
                    tv.setImage(response.getString("poster_path"));
                    Glide.with(ItemDetailActivityTV.this).load(AppVariable.TMDB_BASEPATH_IMG_ORIGINAL+response.getString("poster_path")).asBitmap().into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Drawable d = new BitmapDrawable(getResources(),resource);
                            ctl.setBackground(d);
                        }
                    });

                    isFinished = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rq.add(jObj);
    }
}
