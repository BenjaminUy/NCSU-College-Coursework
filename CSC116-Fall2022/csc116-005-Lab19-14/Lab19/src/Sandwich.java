/**
 * Class that deals with final sandwich made
 * @author Yamini Ramadurai
 * @author Benjamin Uy
 */
public class Sandwich {
    
    /** String value of name of sandwich used throughout class */
    private String name;

    /** Bread object for type of bread */
    private Bread bread;

    /** SandwichFilling object for type of filling */
    private SandwichFilling filling;

    /**
     * Constructor for Sandwich class
     * @param name String value of name of Sandwich
     * @param bread Bread value of bread type for Sandwich
     * @param filling Filling value of sandwichfilling type for Sandwich
     */
    public Sandwich(String name, Bread bread, SandwichFilling filling) {
        this.name = name;
        this.bread = bread;
        this.filling = filling;
    }

    /**
     * Deals with the name of the Sandwich object
     * @return String of the name of the Sandwich object
     */
    public String getName() {
        return name;
    }

    /**
     * Method returns the total calories in 1 serving of SandwichFilling and 2 slices of Bread
     * @return integer representing the total number of calories
     */
    public int getCalories() {
        return (filling.getCalories() + (2 * bread.getCalories()));
    }

    /**
     * Method returns a string about a Sandwich object's calories, filling, and bread
     * @return String in the format: Total Calories (X): fillingType on breadType.
     */
    public String toString() {
        return ("Total Calories (" + getCalories() + "): " + filling + " on " + bread);
    }

    /**
     * Method determines if two Sandwich objects are the same
     * @param o Object for Sandwich
     * @return boolean for whether both Sandwich objects are equal--having the same
     * SandwichFilling and Bread 
     */
    public boolean equals(Object o) {
        if (o instanceof Sandwich) {
            Sandwich r = (Sandwich) o;
            return ("Total Calories (" + r.getCalories() + "): " + r.filling + " on " 
                + r.bread).equals(toString());
        } else {
            return false;
        }
    }
}
