package People;

import Game.Position;
import Items.Trophy;
import Items.Weapon;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
    String Name;
    Position position;
    ArrayList<String> keys;
    ArrayList<String> weapons;
    ArrayList<String> potions;
    ArrayList<String> fragments;
    Trophy trophy;
    int attack;
    int health;

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

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void addKey(String key) {
        keys.add(key);
    }

    public void removeKey(String key) {
        keys.remove(key);
    }

    public int getHealth() {
        return health;
    }

    public void addWeapon(String name) {
        weapons.add(name);
    }

    public ArrayList<String> getPotions() {
        return this.potions;
    }

    public void addPotion(String name)
    {
        potions.add(name);
    }

    public ArrayList<String> getWeapons() {
        return this.weapons;
    }

    public void addHealth(int health) {
        this.health +=health;
    }

    public void removeHealth(int damage) {
        this.health -= damage;
    }

    public int getAttack()
    {
        return this.attack;
    }

    public ArrayList<String> getFragments() {
        return fragments;
    }

    public void addFragment(String id)
    {
        fragments.add(id);
    }

    public void addAttack(int amount)
    {
        this.attack+=amount;
    }

    public boolean hasTrophy()
    {
        return this.trophy!=null;
    }

    public void setTrophy()
    {
        this.trophy=new Trophy();
    }

    public void removePotion(String name)
    {
        this.potions.remove(name);
    }

    public Person (String Name, Position position)
    {
        this.Name = Name;
        this.position=position;
        this.keys=new ArrayList<>();
        this.attack=5;
        this.weapons=new ArrayList<>();
        this.health= 100;
        this.potions=new ArrayList<>();
        this.fragments= new ArrayList<>();
        this.trophy=null;
    }
    public void printInv()
    {
        String keysl="";
        for(String string: keys)
        {
            keysl=keysl+"Key ID: "+ string+" ";
        }
        System.out.println("You currently have the following key(s): ");
        System.out.println(keysl);

        String Weapon="";
        for(String string : weapons)
        {
            Weapon = Weapon+ string+" ";
        }
        System.out.println("You currently have the following weapon(s): ");
        System.out.println(Weapon);

        String potion="";
        for(String string: potions)
        {
            potion=potion+ string+" ";
        }
        System.out.println("You currently have the following potion(s): ");
        System.out.println(potion);

        String fragmen="";
        for(String string: fragments)
        {
            fragmen=fragmen+"Fragment "+ string+" ";
        }
        System.out.println("You currently have the following fragment(s): ");
        System.out.println(fragmen);
    }
}