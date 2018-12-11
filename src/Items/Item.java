package Items;

import People.Person;

public abstract class Item {
    String id;
    Person x;

    public Item(Person x,String id)
    {
        this.x =x;
        this.id=id;
    }
    public abstract String getID();
}
