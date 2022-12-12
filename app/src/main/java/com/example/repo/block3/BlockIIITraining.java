package com.example.repo.block3;

import android.util.Log;

import androidx.annotation.NonNull;

public class BlockIIITraining {

    /*
      3.

      Написать простое лямбда-выражение в переменной myClosure,
      лямбда-выражение должно выводить в консоль фразу "I love Java".
      Вызвать это лямбда-выражение.

      Далее написать функцию, которая будет запускать заданное лямбда-выражение заданное
      количество раз. Объявить функцию так: public void repeatTask (int times, Runnable task).
      Функция должна запускать times раз лямбда-выражение task . Используйте эту функцию для
      печати "I love Java" 10 раз.
     */
    interface MyClosure {
        void printMessage(String message);
    }

    MyClosure myClosure = (message) -> Log.e("TAG", message);

    void startLambda() {
        myClosure.printMessage("I love Java");
    }

    public void repeatTask(int times, Runnable task) {
        for (int i = 0; i < 10; i++) {
            myClosure.printMessage("I love Java");
        }
    }

    /*
      4.

      Условия: есть начальная позиция на двумерной плоскости,
      можно осуществлять последовательность шагов по четырем направлениям up, down, left, right.
      Размерность каждого шага равна 1.

      Задание:
      - Создать enum Directions с возможными направлениями движения
      - Создать метод, принимающий координаты и одно из направлений и возвращающий координаты после
        перехода по направлению
      - Создать метод, осуществляющий несколько переходов от первоначальных координат и выводящий
        координаты после каждого перехода. Для этого внутри метода следует определить переменную
        location с начальными координатами (0,0) и массив направлений, содержащий элементы
        [up, up, left, down, left, down, down, right, right, down, right], и програмно вычислить
        какие будут координаты у переменной location после выполнения этой последовательности шагов.
      Для вычисленеия результата каждого перемещения следует использовать созданный ранее метод
      перемещения на один шаг.
     */
    enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    class Location {
        private int x;
        private int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @NonNull
        @Override
        public String toString() {
            return "X: " + x + " Y:" + y;
        }
    }

    Location step(Location location, Directions direction) {
        if (direction == Directions.UP) {
            location.y++;
        }
        if (direction == Directions.DOWN) {
            location.y--;
        }
        if (direction == Directions.LEFT) {
            location.x--;
        }
        if (direction == Directions.RIGHT) {
            location.x++;
        }

        return location;
    }

    void severalSteps(Directions[] directions) {
        Location location = new Location(0, 0);

        for (Directions direction : directions) {
            Log.e("LOCATION", step(location, direction).toString());
        }
    }

    /*
      5.

      Создать интерфейс Shape с двумя методами perimeter и area, выводящими периметр
      и площадь фигуры соответственно, после чего реализовать и использовать для вывода периметра
      и площади следующие классы, реализующие интерфейс Shape:
      - Rectangle - прямоугольник с двумя свойствами: ширина и длина
      - Square - квадрат с одним свойством: длина стороны
      - Circle - круг с одним свойством: диаметр круга
     */
    interface Shape {
        void perimeter();

        void area();
    }


    class Rectangle implements Shape {
        private int width;
        private int length;

        Rectangle(int width, int length) {
            this.width = width;
            this.length = length;
        }

        @Override
        public void perimeter() {
            Log.e("PERIMETER", String.valueOf(2 * width + 2 * length));
        }

        @Override
        public void area() {
            Log.e("AREA", String.valueOf(width * length));
        }
    }


    class Square implements Shape {
        private int sideLength;

        Square(int sideLength) {
            this.sideLength = sideLength;
        }

        @Override
        public void perimeter() {
            Log.e("PERIMETER", String.valueOf(4 * sideLength));
        }

        @Override
        public void area() {
            Log.e("AREA", String.valueOf(sideLength * sideLength));
        }
    }


    class Circle implements Shape {
        private int diameter;

        Circle(int diameter) {
            this.diameter = diameter;
        }

        @Override
        public void perimeter() {
            Log.e("PERIMETER", String.valueOf(Math.PI * diameter));
        }

        @Override
        public void area() {
            Log.e("AREA", String.valueOf(Math.PI * Math.pow((double) (diameter / 2), 2)));
        }
    }
}