package com.ifmo.lesson5;

public class Shapes {
    public static void main(String[] args) {
        double totalArea = totalArea(
                new Rectangle(10, 15),
                new Rectangle(4, 8),
                new Square(23));

        System.out.println(totalArea);
    }

    public static double totalArea(Shape... shapes) {
        double sum = 0;

        for (Shape shape : shapes) {
            sum += shape.area();
        }

        return sum;
    }
}
