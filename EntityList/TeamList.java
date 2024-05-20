package EntityList;
import Entity.*;
import java.util.ArrayList;
public class TeamList {
    ArrayList <Team> teams =new ArrayList <Team>();
    public void insert(Team t){
        teams.add(t);
    }
    
    public boolean removeTeamByName(String s){
        for(int i=0;i<teams.size();i++){
            if(teams.get(i).getTeamName().equals(s)){
                teams.remove(i);
                return true;
            }
        }
        return false;
    }

    public Team getTeam(String s){
        for(int i=0;i<teams.size();i++){
            if(teams.get(i).getTeamName().equals(s)){
                return teams.get(i);
            }
        }
        return null;
    }

    public boolean searhTeamByName(String s){
        if(getTeam(s) != null){return true;}
        else return false;
    }
    
    public ArrayList <Team> getAll(){
		return teams;
	}
}
