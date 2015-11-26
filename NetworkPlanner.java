import java.util.Scanner;
import java.util.Random;
import java.util.Stack;

/*
 * Wraps double in a class for use in stack.
 */
class DoubleWrapper {
  private double data;
  /*
   * Construct a DoubleWrapper with initial value init.
   * @param init the initial value to which to set the data
   */
  public DoubleWrapper(double init) {
    this.data = init;
  }
  /*
   * Return the data.
   * @return the data
   */
  public double getDouble() {
    return this.data;
  }
  /*
   * Sets data to value new_data.
   * @param new_data the value to set data to
   */
  public void setDouble(double new_data) {
    this.data = new_data;
  }
}
/*
 * Stores a polynomial that can be evaluated.
 */
class Polynomial {
  private Stack<DoubleWrapper> coefficients;
  /* Constructs a polynomial from a string of numbers separated by spaces. The
   * numbers must be in order from least-degree coefficient to highest-degree
   * coefficient.
   * @param coefficient_string the initial string of coefficients
   */
  public Polynomial(String coefficient_string) {
    coefficient_string = coefficient_string.trim();
    String[] string_coefficients = coefficient_string.split(" ");
    this.coefficients = new Stack<DoubleWrapper>();
    for(int i = 1; i <= string_coefficients.length; i++) {
      coefficients.push(new DoubleWrapper(Double.parseDouble(string_coefficients[string_coefficients.length-i])));
    }
  }
  /*
   * Constructs a polynomial from a given stack of coefficients, the stack
   * having the highest-degree coefficient at the bottom.
   * @param coefficients stack of coefficients
   */
  public Polynomial(Stack<DoubleWrapper> coefficients) {
    this.coefficients = coefficients;
  }
  /*
   * Evaluates the polynomial for x.
   * @param x the input to the polynomial
   * @return the polynomial evaluated for x
   */
  public double substitute(double x) {
    double sum = 0;
    for(int i = 0; !this.coefficients.empty(); i++) {
      sum += this.coefficients.pop().getDouble()*(Math.pow(x,i));
    }
    return sum;
  }
}
/*
 *
/*
 * Uses a stochastic hillclimber to solve the problem of placing nodes inside
 * a shape such that the union of the neighborhoods of the nodes includes the
 * whole shape, minimized for the amount of nodes needed.
 */
class NetworkPlanner {
  private static double nodeRadius;
  private static double a;
  private static double b;
  private static Polynomial top;
  private static Polynomial bottom;
  public static void main(String[] args) {
    System.out.println("Welcome to NetworkPlanner! Please enter the coefficients of the polynomial that\ndescribes the curve of the top of your shape, in\nascending order of degree, separated by spaces.");
    Scanner in = new Scanner(System.in);
    String input = in.next();
    // This is the part where I validate input.
    String[] in_array = input.trim().split(" ");
    double dummy = 0;
    for(int i = 0; i < in_array.length; i++) {
      try {
        dummy = Double.parseDouble(in_array[i]);
      }
      catch(Exception e) {
        System.out.println(e);
      }
    }
    this.top = new Polynomial(input);
    System.out.println("Please enter the coefficients of the polynomial that describes the curve of the\nbottom of your shape.");
    input = in.next();
    // This is the part where I validate input.
    in_array = input.trim().split(" ");
    dummy = 0;
    for(int i = 0; i < in_array.length; i++) {
      try {
        dummy = Double.parseDouble(in_array[i]);
      }
      catch(Exception e) {
        System.out.println(e);
      }
    }
    bottom = new Polynomial(input);
    System.out.print("Please enter the beginning of the range of your curve. ");
    a = in.nextDouble();
    System.out.print("Please enter the end of the reange of your curve. ");
    b = in.nextDouble();
    System.out.print("Please enter the radius of the neighborhood of a node. ");
    nodeRadius = in.nextDouble();
  }
}
