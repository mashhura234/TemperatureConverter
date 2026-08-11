package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class TemperatureConverterGUI {

    JFrame frame;

    JTextField temperatureField;

    JComboBox<String> fromComboBox;
    JComboBox<String> toComboBox;

    JButton convertButton;
    JButton swapButton;
    JButton clearButton;
    JButton clearHistoryButton;

    JButton whiteButton;
    JButton darkButton;
    JButton greenButton;

    JLabel resultLabel;
    JLabel categoryLabel;

    JLabel fromUnitBadge;
    JLabel toUnitBadge;

    JList<String> historyList;
    DefaultListModel<String> historyModel;
    HistoryCellRenderer historyRenderer;

    String currentTheme = "White";

    TemperatureConverter converter;
    HistoryManager historyManager;


    // COLORS
    Color red = new Color(255, 82, 82);
    Color dark = new Color(70, 78, 92);
    Color background = new Color(250, 245, 245);
    Color textColor = new Color(55, 55, 55);


    // FONTS
    Font normalFont = new Font("Segoe UI", Font.PLAIN, 16);
    Font boldFont = new Font("Segoe UI", Font.BOLD, 16);
    Font titleFont = new Font("Georgia", Font.BOLD | Font.ITALIC, 36);

    public TemperatureConverterGUI() {

        converter = new TemperatureConverter();
        historyManager = new HistoryManager();

        frame = new JFrame("🌡 Temperature Converter");
        frame.setSize(600, 830);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(background);
        frame.setLayout(new BorderLayout());


        // TITLE
        GradientTitleLabel title = new GradientTitleLabel( "Temperature Converter");
        title.setGradientColors(red, new Color(190, 60, 110));
        title.setBorder( new EmptyBorder(25, 10, 20, 10));
       
        frame.add(title, BorderLayout.NORTH );


        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout( new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(0, 45, 10, 45));


        // CONVERTER CARD
        RoundedPanel converterCard = new RoundedPanel(22);
        converterCard.setBackground(Color.WHITE);
        converterCard.setLayout(new BoxLayout(converterCard, BoxLayout.Y_AXIS));

        converterCard.setPreferredSize(new Dimension(510, 500));
        converterCard.setMaximumSize(new Dimension(510, 500));


        // FROM PANEL
        JPanel fromPanel = new JPanel();
        fromPanel.setBackground(red);
        fromPanel.setLayout(new BoxLayout(fromPanel, BoxLayout.Y_AXIS) );

        fromPanel.setBorder( new EmptyBorder(18,40, 15,40));
        fromPanel.setPreferredSize(new Dimension(510, 230));
        fromPanel.setMaximumSize( new Dimension(510, 230));


        JLabel fromLabel = new JLabel("FROM", SwingConstants.CENTER);
        fromLabel.setAlignmentX( Component.CENTER_ALIGNMENT);
        fromLabel.setFont( new Font( "Segoe UI", Font.BOLD,18) );

        fromLabel.setForeground(Color.WHITE);
        fromPanel.add(fromLabel);
        fromPanel.add(Box.createVerticalStrut(8));


        // FROM COMBO BOX
        fromComboBox = new JComboBox<>();
        fromComboBox.addItem("Celsius");

        fromComboBox.addItem("Fahrenheit");
        fromComboBox.addItem("Kelvin");

        fromComboBox.setFont(normalFont);
        fromComboBox.setMaximumSize(new Dimension(250, 42));

        fromComboBox.setAlignmentX( Component.CENTER_ALIGNMENT);
        fromPanel.add(fromComboBox);
        fromPanel.add( Box.createVerticalStrut(12));


        // TEMPERATURE FIELD
        temperatureField = new JTextField();
        temperatureField.setHorizontalAlignment(SwingConstants.CENTER);
        temperatureField.setFont(new Font( "Segoe UI", Font.PLAIN, 42));

        temperatureField.setForeground(Color.WHITE);
        temperatureField.setCaretColor(Color.WHITE);
        temperatureField.setOpaque(false);

        temperatureField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        temperatureField.setPreferredSize(new Dimension(180, 65));
        temperatureField.setMaximumSize(new Dimension(300, 65));

        temperatureField.setAlignmentX( Component.CENTER_ALIGNMENT);
        fromUnitBadge = new JLabel("°C");
        fromUnitBadge.setFont( new Font("Segoe UI", Font.BOLD, 20));

        fromUnitBadge.setForeground( new Color(255, 225, 225));
        JPanel fromValueRow = new JPanel();
        fromValueRow.setOpaque(false);

        fromValueRow.setLayout(new BoxLayout(fromValueRow, BoxLayout.X_AXIS));
        fromValueRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        fromValueRow.setMaximumSize( new Dimension(320, 70));

        fromValueRow.add(Box.createHorizontalGlue());
        fromValueRow.add(temperatureField);
        fromValueRow.add(Box.createHorizontalStrut(8));

        fromValueRow.add(fromUnitBadge);
        fromValueRow.add(Box.createHorizontalGlue());
        fromPanel.add(fromValueRow);


        converterCard.add(fromPanel);


        // SWAP BUTTON
        JPanel swapPanel = new JPanel();
        swapPanel.setBackground(Color.WHITE);
        swapPanel.setLayout(new FlowLayout( FlowLayout.CENTER, 0, 0));

        swapPanel.setPreferredSize( new Dimension(510, 55));
        swapPanel.setMaximumSize( new Dimension(510, 55));
        swapButton = new RoundButton("⇅");

        swapButton.setBackground(Color.WHITE);
        swapButton.setForeground(red);
        swapButton.setFont(new Font("Segoe UI Symbol",Font.BOLD, 28));

        swapButton.setPreferredSize(new Dimension(65, 65));
        swapPanel.add(swapButton);
        converterCard.add(swapPanel);


        // TO PANEL
        JPanel toPanel = new JPanel();
        toPanel.setBackground(Color.WHITE);
        toPanel.setLayout( new BoxLayout(toPanel, BoxLayout.Y_AXIS));

        toPanel.setBorder( new EmptyBorder( 0, 40, 15, 40 ));
        toPanel.setPreferredSize( new Dimension(510, 210)
        );

        toPanel.setMaximumSize(new Dimension(510, 210)
        );


        JLabel toLabel = new JLabel(
                "TO",
                SwingConstants.CENTER
        );

        toLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        toLabel.setForeground(red);

        toLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        toPanel.add(toLabel);

        toPanel.add(
                Box.createVerticalStrut(8)
        );


        // TO COMBO BOX

        toComboBox = new JComboBox<>();

        toComboBox.addItem("Celsius");
        toComboBox.addItem("Fahrenheit");
        toComboBox.addItem("Kelvin");

        toComboBox.setSelectedIndex(1);

        toComboBox.setFont(normalFont);

        toComboBox.setMaximumSize(
                new Dimension(250, 42)
        );

        toComboBox.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        toPanel.add(toComboBox);

        toPanel.add(
                Box.createVerticalStrut(8)
        );


        // RESULT

        resultLabel = new JLabel(
                "0.00",
                SwingConstants.CENTER
        );

        resultLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        46
                )
        );

        resultLabel.setForeground(red);

        resultLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        toUnitBadge = new JLabel("°F");

        toUnitBadge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        toUnitBadge.setForeground(new Color(160, 160, 160));
        JPanel toValueRow = new JPanel();
        toValueRow.setOpaque(false);

        toValueRow.setLayout(new BoxLayout(toValueRow, BoxLayout.X_AXIS) );
        toValueRow.setAlignmentX( Component.CENTER_ALIGNMENT);
        toValueRow.setMaximumSize( new Dimension(320, 70) );

        toValueRow.add(Box.createHorizontalGlue());
        toValueRow.add(resultLabel);
        toValueRow.add(Box.createHorizontalStrut(8));
        toValueRow.add(toUnitBadge);
        toValueRow.add(Box.createHorizontalGlue());

        toPanel.add(toValueRow);


        // LIVE UNIT BAD
        fromComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                fromUnitBadge.setText(
                        Utils.getUnitSymbol((String) fromComboBox.getSelectedItem())
                );
            }
        });

        toComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                toUnitBadge.setText(
                        Utils.getUnitSymbol((String) toComboBox.getSelectedItem())
                );
            }
        });


        // CATEGORY
        categoryLabel = new JLabel(
                "Category: --",
                SwingConstants.CENTER
        );
        categoryLabel.setFont(boldFont);
        categoryLabel.setForeground(
                new Color(95, 95, 95)
        );

        categoryLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        toPanel.add(categoryLabel);
        converterCard.add(toPanel);
        mainPanel.add(converterCard);
        mainPanel.add( Box.createVerticalStrut(12)
        );


        // CONVERT / CLEAR BUTTONS
        JPanel actionPanel = new JPanel();

        actionPanel.setOpaque(false);

        actionPanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        12,
                        5
                )
        );


        convertButton =
                new RoundedButton("🔄  Convert");

        clearButton =
                new RoundedButton("🧹  Clear");


        styleButton( convertButton,
                red,
                Color.WHITE
        );

        styleButton(
                clearButton,
                dark,
                Color.WHITE
        );


        actionPanel.add(convertButton);
        actionPanel.add(clearButton);

        mainPanel.add(actionPanel);

        mainPanel.add(
                Box.createVerticalStrut(12)
        );


        // HISTORY PANEL
        RoundedPanel historyPanel = new RoundedPanel(16);

        historyPanel.setBackground(Color.WHITE);

        historyPanel.setBorder(
                new EmptyBorder(
                        10,
                        14,
                        10,
                        14
                )
        );

        historyPanel.setLayout( new BorderLayout(8, 8));
        historyPanel.setPreferredSize(new Dimension(510, 190));
        historyPanel.setMaximumSize( new Dimension(510, 190));
        
        JPanel historyHeader = new JPanel(  new BorderLayout() );
        historyHeader.setOpaque(false);

        JLabel historyTitle = new JLabel( "Conversion History" );
        historyTitle.setFont( new Font( "Segoe UI",Font.BOLD,16));
        historyTitle.setForeground(textColor);
        historyHeader.add( historyTitle, BorderLayout.WEST);


        // CLEAR HISTORY (outline style, top-right of the header row)
        clearHistoryButton =new RoundedButton("🗑  Clear History");
        clearHistoryButton.setContentAreaFilled(false);

        clearHistoryButton.setOpaque(false);
        clearHistoryButton.setFont(new Font( "Segoe UI", Font.BOLD, 12));
        clearHistoryButton.setBackground( Color.WHITE);

        clearHistoryButton.setForeground(new Color(210, 60, 60));
        clearHistoryButton.setBorder( new EmptyBorder(6, 12, 6, 12));
        clearHistoryButton.setPreferredSize(new Dimension(150, 34));

        historyHeader.add(clearHistoryButton, BorderLayout.EAST);
        historyPanel.add( historyHeader, BorderLayout.NORTH);


        // HISTORY LIST
        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);

        historyRenderer = new HistoryCellRenderer();
        historyList.setCellRenderer(historyRenderer);
        historyList.setFixedCellHeight(34);

        historyList.setBackground(Color.WHITE);
        historyList.setSelectionBackground(Color.WHITE);
        historyList.setSelectionForeground(textColor);
        historyList.setFocusable(false);

        for (String entry : historyManager.getHistory()) {
             historyModel.addElement(entry);
        }


        JScrollPane historyScroll =new JScrollPane(historyList);
        historyScroll.setBorder( BorderFactory.createEmptyBorder() );
        historyScroll.getViewport().setBackground(Color.WHITE);
        historyScroll.setPreferredSize(new Dimension(470, 130) );

        historyPanel.add(historyScroll,BorderLayout.CENTER);
        mainPanel.add(historyPanel);
        mainPanel.add(Box.createVerticalStrut(8) );
        frame.add( mainPanel,BorderLayout.CENTER);


        // THEME BUTTONS
        JPanel themePanel = new JPanel();
        themePanel.setOpaque(false);
        themePanel.setLayout( new FlowLayout( FlowLayout.CENTER,  8, 5 ));

        whiteButton = new RoundedButton("White");
        darkButton = new RoundedButton("Dark");
        greenButton =new RoundedButton("Green");


        styleThemeButton(whiteButton, Color.WHITE, textColor);
        styleThemeButton(darkButton, new Color(70, 70, 70), Color.WHITE);
        styleThemeButton(greenButton, new Color(215, 235, 220), new Color(35, 120, 60) );

        themePanel.add(whiteButton);
        themePanel.add(darkButton);
        themePanel.add(greenButton);

        frame.add( themePanel,BorderLayout.SOUTH );


        // CONVERT BUTTON
        convertButton.addActionListener(e -> {
            String inputText =temperatureField.getText().trim();
            if (!Utils.isValidNumber(inputText)) {
                JOptionPane.showMessageDialog( frame, "Please enter a valid number!",
                        "Invalid Input",JOptionPane.WARNING_MESSAGE);
                return;
            }


            try {
                double temperature =Double.parseDouble(inputText);
                String from = (String)fromComboBox.getSelectedItem();
                String to =(String)toComboBox.getSelectedItem();
                double result;

                // Celsius conversions
                if ( from.equals("Celsius") && to.equals("Fahrenheit") ) {
                    result = converter.celsiusToFahrenheit( temperature );
                }

                else if ( from.equals("Celsius") && to.equals("Kelvin")) {
                    result = converter.celsiusToKelvin( temperature );
                }


                // Fahrenheit conversions
                else if ( from.equals("Fahrenheit") && to.equals("Celsius")) {result = converter.fahrenheitToCelsius( temperature);
                }

                else if ( from.equals("Fahrenheit") && to.equals("Kelvin")) {
                    result = converter.fahrenheitToKelvin(temperature);
                }


                // Kelvin conversions
                else if (from.equals("Kelvin") && to.equals("Celsius")) {
                    result =converter.kelvinToCelsius(temperature);
                }

                else if (from.equals("Kelvin") && to.equals("Fahrenheit")) {
                    result =converter.kelvinToFahrenheit( temperature);
                }
                else {
                    result = temperature;
                }
                result = Utils.round(result,2 );

                // SHOW RESULT
                resultLabel.setText(
                        String.format(
                                "%.2f",
                                result
                        )
                );


                // CATEGORY
                double celsiusTemperature;
                if (from.equals("Celsius")) {
                   celsiusTemperature =temperature;
                }

                else if (from.equals("Fahrenheit") ) {
                    celsiusTemperature = converter.fahrenheitToCelsius( temperature );
                }

                else {
                    celsiusTemperature =converter.kelvinToCelsius( temperature);
                }

                String category = Utils.getTemperatureCategory(celsiusTemperature);
                categoryLabel.setText( "Category: " + category);
                categoryLabel.setForeground(Utils.getCategoryColor(category));


                // SAVE HISTORY
                historyManager.addEntry(from,to,temperature, result);
                historyModel.addElement(historyManager.getHistory().get(historyManager.getHistory().size() - 1));
                historyList.ensureIndexIsVisible(historyModel.getSize() - 1 );

                mainPanel.revalidate();
                mainPanel.validate();
                mainPanel.repaint();

            }

            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,"Please enter a valid number!",
                        "Invalid Input",JOptionPane.WARNING_MESSAGE);
            }

        });


        // SWAP BUTTON
        swapButton.addActionListener(e -> {
            int fromIndex =fromComboBox.getSelectedIndex();
            int toIndex = toComboBox.getSelectedIndex();
            fromComboBox.setSelectedIndex(toIndex);
            toComboBox.setSelectedIndex(fromIndex );

        });


        // CLEAR BUTTON
        clearButton.addActionListener(e -> {
            temperatureField.setText("");
            resultLabel.setText("0.00" );

            categoryLabel.setText("Category: --");
            categoryLabel.setForeground(new Color(95, 95, 95));

            mainPanel.revalidate();
            mainPanel.validate();
            mainPanel.repaint();

        });


        // CLEAR HISTORY
        clearHistoryButton.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(frame,"Are you sure you want to clear all history?",
                            "Clear History", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                historyManager.clearHistory();
                historyModel.clear();
            }

        });



        // THEME SWITCHING
        whiteButton.addActionListener(e -> applyTheme(
                "White",
                frame, mainPanel, converterCard, toPanel, swapPanel,
                historyPanel, historyTitle, historyList, historyRenderer,
                title, categoryLabel
        ));

        darkButton.addActionListener(e -> applyTheme(
                "Dark",
                frame, mainPanel, converterCard, toPanel, swapPanel,
                historyPanel, historyTitle, historyList, historyRenderer,
                title, categoryLabel
        ));

        greenButton.addActionListener(e -> applyTheme(
                "Green",
                frame, mainPanel, converterCard, toPanel, swapPanel,
                historyPanel, historyTitle, historyList, historyRenderer,
                title, categoryLabel
        ));

        frame.setVisible(true);
    }


    // THEME APPLICATION
    private void applyTheme(
            String theme,
            JFrame frame,
            JPanel mainPanel,
            RoundedPanel converterCard,
            JPanel toPanel,
            JPanel swapPanel,
            RoundedPanel historyPanel,
            JLabel historyTitle,
            JList<String> historyList,
            HistoryCellRenderer historyRenderer,
            GradientTitleLabel title,
            JLabel categoryLabel
    ) {

        Color pageBg;
        Color cardBg;
        Color textCol;
        Color titleStart;
        Color titleEnd;
        Color historyCardBg;
        Color historyCardBorder;
        Color historyText;

        switch (theme) {

            case "Dark":
                pageBg = new Color(35, 35, 38);
                cardBg = new Color(50, 50, 54);
                textCol = new Color(230, 230, 230);
                titleStart = new Color(255, 120, 120);
                titleEnd = Color.WHITE;
                historyCardBg = new Color(60, 60, 64);
                historyCardBorder = new Color(85, 85, 90);
                historyText = new Color(220, 220, 220);
                break;

            case "Green":
                pageBg = new Color(143, 188, 143);
                cardBg = new Color(245, 255, 246);
                textCol = new Color(35, 90, 50);
                titleStart = new Color(45, 140, 80);
                titleEnd = new Color(20, 90, 50);
                historyCardBg = new Color(235, 250, 238);
                historyCardBorder = new Color(190, 225, 195);
                historyText = new Color(40, 90, 55);
                break;

            default:
                pageBg = background;
                cardBg = Color.WHITE;
                textCol = textColor;
                titleStart = red;
                titleEnd = new Color(190, 60, 110);
                historyCardBg = Color.WHITE;
                historyCardBorder = new Color(225, 225, 225);
                historyText = new Color(60, 60, 60);
                break;
        }

        currentTheme = theme;

        frame.getContentPane().setBackground(pageBg);
        mainPanel.setOpaque(false);

        converterCard.setBackground(cardBg);
        toPanel.setBackground(cardBg);
        swapPanel.setBackground(cardBg);
        historyPanel.setBackground(cardBg);

        historyTitle.setForeground(textCol);

        historyList.setBackground(cardBg);
        historyList.setSelectionBackground(cardBg);
        historyList.setSelectionForeground(textCol);

        historyRenderer.cardBg = historyCardBg;
        historyRenderer.cardBorder = historyCardBorder;
        historyRenderer.textColor = historyText;

        title.setGradientColors(titleStart, titleEnd);

        historyList.repaint();
        converterCard.repaint();
        toPanel.repaint();
        swapPanel.repaint();
        historyPanel.repaint();
        mainPanel.repaint();
        frame.repaint();
    }


    // NORMAL BUTTON STYLE
    private void styleButton(
            JButton button,
            Color backgroundColor,
            Color foregroundColor) {
                button.setPreferredSize(new Dimension(145, 45));

                button.setBackground( backgroundColor);
                button.setForeground(foregroundColor);
                button.setFont( new Font( "Segoe UI",Font.BOLD, 16 ));

                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.setCursor(new Cursor( Cursor.HAND_CURSOR));
    }



    // THEME BUTTON STYLE
    private void styleThemeButton(
            JButton button,
            Color backgroundColor,
            Color foregroundColor) {
                button.setPreferredSize(new Dimension(85, 32));
                button.setBackground(backgroundColor);

                button.setForeground(foregroundColor);
                button.setFont(new Font("Segoe UI",Font.BOLD,12));

                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createLineBorder( new Color(190,190,190)));
                button.setCursor( new Cursor(Cursor.HAND_CURSOR));
    }


    // CIRCULAR BUTTON
    static class RoundButton extends JButton {
        RoundButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);

            setBorderPainted(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }


        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 =(Graphics2D) g.create();
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Shadow
            g2.setColor(new Color(0,0,0,35));
            g2.fillOval(3, 4,getWidth() - 6, getHeight() - 6 );

            // Circle
            g2.setColor(getBackground());
            g2.fillOval(0,0,getWidth() - 5, getHeight() - 5);
            g2.dispose();
            super.paintComponent(g);
        }
    }


    // ROUNDED BUTTON
    static class RoundedButton
            extends JButton {

        RoundedButton(String text) {
            super(text);

            setContentAreaFilled(false);
            setFocusPainted(false);

            setBorderPainted(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }


        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Shadow
            g2.setColor(new Color(0,0, 0,30 ));
            g2.fillRoundRect(3, 4, getWidth() - 6,getHeight() - 6,18, 18);

            // Button
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 18, 18);
            g2.dispose();
            super.paintComponent(g);
        }
    }



    // GRADIENT TITLE LABEL (script-style header text)
    static class GradientTitleLabel extends JLabel {

        private Color colorStart;
        private Color colorEnd;
        private final Font scriptFont;

        GradientTitleLabel(String text) {
            super(text, SwingConstants.CENTER);

            scriptFont = pickScriptFont();
            setFont(scriptFont);
            setForeground(Color.RED);
        }

        void setGradientColors(Color start, Color end) {
            this.colorStart = start;
            this.colorEnd = end;
            repaint();
        }

        private Font pickScriptFont() {
            String[] candidates = {
                    "Segoe Script", "Brush Script MT", "Lucida Handwriting",
                    "Comic Sans MS"
            };

            String[] available =
                    GraphicsEnvironment.getLocalGraphicsEnvironment()
                            .getAvailableFontFamilyNames();

            java.util.Set<String> availableSet = new java.util.HashSet<>();
            for (String name : available) {
                availableSet.add(name);
            }

            for (String candidate : candidates) {
                if (availableSet.contains(candidate)) {
                    return new Font(candidate, Font.PLAIN, 42);
                }
            }

            return new Font("Georgia", Font.BOLD | Font.ITALIC, 36);
        }

        @Override
        protected void paintComponent(Graphics g) {

            if (colorStart == null || colorEnd == null) {
                super.paintComponent(g);
                return;
            }

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setFont(scriptFont);

            FontMetrics fm = g2.getFontMetrics();
            String text = getText();

            int textWidth = fm.stringWidth(text);
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            GradientPaint paint = new GradientPaint( x, 0, colorStart, x + textWidth, 0, colorEnd);

            g2.setPaint(paint);
            g2.drawString(text, x, y);
            g2.dispose();
        }
    }


    // ROUNDED PANEL (used for the converter, and history cards)
    static class RoundedPanel extends JPanel {
        private final int radius;
        RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Soft shadow
            g2.setColor(new Color(0, 0, 0, 18));
            g2.fillRoundRect(2, 4, getWidth() - 4, getHeight() - 4, radius, radius);

            // Card fill
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 5, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Shape clip = new RoundRectangle2D.Float(0, 0, getWidth() - 3, getHeight() - 5, radius, radius);

            g2.setClip(clip);
            super.paint(g2);
            g2.dispose();
        }
    }



    // HISTORY LIST CELL RENDERER (rounded row + clock icon)
    static class HistoryCellRenderer extends JPanel
            implements ListCellRenderer<String> {

        Color cardBg = Color.WHITE;
        Color cardBorder = new Color(225, 225, 225);
        Color textColor = new Color(60, 60, 60);

        private final JLabel iconLabel;
        private final JLabel textLabel;

        HistoryCellRenderer() {
            setLayout(new BorderLayout(8, 0));
            setOpaque(false);
            setBorder(new EmptyBorder(4, 8, 4, 8));

            iconLabel = new JLabel("\uD83D\uDD52");
            iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));

            textLabel = new JLabel();
            textLabel.setFont(new Font("Consolas", Font.PLAIN, 12));

            add(iconLabel, BorderLayout.WEST);
            add(textLabel, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent( JList<? extends String> list,
                String value,int index,boolean isSelected, boolean cellHasFocus) {
            textLabel.setText(value);
            textLabel.setForeground(textColor);
            setBackground(cardBg);
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(cardBg);
            g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 6, 10, 10);

            g2.setColor(cardBorder);
            g2.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 7, 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}