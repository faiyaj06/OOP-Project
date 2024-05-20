package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import EntityList.TeamList;
import Entity.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import File.FileIO;

public class ManagePlayer extends JFrame implements ActionListener{
	JPanel panel;
    JButton backBtn,addBtn,delete,transfer,updateBtn,loadBtn;

    ImageIcon image;
    JLabel background;

    Font font = new Font("arial",Font.PLAIN,20);
	
    JTable table,table1;
    DefaultTableModel model,model1;
    Homepage h;
    TeamList l;
	
    public ManagePlayer(Homepage h,TeamList l) {
		super("Player Management");
        this.l=l;
		this.h=h;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1500, 900);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setIconImage(new ImageIcon("./Assets/football.png").getImage());
        
        panel = new JPanel();
        
        backBtn= new JButton("<-Back");
        addBtn=new JButton("Add Player");
        delete=new JButton("Delete");
        transfer =new JButton("Transfer Player");
        updateBtn=new JButton("Update");
        loadBtn=new JButton("Load Players");
		
        backBtn.setBounds(10,10,100,40);
		backBtn.setBackground(Color.ORANGE);
        backBtn.addActionListener(this);
        
        addBtn.setBounds(250, 710, 150, 60);
        addBtn.setBackground(Color.GREEN);
        addBtn.addActionListener(this);
        
        delete.setBounds(850,710,150,60);
        delete.setBackground(Color.RED);
        delete.addActionListener(this);

        transfer.setBounds(450,710,150,60);
        transfer.setBackground(Color.lightGray);
        transfer.addActionListener(this);

        updateBtn.setBounds(650,710,150,60);
        updateBtn.setBackground(Color.YELLOW);
        updateBtn.addActionListener(this);

        loadBtn.setBounds(1050,710,150,60);
        loadBtn.setBackground(Color.GREEN);
        loadBtn.addActionListener(this);
		
        panel.add(backBtn);
        panel.add(addBtn);
        panel.add(delete);
        panel.add(transfer);
        panel.add(updateBtn);
        panel.add(loadBtn);

        createTable();
        createteamTable();
        
        image = new ImageIcon("./Assets/addteam.jpg");
		background = new JLabel();
		background.setBounds(0,0,1500,900);
		background.setIcon(image);
		panel.add(background);

        this.setVisible(true);
    }

    public void createTable() { 

        //id;name;age;nation;salary;agreement;jersey no;position;leg;height;prev club;goal;current team
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("age");
        model.addColumn("Nationality");
        model.addColumn("Salary");
        model.addColumn("Agreement");
        model.addColumn("Jersey no");
        model.addColumn("Position");
        model.addColumn("Leg");
        model.addColumn("Height");
        model.addColumn("Prev club");
        model.addColumn("Goal");
        model.addColumn("Club");

        table = new JTable(model);
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setBounds(0, 0, 1000, 700);
		table.setRowHeight(30);
		table.setBackground(new Color(174,247,255));
		table.setSelectionBackground(new Color(200, 200, 230));
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setBounds(10, 100, 1200, 550);
		
		// ArrayList <Team> teams=l.getAll();
		// ArrayList <Player> players = new ArrayList<>();
        // for(int i=0;i<l.getAll().size();i++){
        //     System.out.println("player number: "+ l.getAll().get(i).getPlayers().size());
        // }

		// for(int i= 0; i<l.getAll().size();i++){

		// 	players= teams.get(i).getPlayers();

		// 	if(players.size()==0){
        //         System.out.println("Empty");
		// 	}
		// 	else{
        //         //id;name;age;nation;salary;agreement;jersey no;position;leg;height;prev club;goal;current team
        //         for (int j=0;j<players.size();j++) {
        //             System.out.println("adding row");
        //             model.addRow(new Object[]{players.get(j).getId() , players.get(j).getName() , players.get(j).getAge() , players.get(j).getNation() , players.get(j).getSalary() ,players.get(j).getAgreement(),players.get(j).getJerseyNo(),players.get(j).getPosition(),players.get(j).getLeg(),players.get(j).getHeight(),players.get(j).getPreviousClub(),players.get(j).getGoal() , teams.get(i).getTeamName()});
        //         }
		// 	}
		// }
        
        panel.setLayout(null);
        panel.add(scrollpane);

        this.setContentPane(panel);
    }

    public void updateFile(){
		int rows = model.getRowCount();
		String allLines = "";
			for(int i= 0; i < rows; i++){
                System.out.println("update running");
                //id;name;age;nation;salary;agreement;jersey no;position;leg;height;prev club;goal;current team
				String id = table.getModel().getValueAt(i,0).toString();
				String name = table.getModel().getValueAt(i,1).toString();
				String age = table.getModel().getValueAt(i,2).toString();
				String nation = table.getModel().getValueAt(i,3).toString();
				String salary = table.getModel().getValueAt(i,4).toString();
				String agreement = table.getModel().getValueAt(i,5).toString();
				String jerseyNo = table.getModel().getValueAt(i,6).toString();
				String position = table.getModel().getValueAt(i,7).toString();
				String leg = table.getModel().getValueAt(i,8).toString();
				String height = table.getModel().getValueAt(i,9).toString();
				String prevClub = table.getModel().getValueAt(i,10).toString();
				String goal = table.getModel().getValueAt(i,11).toString();
				String currentTeam = table.getModel().getValueAt(i,12).toString();
				String line;
				if(i<rows-1){
					 line = id+";"+  name+";"+  age+";"+  nation+";"+  salary+";"+  agreement+";"+  jerseyNo+";"+  position+";"+  leg+";"+  height+";"+ prevClub+";"+  goal+";"+  currentTeam+"\n";
				}
				else{
					 line = id+";"+  name+";"+  age+";"+  nation+";"+  salary+";"+  agreement+";"+  jerseyNo+";"+  position+";"+  leg+";"+  height+";"+  prevClub+";"+goal+";"+currentTeam;
				}
				allLines += line;
			}
			FileIO.writeInFile(allLines,"./File/players.txt",false);
	}
    
    public void actionPerformed(ActionEvent e){
        if(backBtn== e.getSource()){
          this.dispose();
          this.h.setVisible(true);
        }
        else if(e.getSource()==addBtn){
            // AddTeam m=new AddTeam(this,l,model);
                AddPlayer p=new AddPlayer(this,l,model);
                this.setVisible(false);
        }
        else if(e.getSource()==delete){
            int rows[] = table.getSelectedRows();
			if(rows!=null){
				Arrays.sort(rows);
                for(int i= rows.length-1; i>=0;i--){
                    String name = table.getModel().getValueAt(rows[i],1).toString();
                    String team=table.getModel().getValueAt(rows[i],12).toString();
                    l.getTeam(team).removeByName(name);
                    model.removeRow(rows[i]);
                    updateFile();
                }
			}
            else{
                JOptionPane.showMessageDialog(this, "select a team first", 
											  "Error",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(e.getSource()==transfer){
            int rows[] = table.getSelectedRows();
			if(rows!=null){
				Arrays.sort(rows);
                for(int i= rows.length-1; i>=0;i--){
                    String name = table.getModel().getValueAt(rows[i],1).toString();
                    String currentTeam=table.getModel().getValueAt(rows[i],12).toString();
                    Player pla=l.getTeam(currentTeam).getPlayerByName(name);

                    // l.getTeam(team).removeByName(name);
                    // model.removeRow(rows[i]);
                    TransferPlayer t = new TransferPlayer(this, l, model, pla, rows[i],currentTeam);
                    updateFile();
                }
			}
        }
        else if(e.getSource()==updateBtn){
            updateFile();
            LoadPlayers();
        }
        else if(e.getSource()==loadBtn){

            System.out.println("load hitted");

            for(int i=0;i<model.getRowCount();i++){
                    model.removeRow(i);
                }
            int rows[] = table1.getSelectedRows();
            System.out.println("length: "+rows.length);

			if(rows!=null){
				Arrays.sort(rows);
                for(int i= rows.length-1; i>=0;i--){

                    String teamName = table1.getModel().getValueAt(rows[i],1).toString();

                    System.out.println(teamName);

                    //reading file
                    try {
                    Scanner sc = new Scanner(new File("./File/players.txt"));
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        String cols[] = line.split(";");
                        String playersTeam=cols[12];
                        
                        if(playersTeam.equals(teamName)){
                            System.out.println("Player found: " + cols[12]);
                            Player player = l.getTeam(cols[12]).getPlayerByName(cols[1]);
                            model.addRow(new Object[]{player.getId() , player.getName() , player.getAge() , player.getNation() , player.getSalary() ,player.getAgreement(),player.getJerseyNo(),player.getPosition(),player.getLeg(),player.getHeight(),player.getPreviousClub(),player.getGoal() , cols[12]});
                        }
                    }
                    sc.close();
                    } 
                    catch (FileNotFoundException e1) {
                        System.out.println("File not found: " + e1.getMessage());
                        System.out.println("file not found");
                    }
                }
            }
            
        }
    }
    
    public void LoadPlayers(){
        try {
            Scanner sc = new Scanner(new File("./File/players.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String cols[] = line.split(";");
                Player player;
                
                System.out.println("Player found: " + cols[1]);

                //id;name;age;nation;salary;agreement;jersey no;position;leg;height;prev club;goal;current team

                //String name,int age,String nation,double salary,Date joiningdate,double agreement,int jerseyNo,String position,String leg,double height,String prevClub,int goal,int id

                player=new Player(cols[1] , Integer.parseInt(cols[2]), cols[3],Double.parseDouble(cols[4]),new Date(1, 1, 2000), Double.parseDouble(cols[5]),Integer.parseInt(cols[6]),cols[7],cols[8],Double.parseDouble(cols[9]),cols[10],Integer.parseInt(cols[11]),Integer.parseInt(cols[0]));

				// System.out.println(l.getTeam(cols[12]).getTeamName());
                l.getTeam(cols[12]).insert(player);

                System.out.println(cols[1]+" Player Loaded");
				
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("file not found");
        }
    }
    
    public void createteamTable() { 
        model1 = new DefaultTableModel();
        model1.addColumn("ID");
        model1.addColumn("Name");
        
        table1 = new JTable(model1);
        table1.setFont(font);
		table1.getTableHeader().setFont(font);

		table1.setRowHeight(30);
		table1.setBackground(new Color(174,247,255));
		table1.setSelectionBackground(new Color(200, 200, 230));
        JScrollPane scrollpane = new JScrollPane(table1);
        scrollpane.setBounds(1230, 100, 200, 550);

        ArrayList <Team> teams=l.getAll();
                for (int j=0;j<teams.size();j++) {

                    System.out.println("adding row");
                    System.out.println(teams.get(j).getTeamId() +"team id");
                    model1.addRow(new Object[]{teams.get(j).getTeamId(),teams.get(j).getTeamName()});
                }
		
        panel.setLayout(null);
        panel.add(scrollpane);

        this.setContentPane(panel);
    } 

}