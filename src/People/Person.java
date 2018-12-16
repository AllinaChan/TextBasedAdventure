package People;

import Game.Position;

import java.util.ArrayList;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
    String Name;
    Position position;
    ArrayList<String> keys;

    public int getxLoc() {
        return position.getX();
    }

    public void setxLoc(int xLoc) {
        this.position.setX(xLoc);
    }

    public int getyLoc() {
        return position.getY();
    }

    public void setyLoc(int yLoc) {
        this.position.setY(yLoc);
    }

    public ArrayList<String> getKeys()
    {
        return keys;
    }

    public void addKey(String key)
    {
        keys.add(key);
    }

    public void removeKey(String key)
    {
        keys.remove(key);
    }


    public Person (String Name, Position position)
    {
        this.Name = Name;
        this.position=position;
        this.keys=new ArrayList<>();
    }
}