package Items;

import Game.Constants;
import People.Person;

public class Key extends Item implements ConsumableItems{

    Person x;
    String id=Constants.getNextKeyID();

    public Key(Person x)
    {
        super(x);
    }

    public void use(Person x)
    {
        x.removeKey(id);
    }

    public String getID() {
        return this.id;
    }

}
