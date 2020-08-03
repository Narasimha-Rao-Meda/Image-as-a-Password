package com.company;


public class Main {
    public static void main(String[] args) {
        //Calling  Registration class
        Registration r = new Registration();
        // Calling Registration Frame
        r.RegistrationPanel();
        if (r.jFrame !=null && r.jFrame.isVisible()){
            r.actions();
        }
        else {
            System.out.println("Error");
        }
    }
}
