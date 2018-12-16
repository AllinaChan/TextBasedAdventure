package Items;

import People.Person;

public class Gun extends Weapon{

    public Gun ()
    {
        attack=0;
        name="gun";
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
