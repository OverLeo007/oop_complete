package com.lab3;

import java.util.Objects;

/**
 * Класс студента
 */
public class Student extends Person {

  /**
   * Номер курса студента
   */
  private int course;
  /**
   * Факультет студента
   */
  private String faculty;

  /**
   * Границы для курса
   */
  private final int MAX_COURSE = 5, MIN_COURSE = 0;

  /**
   * Конструктор Student без параметров
   */
  public Student() {
    this.course = -1;
    this.faculty = "Unknown";
  }

  /**
   * Конструктор Student с параметрами
   *
   * @param years_old возраст студента
   * @param name имя студента
   * @param course номер курса студента
   * @param faculty факультет студента
   */
  public Student(int years_old, String name, int course, String faculty) {
    super(years_old, name);
    if (course > MIN_COURSE & course <= MAX_COURSE & faculty != null) {
      this.course = course;
      this.faculty = faculty;
    } else {
      this.course = -1;
      this.faculty = "Unknown";
    }
  }

  /**
   * Конструктор Student от Person
   * @param person исходный человек с заполненными полями
   */
  public Student(Person person) {
    super(person.getYearsOld(), person.getName());
  }

  /**
   * Метод получения значения факультета студента
   *
   * @return private String faculty - wфакультет студента
   */
  public String getFaculty() {
    return faculty;
  }

  /**
   * Метод получения значения номера курса студента
   *
   * @return private int course - номер курса студента
   */
  public int getCourse() {
    return course;
  }

  /**
   * Метод установки значения факультета студента
   *
   * @param faculty факультет студента
   * @return true при корректном факультете false при некорректном
   */
  public boolean setFaculty(String faculty) {
    if (faculty != null) {
      this.faculty = faculty;
      return true;
    }
    return false;
  }

  /**
   * Метод установки значения номера курса студента
   *
   * @param course номер курса студента
   * @return true при корректном номере false при некорректном
   */
  public boolean setCourse(int course) {
    if (course > MIN_COURSE & course <= MAX_COURSE) {
      this.course = course;
      return true;
    }
    return false;
  }

  /**
   * Метод строкового представления Student
   */
  @Override
  public String toString() {
    return String.format("""
            Студент %s
            %d лет
            Факультет: %s
            %d курс обучения""",
        super.getName(),
        super.getYearsOld(),
        this.getFaculty(),
        this.getCourse());
  }

  /**
   * Метод сравнения Employee с Student
   *
   * @param o сравниваемый Object
   * @return true при эквивалентности объектов false при их различности
   */
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    Student student = (Student) o;
    return this.getCourse() == student.getCourse() && this.getFaculty()
        .equals(student.getFaculty());
  }

  /**
   * Метод получения хэш-кода объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.faculty, this.course);
  }
}