package com.lab3;

import java.util.Objects;

/**
 * Класс сотрудника
 */
public class Employee extends Person {

  /**
   * Стаж работы сотрудника
   */
  private int lengthOfWork;
  /**
   * Должность сотрудника
   */
  private String jobTitle;

  /**
   * Конструктор Employee без параметров
   */
  public Employee() {
    this.lengthOfWork = -1;
    this.jobTitle = "Unknown";
  }

  /**
   * Конструктор Emploee с параметрами
   *
   * @param name         имя сотрудника
   * @param yearsOld     возраст сотрудника
   * @param jobTitle     должность сотрнудника
   * @param lengthOfWork стаж работы сотрундника
   */
  public Employee(int yearsOld, String name, int lengthOfWork, String jobTitle) {
    super(yearsOld, name);
    if (lengthOfWork > 0 & lengthOfWork < yearsOld & jobTitle != null) {
      this.lengthOfWork = lengthOfWork;
      this.jobTitle = jobTitle;
    } else {
      this.lengthOfWork = -1;
      this.jobTitle = "Unknown";
    }
  }

//  /**
//   * Конструктор Employee от Person
//   *
//   * @param person исходный человек с заполненными полями
//   */
//  public Employee(Person person) {
//    super(person.getYearsOld(), person.getName());
//  }

  /**
   * Метод получения значения должности сотрудника
   *
   * @return private String jobTitle - должность сотрудника
   */
  public String getJobTitle() {
    return jobTitle;
  }

  /**
   * Метод получения значения стажа работы сотрудника
   *
   * @return private int lengthOfWork - стаж работы сотрудника
   */
  public int getLengthOfWork() {
    return lengthOfWork;
  }

  /**
   * Метод установки значения должности сотрудника
   *
   * @param jobTitle должность сотрудника
   * @return true при корректной должности false при некорректной
   */
  public boolean setJobTitle(String jobTitle) {
    if (jobTitle != null) {
      this.jobTitle = jobTitle;
      return true;
    }
    return false;
  }

  /**
   * Метод установки значения стажа работы сотрудника
   *
   * @param lengthOfWork стаж работы сотрудника
   * @return true при корректном стаже false при некорректном
   */
  public boolean setLengthOfWork(int lengthOfWork) {
    if (lengthOfWork > 0 & lengthOfWork < super.getYearsOld()) {
      this.lengthOfWork = lengthOfWork;
      return true;
    }
    return false;
  }

  /**
   * Метод строкового представления Employee
   */
  @Override
  public String toString() {
    return String.format("""
            Сотрудник %s
            %d лет
            Должность: %s
            %d лет стажа""",
        super.getName(),
        super.getYearsOld(),
        this.getJobTitle(),
        this.getLengthOfWork());
  }

  /**
   * Метод сравнения Employee с Object
   *
   * @param o сравниваемый Object
   * @return true при эквивалентности объектов false при их различности
   */
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    Employee employee = (Employee) o;
    return this.getLengthOfWork() == employee.getLengthOfWork()
        && this.getJobTitle().equals(employee.getJobTitle());
  }

  /**
   * Метод получения хэш-кода объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.jobTitle, this.lengthOfWork);
  }

}