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
        starService.create(new Star("Ismael Huang", "https://images.unsplash.com/photo-1568602471122-7832951cc4c5?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzU4MTY2NA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=500", 3));
        starService.create(new Star("Eduardo Hancock", "https://images.unsplash.com/photo-1613181013804-1dcba09e6a9d?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzU4MTY5Nw&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=500", 3));
        starService.create(new Star("Roland Lloyd", "https://images.unsplash.com/photo-1480455624313-e29b44bbfde1?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzU4MTc5MA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=500", 2));
        starService.create(new Star("Oliver Forbes", "https://images.unsplash.com/photo-1577880216142-8549e9488dad?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzU4MTgxMQ&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=500", 5));
        starService.create(new Star("Lonnie Mansfield", "https://images.unsplash.com/photo-1573497019940-1c28c88b4f3e?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzU4MTgyMQ&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=500", 5));
        starService.create(new Star("Brandon Jordan", "https://images.unsplash.com/photo-1552642986-ccb41e7059e7?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzU4MTgzOA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=500", 4));
        starService.create(new Star("Calvin Baker", "https://source.unsplash.com/500x500/?man", 5));
        starService.create(new Star("Salvador Hernandez", "https://images.unsplash.com/photo-1581382575646-a4d90bb53a4a?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzU4MTkxMA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=500", 5));
        starService.create(new Star("Jon Norris", "https://images.unsplash.com/photo-1549237511-bbe6a0979d6a?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzU4MTkyOA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=500", 1));



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