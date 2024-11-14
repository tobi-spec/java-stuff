package designePatterns.behavioralPatterns.statePattern.state;

import designePatterns.behavioralPatterns.statePattern.Phone;

public class ReadyState extends State{

    public ReadyState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        return phone.home();
    }

    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return phone.lock();
    }
}
