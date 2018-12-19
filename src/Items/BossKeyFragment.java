package Items;

import Game.Constants;
import People.Person;

public class BossKeyFragment  {
    String id;

    public BossKeyFragment()
    {
        this.id= Constants.getNextFragmentID();
    }

    /**
     * @param x - Person that picks up the fragment
     */
    public void pickUp(Person x)
    {
        x.addFragment(new BossKeyFragment());
    }

    /**
     * @return id of key Fragment
     */
    public String toString() {
        return id;
    }
}
