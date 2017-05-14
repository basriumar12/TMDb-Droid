package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter.SearchAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.MainGrid;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.Search;

public class SearchActivity extends AppCompatActivity {

    public List<Search> listItem;
    public GridLayoutManager lLayout;
    public RecyclerView rView;
    public SearchAdapter sAdapter;
    public String kind = "";
    public String query = "";
    public String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        kind = getIntent().getStringExtra("kind");
        query = getIntent().getStringExtra("query");
        if(kind != null && query != null){

            if(kind.equals("tv")){
                setTitle("TV Show Search");
            } else if (kind.equals("movie")) {
                setTitle("Movie Search");
            }else{
                Toast.makeText(getApplicationContext(),"Unable to process search.",Toast.LENGTH_SHORT).show();
                finish();
            }

        }else{
            Toast.makeText(getApplicationContext(),"Unable to process search.",Toast.LENGTH_SHORT).show();
            finish();
        }

        listItem = new ArrayList<Search>();
        lLayout = new GridLayoutManager(SearchActivity.this,1);
        rView = (RecyclerView)findViewById(R.id.rvSearch);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rView.addOnItemTouchListener(new RecyclerItemClickListener(SearchActivity.this, rView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Search selectedSearch = listItem.get(position);

                if(kind.equals("movie")){

                    Intent i = new Intent(SearchActivity.this,ItemDetailActivityMovie.class);
                    i.putExtra("title",selectedSearch.getTitle());
                    i.putExtra("id",selectedSearch.getId());
                    startActivity(i);
                }else if(kind.equals("tv")){
                    Intent i = new Intent(SearchActivity.this,ItemDetailActivityTV.class);
                    i.putExtra("title",selectedSearch.getTitle());
                    i.putExtra("id",selectedSearch.getId());
                    startActivity(i);
                }
                //Toast.makeText(SearchActivity.this,kind,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        if(kind.equals("movie")){
            processTheFuckingDataMovie();
        }else{
            processTheFuckingDataTV();
        }
    }

    private void processTheFuckingDataTV() {
        RequestQueue rq = Volley.newRequestQueue(this);
        String url = String.format("https://api.themoviedb.org/3/search/tv?api_key=%s&query=%s",AppVariable.TMDB_APIKEY,getIntent().getStringExtra("query"));

        JsonObjectRequest jObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("results");
                    Log.d("APPRESULT",String.valueOf(result.length()));

                    if(result.length() == 0){
                        Toast.makeText(SearchActivity.this,"Movies or TV Shows not Found!",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        for(int i = 0; i < result.length(); i++){
                            JSONObject jObject = (JSONObject) result.get(i);
                            Log.d("APPRESULT-TITLE",jObject.getString("name"));
                            listItem.add(new Search(jObject.getString("poster_path"),jObject.getString("name"),jObject.getString("first_air_date"),jObject.getString("id"),jObject.getString("overview")));
                            if(i == result.length() - 1){
                                tellTheFuckingRecyclerViewToShowResults();
                            }
                        }
                    }

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

    private void processTheFuckingDataMovie() {
        RequestQueue rq = Volley.newRequestQueue(this);
        String url = String.format("https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s",AppVariable.TMDB_APIKEY,getIntent().getStringExtra("query"));

        JsonObjectRequest jObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("results");
                    //Log.d("APPRESULT",String.valueOf(result.length()));

                    if(result.length() == 0){
                        Toast.makeText(SearchActivity.this,"Movies or TV Shows not Found!",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        for(int i = 0; i < result.length(); i++){
                            JSONObject jObject = (JSONObject) result.get(i);
                            //Log.d("APPRESULT-TITLE",jObject.getString("title"));
                            listItem.add(new Search(jObject.getString("poster_path"),jObject.getString("title"),jObject.getString("release_date"),jObject.getString("id"),jObject.getString("overview")));
                            if(i == result.length() - 1){
                                tellTheFuckingRecyclerViewToShowResults();
                            }
                        }
                    }

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

    private void tellTheFuckingRecyclerViewToShowResults() {
        sAdapter = new SearchAdapter(this,listItem);
        rView.setAdapter(sAdapter);

    }
}
