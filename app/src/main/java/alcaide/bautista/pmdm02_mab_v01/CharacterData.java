package alcaide.bautista.pmdm02_mab_v01;

import java.util.List;
import java.util.Random;
/**
 * Clase que representa los datos de un personaje, incluyendo su imagen, nombre, descripción, habilidades, sonidos y fondo.
 */
public class CharacterData {

    private final int image;
    private final String name;
    private final String description;
    private final String skills;
    private final List<Integer> sound;
    private final int background;

    /**
     * Constructor de la clase CharacterData.
     *
     * @param image        Imagen asociada al personaje.
     * @param name         Nombre del personaje.
     * @param description  Descripción del personaje.
     * @param skills       Habilidades del personaje.
     * @param sound        Lista de sonidos asociados al personaje.
     * @param background   Fondo del personaje.
     */
    public CharacterData(int image, String name, String description, String skills, List<Integer> sound, int background) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.skills = skills;
        this.sound = sound;
        this.background = background;
    }

    /**
     * Obtiene la imagen asociada al personaje.
     *
     * @return La imagen del personaje.
     */
    public int getImage() {
        return image;
    }
    /**
     * Obtiene el nombre del personaje.
     *
     * @return El nombre del personaje.
     */
    public String getName() {
        return name;
    }
    /**
     * Obtiene la descripción del personaje.
     *
     * @return La descripción del personaje.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Obtiene las habilidades del personaje.
     *
     * @return Las habilidades del personaje.
     */
    public String getSkills() {
        return skills;
    }
    /**
     * Obtiene la lista de sonidos asociados al personaje.
     *
     * @return La lista de sonidos del personaje.
     */
    public List<Integer> getSound() {
        return sound;
    }
    /**
     * Obtiene el fondo asociado al personaje.
     *
     * @return El fondo del personaje.
     */
    public int getBackground() {
        return background;
    }

    /**
     * Obtiene un sonido aleatorio de la lista de sonidos disponibles.
     *
     * @return Un sonido aleatorio asociado al personaje. Si no hay sonidos disponibles, retorna -1.
     */
    public int getRandomSound() {
        if (sound != null && !sound.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(sound.size());  // Genera un índice aleatorio
            return sound.get(randomIndex); // Devuelve el sonido aleatorio
        }
        return -1; // Devuelve -1 si no hay sonidos disponibles
    }
}
