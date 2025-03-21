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
        service.create(new Star("kate bosworth", "https://hollywoodlife.com/wp-content/uploads/2016/09/kate-bosworth-bio-photo-rex.jpg", 3.5f));
        service.create(new Star("george clooney", "https://www.themoviedb.org/t/p/w500/kHiVY6r1k6juXrNetAYk2jILqn9.jpg", 3));
        service.create(new Star("michelle rodriguez", "https://cinepassion34.fr/wp-content/uploads/2020/03/Michelle-Rodriguez-cinepassion34.jpg", 5));
        service.create(new Star("meryl Streep", "https://hips.hearstapps.com/hmg-prod/images/actrice-meryl-streep-lors-de-la-c-c3-a9r-c3-a9monie-douverture-du-news-photo-1726066263.jpg?crop=0.632xw:0.948xh;0.204xw,0.0518xh&resize=1200:*", 1));
        service.create(new Star("tom hanks", "https://movieplayer.net-cdn.it/t/images/2011/12/20/fotografia-di-tom-hanks-226894_jpg_170x0_crop_q85.jpg", 5));
        service.create(new Star("louise bouroin", "https://image.tmdb.org/t/p/h632/n83zu056tAm0wXHVrNk3khr8Gm4.jpg", 1));
        service.create(new Star("emma stone", "https://resize.elle.fr/square_960_webp/var/plain_site/storage/images/beaute/cheveux/stars/metamorphosee-emma-stone-affiche-une-coupe-ultra-courte-sur-le-tapis-rouge-4298796/103526408-1-fre-FR/Metamorphosee-Emma-Stone-affiche-une-coupe-ultra-courte-sur-le-tapis-rouge.jpg", 1));
    }

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