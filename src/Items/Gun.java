package Items;

import People.Person;

public class Gun extends Weapon{

    public Gun ()
    {
        attack=0;
        name="gun";
    }

    /**
     * IF the player already have the silverBullet in their inventory, then they gain enough attack to kill the Werewolf
     * @param x - Player picking the item up
     */
    public void fusionPickUp(Person x)
    {
        if(x.getWeaponNames().contains("silverBullet"))
        {
            x.addAttack(10000);
            x.addWeapon(new Gun());
        }else{
            x.addWeapon(new Gun());
        }
    }
    public void pickUp(Person x)
    {
        x.addWeapon(new Gun());
        x.addAttack(attack);
    }

}
