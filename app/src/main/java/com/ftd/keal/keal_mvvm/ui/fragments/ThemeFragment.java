package com.ftd.keal.keal_mvvm.ui.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.ftd.keal.keal_mvvm.R;
import com.ftd.keal.keal_mvvm.utils.PreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThemeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThemeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemeFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private List<RelativeLayout> mLayoutList;
    private List<TextView> mTextViewList;
    private List<Switch> mSwitchList;


    public ThemeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThemeFragment.
     */
    public static ThemeFragment newInstance(String param1, String param2) {
        ThemeFragment fragment = new ThemeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        initTheme();
        mLayoutList = new ArrayList<>();
        mLayoutList.add((RelativeLayout) view.findViewById(R.id.rootlayout));
        mLayoutList.add((RelativeLayout) view.findViewById(R.id.layout1));
        mLayoutList.add((RelativeLayout) view.findViewById(R.id.layout2));

        mTextViewList = new ArrayList<>();
        mTextViewList.add((TextView) view.findViewById(R.id.tv1));
        mTextViewList.add((TextView) view.findViewById(R.id.tv1));

        mSwitchList = new ArrayList<>();
        Switch switch1 = (Switch) view.findViewById(R.id.switch1);
        Switch switch2 = (Switch) view.findViewById(R.id.switch2);
        mSwitchList.add(switch1);
        mSwitchList.add(switch2);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                setTheme();
                refreshUI();
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                setTheme();
                refreshUI();
            }
        });
        return view;
    }

    private void initTheme(){
        if(PreferencesUtil.getDayNightMode(getActivity())){
            getActivity().setTheme(R.style.DayTheme);
        }else{
            getActivity().setTheme(R.style.NightTheme);
        }
    }

    private void setTheme(){
        if(PreferencesUtil.getDayNightMode(getActivity())){
            PreferencesUtil.saveDayNightMode(getActivity(),false);
            getActivity().setTheme(R.style.NightTheme);
        }else{
            PreferencesUtil.saveDayNightMode(getActivity(),true);
            getActivity().setTheme(R.style.DayTheme);
        }
    }

    /**
     * 刷新UI界面
     */
    private void refreshUI() {
        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        Resources.Theme theme = getActivity().getTheme();
        theme.resolveAttribute(R.attr.colorBackground, background, true);
        theme.resolveAttribute(R.attr.colorTextColor, textColor, true);

        for (RelativeLayout layout : mLayoutList) {
            layout.setBackgroundResource(background.resourceId);
        }
        for (Switch sw : mSwitchList) {
            sw.setBackgroundResource(background.resourceId);
        }
        for (TextView textView : mTextViewList) {
            textView.setBackgroundResource(background.resourceId);
        }
        for (TextView textView : mTextViewList) {
            textView.setTextColor(getResources().getColor(textColor.resourceId));
        }

        refreshStatusBar();
    }

    /**
     * 刷新 StatusBar
     */
    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getActivity().getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getActivity().getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
