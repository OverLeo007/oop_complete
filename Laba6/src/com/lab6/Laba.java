package com.lab6;


import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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

  int MAKE_INT_ARRAY = 1,
      MAKE_STRING_ARRAY = 2,
      EXIT = 3;

  int PRINT_ARRAY = 1,
      GET_BY_INDEX = 2,
      SET_BY_INDEX = 3,
      CHANGE_START_INDEX = 4,
      CHANGE_END_INDEX = 5,
      INVERT_ARRAY = 6,
      CONCAT_ARRAY = 7,
      FIND_ITEM = 8,

  INSERT_SEQ = 9,
      PRINT_BOUNDED = 10,
      ARR_MENU_EXIT = 11;
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
   * Массив со строками
   */
  private MyArray<String> strArray;
  /**
   * Массив с числами
   */
  private MyArray<Integer> intArray;


  /**
   * Метод основного меню работы
   */
  public void menu() {

    int choice;

    do {
      this.out.println("""
          1. Создать массив типа Int
          2. Создать массив типа String
          3. Выход
          """);

      choice = inp.getInt();
      switch (choice) {
        case MAKE_INT_ARRAY -> setIntArray();
        case MAKE_STRING_ARRAY -> setStringArray();
        default -> {
          if (choice != EXIT) {
            this.out.println("Некорректный ввод");
          }
        }
      }

    } while (choice != EXIT);
  }


  /**
   * Метод установки значений для массива чисел
   */
  private void setIntArray() {
    while (true) {
      out.println("Введите числа через пробел");
      try {
        Integer[] ints = inp.getIntLine();
        intArray = new MyArray<>(ints);
        arrayMenu("int");
        return;
      } catch (NumberFormatException e) {
        out.println("Некорректное значение " + e.getMessage());
      }
    }
  }

  /**
   * Метод установки значений для массива строк
   */
  private void setStringArray() {
    while (true) {
      out.println("Введите строки через пробел");
      try {
        String[] strings = inp.getStrLine();
        strArray = new MyArray<>(strings);
        arrayMenu("str");
        return;
      } catch (NullPointerException e) {
        out.println("Некорректное значение");
      }
    }

  }

  /**
   * Меню взаимодействий с массивом
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void arrayMenu(String arrType) {
    int choice;

    do {
      this.out.println("""
          1. Вывести массив
          2. Получить значение элемента по индексу
          3. Установить значение элемента по индексу
          4. Изменить начальный индекс
          5. Изменить конечный индекс
          6. Инвертировать массив
          7. Конкатенация текущего массива с введенным
          8. Найти индекс первого вохждения
          9. Вставить последовательность после элемента с индексом
          10. Вывести часть массива с заданными границами
          11. Выход
          """);

      choice = inp.getInt();
      switch (choice) {
        case PRINT_ARRAY -> printArray(arrType);
        case GET_BY_INDEX -> getByIndex(arrType);
        case SET_BY_INDEX -> setByIndex(arrType);
        case CHANGE_START_INDEX -> changeStartIndex(arrType);
        case CHANGE_END_INDEX -> changeEndIndex(arrType);
        case INVERT_ARRAY -> invertArray(arrType);
        case CONCAT_ARRAY -> concatArray(arrType);
        case INSERT_SEQ -> insertSequence(arrType);
        case FIND_ITEM -> findItem(arrType);
        case PRINT_BOUNDED -> printBoundedArray(arrType);
        default -> {
          if (choice != ARR_MENU_EXIT) {
            this.out.println("Некорректный ввод");
          }
        }
      }

    } while (choice != ARR_MENU_EXIT);

  }

  /**
   * Метод вывода массива на экран
   *
   * @param arrType  Тип массива ("str" или "int")
   */
  private void printArray(String arrType) {
    if (Objects.equals(arrType, "str")) {
      out.println(strArray);
    } else {
      out.println(intArray);
    }
  }

  /**
   * Метод получения значения из массива и вывода его на экран
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void getByIndex(String arrType) {
    out.println("Введите индекс:");
    int index = inp.getInt();
    try {
      if (Objects.equals(arrType, "str")) {
        out.println(strArray.getItemAt(index));
      } else {
        out.println(intArray.getItemAt(index));
      }
    } catch (IndexOutOfBoundsException e) {
      out.println("Некорректный индекс: " + e.getMessage());
    }
  }

  /**
   * Метод установки значения введенного из консоли в массив
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void setByIndex(String arrType) {
    out.println("Введите индекс:");
    int index = inp.getInt();
    out.println("Введите значение:");
    try {
      if (Objects.equals(arrType, "str")) {
        strArray.setItemAt(index, inp.getString());
      } else {
        int newInt = inp.getInt();
        if (newInt == -1) {
          throw new NumberFormatException();
        }
        intArray.setItemAt(index, newInt);
      }
    } catch (IndexOutOfBoundsException e) {
      out.println("Некорректный индекс: " + e.getMessage());
    } catch (NumberFormatException e) {
      out.println("Некорректное значение");
    }
  }

  /**
   * Изменение начального индекса массива
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void changeStartIndex(String arrType) {
    out.println("Введите индекс:");
    int index = inp.getInt();
    if (index == -1) {
      out.println("Некорректный индекс");
      return;
    }
    if (Objects.equals(arrType, "str")) {
      strArray.setStartIndex(index);
    } else {
      intArray.setStartIndex(index);
    }
  }

  /**
   * Изменение конечного индекса массива
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void changeEndIndex(String arrType) {
    out.println("Введите индекс:");
    int index = inp.getInt();
    if (index == -1) {
      out.println("Некорректный индекс");
      return;
    }
    if (Objects.equals(arrType, "str")) {
      strArray.setEndIndex(index);
    } else {
      intArray.setEndIndex(index);
    }
  }

  /**
   * Инвертирование массива
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void invertArray(String arrType) {
    if (Objects.equals(arrType, "str")) {
      strArray.invert();
    } else {
      intArray.invert();
    }
  }

  /**
   * Конкатенация двух массивов
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void concatArray(String arrType) {
    if (Objects.equals(arrType, "str")) {
      while (true) {
        out.println("Введите строки через пробел");
        try {
          String[] strings = inp.getStrLine();
          strArray.extend(strings);
          return;
        } catch (NullPointerException e) {
          out.println("Некорректное значение");
        }
      }
    } else {
      while (true) {
        out.println("Введите числа через пробел");
        try {
          Integer[] ints = inp.getIntLine();
          intArray.extend(ints);
          return;
        } catch (NumberFormatException e) {
          out.println("Некорректное значение " + e.getMessage());
        }
      }
    }
  }

  /**
   * Метод поиска элемента в массиве
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void findItem(String arrType) {
    if (Objects.equals(arrType, "str")) {
      out.println(strArray.find(inp.getString()));
    } else {
      out.println(intArray.find(inp.getInt()));
    }
  }

  /**
   * Метод вставки последовательности после элемента с индексом
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void insertSequence(String arrType) {
    out.println("ВВедите индекс");
    int index = inp.getInt();

    if (Objects.equals(arrType, "str")) {
      while (true) {
        out.println("Введите строки через пробел");
        try {
          String[] strings = inp.getStrLine();
          strArray.insert(index, strings);
          return;
        } catch (NullPointerException e) {
          out.println("Некорректное значение");
        } catch (IndexOutOfBoundsException e) {
          out.println("Некорректный индекс " + e.getMessage());
        }
      }
    } else {
      while (true) {
        out.println("Введите числа через пробел");
        try {
          Integer[] ints = inp.getIntLine();
          intArray.insert(index, ints);
          return;
        } catch (NumberFormatException e) {
          out.println("Некорректное значение " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
          out.println("Некорректный индекс " + e.getMessage());
        }
      }
    }

  }

  /**
   * Метод вывода массива с заданными границами
   *
   * @param arrType Тип массива ("str" или "int")
   */
  private void printBoundedArray(String arrType) {
    out.println("Введите левую границу");
    int left = inp.getInt();
    out.println("Введите правую границу");
    int right = inp.getInt();
    if (left == -1 || right == -1) {
      out.println("Некорректное значение");
    }
    try {
      if (Objects.equals(arrType, "str")) {
        out.println(strArray.toString(left, right));
      } else {
        out.println(intArray.toString(left, right));
      }
    } catch (IndexOutOfBoundsException e) {
      out.println("Некорректный индекс: " + e);
    }
  }


}