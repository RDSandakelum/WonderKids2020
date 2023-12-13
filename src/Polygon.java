import java.awt.Color;

public class Polygon {
    private int sides;
    private int sideLength;
    private Color color;
    private String name;
    private int rotation;

    public Polygon(int sides, int sideLength, Color color, String name) {
        this.sides = sides;
        this.sideLength = sideLength;
        this.color = color;
        this.name = name;
        this.rotation = 0;
    }


    public int getSides() {
        return sides;
    }

    public int getSideLength() {
        return sideLength;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getRotation() {
        return rotation;
    }



    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

}
