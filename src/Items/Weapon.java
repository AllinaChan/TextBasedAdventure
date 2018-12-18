package Items;

import People.Person;

public abstract class Weapon {
    int attack;
    String name;

    public abstract void pickUp(Person x);

    public String getName()
    {
        return name;
    }
    public String toString() {
        return name;
    }
}
