package Game;

import java.util.ArrayList;

public class Position {
    int xLoc, yLoc;


    public Position(final int x, final int y) {
        this.xLoc = x;
        this.yLoc = y;
    }

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

    public ArrayList<Position> getSurroundingCardinals() {
        final ArrayList<Position> suroundingCardinals = new ArrayList<>();
        suroundingCardinals.add(new Position (xLoc-1, yLoc));
        suroundingCardinals.add(new Position (xLoc+1, yLoc));
        suroundingCardinals.add(new Position (xLoc, yLoc-1));
        suroundingCardinals.add(new Position (xLoc, yLoc+1));

        return suroundingCardinals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (xLoc != position.xLoc) return false;
        return yLoc == position.yLoc;
    }

    @Override
    public String toString() {
        return "(x:"+ getX()+", y:" + getY()+")";
    }
}
