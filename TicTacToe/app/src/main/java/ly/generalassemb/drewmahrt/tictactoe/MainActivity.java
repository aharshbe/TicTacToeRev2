package ly.generalassemb.drewmahrt.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Creating a reference for my buttons
    TextView button1, button2, button3, button4, button5, button6, button7, button8, button9;

    //Creating a button/TextView array to store the buttons
    TextView[] buttonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking the buttons/textViews to the xml

        button1 = (TextView) findViewById(R.id.textView);
        button2 = (TextView) findViewById(R.id.textView2);
        button3 = (TextView) findViewById(R.id.textView3);
        button4 = (TextView) findViewById(R.id.textView4);
        button5 = (TextView) findViewById(R.id.textView5);
        button6 = (TextView) findViewById(R.id.textView6);
        button7 = (TextView) findViewById(R.id.textView7);
        button8 = (TextView) findViewById(R.id.textView8);
        button9 = (TextView) findViewById(R.id.textView9);

        //Instantiating the textView array
        buttonArray = new TextView[]{button1, button2, button3, button4,  button5, button6, button7,  button8, button9};



    }
}
