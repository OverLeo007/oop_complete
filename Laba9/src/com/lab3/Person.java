package com.lab3;

import java.util.Objects;

/**
 * Класс человека
 */
public class Person {


  /**
   * Сколько человеку лет
   */
  private int yearsOld;
  /**
   * Границы для возраста
   */
  private final int MAX_YEARS = 150, MIN_YEARS = 0;
  /**
   * Имя человека
   */
  private String name;

  /**
   * Конструктор Person без параметров
   */
  public Person() {
    this.yearsOld = -1;
    this.name = "Unnamed";
  }

  /**
   * Конструктор Person с параметрами
   *
   * @param name     имя человека
   * @param yearsOld возраст человека
   */
  public Person(int yearsOld, String name) {


    if (yearsOld >= MIN_YEARS & yearsOld < MAX_YEARS & name != null) {
      this.yearsOld = yearsOld;
      this.name = name;
    } else {
      this.yearsOld = -1;
      this.name = "Unnamed";
    }
  }



  /**
   * Метод получения значения имени человека
   *
   * @return private String name - имя человека
   */
  public String getName() {
    return name;
  }

  /**
   * Метод получения значения возраста человека
   *
   * @return private int yearsOld - возраст человека
   */
  public int getYearsOld() {
    return yearsOld;
  }

  /**
   * Метод установки значения имени человека
   *
   * @param name имя человека
   * @return true при корректном имени false при некорректном
   */
  public boolean setName(String name) {
    if (name != null) {
      this.name = name;
      return true;
    }
    return false;
  }

  /**
   * Метод установки значения возраста человека
   *
   * @param years_old возраст человека
   * @return true при корректном возрасте false при некорректном
   */
  public boolean setYearsOld(int years_old) {
    if (years_old >= MIN_YEARS & years_old < MAX_YEARS) {
      this.yearsOld = years_old;
      return true;
    }
    return false;
  }


  /**
   * Метод строкового представления Person
   */
  @Override
  public String toString() {
    return String.format("""
        Человек %s
        %d лет""", this.name, this.yearsOld);
  }

  /**
   * Метод сравнения Person с Object
   *
   * @param o сравниваемый Object
   * @return true при эквивалентности объектов false при их различности
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return this.yearsOld == person.yearsOld && this.name.equals(person.name);
  }

  /**
   * Метод получения хэш-кода объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, yearsOld);
  }
}