package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import File.FileIO;

public class Registration extends JFrame implements ActionListener {
    JLabel namelabel,passlabel,inforLabel;
    JTextField name;
    JPasswordField pass;
    JButton RegisterBtn,cancelBtn;
    LogIn lg;
    ImageIcon image;
    JLabel background;
    Font font = new Font("cambria", Font.PLAIN, 30);
    Font f1 = new Font("Segoe UI", Font.BOLD, 40);
    public Registration(LogIn lg){
        super("Registration page");
        this.lg = lg;
        this.setSize(900, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setIconImage(new ImageIcon("./Assets/football.png").getImage());

        //Label
        inforLabel=new JLabel("Enter Information");
        inforLabel.setBounds(300,50,400,60);
        inforLabel.setFont(f1);
        inforLabel.setForeground(Color.WHITE);
        this.add(inforLabel);

        namelabel = new JLabel("Enter username");
        namelabel.setBounds(200, 180, 230, 50);
        namelabel.setFont(font);
        namelabel.setForeground(Color.WHITE);
        this.add(namelabel);

        passlabel = new JLabel("Enter password");
        passlabel.setBounds(200, 250, 230, 50);
        passlabel.setFont(font);
        passlabel.setForeground(Color.WHITE);
        this.add(passlabel);

        //textfield
        name = new JTextField();
        name.setBounds(430, 180, 270, 50);
        name.setFont(font);
        this.add(name);

        pass = new JPasswordField();
        pass.setBounds(430, 250, 270, 50);
        pass.setFont(font);
        this.add(pass);

        //btn
        RegisterBtn = new JButton("Register");
        RegisterBtn.setBounds(350, 320, 170, 50);
        RegisterBtn.setFont(font);
        RegisterBtn.setBackground(Color.GRAY);
        RegisterBtn.setForeground(Color.WHITE);
        RegisterBtn.addActionListener(this);
        this.add(RegisterBtn);

        cancelBtn = new JButton("Back");
        cancelBtn.setBounds(10, 10, 100, 35);
        cancelBtn.setFont(font);
        cancelBtn.setBackground(Color.GRAY);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(this);
        this.add(cancelBtn); 
        
        image = new ImageIcon("./Assets/registration2.jpeg");
		background = new JLabel();
		background.setBounds(0,0,900,500);
		background.setIcon(image);
		this.add(background);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(RegisterBtn == e.getSource()){
                String username = name.getText();
                String password = String.valueOf(pass.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    // Show an error message if either field is empty
                    JOptionPane.showMessageDialog(this, "Username and password cannot be empty.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                FileIO.registerUser(name.getText(), String.valueOf(pass.getPassword()), "./File/users.txt");
                JOptionPane.showMessageDialog(this, "Registration successfull");
                this.setVisible(false);
                }
        }
        else if(e.getSource()==cancelBtn){
            this.setVisible(false);
        }
    }
}