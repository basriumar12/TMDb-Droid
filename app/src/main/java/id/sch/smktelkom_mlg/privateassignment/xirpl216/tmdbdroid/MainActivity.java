package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseDrawer;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.pixplicity.easyprefs.library.Prefs;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Fragment fragment = null;
    public Bundle bundle = new Bundle();
    public Toolbar toolbar;
    public  MenuItem myActionMenuItem;
    public String kind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.mov_popular);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.mov_popular));

        Target homeTarget = new Target() {
            @Override
            public Point getPoint() {
                // Get approximate position of home icon's center
                int actionBarSize = toolbar.getHeight();
                actionBarSize = actionBarSize;
                int x = actionBarSize / 2;
                int y = actionBarSize / 2;
                return new Point(x, y);
            }
        };

        if(Prefs.getBoolean("showTipsAtMain",true) == true){
            new ShowcaseView.Builder(this)
                    .setContentTitle("Get Started")
                    .setContentText("Swipe or click to begin, or you can search with Search button.")
                    .setTarget(homeTarget)
                    .setStyle(10)
                    .hideOnTouchOutside()
                    .withMaterialShowcase()
                    .build();
            Prefs.putBoolean("showTipsAtMain",false);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        myActionMenuItem = menu.findItem( R.id.search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }

                Intent i = new Intent(MainActivity.this,SearchActivity.class);
                i.putExtra("kind",kind);
                i.putExtra("query",query);

                startActivity(i);

                myActionMenuItem.collapseActionView();

                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }

        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String fragmentTitle = "";
        fragment = null;
        bundle = new Bundle();
        // Movies

        if(id == R.id.nav_saved){
            Intent i = new Intent(MainActivity.this,SavedActivity.class);
            startActivity(i);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        if(id == R.id.mov_popular){
            fragmentTitle = "Popular";
            fragment = new MainGridFragment();

            kind = "movie";
            bundle.putString("kind","movie");
            bundle.putString("type","popular");
        }else if(id == R.id.mov_now){
            fragmentTitle = "Now Playing";
            fragment = new MainGridFragment();
            kind = "movie";
            bundle.putString("kind","movie");
            bundle.putString("type","now_playing");
        }else if(id == R.id.mov_upcoming){
            fragmentTitle = "Upcoming";
            fragment = new MainGridFragment();
            kind = "movie";
            bundle.putString("kind","movie");
            bundle.putString("type","upcoming");
        }

        // TV Shows
        if(id == R.id.tv_popular){
            fragmentTitle = "Popular";
            fragment = new MainGridFragment();
            kind = "tv";
            bundle.putString("kind","tv");
            bundle.putString("type","popular");
        }else if(id == R.id.tv_now){
            fragmentTitle = "Now Airing";
            fragment = new MainGridFragment();
            kind = "tv";
            bundle.putString("kind","tv");
            bundle.putString("type","on_the_air");
        }/*else if(id == R.id.tv_upcoming){
            fragmentTitle = "Upcoming";
            fragment = new MainGridFragment();

            bundle.putString("kind","tv");
            bundle.putString("type","upcoming");
        }*/





        fragment.setArguments(bundle);


            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }


        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(fragmentTitle);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
