package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter.MainGridAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.MainGrid;

public class ItemDetailActivityMovie extends AppCompatActivity {

    TextView OriginalTitle, Overview, Status, ReleaseDate, Runtime, VoteAverage, Creator;

    boolean isFinished = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setTitle(getIntent().getStringExtra("title"));
        showSomeMovieGoodness();
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
                    Runtime.setText(response.getString("runtime"));
                    VoteAverage.setText(response.getString("vote_average"));
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
