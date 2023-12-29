package ru.zubkoff;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {
  public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
    destination.addAll(source);
  }

  public static <T> List<T> newArrayList() {
    return new ArrayList<T>();
  }

  public static <T> int indexOf(List<? super T> source, T o) {
    return source.indexOf(o);
  }

  public static <T> List<T> limit(List<T> source, int size) {
    return new ArrayList<>(source.subList(0, size));
  }

  public static <T> void add(List<? super T> source, T o) {
    source.add(o);
  }

  public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
    removeFrom.removeIf(c2::contains);
  }

  // true если первый лист содержит все элементы второго
  public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
    return c1.containsAll(c2);
  }

  // true если первый лист содержит хотя-бы 1 второго
  public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
    return c1.stream().anyMatch(c2::contains);
  }

  // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до
  // max.
  // Элементы сравнивать через Comparable.
  // Прмер range(Arrays.asList(8,1,3,5,6,4), 3, 6) вернет {3,4,5,6}
  public static <T1 extends Comparable<? super T1>, T2 extends T1, T3 extends T1> List<? extends T1> range(
      List<? extends T1> list, T2 min, T3 max) {
    return list.stream()
        .filter(item -> min.compareTo(item) <= 0 && max.compareTo(item) >= 0)
        .collect(Collectors.toList());
  }

  // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до
  // max.
  // Элементы сравнивать через Comparable.
  // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
  public static <T1, T2 extends T1, T3 extends T1> List<? extends T1> range(List<? extends T1> list, T2 min, T3 max,
      Comparator<? super T1> comparator) {
    return list.stream()
        .filter(item -> comparator.compare(min, item) <= 0 && comparator.compare(max, item) >= 0)
        .collect(Collectors.toList());
  }
}
