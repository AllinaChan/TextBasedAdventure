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
            x.addWeapon(name);
        }else{
            x.addWeapon(name);
        }
    }

}
