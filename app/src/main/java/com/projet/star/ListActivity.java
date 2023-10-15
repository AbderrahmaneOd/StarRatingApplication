package com.projet.star;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.projet.star.adapter.StarAdapter;
import com.projet.star.classes.Star;
import com.projet.star.services.StarService;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    
    // creating variables for
    // our ui components.
    private RecyclerView starRV;

    // variable for our adapter
    // class and array list
    private StarAdapter adapter;
    private ArrayList<Star> starModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        // initializing our variables.
        starRV = findViewById(R.id.idRVStars);

        // calling method to
        // build recycler view.
        buildRecyclerView();
    }

    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Star> filteredlist = new ArrayList<Star>();

        StarService starService = StarService.getInstance();

        // running a for loop to compare elements.
        for (Star item : starService.getStars()) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getNom().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // We are using switch case because multiple icons can be kept
        if (item.getItemId() == R.id.shareButton) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);

            // type of the content to be shared
            sharingIntent.setType("text/plain");

            // Body of the content
            String shareBody = "Your Body Here";

            // subject of the content. you can share anything
            String shareSubject = "Your Subject Here";

            // passing body of the content
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

            // passing subject of the content
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }
        return super.onOptionsItemSelected(item);
    }

    private void buildRecyclerView() {

        // below line we are creating a new array list
        starModelArrayList = new ArrayList<Star>();
        StarService starService = StarService.getInstance();

        // below line is to add data to our array list.
        starService.create(new Star("kate bosworth", "https://media1.popsugar-assets.com/files/thumbor/Naz9jjkeK7vb7xtgNDenra6sB4o/0x62:769x1119/fit-in/728xorig/filters:format_auto-!!-:strip_icc-!!-/2022/05/18/518/n/44490375/4ad285e96284d8179f8d19.98869593_/i/up-and-coming-british-male-actors.jpg", 3));
        starService.create(new Star("george clooney", "https://hips.hearstapps.com/hmg-prod/images/mh-12-16-australians-1608143046.jpg?crop=0.231xw:0.462xh;0.731xw,0.0705xh&resize=640:*", 3));
        starService.create(new Star("michelle rodriguez",
                "https://images.fandango.com/ImageRenderer/300/0/redesign/static/img/default_poster.png/0/images/masterrepository/performer%20images/205550/ChrisEvans-2019_r.jpg", 5));
        starService.create(new Star("george clooney", "https://imgix.ranker.com/user_node_img/23/458938/original/andrew-garfield-theater-actors-photo-u34", 1));
        starService.create(new Star("louise bouroin", "https://i0.wp.com/flickside.com/wp-content/uploads/2022/11/highest-paid-actors.jpg?fit=1200%2C900&ssl=1", 5));
        starService.create(new Star("louise bouroin", "https://images.augustman.com/wp-content/uploads/2020/09/14123902/Chris-Delmas-AFP.jpg", 4));
        starService.create(new Star("louise bouroin", "https://i.pinimg.com/550x/75/06/5d/75065da93d181c15f8266289313231c6.jpg", 3));
        starService.create(new Star("Tom Holland", "https://www.hollywoodreporter.com/wp-content/uploads/2017/07/tom_holland_-_getty_-_h_2017.jpg?w=800", 2));
        starService.create(new Star("louise bouroin", "https://i.insider.com/5a9da45be6a9cf17008b4643?width=750&format=jpeg&auto=webp", 2));
        starService.create(new Star("louise bouroin", "https://i.insider.com/5a9ed8a45cc4102c048b459f?width=1000&format=jpeg&auto=webp", 2));
        starService.create(new Star("louise bouroin", "https://cdn.shopify.com/s/files/1/0182/8937/files/tom_hordy_1024x1024.jpg?v=1482502984", 2));
        starService.create(new Star("louise bouroin", "https://cdn-aikll.nitrocdn.com/zpODUSdXaRhrjOSnTvJWtCYyNCoHlwTB/assets/images/optimized/rev-b4a2829/beardoholic.com/wp-content/uploads/2022/11/henry-cavill.jpg", 2));



        // initializing our adapter class.
        adapter = new StarAdapter(starService.getStars(), ListActivity.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        starRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        starRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        starRV.setAdapter(adapter);
    }
}