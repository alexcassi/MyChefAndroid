package preferenze;

import android.content.Context;
import android.content.SharedPreferences;

//classe da estendere per gestire i vari tipi di preferenze
public class Preferenze {

    private final static String PREF_FILE = "Preferenze";
    private final static String DEFAULT_VALUE = "no values";

    protected static void inserisciStringa(Context context, String key, String value){
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }
    protected static String leggiStringa(Context context, String key){
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getString(key, DEFAULT_VALUE);
    }

    protected static void rimuovi (Context context, String key){
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }

    protected static void eliminaPreferenze(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    protected static boolean containsKey (Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.contains(key);
    }

}
