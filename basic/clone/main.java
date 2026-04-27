package basic.clone;

import java.util.HashMap;
import java.util.List;

public class main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Moped moped1 = new Moped();
        moped1.setBrand("BMW");
        moped1.setPs(10);

        Moped moped2 = moped1.clone();
        Moped moped3 = Moped.copyOf(moped1);
        Moped moped4 = new Moped(moped1);

        // different memory positions
        System.out.println(moped1);
        System.out.println(moped2);
        System.out.println(moped3);
        System.out.println(moped4);

        // check
        System.out.println(moped1.getBrand());
        System.out.println(moped2.getBrand());
        System.out.println(moped3.getBrand());
        System.out.println(moped4.getBrand());
    }
}
