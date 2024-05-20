package Interface;

import Entity.Coach;
import Entity.Owner;
import Entity.Person;
import Entity.Player;

public interface Iteam{
    boolean insertPlayer(Player p);
    boolean insertCoach(Coach coach);
    boolean insertOwner(Owner owner);
    Player getPlayerByName(String s);
    Person getPlayerById(int ii);
    Player getByName(String s);
    boolean searchByName(String s);
    boolean removeByName(String a);
}
