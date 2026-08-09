package src;

public class Utils {
    private Utils(){

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
