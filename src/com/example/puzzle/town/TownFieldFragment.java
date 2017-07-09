package com.example.puzzle.town;

import com.example.puzzle.field.FieldFragment;

/**
 * Created by sephirothus on 29.01.17.
 */
public class TownFieldFragment extends FieldFragment {

    @Override
    public void createPlayField() {
        mMainMap.create().setTownUnits(getArguments().getString("town_name"));
    }
}
