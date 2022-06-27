package com.example.ejercicio14;

public class Transacciones {
    /* tablas */
    public  static  final String tablaimagen = "imagenes";
    /* campos */
    public static final String id = "nameImg";
    public static final String imagen = "imagen";

    /* tablas -CREATE, DROP */
    public static final String CreateTableImagen = "CREATE TABLE imagenes(nameIng TEXT, imagen BLOB)";

    public static final String DropTableImagen = "DROP TABLE IF EXISTS imagenes";

    /* Creacion del nombre de la base de datos */
    public static final String NameDataBase = "DBImagen";
}
