package main;

import fact.Apple;
import org.kie.api.runtime.KieSession;
import util.DroolsUtil;

import java.util.Arrays;
import java.util.List;

public class DroolsSample {

    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(
                new Apple(1),
                new Apple(5),
                new Apple(10),
                new Apple(30),
                new Apple(70),
                new Apple(120)
        );


        DroolsUtil<Apple> droolsUtil = null;
        try {
            droolsUtil = new DroolsUtil<>("rules1.xls");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (Apple apple : apples) {
            droolsUtil.executeRule(apple);
            System.out.println(apple);
        }
    }
}
