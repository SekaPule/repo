package com.example.repo.collections;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        List<T> result = new ArrayList<>();
        if (firstList == null || secondList == null) {
            throw new NullPointerException();
        }

        result.addAll(firstList);
        result.addAll(secondList);
        result.sort(new ComparatorT());
        Collections.reverse(result);

        return result;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) {
        List<T> result = new ArrayList<>();

        if (inputList == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < inputList.size(); i++) {
            result.add(inputList.get(i));
            result.addAll(inputList.subList(0, i));
        }

        return result;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        if (firstList == null || secondList == null) {
            throw new NullPointerException();
        }

        return new HashSet<>(firstList).equals(new HashSet<>(secondList));
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) {
        List<T> copyInputList = new ArrayList<>();
        List<T> result;
        List<T> dataBroker;

        copyInputList.addAll(inputList);

        if (inputList == null) {
            throw new NullPointerException();
        }

        if (n > 0) {
            result = copyInputList.subList(inputList.size() - n, inputList.size() - 1);
            dataBroker = copyInputList.subList(0, inputList.size() - n - 1);
            result.addAll(dataBroker);
        } else if (n < 0) {
            result = copyInputList.subList(n, inputList.size() - 1);
            dataBroker = copyInputList.subList(0, n - 1);
            result.addAll(dataBroker);
        } else {
            return inputList;
        }

        return result;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        if (inputList == null || a == null || b == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).equals(a)) {
                inputList.set(i, b);
            }
        }

        return inputList;
    }


    class ComparatorT implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */

}
    class Student {
        public String name;
        public String surname;
        public String patronymic;
        public int birthYear;
        public int studyYear;
        public String groupId;
        public Mark[] marks;

        Student(String name, String surname, String patronymic, int birthYear, int studyYear,
                String groupNumber, Mark[] marks) {
            this.name = name;
            this.surname = surname;
            this.patronymic = patronymic;
            this.birthYear = birthYear;
            this.studyYear = studyYear;
            this.groupId = groupNumber;
            this.marks = marks;
        }
    }


    class Mark {
        public String subject;
        public int value;

        Mark(String subject, int value) {
            this.subject = subject;
            this.value = value;
        }
    }


    class Students {
        private final int MARKS_QUANTITY = 5;
        private List<Student> students;

        Students(List<Student> students) {
            this.students = students;
        }

        public void arrangeByStudyYear(int studyYear) {
            students.sort(new StudyYearComparator());
        }

        public double avgMarkInGroupBySubject(String groupID, String subject) {
            int studentsInGroup = 0;
            int markSum = 0;

            for (Student student : students) {
                if (student.groupId.equals(groupID)) {

                    for (Mark mark : student.marks) {
                        if (subject.equals(mark.subject)) {
                            markSum += mark.value;
                            studentsInGroup++;
                        }
                    }
                }
            }

            return (double) markSum / (double) studentsInGroup;
        }

        public Student elderStudent() {
            if (students.size() == 0) {
                throw new NullPointerException();
            }

            Student eldStudent = students.get(0);

            for (Student student : students) {
                if (eldStudent.birthYear > student.birthYear) {
                    eldStudent = student;
                }
            }

            return eldStudent;
        }

        public Student youngerStudent() {
            if (students.size() == 0) {
                throw new NullPointerException();
            }

            Student yngStudent = students.get(0);

            for (Student student : students) {
                if (yngStudent.birthYear < student.birthYear) {
                    yngStudent = student;
                }
            }

            return yngStudent;
        }

        public Student bestAcademicRecord() {
            int markSum;
            double bestMarksAvg = 0;

            if (students.size() == 0) {
                throw new NullPointerException();
            }

            Student bestStudent = students.get(0);

            for (Student student : students) {
                markSum = 0;

                for (Mark mark : student.marks) {
                    markSum += mark.value;
                }

                if ((double) (markSum / MARKS_QUANTITY) > bestMarksAvg) {
                    bestStudent = student;
                    bestMarksAvg = (double) (markSum / MARKS_QUANTITY);
                }
            }

            return bestStudent;
        }
    }


    class StudyYearComparator implements Comparator<Student> {
        @Override
        public int compare(Student student1, Student student2) {
            if (student1.studyYear == student2.studyYear) {
                return student1.name.compareTo(student2.name);
            } else {
                return student1.studyYear - student2.studyYear;
            }
        }
    }

