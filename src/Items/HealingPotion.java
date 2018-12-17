package Items;

import People.Person;

public class HealingPotion implements ConsumableItems{

    String name;

    public HealingPotion()
    {
        this.name= "healPotion";
    }
    public void use(Person x)
    {
       x.addHealth(100-x.getHealth());
       x.removePotion(name);
    }
    public String getName()
    {
        return name;
    }
    public void pickUp(Person x)
    {
        x.addPotion(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
