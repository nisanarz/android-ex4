package com.example.nisan.ex4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class OrderSendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_send);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolBar);
    }
}
