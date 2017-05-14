package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.R;

/**
 * Created by nerdywoffy on 5/13/17.
 */

public class SavedViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView savedTitle,savedOverview,savedYear;

    public SavedViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        savedTitle = (TextView) itemView.findViewById(R.id.savedTitle);
        savedOverview = (TextView) itemView.findViewById(R.id.savedOverview);
        //savedYear = (TextView) itemView.findViewById(R.id.savedYear);
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
