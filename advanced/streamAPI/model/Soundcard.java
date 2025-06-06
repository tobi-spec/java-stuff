package advanced.streamAPI.model;

import java.util.Optional;

public class Soundcard {
    public Optional<USB> usb;

    public Optional<USB> getUsb() {
        return usb;
    }

    @Override
    public String toString() {
        return "Soundcard{" +
                "usb=" + usb +
                '}';
    }
}
