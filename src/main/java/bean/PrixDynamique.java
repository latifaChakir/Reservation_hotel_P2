package bean;

import java.util.HashMap;
import java.util.Map;

public class PrixDynamique {
    private Map<String,Double> days_of_week_rates = new HashMap<String,Double>();
    private Map<String, Double> events_rates=new HashMap<>();
    private Map<String, Double> saisons_rates=new HashMap<>();

    public PrixDynamique() {
        days_of_week_rates.put("Saturday", 6.0);
        days_of_week_rates.put("Sunday", 7.0);

        events_rates.put("NEW_YEAR", 6.0);
        events_rates.put("HOLIDAY", 7.0);

        saisons_rates.put("SUMMER", 6.0);
        saisons_rates.put("WINTER", 7.0);
    }
    public Map<String, Double> getDays_of_week() {
        return days_of_week_rates;
    }
    public Map<String, Double> getevents_rates() {
        return events_rates;
    }
    public Map<String, Double> getSaisons() {
        return saisons_rates;
    }
    public void addDayRate(String day, double rate) {
        days_of_week_rates.put(day, rate);
    }
    public void addEventRate(String event, double rate) {
        events_rates.put(event, rate);
    }
    public void addSaisonRate(String saison, double rate) {
        saisons_rates.put(saison, rate);
    }

}
