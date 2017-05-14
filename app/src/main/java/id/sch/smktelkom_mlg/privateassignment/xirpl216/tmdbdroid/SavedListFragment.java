package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter.MainGridAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter.SavedAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.DB.Movies;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.DB.TVShows;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.MainGrid;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.Saved;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedListFragment extends Fragment {

    public Bundle b = null;
    public String kind = "";
    public SavedListFragment() {
        // Required empty public constructor
    }

    public List<Saved> sg;
    private GridLayoutManager lLayout;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_saved_list, container, false);

        b = this.getArguments();
        Log.d("APP","We're Called!");
        kind = b.getString("kind");
        lLayout = new GridLayoutManager(getActivity(),1);
        rv = (RecyclerView) v.findViewById(R.id.rvDB);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lLayout);
        sg = new ArrayList<Saved>();
        if(kind.equals("movie")){
            showSavedMovie();
        }else if(kind.equals("tv")){
            showSavedTV();
        }
        return v;
    }

    private void showSavedTV() {
        List<TVShows> tv = TVShows.listAll(TVShows.class);
        if(tv.size() != 0){
            for(int i = 0; i < tv.size(); i++){
                TVShows current = tv.get(i);

                Saved s = new Saved();
                s.setId(current.getTvId());
                s.setOverview(current.getOverview());
                s.setTitle(current.getTitle());
                s.setYear(current.getYear());
                sg.add(s);
            }
            notifyRv();
        }
    }

    private void showSavedMovie() {
        List<Movies> movies = Movies.listAll(Movies.class);
        if(movies.size() != 0){
            for(int i = 0; i < movies.size(); i++){
                Movies current = movies.get(i);

                Saved s = new Saved(current.getTitle(),current.getYear(),current.getMovieId(),current.getOverview());
                sg.add(s);
            }
            notifyRv();
        }
    }

    private void notifyRv(){
        SavedAdapter sgAdapter = new SavedAdapter(getActivity(),sg);
        rv.setAdapter(sgAdapter);
        if(rv.getAdapter() != null) {
            rv.swapAdapter(sgAdapter, true);
        }
        rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Saved selectedMG = sg.get(position);
                Log.d("SMG",selectedMG.getTitle());
                if(kind == "movie"){
                    Intent i = new Intent(getContext(),ItemDetailActivityMovie.class);
                    i.putExtra("title",selectedMG.getTitle());
                    i.putExtra("id",selectedMG.getId());
                    i.putExtra("archive",true);
                    startActivity(i);
                }else if(kind == "tv"){
                    Intent i = new Intent(getContext(),ItemDetailActivityTV.class);
                    i.putExtra("title",selectedMG.getTitle());
                    i.putExtra("id",selectedMG.getId());
                    i.putExtra("archive",true);
                    startActivity(i);
                }
                //Toast.makeText(getContext(),selectedMG.getId(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Saved SelectedMG = sg.get(position);
                String dataID = SelectedMG.getId();

                if(kind == "movie"){
                    List<Movies> selectedMovies = Movies.find(Movies.class,"movie_id = ?",dataID);
                    Movies m = selectedMovies.get(0);
                    Boolean isDeleted = m.delete();

                }else if(kind == "tv"){
                    List<TVShows> selectedTVShows = TVShows.find(TVShows.class,"tv_id = ?",dataID);
                    TVShows t = selectedTVShows.get(0);
                    Boolean isDeleted = t.delete();
                }
            }
        }));
    }


}
