package Entity;
import Interface.*;
public class Player extends Person implements Iplayer{
    private int jerseyNo;
    private String position;
    private String leg;
    private double height;
    private String prevClub;
    private int goal;

    //constructor
    public Player(String name,int age,String nation,double salary,Date joiningdate,double agreement,int jerseyNo,String position,String leg,double height,String prevClub,int goal,int id){
        super( name, age, nation, salary,"player", joiningdate, agreement,id);
        this.jerseyNo=jerseyNo;
        this.position=position;
        this.leg=leg;
        this.height=height;
        this.prevClub=prevClub;
        this.goal=goal;
        super.setType("player");
    }
    
    public Player(){
        super( "N/A", 0, "N/A", 0.00,"Player", new Date(0,0,0), 0,0);
        this.jerseyNo=jerseyNo;
        this.position=position;
        this.leg=leg;
        this.height=height;
        this.prevClub=prevClub;
        this.goal=goal;
        super.setType("Player");
    }

    //setter methods
    public void setJerseyNo(int jerseyNo){
        this.jerseyNo = jerseyNo;
    }
    public void setPosition(String position){
        this.position = position;
    }
    public void setLeg(String leg){
        this.leg = leg;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setPreviousClub(String prevClub){
        this.prevClub = prevClub;
    }
    public void setGoal(int goal){
        this.goal = goal;
    }

    //getter methods
    public int getJerseyNo(){
        return this.jerseyNo;
    }
    public String getPosition(){
        return this.position;
    }
    public String getLeg(){
        return this.leg;
    }
    public double getHeight(){
        return this.height;
    }
    public String getPreviousClub(){
        return this.prevClub;
    }
    public int getGoal(){
        return this.goal;
    }

    public void showInfo(){
        System.out.println("Id" + super.getId());
        System.out.println("Person Details");
        System.out.println("Name: " + super.getName());
        System.out.println("Age: " + super.getAge());
        System.out.println("Nation: " + super.getNation());
        System.out.println("Salary: " + super.getSalary());
        System.out.println("Type: " + super.getType());
        System.out.println("Joining Date: " + super.getJoiningDate());
        System.out.println("Agreement" + super.getAgreement());
        System.out.println("Player's Jersy Number : "+ this.jerseyNo);
        System.out.println("Players's playing position : "+this.position);
        System.out.println("Player's playing leg : "+this.leg);
        System.out.println("player's height : "+this.height);
        System.out.println("Previous club of  player : "+this.prevClub);
        System.out.println("Number of goals of player : "+this.goal);
    }
}
