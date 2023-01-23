package com.makotojava.learn.hellojunit5;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit 5 (with JUnitPlatform.class)
 * 
 * Class-level Exercises:
 * <ol>
 * <li>Enable Eclipse JUnit support for this test class using the {@link org.junit.runner.RunWith @RunWith} and
 * {@link org.junit.platform.runner.JUnitPlatform
 * JUnitPlatform} class.</li>
 * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
 * report.</li>
 * </ol>
 *
 */
public class JUnit5AppTest {

  // Create a JDK Logger here
  Logger logger = LoggerFactory.getLogger(getClass());

  // Create a fixture for the class under test
  App app;

  // Do something before ANY test is run in this class
  @BeforeAll
  public static void init() {
  }

  // Do something after ALL tests in this class are run
  @AfterAll
  public static void done() {
  }

  // Create an instance of the test class before each @Test method is executed
  @BeforeEach
  void beforeEach(){
    app = new App();
  }

  // Destroy reference to the instance of the test class after each @Test method is executed
  @AfterEach
  void afterEach(){
    app = null;
  }

  // Disabled test
  @Disabled
  void testNotRun() {
  }

  /**
   * testAdd() - Exercises:
   * <ol>
   * <li>Tell JUnit this method is a test method.</li>
   * <li>Give it a cool display name for the test report.</li>
   * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
   * <li>Create a group of three tests of the add methods with the following arrays of positive numbers:
   * <ol>
   * <li>1, 2, 3, 4</li>
   * <li>20, 934, 110</li>
   * <li>2, 4, 6</li>
   * </ol>
   * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers are
   * tested (i.e., if one fails, it should not fail the test method). Hint: use
   * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
   * </ol>
   */
  @Test
  @DisplayName("Test the Add function of positive longs in App.java")
  public void testAdd() {
    Assertions.assertNotNull(app);

    long[] arr1 = new long[]{1, 2, 3, 4};
    long[] arr2 = new long[]{20, 934, 110};
    long[] arr3 = new long[]{2, 4, 6};

    logger.info("Constructed arrays of positive longs");
    
    Assertions.assertAll(
      () -> Assertions.assertEquals(10, app.add(arr1), "The sum of 1, 2, 3, and 4 should be 10"),
      () -> Assertions.assertEquals(1064, app.add(arr2), "The sum of 20, 934, and 110 should be 1064"),
      () -> Assertions.assertEquals(12, app.add(arr3), "The sum of 2, 4, and 6 should be 12")
    );

    logger.info("All assertions passed for adding positive long arrays");
  }

  /**
   * Class-level Exercises:
   * <ol>
   * <li>Make this class a nested test class (hint: use {@link org.junit.jupiter.api.Nested @Nested}).
   * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
   * report.</li>
   * <li>Create an instance of the {@link com.makotojava.learn.hellojunit5.App App} class specifically for this nested
   * class:
   * <ul>
   * <li>Set the <code>classUnderTest</code> variable in a method called <code>setUp()</code> that runs before the test
   * method does (hint: use {@link org.junit.jupiter.api.BeforeEach @BeforeEach})</li>
   * </ul>
   * <li>Set the <code>classUnderTest</code> variable to null in a method called <code>tearDown()</code> that runs after
   * the
   * test method does (hint: use {@link org.junit.jupiter.api.BeforeEach @AfterEach})</li>
   * </ol>
   * 
   */
  @Nested
  @DisplayName("Test the proper summing of negative longs in App.add()")
  class NegativeNumbersTest {

    App app;

    @BeforeEach
    void setup(){
      app = new App();
    }

    @AfterEach
    void tearDown(){
      app = null;
    }

    /**
     * testAdd() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Create a group of three tests of the add methods with the following arrays of positive numbers:
     * <ol>
     * <li>-1, -2, -3, -4</li>
     * <li>-20, -934, -110</li>
     * <li>-2, -4, -6</li>
     * </ol>
     * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers
     * are
     * tested (i.e., if one fails, it should not fail the test method). Hint: use
     * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
     * </ol>
     */
    @Test
    @DisplayName("Test summing of long arrays including negative integers")
    public void testAdd() {
      Assertions.assertNotNull(app);

      long[] arr1 = new long[]{1, -2, -3, 4};
      long[] arr2 = new long[]{20, -934, -110};
      long[] arr3 = new long[]{-2, -4, -6};

      logger.info("Constructed arrays of mixed positive and negative longs");
      
      Assertions.assertAll(
        () -> Assertions.assertEquals(0, app.add(arr1), "The sum of 1, -2, -3, and 4 should be 0"),
        () -> Assertions.assertEquals(-1024, app.add(arr2), "The sum of 20, -934, and -110 should be -1024"),
        () -> Assertions.assertEquals(-12, app.add(arr3), "The sum of -2, -4, and -6 should be -12")
      );

      logger.info("All assertions passed for adding positive and negative long arrays");
    }
  }

  /**
   * Class-level Exercises:
   * <ol>
   * <li>Make this class a nested test class (hint: use {@link org.junit.jupiter.api.Nested @Nested}).
   * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
   * report.</li>
   * </ol>
   * 
   */
  class PositiveAndNegativeNumbersTest {

    /**
     * testAdd() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Create a group of three tests of the add methods with the following arrays of positive numbers:
     * <ol>
     * <li>-1, 2, -3, 4</li>
     * <li>-20, 934, -110</li>
     * <li>-2, -4, 6</li>
     * </ol>
     * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers
     * are
     * tested (i.e., if one fails, it should not fail the test method). Hint: use
     * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
     * </ol>
     */
    public void testAdd() {
      //
      // EXERCISE: TODO: ADD CODE HERE (See Javadoc comments for instructions. Use the Javadoc View in Eclipse to see
      // the buttery smooth javadoc above.)
      //
      fail("Test not implemented!");
    }

    /**
     * testAdd_OnlyOnFriday - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>Use the JDK 8 Date/Time API to get the current local date/time, along with a simple Assumption (hint: use
     * {@link org.junit.jupiter.api.Assumptions#assumeTrue(boolean) assumeTrue()}) that today is Friday
     * (or any other day of the week of your choosing, just so you pick one), or the test is skipped.</li>
     * <li>Pass the following numbers as operands to the {@link com.makotojava.learn.hellojunit5.App#add(long[]) add}
     * method: 1, 2, 3, 4, 5</li>
     * <li>Ensure the actual sum matches the expected sum.</li>
     * </ol>
     */
    public void testAdd_OnlyOnFriday() {
      //
      // EXERCISE: TODO: ADD CODE HERE (See Javadoc comments for instructions. Use the Javadoc View in Eclipse to see
      // the buttery smooth javadoc above.)
      //
      fail("Test not implemented!");
    }

    /**
     * testAdd_OnlyOnFriday_WithLambda - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>Use the JDK 8 Date/Time API to get the current local date/time, along with a simple Assumption (hint: use
     * {@link org.junit.jupiter.api.Assumptions#assumingThat(boolean, org.junit.jupiter.api.function.Executable)
     * assumingThat()}) that today is Friday
     * (or any other day of the week of your choosing, just so you pick one), or the test is skipped.</li>
     * <li>Pass the following numbers as operands to the {@link com.makotojava.learn.hellojunit5.App#add(long[]) add}
     * method: 1, 2, 3, 4, 5</li>
     * <li>Ensure the actual sum matches the expected sum.</li>
     * </ol>
     */
    public void testAdd_OnlyOnFriday_WithLambda() {
      //
      // EXERCISE: TODO: ADD CODE HERE (See Javadoc comments for instructions. Use the Javadoc View in Eclipse to see
      // the buttery smooth javadoc above.)
      //
      fail("Test not implemented!");
    }

  }

  /**
   * Class-level Exercises:
   * <ol>
   * <li>Make this class a nested test class (hint: use {@link org.junit.jupiter.api.Nested @Nested}).
   * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
   * report.</li>
   * </ol>
   * 
   */
  class JUnit5AppSingleOperandTest {

    /**
     * testAdd_NumbersGt0() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Create a group of two tests of the add methods with the following arrays of positive numbers:
     * <ol>
     * <li>1</li>
     * <li>0</li>
     * </ol>
     * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers
     * are
     * tested (i.e., if one fails, it should not fail the test method). Hint: use
     * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
     * </ol>
     */
    public void testAdd_NumbersGt0() {
      //
      // EXERCISE: TODO: ADD CODE HERE (See Javadoc comments for instructions. Use the Javadoc View in Eclipse to see
      // the buttery smooth javadoc above.)
      //
      fail("Test not implemented!");
    }

    /**
     * testAdd_NumbersLt0() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Create a group of two tests of the add methods with the following arrays of positive numbers:
     * <ol>
     * <li>-1</li>
     * <li>-10</li>
     * </ol>
     * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers
     * are
     * tested (i.e., if one fails, it should not fail the test method). Hint: use
     * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
     * </ol>
     */
    public void testAdd_NumbersLt0() {
      //
      // EXERCISE: TODO: ADD CODE HERE (See Javadoc comments for instructions. Use the Javadoc View in Eclipse to see
      // the buttery smooth javadoc above.)
      //
      fail("Test not implemented!");
    }
  }

  /**
   * Class-level Exercises:
   * <ol>
   * <li>Make this class a nested test class (hint: use {@link org.junit.jupiter.api.Nested @Nested}).
   * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
   * report.</li>
   * </ol>
   * 
   */
  class JUnit5AppZeroOperandsTest {

    /**
     * testAdd_ZeroOperands_EmptyArgument() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Pass an empty array as operands argument to the {@link com.makotojava.learn.hellojunit5.App#add(long[]) add}
     * method.</li>
     * <li>Ensure that an {@link java.lang.IllegalArgumentException IllegalArgumentException} is thrown (hint: use the
     * {@link org.junit.jupiter.api.Assertions#assertThrows(Class, org.junit.jupiter.api.function.Executable)
     * assertThrows()} method).</li>
     * </ol>
     */
    public void testAdd_ZeroOperands_EmptyArgument() {
      //
      // EXERCISE: TODO: ADD CODE HERE (See Javadoc comments for instructions. Use the Javadoc View in Eclipse to see
      // the buttery smooth javadoc above.)
      //
      fail("Test not implemented!");
    }

    /**
     * testAdd_ZeroOperands_NullArgument() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Pass an empty array as operands argument to the {@link com.makotojava.learn.hellojunit5.App#add(long[]) add}
     * method.</li>
     * <li>Ensure that an {@link java.lang.IllegalArgumentException IllegalArgumentException} is thrown (hint: use the
     * {@link org.junit.jupiter.api.Assertions#assertThrows(Class, org.junit.jupiter.api.function.Executable)
     * assertThrows()} method).</li>
     * <li>The test should fail if the message in the exception is not "Operands argument cannot be null".</li>
     * </ol>
     */
    public void testAdd_ZeroOperands_NullArgument() {
      //
      // EXERCISE: TODO: ADD CODE HERE (See Javadoc comments for instructions. Use the Javadoc View in Eclipse to see
      // the buttery smooth javadoc above.)
      //
      fail("Test not implemented!");
    }

  }

}
