package src;

public class TemperatureConverter {

    public double celsiusToFahrenheit(double celsius){
        return (celsius * 9/5)+32;
    }
    
    public  double celsiusToKelvin(double celsius){
        return celsius +273.15;
    }

    public double fahrenheitToCelsius(double fahrenheit){
        return (fahrenheit -32)* 5 /9;
    }

    public double fahrenheitToKelvin(double fahrenheit){
        return (fahrenheit - 32) * 5 / 9 +273.15;
    }

    public double kelvinToCelsius(double kelvin){
        return kelvin - 273.15;
    }

    public double kelvinToFahrenheit(double kelvin){
        return (kelvin -273.15) *9 / 5 + 32;
    }
    
}
