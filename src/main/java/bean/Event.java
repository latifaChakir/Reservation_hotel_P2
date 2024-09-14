package bean;

import java.time.LocalDate;

public class Event {
    private LocalDate startDate;
    private LocalDate endDate;
    private double coefficient;

    public Event(LocalDate startDate, LocalDate endDate, double coefficient) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coefficient = coefficient;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Event{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", coefficient=" + coefficient +
                '}';
    }
}
