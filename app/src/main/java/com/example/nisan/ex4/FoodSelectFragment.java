package com.example.nisan.ex4;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by narzoan on 11/19/2016.
 */

public class FoodSelectFragment extends Fragment {

    public interface OnFragmentInteractionListener {
        public void itemPicked(String itemPicked);
    }

    private FoodSelectFragment.OnFragmentInteractionListener mCallback;

    public final static String EXTRA_MESSAGE = "com.example.nisan.MESSAGE";

    private String inputbox;
    private Boolean checkbox;
    ListView listView;

    private String[] values;

    public FoodSelectFragment() {

    }

    public static FoodSelectFragment newInstance(String param1, String param2) {
        FoodSelectFragment fragment = new FoodSelectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FoodSelectFragment.OnFragmentInteractionListener) {
            mCallback = (FoodSelectFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.fragment_foodselect, container, false);

        values = getResources().getStringArray(R.array.foods);
        listView = (ListView) returnView.findViewById(R.id.listview_id);
        CustomFruitListAdapter adapter = new CustomFruitListAdapter(returnView.getContext(),values);
        listView.setAdapter(adapter);

        listViewHandler();

        return returnView;
    }

    private void listViewHandler(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position, long id){
                mCallback.itemPicked(values[position]);
            }
        });
    }
}
