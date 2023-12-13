import java.awt.Color;

import java.awt.Color;

public class Rectangle {
    private int width;
    private int height;
    private Color color;
    private String name;
    private int rotation;

    public Rectangle(int width, int height, Color color, String name) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.name = name;
        this.rotation = 0;
    }

    public int getWidth() {
        return width;
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


    public void setWidth(int width) {
        this.width = width;
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
