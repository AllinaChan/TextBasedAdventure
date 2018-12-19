package Items;

import People.Person;

public class HealingPotion implements ConsumableItems{

    String name;

    public HealingPotion()
    {
        this.name= "healPotion";
    }

    /**
     * removes the first potion in the player inventory, because there is only one type of potions
     * @param x - Player
     * @param name - name of the potion
     */
    public void use(Person x, String name)
    {
        name= this.name;
       x.addHealth(100-x.getHealth());
       x.removePotion();
    }
    public String getName()
    {
        return name;
    }
    public void pickUp(Person x)
    {
        x.addPotion(new HealingPotion());
    }

    @Override
    public String toString() {
        return name;
    }
}
