package service;

import Dao.impl.PrixDynamiqueDaoImpl;
import bean.Chambre;
import bean.PrixDynamique;
import bean.Reservation;
import enums.Days;
import enums.Events;
import enums.Saison;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class PrixDynamiqueService {
    private PrixDynamiqueDaoImpl prixDynamiqueDaoImpl;

    public PrixDynamiqueService() {
        this.prixDynamiqueDaoImpl = new PrixDynamiqueDaoImpl();
    }

    public void addPricingRule(PrixDynamique prixDynamique) {
            prixDynamiqueDaoImpl.save(prixDynamique);
            System.out.println("Pricing rule added successfully.");
    }

    public List<PrixDynamique> getAllPricingRules() {
            return prixDynamiqueDaoImpl.findAll();
    }

    public BigDecimal calculeTotalPrice(Reservation reservation) {
        BigDecimal totalPrice = BigDecimal.ZERO; // Start with zero
        LocalDate startDate = reservation.getDateDebut();
        LocalDate endDate = reservation.getDateFin();
        Chambre chambre = reservation.getChambre();

        BigDecimal prixChambre = BigDecimal.valueOf(chambre.getBasePrice()); // Room base price

        // Loop through each day in the reservation period
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            // Get dynamic pricing factors
            Saison saison = getSaison(date);
            Days dayOfWeek = Days.valueOf(date.getDayOfWeek().name());
            Events event = getEvent(date);

            BigDecimal coefficient = BigDecimal.valueOf(getCoefficient(saison, dayOfWeek, event));
//
//            System.out.println("Coefficient: " + coefficient);
//            System.out.println("Room Base Price: " + prixChambre);

            BigDecimal dailyPrice = prixChambre.multiply(coefficient);

            totalPrice = totalPrice.add(dailyPrice);
        }

        // Return the total price, rounded to two decimal places
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }
    private Saison getSaison(LocalDate date) {
        Month month = date.getMonth();
        if (month == Month.JUNE || month == Month.JULY || month == Month.AUGUST) {
            return Saison.SUMMER;
        } else if (month == Month.SEPTEMBER || month == Month.OCTOBER || month == Month.NOVEMBER) {
            return Saison.FALL;
        } else if (month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY) {
            return Saison.WINTER;
        } else {
            return Saison.SPRING;
        }
    }

    private Events getEvent(LocalDate date) {
        return null;
    }

    private double getCoefficient(Saison saison, Days dayOfWeek, Events event) {
        List<PrixDynamique> pricingRules = prixDynamiqueDaoImpl.findAll();

        for (PrixDynamique prixDynamique : pricingRules) {
            boolean match = prixDynamique.getSeason() == saison && prixDynamique.getDayOfWeek() == dayOfWeek;

            if (event != null) {
                match = match && prixDynamique.getEvent() == event;
            }

            if (match) {
                return prixDynamique.getCoefficient().doubleValue();
            }
        }

        return 1.0;
    }
}
