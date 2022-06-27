package com.example.ejercicio14.Tabla;

import android.graphics.Bitmap;

public class Imagen {

    private String nameImg;
    private Bitmap img;

    public Imagen(String string, byte[] blob){
        //Constructor vacio
    }
    public Imagen(String nameImg, Bitmap img) {
        this.nameImg = nameImg;
        this.img = img;
    }//constructor con parametros


    public String getNameImg() {
        return nameImg;
    }

    public void setNameImg(String nameImg) {
        this.nameImg = nameImg;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

}
