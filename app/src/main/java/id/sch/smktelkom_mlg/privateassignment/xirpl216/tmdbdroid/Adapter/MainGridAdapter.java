package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model.MainGrid;
import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.R;

/**
 * Created by nerdywoffy on 5/13/17.
 */

public class MainGridAdapter extends RecyclerView.Adapter<MainGridViewHolders> {
    private List<MainGrid> itemList;
    private Context context;

    public MainGridAdapter(Context context, List<MainGrid> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MainGridViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        MainGridViewHolders rcv = new MainGridViewHolders(layoutView);
        return rcv;

    }

    @Override
    public void onBindViewHolder(MainGridViewHolders holder, int position) {

        holder.itemName.setText(itemList.get(position).getTitle());
        //Log.d("APPIMG",itemList.get(position).getPicture());
        Glide.with(context).load(itemList.get(position).getPicture()).into(holder.itemPhoto);

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
