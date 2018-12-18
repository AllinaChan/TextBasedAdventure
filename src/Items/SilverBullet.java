package Items;

import People.Person;

public class SilverBullet extends Weapon{

    public SilverBullet()
    {
        this.attack=0;
        this.name="silverBullet";
    }

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
    public void pickUp(Person x)
    {
        x.addWeapon(new SilverBullet());
        x.addAttack(attack);
    }


}
