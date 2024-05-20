// String name,int age,String nation,double salary,Date joiningDate,double agreement, double investment,int id

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
public class ManageOwner extends JFrame implements ActionListener {
    JTable table;
    DefaultTableModel model;
    Homepage h;
    TeamList l;

    JPanel panel;
    JButton backBtn,addBtn,delete,updateBtn;

    ImageIcon image;
    JLabel background;

    Font font = new Font("Segoe UI", 1, 18);

    public ManageOwner(Homepage h,TeamList l){
		super("Owner Management");
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
        addBtn=new JButton("Add Owner");
        delete=new JButton("Delete");
        updateBtn=new JButton("Update");
		
        backBtn.setBounds(1150,10,100,40);
		backBtn.setBackground(Color.ORANGE);
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
            this.dispose();
            h.setVisible(true);
        }

        else if(e.getSource()==delete){
            int rows[] = table.getSelectedRows();
			if(rows!=null){
				Arrays.sort(rows);
                for(int i= rows.length-1; i>=0;i--){
                    // 0 id, 1 name, 2 age, 3 nation, 4 salary, , 5 agreement, 6  investment, 7 current team

                    String team=table.getModel().getValueAt(rows[i],7).toString();
                    System.out.println("Deleting: "+l.getTeam(team).getOwner().getName());
                    l.getTeam(team).removeOwner();
                    model.removeRow(rows[i]);
                    updateFile();
                }
			}
        }
        else if(e.getSource()==addBtn){
            AddOwner c = new AddOwner(this,l,model);
            System.out.println("Add btn");
        }
        else if(e.getSource()==updateBtn){
            updateFile();
            LoadOwner();
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
				String investment = table.getModel().getValueAt(i,6).toString();
				String currentTeam = table.getModel().getValueAt(i,7).toString();
                
                // 0 id, 1 name, 2 age, 3 nation, 4 salary, , 5 agreement, 6  investment, 7 current team
				String line;
				if(i<rows-1){
					 line = id+";"+name+";"+age+";"+nation+";"+salary+";"+agreement+";"+investment+";"+currentTeam+"\n";
				}
				else{
					 line = id+";"+name+";"+age+";"+nation+";"+salary+";"+agreement+";"+investment+";"+currentTeam;
				}
				allLines += line;
			}
			FileIO.writeInFile(allLines,"./File/owner.txt",false);
	}

    public void createTable() {
        //// 0 id, 1 name, 2 age, 3 nation, 4 salary, , 5 agreement, 6  investment, 7 current team
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Nationality");
        model.addColumn("Salary");
        model.addColumn("Agreement");
        model.addColumn("Investment");
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

		for(int i= 0; i<l.getAll().size();i++){

			if(l.getAll().get(i).getOwner()==null){
                System.out.println("Empty");
			}
			else{
                // 0 id, 1 name, 2 age, 3 nation, 4 salary, , 5 agreement, 6  investment, 7 current team
                
                    System.out.println("adding row");

                    model.addRow(new Object[]{teams.get(i).getOwner().getId() , teams.get(i).getOwner().getName() , teams.get(i).getOwner().getAge() , l.getAll().get(i).getOwner().getNation() , teams.get(i).getOwner().getSalary(), teams.get(i).getOwner().getAgreement() , teams.get(i).getOwner().getInvestment() , teams.get(i).getTeamName()});
			}
		}
        
        panel.setLayout(null);
        panel.add(scrollpane);

        this.setContentPane(panel);
    }

    public void LoadOwner(){
        try {
            Scanner sc = new Scanner(new File("./File/owner.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String cols[] = line.split(";");
                Owner owner;
                // 0 id, 1 name, 2 age, 3 nation, 4 salary, , 5 agreement, 6  investment, 7 current team
                
                //Owner(String name,int age,String nation,double salary,Date joiningDate,double agreement, double investment,int id)

                System.out.println("owner found: " + cols[1]);
                owner=new Owner(cols[1] , Integer.parseInt(cols[2]), cols[3],Double.parseDouble(cols[4]),new Date(1, 1, 2000), Double.parseDouble(cols[5]),Double.parseDouble(cols[6]),Integer.parseInt(cols[0]));

                l.getTeam(cols[7]).insertOwner(owner);

                System.out.println("owner Loaded to " + l.getTeam(cols[7]).getTeamName());
				
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("file not found");
        }
    }
}