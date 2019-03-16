package database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import entità.Chef;
import entità.Ricetta;
import preferenze.Sessione;

public class DbAdapterRicetta {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapterRicetta.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Database fields
    private static final String DATABASE_TABLE = "ricetta";

    public static final String KEY_ID = "id";
    public static final String KEY_nome_ricetta = "nome_ricetta";
    public static final String KEY_ingredienti = "ingredienti";
    public static final String KEY_tempo_preparazione = "tempo_preparazione";
    public static final String KEY_prezzo = "prezzo";
    public static final String KEY_chef = "chef";
    public static final String KEY_immagine_ricetta = "immagine_ricetta";

    public DbAdapterRicetta(Context context) {
        this.context = context;
    }

    public DbAdapterRicetta open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(Integer id, String nome_ricetta, String ingredienti, String tempo_preparazione, Double prezzo, String chef_email, String immagine_ricetta) {
        ContentValues values = new ContentValues();
        values.put( KEY_ID, id);
        values.put( KEY_nome_ricetta, nome_ricetta);
        values.put( KEY_ingredienti, ingredienti );
        values.put( KEY_tempo_preparazione, tempo_preparazione );
        values.put( KEY_prezzo, prezzo);
        values.put( KEY_chef, chef_email);
        values.put( KEY_immagine_ricetta, immagine_ricetta);
        return values;
    }

    //create
    public long createRicetta(Ricetta r) {
        ContentValues initialValues = createContentValues(r.getId(),r.getNome_ricetta(), r.getIngredienti(), r.getTempo_preparazione(), r.getPrezzo(), r.getChef().getEmail(), r.getImmagine_ricetta());
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public long createRicetta(Integer id, String nome_ricetta, String ingredienti, String tempo_preparazione, Double prezzo, String chef_email, String immagine_ricetta) {
        ContentValues initialValues = createContentValues(id, nome_ricetta, ingredienti, tempo_preparazione, prezzo, chef_email, immagine_ricetta);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update
    public boolean updateRicetta( Integer id, String nome_ricetta, String ingredienti, String tempo_preparazione, Double prezzo, String chef_email, String immagine_ricetta ) {
        ContentValues updateValues = createContentValues(id, nome_ricetta, ingredienti, tempo_preparazione, prezzo, chef_email, immagine_ricetta);
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + "= ?",
                new String[]{id.toString()}) > 0;
    }
    public void updateOrCreateRicette(List<Ricetta> list, String email_chef) {
        for (Ricetta r : list) {
            ContentValues updateValues = createContentValues(r.getId(), r.getNome_ricetta(), r.getIngredienti(), r.getTempo_preparazione(), r.getPrezzo(), email_chef, r.getImmagine_ricetta());
            String id_str = r.getId().toString();
            if (!((database.update(DATABASE_TABLE, updateValues, KEY_ID + "= ?",
                    new String[]{id_str}))>0)){
                database.insertOrThrow(DATABASE_TABLE, null, updateValues);
            }
        }
    }

    //delete
    public boolean deleteRicetta(Integer id) {
        return database.delete(DATABASE_TABLE, KEY_ID + "= ?", new String[]{id.toString()}) > 0;
    }

    //fetch all
    public Cursor fetchAllRicette() {
        return database.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_nome_ricetta, KEY_ingredienti, KEY_tempo_preparazione, KEY_prezzo, KEY_chef, KEY_immagine_ricetta}, null, null, null, null, null);
    }

    //fetch chefs filter by a string
    /*public Cursor fetchChefsByFilter(String filter) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        KEY_EMAIL, KEY_NOME, KEY_COGNOME},
                KEY_NOME + " like '%"+ filter + "%'", null, null, null,
                null, null);
        return mCursor;
    }*/

    /*
    public HashMap getChefInfo(String email) {
        HashMap wordList = new HashMap();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM chef where email='"+email+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                wordList.put("name", cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return wordList;
    }*/


    /*
    public void insertFast(int insertCount) {
        // you can use INSERT only
        String sql = "INSERT OR REPLACE INTO " + DATABASE_TABLE + " ( email, nome, cognome ) VALUES ( ?, ?, ? )";
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();*/
 /* Come da http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
 * Writers deve usare beginTransactionNonExclusive() oppure
 beginTransactionWithListenerNonExclusive(SQLiteTransactionListener) per iniziare una transazione */
        /*db.beginTransactionNonExclusive();

        SQLiteStatement stmt = db.compileStatement(sql);
        for(int x=1; x<=insertCount; x++){
            stmt.bindString(1, ""+ x + "@" + x + ".com");
            stmt.bindString(2, "nome" + x);
            stmt.bindString(3, "cognome" + x);

            stmt.execute();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();
    }*/

}