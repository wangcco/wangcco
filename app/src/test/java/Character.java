import java.io.Serializable;

public class Character implements Serializable {
    private String name;
    private String height;
    private String mass;
    private String hairColor;
    private String eyeColor;

    public Character(String name, String height, String mass, String hairColor, String eyeColor) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }
}
