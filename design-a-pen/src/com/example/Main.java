package com.example;

public class Main {
    public static void main(String[] args) {
        Pen pen = new BallPen("Pilot", 0.7, 5);

        pen.start();
        System.out.println(pen.write("Low level design is fun."));
        System.out.println(pen.write("Practicing object modeling."));
        pen.close();

        pen.refill();
        pen.start();
        System.out.println(pen.write("After refill, pen writes again."));
        pen.close();
    }
}
