package Items;

import People.Person;

public class HealingPotion implements ConsumableItems{

    String name;
    public HealingPotion()
    {
        this.name= "healingPotion";
    }
    public void use(Person x)
    {

    }
    public String getName()
    {
        return name;
    }
}
