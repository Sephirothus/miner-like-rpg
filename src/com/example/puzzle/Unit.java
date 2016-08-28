package com.example.puzzle;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public abstract class Unit {

    public static Class[] units = {Enemy.class, Gold.class};

    public static Unit getRandomUnit(Context context, int lvl, int position) {
        Random r = new Random();
        Class c = units[r.nextInt(units.length)];
        try {
            return (Unit) c
                    .getDeclaredConstructor(Context.class, Integer.class, Integer.class)
                    .newInstance(context, lvl, position);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void action();
    public abstract String getTitle();
    public abstract int getColor();
}
