package com.lab4;

/**
 * Класс реализации метода hash интерфейса IHash
 */
public class StrongHash implements IHash {

  /**
   * Метод, реализующий абстрактный метод hash интерфейса IHash,
   * вычисляет сильный хеш строки
   *
   * @param strToHash строка для вычисления хеша
   * @return сильный хеш строки
   */
  @Override
  public int hash(String strToHash) {
    IHash implement = (String str) -> {
      if (str != null) {
        int count = 1;
        int res = 0;
        for (int letCode : str.chars().toArray()) {
          res += count++ * letCode;
        }
        return res;
      }
      return -1;
    };
    return implement.hash(strToHash);
  }
}
