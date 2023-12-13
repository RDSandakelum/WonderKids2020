import java.awt.Color;

public class Circle {

    private int radius;
    private Color color;
    private String name;

    public Circle(int radius, Color color, String name) {
        this.radius = radius;
        this.color = color;
        this.name = name;
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }
}
