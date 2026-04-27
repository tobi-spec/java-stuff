package basic.clone;

public class Moped implements Cloneable {

    private int ps;
    private String brand;

    // empty constructor for setter use only
    public Moped() {}

    // standard constructor
    public Moped(int ps, String brand) {
        this.ps = ps;
        this.brand = brand;
    }

    // copy constructor
    public Moped(Moped source) {
        this.ps = source.ps;
        this.brand = source.brand;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Moped clone() throws CloneNotSupportedException {
        return (Moped) super.clone();
    }

    public static Moped copyOf(Moped source) {
        Moped copy = new Moped();
        copy.setPs(source.getPs());
        copy.setBrand(source.getBrand());
        return copy;
    }
}
