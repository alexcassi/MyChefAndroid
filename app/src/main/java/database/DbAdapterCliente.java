package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.security.KeyException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeMap;

import entità.Chef;
import entità.Cliente;

public class DbAdapterCliente {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapterCliente.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Database fields
    private static final String DATABASE_TABLE = "cliente";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_NOME = "nome";
    public static final String KEY_COGNOME = "cognome";
    public static final String KEY_provincia = "provincia";
    public static final String KEY_comune = "comune";
    public static final String KEY_indirizzo = "indirizzo";

    public DbAdapterCliente(Context context) {
        this.context = context;
    }

    public DbAdapterCliente open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String email, String name, String surname, String provincia, String comune, String indirizzo ) {
        ContentValues values = new ContentValues();
        values.put( KEY_EMAIL, email);
        values.put( KEY_NOME, name );
        values.put( KEY_COGNOME, surname );
        values.put(KEY_provincia, provincia);
        values.put( KEY_comune, comune);
        values.put( KEY_indirizzo, indirizzo);
        return values;
    }

    //create
    public long createCliente(Cliente c) {
        ContentValues initialValues = createContentValues(c.getEmail(), c.getNome(), c.getCognome(), c.getProvincia(), c.getComune(), c.getIndirizzo());
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public long createCliente(String email, String name, String surname, String provincia, String comune, String indirizzo) {
        ContentValues initialValues = createContentValues(email, name, surname, provincia, comune, indirizzo);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public long createCliente (TreeMap<String,String> c) {
        ContentValues initialValues = createContentValues(c.get("email"), c.get("nome"), c.get("cognome"), c.get("provincia"), c.get("comune"),c.get("indirizzo"));
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update
    public boolean updateCliente( String email, String name, String surname, String provincia, String comune, String indirizzo ) {
        ContentValues updateValues = createContentValues(email, name, surname, provincia, comune, indirizzo);
        return database.update(DATABASE_TABLE, updateValues, KEY_EMAIL + "= ?",
                new String[]{email}) > 0;
    }
    public boolean updateCliente( TreeMap<String,String> c ) {
        ContentValues updateValues = createContentValues(c.get("email"), c.get("nome"), c.get("cognome"), c.get("provincia"), c.get("comune"),c.get("indirizzo"));
        String email = c.get("email");
        return database.update(DATABASE_TABLE, updateValues, KEY_EMAIL + "= ?",
                new String[]{email}) > 0;
    }

    //delete
    public boolean deleteCliente(String email) {
        return database.delete(DATABASE_TABLE, KEY_EMAIL + "= ?", new String[]{email}) > 0;
    }

    //fetch all
    public Cursor fetchAllChefs() {
        return database.query(DATABASE_TABLE, new String[] { KEY_EMAIL, KEY_NOME, KEY_COGNOME, KEY_provincia, KEY_comune, KEY_indirizzo}, null, null, null, null, null);
    }

    public boolean containsCliente(String email) {
        String selectQuery = "SELECT * FROM cliente where email='"+email+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
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