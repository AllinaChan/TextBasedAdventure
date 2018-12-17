package Rooms;

import Game.Position;
import Items.Gun;
import Items.HealingPotion;
import Items.Sword;
import Items.Trophy;
import Monsters.WereWolf;
import People.Person;

import java.util.Scanner;

public class RoomWithWerewolf extends Room{

    WereWolf wereWolf;
    String broadcast;
    String contains;
    Trophy trophy;

    public RoomWithWerewolf(Position position)
    {
        super(position);
        this.wereWolf=new WereWolf();
        this.broadcast="";
        this.contains="[W]";
        this.trophy= new Trophy();
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
            System.out.println("RAWRRRR!"+"\n"+ "QUICK, THE WEREWOLF SPOTTED YOU! DO YOU WANT TO 'flee' or 'attack'");
            option=in.nextLine();
            option=option.toLowerCase().trim();
        }
        if(option.equals("flee"))
        {
            if(x.getAttack()>=10000)
            {
                System.out.println("WHY DID YOU RUN??? YOU COULD'VE KILLED THAT MONSTER!");
            }else {
                System.out.println("oof! that was a close call, you should find the GUN and the SILVER BULLET before fighting that thing");
                broadcast="sendBack";
            }
        }
        if(option.equals("attack"))
        {
            if(x.getAttack()>=10000)
            {
                System.out.println(
                        "…..____________________ , ,__\n" +
                        "……/ `—___________—-_____] – - – - – - – - ░ ▒▓▓█D\n" +
                        "…../_==o;;;;;;;;_______.:/\n" +
                        "…..), —.(_(__) /\n" +
                        "….// (..) ), —-”\n" +
                        "…//___//\n" +
                        "..//___//\n" +
                        ".//___//\n"
                );
                System.out.println("BANG!");
                try
                {
                    Thread.sleep(2000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                System.out.println("The SILVER BULLET pierced through the werewolf's heart");
                System.out.println("You walked up to the werewolf and severed its head");
                System.out.println("--TROPHY ACQUIRED--");
                try
                {
                    Thread.sleep(2000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                System.out.print("CReeeKKKKK");
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                System.out.println(
                        " ______________\n" +
                        "|\\ ___________ /|\n" +
                        "| |  /|,| |   | |\n" +
                        "| | |,x,| |   | |\n" +
                        "| | |,x,' |   | |\n" +
                        "| | |,x   ,   | |\n" +
                        "| | |/    |   | |\n" +
                        "| |    /] ,   | |\n" +
                        "| |   [/ ()   | |\n" +
                        "| |       |   | |\n" +
                        "| |       |   | |\n" +
                        "| |       |   | |\n" +
                        "| |      ,'   | |\n" +
                        "| |   ,'      | |\n" +
                        "|_|,'_________|_|");
                System.out.println("That door is now opened, go EAST to escape!");
            }
            else{
                System.out.println(
                        "────────────────────▄▄───────────────────▄▄──\n" +
                        "──────────────────────▀█───────────────────▀█─\n" +
                        "────────────────────▄█───────────────────▄█─\n" +
                        "──█████████▀───────────█████████▀─\n" +
                        "───▄██████▄─────────────▄██████▄──\n" +
                        "─▄██▀────▀██▄─────────▄██▀────▀██▄\n" +
                        "─██────────██─────────██─────────────██\n" +
                        "─██───██───██─────────██───██──────██\n" +
                        "─██────────██─────────██────────────██\n" +
                        "──██▄────▄██───────────██▄────────▄██─\n" +
                        "───▀██████▀─────────────▀██████▀──\n" +
                        "──────────────────────────────────\n" +
                        "──────────────────────────────────\n" +
                        "──────────────────────────────────\n" +
                        "───────────█████████████──────────\n" +
                        "──────────────────────────────────\n" +
                        "──────────────────────────────────");

                try
                {
                    Thread.sleep(2000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }

                System.out.println("You attack the Werewolf for "+x.getAttack()+ "damage");
                System.out.println("It wasn't fazed...");
                try
                {
                    Thread.sleep(2000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                System.out.println("RAWR! The Werewolf pounced at you out of anger");
                wereWolf.attack(x);
                if(x.getHealth()<=0)
                {
                    System.out.println("YOU HAVE DIED...Respawning...");
                    broadcast="sendBackToSPAWN";
                    contains="[W]";
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
