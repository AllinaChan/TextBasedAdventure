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
    String contains;
    BossKeyFragment fragment;

    public RoomWithWolf(Position position)
    {
        super(position);
        this.wolf=new Wolf();
        this.broadcast="";
        this.contains="[w]";
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
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
        while(!option.equals("flee")||!option.equals("attack")) {
            if(option.equals("flee")||option.equals("attack"))
            {
                break;
            }
            System.out.println("Owooo! The wolf turned around with its red eyes. DO YOU WANT TO 'flee' or 'attack'");
            option=in.nextLine();
            option=option.toLowerCase().trim();
        }
        if(option.equals("flee"))
        {
            if(x.getAttack()>=25)
            {
                System.out.println("You had a sword for a reason...Go back and fight it");
            }else {
                System.out.println("You sprinted out of the room, but the wolf still scratched you");
                x.removeHealth(5);
                broadcast="sendBack";
            }
        }
        if(option.equals("attack"))
        {
            System.out.println("You exchanged blows with the wolf, the wolf died and you took damage");
            wolf.attack(x);
            fragment.pickUp(x);
            System.out.println("--Fragment Acquired--");
            if(x.getHealth()<=0)
            {
                System.out.println("YOU HAVE DIED...Respawning...");
                broadcast="sendBackToSPAWN";
                contains="[w]";
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
    }

    /**
     * Removes the player from the room.
     *
     * @param x
     */
    @Override
    public void leaveRoom(Person x) {
        contains="[ ]";
        occupant = null;
    }
    @Override
    public String toString() {
        return contains;
    }


}
