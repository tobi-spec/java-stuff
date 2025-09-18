package basic.syntax;

import java.util.ArrayList;

public class syntax {

    public static int simpleAddFunction(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        String variable = "value";
        int num1 = 3;
        var num2 = 5;

        var merge = num1 + variable;
        System.out.println(merge);

        if (variable.equals("value")) {
            System.out.println("The variable is equal to 'value'");
        } else {
            System.out.println("The variable is not equal to 'value'");
        }

        for (int i = 0; i < 5; i++) {
            System.out.println("Iteration: " + i);
        }

        ArrayList<String> list = new ArrayList<>();
        list.add("value");
        list.add("value2");
        list.add("value3");

        for(String item : list) {
            System.out.println("Item: " + item);
        }

        int result = simpleAddFunction(num1, num2);
        System.out.println("Addtition result: " + result);
    }
}
