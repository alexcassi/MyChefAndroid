package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.sql.SQLException;
import java.util.HashMap;

import entità.Chef;

public class DbAdapterChef {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapterChef.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Database fields
    private static final String DATABASE_TABLE = "chef";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NOME = "nome";
    public static final String KEY_COGNOME = "cognome";
    public static final String KEY_LUOGO_LAVORO = "luogo_lavoro";
    public static final String KEY_IMMAGINE_PROFILO = "immagine_profilo";

    public DbAdapterChef(Context context) {
        this.context = context;
    }

    public DbAdapterChef open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String email, String password, String name, String surname, String place, String image ) {
        ContentValues values = new ContentValues();
        values.put( KEY_EMAIL, email);
        values.put( KEY_PASSWORD, password);
        values.put( KEY_NOME, name );
        values.put( KEY_COGNOME, surname );
        values.put( KEY_LUOGO_LAVORO, place);
        values.put( KEY_IMMAGINE_PROFILO, image);
        return values;
    }

    //create a chef
    public long createChef(Chef c) {
        ContentValues initialValues = createContentValues(c.getEmail(),c.getPassword(), c.getNome(), c.getCognome(), c.getLuogo_lavoro(), c.getImmagine_profilo());
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public long createChef(String email, String password, String name, String surname, String place, String image) {
        ContentValues initialValues = createContentValues(email, password, name, surname, place, image);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update a chef
    public boolean updateChef( String email, String password, String name, String surname, String place, String image ) {
        ContentValues updateValues = createContentValues(email, password, name, surname, place, image);
        return database.update(DATABASE_TABLE, updateValues, KEY_EMAIL + "=" + email,
                null) > 0;
    }
    //delete a chef
    public boolean deleteChef(String email) {
        return database.delete(DATABASE_TABLE, KEY_EMAIL + "=" + email, null) > 0;
    }

    //fetch all chefs
    public Cursor fetchAllChefs() {
        return database.query(DATABASE_TABLE, new String[] { KEY_EMAIL, KEY_PASSWORD, KEY_NOME, KEY_COGNOME, KEY_LUOGO_LAVORO, KEY_IMMAGINE_PROFILO}, null, null, null, null, null);
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