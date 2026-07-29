package src;

import javax.swing.JFrame;
public class TemperatureConverterGUI {

    JFrame frame;
    public TemperatureConverterGUI(){
        frame = new JFrame("Temperature Converter");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
