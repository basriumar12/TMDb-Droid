package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.R;

/**
 * Created by nerdywoffy on 5/13/17.
 */

public class MainGridViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView itemName;
    public ImageView itemPhoto;

    public MainGridViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemName = (TextView)itemView.findViewById(R.id.item_name);
        itemPhoto = (ImageView)itemView.findViewById(R.id.item_photo);
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
