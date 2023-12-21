package com.lab6;

import java.util.Arrays;


public class MyArray<T> {


  /**
   * Кол-во элементов массива
   */
  private int size;
  /**
   * Размер массива (с незаполненными элементами)
   */
  private int capacity;


  /**
   * Массив под элементы любого типа
   */
  private Object[] data;

  /**
   * Начальный индекс массива
   */
  private int startIndex = 0;


  /**
   * Конструктор массива, позволяющий создать массив определенной длины
   *
   * @param length длина массива
   */
  public MyArray(int length) {
    if (length > 0) {
      this.data = new Object[length];
      this.capacity = length;
      this.size = length;
    } else if (length == 0) {
      this.data = new Object[0];
    } else {
      throw new IllegalArgumentException("Некорректная длина массива: " + length);
    }
  }

  /**
   * Конструктор массива, позволяющий создать
   * массив из последовательности элементов определенного типа
   *
   * @param elements последовательность элементов
   */
  @SafeVarargs
  public MyArray(T... elements) {
    int new_size = elements.length;
    data = Arrays.copyOf(elements, new_size, Object[].class);
    this.size = new_size;
    this.capacity = new_size;
  }

  /**
   * Констркутор массива без параметров
   */
  public MyArray() {
    this(0);
  }

  /**
   * Метод увеличения размера массива,
   * увеличивает размер вдвое если кол-во элементов совпадает с размером
   */
  private void increaseCapacity() {
    if (size == capacity) {
      if (size != 0) {
        capacity *= 2;
      } else {
        capacity = 1;
      }
      updCapacity();
    }
  }

  /**
   * Метод увеличения размера массива на заданную длину
   *
   * @param additionalCapacity длина на которую увеличиваем длину массива
   */
  private void increaseCapacity(int additionalCapacity) {
    capacity += additionalCapacity;
    updCapacity();
  }

  /**
   * Метод уменьшения длины массива вдвое,
   * при соотношении кол-ва элементов к длине = 1/2
   */
  private void decreaseCapacity() {
    if (size <= capacity / 2) {
      capacity /= 2;
      updCapacity();
    }
  }

  /**
   * Метод пересоздания массива с новой длиной
   */
  private void updCapacity() {
    Object[] temp;
    temp = Arrays.copyOf(data, capacity, Object[].class);
    data = temp;
  }

  /**
   * Метод проверки индекса на соответствие границам массива
   *
   * @param index проверяемый индекс
   */
  private void checkIndex(int index) {
    if (size == 0){
      throw new IndexOutOfBoundsException("Cannot access by index to empty array");
    }
    if (index < startIndex || index - startIndex > size - 1) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
  }

  /**
   * Метод установки начального индекса
   *
   * @param newIndex новый начальный индекс
   */
  public void setStartIndex(int newIndex){
    startIndex = newIndex;
  }

  /**
   * Метод установки конечного индекса, с пересчетом начального
   * исходя из кол-ва элементов
   *
   * @param newIndex новый конечный индекс
   */
  public void setEndIndex(int newIndex){
    if (size == 0){
      startIndex = newIndex;
      return;
    }
    startIndex = newIndex - size + 1;
  }

  /**
   * Метод установки значения по индексу
   *
   * @param index индекс значения
   * @param item новый элемент
   */
  public void setItemAt(int index, T item) {
    checkIndex(index);
    data[index - startIndex] = item;
  }

  /**
   * Метод получения значения по индексу
   *
   * @param index индекс получаемого значения
   * @return значение расположенное по индексу
   */
  @SuppressWarnings("unchecked")
  public T getItemAt(int index) {
    checkIndex(index);
    return (T) data[index - startIndex];
  }

  /**
   * Метод добавления элемента в массив
   *
   * @param item новый элемент массива
   */
  public void append(T item) {
    increaseCapacity();
    data[size] = item;
    size += 1;
  }

  /**
   * Метод расширения массива другим массивом
   *
   * @param additionalArray массив, которым расширяем
   */
  public void extend(MyArray<T> additionalArray) {
    increaseCapacity(additionalArray.size);
    System.arraycopy(additionalArray.data, 0, data, size, additionalArray.size);
    size += additionalArray.size;
  }

  /**
   * Метод расширения массива
   * последовательностью элементов
   *
   * @param items элемент(ы), которые будут добавлены в массив
   */
  @SafeVarargs
  public final void extend(T... items) {
    increaseCapacity(items.length);
    System.arraycopy(items, 0, data, size, items.length);
    size += items.length;
  }


  /**
   * Метод конкатенации двух массивов
   *
   * @param array1 первый массив
   * @param array2 второй массив
   * @param <E> тип обоих массивов
   * @return новый массив, полученный соединением первого и второго
   */
  public static <E> MyArray<E> concat(MyArray<E> array1, MyArray<E> array2){
    MyArray<E> newArray = new MyArray<E>(array1.size + array2.size);
    System.arraycopy(array1.data, 0, newArray.data, 0, array1.size);
    System.arraycopy(array2.data, 0, newArray.data, array1.size, array2.size);
    return newArray;
  }


  /**
   * Метод удаления элемента из массива по индексу,
   * с последующим возвращением этого элемента
   *
   * @param index индекс удаляемого элемента
   * @return удаленный элемент
   */
  @SuppressWarnings("unchecked")
  public T pop(int index) {
    checkIndex(index);
    T popVal = (T) data[index - startIndex];

    for (int i = index - startIndex; i < size - 1; i++) {
      data[i] = data[i + 1];
    }
    size -= 1;
    decreaseCapacity();
    return popVal;
  }

  /**
   * Метод удаления первого элемента из массива
   *
   * @return удаленный первый элемент массива
   */
  public T pop() {
    return pop(startIndex);
  }

  /**
   * Метод вставки последовательности элементов
   * в массив после индекса
   *
   * @param index индекс, после которого вставляем элементы
   * @param elements вставляемые элементы
   */
  @SafeVarargs
  public final void insert(int index, T... elements){
    checkIndex(index);
    int elSize = elements.length;
    increaseCapacity(elSize);
    if (size == 0){
      extend(elements);
      return;
    }
    for (int i = size - 1; i > index - startIndex; i --) {
      data[i + elSize] =  data[i];
      data[i] = null;
    }
    size += elements.length;
    System.arraycopy(elements, 0, data, index - startIndex + 1, elSize);
  }

  /**
   * Метод поиска индекса элемента
   * по его значению
   *
   * @param value значение элемента
   * @return индекс элемента или -1, если такового не нашлось
   */
  public int find(T value) {
    if (size == 0) {
      return -1;
    }
    for (int i = 0; i < size; i++) {
      if (data[i] == value) {
        return i + startIndex;
      }
    }
    return -1;
  }

  /**
   * Метод инвертирования массива
   */
  public void invert(){
    int end;
    if (size % 2 == 0){
      end = size / 2;
    } else {
      end = size / 2 + 1;
    }

    for (int i = 0; i < end; i ++){
      Object temp = data[i];
      data[i] = data[size - i - 1];
      data[size - i - 1] = temp;
    }

  }

  /**
   * Метод строкового представления массива
   *
   * @return строка с элементами массива
   */
  public String toString() {
    Object[] temp;
    temp = Arrays.copyOf(data, size, Object[].class);
    return Arrays.toString(temp);
  }

  /**
   * Метод строкового представления массива, с поддержкой границ вывода
   *
   * @param start с какого элемента выводим
   * @param end до какого элемента выводим (не включительно)
   * @return строка с элементами массива
   */
  public String toString(int start, int end){
    checkIndex(start);
    checkIndex(end);
    int size = end - start;
    Object[] temp = new Object[size];
    System.arraycopy(data, start, temp, 0, size);
    return Arrays.toString(temp);
  }

}
