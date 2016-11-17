package com.example.nisan.ex4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class SelectFoodActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.nisan.MESSAGE";
    public final static String EXTRA_INPUTSTATE = "com.example.nisan.INPUTSTATE";
    public final static String EXTRA_CHECKBOXSTATE = "com.example.nisan.CHECKBOXSTATE";

    private String inputbox;
    private Boolean checkbox;
    ListView listView;

    private String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);

        Intent intent = getIntent();
        inputbox = intent.getStringExtra(MainActivity.EXTRA_INPUTSTATE);
        checkbox = intent.getBooleanExtra(MainActivity.EXTRA_CHECKBOXSTATE,false);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolBar);

        values = getResources().getStringArray(R.array.foods);
        listView = (ListView) findViewById(R.id.listview_id);
        CustomFruitListAdapter adapter = new CustomFruitListAdapter(this,values);
        listView.setAdapter(adapter);

        listViewHandler();
    }

    private void listViewHandler(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position, long id){
                Intent output = new Intent();
                output.putExtra(EXTRA_MESSAGE , values[position]);
                setResult(RESULT_OK, output);
                finish();
            }
        });
    }

}
