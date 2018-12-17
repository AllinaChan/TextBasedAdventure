package Items;

import People.Person;

public class Trophy {

    String name;

    public Trophy()
    {
        this.name="Trophy";
    }

    public void pickUp(Person x)
    {
        x.setTrophy();
    }

}
