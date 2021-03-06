package com.kodilla.testing.shape;

public class Triangle implements Shape {

    private String triangleName;
    private double hight;
    private double width;

    public Triangle(String triangleName, double hight, double width) {
        this.triangleName = triangleName;
        this.hight = hight;
        this.width = width;
    }


    @Override
    public String getShapeName() {
        return "Circle";
    }

    @Override
    public double getField() {
        return hight * width / 2;
    }
}
