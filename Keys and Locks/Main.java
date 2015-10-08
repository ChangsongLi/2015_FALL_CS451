/*
 * This is the main class that maintains the keys, locks and their relationships.
 * Key IDs start from 1 and auto-generated every time a new key is issued.
 * Lock IDs start from 1001 and auto-generated every time a new lock is created.
 * The test data for the problem is hardcoded in this class. There are only two
 * buildings used in the test data - CENTENNIAL and WING TECH CENTER.
 * The room numbers in WING TECH CENTER are from 101 to 104 both inclusive,
 * and the room numbers in CENTENNTIAL are from 101 to 103 both inclusive and
 * from 201 to 203 both inclusive.
 */

/**
 * @author Kasi Periyasamy
 * created on Sep 04, 2013
 */

import java.util.HashSet;
import java.util.Iterator;

public class Main {
    private HashSet<Key1> keys;
    private HashSet<Lock1> locks;
    private HashSet<KeyLock> combos;
    private int lockCounter = 1001; // all lock IDs >= 1001
    private int keyCounter = 1; // all key IDs are between 1 and 1000.
    
    public Main () {
        keys = new HashSet<Key1>();
        locks = new HashSet<Lock1>();
        combos = new HashSet<KeyLock>();
        loadInitialData();
    }
    
    /**
     * This method adds a new lock to the system. It requires a new key as well.
     * The method ensures that the building and room number are both passed as
     * parameters and are within the range of test values. 
     * The method adds a new physical key. It is assumed that every lock will have
     * a physical key to start with.
     * @param room the room number in which the lock is placed.
     * @param bldg the name of the building in which the room is located.
     * @throws java.lang.Exception when building name is incorrect or the room
     *  is not found.
     */
    public void addNewLock (int room, String bldg) throws Exception { 
        if (!(bldg.trim().equalsIgnoreCase("WING TECH CENTER") ||
            bldg.trim().equalsIgnoreCase("CENTENNIAL")))
            throw new Exception (" Invalid Building name");
        if (bldg.trim().equalsIgnoreCase("WING TECH CENTER")) {
            if (room < 101 || room > 104)
                throw new Exception (" Invalid room number for this building");
        }
        if (bldg.trim().equalsIgnoreCase("CENTENNIAL")) {
            if (room < 101 || (room > 103 && room < 201) || room > 203)
                throw new Exception (" Invalid room number for this building");
        }
        // save the current lock ID and key ID to add them into the combo
        int currentLockID = lockCounter++;
        int currentKeyID = keyCounter++;
        locks.add (new Lock1 (currentLockID, room, bldg ));
        keys.add (new Key1 (currentKeyID, true)); // adds a new physical key
        combos.add (new KeyLock (currentKeyID, currentLockID));
    }
    
    /**
     * This method adds a new key to the system and maps it to an existing lock.
     * @param lockID the ID number of the lock to which the new key is mapped.
     * @param keyType the type of the new key - true for physical and false for swipe card.
     * @throws java.lang.Exception when the lock is not found
     */
    public void addNewKey (int lockID, boolean keyType) throws Exception {
        Lock1 temp;
        int currentKeyID = keyCounter++;
        // search for the specified lock
        temp = searchLock (lockID);
        if (temp == null)
            throw new Exception (" There is no lock with the given ID");
        else {
            keys.add (new Key1 (currentKeyID, keyType));
            combos.add (new KeyLock (currentKeyID, temp.getID()));
        }
    }
    
    /**
     * This method adds an existing key to an existing lock. It only creates a
     * new entry in the combos.
     * @param keyID the ID of the key to be added.
     * @param lockID the ID of the lock to be added.
     * @throws java.lang.Exception when one of the entries is not found or when 
     *    a combo for the given pair already exists
     */
    
    public void addKey (int keyID, int lockID) throws Exception {
        Key1 tempKey = null;
        Lock1 tempLock = null;
        KeyLock tempCombo = null;
        // ensure that the key already exits
        tempKey = searchKey (keyID);
        if (tempKey == null) 
            throw new Exception (" No key is found with the given ID");
        else if (tempKey.getType()) // true indicates it is a physical key
            throw new Exception (" The given key is a physical key which cannot be mapped to another lock");
        tempLock = searchLock (lockID);
        if (tempLock == null) throw new Exception (" No lock is found with the given ID");
        if (tempKey != null && tempLock != null) {
            tempCombo = searchCombo (tempKey.getID(), tempLock.getID());
            if (tempCombo != null)
                throw new Exception (" The key and lock are already associated");
            else combos.add (new KeyLock (tempKey.getID(), tempLock.getID()));
        }
    }
    
    /**
     * This method deletes an existing key from an existing lock. It only updates the
     * entry in the combos.
     * @param keyID the ID of the key to be deleted.
     * @param lockID the ID of the lock to be deleted.
     * @throws java.lang.Exception when one of the entries is not found or when
     *    no such combo for the given pair exists
     */
    
    public void deleteKey (int keyID, int lockID) throws Exception {
        Key1 tempKey;
        Lock1 tempLock;
        KeyLock tempCombo;
        // ensure that the key already exits
        tempKey = searchKey (keyID);
        if (tempKey == null) 
            throw new Exception (" No key is found with the given ID");
        else if (tempKey.getType()) // true indicates it is a physical key
            throw new Exception (" The given key is a physical key which cannot be deleted from the lock");
        tempLock = searchLock (lockID);
        if (tempLock == null) throw new Exception (" No lock is found with the given ID");
        tempCombo = searchCombo (tempKey.getID(), tempLock.getID());
        if (tempCombo == null)
            throw new Exception (" The given key and lock are NOT already associated");
        else combos.remove (tempCombo);
    }
    
    /**
     * This method searches for a key in the key collection.
     * @param keyID the ID of the key being searched.
     * @return the key if it is found in the collection; returns null otherwise.
     */
    public Key1 searchKey (int keyID) {
        Key1 temp = null;
        boolean found = false;
        Iterator<Key1> iter = keys.iterator();
        while (!found && iter.hasNext()) {
            temp = iter.next();
            if (temp.getID() == keyID) {
                found = true;
                return temp;
            }
        }
        return null;
    }
    
    /**
     * This method searches for a lock in the lock collection.
     * @param lockID the ID of the lock being searched.
     * @return the lock if it is found in the collection. returns null otherwise.
     */
    public Lock1 searchLock (int lockID) {
        Lock1 temp = null;
        boolean found = false;
        Iterator<Lock1> iter = locks.iterator();
        while (!found && iter.hasNext()) {
            temp = iter.next();
            if (temp.getID() == lockID) {
                found = true;
                return temp;
            }
        }
        return null;
    }
    
    
    /**
     * This method searches for a key and lock combo in the combos collection.
     * @param keyID the ID of the key being searched.
     * @param lockID the ID of the lock being searched.
     * @return the combo if it is found in the collection. returns null otherwise.
     */
    public KeyLock searchCombo (int keyID, int lockID) {
        KeyLock temp = null;
        boolean found = false;
        Iterator<KeyLock> iter = combos.iterator();
        while (!found && iter.hasNext()) {
            temp = iter.next();
            if (temp.getLockID() == lockID && temp.getKeyID() == keyID) {
                found = true;
                return temp;
            }
        }
        return null;
    }
    
    
    /**
     * This method searches for all locks that are opened by a given key. 
     * It returns the result as a set of locks. It also ensures that the key 
     * exists; otherwise an exception is thrown.
     * @param keyID the ID of the key being searched.
     * @return the set of locks that can be opened by the key; returns null 
     * if no such lock is found.
     * @throws java.lang.Exception if the key itself is not found.
     */
    public HashSet<Lock1> searchLocksOpenedByGivenKey (int keyID) throws Exception {
        HashSet<Lock1> result = new HashSet<Lock1>();
        KeyLock temp = null;
        Lock1 tempLock = null;
        if (searchKey (keyID) == null)
            throw new Exception (" No key is found with the given ID");
        else {
            Iterator<KeyLock> iter = combos.iterator();
            while (iter.hasNext()) {
                temp = iter.next();
                if (temp.getKeyID() == keyID) {
                    tempLock = searchLock (temp.getLockID());
                    if (tempLock != null) 
                        result.add (tempLock);
                }
            }
        }
        return result;
    }
    
    
    public HashSet<Lock1> searchLocksNotOpenedByGivenKey (int keyID) throws Exception {
        HashSet<Lock1> result = new HashSet<Lock1>();
        KeyLock temp = null;
        int[] arr = new int[lockCounter-1000-1]; 
        if (searchKey (keyID) == null)
            throw new Exception (" No key is found with the given ID");
        else {
            Iterator<KeyLock> iter = combos.iterator();
            while (iter.hasNext()) {
                temp = iter.next();
                if (temp.getKeyID() == keyID) {
                	arr[temp.getLockID()-1001] = 1;
                }
            }
        }
        for(int i = 0; i < arr.length;i++){
        	if(arr[i]==0){
        		result.add(searchLock(i+1001));
        	}
        }
        return result;
    }
    
    /**
     * This method searches for all keys that opened a given lock. 
     * It returns the result as a set of keys. It also ensures that the lock
     * exists; otherwise an exception is thrown.
     * @param lockID the ID of the lock being searched.
     * @return the set of keys that open the lock; returns null 
     * if no such key is found.
     * @throws java.lang.Exception if the lock itself is not found.
     */
    public HashSet<Key1> searchKeysOpeningGivenLock (int lockID) throws Exception {
        HashSet<Key1> result = new HashSet<Key1>();
        KeyLock temp = null;
        Key1 tempKey = null;
        if (searchLock (lockID) == null)
            throw new Exception (" No lock is found with the given ID");
        else {
            Iterator<KeyLock> iter = combos.iterator();
            while (iter.hasNext()) {
                temp = iter.next();
                if (temp.getLockID() == lockID) {
                    tempKey = searchKey (temp.getKeyID());
                    if (tempKey != null)
                        result.add (tempKey);
                }
            }
        }
        return result;
    }
    
    /**
     * This method searches for all locks in a given building. It throws an 
     * exception if the building name is incorrect. It returns null if there is
     * no lock in that building.
     * @param bldg the name of the building
     * @return the set of locks in the given building.
     * @throws java.lang.Exception if the building name is incorrect.
     */
    public HashSet<Lock1> searchAllLocksInBuilding (String bldg) throws Exception {
        if (! (bldg.trim().equalsIgnoreCase ("WING TECH CENTER") ||
               bldg.trim().equalsIgnoreCase ("CENTENNIAL")))
            throw new Exception (" Incorrect building name");
        HashSet<Lock1> result = new HashSet<Lock1>();
        Lock1 tempLock = null;
        Iterator<Lock1> iter = locks.iterator();
        while (iter.hasNext()) {
            tempLock = iter.next();
            if (tempLock.getBuilding().trim().equalsIgnoreCase (bldg))
                result.add (tempLock);
        }
        return result;
    }
    
    public HashSet<Key1> getAllKeys () {
        return keys;
    }
    
    public HashSet<Lock1> getAllLocks () {
        return locks;
    }
    
    public HashSet<KeyLock> getAllCombos () {
        return combos;
    }
    
    /**
    * This method loads initial data. All values are hard-coded.
    */
    
    public void loadInitialData() {
        // create 10 keys, half of them as swipe card type and the 
        // rest as physical type.
        for (int i = 0; i < 5; i++) {
            keys.add (new Key1 (keyCounter++, false));
        }
        for (int i = 0; i < 5; i++) {
            keys.add (new Key1 (keyCounter++, true));
        }
        // create 10 locks. There are 2 buildings named "WING TECH CENTER",
        // and "CENTENNIAL". First building has 4 locks, one in each
        // room numbered 101 through 104. The second one has 6 locks, 
        // one in each room numbered 101 through 103, 201 through 203.

        int roomCounter;
    
        roomCounter = 101;
        for (int i = 0; i < 4; i++) {
            locks.add (new Lock1 (lockCounter++, roomCounter++, "WING TECH CENTER"));
        }
        roomCounter = 101;
        for (int i = 0; i < 3; i++) {
            locks.add (new Lock1 (lockCounter++, roomCounter++, "CENTENNIAL"));
        }
        roomCounter = 201;
        for (int i = 0; i < 3; i++) {
            locks.add (new Lock1 (lockCounter++, roomCounter++, "CENTENNIAL"));
        }
        // assign keys to locks
        combos.add (new KeyLock (1, 1001));
        combos.add (new KeyLock (2, 1002));
        combos.add (new KeyLock (3, 1003));
        combos.add (new KeyLock (4, 1004));
        combos.add (new KeyLock (5, 1005));
        combos.add (new KeyLock (6, 1006));
        combos.add (new KeyLock (7, 1007));
        combos.add (new KeyLock (8, 1008));
        combos.add (new KeyLock (9, 1009));
        combos.add (new KeyLock (10, 1010));
        combos.add (new KeyLock (1, 1003));
        combos.add (new KeyLock (1, 1007));
        combos.add (new KeyLock (4, 1001));
        combos.add (new KeyLock (1, 1002));
        combos.add (new KeyLock (1, 1009));
        combos.add (new KeyLock (3, 1005));
        combos.add (new KeyLock (6, 1007));
        combos.add (new KeyLock (8, 1001));
        combos.add (new KeyLock (8, 1010));
    }
}
