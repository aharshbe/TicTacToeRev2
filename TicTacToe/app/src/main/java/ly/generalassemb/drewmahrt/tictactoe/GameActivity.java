package ly.generalassemb.drewmahrt.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener; //Imports the ability to implement OnClick
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    //Creating a reference for my buttons
    TextView button1, button2, button3, button4, button5, button6, button7, button8, button9;
    //Creating a button/TextView array to store the buttons
    TextView[] buttonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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
        //Creates a loop for every textView to take an action when clicked
        for(TextView looppingTextViews : buttonArray){
            //Sets the onClick and uses the class as the handler for the event
            looppingTextViews.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View v) {
        //method called when person clicks
        //calling the toast message and assigning it  a message when clicked.
        toast("TextView Button clicked");
        //Instantiates a new textView so that we can pass it as a parameter in the following method call
        TextView newButton = (TextView) v;
        //Calls the button clicked method on every click
        buttonClicked(newButton);
    }

    public void buttonClicked(TextView newButton){

    }
    private void toast(String message){
        //Making sure the buttons are clickable by creating a toast message
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
