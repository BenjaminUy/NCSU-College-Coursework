/**
 * Class that represents a single symbol used in the Whack A Mole game
 * 
 * @author Benjamin Uy
 */
public class Symbol {
    
    /** Creates the string name of the symbol */
    private String name;

    /** Creates the integer for the number of points */
    private int points;

    /** Creates the boolean for whether or not the symbol has been clicked by the player */
    private boolean hasBeenClickedOn;

    /**
     * Constructor method for Symbol class 
     * @param name string representing name
     * @param points integer representing the number of points
     * @throws IllegalArgumentException is name is null
     * @throws IllegalArgumentException if points is less than 1
     */
    public Symbol(String name, int points) {
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        } else if (points < 1) {
            throw new IllegalArgumentException("Invalid points");
        }
        this.name = name;
        this.points = points;
        this.setHasBeenClickedOn(false);
    }

    /**
     * Method gets the name of the symbol
     * @return instance field that knows name of the symbol
     */
    public String getName(){
        return name;
    }

    /**
     * Method gets the number of points for the symbol
     * @return instance field that knows points for the symbol
     */
    public int getPoints(){
        return points;
    }

    /**
     * Method gets the boolean for whether the symbol has been clicked on
     * @return instance field that knows whether symbol has been clicked on
     */
    public boolean hasBeenClickedOn() {
        return hasBeenClickedOn;
    }

    /**
     * Method sets boolean for hasBeenClickedOn
     * @param hasBeenClickedOn boolean for whether symbol has been clicked on
     */
    public void setHasBeenClickedOn(boolean hasBeenClickedOn){
        this.hasBeenClickedOn = hasBeenClickedOn;
    }

    /**
     * Method returns whether this Symbol and object o are equal
     * @param o object that is being compared to the Symbol
     * @return boolean for whether o is equal to this Symbol
     */
    public boolean equals(Object o){
        if (o instanceof Symbol) {
            Symbol r = (Symbol) o;
            return name.equals(r.getName()) && points == r.getPoints() && 
                hasBeenClickedOn == r.hasBeenClickedOn();
        }
        else {
            return false;
        }
    }

    /**
     * Method gets string containing symbol name, points of the symbol, and boolean for
     * whether the symbol has been clicked
     * @return string with name followed number of points whether symbol has been clicked on
     */
    public String toString(){
        return (getName() + " " + getPoints() + " " + hasBeenClickedOn());
    }
}
