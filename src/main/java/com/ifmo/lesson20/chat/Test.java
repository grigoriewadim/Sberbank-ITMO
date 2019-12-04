package com.ifmo.lesson20.chat;

public class Test {

    interface Shape {
        void draw();
    }

    public class Rectngle implements Shape {
        @Override
        public void draw() {
            System.out.println("I drow a rectangle");
        }
    }

    abstract class Color extends Rectngle {
        void Color() {

        }
    }
}
