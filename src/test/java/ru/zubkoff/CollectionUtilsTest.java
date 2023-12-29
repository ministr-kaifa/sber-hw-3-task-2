package ru.zubkoff;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class CollectionUtilsTest {

  @Test
  public void indexOfTest() {
    assertThat(CollectionUtils.indexOf(List.of(1,2,3,4,5), 2)).isEqualTo(1);
  }
  
  @Test
  public void limitTest() {
    assertThat(CollectionUtils.limit(List.of(1,2,3,4,5), 3).size()).isEqualTo(3);
  }

  class Parent {

    public int value;

    public Parent(int value) {
      this.value = value;
    }

    @Override
    public int hashCode() {
      return value;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null || getClass() != obj.getClass())
        return false;
      if (value != ((Parent)obj).value)
        return false;
      return true;
    }

  }
  
  class Child extends Parent {

    public Child(int value) {
      super(value);
    }
  
  }

  @Test
  public void addTest() {
    var list = new ArrayList<Parent>(List.of(new Parent(1), new Parent(2), new Parent(3)));
    CollectionUtils.add(list, new Child(4));
    assertThat(list.size()).isEqualTo(4);
  }
  
  @Test
  public void removeAllTest() {
    var list1 = new ArrayList<>(List.of(new Parent(1), new Child(2), new Parent(3), new Child(4)));
    var list2 = List.of(new Child(2), new Parent(3));
    CollectionUtils.removeAll(list1, list2);
    assertThat(list1).hasSameElementsAs(List.of(new Parent(1), new Child(4)));
  }

  @Test
  public void containsAllTest() {
    var list1 = List.of(new Parent(1), new Child(2), new Parent(3), new Child(4));
    var list2 = List.of(new Child(9), new Parent(2));
    assertThat(CollectionUtils.containsAll(list1, list2)).isFalse();
    list1 = List.of(new Child(9), new Parent(2));
    assertThat(CollectionUtils.containsAll(list1, list2)).isTrue();
  }

  @Test
  public void containsAnyTest() {
    var list1 = List.of(new Parent(1), new Child(2), new Parent(3), new Child(4));
    assertThat(CollectionUtils.containsAll(list1, List.of(new Parent(2)))).isFalse();
    assertThat(CollectionUtils.containsAll(list1, List.of(new Child(2)))).isTrue();
  }

  @Test 
  public void rangeTest() {
    assertThat(CollectionUtils.range(Arrays.asList(8,1,3,5,6,4), 3, 6)).hasSameElementsAs(List.of(3,4,5,6));
  }

  @Test 
  public void rangeComparatorTest() {
    assertThat(CollectionUtils.range(Arrays.asList(8,1,3,5,6,4), 3, 6, Comparator.naturalOrder()))
      .hasSameElementsAs(List.of(3,4,5,6));
  }

}
