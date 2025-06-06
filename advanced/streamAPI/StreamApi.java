package advanced.streamAPI;

import advanced.streamAPI.model.AppUser;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamApi {

    public static AppUser changeId(AppUser appUser) {
        appUser.setId(10);
        return appUser;
    }

    public static void main(String[] args) {
        System.out.println("------For Each-------");
        Stream<String> myStream = Stream.of("Apple", "Ananas", "Banana", "Orange");
        myStream.forEach(System.out::println);

        System.out.println("------Filter by String------");

        Stream<String> myStringStream = Stream.of("Apple", "Ananas", "Banana", "Orange");
        myStringStream.filter(element -> element.equals("Banana"))
                        .forEach(System.out::println);

        System.out.println("-----Filter by StartsWith-----");

        Stream<String> myStringStream2 = Stream.of("Apple", "Ananas", "Banana", "Orange");
        myStringStream2.filter(element -> element.startsWith("A"))
                        .forEach(System.out::println);

        System.out.println("------Filter by Calculation---------");

        Stream<Integer> myIntStream = Stream.of(1,2,3,4,5);
        myIntStream.filter(integer -> integer % 2 == 0)
                    .forEach(System.out::println);

        System.out.println("-------Map-------");

        Stream<Integer> myIntStream2 = Stream.of(1,2,3,4,5);
        myIntStream2.map(element -> element*3)
                    .forEach(System.out::println);

        System.out.println("------Find Objects by Attribute--------");

        Stream<AppUser> myUser = Stream.of(new AppUser("Tom", 1), new AppUser("Tim", 2), new AppUser("Tom", 3), new AppUser("Frank", 4));
        myUser.filter(user -> user.getName().equals("Tom"))
                .map(AppUser::toString)
                .forEach(System.out::println);

        System.out.println("------Map Function--------");

        Stream<AppUser> myUser2 = Stream.of(new AppUser("Tom", 1), new AppUser("Tim", 2), new AppUser("Tom", 3), new AppUser("Frank", 4));
        myUser2.filter(user -> user.getName().equals("Tom"))
                .map(StreamApi::changeId)
                .forEach(System.out::println);

        System.out.println("------Find First--------");

        Stream<AppUser> myUser3 = Stream.of(new AppUser("Tom", 1), new AppUser("Tim", 2), new AppUser("Tom", 3), new AppUser("Frank", 4));
        Optional<AppUser> firstTom = myUser3.filter(user -> user.getName().equals("Tom")).findFirst();
        System.out.println(firstTom.get());
    }
}
