package src;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
    public SplashScreen(){
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel titleLabel = new JLabel("Temperature Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(titleLabel, gbc);
        gbc.gridy = 1;

        JLabel loadingLabel = new JLabel("Loading...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(loadingLabel, gbc);

        setContentPane(panel);
        setSize(450,220);
        setLocationRelativeTo(null);
        setVisible(true);

        Timer timer = new Timer(2000, e -> {
            dispose();
            new TemperatureConverterGUI();
        });

        timer.setRepeats(false);
        timer.start();

    }
}