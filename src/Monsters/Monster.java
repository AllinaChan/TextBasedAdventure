package Monsters;

import People.Person;

public abstract class Monster {
    int health;
    int attack;

    /**
     * @param x - the player that gets attacked or damaged.
     */
    public void attack(Person x)
    {
        x.removeHealth(attack);
    }

    /**
     * @param x removes health from the monster
     */
    public void removeHealth(Person x)
    {
        this.health-=x.getAttack();
    }
}
