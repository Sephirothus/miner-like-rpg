package com.example.puzzle;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public abstract class Unit {

    public static Class[] units;
    protected String mLocation;

    public static Unit getRandomUnit(Context context, int position, String location) {
        return newInstance(units[(new Random()).nextInt(units.length)], context, position, location);
    }

    public static Unit newInstance(Class unit, Context context, int position, String location) {
        try {
            return (Unit) unit
                    .getDeclaredConstructor(Context.class, Integer.class, String.class)
                    .newInstance(context, position, location);
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

    public void addUnitToCell(Context context, View view, Boolean changeBackColor) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.cell);
        Integer unitImg = getImg();
        ImageView img = new ImageView(context);
        img.setId(R.id.cell_img);
        if (unitImg != null) {
            img.setImageResource(unitImg);
            int size = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()
            );
            img.setLayoutParams(new LinearLayout.LayoutParams(size, size));
        }
        layout.addView(img);
        if (changeBackColor) view.setBackgroundColor(MainMap.OPENED_CELL_COLOR);
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public abstract void action();
    public abstract Integer getImg();
}
