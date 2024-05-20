package Entity;
public class Coach extends Person{
    private int experience;
    private String achievements;

    //const
    public Coach(){}
    public Coach(String name,int age,String nation,double salary,Date joiningDate,double agreement, int experience, String achievements, int id){
        super(name, age, nation, salary, "coach", joiningDate, agreement, id);
        this.experience = experience;
        this.achievements = achievements;
    }

    //setter
    public void setExperience(int experience){
        this.experience = experience;
    }
    public void setAchievements(String achievements){
        this.achievements = achievements;
    }

    //getter
    public int getExperience(){
        return this.experience;
    }
    public String getAchievements(){
        return this.achievements;
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
        System.out.println("Experience of coach : "+this.experience);
        System.out.println("Achievements of coach : "+this.achievements);
    }
}