package com.lab4;

/**
 * Класс реализации метода hash интерфейса IHash
 */
public class WeakHash implements IHash {

  /**
   * Метод, реализующий абстрактный метод hash интерфейса IHash,
   * вычисляет слабый хеш строки
   *
   * @param strToHash строка для вычисления хеша
   * @return слабый хеш строки
   */
  @Override
  public int hash(String strToHash) {
    IHash implement = (String str) -> {
      if (str != null) {
        return (int) str.chars().distinct().count();
      }
      return -1;
    };
    return implement.hash(strToHash);
  }

}
