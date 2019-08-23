package entities;

/**
 *
 * @author Camilla
 */
public class Animal {
    private String type;
    private int birthYear;
    private String sound;

    public Animal() {
    }

    public Animal(int birthYear, String type, String sound) {
        this.birthYear = birthYear;
        this.type = type;
        this.sound = sound;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    
    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
    
    
}
