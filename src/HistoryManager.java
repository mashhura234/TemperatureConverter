package src;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private List<String> history;

    public HistoryManager(){
        history = new ArrayList<>();
    }

    public void addEntry(String fromUnit, String toUnit, double inputValue, double resultValue){
        String entry = String.format("%.2f %s -> %.2f 5s", inputValue, fromUnit, resultValue, toUnit);
        history.add(entry);
    }

    public List<String> getHistory(){
        return history;
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
    }
    
}
