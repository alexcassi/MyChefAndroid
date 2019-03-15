package preferenze;

import android.content.Context;

public class Sessione extends Preferenze {

    private final static String KEY_SESSION_ID = "Sessione_id";
    private final static String KEY_SESSION_PASSWORD = "Sessione_password";

    public static void login (Context context, String id_utente, String password){
        inserisciStringa(context, KEY_SESSION_ID, id_utente);
        inserisciStringa(context, KEY_SESSION_PASSWORD, password);
    }

    public static void logout (Context context) {
        rimuovi(context, KEY_SESSION_ID);
        rimuovi(context, KEY_SESSION_PASSWORD);
    }

    public static String getSessionId (Context context){
        return leggiStringa(context, KEY_SESSION_ID);
    }

    public static String getSessionPassword (Context context){
        return leggiStringa(context, KEY_SESSION_PASSWORD);
    }

    public static boolean isLogged (Context context){ return containsKey(context,KEY_SESSION_ID); }

}
