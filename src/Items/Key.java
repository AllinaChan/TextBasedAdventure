package Items;

import Game.Constants;
import People.Person;

public class Key implements ConsumableItems{

    String id;
    String name;

    public Key()
    {
        this.id=Constants.getNextKeyID();
        this.name= id;
    }


    /**
     * Uses the key and then remove it from the player inventory since its a consumable
     * @param x - player
     * @param keyID - int of the keyId to use
     */
    public void use(Person x, String keyID)
    {
        x.removeKey(keyID);
    }

    public String getID() {
        return this.id;
    }
    public String getName() {return this.name;}

    @Override
    public String toString() {
        return name;
    }
}
