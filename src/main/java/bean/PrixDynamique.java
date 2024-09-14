package bean;

import enums.Days;
import enums.Events;
import enums.Saison;

import java.math.BigDecimal;

public class PrixDynamique {
    private Long id;
    private Saison season;
    private Days dayOfWeek;
    private Events event;
    private BigDecimal coefficient;

    public PrixDynamique(Saison season, Days dayOfWeek, Events event, BigDecimal coefficient) {
        this.season = season;
        this.dayOfWeek = dayOfWeek;
        this.event = event;
        this.coefficient = coefficient;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Saison getSeason() {
        return season;
    }

    public void setSeason(Saison season) {
        this.season = season;
    }

    public Days getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Days dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
}
