package com.example.nisan.ex4;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener,
        FoodSelectFragment.OnFragmentInteractionListener  {

    private Menu menu;
    private MenuItem send_order_button;
    public final static String EXTRA_MESSAGE = "com.example.nisan.MESSAGE";
    public final static String EXTRA_INPUTSTATE = "com.example.nisan.INPUTSTATE";
    public final static String EXTRA_CHECKBOXSTATE = "com.example.nisan.CHECKBOXSTATE";
    public final static int askForFoodType =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolBar);



//        Intent intent = getIntent();
//        String message = intent.getStringExtra(SelectFoodActivity.EXTRA_MESSAGE);
//        String inputValue = intent.getStringExtra(SelectFoodActivity.EXTRA_INPUTSTATE);
//        Boolean checkboxValue = intent.getBooleanExtra(SelectFoodActivity.EXTRA_CHECKBOXSTATE,false);
//        intent.removeExtra(EXTRA_MESSAGE);
//        if (message != null){
//            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
//            message=null;
//        }
//        if (inputValue != null){
//            numInputText.setText(inputValue);
//        }
//        if (checkboxValue != null){
//            foodCheckbox.setChecked(checkboxValue);
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == askForFoodType && resultCode == RESULT_OK && data != null) {
            Toast.makeText(getApplicationContext(),data.getExtras().getString(EXTRA_MESSAGE)
                    ,Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_item, menu);
        send_order_button = menu.findItem(R.id.send_order_menu_button); //menu.getItem(0);
        send_order_button.setEnabled(false);
        checkButtonValid();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.send_order_menu_button:
                makeOrder(item.getActionView());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
