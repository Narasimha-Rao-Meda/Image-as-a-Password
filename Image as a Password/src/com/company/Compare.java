package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.sql.*;

public class Compare {

    public static int error = 0;
    private Blob blob;
    public static double percentage;
    // Comparing the user password
    public void compareImage(){
        Login l = new Login();
        String mail = l.nameLogin;
        String path = l.pathLogin;
        try{
            // Getting the stored image from the database using the user mail
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp?characterEncoding=latin1", "root", "Mnrjr@14");
            PreparedStatement preparedStatement = connection.prepareStatement("select passwd from user_reg \n"+" where user_mail = '"+ mail+"' ");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
               blob = rs.getBlob(1);
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        // Comparing the database image with the current user selected image
        File file = new File(path);
        try{
            BufferedImage selectedImage = ImageIO.read(file);
            InputStream in= blob.getBinaryStream();
            BufferedImage loadImage = ImageIO.read(in);
            int width1 = selectedImage.getWidth();
            int height1 = selectedImage.getHeight();
            int width2 = loadImage.getWidth();
            int height2 = loadImage.getHeight();
            if (width1!=width2 || height1!=height2){
                error = 1;
            }
            else{
                 long difference = 0;
                 for (int j =0;j<height1;j++){
                     for (int i =0;i<width1;i++){
                          int pixel1 = selectedImage.getRGB(i,j);
                          Color color1 = new Color(pixel1, true);
                          int r1 = color1.getRed();
                          int g1 = color1.getGreen();
                          int b1 = color1.getBlue();
                          int pixel2 = loadImage.getRGB(i,j);
                          Color color2 = new Color(pixel2, true);
                          int r2 = color2.getRed();
                          int g2 = color2.getGreen();
                          int b2 = color2.getBlue();
                          long data = Math.abs(r1-r2)+Math.abs(g1-g2)+Math.abs(b1-b2);
                          difference += data;
                     }
                 }
                 double avg = difference/(width1*height1*3);
                 percentage = (avg/255)*100;

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
