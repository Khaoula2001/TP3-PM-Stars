package com.emsi.cinemagallery;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emsi.cinemagallery.adapter.StarAdapter;
import com.emsi.cinemagallery.beans.Star;
import com.emsi.cinemagallery.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();

        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void init() {
        service.create(new Star("kate bosworth", "http://www.stars-photos.com/resize.php?id=801", 3.5f));
        service.create(new Star("george clooney", "http://www.stars-photos.com/resize.php?id=1191", 3));
        service.create(new Star("michelle rodriguez", "http://www.stars-photos.com/resize.php?id=1120", 5));
        service.create(new Star("george clooney", "http://www.stars-photos.com/resize.php?id=1193", 1));
        service.create(new Star("louise bouroin", "http://www.stars-photos.com/resize.php?id=1185", 5));
        service.create(new Star("louise bouroin", "http://www.stars-photos.com/resize.php?id=1184", 1));
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}