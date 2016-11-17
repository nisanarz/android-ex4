package com.example.nisan.ex4;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private EditText numInputText;
    private CheckBox foodCheckbox;
    private Button orderButton;
    private Button selectButton;
    private SeekBar seekBar;
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

        numInputText = (EditText) findViewById(R.id.input_id);
        foodCheckbox = (CheckBox) findViewById(R.id.checkbox_id);
        orderButton = (Button) findViewById(R.id.button_id);
        seekBar = (SeekBar) findViewById(R.id.seek_id);
        selectButton = (Button) findViewById(R.id.select_button_id);

        inputTextHandler();
        seekbarHandler();
        checkBoxHandler();


        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectFoodActivity.class);
                startActivityForResult(intent,askForFoodType);
            }
        });

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

    private void inputTextHandler(){
        numInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkButtonValid();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputText = (String) s.toString();
                double sheep_num = 0;
                if (inputText.length() > 0 || (inputText.length() > 0 && inputText.length() < 10)) {
                    sheep_num = Double.parseDouble(inputText);
                }
                if (sheep_num >100){
                    seekBar.setProgress(seekBar.getMax());
                }
                else {
                    seekBar.setProgress((int)sheep_num);
                }
                checkButtonValid();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void seekbarHandler(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                String str = Integer.toString(progressChanged);
                if (fromUser){
                    if (progressChanged == 0){
                        numInputText.setText("");
                        return;
                    }
                    numInputText.setText(str);
                    numInputText.setSelection(str.length());
                    checkButtonValid();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void checkBoxHandler(){
        foodCheckbox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkButtonValid();
            }
        });
    }

    public void makeOrder(View view){
        orderButton = (Button) findViewById(R.id.button_id);
        numInputText = (EditText) findViewById(R.id.input_id);
        foodCheckbox = (CheckBox) findViewById(R.id.checkbox_id);

        //Toast.makeText(getApplicationContext(),"Order sent",Toast.LENGTH_SHORT).show();
        orderButton.setEnabled(false);
        numInputText.setText("");
        foodCheckbox.setChecked(false);

        Intent intent = new Intent(this, OrderSendActivity.class);
        startActivity(intent);

    }

//    public void selectFood(View view){
//        Intent intent = new Intent(this, SelectFoodActivity.class);
//        intent.putExtra(EXTRA_INPUTSTATE, numInputText.getText().toString());
//        intent.putExtra(EXTRA_CHECKBOXSTATE, foodCheckbox.isChecked());
//        startActivity(intent);
//    }


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

    private void checkButtonValid(){
        if (foodCheckbox.isChecked() && checkEditTextInput(numInputText)){
            orderButton.setEnabled(true);
            if (send_order_button != null){
                send_order_button.setEnabled(true);
            }
        }
        else {
            orderButton.setEnabled(false);
            if (send_order_button != null){
                send_order_button.setEnabled(false);
            }
        }
    }

    private boolean checkEditTextInput(EditText et){
        String text = et.getText().toString();
        double sheep_num = 0;
        boolean flag=false;
        if (text.length() > 0 || (text.length() > 0 && text.length() < 10)) {

            sheep_num = Double.parseDouble(text);//Integer.parseInt(text);
            if (sheep_num > 0 && sheep_num < Integer.MAX_VALUE) {
                return true;
            }
            else {
                flag=false;
            }
        }
        return flag;
    }

}
