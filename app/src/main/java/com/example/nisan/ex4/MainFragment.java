package com.example.nisan.ex4;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Created by narzoan on 11/19/2016.
 */

public class MainFragment extends Fragment {


    private EditText numInputText;
    private CheckBox foodCheckbox;
    private Button orderButton;
    private Button selectButton;
    private SeekBar seekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View returnView =  inflater.inflate(R.layout.fragment_main, container, false);

        numInputText = (EditText) returnView.findViewById(R.id.input_id);
        foodCheckbox = (CheckBox) returnView.findViewById(R.id.checkbox_id);
        orderButton = (Button) returnView.findViewById(R.id.button_id);
        seekBar = (SeekBar) returnView.findViewById(R.id.seek_id);
        selectButton = (Button) returnView.findViewById(R.id.select_button_id);

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


        return returnView;
    }

    public interface OnFragmentInteractionListener {

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
        orderButton = (Button) view.findViewById(R.id.button_id);
        numInputText = (EditText) view.findViewById(R.id.input_id);
        foodCheckbox = (CheckBox) view.findViewById(R.id.checkbox_id);

        //Toast.makeText(getApplicationContext(),"Order sent",Toast.LENGTH_SHORT).show();
        orderButton.setEnabled(false);
        numInputText.setText("");
        foodCheckbox.setChecked(false);

        Intent intent = new Intent(this, OrderSendActivity.class);
        startActivity(intent);

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