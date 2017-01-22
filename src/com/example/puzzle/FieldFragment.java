package com.example.puzzle;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sephirothus on 12.01.17.
 */
public class FieldFragment extends Fragment {

    public MainMap mMainMap;
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.field, container, false);
        }
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            ViewGroup parentViewGroup = (ViewGroup) mRootView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mMainMap == null) {
            mMainMap = new MainMap(getActivity());
            createPlayField();
        }
    }

    public void createPlayField() {
        mMainMap.create().setUnits();
    }
}