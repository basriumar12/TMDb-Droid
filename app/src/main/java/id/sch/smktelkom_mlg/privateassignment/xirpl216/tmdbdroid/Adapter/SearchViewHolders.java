package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.R;

/**
 * Created by nerdywoffy on 5/13/17.
 */

public class SearchViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView searchTitle,searchOverview,searchYear;
    public ImageView searchPicture;

    public SearchViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        searchTitle = (TextView) itemView.findViewById(R.id.searchTitle);
        searchOverview = (TextView) itemView.findViewById(R.id.searchOverview);
        searchYear = (TextView) itemView.findViewById(R.id.searchYear);
        searchPicture = (ImageView) itemView.findViewById(R.id.searchPhoto);
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
