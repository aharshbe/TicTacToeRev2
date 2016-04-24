package ly.generalassemb.drewmahrt.tictactoe;

import android.graphics.Color;
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
    //X = true, O = false
    boolean turn = true;
    //Creates an initial counter for the turns and sets it to 0
    int turn_count = 0;

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

        //A boolean that changes the text on the textview to either x or o depending on whose turn it is
        if(turn){
            //X's turn
            newButton.setText("X");
        }else {
            //O's turn
            newButton.setText("O");
        }
        //changes the color of the button when clicked so that it's painful oby it's already been taken by other user
        newButton.setBackgroundColor(Color.RED);
        //fixes the textView from changing when clicked by two players.
        newButton.setClickable(false);
        //changes the turn itself
        turn = !turn;



    }
    private void toast(String message){
        //Making sure the buttons are clickable by creating a toast message
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
