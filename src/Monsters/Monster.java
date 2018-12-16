package Monsters;

import People.Person;

public abstract class Monster {
    int health;
    int attack;

    public void attack(Person x)
    {
        x.removeHealth(attack);
    }

    public void removeHealth(Person x)
    {
        this.health-=x.getAttack();
    }
}
