package Items;

import People.Person;

public class Key extends Item implements ConsumableItems{

    Person x;
    int id;

    public Key(Person x, int id)
    {
        super(x , id);
    }


    public String getID() {
        return  Integer.toString(id);
    }

}
