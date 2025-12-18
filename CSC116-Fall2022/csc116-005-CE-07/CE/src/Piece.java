/**
 * Class for Piece object, represents a single piece (X or O) 
 * 
 * @author Noah Evans Lehner
 * @author Benjamin Uy
 * @author Yamini Ramadurai
 * @author Aman Anas
 */
public class Piece {
    
    /** A char that represents the type */
    private char type;

    /**
     * Constructor method for Piece
     * 
     * @param type type as a char
     * @throws IllegalArgumentException if the type is a null character,
     *                                  Message: Type is a null character
     */
    public Piece(char type) {
        // Checks if the type is a null character
        if (type == '\0') {
            throw new IllegalArgumentException("Type is a null character");
        }
        
        this.type = type;
    }

    /**
     * Method to return the type
     * 
     * @return the type as a char
     */
    public char getType() {
        return type;
    }

    /**
     * Method determines whether this Piece and o are equivalent (equal type) 
     * 
     * @param o other object
     * @return whether this Piece and o are equivalent
     */
    public boolean equals(Object o) {
        if (o instanceof Piece) {
            Piece n = (Piece) o;
            return (n.getType() == type);
        } else {
            return false;
        }
    }

    /**
     * Method returns type as a string
     *
     * @return type as a String
     */
    public String toString() {
        return "" + type;
    } 
}