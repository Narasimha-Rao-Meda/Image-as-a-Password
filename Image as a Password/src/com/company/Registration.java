package com.company;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Registration implements ActionListener {
       /*
       New Jframe variable with different component variables
       (JLabel, JButton, JTextField, JFilechooser)
        */
       public static JFrame jFrame;
       private JLabel title;
       private JLabel userNameLabel;
       private JTextField userNameMail;
       private JLabel firstName;
       private JTextField firstNameTextField;
       private JTextField lastNameTextField;
       private JLabel dateOfBirth;
       private JLabel lastName;
       private JTextField datePicker;
       private JLabel mobileNumber;
       private JTextField number;
       private JLabel password;
       private JFileChooser fileChooser;
       private JButton open;
       private JButton save;
       private JLabel select;
       private JButton signup;
       private JLabel note;
       private String path;

       //public variables to store the user details in database

       public static String maildb;
       public static String firstNamedb;
       public static String lastNamedb;
       public static String dobdb;
       public static String mobileNumberdb;
       public static String pathdb;
       /*
       Creating Registration frame
        */
       public void RegistrationPanel() {
              jFrame = new JFrame();
              title = new JLabel("Registration");
              title.setBounds(500, 20, 300, 25);
              title.setFont(new Font("Arial", Font.BOLD, 25));
              userNameLabel = new JLabel("Enter Your Email");
              userNameLabel.setBounds(30, 50, 300, 20);
              userNameMail = new JTextField();
              userNameMail.setBounds(30, 80, 400, 30);
              userNameMail.setEditable(true);
              firstName = new JLabel("First Name");
              firstName.setBounds(30, 110, 300, 20);
              firstNameTextField = new JTextField();
              firstNameTextField.setBounds(30, 140, 400, 30);
              lastName = new JLabel("Last Name");
              lastName.setBounds(30, 170, 300, 20);
              lastNameTextField = new JTextField();
              lastNameTextField.setBounds(30, 200, 400, 30);
              dateOfBirth = new JLabel("Date of Birth");
              dateOfBirth.setBounds(30, 230, 300, 20);
              datePicker = new JTextField();
              datePicker.setBounds(30, 260, 400, 30);
              datePicker.setToolTipText("day/month/year");
              mobileNumber = new JLabel("Mobile Number");
              mobileNumber.setBounds(30, 290, 300, 20);
              number = new JTextField();
              number.setBounds(30, 320, 400, 30);
              password = new JLabel("Password");
              password.setBounds(30, 350, 300, 20);
              open = new JButton("Open");
              open.setBounds(30,380,150,35);
              save = new JButton("Save");
              save.setBounds(200,380,150,35);
              select = new JLabel("No file selected");
              select.setBounds(400,380,600,20);
              signup = new JButton("SignUp");
              signup.setBounds(500, 640, 150, 25);
              note = new JLabel();
              note.setBounds(30,650,300,20);
             // Adding components to the Jframe
              jFrame.add(title);
              jFrame.add(userNameLabel);
              jFrame.add(userNameMail);
              jFrame.add(firstName);
              jFrame.add(firstNameTextField);
              jFrame.add(lastName);
              jFrame.add(lastNameTextField);
              jFrame.add(dateOfBirth);
              jFrame.add(datePicker);
              jFrame.add(mobileNumber);
              jFrame.add(number);
              jFrame.add(password);
              jFrame.add(open);
              jFrame.add(save);
              jFrame.add(select);
              jFrame.add(signup);
              jFrame.add(note);
              // Setting different attributes to the Registration frame
              jFrame.setSize(1200, 1200);
              jFrame.getContentPane().setBackground(Color.WHITE);
              jFrame.setLayout(null);
              jFrame.setVisible(true);
              jFrame.setTitle("Registration");
              jFrame.setDefaultCloseOperation(jFrame.HIDE_ON_CLOSE);
       }
       // Calling Login class
       Login l = new Login();
       // Calling Connectiondb class
       Connectiondb con = new Connectiondb();
      // Setting the actions when user clicks on the different buttons
       public void actions() {
              signup.addActionListener(this);
              open.addActionListener(this);
              save.addActionListener(this);
       }
       // Different actions performed on various buttons

       @Override
       public void actionPerformed(ActionEvent e) {
              // Save button to select an image as a password
              if(e.getSource() == save){
                     fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                     fileChooser.setAcceptAllFileFilterUsed(false);
                     fileChooser.setDialogTitle("Image Files Only");
                     FileNameExtensionFilter filter = new FileNameExtensionFilter("Images only","jpg","png","jpeg");
                     fileChooser.addChoosableFileFilter(filter);
                     int r = fileChooser.showSaveDialog(null);
                     if (r == JFileChooser.APPROVE_OPTION){
                            select.setText(fileChooser.getSelectedFile().getAbsolutePath());
                            path = fileChooser.getSelectedFile().getAbsolutePath();
                     }
                     else{
                            select.setText("Opertion Cancelled");
                     }
              }
              // Open button also used to select an image file
              if (e.getSource()==open){
                     fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                     fileChooser.setAcceptAllFileFilterUsed(false);
                     fileChooser.setDialogTitle("Image Files Only");
                     FileNameExtensionFilter filter = new FileNameExtensionFilter("Images only","jpg","png","jpeg");
                     fileChooser.addChoosableFileFilter(filter);
                     int r = fileChooser.showSaveDialog(null);
                     if (r == JFileChooser.APPROVE_OPTION){
                            select.setText(fileChooser.getSelectedFile().getAbsolutePath());
                            path = fileChooser.getSelectedFile().getAbsolutePath();
                     }
                     else{
                            select.setText("Operation Cancelled");
                     }

              }
              /* signup button is used to register the user on first time.
                 When clicking on signup button the user details stored into the mysql user_reg database.
                 If the usre enterd details matches with signup creditinals new frame will popup as login frame
               */
              if (e.getSource() == signup) {
                     String userName = userNameMail.getText();
                     String firstName = firstNameTextField.getText();
                     String lastName = lastNameTextField.getText();
                     String dob = datePicker.getText();
                     String phnumber = number.getText();
                     int count = 0;
                     if (userName.length() > 0 && firstName.length() > 0 && lastName.length() > 0 && dob.length() >= 10 && phnumber.length() >= 10 && path.length()>0) {
                            count++;
                     }
                     maildb = userName;
                     firstNamedb = firstName;
                     lastNamedb = lastName;
                     dobdb = dob;
                     mobileNumberdb = phnumber;
                     pathdb = path;
                     pathdb = pathdb.replaceAll("\\\\","\\\\\\\\");
                     if (count == 1) {
                            jFrame.dispose();
                            con.connection();
                            l.login();
                            l.actionsButton();
                     }
                     else {
                             note.setText("Try Again");
                     }
              }
       }

}