package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;

import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Movie;
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
import org.w3c.dom.Text;

import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter.MainGridAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.DB.Movies;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.MainGrid;

public class ItemDetailActivityMovie extends AppCompatActivity {

    TextView OriginalTitle, Overview, Status, ReleaseDate, Runtime, VoteAverage, Creator;
    Movies m = null;
    boolean isFinished = false;
    public ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        progress = new ProgressDialog(this);
        OriginalTitle = (TextView) findViewById(R.id.tvOriginalTitle);
        Overview = (TextView) findViewById(R.id.tvOverview);
        Status = (TextView) findViewById(R.id.tvStatus);
        ReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        Runtime = (TextView) findViewById(R.id.tvRuntime);
        VoteAverage = (TextView) findViewById(R.id.tvVoteAverage);
        Creator = (TextView) findViewById(R.id.tvCreator);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveToDb(view);
            }
        });

        setTitle(getIntent().getStringExtra("title"));

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();


        if(getIntent().getBooleanExtra("archive",false) == true){
            fab.setVisibility(View.INVISIBLE);
            fetchOffline();
            showGuide();
        }else{

            showSomeMovieGoodness();
            progress.setTitle("Loading");
            progress.setMessage("Fetching data from server");
            progress.setCancelable(false);
            progress.show();
        }
    }
    private void fetchOffline() {
        List<Movies> selectedMovie = Movies.find(Movies.class,"movie_id = ?",getIntent().getStringExtra("id"));
        Movies myMov = selectedMovie.get(0);
        setTitle(myMov.getTitle());
        OriginalTitle.setText(myMov.getOriginalTitle());
        Overview.setText(myMov.getOverview());
        Status.setText(myMov.getStatus());
        ReleaseDate.setText(myMov.getReleaseDate());
        Runtime.setText(myMov.getRuntime());
        VoteAverage.setText(myMov.getVoteAverage());
        Creator.setText(myMov.getCreator());

        final CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Glide.with(ItemDetailActivityMovie.this).load(AppVariable.TMDB_BASEPATH_IMG_ORIGINAL+myMov.getImage()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable d = new BitmapDrawable(getResources(),resource);
                ctl.setBackground(d);
            }
        });
    }

    private void showGuide(){

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
    }

    private void saveToDb(View view) {
        if(isFinished == true){
            //List<Movies> existsMovie = Movies.find(Movies.class,"movieId = ?",m.getMovieId());

            if(true){
                m.save();
                Snackbar.make(view, "Movie Archived, You can show this information anytime without internet now.", Snackbar.LENGTH_LONG)
                        .setAction("Got It", null).show();
            }

        }
    }

    private void showSomeMovieGoodness() {
        RequestQueue rq = Volley.newRequestQueue(this);
        String url = "https://api.themoviedb.org/3/movie/" + getIntent().getStringExtra("id") + "?api_key=" + AppVariable.TMDB_APIKEY;
        JsonObjectRequest jObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    OriginalTitle.setText(response.getString("original_title"));
                    Overview.setText(response.getString("overview"));
                    Status.setText(response.getString("status"));

                    String creatorTxt = "";
                    JSONArray creator = response.getJSONArray("production_companies");
                    for(int i = 0; i < creator.length(); i++){
                        JSONObject jObj = (JSONObject) creator.get(i);
                        if(i == 0){
                            creatorTxt = jObj.getString("name");
                        }else{
                            creatorTxt = creatorTxt + ", " + jObj.getString("name");
                        }
                    }

                    Creator.setText(creatorTxt);
                    ReleaseDate.setText(response.getString("release_date"));
                    Runtime.setText(response.getString("runtime") + " Minutes");
                    VoteAverage.setText(response.getString("vote_average"));

                    final CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

                    Glide.with(ItemDetailActivityMovie.this).load(AppVariable.TMDB_BASEPATH_IMG_ORIGINAL+response.getString("poster_path")).asBitmap().into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Drawable d = new BitmapDrawable(getResources(),resource);
                            ctl.setBackground(d);
                        }
                    });

                    m = new Movies(getIntent().getStringExtra("id"),response.getString("poster_path"),getIntent().getStringExtra("title"),response.getString("original_title"),response.getString("overview"),response.getString("status"),creatorTxt,response.getString("release_date"),response.getString("runtime"),response.getString("vote_average"),"");
                    isFinished = true;

                    progress.dismiss();
                    showGuide();
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
