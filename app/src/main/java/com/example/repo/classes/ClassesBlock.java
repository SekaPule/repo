package com.example.repo.classes;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Набор заданий по работе с классами в java.
 * <p>
 * Задания подразумевают создание класса(ов), выполняющих задачу.
 * <p>
 * Проверка осуществляется ментором.
 */
public interface ClassesBlock {

    /*
      I

      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */
    class TwoNumbers {
        private int firstNum;
        private int secondNum;

        public TwoNumbers(int firstNum, int secondNum) {
            this.firstNum = firstNum;
            this.secondNum = secondNum;
        }

        public void setNumbers(int firstNum, int secondNum) {
            this.firstNum = firstNum;
            this.secondNum = secondNum;
        }

        public void printNumbers() {
            Log.e("NUMBERS", firstNum + "|" + secondNum);
        }

        public int sumNumbers() {
            return firstNum + secondNum;
        }

        public int maxNumber() {
            return Math.max(firstNum, secondNum);
        }
    }

    /*
      II

      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */
    class DynamicArray {
        private int[] array;
        private int arraySize;

        public DynamicArray(int arraySize) {
            this.arraySize = arraySize;
            this.array = new int[this.arraySize];
        }

        public void setArrayValues() {
            Random random = new Random();

            for (int i = 0; i < arraySize; i++) {
                array[i] = random.nextInt();
            }
        }

        public void shuffleArray() {
            Random random = new Random();
            int dataBroker;

            for (int i = 0; i < arraySize; i++) {
                int randomIndex = random.nextInt(i + 1);

                dataBroker = array[i];
                array[i] = array[randomIndex];
                array[randomIndex] = dataBroker;
            }
        }

        public int repeatingElementQuantity(int searchElement) {
            int elementQuantity = 0;

            for (int element : array) {
                if (element == searchElement) {
                    elementQuantity++;
                }
            }

            return elementQuantity;
        }

        public void printArray() {
            StringBuilder arrayElements = new StringBuilder();

            for (int element : array) {
                arrayElements.append(element).append("\n");
            }

            Log.e("ARRAY", String.valueOf(arrayElements));
        }

    }

    /*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */
    class Triangle {
        private final Point a;
        private final Point b;
        private final Point c;
        private String objectState;

        public Triangle(Point a, Point b, Point c) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.objectState = "created";
        }

        private double side(Point startPoint, Point endPoint) {
            return Math.sqrt(Math.pow((endPoint.getX() - startPoint.getX()), 2)
                    + Math.pow((endPoint.getX() - startPoint.getX()), 2));
        }

        public double perimeter() {
            return side(a, b) + side(b, c) + side(c, a);
        }

        public double square() {
            double firstSide = side(a, b);
            double secondSide = side(b, c);
            double thirdSide = side(c, a);
            double p = (firstSide + secondSide + thirdSide) / 2;

            return Math.sqrt(p * (p - firstSide) * (p - secondSide) * (p - thirdSide));
        }

        public Point medianIntersectionPoint() {
            Point p = new Point((b.getX() + c.getX()) / 2, (b.getY() + c.getY()) / 2);

            return new Point((a.getX() + 2 * p.getX()) / 3, (a.getY() + 2 * p.getY()) / 3);
        }

        public String getObjectState() {
            return objectState;
        }

        public void setObjectState(String objectState) {
            this.objectState = objectState;
        }

        /*
         *  Inner class for definition points of triangle
         */
        private static class Point {
            private final int x;
            private final int y;

            private Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }
        }
    }

    /*
      IV

      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */
    class TimeClass {
        private int hour;
        private int minute;
        private int second;

        public TimeClass(int hour, int minute, int second) {
            try {
                if ((hour < 0) || (hour > 23)) {
                    throw new Exception("Wrong hour format!");
                }
                if ((minute < 0) || (minute > 59)) {
                    throw new Exception("Wrong minute format!");
                }
                if ((second < 0) || (second > 59)) {
                    throw new Exception("Wrong second format!");
                }

                this.hour = hour;
                this.minute = minute;
                this.second = second;

            } catch (Exception exception) {
                Log.e("Exception", exception.getMessage());
            }

        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            try {
                if ((hour < 0) || (hour > 23)) {
                    throw new Exception("Wrong hour format!");
                }

                this.hour = hour;
            } catch (Exception exception) {
                Log.e("Exception", exception.getMessage());
            }
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            try {
                if ((minute < 0) || (minute > 59)) {
                    throw new Exception("Wrong hour format!");
                }

                this.minute = minute;
            } catch (Exception exception) {
                Log.e("Exception", exception.getMessage());
            }
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            try {
                if ((second < 0) || (second > 59)) {
                    throw new Exception("Wrong hour format!");
                }

                this.second = second;
            } catch (Exception exception) {
                Log.e("Exception", exception.getMessage());
            }
        }

        public void changeTime(int hour, int minute, int second) {
            int extraHours;
            int extraMinutes;

            hour = hour + this.hour;
            minute = minute + this.minute;
            second = second + this.second;

            if (second > 59) {
                extraMinutes = second / 60;
                second = second % 60;
                minute += extraMinutes;
            }
            setSecond(second);

            if (minute > 59) {
                extraHours = minute / 60;
                minute = minute % 60;
                hour += extraHours;
            }
            setMinute(minute);

            if (hour > 23) {
                hour = hour % 24;
            }
            setHour(hour);
        }

        public void printTime() {
            Log.e("Time", hour + ":" + minute + ":" + second);
        }
    }

    /*
      V

      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации. Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */
    class Subscriber {
        private int id;
        private String surname;
        private String name;
        private String patronymic;
        private String address;
        private String creditCardNumber;
        private String debit;
        private String credit;
        private long roamingMeetingTime;

        public Subscriber(
                int id,
                String surname,
                String name,
                String patronymic,
                String address,
                String creditCardNumber,
                String debit,
                String credit,
                long roamingMeetingTime
        ) {
            this.id = id;
            this.surname = surname;
            this.name = name;
            this.patronymic = patronymic;
            this.address = address;
            this.creditCardNumber = creditCardNumber;
            this.debit = debit;
            this.credit = credit;
            this.roamingMeetingTime = roamingMeetingTime;
        }

        int getId() {
            return id;
        }

        String getName() {
            return name;
        }

        String getSurname() {
            return surname;
        }

        String getPatronymic() {
            return patronymic;
        }

        String getAddress() {
            return address;
        }

        String getCreditCardNumber() {
            return creditCardNumber;
        }

        String getDebit() {
            return debit;
        }

        String getCredit() {
            return credit;
        }

        long getRoamingMeetingTime() {
            return roamingMeetingTime;
        }

        void setId(int id) {
            this.id = id;
        }

        void setName(String name) {
            this.name = name;
        }

        void setSurname(String surname) {
            this.surname = surname;
        }

        void setPatronymic(String patronymic) {
            this.patronymic = patronymic;
        }

        void setAddress(String address) {
            this.address = address;
        }

        void setCreditCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
        }

        void setDebit(String debit) {
            this.debit = debit;
        }

        void setCredit(String credit) {
            this.credit = credit;
        }

        void setRoamingMeetingTime(int roamingMeetingTime) {
            this.roamingMeetingTime = roamingMeetingTime;
        }

        public void printSubscriberInfo() {
            String info = "\n" + id + "\n" + surname + "\n" + name + "\n" + patronymic + "\n"
                    + address + "\n" + creditCardNumber + "\n" + debit + "\n" + credit + "\n"
                    + roamingMeetingTime + "\n";
            Log.e("SUBSCRIBER", info);
        }

    }


    class Subscribers {
        private Subscriber[] subscribers;

        public Subscribers(Subscriber[] subscribers) {
            this.subscribers = subscribers;
        }

        public Subscriber[] subscribersByMeetingTime(int time) {
            Subscriber[] searchedSubscribers;
            int searchedSubscribersCount = 0;
            int searchedSubscribersIterator = 0;

            for (Subscriber sub : subscribers) {
                if (sub.roamingMeetingTime > time) {
                    searchedSubscribersCount++;
                }
            }

            searchedSubscribers = new Subscriber[searchedSubscribersCount];

            for (Subscriber sub : subscribers) {
                if (sub.roamingMeetingTime > time) {
                    searchedSubscribers[searchedSubscribersIterator] = sub;
                    searchedSubscribersIterator++;
                }
            }

            return sortByName(searchedSubscribers);
        }

        private Subscriber[] sortByName(Subscriber[] array) {
            final int ARRAY_LENGTH = array.length;
            Subscriber dataBroker; // provides data exchange

            for (int i = 0; i < ARRAY_LENGTH; i++) {
                for (int j = 0; j < ARRAY_LENGTH - i - 1; j++) {
                    if (array[j].name.compareTo(array[j + 1].name) > 0) {
                        dataBroker = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = dataBroker;
                    }
                }
            }

            return array;
        }
    }

    /*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */
    class Answer {
        private String task;
        private boolean isChecked;

        public Answer(String task) {
            this.task = task;
            this.isChecked = false;
        }

        public void check() {
            isChecked = true;
        }
    }


    class Exam {
        private String subject;
        private String task;
    }


    class Faculty {
        private String name;
        private String teacherName;
        private List<Exam> exams;
        private List<Entrant> entrants;

        public Faculty(String name, String teacherName, List<Exam> exams,
                       List<Entrant> entrants) {
            this.name = name;
            this.teacherName = teacherName;
            this.exams = exams;
            this.entrants = Collections.emptyList();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public List<Exam> getExams() {
            return exams;
        }

        public void setExams(List<Exam> exams) {
            this.exams = exams;
        }

        public List<Entrant> getEntrants() {
            return entrants;
        }

        public void registerEntrant(Entrant entrant) {
            if (entrant.isRegistered == true) {
                Log.e("WARNING", "Already registered!");
            } else {
                entrants.add(entrant);
                entrant.registerOnFaculty(name);
            }
        }

        public void enrollEntrant(Entrant entrant) {
            if (entrant.isEntranced == false) {
                if (entrant.isExamPassed) {
                    entrant.setEntranced(true);
                }
            } else {
                Log.e("WARNING", "Already enrolled");
            }
        }
    }


    class Entrant {
        private String name;
        private String facultyName;
        private boolean isRegistered;
        private boolean isExamPassed;
        private boolean isEntranced;
        private List<Answer> answers;

        public Entrant(String name) {
            this.name = name;
            this.isRegistered = false;
            this.isExamPassed = false;
            this.isEntranced = false;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void registerOnFaculty(String facultyName) {
            this.facultyName = facultyName;
            isRegistered = true;
        }

        public boolean isExamPassed() {
            return isExamPassed;
        }

        public void setExamPassed(boolean examPassed) {
            this.isExamPassed = examPassed;
        }

        public boolean isEntranced() {
            return isEntranced;
        }

        public void setEntranced(boolean entranced) {
            this.isEntranced = entranced;
        }

        public void passExam(Faculty faculty) {
            for (Exam exam : faculty.exams) {
                answers.add(new Answer(exam.task));
            }
        }
    }


    class Teacher {
        private String name;
        private String subject;

        public Teacher(String name, String subject) {
            this.name = name;
            this.subject = subject;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void rateEntrant(Entrant entrant) {
            for (Answer answer : entrant.answers) {
                answer.check();
                if (entrant.isExamPassed == false) {
                    entrant.setExamPassed(true);
                } else {
                    Log.e("WARNING", "Exam already passed!");
                }
            }
        }
    }

    /*
      VII

      Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */
    class Product {
        private String name;
        private int price;

        public Product(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @NonNull
        @Override
        public String toString() {
            return "Name: " + name + " " + "Price: " + price;
        }
    }


    class Order {
        private List<Product> orderList = Collections.emptyList();
        private boolean payment = false;
        private boolean register = false;

        public void addProdToOrder(List<Product> orderList) {
            this.orderList = orderList;
        }

        public void addProdToOrder(Product product) {
            orderList.add(product);
        }

        public void showOrder() {
            for (Product product : orderList) {
                Log.e("ORDER", product.toString());
            }
        }

        public boolean isPayment() {
            return payment;
        }

        public void setPayment(boolean payment) {
            this.payment = payment;
        }

        public boolean isRegister() {
            return register;
        }

        public void setRegister(boolean register) {
            this.register = register;
        }

    }


    class Customer {
        private Order order;

        public void book(Order order) {
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }

        public void showOrder() {
            order.showOrder();
        }

        public void pay() {
            if (order.isPayment() == true) {
                Log.e("WARNING", "Вы уже оплатили заказ");
            } else {
                order.setPayment(true);
            }
        }

        public void take() {
            if (order.isPayment() == false) {
                Log.e("WARNING", "Вы ещё не оплатили товар");
            } else if (order.isRegister() == false) {
                Log.e("WARNING", "Ваша заявка ещё не обработана");
            } else {
                Log.e("SUCCESS", "Спасибо за покупку!");
            }
        }

    }


    class Administrator {
        private List<Product> products = Collections.emptyList();
        private List<Customer> blackList = Collections.emptyList();

        public void registerOrder(Customer customer) {
            if (customer.getOrder().isPayment() == true) {
                customer.getOrder().setRegister(true);
            } else {
                blackList.add(customer);
            }
        }

        public void createNewProduct(String name, int price) {
            products.add(new Product(name, price));
        }
    }

}
