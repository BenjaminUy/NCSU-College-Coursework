import java.util.*;

/**
 * Class that displys fraction with integer numerator and denominator
 * @author Yamini Ramadurai
 * @author Benjamin Uy
 */
public class RationalNumber {

    /** Creates the denominator of the rational number as int throughout class  */
    private int denominator;
    
    /** Creates the numerator of the rational number as int throughout the class */
    private int numerator;

    /**
     * Constructs a new rational number to represent the ratio 0/1
     */
    public RationalNumber() {
        this(0, 1);
    }

    /**
     * Constructs new rational number to represent the ratio (numerator/denominator)
     * @param numerator int numerator of rational number
     * @param denominator int denominator of rational number
     * @throws IllegalArgumentException if the denominator is 0
     */
    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator is 0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    /**
     * Eliminates any common factor and ensures that denominator is greater than 0
     */
    private void reduce() {
        if (denominator < 0) {
            denominator *= -1;
            numerator *= -1;
        }
        int a = numerator;
        int b = denominator;
        while (b != 0) {
            int tempA = a;
            a = b;
            b = tempA % b;
        }
        if (a < 0) {
            a *= -1;
        }
        denominator /= a;
        numerator /= a;
    }

    /**
     * Gets rational number's numerator value
     * @return Returns rational number's numerator value
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Gets rational number's denominator value
     * @return Returns rational number's denominator value
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Represents rational number as a String
     * @return Returns String representation of this rational number
     */
    public String toString() {
        if (denominator == 1) {
            return ("" + numerator);
        } else {
            return (numerator + "/" + denominator);
        }
    }

    /**
     * Determines if this and other are equal
     * @param other Object as the other rational number
     * @return Returns true if this and other are equivalent rational numbers; false otherwise
     */
    public boolean equals(Object other) {
        if (other instanceof RationalNumber) {
            RationalNumber r = (RationalNumber) other;
            return (numerator == r.getNumerator() 
                && denominator == r.getDenominator());
        } else {
            return false;
        }
    }

    /**
     * Adds this and other together
     * @param other RationalNumber taking in other rational number
     * @return Returns the sum as a rational number
     */
    public RationalNumber add(RationalNumber other) {
        RationalNumber r = (RationalNumber) other;
        int newNum = r.getNumerator();
        int newDenominator = r.getDenominator();
        int otherNum = getNumerator();
        int otherDenom = getDenominator();
        /* If denominators are not the same, then multiply */
        if (newDenominator != otherDenom) {
            int tempNewDenom = newDenominator;
            newDenominator *= otherDenom;
            otherDenom *= tempNewDenom;
            newNum *= otherDenom;
            otherNum *= tempNewDenom;
        }
        /* At this point, denominators are the same */
        r = new RationalNumber(newNum + otherNum, newDenominator);
        return r;
    }

    /**
     * Subtracts this and other
     * @param other RationalNumber taking in other rational number
     * @return Returns the difference as a rational number
     */
    public RationalNumber subtract(RationalNumber other) {
        RationalNumber r = (RationalNumber) other;
        int newNum = r.getNumerator();
        int newDenominator = r.getDenominator();
        int otherNum = getNumerator();
        int otherDenom = getDenominator();
        /* If denominators are not the same, then multiply */
        if (newDenominator != otherDenom) {
            int tempNewDenom = newDenominator;
            newDenominator *= otherDenom;
            otherDenom *= tempNewDenom;
            newNum *= otherDenom;
            otherNum *= tempNewDenom;
        }
        /* At this point, denominators are the same */
        r = new RationalNumber(newNum - otherNum, newDenominator);
        return r;
    }

    /**
     * Multiplies this and other
     * @param other RationalNumber taking in other rational number
     * @return Returns the product as a rational number
     */
    public RationalNumber multiply(RationalNumber other) {
        RationalNumber r = (RationalNumber) other;
        int numeratorA = r.getNumerator();
        int denominatorA = r.getDenominator();
        int otherNum = getNumerator();
        int otherDenom = getDenominator();
        r = new RationalNumber(numeratorA * otherNum, denominatorA * otherDenom);
        return r;
    }

    /**
     * Divides this and other
     * @param other RationalNumber taking in other rational number
     * @return Returns the quotient as a rational number
     */
    public RationalNumber divide(RationalNumber other) {
        RationalNumber r = (RationalNumber) other;
        int newNum = r.getNumerator();
        int newDenominator = r.getDenominator();
        int otherNum = getNumerator();
        int otherDenom = getDenominator();
        /* If denominators are not the same, then multiply */
        if (newDenominator != otherDenom) {
            int tempNewDenom = newDenominator;
            newDenominator *= otherDenom;
            otherDenom *= tempNewDenom;
            newNum *= otherDenom;
            otherNum *= tempNewDenom;
        }
        /* At this point, otherDenom should equal newDenominator */
        if (newNum % otherNum == 0) {
            r = new RationalNumber(newNum / otherNum, 1);
        } else {
            r = new RationalNumber(newNum, otherNum);
        }
        return r;
    }
}