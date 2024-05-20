package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import EntityList.*;
import Entity.*;
import File.*;
public class ManageCoach extends JFrame implements ActionListener {
    JTable table;
    DefaultTableModel model;
    Homepage h;
    TeamList l;

    JPanel panel;
    JButton backBtn,addBtn,delete,updateBtn;
    ImageIcon image;
    JLabel background;

    Font font = new Font("arial",Font.PLAIN,20);

    public ManageCoach(Homepage h,TeamList l){
		super("Coach Management");
        this.l=l;
		this.h=h;
        this.setSize(1300, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setIconImage(new ImageIcon("./Assets/football.png").getImage());

        panel = new JPanel();

        backBtn= new JButton("<- Back");
        addBtn=new JButton("Add coach");
        delete=new JButton("Delete");
        updateBtn=new JButton("Update");
		
        backBtn.setBounds(1150,10,100,40);
		backBtn.setBackground(Color.ORANGE);
        backBtn.setForeground(Color.BLACK);
        backBtn.addActionListener(this);
        
        addBtn.setBounds(1050, 200, 150, 60);
        addBtn.setBackground(Color.GREEN);
        addBtn.addActionListener(this);
        
        delete.setBounds(1050,400,150,60);
        delete.setBackground(Color.RED);
        delete.addActionListener(this);

        updateBtn.setBounds(1050,300,150,60);
        updateBtn.setBackground(Color.YELLOW);
        updateBtn.addActionListener(this);
		
        panel.add(backBtn);
        panel.add(addBtn);
        panel.add(delete);
        panel.add(updateBtn);
        createTable();

        image = new ImageIcon("./Assets/addteam.jpg");
        background = new JLabel();
        background.setBounds(0,0,1300,700);
        background.setIcon(image);
        this.add(background);
        

        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
		if(e.getSource()==backBtn){
            h.setVisible(true);
            this.dispose();
        }

        else if(e.getSource()==delete){
            int rows[] = table.getSelectedRows();
			if(rows!=null){
				Arrays.sort(rows);
                for(int i= rows.length-1; i>=0;i--){

                    //0 id , 1 name, 2 age, 3 nation, 4 salary,  , 5 agreement, 6 experience,  7 achievements, 8 current team

                    String team=table.getModel().getValueAt(rows[i],8).toString();
                    System.out.println("Deleting: "+l.getTeam(team).getCoach().getName());
                    l.getTeam(team).removeCoach();
                    model.removeRow(rows[i]);
                    updateFile();
                }
			}
        }
        else if(e.getSource()==addBtn){
            AddCoach c = new AddCoach(this,l,model);
            c.setVisible(true);
            System.out.println("Add btn");
        }
        else if(e.getSource()==updateBtn){
            updateFile();
            LoadCoach();
        }
	}

    public void updateFile(){
		int rows = model.getRowCount();
		String allLines = "";
			for(int i= 0; i < rows; i++){
                String id = table.getModel().getValueAt(i,0).toString();
				String name = table.getModel().getValueAt(i,1).toString();
				String age = table.getModel().getValueAt(i,2).toString();
				String nation = table.getModel().getValueAt(i,3).toString();
				String salary = table.getModel().getValueAt(i,4).toString();
				String agreement = table.getModel().getValueAt(i,5).toString();
				String experience = table.getModel().getValueAt(i,6).toString();
				String acheivements = table.getModel().getValueAt(i,7).toString();
				String currentTeam = table.getModel().getValueAt(i,8).toString();

                //id , name, age, nation, salary, ,double agreement,  experience,  achievements, current team
				String line;
				if(i<rows-1){
					 line = id+";"+name+";"+age+";"+nation+";"+salary+";"+agreement+";"+experience+";"+acheivements+";"+currentTeam+"\n";
				}
				else{
					 line = id+";"+name+";"+age+";"+nation+";"+salary+";"+agreement+";"+experience+";"+acheivements+";"+currentTeam;
				}
				allLines += line;
			}
			FileIO.writeInFile(allLines,"./File/coach.txt",false);
	}

    public void createTable() {
        //String name,int age,String nation,double salary,Date joiningDate,double agreement, int experience, String achievements, int id
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Nationality");
        model.addColumn("Salary");
        model.addColumn("Agreement");
        model.addColumn("Experience");
        model.addColumn("Acheivements");
        model.addColumn("Current Team");
        table = new JTable(model);
        table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setBounds(0, 0, 1000, 550);
		table.setRowHeight(30);
		table.setBackground(new Color(174,247,255));
		table.setSelectionBackground(new Color(200, 200, 230));
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setBounds(10, 50, 1000, 600);
		
		ArrayList <Team> teams=l.getAll();
        for(int i=0;i<l.getAll().size();i++){
            System.out.println("Coach name: "+ l.getAll().get(i).getCoach());
        }

		for(int i= 0; i<l.getAll().size();i++){

			if(l.getAll().get(i).getCoach()==null){
                System.out.println("Empty");
			}
			else{
                //id , name, age, nation, salary, ,double agreement,  experience,  achievements, current team
                    System.out.println("adding row");
                    model.addRow(new Object[]{teams.get(i).getCoach().getId() , teams.get(i).getCoach().getName() , teams.get(i).getCoach().getAge() , l.getAll().get(i).getCoach().getNation() , teams.get(i).getCoach().getSalary(), teams.get(i).getCoach().getAgreement(), teams.get(i).getCoach().getExperience() , teams.get(i).getCoach().getAchievements() , teams.get(i).getTeamName()});
			}
		}
        
        panel.setLayout(null);
        panel.add(scrollpane);

        this.setContentPane(panel);
    }
    
    public void LoadCoach(){
        try {
            Scanner sc = new Scanner(new File("./File/coach.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String cols[] = line.split(";");
                Coach coach;
                //0 id , 1 name, 2 age, 3 nation, 4 salary,  , 5 agreement, 6 experience,  7 achievements, 8 current team
                
                //public Coach(String name,int age,String nation,double salary,Date joiningDate,double agreement, int experience, String achievements, int id){
                System.out.println("Coach found: " + cols[1]);
                coach=new Coach(cols[1] , Integer.parseInt(cols[2]), cols[3],Double.parseDouble(cols[4]),new Date(1, 1, 2000), Double.parseDouble(cols[5]),Integer.parseInt(cols[6]),cols[7],Integer.parseInt(cols[0]));

                l.getTeam(cols[8]).insertCoach(coach);

                System.out.println("Coach Loaded");
				
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("file not found");
        }
    }
}