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
import android.widget.Toast;


public class CheatActivity extends AppCompatActivity {

    private static final String TAG1 = "CheatActivityNumber";
    private static final String TAG2 = "CheatActivityCheck";
    private static final String TAG3 = "CheatActivityResult";
    private Button button_cheat;
    private Button button_back;
    private TextView text_showCheat;
    private int clicked_cheat = 0;
    private int num = 0;
    private int result = 0;
    private String cheat_ans = "";

    @Override
    protected void onCreate(Bundle savedInstanceStateC) {
        super.onCreate(savedInstanceStateC);
        setContentView(R.layout.activity_cheat);

        button_cheat = (Button) findViewById(R.id.button_showcheat);
        button_back = (Button) findViewById(R.id.button_back_cheat);
        text_showCheat = (TextView) findViewById(R.id.text_cheat);

        /* Get the value from the saved instance if instance saved */
        if (savedInstanceStateC != null) {
            num = savedInstanceStateC.getInt(TAG1);
            clicked_cheat = savedInstanceStateC.getInt(TAG2);
            result = savedInstanceStateC.getInt(TAG3);
            if (result == 1) {
                cheat_ans = " " + num + " is prime";
            } else if (result == 0) {
                cheat_ans = " " + num + " is not prime";
            } else {
                cheat_ans = " " + num + " is neither prime nor composite";
            }
            text_showCheat.setText(cheat_ans);
        }

        //Shows whether the number is prime or not when 'Show Cheat' button is pressed
        button_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_cheat = 1;
                String numString;
                //Bundle object is created to access the values from the MainActivity
                Bundle passed_value = getIntent().getExtras();
                if (passed_value != null) {
                    numString = passed_value.getString("value");
                    num = Integer.parseInt(numString);
                    result = checkPrime(num);
                    if (result == 1) {
                        cheat_ans = " " + num + " is prime";
                    } else if (result == 0) {
                        cheat_ans = " " + num + " is not prime";
                    } else {
                        cheat_ans = " " + num + " is neither prime nor composite";
                    }
                    text_showCheat.setText(cheat_ans);
                } else {
                    System.out.println("Problem loading number");
                }
            }
        });

        /*
         * Finishes the activity when 'Back' button is pressed
         * and returns a value indicating whether user has cheated or not to the parent activity(MainActivity)
         * */
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gotoMain = new Intent(CheatActivity.this, MainActivity.class);
                if (clicked_cheat == 1) {
                    gotoMain.putExtra("cheat_seen", 1);
                } else {
                    gotoMain.putExtra("cheat_seen", 0);
                }
                setResult(RESULT_OK, gotoMain);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cheat, menu);
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

    /* Saving the instance */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceStateC) {
        savedInstanceStateC.putInt(TAG1, num);
        savedInstanceStateC.putInt(TAG2, clicked_cheat);
        super.onSaveInstanceState(savedInstanceStateC);
    }

    //Checks whether the number passed from the MainActivity is prime or not
    private int checkPrime(int check) {
        int ans = 1;
        if (check == 1) {
            ans = 3;
            return ans;
        }
        for (int j = 2; j < check / 2; j++) {
            if ((check % j) == 0) {
                ans = 0;//is not prime
                return ans;
            } else {
                ans = 1;//is prime
            }

        }
        return ans;
    }
}
