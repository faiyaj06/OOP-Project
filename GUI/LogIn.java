package GUI;
import javax.swing.*;

import EntityList.TeamList;
import Entity.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import File.*;
public class LogIn extends JFrame implements ActionListener{
    JLabel userNameLable, userPassLabel,textlabel,welcomeLable;
    JTextField userName;
    JPasswordField password;
    JButton btnLogin,registerBtn;
    JLabel background;
    ImageIcon image;
    
    TeamList l= new TeamList();
    Font font = new Font("cambria", Font.PLAIN, 30);
    Font f1 = new Font("Segoe UI", Font.BOLD, 60);

    public LogIn() {
        super("Login");
        this.setSize(900, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setIconImage(new ImageIcon("./Assets/football.png").getImage());

        loadTeams();

        //Welcome
        welcomeLable=new JLabel("Welcome");
        welcomeLable.setBounds(300,50,300,60);
        welcomeLable.setFont(f1);
        welcomeLable.setForeground(Color.WHITE);
        this.add(welcomeLable);

		//USER NAME Lable
        userNameLable = new JLabel("Username");
        userNameLable.setBounds(230, 180, 150, 50);
        userNameLable.setFont(font);
        userNameLable.setForeground(Color.WHITE);
        this.add(userNameLable);

		//USER NAME TextField
        userName = new JTextField();
        userName.setBounds(400, 180, 200, 50);
        userName.setFont(font);
        this.add(userName);
		
		//User Password Label 
        userPassLabel = new JLabel("Password");
        userPassLabel.setBounds(230, 250, 150, 50);
        userPassLabel.setFont(font);
        userPassLabel.setForeground(Color.WHITE);
        this.add(userPassLabel);

        /*textlabel = new JLabel("Don't have account?");
        textlabel.setBounds(30,190,220,25);
        textlabel.setFont(font);
        textlabel.setForeground(Color.WHITE);
        this.add(textlabel);*/

		//User Password Password Field
        password = new JPasswordField();
        password.setBounds(400, 250, 200, 50);
		password.setEchoChar('*');
        password.setFont(font);
        this.add(password);
        
        btnLogin = new JButton("Login");
        btnLogin.setBounds(230, 320, 170, 50);
        btnLogin.setFont(font);
        btnLogin.setBackground(Color.GRAY);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(this);
        this.add(btnLogin);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(440,320,170,50);
        registerBtn.setFont(font);
        registerBtn.setBackground(Color.GRAY);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.addActionListener(this);
        this.add(registerBtn);

        image = new ImageIcon("./Assets/footballlogin.jpg");
		background = new JLabel();
		background.setBounds(0,0,900,500);
		background.setIcon(image);
		this.add(background);
        
        LoadPlayers();
        LoadCoach();
        LoadOwner();
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (btnLogin == e.getSource()) {
            String name = userName.getText();
            String pass = String.valueOf(password.getPassword());
			int status = FileIO.checkUser(name,pass,"./File/users.txt");
            if (status == 1) {
				
				JOptionPane.showMessageDialog(this, "Login Successfull");
				
				Homepage hp = new Homepage(this,l);
				
                userName.setText("");
                password.setText("");
                this.setVisible(false);
            }
			else if(status == 2){
                JOptionPane.showMessageDialog(this, "Invalid Password", 
											  "Error",JOptionPane.WARNING_MESSAGE);
            }
			else{
                JOptionPane.showMessageDialog(this, "USER NOT REGISTERED", 
											  "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(registerBtn == e.getSource()){
            Registration rs = new Registration(this);   
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


                team=new Team(Integer.parseInt(cols[0]) , cols[1]);

                l.insert(team);
                System.out.println(cols[1]+" team Loaded");
				
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("file not found");
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
    
    public void LoadCoach(){
        try {
            Scanner sc = new Scanner(new File("./File/coach.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String cols[] = line.split(";");
                Coach coach;
                //0 id , 1 name, 2 age, 3 nation, 4 salary,  , 5 agreement, 6 experience,  7 achievements, 8 current team
                
                //public Coach(String name,int age,String nation,double salary,Date joiningDate,double agreement, int experience, String achievements, int id)

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