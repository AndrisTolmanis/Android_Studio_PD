package com.example.andristolmanis.praktiskaisdarbs1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonDialog;
    Button buttonActivityTwo;
    Button btnSave;
    String[] namesArray;
    SharedPreferences sp;
    EditText textt;

    public static final String USER_PREF = "USER_PREF" ;
    public static final String KEY_TEXT = "KEY_TEXT";


    protected String izvelets(boolean check){
        if(check == true){
            return "tiek izvēlēts!";
        }
        else {
            return "tiek neizvēlēts!";
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonDialog = (Button) findViewById(R.id.buttonDialogs);
        buttonActivityTwo = (Button) findViewById(R.id.buttonActivity2);

        // PD3
        btnSave = (Button) findViewById(R.id.btnSave);
        sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        textt = (EditText) findViewById(R.id.textToBeSaved);

        buttonDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                namesArray = new String[] {"Andris Tolmanis", "Dita Prūse", "Annija Viktorija Zaķe"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("2. Grupas dialoga logs!");
                final boolean[] checkedNames = new boolean[]{false, false, false};
                final List<String> namesList = Arrays.asList(namesArray);
                dialog.setMultiChoiceItems(namesArray, checkedNames, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        String currentItem = namesList.get(which);
                        Toast.makeText(MainActivity.this, currentItem+ " "+ izvelets(isChecked), Toast.LENGTH_SHORT).show();
                    }
                });
                // Dialog "yes" button
                dialog.setPositiveButton("Labi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Labi poga tika nospiesta!", Toast.LENGTH_SHORT).show();
                    }
                });
                // Dialog "cancel" button
                dialog.setNeutralButton("Atcelt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Atclet poga tika nospiesta!", Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog dialogShow = dialog.create();
                dialogShow.show();
            }

        });
        buttonActivityTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondaryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        // PD3
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name  = textt.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString(KEY_TEXT, name);
                editor.commit();

                Toast.makeText(MainActivity.this, "Saglabāts!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showAlertDialog(View v){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("2. Grupas dialoga logs");
            dialog.setMessage("");
            dialog.setPositiveButton("Labi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

    }
}
