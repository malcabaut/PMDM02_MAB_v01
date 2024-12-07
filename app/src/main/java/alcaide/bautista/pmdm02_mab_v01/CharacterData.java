package alcaide.bautista.pmdm02_mab_v01;

import java.util.List;

public class CharacterData {

    private final int image;
    private final String name;
    private final String description;
    private final String skills;
    private final List<Integer> sound;
    private final int background;

    // Constructor
    public CharacterData(int image, String name, String description, String skills, List<Integer> sound, int background) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.skills = skills;
        this.sound = sound;
        this.background = background;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSkills() {
        return skills;
    }

    public List<Integer> getSound() {
        return sound;
    }

    public int getBackground() {
        return background;
    }
}
