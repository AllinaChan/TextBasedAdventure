package Items;

import People.Person;

public abstract class Item {
    Person x;

    public Item(Person x)
    {
        this.x =x;
    }
    public abstract String getID();
}
