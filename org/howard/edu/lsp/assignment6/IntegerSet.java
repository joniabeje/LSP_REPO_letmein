package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntegerSet  {
  private List<Integer> set = new ArrayList<Integer>();

  // Clears the internal representation of the set.
  public void clear() { 
    set.clear();
  }

  // Returns the number of elements in the set.
  public int length() { 
    return set.size();
  }

  /*
   * Returns true if the 2 sets are equal, false otherwise;
   * Two sets are equal if they contain all of the same values in ANY order.
   * This overrides the equals method from the Object class.
   */
  @Override
  public boolean equals(Object o) { return false; }

  // Returns true if the set contains the value, otherwise false.
  public boolean contains(int value) { 
    return set.contains(value);
  }

  // Returns the largest item in the set (throws IllegalStateException if empty).
  public int largest()  { 
    if (set.isEmpty()) {
      throw new IllegalStateException("Set is empty");
    }
    return Collections.max(set);
  }

  // Returns the smallest item in the set (throws IllegalStateException if empty).
  public int smallest()  { 
    if (set.isEmpty()) {
      throw new IllegalStateException("Set is empty");
    }
    return Collections.min(set);
  }

  // Adds an item to the set or does nothing if already present.
  public void add(int item) { 
    if (!set.contains(item)) {
      set.add(item);
    }
  }

  // Removes an item from the set or does nothing if not there.
  public void remove(int item) { 
    set.remove(Integer.valueOf(item));
  }

  // Set union: modifies this to contain all unique elements in this or other.
  public void union(IntegerSet other) { }

  // Set intersection: modifies this to contain only elements in both sets.
  public void intersect(IntegerSet other) { }

  // Set difference (this \ other): modifies this to remove elements found in other.
  public void diff(IntegerSet other) { }

  // Set complement: modifies this to become (other \ this).
  public void complement(IntegerSet other) { }

  // Returns true if the set is empty, false otherwise.
  public boolean isEmpty() { 
    return set.isEmpty();
  }

  // Returns a String representation; overrides Object.toString().
  @Override
  public String toString() { 
    if (set.isEmpty()) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < set.size(); i++) {
      sb.append(set.get(i));
      if (i < set.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}

