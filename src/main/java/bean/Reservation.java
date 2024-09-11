package bean;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private Client client;
    private Chambre chambre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private ReservationStatus status;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client=" + client +
                ", chambre=" + chambre +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }


}
