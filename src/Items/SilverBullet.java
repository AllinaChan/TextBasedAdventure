package Items;

import People.Person;

public class SilverBullet extends Weapon{

    public SilverBullet()
    {
        this.attack=0;
        this.name="silverBullet";
    }

    /**
     * IF the player already have the gun in their inventory, then they gain enough attack to kill the Werewolf
     * @param x - Player picking the item up
     */
    public void fusionPickUp(Person x)
    {
        if(x.getWeapons().contains("gun"))
        {
            x.addAttack(10000);
            x.addWeapon(new SilverBullet());
        }else{
            x.addWeapon(new SilverBullet());
        }
    }

    /**
     * picks up the silverBullet
     * @param x -person
     */
    public void pickUp(Person x)
    {
        x.addWeapon(new SilverBullet());
        x.addAttack(attack);
    }


}
