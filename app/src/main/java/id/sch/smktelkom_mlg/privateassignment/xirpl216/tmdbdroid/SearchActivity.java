package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

public class SearchActivity extends AppCompatActivity {

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

        processTheFuckingData();
    }

    private void processTheFuckingData() {

    }
}
