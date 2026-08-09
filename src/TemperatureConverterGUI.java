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
    JLabel categoryLabel;

    JButton whiteButton;
    JButton darkButton;
    JButton darkseagreenButton;

    JTextArea historyArea;
    TemperatureConverter converter;
    HistoryManager historyManager;

    public TemperatureConverterGUI(){
        frame = new JFrame("Temperature Converter");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
      
        converter = new TemperatureConverter();
        historyManager = new HistoryManager();

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
        categoryLabel = new JLabel("Category: ");

        whiteButton = new JButton("White");
        darkButton = new JButton("Dark");
        darkseagreenButton = new JButton("Green");

        historyArea = new JTextArea(10,30);
        historyArea.setEditable(false);
        historyArea.setText(historyManager.getHistoryAsText());

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
        frame.add(categoryLabel);

        frame.add(whiteButton);
        frame.add(darkButton);
        frame.add(darkseagreenButton);

        frame.add(new JScrollPane(historyArea));

//Implementation of Convert Button
//-----------------------------------
        convertButton.addActionListener(e -> {

            String inputText = temperatureField.getText();

            if(!Utils.isValidNumber(inputText)){
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
            }

            try{
               double temperature = Double.parseDouble(temperatureField.getText());

               String from = (String) fromComboBox.getSelectedItem();
               String to = (String) toComboBox.getSelectedItem();

               double result = 0;

               if(from.equals("Celsius") && to.equals("Fahrenheit")){
                result = converter.celsiusToFahrenheit(temperature);
               }

               else if(from.equals("Celsius") && to.equals("Kelvin")){
                result = converter.celsiusToKelvin(temperature);
               }

               else if(from.equals("Fahrenheit") && to.equals("Celsius")){
                result = converter.fahrenheitToCelsius(temperature);
               }

               else if(from.equals("Fahrenheit") && to.equals("Kelvin")){
                result = converter.fahrenheitToKelvin(temperature);
               }

               else if(from.equals("Kelvin") && to.equals("Celsius")){
                result = converter.kelvinToCelsius(temperature);
               }

               else if(from.equals("Kelvin") && to.equals("Fahrenheit")){
                result = converter.kelvinToFahrenheit(temperature);
               }
               
               else{
                result = temperature;
               }

               result = Utils.round(result, 2);
               resultJLabel.setText("Result : " + String.format("%.2f", result));

               double celsiusTemperature;

               if(from.equals("Celsius")){
                celsiusTemperature = temperature;
               }
               else if(from.equals("Fahrenheit")){
                celsiusTemperature = converter.fahrenheitToCelsius(temperature);
               }
               else{
                celsiusTemperature = converter.kelvinToCelsius(temperature);
               }

               String category = Utils.getTemperatureCategory(celsiusTemperature);
               categoryLabel.setText("Category: " + category);

            historyManager.addEntry(from, to, temperature, result);
            historyArea.setText(historyManager.getHistoryAsText());
            }

            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
            }
        });


//Implementation of Swap Button
//-----------------------------------
        swapButton.addActionListener(e -> {
            int fromIndex = fromComboBox.getSelectedIndex();
            int toIndex = toComboBox.getSelectedIndex();

            fromComboBox.setSelectedIndex(toIndex);
            toComboBox.setSelectedIndex(fromIndex);
        });


//Implementation of Clear Button
//-----------------------------------
        clearButton.addActionListener(e -> {
            temperatureField.setText("");
            resultJLabel.setText("Result: ");
            categoryLabel.setText("Category: ");
        });


//Implementation of Theme Button
//-----------------------------------
        whiteButton.addActionListener(e ->{
            frame.getContentPane().setBackground(Color.WHITE);
        });

        darkButton.addActionListener(e ->{
           frame.getContentPane().setBackground(new Color(105,105,105));
           //frame.getContentPane().setBackground(Color.BLACK);
        });

        darkseagreenButton.addActionListener(e ->{
            frame.getContentPane().setBackground(new Color(143,188,143));
        });

      

        frame.setVisible(true);
    }
    
}
