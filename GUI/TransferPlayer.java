package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import Entity.*;
import EntityList.*;
import File.FileIO;

import java.util.ArrayList;

public class TransferPlayer extends JFrame implements ActionListener{
    JTextField name;
    JPanel panel;
    JLabel namelabel;
    JButton trans,exitBtn;
    TeamList l;
    FileIO fIo;
    ManagePlayer m;
    int row;
    DefaultTableModel model;
    Player p;
    String currentTeam;
    public TransferPlayer(ManagePlayer mp,TeamList l,DefaultTableModel model,Player p, int row, String currentTeam) {
        super("Transfer Player");
        this.l=l;
        this.m=mp;
        this.model = model;
        this.p=p;
        this.row=row;
        this.currentTeam=currentTeam;
        this.row=row;

        fIo = new FileIO();
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        name=new JTextField();
        panel = new JPanel();
        panel.setLayout(null);
		panel.setSize(400,400);
		panel.setBackground(new Color(200,200,200));

        namelabel = new JLabel("Enter Team name");
        namelabel.setBounds(30,50,250,40);
        this.add(namelabel);
        
        //text field
        name.setBounds(150,50,200,40);
        panel.add(name);
        this.add(panel);

        //Btn
        trans = new JButton("Transfer");
        trans.setBounds(170,110,150,40);
        trans.addActionListener(this);
        trans.setLayout(null);
        
        exitBtn=new JButton("Cancell");
        exitBtn.setBounds(170,160,150,40);
        exitBtn.addActionListener(this);
        exitBtn.setLayout(null);

        panel.add(trans);
        panel.add(exitBtn);
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==trans){
            ArrayList <Team> teams=l.getAll();
            int flag=0;
            for (int i=0;i<teams.size();i++) {
                if(name.getText().equals(teams.get(i).getTeamName())){
                    System.out.println("team matched");
                    flag++;
                    break;
                }
                else{
                    System.out.println("team did not match");
                }
            }
            if(flag!=0){
                transfer();
            }
            else{
                JOptionPane.showMessageDialog(this, "Team Does not exist", 
											  "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource()==exitBtn){
            this.setVisible(false);
            m.setVisible(true);
            m.createTable();
        }
    }

//id;name;age;nation;salary;agreement;jersey no;position;leg;height;prev club;goal;current team

    void transfer(){
        l.getTeam(name.getText()).insert(p);
        l.getTeam(currentTeam).removeByName(p.getName());
        model.removeRow(row);

        model.addRow(new Object[]{p.getId(),p.getName(),p.getAge(),p.getNation(),p.getSalary(),p.getAgreement(),p.getJerseyNo(),p.getPosition(),p.getLeg(),p.getHeight(),currentTeam,p.getGoal(),name.getText()});

        String line= p.getId()+";"+   p.getName()+";"+   p.getAge()+";"+   p.getNation()+";"+   p.getSalary()+";"+   p.getAgreement()+";"+   p.getJerseyNo()+";"+             p.getPosition()+";"+   p.getLeg()+";"+   p.getHeight()+";"+  currentTeam+";"+   p.getGoal()+";"+name.getText();

        p.setPreviousClub(currentTeam);

        fIo.writeInFile(line,"./File/players.txt",true);

        line="";

        m.updateFile();
        
        this.setVisible(false);
        m.setVisible(true);
    }
}