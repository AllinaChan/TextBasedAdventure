package People;

import Game.Position;
import Items.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
    String Name;
    Position position;
    ArrayList<Key> keys;
    ArrayList<String> keyIDs;
    ArrayList<Weapon> weapons;
    ArrayList<String> weaponNames;
    ArrayList<HealingPotion> potions;
    ArrayList<BossKeyFragment> fragments;
    Trophy trophy;
    int attack;
    int health;

    public Person (String Name, Position position)
    {
        this.Name = Name;
        this.position=position;
        keys=new ArrayList<>();
        attack=5;
        weapons=new ArrayList<>();
        health= 100;
        potions=new ArrayList<>();
        fragments= new ArrayList<>();
        trophy=null;
        keyIDs= new ArrayList<>();
        weaponNames= new ArrayList<>();
    }

    //GETTERS AND SETTERS FOR EACH FIELD
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

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public void addKey(Key key) {
        keys.add(key);
    }

    public ArrayList<String> getKeyIDs()
    {
        keyIDs= new ArrayList<>();
        for(Key key: keys)
        {
            keyIDs.add(key.getID());
        }
        return keyIDs;
    }

    public void removeKey(String id) {
        keys.remove(keys.get(keyIDs.indexOf(id)));
    }

    public int getHealth() {
        return health;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public ArrayList<String> getWeaponNames()
    {
        weaponNames= new ArrayList<>();
        for (Weapon weapon: weapons)
        {
            weaponNames.add(weapon.toString());
        }
        return weaponNames;
    }

    public ArrayList<HealingPotion> getPotions() {
        return potions;
    }

    public void addPotion(HealingPotion potion)
    {
        potions.add(potion);
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
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

    public ArrayList<BossKeyFragment> getFragments() {
        return fragments;
    }

    public void addFragment(BossKeyFragment fragment)
    {
        fragments.add(fragment);
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
        trophy=new Trophy();
    }

    public void removePotion()
    {
        this.potions.remove(potions.get(0));
    }


    /**
     * Prints the entire player inventory in an readable way
     */
    public void printInv()
    {
        String keysl="";
        for(Key key: keys)
        {
            keysl=keysl+"Key ID: "+ key.toString()+" ";
        }
        System.out.println("You currently have the following key(s): ");
        System.out.println(keysl);

        String Weapon="";
        for(Weapon weapon : weapons)
        {
            Weapon = Weapon+ weapon.toString()+" ";
        }
        System.out.println("You currently have the following weapon(s): ");
        System.out.println(Weapon);

        String potion="";
        for(HealingPotion potion1 : potions)
        {
            potion=potion+potion1.toString()+" ";
        }
        System.out.println("You currently have the following potion(s): ");
        System.out.println(potion);

        String fragmen="";
        for(BossKeyFragment fragment: fragments)
        {
            fragmen=fragmen+"Fragment "+ fragment.toString()+" ";
        }
        System.out.println("You currently have the following fragment(s): ");
        System.out.println(fragmen);
    }
}