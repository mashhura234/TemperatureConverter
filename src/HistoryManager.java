package src;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryManager {

    private List<String> history;
    private final String fileName = "history.txt";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");


    public HistoryManager(){
        history = new ArrayList<>();
        loadHistory();
    }

    public void addEntry(String fromUnit, String toUnit, double inputValue, double resultValue){
        String dateTime = LocalDateTime.now().format(formatter);
        String entry = String.format("%s | %.2f %s -> %.2f %s", dateTime, inputValue, fromUnit, resultValue, toUnit);
       
        history.add(entry);
        saveHistory();
    }

    public List<String> getHistory(){
        return history;
    }

    private void saveHistory(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for(String entry : history){
                writer.write(entry);
                writer.newLine();
            }
        }
            catch(IOException e){
                System.out.println("Ohoo! Unable to save history.");
            
        }
    }

    private void loadHistory(){
        File file = new File(fileName);
        if(!file.exists()){
            return;
        }
        try(BufferedReader reader = new BufferedReader(new  FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null){
                if(!line.trim().isEmpty()){
                    history.add(line);
                }
            }
        }
        catch(IOException e){
            System.out.println("Ohhoo! Unable to load history :(");
        }
    }

    public String getHistoryAsText(){
        StringBuilder sb = new StringBuilder();
        for(String entry : history){
            sb.append(entry).append("\n");
        }

        return sb.toString();
    }

    public void clearHistory(){
        history.clear();
        saveHistory();
    }
    
}