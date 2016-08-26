package com.example.sarika.checkprime2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HintActivity extends AppCompatActivity {

    private static final String TAG4 = "HintActivityHint";
    private Button button_hint_show;
    private Button button_back;
    private TextView text_showHint;
    private int clicked_hint = 0;
    private String showHint = " ";

    @Override
    protected void onCreate(Bundle savedInstanceStateH) {
        super.onCreate(savedInstanceStateH);
        setContentView(R.layout.activity_hint);

        button_hint_show = (Button) findViewById(R.id.button_showhint);
        button_back = (Button) findViewById(R.id.button_back);
        text_showHint = (TextView) findViewById(R.id.text_hint);

        /* Get the value from the saved instance if instance saved */
        if (savedInstanceStateH != null) {
            clicked_hint = savedInstanceStateH.getInt(TAG4);
            if (clicked_hint == 1) {
                showHint = "Check whether the number is divisible by numbers less than its square root other than 1. If so then it is not prime otherwise it is prime";
            } else {
                showHint = " ";
            }
            text_showHint.setText(showHint);
        }

        //Shows the hint when 'Show Hint' button is pressed
        button_hint_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_hint = 1;
                showHint = "Check whether the number is divisible by numbers less than its square root other than 1. If so then it is not prime otherwise it is prime";
                text_showHint.setText(showHint);
            }
        });

        /*
         * Finishes the activity when 'Back' button is pressed
         * and returns a value indicating whether user has taken the hint or not to the parent activity(MainActivity)
         * */
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gotoMain = new Intent(HintActivity.this, MainActivity.class);
                if (clicked_hint == 1) {
                    gotoMain.putExtra("hint_seen", 1);
                } else {
                    gotoMain.putExtra("hint_seen", 0);
                }
                setResult(RESULT_OK, gotoMain);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hint, menu);
        return true;
    }


    /* Saving the instance */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceStateH) {
        savedInstanceStateH.putInt(TAG4, clicked_hint);
        super.onSaveInstanceState(savedInstanceStateH);
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


}
