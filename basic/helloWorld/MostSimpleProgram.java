package basic.helloWorld;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MostSimpleProgram {

    public static void main(String[] args) {
        System.out.println("Hello World");
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(zonedDateTimeNow);
    }
}
