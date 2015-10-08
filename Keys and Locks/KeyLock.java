/*
 * This class represents the combination of a key and a lock. It only maintains 
 * the IDs of the key and the lock.
 */

/**
 * @author Kasi Periyasamy
 * created on Sep 04, 2013
 */
@SuppressWarnings("rawtypes")
public class KeyLock implements Comparable {
    private int keyID;
    private int lockID;
    
    public KeyLock (int param_keyID, int param_lockID) {
        keyID = param_keyID;
        lockID = param_lockID;
    }
    
    public int getKeyID () {
        return keyID;
    }
    
    public int getLockID () {
        return lockID;
    }
    
    /**
     * This method compares the given object with the current one for equality.
     * @param param_key the key to be compared
     * @return true if the object has the same id
     */
   
    @Override
    public boolean equals (Object param_keylock) {
        return ((KeyLock) param_keylock).getKeyID() == this.keyID &&
               ((KeyLock) param_keylock).getLockID() == this.lockID;
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
