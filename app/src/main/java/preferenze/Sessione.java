package preferenze;

import android.content.Context;

public class Sessione extends Preferenze {

    private final static String KEY_SESSION_ID = "Sessione_id";
    private final static String KEY_SESSION_PASSWORD = "Sessione_password";
    private final static String KEY_SESSION_USER_TYPE = "Sessione_tipo_utente";

    public static void loginChef (Context context, String id_utente, String password){
        inserisciStringa(context, KEY_SESSION_ID, id_utente);
        inserisciStringa(context, KEY_SESSION_PASSWORD, password);
        inserisciStringa(context, KEY_SESSION_USER_TYPE, "chef");
    }

    public static void loginCliente (Context context, String id_utente, String password){
        inserisciStringa(context, KEY_SESSION_ID, id_utente);
        inserisciStringa(context, KEY_SESSION_PASSWORD, password);
        inserisciStringa(context, KEY_SESSION_USER_TYPE, "cliente");
    }

    public static void logout (Context context) {
        rimuovi(context, KEY_SESSION_ID);
        rimuovi(context, KEY_SESSION_PASSWORD);
        rimuovi(context, KEY_SESSION_USER_TYPE);
    }

    public static String getSessionId (Context context){
        return leggiStringa(context, KEY_SESSION_ID);
    }

    public static String getSessionPassword (Context context){
        return leggiStringa(context, KEY_SESSION_PASSWORD);
    }

    public static boolean isChef (Context context){
        return leggiStringa(context, KEY_SESSION_USER_TYPE).equals("chef");
    }

    public static boolean isCliente (Context context){
        return leggiStringa(context, KEY_SESSION_USER_TYPE).equals("cliente");
    }


}
