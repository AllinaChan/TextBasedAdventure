package Items;

import People.Person;

public abstract class Weapon {
    int attack;
    String name;

    public void pickUp(Person x)
    {
        x.addWeapon(name);
    }
}
