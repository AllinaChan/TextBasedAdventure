package Game;

import java.util.ArrayList;

public class Position {
    int xLoc, yLoc;


    public Position(final int x, final int y) {
        this.xLoc = x;
        this.yLoc = y;
    }

    //Getters and setters of Position
    public int getX() {
        return xLoc;
    }

    public int getY() {
        return yLoc;
    }
    public void setX(int x)
    {
        xLoc= x;
    }

    public void setY(int y)
    {
        yLoc= y;
    }

    /**
     * @return - The surrounding positions of a position, Northern Pos, Southern Pos, Western Pos, Easter Pos
     */
    public ArrayList<Position> getSurroundingCardinals() {
        final ArrayList<Position> suroundingCardinals = new ArrayList<>();
        suroundingCardinals.add(new Position (xLoc-1, yLoc));
        suroundingCardinals.add(new Position (xLoc+1, yLoc));
        suroundingCardinals.add(new Position (xLoc, yLoc-1));
        suroundingCardinals.add(new Position (xLoc, yLoc+1));

        return suroundingCardinals;
    }

    /**
     * @param x -The position to be checked
     * @return boolean that verifies if one position is equal to another
     */
    @Override
    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null || getClass() != x.getClass()) return false;

        Position position = (Position) x;

        if (xLoc != position.xLoc) return false;
        return yLoc == position.yLoc;
    }

    /**
     * @return The position in String form
     */
    @Override
    public String toString() {
        return "(x:"+ getX()+", y:" + getY()+")";
    }
}
