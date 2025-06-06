package advanced.streamAPI.model;

import java.util.Optional;

public class Computer {
    public Optional<Soundcard> soundcard;

    public Optional<Soundcard> getSoundcard() {
        return soundcard;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "soundcard=" + soundcard +
                '}';
    }
}
