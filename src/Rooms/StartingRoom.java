package Rooms;

import Game.Position;
import Game.Runner;
import People.Person;

public class StartingRoom extends Room
{
    String contain;
    String broadcast;
    public StartingRoom(Position position) {
        super(position);
        this.contain="[X]";
        this.broadcast="";
    }

    /**
     * Triggers the game ending conditions.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {

        contain = "[X]";
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());

        if(broadcast.equals("playerLeft"))
        {
            System.out.println("Welcome back...");
        }
        else {

            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());
            contain = "[X]";
            System.out.println(" ");
            System.out.println("Here's the MAP LEGEND, you can access it anytime.");
            System.out.println("[X] = You"+"\n"+"[K] = Room with a KEY" +"\n"+ "[L] = Room with a lock, you can check what key ID you need by entering it"+
                    "\n" +"[W] = Werewolf"+ "\n" +"[w] = Wolf" +"\n"+ "[G] = Gun"+"\n"+"[B] = Silver Bullet"+"\n"+ "[H] = Health Potion" +"\n"+"[S] = Sword" + "\n"+ "[-] = Starting Room");

            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

            System.out.println("-------------------------");
            System.out.println("While you risk your life, I will be back at the village sipping on a cup of tea.");
            System.out.println("*Whispering* it sure is smart asking someone to kill a Werewolf during a full moon...");
            broadcast = "playerLeft";
        }

    }

    @Override
    public void leaveRoom(Person x)
    {
        occupant = null;
        contain="[-]";
    }

    @Override
    public String getBroadcast() {
        return broadcast;
    }

    public String getContain() {
        return contain;
    }

    @Override
    public String toString() {
        return contain;
    }

}
