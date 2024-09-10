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
    public Chambre(int id,int numero, RoomType type, boolean isDisponible) {
        this.numero = numero;
        this.type=type;
        this.id=id;
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

    public int getId() {
        return id;
    }

    public void setId(int chambreId) {
        this.id = chambreId;
    }
    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + id +
                ", numero=" + numero +
                ", type=" + type +
                ", isDisponible=" + isDisponible +
                '}';
    }
}
