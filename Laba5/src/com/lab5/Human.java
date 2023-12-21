package com.lab5;


import com.lab5.Exceptions.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.lab5.Exceptions.*;

/**
 * Класс человека
 */
public class Human {

  /**
   * Для приведения чисел к нужному формату
   */
  private final DecimalFormat decimalFormat = new DecimalFormat("#.###");
  private float height;
  /**
   * Вес человека
   */
  private int weight;
  /**
   * Имя человека
   */
  private String name;
  /**
   * Фамилия человека
   */
  private String surname;
  /**
   * Пол человека
   */
  private boolean gender;
  /**
   * Флаг, активируемый при вводе пола в экземпляр класса
   */
  private boolean isGender;

  /**
   * Конструктор возвращающий пустой экземпляр класса
   */
  public Human() {

    this.isGender = false;
  }

  /**
   * Конструктор возвращаюший заполненный экземпляр класса
   *
   * @param height  Рост человека
   * @param weight  Вес человека
   * @param name    Имя человека
   * @param surname Фамилия человека
   * @param gender  Пол человека
   * @throws IncorrectStringException при некорректных строковых значениях
   * @throws IncorrectFloatException  при некорректных float значениях
   * @throws IncorrectIntException при некорректных int значениях
   */
  public Human(float height, int weight, String name, String surname, String gender)
      throws IncorrectStringException, IncorrectFloatException, IncorrectIntException {

    if (height <= 0) {
      throw new IncorrectFloatException("Некорректный рост человека", height);
    }
    if (weight <= 0) {
      throw new IncorrectIntException("Некорректный вес человека", weight);
    }
    if (name == null) {
      throw new IncorrectStringException("Некорректное имя человека", null);
    }
    if (surname == null) {
      throw new IncorrectStringException("Некорректная фамилия человека", null);
    }

    this.height = height;
    this.weight = weight;

    this.name = name;
    this.surname = surname;
    setGender(gender);
    this.isGender = true;
  }


  /**
   * Метод получения имя и фамилии человека
   *
   * @return имя и фамилия через пробел
   */
  public String getFullName() {
    return String.format("%s %s", this.name, this.surname);
  }

  /**
   * Сеттер веса человека
   *
   * @param weight вес человека
   * @throws IncorrectIntException при некорректном весе человека
   */
  public void setWeight(int weight) throws IncorrectIntException{
    if (weight <= 0) {
      throw new IncorrectIntException("Некорректный вес", weight);
    }
    this.weight = weight;
  }

  /**
   * Геттер веса человека
   *
   * @return вес человека
   */
  public float getWeight() {
    return this.weight;
  }

  /**
   * Сеттер роста человека
   *
   * @param height рост человека
   * @throws IncorrectFloatException при некорректном росте человека
   */
  public void setHeight(float height) throws IncorrectFloatException{
    if (height <= 0) {
      throw new IncorrectFloatException("Некорректный вес ", weight);
    }
    this.height = height;
  }

  /**
   * Геттер роста человека
   *
   * @return рост человека
   */
  public float getHeight() {

    return this.height;
  }


  /**
   * Сеттер имени человека
   *
   * @param name имя человека
   * @throws IncorrectStringException при некорректном имени человека
   */
  public void setName(String name) throws IncorrectStringException {
    if (name == null) {
      throw new IncorrectStringException("Некорректное имя человека", null);
    }
    this.name = name;
  }

  /**
   * Геттер имени человека
   *
   * @return Имя человека
   */
  public String getName() {
    return this.name;
  }

  /**
   * Сеттер фамилии человека
   *
   * @param surname фамилия человека
   * @throws IncorrectStringException при некорректной фамилии человека
   */
  public void setSurname(String surname) throws IncorrectStringException {
    if (surname == null) {
      throw new IncorrectStringException("Некорректная фамилия человека", null);
    }
    this.surname = surname;
  }

  /**
   * Геттер фамилии человека
   *
   * @return Фамилию человека
   */
  public String getSurname() {
    return this.surname;
  }

  /**
   * Сеттер пола человека, преобразует строковые обозначения в boolean
   *
   * @param gender, где ж - женский пол, м - мужской
   * @throws IncorrectStringException при некорректном обозначении пола (м/ж)
   */
  public void setGender(String gender) throws IncorrectStringException{
    if (Objects.equals(gender.toLowerCase(), "м")) {
      this.gender = true;
      this.isGender = true;
    } else if (Objects.equals(gender.toLowerCase(), "ж")) {
      this.gender = false;
      this.isGender = true;
    } else {
      throw new IncorrectStringException("Некорректное обозначение пола", gender);
    }
  }

  /**
   * Геттер пола человека
   *
   * @return Строку содержащую пол человека
   */
  public String getGender() {
    if (!this.isGender) {
      return "Нет данных";
    }
    if (this.gender) {
      return "Мужской";
    } else {
      return "Женский";
    }
  }

  /**
   * Метод получения ИМТ человека, рассчитываемый из роста и веса человека
   *
   * @return строку, содержащую вердикт о комплекции человека согласно ИМТ
   */
  public String getBMI() {
    float VERY_LOW_MASS = 16f,
        LOW_MASS = 18.5f,
        NORM_MASS = 25f,
        NEAR_TO_FAT_MASS = 30f,
        FAT_MASS = 35f,
        VERY_FAT_MASS = 40f;
    if (this.weight == 0 || this.height == 0) {
      return "Недостаточно данных";
    }
    double BMI = this.weight / Math.pow(this.height, 2);
    if (BMI <= VERY_LOW_MASS) {
      return "Выраженный дефицит массы тела";
    } else if (BMI < LOW_MASS) {
      return "Недостаточная масса тела";
    } else if (BMI < NORM_MASS) {
      return "Норма";
    } else if (BMI < NEAR_TO_FAT_MASS) {
      return "Избыточная масса тела";
    } else if (BMI < FAT_MASS) {
      return "Ожирение 1 степени";
    } else if (BMI < VERY_FAT_MASS) {
      return "Ожирение 2 степени";
    } else {
      return "Ожирение 3 степени";
    }
  }

  /**
   * Метод строкового представления класса
   */
  @Override
  public String toString() {
    return String.format("""
            Имя: %s
            Фамилия: %s
            Пол: %s
            Рост: %s
            Вес: %s
            Конфигурация тела,
            согласно ИМТ: %s""",
        this.getName(),
        this.getSurname(),
        this.getGender(),
        this.decimalFormat.format(this.getHeight()),
        this.decimalFormat.format(this.getWeight()),
        this.getBMI());
  }
}