package Items;

import People.Person;

public abstract class Weapon {
    int attack;
    String name;

    public void pickUp(Person x)
    {
        x.addWeapon(name);
        x.addAttack(attack);
    }

    public String getName()
    {
        return name;
    }
    public String toString() {
        return name;
    }
}
