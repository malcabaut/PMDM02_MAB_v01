package alcaide.bautista.pmdm02_mab_v01;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Esta clase se encarga de gestionar las preferencias de idioma de la aplicación.
 * Permite guardar y obtener el idioma seleccionado (inglés o español) utilizando SharedPreferences.
 */
public class PreferencesHelper {

    // Nombre del archivo de preferencias
    private static final String PREF_NAME = "language_pref";
    // Clave para acceder a la preferencia del idioma
    private static final String KEY_LANGUAGE = "language";

    /**
     * Guarda el idioma seleccionado en las preferencias.
     * El idioma se guarda como "en" para inglés o "es" para español según el estado del SwitchCompat.
     *
     * @param context El contexto de la aplicación utilizado para acceder a SharedPreferences.
     * @param isEnglish Un valor booleano que indica si el idioma seleccionado es inglés (true) o español (false).
     */
    public static void setLanguage(Context context, boolean isEnglish) {
        // Obtener el archivo de preferencias
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Si el Switch está activado, guardamos "en" (inglés); si está desactivado, guardamos "es" (español)
        String languageCode = isEnglish ? "en" : "es";

        // Guardar el código de idioma en SharedPreferences
        editor.putString(KEY_LANGUAGE, languageCode);
        editor.apply();  // Aplicar los cambios de forma asíncrona
    }

    /**
     * Obtiene el idioma guardado en las preferencias.
     * Si no se ha guardado ningún idioma previamente, se devuelve "es" (español) por defecto.
     *
     * @param context El contexto de la aplicación utilizado para acceder a SharedPreferences.
     * @return El código del idioma guardado ("en" para inglés, "es" para español).
     */
    public static String getLanguage(Context context) {
        // Obtener el archivo de preferencias
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Devolver el idioma guardado o "es" (español) si no se ha guardado ninguno
        return preferences.getString(KEY_LANGUAGE, "es");
    }
}
