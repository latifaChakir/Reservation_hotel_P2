package bean;

import enums.ReservationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {
    private int id;
    private Client client;
    private Chambre chambre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private ReservationStatus status;
    private BigDecimal total_price;
    public Reservation() {}
    public Reservation(Client client, Chambre chambre, LocalDate dateDebut, LocalDate dateFin, ReservationStatus status) {
        this.client = client;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.status = status;
        this.dateFin = dateFin;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client=" + client +
                ", chambre=" + chambre +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", status=" + status +
                ", total_price=" + total_price +
                '}';
    }


}
