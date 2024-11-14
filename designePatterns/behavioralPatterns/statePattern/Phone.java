package designePatterns.behavioralPatterns.statePattern;

import designePatterns.behavioralPatterns.statePattern.state.OffState;
import designePatterns.behavioralPatterns.statePattern.state.State;

public class Phone {
    private State state;

    public Phone() {
        state = new OffState(this);
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String turnOn() {
        return "Turning screen on, device still locked";
    }

    public String unlock() {
        return "Unlock phone and go to home screen";
    }

    public String lock() {
        return "Locking phone and turning off screen";
    }

    public String home() {
        return "Go to home screen";
    }
}
