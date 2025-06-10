package advanced.streamAPI;

import advanced.streamAPI.model.AppUser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsApi {

    public static AppUser changeId(AppUser appUser) {
        appUser.setId(10);
        return appUser;
    }

    public static void main(String[] args) {
        System.out.println("------Collect to List-------");

        Stream<AppUser> myUser = Stream.of(new AppUser("Tom", 1), new AppUser("Tim", 2), new AppUser("Tom", 3), new AppUser("Frank", 4));
        List<AppUser> usersNamedTom = myUser.filter(user -> user.getName().equals("Tom"))
                .collect(Collectors.toList());
        System.out.println(usersNamedTom);

        System.out.println("------Collect to List-------");

        Stream<AppUser> myUser2 = Stream.of(new AppUser("Tom", 1), new AppUser("Tim", 2), new AppUser("Tom", 3), new AppUser("Frank", 4));
        List<AppUser> userChangedIds = myUser2
                .map(StreamApi::changeId)
                .toList();
        System.out.println(userChangedIds);

        System.out.println("------Collect to Map-------");

        Stream<AppUser> myUser3 = Stream.of(new AppUser("Tom", 1), new AppUser("Tim", 2), new AppUser("Tom", 3), new AppUser("Frank", 4));
        Map<Integer, String> mapOfUsers = myUser3.collect(Collectors.toMap(AppUser::getId, AppUser::getName));
        System.out.println(mapOfUsers);

        System.out.println("------Collect all names-------");
        Stream<AppUser> myUser4 = Stream.of(new AppUser("Tom", 1), new AppUser("Tim", 2), new AppUser("Tom", 3), new AppUser("Frank", 4));
        List<String> allNames = myUser4.map(AppUser::getName).toList();
        System.out.println(allNames);
    }

}
