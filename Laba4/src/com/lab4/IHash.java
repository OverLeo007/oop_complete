package com.lab4;

/** Интерфейс, позволяющий вычислять хеш строки. */
@FunctionalInterface
public interface IHash {

  /**
   * Абстрактный метод, который может быть реализован для вычисления хеша строки
   * @param str строка для вычисления хеша
   * @return хеш строки
   */
  int hash(String str);

}
