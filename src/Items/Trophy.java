package Items;

import People.Person;

public class Trophy {

    String name;

    public Trophy()
    {
        this.name="Trophy";
    }

    /**
     * picks up the trophy
     * @param x - player picking the trophy up
     */
    public void pickUp(Person x)
    {
        x.setTrophy();
    }

}
