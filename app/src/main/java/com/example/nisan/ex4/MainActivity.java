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
    static final String MAIN_FRAGMENT_TAG = "main_fragment";


    @Override
    public void sendOrderClicked() {
        Intent intent = new Intent(this, OrderSendActivity.class);
        startActivity(intent);
    }

    @Override
    public void selectButtonClicked() {
//        Bundle args = new Bundle();
//        foodsSelectFragment = new FoodSelectFragment();
//        foodsSelectFragment.setArguments(args);
//
//        if (findViewById(R.id.fragment_container) != null) { //not tablet
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.fragment_container, foodsSelectFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }
    }

    @Override
    public void itemPicked(int itemPicked){


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
            getFragmentManager().beginTransaction().add(R.id.fragment_container, mainFragment).commit();
        }
        //TODO: implement for tablet

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_item, menu);
        send_order_button = menu.findItem(R.id.send_order_menu_button); //menu.getItem(0);
        send_order_button.setEnabled(false);
        //checkButtonValid();
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
