package src;

import javax.swing.*;
import java.awt.*;
public class TemperatureConverterGUI {

    JFrame frame;

    JLabel temperatureLabel;
    JTextField temperatureField;

    JLabel fromLabel;
    JComboBox<String> fromComboBox;

    JLabel toJLabel;
    JComboBox<String> toComboBox;

    JButton convertButton;
    JButton swapButton;
    JButton clearButton;

    JLabel resultJLabel;

    JButton whiteButton;
    JButton darkButton;
    JButton brownButton;

    JTextArea historyArea;

    public TemperatureConverterGUI(){
        frame = new JFrame("Temperature Converter");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
       

        temperatureLabel = new JLabel("Temperature:");
        temperatureField = new JTextField(15);

        fromLabel = new JLabel("Convert From:");
        fromComboBox =new JComboBox<>();

        fromComboBox.addItem("Celsius");
        fromComboBox.addItem("Fahrenheit");
        fromComboBox.addItem("Kelvin");

        toJLabel = new JLabel("Convert to:");
        toComboBox = new JComboBox<>();

        toComboBox.addItem("Celsius");
        toComboBox.addItem("Fahrenheit");
        toComboBox.addItem("Kelvin");

        convertButton = new JButton("Convert");
        swapButton = new JButton("Swap");
        clearButton = new JButton("Clear");

        resultJLabel = new JLabel("Result: ");

        whiteButton = new JButton("White");
        darkButton = new JButton("Dark");
        brownButton = new JButton("Brown");

        historyArea = new JTextArea(10,30);
        historyArea.setEditable(false);

        frame.add(temperatureLabel);
        frame.add(temperatureField);

        frame.add(fromLabel);
        frame.add(fromComboBox);

        frame.add(toJLabel);
        frame.add(toComboBox);

        frame.add(convertButton);
        frame.add(swapButton);
        frame.add(clearButton);
        
        frame.add(resultJLabel);

        frame.add(whiteButton);
        frame.add(darkButton);
        frame.add(brownButton);

        frame.add(new JScrollPane(historyArea));

         frame.setVisible(true);
    }
    
}
