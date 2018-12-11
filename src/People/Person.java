package People;

import java.util.ArrayList;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
    String Name;
    int xLoc, yLoc;
    ArrayList<String> keys;

    public int getxLoc() {
        return xLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    public ArrayList<String> getKeys()
    {
        return this.keys;
    }

    public void addItem(String key)
    {
        this.keys.add(key);
    }


    public Person (String Name, int xLoc, int yLoc)
    {
        this.Name = Name;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }
    public ArrayList<>


}