package designePatterns.builderPattern;

public class CarBuilderMain {

    public static void main(String[] args) {
        Car car = new CarBuilder()
                .id(1)
                .brand("VW")
                .model("Polo")
                .color("Blue")
                .build();

        System.out.println(car.toString());
    }
}
