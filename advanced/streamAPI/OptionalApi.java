package advanced.streamAPI;

import advanced.streamAPI.model.AppUser;
import advanced.streamAPI.model.Computer;
import advanced.streamAPI.model.Soundcard;
import advanced.streamAPI.model.USB;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalApi {

    public static void main(String[] args) {
        Optional<Soundcard> empty = Optional.empty();
        Optional<Soundcard> soundcard = Optional.of(new Soundcard());
        Optional<Soundcard> soundcard2 = Optional.ofNullable(new Soundcard());

        System.out.println("----- is empty -----");
        System.out.println(empty.isEmpty());

        System.out.println("----- is present -----");
        System.out.println(soundcard.isPresent());

        System.out.println("---------- if present -------");
        soundcard.ifPresent(System.out::println);

        System.out.println("---------- orElse -------");
        Soundcard soundcard3 = empty.orElse(new Soundcard());
        System.out.println(soundcard3);

        System.out.println("---------- filter + isPresent -------");
        Stream<AppUser> myUsers = Stream.of(new AppUser("Tom", 1), new AppUser("Tim", 2), new AppUser("Tom", 3), new AppUser("Frank", 4));
        myUsers.filter(user -> "Tim".equals(user.getName()))
                .findFirst()
                .ifPresent(System.out::print);


        System.out.println("---------- filter + orElse -------");
        Stream<AppUser> myUsers2 = Stream.of(new AppUser("Tom", 1), new AppUser("Tim", 2), new AppUser("Tom", 3), new AppUser("Frank", 4));
        AppUser foundOrNewUser = myUsers2
                .filter(user -> "Marry".equals(user.getName()))
                .findFirst()
                .orElse(new AppUser("Marry", 5));
        System.out.println(foundOrNewUser);

    }




}
