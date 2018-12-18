package Items;

import People.Person;

public class Sword extends Weapon {

    public Sword()
    {
        attack=20;
        name="Sword";
    }
    public void pickUp(Person x)
    {
        x.addWeapon(new Sword());
        x.addAttack(attack);
    }

}
