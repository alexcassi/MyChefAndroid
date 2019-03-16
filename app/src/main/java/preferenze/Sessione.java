package preferenze;

import android.content.Context;

public class Sessione extends Preferenze {

    private final static String KEY_SESSION_ID = "Sessione_id";
    private final static String KEY_SESSION_PASSWORD = "Sessione_password";
    private final static String KEY_SESSION_USER_TYPE = "Sessione_tipo_utente";
    private final static String KEY_SESSION_USER_NAME = "Sessione_nome_utente";

    public static void loginChef (Context context, String id_utente, String password, String nome){
        inserisciStringa(context, KEY_SESSION_ID, id_utente);
        inserisciStringa(context, KEY_SESSION_PASSWORD, password);
        inserisciStringa(context, KEY_SESSION_USER_TYPE, "chef");
        inserisciStringa(context, KEY_SESSION_USER_NAME, nome);
    }

    public static void loginCliente (Context context, String id_utente, String password, String nome){
        inserisciStringa(context, KEY_SESSION_ID, id_utente);
        inserisciStringa(context, KEY_SESSION_PASSWORD, password);
        inserisciStringa(context, KEY_SESSION_USER_TYPE, "cliente");
        inserisciStringa(context, KEY_SESSION_USER_NAME, nome);
    }

    public static void logout (Context context) {
        rimuovi(context, KEY_SESSION_ID);
        rimuovi(context, KEY_SESSION_PASSWORD);
        rimuovi(context, KEY_SESSION_USER_TYPE);
        rimuovi(context, KEY_SESSION_USER_NAME);
    }

    public static String getSessionId (Context context){
        return leggiStringa(context, KEY_SESSION_ID);
    }

    public static String getSessionPassword (Context context){
        return leggiStringa(context, KEY_SESSION_PASSWORD);
    }

    public static String getSessionUserName(Context context){
        return leggiStringa(context, KEY_SESSION_USER_NAME);
    }

    public static boolean isChef (Context context){
        return leggiStringa(context, KEY_SESSION_USER_TYPE).equals("chef");
    }

    public static boolean isCliente (Context context){
        return leggiStringa(context, KEY_SESSION_USER_TYPE).equals("cliente");
    }

}
