package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerSetTest {
  
  private IntegerSet set1;
  private IntegerSet set2;
  
  @BeforeEach
  public void setUp() {
    set1 = new IntegerSet();
    set2 = new IntegerSet();
  }
  
  // Test length()
  @Test
  public void testLength() {
    assertEquals(0, set1.length());
    set1.add(1);
    assertEquals(1, set1.length());
    set1.add(2);
    assertEquals(2, set1.length());
    set1.add(1); // duplicate
    assertEquals(2, set1.length()); // should not increase
    set1.remove(1);
    assertEquals(1, set1.length());
  }
  
  // Test isEmpty()
  @Test
  public void testIsEmpty() {
    assertTrue(set1.isEmpty());
    set1.add(1);
    assertFalse(set1.isEmpty());
    set1.clear();
    assertTrue(set1.isEmpty());
  }
  
  // Test contains()
  @Test
  public void testContains() {
    assertFalse(set1.contains(1));
    set1.add(1);
    assertTrue(set1.contains(1));
    assertFalse(set1.contains(2));
    set1.add(2);
    assertTrue(set1.contains(2));
    set1.remove(1);
    assertFalse(set1.contains(1));
  }
  
  // Test add()
  @Test
  public void testAdd() {
    assertEquals(0, set1.length());
    set1.add(1);
    assertEquals(1, set1.length());
    assertTrue(set1.contains(1));
    set1.add(1); // adding duplicate
    assertEquals(1, set1.length()); // should not increase
    set1.add(2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(2));
  }
  
  // Test remove()
  @Test
  public void testRemove() {
    set1.add(1);
    set1.add(2);
    assertEquals(2, set1.length());
    set1.remove(1);
    assertEquals(1, set1.length());
    assertFalse(set1.contains(1));
    assertTrue(set1.contains(2));
    set1.remove(3); // removing non-existent item
    assertEquals(1, set1.length()); // should not change
  }
  
  // Test clear()
  @Test
  public void testClear() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    assertEquals(3, set1.length());
    set1.clear();
    assertEquals(0, set1.length());
    assertTrue(set1.isEmpty());
    assertFalse(set1.contains(1));
  }
  
  // Test toString()
  @Test
  public void testToString() {
    assertEquals("[]", set1.toString());
    set1.add(1);
    assertEquals("[1]", set1.toString());
    set1.add(2);
    assertEquals("[1, 2]", set1.toString());
    set1.add(3);
    assertEquals("[1, 2, 3]", set1.toString());
    set1.clear();
    assertEquals("[]", set1.toString());
  }
  
  // Test largest()
  @Test
  public void testLargest() {
    // Test exception on empty set
    assertThrows(IllegalStateException.class, () -> set1.largest());
    
    set1.add(3);
    assertEquals(3, set1.largest());
    set1.add(1);
    assertEquals(3, set1.largest());
    set1.add(5);
    assertEquals(5, set1.largest());
    set1.add(2);
    assertEquals(5, set1.largest());
  }
  
  // Test smallest()
  @Test
  public void testSmallest() {
    // Test exception on empty set
    assertThrows(IllegalStateException.class, () -> set1.smallest());
    
    set1.add(3);
    assertEquals(3, set1.smallest());
    set1.add(1);
    assertEquals(1, set1.smallest());
    set1.add(5);
    assertEquals(1, set1.smallest());
    set1.add(2);
    assertEquals(1, set1.smallest());
  }
  
  // Test equals()
  @Test
  public void testEquals() {
    // Equal empty sets
    assertTrue(set1.equals(set2));
    
    // Same reference
    assertTrue(set1.equals(set1));
    
    // Null check
    assertFalse(set1.equals(null));
    
    // Non-IntegerSet object
    assertFalse(set1.equals("not a set"));
    
    // Sets with same elements in different order
    set1.add(1);
    set1.add(2);
    set1.add(3);
    
    set2.add(3);
    set2.add(1);
    set2.add(2);
    
    assertTrue(set1.equals(set2));
    
    // Sets with different elements
    set2.add(4);
    assertFalse(set1.equals(set2));
    
    // Sets with same size but different elements
    set1.clear();
    set2.clear();
    set1.add(1);
    set1.add(2);
    set2.add(2);
    set2.add(3);
    assertFalse(set1.equals(set2));
    
    // Empty vs non-empty
    set1.clear();
    set2.clear();
    set1.add(1);
    assertFalse(set1.equals(set2));
  }
  
}

