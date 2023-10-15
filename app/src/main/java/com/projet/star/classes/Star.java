package com.projet.star.classes;

public class Star {
    private int id;
    private String nom;
    private String image;
    private float rating;
    private static int comp;

    public Star() {
    }

    public Star(String nom, String image, float rating) {
        this.id = ++comp;
        this.nom = nom;
        this.image = image;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
