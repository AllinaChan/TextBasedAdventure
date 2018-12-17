package Items;

import Game.Constants;
import People.Person;

public class BossKeyFragment  {
    String id;

    public BossKeyFragment()
    {
        this.id= Constants.getNextFragmentID();
    }

    public void pickUp(Person x)
    {
        x.addFragment(id);
    }
    public String toString() {
        return id;
    }
}
