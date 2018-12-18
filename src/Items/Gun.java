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
