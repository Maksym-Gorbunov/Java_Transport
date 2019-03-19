package app;

import java.io.Serializable;

public class Car implements Serializable {
    private String licensenumber;
    private String make;
    private String color;
    public static final long serialVersionUID = 11L;

    public Car(String licensenumber, String make, String color) {
        this.licensenumber = licensenumber;
        this.make = make;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Car obj2 = (Car) obj;
        return getLicensenumber().equals(obj2.getLicensenumber());
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(getLicensenumber());
    }
    @Override
    public String toString() {
        return "reg.number : " + licensenumber + "\n" +
               "make : " + make + "\n" +
               "color      : " + color + "\n";
    }

    public String getLicensenumber() {
        return licensenumber;
    }
    public void setLicensenumber(String licensenumber) {
        this.licensenumber = licensenumber;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
