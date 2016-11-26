package com.example.nisan.ex4;

import android.content.Intent;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Transition;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import static android.support.v7.app.AppCompatActivity.*;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener,
        FoodSelectFragment.OnFragmentInteractionListener  {

    private Menu menu;
    private MenuItem send_order_button;
    private MainFragment mainFragment;
    private FoodSelectFragment foodsSelectFragment;

    public final static String EXTRA_MESSAGE = "com.example.nisan.MESSAGE";


    @Override
    public void sendOrderClicked() {
        Intent intent = new Intent(this, OrderSendActivity.class);
        startActivity(intent);
    }

    @Override
    public void selectButtonClicked() {
        Bundle args = new Bundle();
        foodsSelectFragment = new FoodSelectFragment();
        foodsSelectFragment.setArguments(args);

        if (findViewById(R.id.fragment_container) != null) { //not tablet
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, foodsSelectFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void itemPicked(String itemPicked){

        if (findViewById(R.id.fragment_container) != null) { //not tablet
            getSupportFragmentManager().popBackStackImmediate();

            MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("main_f");
            fragment.handleItemPick(itemPicked);
        }
        else {
            MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("main_f");
            fragment.handleItemPick(itemPicked);
        }

    }

    @Override
    public void visibleItemMenu(){
        if (findViewById(R.id.fragment_container) != null) { //not tablet
            if (send_order_button != null) {
                send_order_button.setVisible(true);
            }
        }
        else {
//            if (send_order_button != null) {
//                send_order_button.setVisible(!send_order_button.isVisible());
//            }
        }

    }

    @Override
    public void invisibleItemMenu(){
        if (findViewById(R.id.fragment_container) != null) { //not tablet
            if (send_order_button != null) {
                send_order_button.setVisible(false);
            }
        }
        else {
//            if (send_order_button != null) {
//                send_order_button.setVisible(!send_order_button.isVisible());
//            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolBar);

        if(findViewById(R.id.fragment_container) != null) { //non-tablet layout
            if (null != savedInstanceState) {
                return;
            }
            mainFragment = new MainFragment();
            mainFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainFragment,"main_f").commit();
        }
        else {
            if (null != savedInstanceState) {
                return;
            }
            mainFragment = new MainFragment();
            foodsSelectFragment = new FoodSelectFragment();
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            transaction.add(R.id.fragment_container1, mainFragment,"main_f");
            transaction.add(R.id.fragment_container2, foodsSelectFragment);

            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_item, menu);
        send_order_button = menu.findItem(R.id.send_order_menu_button); //menu.getItem(0);
        send_order_button.setEnabled(false);
        //TODO://checkButtonValid();
        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("main_f");
        if (fragment.isVisible()){
            fragment.checkButtonValid();
        }
        else {
            invisibleItemMenu();
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.send_order_menu_button:
                sendOrderClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void checkAndSetMenuButton(Boolean foodCheckbox, Boolean checkEditTextInput){
        if (foodCheckbox && checkEditTextInput){
            if (send_order_button != null){
                send_order_button.setEnabled(true);
            }
        }
        else {
            if (send_order_button != null){
                send_order_button.setEnabled(false);
            }
        }
    }




}
