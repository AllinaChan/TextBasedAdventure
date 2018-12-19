package Rooms;

import Game.Position;
import Items.BossKeyFragment;
import Items.Gun;
import Items.HealingPotion;
import Items.Sword;
import Monsters.Wolf;
import People.Person;

import java.util.Scanner;

public class RoomWithWolf extends Room{

    Wolf wolf;
    String broadcast;
    String contain;
    BossKeyFragment fragment;

    //A room with a wolf
    public RoomWithWolf(Position position)
    {
        super(position);
        this.wolf=new Wolf();
        this.broadcast="";
        this.contain="[w]";
        this.fragment=new BossKeyFragment();
    }

    /**
     * Method controls the results when a person enters this room.
     *
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {
        Scanner in = new Scanner(System.in);
        String option="";

        contain = "[X]";
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());

        if(broadcast.equals("wolfDied"))
        {
            contain = "[X]";
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());

        }else{
        while(!option.equals("flee")||!option.equals("attack")) {
            if(option.equals("flee")||option.equals("attack"))
            {
                break;
            }
            System.out.println("-------------------------");
            System.out.println("Owooo! The wolf turned around with its red eyes. DO YOU WANT TO 'flee' or 'attack'");
            option=in.nextLine();
            option=option.toLowerCase().trim();
        }
        if(option.equals("flee"))
        {
            if(x.getAttack()>=25)
            {
                System.out.println("The wolf tried to scratch you, but you blocked it with your sword...Wait... ");
                System.out.println("You had a sword for a reason...Go back and fight it");
                broadcast="sendBack";
                contain="[w]";
            }else {
                System.out.println("You sprinted out of the room, but the wolf still scratched you");
                x.removeHealth(10);
                broadcast="sendBack";
                contain="[w]";
            }
            if(x.getHealth()<=0)
            {
                System.out.println("YOU HAVE DIED...Respawning...");
                broadcast="sendBackToSPAWN";
                contain="[w]";
            } else if(x.getHealth()<=20)
            {
                if(x.getPotions().contains("healPotion"))
                {
                    System.out.println("You have suffered grave damage, you should use that HEALTH POTION or you might not survive the next battle");
                }
                else{
                    System.out.println("Go look for a Health Potion before going into battle, Look for the [H] icon");
                }
            }
        }
        if(option.equals("attack")) {
            if (x.getAttack() >= 25) {
                System.out.println("You exchanged blows with the wolf, the wolf died and you took damage");
                wolf.attack(x);
                fragment.pickUp(x);
                System.out.println("--Fragment Acquired--");
                broadcast = "wolfDied";
                this.contain="[X]";

            } else {
                System.out.println("You punched the wolf with your bare hands, it barely fazed the wolf");
                System.out.println("But the wolf left you with a grievous wound");
                wolf.attack(x);
                broadcast="sendBack";
                contain="[w]";
            }
            if (x.getHealth() <= 0) {
                System.out.println("YOU HAVE DIED...Respawning...");
                broadcast = "sendBackToSPAWN";
                contain = "[w]";
            } else if (x.getHealth() <= 20) {
                if (x.getPotions().contains("healPotion")) {
                    System.out.println("You have suffered grave damage, you should use that HEALTH POTION or you might not survive the next battle");
                } else {
                    System.out.println("Go look for a Health Potion before going into battle, Look for the [H] icon");
                }
            }
        }
        }
    }
    public String getBroadcast()
    {
        return broadcast;
    }
    /**
     * Removes the player from the room.
     *
     * @param x
     */
    @Override
    public void leaveRoom(Person x) {
        contain="[ ]";
        occupant = null;
        if(broadcast.equals("sendBack"))
        {
            contain = "[w]";
        }
    }
    public String getContains()
    {
        return contain;
    }

    /**
     * Allows board print() to be in a readable format
     * @return what the room contains, as a String, the symbol
     */
    @Override
    public String toString() {
        return contain;
    }


}
