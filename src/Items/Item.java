package Items;

import People.Person;

public abstract class Item {
    int id;
    Person x;

    public Item(Person x,int id)
    {
        this.x =x;
        this.id=id;
    }
}
