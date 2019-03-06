package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.HashMap;

public class DbAdapter {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Database fields
    private static final String DATABASE_TABLE = "chef";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_NOME = "nome";
    public static final String KEY_COGNOME = "cognome";

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String email, String name, String surname) {
        ContentValues values = new ContentValues();
        values.put( KEY_EMAIL, email);
        values.put( KEY_NOME, name );
        values.put( KEY_COGNOME, surname );
        return values;
    }

    //create a chef
    public long createChef(String email, String name, String surname) {
        ContentValues initialValues = createContentValues(email, name, surname);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update a chef
    public boolean updateChef( String email, String name, String surname ) {
        ContentValues updateValues = createContentValues(email, name, surname);
        return database.update(DATABASE_TABLE, updateValues, KEY_EMAIL + "=" + email,
                null) > 0;
    }
    //delete a chef
    public boolean deleteChef(String email) {
        return database.delete(DATABASE_TABLE, KEY_EMAIL + "=" + email, null) > 0;
    }

    //fetch all chefs
    public Cursor fetchAllChefs() {
        return database.query(DATABASE_TABLE, new String[] { KEY_EMAIL, KEY_NOME, KEY_COGNOME}, null, null, null, null, null);
    }

    //fetch chefs filter by a string
    public Cursor fetchContactsByFilter(String filter) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        KEY_EMAIL, KEY_NOME, KEY_COGNOME},
                KEY_NOME + " like '%"+ filter + "%'", null, null, null,
                null, null);
        return mCursor;
    }

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
    }
}