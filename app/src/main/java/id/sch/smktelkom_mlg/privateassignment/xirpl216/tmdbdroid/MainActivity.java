package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Fragment fragment = null;
    public Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        final MenuItem myActionMenuItem = menu.findItem( R.id.search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }

                Intent i = new Intent(MainActivity.this,SearchActivity.class);
                i.putExtra("kind",bundle.getString("kind"));
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
        if(id == R.id.mov_popular){
            fragmentTitle = "Popular";
            fragment = new MainGridFragment();

            bundle.putString("kind","movie");
            bundle.putString("type","popular");
        }else if(id == R.id.mov_now){
            fragmentTitle = "Now Playing";
            fragment = new MainGridFragment();

            bundle.putString("kind","movie");
            bundle.putString("type","now_playing");
        }else if(id == R.id.mov_upcoming){
            fragmentTitle = "Upcoming";
            fragment = new MainGridFragment();

            bundle.putString("kind","movie");
            bundle.putString("type","upcoming");
        }

        // TV Shows
        if(id == R.id.tv_popular){
            fragmentTitle = "Popular";
            fragment = new MainGridFragment();

            bundle.putString("kind","tv");
            bundle.putString("type","popular");
        }else if(id == R.id.tv_now){
            fragmentTitle = "Now Airing";
            fragment = new MainGridFragment();

            bundle.putString("kind","tv");
            bundle.putString("type","now");
        }else if(id == R.id.tv_upcoming){
            fragmentTitle = "Upcoming";
            fragment = new MainGridFragment();

            bundle.putString("kind","tv");
            bundle.putString("type","upcoming");
        }

        if(id == R.id.nav_saved){
            fragment = new SavedFragment();
            fragmentTitle = "Saved";
        }

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
