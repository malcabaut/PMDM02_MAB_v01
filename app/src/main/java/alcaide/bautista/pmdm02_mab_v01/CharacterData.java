package alcaide.bautista.pmdm02_mab_v01;

import java.util.List;

public class CharacterData {

    private final String image;
    private final String name;
    private final String description;
    private final String skills;
    private final List<String> sound;
    private final String background;

    // Constructor
    public CharacterData(String image, String name, String description, String skills, List<String> sound, String background) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.skills = skills;
        this.sound = sound;
        this.background = background;
    }

    public String getImage() {
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

    public List<String> getSound() {
        return sound;
    }

    public String getBackground() {
        return background;
    }
}
