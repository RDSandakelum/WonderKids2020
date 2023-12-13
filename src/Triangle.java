import java.awt.Color;

public class Triangle {
    private int base;
    private int height;
    private Color color;
    private String name;
    private int rotation;

    public Triangle(int base, int height, Color color, String name) {
        this.base = base;
        this.height = height;
        this.color = color;
        this.name = name;
        this.rotation = 0;
    }

    public int getBase() {
        return base;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public double getRotation() {
        return rotation;
    }


    public void setBase(int base) {
        this.base = base;
    }

    public void setHeight(int height) {
        this.height = height;
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

