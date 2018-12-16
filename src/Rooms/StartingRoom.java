package Rooms;

import Game.Position;
import Game.Runner;
import People.Person;

public class StartingRoom extends Room
{
    String contain;

    public StartingRoom(Position position) {
        super(position);
        this.contain="[ ]";
    }

    /**
     * Triggers the game ending conditions.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {

        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
        contain= "[X]";

        System.out.println(
                "███████████████████████████\n" +
                "███████▀▀▀░░░░░░░▀▀▀███████\n" +
                "████▀░░░░░░░░░░░░░░░░░▀████\n" +
                "███│░░░░░░░░░░░░░░░░░░░│███\n" +
                "██▌│░░░░░░░░░░░░░░░░░░░│▐██\n" +
                "██░└┐░░░░░░░░░░░░░░░░░┌┘░██\n" +
                "██░░└┐░░░░░░░░░░░░░░░┌┘░░██\n" +
                "██░░┌┘▄▄▄▄▄░░░░░▄▄▄▄▄└┐░░██\n" +
                "██▌░│██████▌░░░▐██████│░▐██\n" +
                "███░│▐███▀▀░░▄░░▀▀███▌│░███\n" +
                "██▀─┘░░░░░░░▐█▌░░░░░░░└─▀██\n" +
                "██▄░░░▄▄▄▓░░▀█▀░░▓▄▄▄░░░▄██\n" +
                "████▄─┘██▌░░░░░░░▐██└─▄████\n" +
                "█████░░▐█─┬┬┬┬┬┬┬─█▌░░█████\n" +
                "████▌░░░▀┬┼┼┼┼┼┼┼┬▀░░░▐████\n" +
                "█████▄░░░└┴┴┴┴┴┴┴┘░░░▄█████\n" +
                "███████▄░░░░░░░░░░░▄███████\n" +
                "██████████▄▄▄▄▄▄▄██████████\n" +
                "███████████████████████████");
        System.out.println("-ESCAPE ROOM INITIALIZATION COMPLETE-");
        System.out.println("Try to get to the end, okaY?");
    }

    public void leaveRoom(Person x)
    {
        occupant = null;
        contain="[ ]";
    }

    @Override
    public String getBroadcast() {
        return null;
    }

    public String getContain() {
        return contain;
    }
    public String toString() {
        return getContain();
    }

}
