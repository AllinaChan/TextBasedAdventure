package Items;

import Game.Constants;
import People.Person;

public class Key extends Item implements ConsumableItems{

    String id;

    public Key()
    {
        this.id=Constants.getNextKeyID();
    }


    public void use(Person x)
    {
        x.removeKey(id);
    }

    public String getID() {
        return this.id;
    }

}
