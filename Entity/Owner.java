package Entity;
public class Owner extends Person{
    private double investment;

    //const
    public Owner(){}
    public Owner(String name,int age,String nation,double salary,Date joiningDate,double agreement, double investment,int id){
        super( name, age, nation, salary, "owner", joiningDate, agreement,id);
        this.investment = investment;
    }

    //set
    public void setInvestment(double investment){
        this.investment = investment;
    }
    //get
    public double getInvestment(){
        return this.investment;
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
        System.out.println("The total investment of owner of the club : "+this.investment);
    }
}