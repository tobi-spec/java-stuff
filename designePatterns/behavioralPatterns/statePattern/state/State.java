package designePatterns.behavioralPatterns.statePattern.state;

import designePatterns.behavioralPatterns.statePattern.Phone;

public abstract class State {
    protected Phone phone;

    public State(Phone phone) {
        this.phone = phone;
    }

    public abstract String onHome();
    public abstract String onOffOn();
}
