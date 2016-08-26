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

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView question;
    private Button button_true;
    private Button button_false;
    private Button button_next;
    private Button button_hint;
    private Button button_cheat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = (TextView) findViewById(R.id.text_question);
        /* Get the value from the saved instance if instance saved and displaying the question */
        if (savedInstanceState != null) {
            int val = savedInstanceState.getInt(TAG);
            question.setText(val + " is a prime number");
        } else {
            Random r1 = new Random();
            int val = r1.nextInt(1000 - 1) + 1;
            question.setText(val + " is a prime number");
        }
        button_true = (Button) findViewById(R.id.button_true);
        button_false = (Button) findViewById(R.id.button_false);
        button_next = (Button) findViewById(R.id.button_next);
        button_hint = (Button) findViewById(R.id.button_hint);
        button_cheat = (Button) findViewById(R.id.button_cheat);

        //Sends the number to CheckTrue function if True button is clicked
        button_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques = (String) question.getText();
                String quest[] = ques.split(" ");
                int value = Integer.parseInt(quest[0]);
                view.requestFocus();
                CheckTrue(value);
            }
        });

        //Sends the number to CheckFalse function if False button is clicked
        button_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques = (String) question.getText();
                String quest[] = ques.split(" ");
                int value = Integer.parseInt(quest[0]);
                view.requestFocus();
                CheckFalse(value);
            }
        });

        /* Sets the TextView to display a new question when next button is pressed */
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r1 = new Random();
                int val = r1.nextInt(1000 - 1) + 1;
                question.setText(val + " is a prime number");
                view.requestFocus();
            }
        });

        //Moves to HintActivity activity using intent when Hint button is pressed
        button_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = 0;
                Intent gotoHint = new Intent(MainActivity.this, HintActivity.class);
                //startActivityForResult is used as we expect some value to be returned from HintActivity when it finishes
                startActivityForResult(gotoHint, result);
            }
        });

        //Moves to CheatActivity activity using intent when Cheat button is pressed
        button_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques = (String) question.getText();
                String quest[] = ques.split(" ");
                int result = 1;
                Intent gotoCheat = new Intent(MainActivity.this, CheatActivity.class);
                String value = quest[0];
                //putExtra is used to send data from MainActivity to CheatActivity
                gotoCheat.putExtra("value", value);
                //startActivityForResult is used as we expect some value to be returned from CheatActivity when it finishes
                startActivityForResult(gotoCheat, result);
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

    /* Saving the instance */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        String ques = question.getText().toString();
        String quest[] = ques.split(" ");
        int val = Integer.parseInt(quest[0]);
        savedInstanceState.putInt(TAG, val);
        super.onSaveInstanceState(savedInstanceState);
    }

    /*
    * This checks from which child Activity the result has been returned and generates corresponding toast
    * whenever the child activity finishes it returns to this method
    * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            int value = data.getIntExtra("hint_seen", 0);
            if (value == 1) {
                Toast.makeText(this, "You have used hint...", Toast.LENGTH_SHORT).show();
            }
        } else {
            int value = data.getIntExtra("cheat_seen", 0);
            if (value == 1) {
                Toast.makeText(this, "You have cheated...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /* Displays Correct if the number is not prime and Wrong if the number is prime through the use of a toast */
    public void CheckFalse(int value) {
        int c = Check(value);
        if (c == 0) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else if (c == 1) {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "1 is neither a prime nor a composite number ", Toast.LENGTH_SHORT).show();
        }


    }

    /* Displays Correct if the number is prime and Wrong if the number is not prime through the use of a toast */
    public void CheckTrue(int value) {
        int c = Check(value);
        if (c == 1) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else if (c == 0) {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "1 is neither a prime nor a composite number ", Toast.LENGTH_SHORT).show();
        }


    }

    /* Returns integer value 0 if the passed argument is not prime and returns 1 if prime */
    private int Check(int check) {
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