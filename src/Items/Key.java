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
