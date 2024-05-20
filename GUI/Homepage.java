package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import EntityList.*;

public class Homepage extends JFrame implements ActionListener{
	JPanel panel;
	JButton manageteamBtn,manageplayerBtn,managecoachBtn,manageownerBtn,logoutBtn;

 	Font bfont = new Font("arial",Font.BOLD,25);
	LogIn logIn;
	TeamList l;
	
	ImageIcon image;
	JLabel background;

	public Homepage(LogIn logIn,TeamList l){
		super("Home Page");
		this.logIn = logIn;
		this.l=l;
		
		image = new ImageIcon("./Assets/home.jpg");
		panel = new JPanel();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000,600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);

		this.setIconImage(new ImageIcon("./Assets/football.png").getImage());

		panel.setLayout(null);
		panel.setSize(1000,600);
		//panel.setBackground(new Color(200,200,200));
		panel.setOpaque(false);
		
		
		
        background = new JLabel();
        background.setBounds(0,0,1000,600);
		//============================//
		manageteamBtn = new JButton("Manage Team");
		manageteamBtn.setBounds(200,225,250,95);
		manageteamBtn.setFont(bfont);
		manageteamBtn.setBackground(Color.GRAY);
		manageteamBtn.setForeground(Color.WHITE);
		manageteamBtn.addActionListener(this);
		this.add(manageteamBtn);

		manageplayerBtn = new JButton("Manage Player");
		manageplayerBtn.setBounds(200,345,250,95);
		manageplayerBtn.setFont(bfont);
		manageplayerBtn.setBackground(Color.GRAY);
		manageplayerBtn.setForeground(Color.WHITE);
		manageplayerBtn.addActionListener(this);
		this.add(manageplayerBtn);

		managecoachBtn = new JButton("Manage Coach");
		managecoachBtn.setBounds(475,225,250,95);
		managecoachBtn.setFont(bfont);
		managecoachBtn.setBackground(Color.GRAY);
		managecoachBtn.setForeground(Color.WHITE);
		managecoachBtn.addActionListener(this);
		this.add(managecoachBtn);

		manageownerBtn = new JButton("Manage Owner");
		manageownerBtn.setBounds(475,345,250,95);
		manageownerBtn.setFont(bfont);
		manageownerBtn.setBackground(Color.GRAY);
		manageownerBtn.setForeground(Color.WHITE);
		manageownerBtn.addActionListener(this);
		this.add(manageownerBtn);


		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(800,10,170,40);
		logoutBtn.setFont(bfont);
		logoutBtn.setBackground(Color.RED);
		logoutBtn.setForeground(Color.WHITE);
		logoutBtn.addActionListener(this);
		this.add(logoutBtn);

		//============================//
		this.setTitle("FootBall Club Management System");
		background.setIcon(image);
		
        panel.add(background);	
		this.add(panel);
		this.setVisible(true);
	}

        public void actionPerformed(ActionEvent e){
			if(e.getSource()==logoutBtn){
				this.setVisible(false);
				LogIn l= new LogIn();
			}
            else if(e.getSource()==manageteamBtn){
              //  Manageteam mt = new Manageteam();
                this.setVisible(false);
				Manageteam m = new Manageteam(this,l);
            }
			
			else if(manageplayerBtn==e.getSource()){
				this.setVisible(false);
				ManagePlayer p = new ManagePlayer(this,l);
			}
			else if(managecoachBtn==e.getSource()){
				this.setVisible(false);
				ManageCoach p = new ManageCoach(this,l);
			}
			else if(manageownerBtn==e.getSource()){
				this.setVisible(false);
				ManageOwner p = new ManageOwner(this,l);
			}
			
        }
	}
