package com.lab3;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Класс для запуска программы
 */
public class Laba {

    public static void main(String[] args) {

        UI myUI = new UI();

        myUI.menu();
    }

}

/**
 * Интерфейс, реализующий нумерацию вариантов выбора для switch()
 */
interface menuEnum {

    int ADD_PERSON = 1,
            DELETE_PERSON = 2,
            PRINT_PERSONS = 3,
            COMPARE_PERSONS = 4,
            EXIT = 5;

    int ADD_STUDENT = 2,
            ADD_TEACHER = 3,
            ADD_EMPLOYEE = 4;

}

/**
 * Основной класс интерфейса
 */
class UI implements menuEnum {

    /**
     * Поток вывода, поддерживающий русские символы
     */
    private final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    /**
     * Экземпляр класса содержащего методы ввода разных типов
     */
    private final Inputer inp = new Inputer();

    /**
     * Основной список людей
     */
    private final ArrayList<Person> persons = new ArrayList<>();

    /**
     * Метод основного меню работы
     */
    public void menu() {

        int choice;

        do {
            this.out.println("""
                    1. Добавить человека
                    2. Удалить человека
                    3. Вывести всех людей
                    4. Сравнить двух людей
                    5. Выход
                    """);

            choice = inp.getInt();
            switch (choice) {
                case ADD_PERSON -> personAdder();
                case DELETE_PERSON -> deletePerson();
                case PRINT_PERSONS -> printPersons();
                case COMPARE_PERSONS -> comparator();
                default -> {
                    if (choice != EXIT) {
                        this.out.println("Некорректный ввод");
                    }
                }
            }

        } while (choice != EXIT);

    }


    /**
     * Метод меню добавления человека
     */
    private void personAdder() {
        int choice;

        do {
            this.out.println("""
                    1. Добавить человека
                    2. Добавить студента
                    3. Добавить преподавателя
                    4. Добавить сотрудника
                    5. Выйти в главное меню
                    """);

            choice = inp.getInt();
            switch (choice) {
                case ADD_PERSON -> adderHandler(1);
                case ADD_STUDENT -> adderHandler(2);
                case ADD_TEACHER -> adderHandler(3);
                case ADD_EMPLOYEE -> adderHandler(4);
                default -> {
                    if (choice != EXIT) {
                        this.out.println("Некорректный ввод");
                    }
                }
            }

        } while (choice != EXIT);
    }

    /**
     * Метод добавления человека, выбранного типа
     *
     * @param human_type выбранный тип человека
     */
    private void adderHandler(int human_type) {
        String data_answer;

        while (true) {
            out.println("Добавлять с входными данными (y/n)?");
            data_answer = inp.getString();
            if (data_answer == null) {
                continue;
            }
            if (data_answer.equals("y") || data_answer.equals("n")) {
                break;
            }
        }
        Person new_person = new Person();
        if (Objects.equals(data_answer, "y")) {
            boolean isName = false, isYO = false;
            while (!(isName & isYO)) {
                if (!isName) {
                    this.out.println("Введите имя человека");
                    isName = new_person.setName(this.inp.getString());
                    if (!isName) {
                        this.out.println("Некорректный ввод");
                    }
                }

                if (!isYO) {
                    this.out.println("Введите возраст человека");
                    isYO = new_person.setYearsOld(this.inp.getInt());
                    if (!isYO) {
                        this.out.println("Некорректный ввод");
                    }
                }
            }
        }

        switch (human_type) {
            case ADD_PERSON -> addPerson(new_person);
            case ADD_STUDENT -> addStudent(new_person);
            case ADD_TEACHER -> addEmployee(new_person, true);
            case ADD_EMPLOYEE -> addEmployee(new_person, false);
            default -> this.out.println("???");
        }
    }

    /**
     * Метод добавления человека
     *
     * @param person добавляемый человек
     */
    private void addPerson(Person person) {
        this.persons.add(person);
    }

    /**
     * Метод добавления студента
     *
     * @param person исходный человек с заполненными полями
     */
    private void addStudent(Person person) {
        if (person.getYearsOld() == -1) {
            this.persons.add(new Student());
            return;
        }

        Student newStudent = new Student(person);

        boolean isCourse = false, isFaculty = false;

        while (!(isCourse & isFaculty)) {
            if (!isFaculty) {
                this.out.println("Введите факультет студента");
                isFaculty = newStudent.setFaculty(this.inp.getString());
                if (!isFaculty) {
                    this.out.println("Некорректный ввод");
                }
            }

            if (!isCourse) {
                this.out.println("Введите курс студента (1-5)");
                isCourse = newStudent.setCourse(this.inp.getInt());
                if (!isCourse) {
                    this.out.println("Некорректный ввод");
                }
            }
        }

        this.persons.add(newStudent);
    }

    /**
     * Метод добавления преподавателя
     *
     * @param employee исходный сотрудник с заполненными полями
     */
    private void addTeacher(Employee employee) {
        if (employee.getYearsOld() == -1) {
            this.persons.add(new Teacher());
            return;
        }

        Teacher newTeacher = new Teacher(employee);

        boolean isGroupCount = false, isDepartment = false;

        while (!(isGroupCount & isDepartment)) {
            if (!isDepartment) {
                this.out.println("Введите кафедру преподавателя");
                isDepartment = newTeacher.setDepartment(this.inp.getString());
                if (!isDepartment) {
                    this.out.println("Некорректный ввод");
                }
            }

            if (!isGroupCount) {
                this.out.println("Введите кол-во групп которые ведет преподаватель");
                isGroupCount = newTeacher.setGroupCount(this.inp.getInt());
                if (!isGroupCount) {
                    this.out.println("Некорректный ввод");
                }
            }
        }

        this.persons.add(newTeacher);
    }

    /**
     * Метод добавления сотрудника
     *
     * @param person исходный человек с заполненными полями
     */
    private void addEmployee(Person person, boolean is_teacher) {
        if (person.getYearsOld() == -1) {
            if (is_teacher) {
                addTeacher(new Employee());
            } else {
                this.persons.add(new Employee());
            }
            return;
        }

        String jobTitle;
        int lengthOfWork;


        if (is_teacher) {
            jobTitle = "Преподаватель";
        } else {
            this.out.println("Введите должность сотрудника");
            jobTitle = this.inp.getString();
        }
        this.out.println("Введите стаж сотрудника");
        lengthOfWork = this.inp.getInt();


        Employee newEmp = new Employee(person.getYearsOld(), person.getName(), lengthOfWork, jobTitle);
        if (is_teacher) {
            addTeacher(newEmp);
        } else {
            this.persons.add(newEmp);
        }

    }

    /**
     * Метод удаления человека из списка
     */
    private void deletePerson() {
        out.println("Введите индекс человека в списке:");
        int index = inp.getInt();
        if (index < 1 || index > persons.size()) {
            out.println("Некорректный индекс");
        } else {
            this.out.println(persons.get(index - 1).getName() + " удален из списка");
            persons.remove(index - 1);
        }
    }

    /**
     * Метод вывода всех людей в списке
     */
    private void printPersons() {
        if (persons.isEmpty()) {
            out.println("Ни одного человека не добавлено");
            return;
        }

        int num = 1;
        for (Person person : persons) {
            out.printf("№%d\n", num++);
            out.println(person);
            out.println("************************");
        }
    }

    /**
     * Метод сравнения двух человек из списка
     */
    private void comparator() {
        int index1 = 1, index2 = 1;
        boolean isIndex1 = false, isIndex2 = false;

        while (!(isIndex1 & isIndex2)) {
            if (!isIndex1) {
                this.out.println("Введите индекс первого человека");
                index1 = inp.getInt();
                if (index1 > 1 || index1 < persons.size()) {
                    isIndex1 = true;
                } else {
                    out.println("Некорректный индекс");
                }
            }

            if (!isIndex2) {
                this.out.println("Введите индекс второго человека");
                index2 = inp.getInt();
                if (index2 > 1 || index2 < persons.size()) {
                    isIndex2 = true;
                } else {
                    out.println("Некорректный индекс");
                }
            }
        }
        if (persons.get(index1 - 1).equals(persons.get(index2 - 1))) {
            out.printf("Люди под номером %d и %d индентичны друг другу\n", index1, index2);
        } else {
            out.printf("Люди под номером %d и %d отличаются друг от друга\n", index1,
                    index2);
        }
    }
}