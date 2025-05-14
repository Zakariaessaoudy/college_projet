package model;

public class Materiel {
    private String serialNumber;
    private String name;
    private Salle salle;

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @Override
    public String toString() {
        return "Materiel : " +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", salle=" + salle +
                '}';
    }
    //creer un materiel sans l'attribuer a une salle
    public Materiel(String serialNumber, String name) {
        this.serialNumber = serialNumber;
        this.name = name;
    }
    //creer un materiel et l'affecter a une salle lors du creation
    public Materiel(String serialNumber, String name, Salle salle) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.salle = salle;
    }

}
