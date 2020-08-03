package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
    /*
     New Login frame with different components
     */
    public static JFrame loginFrame;
    private JLabel titleloginFrame;
    private JLabel userName;
    private JTextField usernameloginFrame;
    private JLabel password;
    private JFileChooser fileChooser;
    private JButton select;
    private JLabel filePath;
    private JButton loginFrameButton;
    public static String nameLogin;
    public static String pathLogin;
    public static JLabel note;
    // Creating Login Frame
    public void login(){
        loginFrame = new JFrame();
        titleloginFrame = new JLabel("loginFrame");
        titleloginFrame.setBounds(500,20,300,25);
        titleloginFrame.setFont(new Font("Arial",Font.BOLD,25));
        userName = new JLabel("Enter Your Email");
        userName.setBounds(30,50,300,20);
        usernameloginFrame = new JTextField();
        usernameloginFrame.setBounds(30,120,400,30);
        password = new JLabel("Password");
        password.setBounds(30,150,300,25);
        select = new JButton("Select File");
        select.setBounds(30,200,400,35);
        filePath = new JLabel("No file Selected");
        filePath.setBounds(600,200,600,20);
        loginFrameButton = new JButton("Login");
        loginFrameButton.setBounds(500,500,150,25);
        note = new JLabel();
        note.setBounds(30,550,300,30);
        // Adding components to the login frame
        loginFrame.add(titleloginFrame);
        loginFrame.add(userName);
        loginFrame.add(usernameloginFrame);
        loginFrame.add(password);
        loginFrame.add(select);
        loginFrame.add(filePath);
        loginFrame.add(loginFrameButton);
        loginFrame.add(note);
        // Setting attributes for the login frame
        loginFrame.setLayout(null);
        loginFrame.setTitle("Login");
        loginFrame.getContentPane().setBackground(Color.WHITE);
        loginFrame.setSize(1200,1200);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(loginFrame.HIDE_ON_CLOSE);

    }
    // Calling Compare class
    Compare compare = new Compare();

    public void actionsButton(){
        select.addActionListener(this);
        loginFrameButton.addActionListener(this);
    }
// Performing events
    @Override
    public void actionPerformed(ActionEvent e) {
        // Select button to read the image from the user
        if (e.getSource() == select){
            fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setDialogTitle("Image Files Only");
            FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Images Only","jpg","png","jpeg");
            fileChooser.addChoosableFileFilter(fileNameExtensionFilter);
            int r = fileChooser.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION){
                filePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
                pathLogin = fileChooser.getSelectedFile().getAbsolutePath();
            } else {
                filePath.setText("Operation Cancelled");
            }
        }
        // login button if user details matched
        if (e.getSource() == loginFrameButton){
            nameLogin = usernameloginFrame.getText();
            pathLogin = pathLogin.replaceAll("\\\\","\\\\\\\\");
            compare.compareImage();
            if (compare.error == 0 && compare.percentage ==0.0 ) {
                note.setText("Success");
            }
            else {
                note.setText("Invalid mail or password");
            }
        }

        
        
    }
}
