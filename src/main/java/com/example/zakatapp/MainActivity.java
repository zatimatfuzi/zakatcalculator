package com.example.zakatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText goldWeight;
    EditText goldValue;
    Button calculate, load;
    TextView output;
    TextView output2;
    TextView output3;
    RadioButton keep, wear;

    String SHARED_PREFS;
    String gold_weight = "goldWeight";
    String gold_value = "goldValue";
    String textview = "output";
    String textview2 = "output2";
    String textview3 = "output3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goldWeight = findViewById(R.id.goldWeightL);
        goldValue = findViewById(R.id.goldValueL);
        calculate = findViewById(R.id.button);
        load = findViewById(R.id.button3);
        output = findViewById(R.id.textView);
        output2 = findViewById(R.id.textView2);
        output3 = findViewById(R.id.textView3);
        keep = findViewById(R.id.radioButton);
        wear = findViewById(R.id.radioButton2);

        calculate.setOnClickListener(this::onClick);
        load.setOnClickListener(this::loadData);

    }



    @Override
    public void onClick(View view) {
        saveData(view.getId(), view);
    }
    public void loadData(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        gold_weight = sharedPreferences.getString("goldWeight", "");
        goldWeight.setText(gold_weight);
        gold_value = sharedPreferences.getString("goldValue", "");
        goldValue.setText(gold_value);
        textview = sharedPreferences.getString("output", "");
        output.setText(textview);
        textview2 = sharedPreferences.getString("output2", "");
        output2.setText(textview2);
        textview3 = sharedPreferences.getString("output3", "");
        output3.setText(textview3);
    }

    public void saveData(int viewId, View view){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //save data
        editor.putString("goldWeight", goldWeight.getText().toString());
        editor.putString("goldValue", goldValue.getText().toString());

        switch (viewId){
            case R.id.button:
                //total value of the gold
                double goldBerat = Double.parseDouble(goldWeight.getText().toString());
                double goldnilai = Double.parseDouble(goldValue.getText().toString());
                double vog = goldBerat*goldnilai;
                output.setText("The total value of gold: RM" + vog);

                //total gold value that is zakat payable
                int x = 0;
                if(keep.isChecked()){
                    //editor.putString("keep", "Type of gold: Keep(85grams)");
                    x = 85;
                }
                else if(wear.isChecked()){
                    //editor.putString("wear", "Type of gold: Wear(200grams)")
                    x = 200;
                }
                double zakatPayble = (goldBerat - x)*goldnilai;
                output2.setText("Total gold value that is zakat payable: RM" + zakatPayble);

                //the total zakat
                double totZakat = 0;
                totZakat = 0.025*zakatPayble;
                output3.setText("The total zakat: RM" + totZakat);

                break;
        }
        editor.putString("output", output.getText().toString());
        editor.putString("output2", output2.getText().toString());
        editor.putString("output3", output3.getText().toString());
        editor.apply();

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.about:
                //Toast.makeText(this,"This is about",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);

                break;

            case R.id.settings:

                Toast.makeText(this,"This is settings",Toast.LENGTH_LONG).show();
                break;

            case R.id.search:

                Toast.makeText(this,"This is search",Toast.LENGTH_LONG).show();

                break;

            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }

}