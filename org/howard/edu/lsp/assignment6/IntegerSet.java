package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that represents a mathematical set of integers.
 * Sets cannot contain duplicate values and support standard set operations
 * such as union, intersection, difference, and complement.
 * 
 * @author Assignment 6
 */
public class IntegerSet  {
  private List<Integer> set = new ArrayList<Integer>();

  /**
   * Clears the internal representation of the set, removing all elements.
   */
  public void clear() { 
    set.clear();
  }

  /**
   * Returns the number of elements in the set.
   * 
   * @return the number of elements in the set
   */
  public int length() { 
    return set.size();
  }

  /**
   * Returns true if the two sets are equal, false otherwise.
   * Two sets are equal if they contain all of the same values in ANY order.
   * This overrides the equals method from the Object class.
   * 
   * @param o the object to compare with this set
   * @return true if the sets are equal (contain the same elements), false otherwise
   */
  @Override
  public boolean equals(Object o) { 
    if (this == o) {
      return true;
    }
    if (o == null || !(o instanceof IntegerSet)) {
      return false;
    }
    IntegerSet other = (IntegerSet) o;
    if (this.set.size() != other.set.size()) {
      return false;
    }
    return this.set.containsAll(other.set) && other.set.containsAll(this.set);
  }

  /**
   * Returns true if the set contains the specified value, otherwise false.
   * 
   * @param value the integer value to check for membership
   * @return true if the value is in the set, false otherwise
   */
  public boolean contains(int value) { 
    return set.contains(value);
  }

  /**
   * Returns the largest item in the set.
   * 
   * @return the largest integer value in the set
   * @throws IllegalStateException if the set is empty
   */
  public int largest()  { 
    if (set.isEmpty()) {
      throw new IllegalStateException("Set is empty");
    }
    return Collections.max(set);
  }

  /**
   * Returns the smallest item in the set.
   * 
   * @return the smallest integer value in the set
   * @throws IllegalStateException if the set is empty
   */
  public int smallest()  { 
    if (set.isEmpty()) {
      throw new IllegalStateException("Set is empty");
    }
    return Collections.min(set);
  }

  /**
   * Adds an item to the set if it is not already present.
   * If the item already exists in the set, this method does nothing.
   * 
   * @param item the integer value to add to the set
   */
  public void add(int item) { 
    if (!set.contains(item)) {
      set.add(item);
    }
  }

  /**
   * Removes an item from the set if it is present.
   * If the item is not in the set, this method does nothing.
   * 
   * @param item the integer value to remove from the set
   */
  public void remove(int item) { 
    set.remove(Integer.valueOf(item));
  }

  /**
   * Performs set union operation: modifies this set to contain all unique elements
   * that are in this set or in the other set (or both).
   * 
   * @param other the other IntegerSet to union with this set
   */
  public void union(IntegerSet other) { 
    for (Integer item : other.set) {
      this.add(item);
    }
  }

  /**
   * Performs set intersection operation: modifies this set to contain only
   * elements that are present in both this set and the other set.
   * 
   * @param other the other IntegerSet to intersect with this set
   */
  public void intersect(IntegerSet other) { 
    set.retainAll(other.set);
  }

  /**
   * Performs set difference operation (this \ other): modifies this set to remove
   * all elements that are found in the other set.
   * 
   * @param other the other IntegerSet to subtract from this set
   */
  public void diff(IntegerSet other) { 
    set.removeAll(other.set);
  }

  /**
   * Performs set complement operation: modifies this set to become (other \ this),
   * containing all elements that are in the other set but not in this set.
   * 
   * @param other the other IntegerSet to use for the complement operation
   */
  public void complement(IntegerSet other) { 
    List<Integer> temp = new ArrayList<Integer>(other.set);
    temp.removeAll(this.set);
    this.set.clear();
    this.set.addAll(temp);
  }

  /**
   * Returns true if the set is empty (contains no elements), false otherwise.
   * 
   * @return true if the set is empty, false otherwise
   */
  public boolean isEmpty() { 
    return set.isEmpty();
  }

  /**
   * Returns a String representation of the set in the format [a, b, c].
   * For an empty set, returns [].
   * This overrides the toString method from the Object class.
   * 
   * @return a string representation of the set in square brackets
   */
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

