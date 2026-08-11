package src;

import java.awt.Color;

public class Utils {
    private Utils(){

    }

    public static String getUnitSymbol(String unit){
        if(unit == null){
            return "";
        }
        switch(unit){
            case "Celsius":
                return "°C";
            case "Fahrenheit":
                return "°F";
            case "Kelvin":
                return "K";
            default:
                return "";
        }
    }

    public static Color getCategoryColor(String category){
        if(category == null){
            return new Color(95, 95, 95);
        }
        switch(category){
            case "Freezing":
                return new Color(70, 130, 220);
            case "Cold":
                return new Color(60, 160, 200);
            case "Cool":
                return new Color(50, 170, 140);
            case "Warm":
                return new Color(235, 150, 40);
            case "Hot":
                return new Color(225, 70, 70);
            default:
                return new Color(95, 95, 95);
        }
    }

    public static double round(double value, int decimalPlaces){
        double factor = Math.pow(10, decimalPlaces);
        return Math.round(value * factor)/ factor;
    }

    public static boolean isValidNumber(String text){
        if (text == null || text.trim().isEmpty()){
            return false;
        }

        try {
            Double.parseDouble(text.trim());
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public static String getTemperatureCategory(double celsius){
        if(celsius < 0){
            return "Freezing";
        }
        else if(celsius < 15){
            return "Cold";
        }

        else if(celsius < 25){
            return "Cool";
        }

        else if(celsius < 35){
            return "Warm";
        }

        else{
            return "Hot";
        }
    }
}