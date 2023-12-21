package com.lab4;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

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

  int INPUT_STRING = 1,
      PRINT_STRING = 2,
      GET_WEAK_HASH = 3,
      GET_STRONG_HASH = 4,
      EXIT = 5;
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
   * Строка, для вычисления хеша
   */
  String strToHash = null;

  /**
   * Метод основного меню работы
   */
  public void menu() {

    int choice;

    do {
      this.out.println("""
          1. Ввести строку
          2. Вывести строку
          3. Вывести слабый хеш
          4. Вывести сильный хеш
          5. Выход
          """);

      choice = inp.getInt();
      switch (choice) {
        case INPUT_STRING -> getString();
        case PRINT_STRING -> printStr();
        case GET_WEAK_HASH -> printWeakHash();
        case GET_STRONG_HASH -> printStrongHash();
        default -> {
          if (choice != EXIT) {
            this.out.println("Некорректный ввод");
          }
        }
      }

    } while (choice != EXIT);

  }

  /**
   * Метод получения строки для работы с ней
   */
  private void getString(){
    String newStr = inp.getString();
    if (newStr == null){
      out.println("Некорректный ввод");
    }
    this.strToHash = newStr;
  }

  /**
   * Метод проверки строки на то, что она введена
   * @return true, когда строка введена, false если строку еще не ввели
   */
  private boolean checkStr(){
    return this.strToHash != null;
  }

  /**
   * Метод печати строки в консоль
   */
  private void printStr(){
    if (checkStr()){
      out.println(this.strToHash);
    } else {
      out.println("Строка еще не введена");
    }
  }

  /**
   * Вывод слабого хеша строки в консоль
   */
  private void printWeakHash(){
    if (checkStr()){
      out.printf("Слабый хеш строки: %d\n", new WeakHash().hash(this.strToHash));
    } else {
      out.println("Строка еще не введена");
    }
  }

  /**
   * Вывод сильного хеша строки в консоль
   */
  private void printStrongHash(){
    if (checkStr()){
      out.printf("Сильный хеш строки: %d\n", new StrongHash().hash(this.strToHash));
    } else {
      out.println("Строка еще не введена");
    }
  }

}