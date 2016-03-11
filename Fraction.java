/*
Program: Fraction Calculator, Fraction.java
Created for assignment in Object Oriented Programming course, Fall 2015
Contributor(s): Scott Williams
Created on: 9-27-15
Last Modified On: 3-11-16
*/
import java.lang.ArithmeticException;
import java.util.Scanner;

public class Fraction {
	
	//numerator and denominator
	private int num, den;
	
	/**
	 * Constructor takes in two numbers and puts them in fraction form.
	 * @param numerator the top of our new fraction
	 * @param denominator the bottom of our new fraction
	 */
	public Fraction(int numerator, int denominator){
		num = numerator;
		den = denominator;
		if(den == 0) throw new ArithmeticException("Denominator is 0.");
	}
	
	/**
	 * Second constructor: creates a fraction using an already existing fraction.
	 * @param a fraction argument
	 */
	public Fraction(Fraction a) {
		num = a.num;
		den = a.den;
	}
	
	/**
	 * Method uses the instance variables of an individual fraction and reduces it.
	 */
	public void reduceFraction() {
		int tnum = num;
		int tden = den;
		while (num != 0 && den != 0) {
			int c = den;
			den = num % den;
			num = c;
		}
		int gcd = num + den;
		num = tnum/gcd;
		den = tden/gcd;
	}
	
	/**
	 * Method adds two fractions together.
	 * @param f1 first fraction
	 * @param f2 second fraction
	 * @return this new fraction
	 */
	public Fraction add(Fraction f1, Fraction f2) {
		int numerator = ((f1.num * f2.den) + (f1.den * f2.num));
		int denominator = (f1.den * f2.den);
		Fraction ans = new Fraction(numerator, denominator);
		ans.reduceFraction();
		return ans;		
	}

	/**
	 * Method subtracts the first fraction from the second one.
	 * @param f1 first fraction
	 * @param f2 second fraction
	 * @return this new fraction
	 */
	public Fraction subtract(Fraction f1, Fraction f2) {
		int numerator = ((f1.num * f2.den) - (f1.den * f2.num));
		int denominator = (f1.den * f2.den);
		Fraction ans = new Fraction(numerator, denominator);
		ans.reduceFraction();
		return ans;		
	}

	/**
	 * Method multiplies two fractions together.
	 * @param f1 first fraction
	 * @param f2 second fraction
	 * @return this new fraction
	 */
	public Fraction multiply(Fraction f1, Fraction f2) {
		int numerator = (f1.num * f2.num);
		int denominator = (f1.den * f2.den);
		Fraction ans = new Fraction(numerator, denominator);
		ans.reduceFraction();
		return ans;		
	}
	
	/**
	 * Method divides the first fraction by the second one.
	 * @param f1 first fraction
	 * @param f2 second fraction
	 * @return this new fraction
	 */
	public Fraction divide(Fraction f1, Fraction f2) {
		if((f2.num / f2.den) == 0) throw new ArithmeticException("Denominator is 0.");
		int numerator = (f1.num * f2.den);
		int denominator = (f1.den * f2.num);
		Fraction ans = new Fraction(numerator, denominator);
		ans.reduceFraction();
		return ans;		
	}
	
	/**
	 * Method overrides toString method to output a fraction with proper syntax.
	 * @return this fraction's formatted output
	 */
	@Override
    public String toString() {
        return String.format(num + "/" + den);
    }
	
	/**
	 * This method tests to see if this current instance object is equal to
	 * the parameter instance. IMPORTANT: Must have already called reduceFraction
	 * on each object before testing them for equivalence. 
	 * Method was intended to be an "@Override", but Eclipse told me that I
	 * was not allowed to override it, and it works.
	 * @param f Fraction to test against this fraction instance.
	 * @return boolean, false if numbers do not equal each other.
	 */
    public boolean equals(Fraction f) {
    	
		if (num == f.num && den == f.den) return true;
		else return false;
	}
	
    /**
     * Accessor for num instance field
     * @return this instance of num
     */
	public int getNum() { return num; }
	
    /**
     * Accessor for den instance field
     * @return this instance of den
     */
	public int getDen() { return den; }

	//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1, den1, num2, den2;
		char operator;
		String answer;
		Scanner in = new Scanner(System.in);
		
		do {
			System.out.print("Enter your first fraction with space in between the numbers: ");
			num1 = in.nextInt();
			den1 = in.nextInt();
			Fraction f1 = new Fraction(num1, den1);
			
			System.out.print("Enter your second fraction with space in between the numbers: ");
			num2 = in.nextInt();
			den2 = in.nextInt();
			Fraction f2 = new Fraction(num2, den2);
			
			
			System.out.print("Would you like to add, subtract, multiply, divide or check equivalance?(+-*/=): ");
			operator = in.next().charAt(0);
			
			switch(operator) {
			case '+': 
				Fraction addans = new Fraction(f2.add(f1, f2));
				System.out.print("After addition you get: " + addans);
				break;
			case '-':
				Fraction subans = new Fraction(f2.subtract(f1, f2));
				System.out.print("After subtraction you get: " + subans);
				break;
			case '*':
				Fraction multans = new Fraction(f2.multiply(f1, f2));
				System.out.print("After multiplication you get: " + multans);
				break;
			case '/':
				Fraction divans = new Fraction(f2.divide(f1, f2));
				System.out.print("After division you get: " + divans);
				break;
			case '=': 
				f1.reduceFraction();
				f2.reduceFraction();
				if(f1.equals(f2)) System.out.print("The two fractions are equal.");
				else System.out.print("The two fractions are NOT equal.");
				break;
			default:
				System.out.print("You have entered an incorrect operator.");
			}
			System.out.print("\nWould you like to continue?(y/n): ");
			answer = in.next();
		} while(answer.equalsIgnoreCase("y"));		

		in.close();
	}

}
