package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Entity.Team;
import EntityList.TeamList;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import File.*;
import File.FileIO;

public class Manageteam extends JFrame implements ActionListener{
    JPanel panel;
    JButton backBtn,addBtn,delete,updateBtn;
    JLabel idLabel, modelLabel, rentLabel;
    JTextField idField, modelField, rentField;
    ImageIcon image;
    JLabel background;

    Font font = new Font("arial",Font.BOLD,20);

    JTable table;
    DefaultTableModel model;
    Homepage h;
    TeamList l;

    public Manageteam(Homepage h,TeamList l) {
        super("Team Management");
        this.l=l;
        this.h=h;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);

        this.setIconImage(new ImageIcon("./Assets/football.png").getImage());

        panel = new JPanel();
        createTable();

        backBtn= new JButton("<- Back");
        addBtn=new JButton("Add team");
        delete=new JButton("Delete");
        updateBtn=new JButton("Update");
        
        backBtn.setBounds(800,10,150,40);
		backBtn.setBackground(Color.ORANGE);
        backBtn.setForeground(Color.BLACK);
        backBtn.addActionListener(this);
        
        addBtn.setBounds(710, 150, 200, 60);
        addBtn.setBackground(Color.GREEN);
        addBtn.setForeground(Color.BLACK);
        addBtn.addActionListener(this);
        
        delete.setBounds(710, 330, 200, 60);
        delete.setBackground(Color.RED);
        delete.setForeground(Color.BLACK);
        delete.addActionListener(this);

        updateBtn.setBounds(710, 240, 200, 60);
        updateBtn.setBackground(Color.YELLOW);
        updateBtn.setForeground(Color.BLACK);
        updateBtn.addActionListener(this);

        panel.add(backBtn);
        panel.add(delete);
        panel.add(addBtn);
        panel.add(updateBtn);

        image = new ImageIcon("./Assets/team.jpg");
        background = new JLabel();
        background.setBounds(0,0,1000,600);
        background.setIcon(image);
        this.add(background);

        this.h=h;
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==backBtn){
            this.setVisible(false);
          this.dispose();
          this.h.setVisible(true);
         
        }
        else if(e.getSource()==addBtn){
            AddTeam m=new AddTeam(this,l,model);
            this.setVisible(false);
        }
        else if(e.getSource()==delete){
            int rows[] = table.getSelectedRows();
			if(rows!=null){
                Arrays.sort(rows);
				for(int i= rows.length-1; i>=0;i--){
                    l.removeTeamByName(table.getModel().getValueAt(rows[i],1).toString());
                    System.out.println("object deleted");

                    String teamName=table.getModel().getValueAt(rows[i],1).toString();
                    System.out.println(teamName + " deleted");
                    //have to delete players also
                    deleteOthers(teamName);

					model.removeRow(rows[i]);
				}
			}
            updateFile();            
        }
        else if(e.getSource()==updateBtn){
            updateFile();
            this.setVisible(false);
			LogIn log= new LogIn();
        }
    }
    public void updateFile(){
		int rows = model.getRowCount();
			String allLines = "";
			for(int i= 0; i < rows; i++){
                int id =Integer.parseInt(table.getModel().getValueAt(i,0).toString());
				String name = table.getModel().getValueAt(i,1).toString();
				String line;
				if(i<rows-1){
					 line = id+";"+name+"\n";
				}
				else{
					 line =id+";"+name;
				}
				allLines += line;
			}
            System.out.println(allLines);
			FileIO.writeInFile(allLines,"./File/teams.txt",false);
	}

    public void createTable() {
        model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Name");

        table = new JTable(model);
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setBounds(0, 0, 650, 400);
		table.setRowHeight(30);
		table.setBackground(new Color(174,247,255));
		table.setSelectionBackground(new Color(200, 200, 230));
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setBounds(50, 50, 650, 450);

        ArrayList<Team> allTeam = l.getAll();

        for (int i=0;i<allTeam.size();i++) {
                model.addRow(new Object[]{allTeam.get(i).getTeamId(),allTeam.get(i).getTeamName()});
        }
        
        panel.setLayout(null);
        panel.add(scrollpane);

        this.setContentPane(panel);
    }
    
    
    
    
    
   void deleteOthers(String teamName){
        //delete players
        try{
            Scanner sc = new Scanner(new File("./File/players.txt"));
            String allLines="";
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String cols[] = line.split(";");
                if(!cols[12].equals(teamName)){
                    allLines+=line+"\n";
                }
            }
            if(allLines.length()>1){
                String filteredLines = allLines.substring(0, allLines.length()-1);
                sc.close();
                FileIO.writeInFile(filteredLines,"./File/players.txt",false);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("file not found");
        }
        //delete coach
        try{
            Scanner sc = new Scanner(new File("./File/coach.txt"));
            String allLines="";
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String cols[] = line.split(";");
                if(!cols[8].equals(teamName)){
                    allLines+=line+"\n";
                }
            }
            if(allLines.length()>1){
                String filteredLines = allLines.substring(0, allLines.length()-1);
                sc.close();
                FileIO.writeInFile(filteredLines,"./File/coach.txt",false);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("file not found");
        }

        //delete owner
        try{
            Scanner sc = new Scanner(new File("./File/owner.txt"));
            String allLines="";
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String cols[] = line.split(";");
                if(!cols[7].equals(teamName)){
                    allLines+=line+"\n";
                }
            }
            if(allLines.length()>1){
                String filteredLines = allLines.substring(0, allLines.length()-1);
                sc.close();
                FileIO.writeInFile(filteredLines,"./File/owner.txt",false);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("file not found");
        }
    }
    void loadTeams(){
        try {
        Scanner sc = new Scanner(new File("./File/teams.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String cols[] = line.split(";");
            Team team;
            
            System.out.println("Team found: " + cols[1]);


            team=new Team(Integer.parseInt(cols[1]) , cols[2]);

            l.insert(team);
            System.out.println(cols[1]+" team Loaded");
            
        }
        sc.close();
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
        System.out.println("file not found");
    }
}   
}
