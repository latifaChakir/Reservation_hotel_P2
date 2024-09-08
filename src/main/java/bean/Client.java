package bean;
public class Client {
    private int id;
    private String name;
    private int age;
    private String address;
    private String phone;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Client(String name, int age, String address, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }
    public String toString() {
        return "Client : {" +"client id :" +id+
                ", clientName :" +name+" " +
                ",ClientAge : "+age+
                ",ClientAdress :" +address+
                ",ClientPhone :" +phone+ "}";
    }

    public int getId() {
        return id;
    }
}
