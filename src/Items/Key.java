package Items;

import Game.Constants;
import People.Person;

public class Key implements ConsumableItems{

    String id;
    String name;

    public Key()
    {
        this.id=Constants.getNextKeyID();
        this.name="Key: " + id;
    }


    public void use(Person x)
    {
        x.removeKey(id);
    }

    public String getID() {
        return this.id;
    }
    public String getName() {return this.name;}

}
