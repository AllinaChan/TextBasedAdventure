package Items;

import People.Person;

public class Sword extends Weapon {

    public Sword()
    {
        attack=20;
        name="Sword";
    }

    /**
     * adds a sword to the player inventory and increases player attack to be able to kill wolves
     * @param x - player picking the sword up
     */
    public void pickUp(Person x)
    {
        x.addWeapon(new Sword());
        x.addAttack(attack);
    }

}
