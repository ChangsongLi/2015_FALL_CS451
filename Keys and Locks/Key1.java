/*
 * This class represents a key. A key can be of physical type or swipe card type.
 * It has a unique identifier.
 */

/**
 * @author Kasi Periyasamy
 * created on Sep 03, 2013
 */

@SuppressWarnings("rawtypes")
public class Key1 implements Comparable {
    private int id;
    private boolean type; // true indicates 'physical' and false indicates 'swipe card'.
    
    public Key1 (int param_id, boolean param_type) {
        id = param_id;
        type = param_type;
    }
    
    public int getID () {
        return id;
    }
    
    public boolean getType() {
        return type;
    }
        
    /**
     * This method compares the given object with the current one for equality.
     * @param param_key the key to be compared
     * @return true if the object has the same id
     */
   
    @Override
    public boolean equals (Object param_key) {
        return ((Key1) param_key).getID() == this.id;
    }
    
    /**
     * This method overrides the hashCode of the current object to the ID value.
     * In Java, it is necessary to override both hashCode() and equal() together.
     * @return the original hashCode of the object.
     */
    
//    @Override
//    public int hashCode () {
//        return this.hashCode();
//    }
    
    /**
     * This method override the compareTo method for Object.
     */
    
    @Override
    public int compareTo (Object obj) {
        if (obj.equals (this)) return 0;
        else return -1;
    }
}