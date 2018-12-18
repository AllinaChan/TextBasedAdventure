package Items;

import People.Person;

public interface ConsumableItems {
    void use(Person x, String name);
    String getName();
}
