package Items;

import People.Person;

public abstract class Weapon {
    int attack;
    String name;

    /**
     * Abstract pickUp method for all the subclass to implement
     * @param x - player picking the item up
     */
    public abstract void pickUp(Person x);

    public String getName()
    {
        return name;
    }

    public String toString() {
        return name;
    }
}
