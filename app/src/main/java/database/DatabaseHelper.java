package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Lo statement SQL di creazione del database
    private static final String DATABASE_CREATE_CHEF = "create table chef (email text primary key, nome text not null, cognome text not null, luogo_lavoro text not null, immagine_profilo text);";
    private static final String DATABASE_CREATE_CLIENTE = "create table cliente (email text primary key, nome text not null, cognome text not null, provincia text not null, comune text not null, indirizzo text not null);";
    private static final String DATABASE_CREATE_RICETTA = "create table ricetta (id INTEGER PRIMARY KEY AUTOINCREMENT, nome_ricetta text not null, ingredienti text not null, tempo_preparazione text not null, prezzo double not null, chef REFERENCES chef(email) not null, immagine_ricetta text);";


    // Costruttore
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Questo metodo viene chiamato durante la creazione del database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_CHEF);
        database.execSQL(DATABASE_CREATE_CLIENTE);
        database.execSQL(DATABASE_CREATE_RICETTA);
    }

    // Questo metodo viene chiamato durante l'upgrade del database, ad esempio quando viene incrementato il numero di versione
    @Override
    public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {

        database.execSQL("DROP TABLE IF EXISTS" + "'chef'");
        database.execSQL("DROP TABLE IF EXISTS" + "'cliente'");
        database.execSQL("DROP TABLE IF EXISTS" + "'ricetta'");
        onCreate(database);

    }
}