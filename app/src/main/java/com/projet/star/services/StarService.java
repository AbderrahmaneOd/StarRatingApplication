package com.projet.star.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.projet.star.classes.Star;
import com.projet.star.dao.IDao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {
    private ArrayList<Star> stars;
    private static StarService instance;
    private StarService() {
        this.stars = new ArrayList<>();
    }
    public static StarService getInstance() {
        if(instance == null)
            instance = new StarService();
        return instance;
    }
    @Override
    public boolean create(Star o) {
        return stars.add(o);
    }
    @Override
    public boolean update(Star o) {
        for(Star s : stars){
            if(s.getId() == o.getId()){
                s.setImage(o.getImage());
                s.setNom(o.getNom());
                s.setRating(o.getRating());
            }
        }
        return true;
    }
    @Override
    public boolean delete(Star o) {
        return stars.remove(o);
    }
    @Override
    public Star findById(int id) {
        for(Star s : stars){
            if(s.getId() == id)
                return s;
        }
        return null;
    }
    @Override
    public List<Star> findAll() {
        return stars;
    }

    public ArrayList<Star> getStars() {
        return stars;
    }

}

