package sep3.classes.Model;

import java.io.Serializable;

public class Hospital implements Serializable {

    private int id;
    private String type;
    private String name;
    private int postalCode;
    private String address;
    private int managerId;
    private double avgRating;
    private boolean validated;
    private String info;
    private byte[] image;

    public Hospital() {
    }

    public Hospital(int id, String type, String name, int postalCode, String address, int managerId,
                    double avgRating, boolean validated, String info,byte[] image) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.postalCode = postalCode;
        this.address = address;
        this.managerId = managerId;
        this.avgRating = avgRating;
        this.validated = validated;
        this.info = info;
        this.image = image;
    }

    public Hospital(String type, String name, int postalCode, String address, int managerId,
                    double avgRating, boolean validated, String info,byte[] image) {
        this.type = type;
        this.name = name;
        this.postalCode = postalCode;
        this.address = address;
        this.managerId = managerId;
        this.avgRating = avgRating;
        this.validated = validated;
        this.info = info;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public boolean getValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", postalCode=" + postalCode +
                ", address='" + address + '\'' +
                ", managerId=" + managerId +
                ", avgRating=" + avgRating +
                ", validated='" + validated + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
