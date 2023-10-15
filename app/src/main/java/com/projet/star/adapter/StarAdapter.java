package com.projet.star.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;

import com.projet.star.R;
import com.projet.star.classes.Star;
import com.projet.star.services.StarService;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.ViewHolder>{

    // creating a variable for array list and context.
    private ArrayList<Star> startModelArrayList;

    //private Context context;

    // creating a constructor for our variables.
    public StarAdapter(ArrayList<Star> courseModelArrayList, Context context) {
        this.startModelArrayList = courseModelArrayList;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<Star> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        startModelArrayList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.star_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // setting data to our views of recycler view.
        Star model = startModelArrayList.get(position);
        holder.StarId.setText(model.getId()+"");
        holder.StarNom.setText(model.getNom());
        holder.ratingbar.setRating(model.getRating());

        // Load the image using Picasso
        Picasso.get().load(model.getImage()).into(holder.starImage);

        // Finally add an onclickListener to the item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View popup = LayoutInflater.from(view.getContext()).inflate(R.layout.star_edit_item, null, false);
                final ImageView starImage = popup.findViewById(R.id.idStarImage);
                final RatingBar bar = popup.findViewById(R.id.ratingBar);
                final TextView starId = popup.findViewById(R.id.idStarId);


                bar.setRating(((RatingBar)view.findViewById(R.id.IdRatingBar)).getRating());
                starId.setText(((TextView)view.findViewById(R.id.idStarId)).getText().toString());
                // Load the image using Picasso
                Picasso.get().load(model.getImage()).into(starImage);

                AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                        .setTitle("Notez : ")
                        .setMessage("Donner une note entre 1 et 5 :")
                        .setView(popup)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float s = bar.getRating();
                                int ids = Integer.parseInt(starId.getText().toString());
                                Star star = StarService.getInstance().findById(ids);
                                star.setRating(s);
                                StarService.getInstance().update(star);
                                notifyItemChanged(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return startModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private final TextView StarId;
        private final TextView StarNom;
        private final ImageView starImage;
        private final RatingBar ratingbar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            StarId = itemView.findViewById(R.id.idStarId);
            StarNom = itemView.findViewById(R.id.idStarNom);
            ratingbar = itemView.findViewById(R.id.IdRatingBar);
            starImage = itemView.findViewById(R.id.idStarImage);
        }
    }

}
