package com.lab3;

import java.util.Objects;

/**
 * Класс преподавателя
 */
public class Teacher extends Employee {

    /**
     * Количество групп у преподавателя
     */
    private int groupCount;
    /**
     * Кафедра преподавателя
     */
    private String department;

    /**
     * Конструктор Teacher без параметров
     */
    public Teacher() {
        this.groupCount = -1;
        this.department = "Unknown";
    }

    /**
     * Конструктор Teacher с параметрами
     *
     * @param name       имя преподавателя
     * @param yearsOld   возраст преподавателя
     * @param department кафедра преподавателя
     * @param groupCount количество групп у преподавателя
     */
    public Teacher(int yearsOld, String name, int lengthOfWork, String jobTitle, int groupCount,
        String department) {
        super(yearsOld, name, lengthOfWork, jobTitle);
        if (groupCount > 0 & department != null) {
            this.department = department;
            this.groupCount = groupCount;
        } else {
            this.groupCount = -1;
            this.department = "Unknown";
        }
    }

    /**
     * Конструктор Teacher от Person
     *
     * @param employee исходный работник с заполненными полями
     */
    public Teacher(Employee employee) {
        super(employee.getYearsOld(), employee.getName(), employee.getLengthOfWork(), employee.getJobTitle());
    }

    /**
     * Метод получения значения кафедры преподавателя
     *
     * @return private String department - кафедра преподавателя
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Метод получения значения количества групп у преподавателя
     *
     * @return private int groupCount - количество групп у преподавателя
     */
    public int getGroupCount() {
        return groupCount;
    }

    /**
     * Метод установки значения кафедры преподавателя
     *
     * @param department кафедра преподавателя
     * @return true при корректной кафедре false при некорректной
     */
    public boolean setDepartment(String department) {
        if (department != null) {
            this.department = department;
            return true;
        }
        return false;
    }

    /**
     * Метод установки значения количества групп у преподавателя
     *
     * @param groupCount количество групп у преподавателя
     * @return true при корректном количестве false при некорректном
     */
    public boolean setGroupCount(int groupCount) {
        if (groupCount > 0) {
            this.groupCount = groupCount;
            return true;
        }
        return false;
    }

    /**
     * Метод строкового представления Teacher
     */
    @Override
    public String toString() {
        return String.format("""
                        Преподаватель %s
                        %d лет
                        Стаж работы: %d лет
                        Кафедра: %s
                        %d групп обучает""",
                super.getName(),
                super.getYearsOld(),
                super.getLengthOfWork(),
                this.getDepartment(),
                this.getGroupCount());
    }

    /**
     * Метод сравнения Teacher с Object
     *
     * @param o сравниваемый Object
     * @return true при эквивалентности объектов false при их различности
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Teacher teacher = (Teacher) o;
        return this.getGroupCount() == teacher.getGroupCount()
                && this.getDepartment().equals(teacher.getDepartment());
    }

    /**
     * Метод получения хэш-кода объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.department, this.groupCount);
    }
}