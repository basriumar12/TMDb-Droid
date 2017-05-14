package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.AppVariable;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.MainGrid;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.Search;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.R;

/**
 * Created by nerdywoffy on 5/13/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolders> {
    private List<Search> itemList;
    private Context context;

    public SearchAdapter(Context context, List<Search> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public SearchViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list, null);
        SearchViewHolders rcv = new SearchViewHolders(layoutView);
        return rcv;

    }

    @Override
    public void onBindViewHolder(SearchViewHolders holder, int position) {

        holder.searchYear.setText(itemList.get(position).getYear());

        String ov = itemList.get(position).getOverview();
        if(ov.length() >= 128){
            holder.searchOverview.setText(ov.substring(0,128) + "...");
        }else{
            holder.searchOverview.setText(ov);
        }

        holder.searchTitle.setText(itemList.get(position).getTitle());
        //Log.d("APPIMG",itemList.get(position).getPicture());
        Glide.with(context).load(AppVariable.TMDB_BASEPATH_IMG+itemList.get(position).getImage()).placeholder(R.drawable.ic_tmdb_icon_green).into(holder.searchPicture);

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
