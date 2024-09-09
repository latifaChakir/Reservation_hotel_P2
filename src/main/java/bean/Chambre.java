package bean;

public class Chambre {
    private int id;
    private  int numero;
    private RoomType type;
    private boolean isDisponible;
    public Chambre(int numero, RoomType type, boolean isDisponible) {
        this.numero = numero;
        this.type=type;
        this.isDisponible=isDisponible;
    }
    public int getNumero() {
        return  numero;
    }
    public RoomType getType() {
        return  type;
    }
    public boolean isDisponible() {
        return isDisponible;
    }
    public void reserver(){
        this.isDisponible=false;
    }
    public void liberer(){
        this.isDisponible=true;
    }

    public String toString(){
        return  "Chambre {"
                + "numero : "+numero+ "," +
                " type : "+type+ ", " +
                "isDisponible : "+isDisponible+'}';
    }

    public int getId() {
        return id;
    }

    public void setId(int chambreId) {
        this.id = chambreId;
    }
}
