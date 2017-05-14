package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.AppVariable;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.Saved;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.Search;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.R;

/**
 * Created by nerdywoffy on 5/13/17.
 */

public class SavedAdapter extends RecyclerView.Adapter<SavedViewHolders> {
    private List<Saved> itemList;
    private Context context;

    public SavedAdapter(Context context, List<Saved> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public SavedViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_list, null);
        SavedViewHolders rcv = new SavedViewHolders(layoutView);
        return rcv;

    }

    @Override
    public void onBindViewHolder(SavedViewHolders holder, int position) {


        //holder.savedYear.setText(itemList.get(position).getYear());

        String ov = itemList.get(position).getOverview();
        if(ov.length() >= 256){
            holder.savedOverview.setText(ov.substring(0,256) + "...");
        }else{
            holder.savedOverview.setText(ov);
        }

        holder.savedTitle.setText(itemList.get(position).getTitle());
        //Log.d("APPIMG",itemList.get(position).getPicture());
        //Glide.with(context).load(AppVariable.TMDB_BASEPATH_IMG+itemList.get(position).getImage()).placeholder(R.drawable.ic_tmdb_icon_green).into(holder.searchPicture);


    }


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
