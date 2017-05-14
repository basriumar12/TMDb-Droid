package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter.MainGridAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.MainGrid;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainGridFragment extends Fragment {

    public String kind;
    public String type;
    public String url;
    public MainGridFragment() {
        // Required empty public constructor
    }
    public Integer page = 1;
    public List<MainGrid> mg;
    private GridLayoutManager lLayout;
    private RecyclerView rv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_grid, container, false);
        lLayout = new GridLayoutManager(getActivity(),2);
        rv = (RecyclerView) v.findViewById(R.id.mgRV);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lLayout);
        Bundle bundle = this.getArguments();
        if(!bundle.isEmpty()){

            kind = bundle.getString("kind");
            type = bundle.getString("type");
            Log.d("APP-INFO",kind);
            Log.d("APP-INFO",type);
        }

        doSomething();

        return v;
        
    }

    /*private List<MainGrid> fetchDataFromInternet() {

        //return mg;
    }*/

    private void doSomething() {
        Log.d("TRIGGER","DO SOMETHING IS TRIGGERED!");
        //final List<MainGrid> rowList = fetchDataFromInternet();
        mg = new ArrayList<MainGrid>();

        Bundle bundle = this.getArguments();
        if(!bundle.isEmpty()){
            kind = bundle.getString("kind");
            type = bundle.getString("type");
            Log.d("APP-INFO",kind);
            Log.d("APP-INFO",type);
        }

        RequestQueue rq = Volley.newRequestQueue(getContext());
        String url = String.format("https://api.themoviedb.org/3/%s/%s?api_key=%s&page=%s&language=en-US",kind,type,AppVariable.TMDB_APIKEY,String.valueOf(page));
        Log.d("??",url);
        JsonObjectRequest jObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jA = response.getJSONArray("results");

                    for(int i = 0; i < jA.length(); i++){
                        JSONObject jO = (JSONObject) jA.get(i);

                        String mvTitle = "", mvPic = "", mvId = "";
                        if(kind == "movie") {
                            mvTitle = jO.getString("title");
                            mvPic = jO.getString("poster_path");
                            mvId = jO.getString("id");
                        }else if(kind == "tv"){
                            mvTitle = jO.getString("name");
                            mvPic = jO.getString("poster_path");
                            mvId = jO.getString("id");
                        }

                        mvPic = AppVariable.TMDB_BASEPATH_IMG + mvPic;

                        Log.d("PIC",mvPic);
                        mg.add(new MainGrid(mvPic,mvTitle,mvId));

                        if(i == jA.length() - 1){
                            // At Least it works btw.
                            setTv();
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

        for(int i = 0; i < 20; i++){
            //mg.add(new MainGrid("https://image.tmdb.org/t/p/w500_and_h281_bestv2/tQkigP2fItdzJWvtIhBvHxgs5yE.jpg","It's a fucking long movie title " + String.valueOf(i)));
        }
    }

    private void setTv() {
        MainGridAdapter mgAdapter = new MainGridAdapter(getActivity(),mg);
        rv.setAdapter(mgAdapter);
        if(rv.getAdapter() != null) {
            rv.swapAdapter(mgAdapter, true);
        }
        rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MainGrid selectedMG = mg.get(position);
                if(kind == "movie"){
                    Intent i = new Intent(getContext(),ItemDetailActivityMovie.class);
                    i.putExtra("title",selectedMG.getTitle());
                    i.putExtra("id",selectedMG.getId());
                    startActivity(i);
                }else if(kind == "tv"){
                    Intent i = new Intent(getContext(),ItemDetailActivityTV.class);
                    i.putExtra("title",selectedMG.getTitle());
                    i.putExtra("id",selectedMG.getId());
                    startActivity(i);
                }
                //Toast.makeText(getContext(),selectedMG.getId(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


}



