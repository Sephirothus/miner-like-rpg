package com.example.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Unit {

    public static Class[] unitsClasses = {Enemy.class, Gold.class};
    public static ArrayList<Class> units = new ArrayList<Class>(Arrays.<Class>asList(unitsClasses));

    public static Unit getRandomUnit() {
        Random r = new Random();
        Class c = units.get(r.nextInt(units.size()));
        try {
            return (Unit) c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
