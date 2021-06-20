package utils;

import javax.swing.*;

public class ShowMessage {

    public static void main(String[] args) {
        error("qwe");
    }

    public static void error(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void success(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
