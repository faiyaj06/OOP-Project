package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import Entity.*;
import EntityList.*;
import File.FileIO;

import java.util.ArrayList;

public class AddTeam extends JFrame implements ActionListener{
    JTextField name, id;
    JPanel panel;
    JLabel namelabel,idlabel,inforLabel;
    JButton addBtn,exitBtn;

    ImageIcon image;
    JLabel background;
    Font font = new Font("Segoe UI", Font.BOLD, 25);
    Font f1 = new Font("Segoe UI", Font.BOLD, 40);

    TeamList l;
    FileIO fIo;
    Manageteam m;
    DefaultTableModel model;
    public AddTeam(Manageteam m,TeamList l,DefaultTableModel model) {
        super("Team Management");
        this.l=l;
        this.m=m;
        this.model = model;
        fIo = new FileIO();
        this.setSize(900, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setIconImage(new ImageIcon("./Assets/football.png").getImage());

        inforLabel=new JLabel("ADD TEAM");
        inforLabel.setBounds(330,50,400,60);
        inforLabel.setFont(f1);
        inforLabel.setForeground(Color.WHITE);
        this.add(inforLabel);

        name=new JTextField();
        id=new JTextField();
        panel = new JPanel();
        panel.setLayout(null);
		panel.setSize(900,500);
		panel.setBackground(new Color(200,200,200));

        namelabel = new JLabel("Enter Team name");
        namelabel.setBounds(200, 180, 235, 70);
        namelabel.setFont(font);
        namelabel.setForeground(Color.WHITE);
        this.add(namelabel);

        idlabel = new JLabel("Enter ID");
        idlabel.setBounds(200, 250, 235, 70);
        idlabel.setFont(font);
        idlabel.setForeground(Color.WHITE);
        this.add(idlabel);
        
        //text field
        name.setBounds(430, 180, 270, 50);
        id.setBounds(430, 250, 270, 50);
        panel.add(name);
        panel.add(id);
        this.add(panel);

        //Btn
        addBtn = new JButton("Create Team");
        addBtn.setBounds(350, 320, 170, 50);
        addBtn.setBackground(Color.GREEN);
        addBtn.addActionListener(this);
        addBtn.setLayout(null);
        
        exitBtn=new JButton("Back");
        exitBtn.setBounds(10, 10, 100, 35);
        exitBtn.setBackground(Color.RED);
        exitBtn.addActionListener(this);
        exitBtn.setLayout(null);

        panel.add(addBtn);
        panel.add(exitBtn);

        image = new ImageIcon("./Assets/registration2.jpeg");
        background = new JLabel();
        background.setBounds(0,0,900,500);
        background.setIcon(image);
        panel.add(background);


        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==addBtn){

            ArrayList<Team> allTeamss = l.getAll();

            int flag=0;
            for (int i=0;i<allTeamss.size();i++) {
                if(name.getText().equals(allTeamss.get(i).getTeamName())){
                    System.out.println("team matched");
                    flag++;
                }
                else{
                    System.out.println("team did not match");
                }
            }
            if(flag==0){
                add();
            }
            else{
                JOptionPane.showMessageDialog(this, "Team already exists", 
											  "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource()==exitBtn){
            this.setVisible(false);
            m.setVisible(true);
            m.createTable();
        }
    }
    
    void add(){
        l.insert(new Team( Integer.parseInt(id.getText()) , name.getText()));
        model.addRow(new Object[]{id.getText(), name.getText()});
        fIo.writeInFile(id.getText()+";"+name.getText(),"./File/teams.txt",true);
        this.setVisible(false);
        m.setVisible(true);
    }
}