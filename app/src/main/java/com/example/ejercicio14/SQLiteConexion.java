package com.example.ejercicio14;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.ejercicio14.Tabla.Imagen;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class SQLiteConexion extends SQLiteOpenHelper {

    Context context;
    private static String dbname=Transacciones.NameDataBase;
    private static int version = 1;
    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;

    public SQLiteConexion(Context context){
        super(context, dbname, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(Transacciones.CreateTableImagen);
            Toast.makeText(context,"Tabla creada con exito", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL(Transacciones.DropTableImagen);
        onCreate(db);
    }

    public void storeImage(Imagen objectImagen){
        try{
            SQLiteDatabase objectSqLiteDatabase = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = objectImagen.getImg();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);

            imageInBytes = objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValues = new ContentValues();

            objectContentValues.put("nameImg", objectImagen.getNameImg());
            objectContentValues.put("img",imageInBytes);

            long checkIfQueryRuns = objectSqLiteDatabase.insert("imagenes", null, objectContentValues);
            if (checkIfQueryRuns!=-1){
                Toast.makeText(context, "Dato agregado en la tabla", Toast.LENGTH_SHORT).show();
                objectSqLiteDatabase.close();
            }
            else {
                Toast.makeText(context, "Dato NO agregado en la tabla", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//storeImage

    public List<Imagen> mostrarImagenes(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM imagenes",null);
        List<Imagen> imagenes = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                imagenes.add(new Imagen(cursor.getString(0), cursor.getBlob(1)));
            }while (cursor.moveToNext());

        }
        return imagenes;
    }
}
