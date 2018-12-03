package People;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
    String Name;
    int xLoc, yLoc;


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

    public Person (String Name, int xLoc, int yLoc)
    {
        this.Name = Name;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }


}