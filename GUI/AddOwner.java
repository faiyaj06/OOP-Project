package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entity.Date;
import Entity.Owner;
import EntityList.TeamList;
import File.FileIO;

public class AddOwner extends JFrame implements ActionListener {
    JPanel panel;
    Font font = new Font("Segoe UI", 1, 20);
    Font f1 = new Font("Segoe UI", Font.BOLD, 35);

    JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,fillLabel;
    JTextField name,age,nation,salary,agreement,investment,id,currentclub;

    JButton addBtn,cancelBtn;

    ImageIcon image;
    JLabel background;

    ManageOwner m;
    TeamList l;
    DefaultTableModel model;
    FileIO fIo=new FileIO();

    public AddOwner(ManageOwner m,TeamList l,DefaultTableModel model){
        super("Add Owner");
        this.m = m;
        this.l = l;
        this.model=model;
        this.setSize(700, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setIconImage(new ImageIcon("./Assets/football.png").getImage());

        //Label
        fillLabel=new JLabel("Enter Owner Information");
        fillLabel.setBounds(120,50,500,60);
        fillLabel.setFont(f1);
        fillLabel.setForeground(Color.WHITE);
        this.add(fillLabel);

        jLabel1 = new JLabel("Name");
        jLabel1.setBounds(100,120,70,30);
        jLabel1.setFont(font);
        jLabel1.setForeground(Color.WHITE);
        this.add(jLabel1);
 
        jLabel2 = new JLabel("Age");
        jLabel2.setBounds(100,165,70,30);
        jLabel2.setFont(font);
        jLabel2.setForeground(Color.WHITE);
        this.add(jLabel2);
 
        jLabel3 = new JLabel("Nation");
        jLabel3.setBounds(100, 210, 100, 30);
        jLabel3.setFont(font);
        jLabel3.setForeground(Color.WHITE);
        this.add(jLabel3);
 
        jLabel4 = new JLabel("Salary");
        jLabel4.setBounds(100, 255, 100, 30);
        jLabel4.setFont(font);
        jLabel4.setForeground(Color.WHITE);
        this.add(jLabel4);
 
        jLabel5 = new JLabel("Agreement");
        jLabel5.setBounds(100, 300, 100, 40);
        jLabel5.setFont(font);
        jLabel5.setForeground(Color.WHITE);
        this.add(jLabel5);

        jLabel6 = new JLabel("Invesment");
        jLabel6.setBounds(100, 345, 120, 30);
        jLabel6.setFont(font);
        jLabel6.setForeground(Color.WHITE);
        this.add(jLabel6);

        jLabel7 = new JLabel("ID");
        jLabel7.setBounds(100, 390, 120, 30);
        jLabel7.setFont(font);
        jLabel7.setForeground(Color.WHITE);
        this.add(jLabel7);

        jLabel8 = new JLabel("CurrentClub");
        jLabel8.setBounds(100, 435, 120, 30);
        jLabel8.setFont(font);
        jLabel8.setForeground(Color.WHITE);
        this.add(jLabel8);

        //Textfield
        name = new JTextField();
        name.setBounds(225, 120, 305, 30);
        this.add(name);

        age = new JTextField();
        age.setBounds(225, 165, 305, 30);
        this.add(age);

        nation = new JTextField();
        nation.setBounds(225, 210, 305, 30);
        this.add(nation);

        salary = new JTextField();
        salary.setBounds(225, 255, 305, 30);
        this.add(salary);

        agreement = new JTextField();
        agreement.setBounds(225, 300, 305, 30);
        this.add(agreement);

        investment = new JTextField();
        investment.setBounds(225, 345, 305, 30);
        this.add(investment);

        id = new JTextField();
        id.setBounds(225, 390, 305, 30);
        this.add(id);

        currentclub = new JTextField();
        currentclub.setBounds(225, 435, 305, 30);
        this.add(currentclub);

        //Btn
        addBtn = new JButton("Add Owner");
        addBtn.setBounds(230, 600, 180, 60);
        addBtn.setFont(font);
        addBtn.setBackground(Color.GREEN);
        addBtn.addActionListener(this);
        this.add(addBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(10, 10, 100, 40);
        cancelBtn.setFont(font);
        cancelBtn.setBackground(Color.ORANGE);
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        panel = new JPanel();
        this.add(panel);

        image = new ImageIcon("./Assets/playeradd.jpg");
        background = new JLabel();
        background.setBounds(0,0,700,800);
        background.setIcon(image);
        this.add(background);

        this.setVisible(true);

    }
    
    public void actionPerformed(ActionEvent e) {
        //String name,int age,String nation,double salary,Date joiningDate,double agreement, double investment,int id

        if(e.getSource()==addBtn){
            Owner o = new Owner(name.getText(),Integer.parseInt(age.getText()),nation.getText(),Double.parseDouble(salary.getText()),new Date(1, 1, 2000),Double.parseDouble(agreement.getText()),Double.parseDouble(investment.getText()),Integer.parseInt(id.getText()));

            l.getTeam(currentclub.getText()).insertOwner(o);
            add();
        }
        else if(e.getSource()==cancelBtn){
            this.setVisible(false);
            m.setVisible(true);
        }

    }
    
    void add(){
        //String name,int age,String nation,double salary, ,double agreement, double investment,int id

        model.addRow(new Object[]{id.getText(),  name.getText(),  age.getText(),  nation.getText(),  salary.getText(),  agreement.getText(),investment.getText(), currentclub.getText()});
    
        String string="";
        string=string+id.getText()+";"+name.getText()+";"+  age.getText()+";"+  nation.getText()+";"+  salary.getText()+";"+   agreement.getText()+";"+  investment.getText()+";"+currentclub.getText();
        
        fIo.writeInFile(string,"./File/owner.txt",true);
    
        this.setVisible(false);
        m.setVisible(true);
        }
    
}