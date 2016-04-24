package ly.generalassemb.drewmahrt.tictactoe;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener; //Imports the ability to implement OnClick
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    //Creating a reference for my buttons
    TextView button1, button2, button3, button4, button5, button6, button7, button8, button9;
    //Creating new object of textView to ref
    TextView gameMessage;
    //Creating newGame button
    Button newGame;
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
        //Adding reference to game message text
        gameMessage = (TextView) findViewById(R.id.game_message_text);
        //gameMessage.setText(p);
        //Setting the textview to the initial first players name!
        gameMessage.setText(getIntent().getExtras().getString("player1Name") + " " + "goes first!");

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
        newGame = (Button) findViewById(R.id.newGame);
        //Instantiating the textView array
        buttonArray = new TextView[]{button1, button2, button3, button4, button5, button6, button7, button8, button9};
        //Creates a loop for every textView to take an action when clicked
        for (TextView looppingTextViews : buttonArray) {
            //Sets the onClick and uses the class as the handler for the event
            looppingTextViews.setOnClickListener(this);
        }
        //Creates onClick listener for the newGame button
        newGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //to reset turns and re-initialize all buttons
                turn = true;
                turn_count = 0;
                resetGame(true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        //method called when person clicks
        //Instantiates a new textView so that we can pass it as a parameter in the following method call
        TextView newButton = (TextView) v;
        //Calls the button clicked method on every click
        buttonClicked(newButton);
    }

    public void buttonClicked(TextView newButton) {

        //A boolean that changes the text on the textview to either x or o depending on whose turn it is
        if (turn) {
            gameMessage.setText(getIntent().getExtras().getString("player2Name") + "'s" + " " + "turn!");
            //X's turn
            newButton.setText("X");
            //changes the color of the textView to blue when player x chooses it
            newButton.setBackgroundColor(Color.BLUE);
        } else {
            gameMessage.setText(getIntent().getExtras().getString("player1Name") + "'s" + " " + "turn!");
            //O's turn
            newButton.setText("O");
            //changes the color of the textView to red when player o chooses it
            newButton.setBackgroundColor(Color.MAGENTA);
        }
        //just the increment counter
        turn_count++;

        //fixes the textView from changing when clicked by two players.
        newButton.setClickable(false);
        //changes the turn itself
        turn = !turn;

        checkForWinner();
    }

    public void checkForWinner() {

        boolean there_is_a_winner = false;

        //checks horozontal numbers
        //by adding that the first button is not clickable it got it to work instead of always saying one is winning when clicked
        if (button1.getText() == button2.getText() && button2.getText() == button3.getText() && !button1.isClickable())
            there_is_a_winner = true;
        else if (button4.getText() == button5.getText() && button5.getText() == button6.getText() && !button4.isClickable())
            there_is_a_winner = true;
        else if (button7.getText() == button8.getText() && button8.getText() == button9.getText() && !button7.isClickable())
            there_is_a_winner = true;

        //vertical
        if (button1.getText() == button4.getText() && button4.getText() == button7.getText() && !button1.isClickable())
            there_is_a_winner = true;
        else if (button2.getText() == button5.getText() && button5.getText() == button8.getText() && !button2.isClickable())
            there_is_a_winner = true;
        else if (button3.getText() == button6.getText() && button6.getText() == button9.getText() && !button3.isClickable())
            there_is_a_winner = true;

        //Digonal
        if (button1.getText() == button5.getText() && button5.getText() == button9.getText() && !button1.isClickable())
            there_is_a_winner = true;
        else if (button3.getText() == button5.getText() && button5.getText() == button7.getText() && !button3.isClickable())
            there_is_a_winner = true;


        if (there_is_a_winner) {
            //because turn is reversed up at the top, you have to reverse the boolean, hence the !
            if (!turn) {
                toast("Player 1 wins!");
                gameMessage.setText("Play again?");
                String player1won = new String("Player 1 won previous game");

            } else {
                toast("Player 2 wins!");
                gameMessage.setText("Play again?");
                String player2won = new String("Player 2 won previous game");



            }
            //generic method call
            resetGame(false);

        } else if (turn_count == 9) {
            //when all buttons are used and no longer useable
            toast("No one wins sadly");
            gameMessage.setText("Play again?");
            String draw = new String("It was a draw");


        }

    }

    //Creates method to reset the game if a player wins
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void resetGame(boolean enable) {
        //false to disable
        for (TextView reset : buttonArray) {
            reset.setClickable(enable);

            //for the color
            if (enable) {
                reset.setBackgroundColor(Color.TRANSPARENT);
                reset.setBackground(getDrawable(R.drawable.border));
                //Resetting default name to the original first player
                gameMessage.setText(getIntent().getExtras().getString("player1Name") + " " + "goes first!");
                //reset the text to be an empty string
                reset.setText(" ");



            } else
                reset.setBackgroundColor(Color.GREEN);
        }
    }

    private String toast(String message) {
        //Making sure the buttons are clickable by creating a toast message
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return message;
    }


//    private String winningPlayer() {
//        if (checkForWinner() == "Player 1 won previous game"){
//            return player1won;
//
//        }else if (checkForWinner() == "Player 2 won previous game"){
//            String player2won = new String ("Player 2 won previous game");
//            return player2won;
//
//        }
//
//        String draw = new String ("It was a draw!");
//        return draw;
//
//    }

    private String sendingOverNameFromSP() {
        SharedPreferences prefs = getSharedPreferences("WinningPlayer", MODE_PRIVATE);
        String winningPlayer = prefs.getString("winning player", "Player 1 won previous match");
        return winningPlayer;
    }

}
