package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Dictionary;

class SavedPagerAdapter extends FragmentPagerAdapter {

    public Dictionary<String,Fragment> fragmentDictionary;
    public SavedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new SavedListFragment();
        Bundle args = new Bundle();
        if(position == 0){
            args.putString("kind","movie");
        }else if(position == 1){
            args.putString("kind","tv");
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if(position == 0){
            title = "Movies";
        }else if(position == 1){
            title = "TV Shows";
        }
        return title;
    }
}


public class SavedActivity extends AppCompatActivity {

    SavedPagerAdapter spa;
    ViewPager vp;
    View ourView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        setTitle("Saved");
        spa = new SavedPagerAdapter(getSupportFragmentManager());
        vp = (ViewPager) findViewById(R.id.pager);
        vp.setAdapter(spa);
    }
}
