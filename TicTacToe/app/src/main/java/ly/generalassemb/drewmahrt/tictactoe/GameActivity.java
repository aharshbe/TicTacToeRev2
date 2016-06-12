package ly.generalassemb.drewmahrt.tictactoe;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
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
    String player1won, player2won;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Adding reference to game message text
        gameMessage = (TextView) findViewById(R.id.game_message_text);
        //gameMessage.setText(p);
        //Setting the textview to the initial first players name!
        gameMessage.setText(getIntent().getExtras().getString("player1Name") + " " + "goes first!");
        gameMessage.setText(getIntent().getExtras().getString("player1Name") + " " + "goes first!");
        //Calling method that saves the shared pref

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
            //intending over players name
            gameMessage.setText(getIntent().getExtras().getString("player2Name") + "'s" + " " + "turn!");
            //changing color of text to match player
            gameMessage.setTextColor(Color.BLACK);
            //X's turn
            newButton.setText("X");
            newButton.setTextColor(Color.BLACK);
            //changes the color of the textView to blue when player x chooses it
//            newButton.setBackgroundColor(Color.WHITE);
        } else {
            //intending over players name
            gameMessage.setText(getIntent().getExtras().getString("player1Name") + "'s" + " " + "turn!");
            gameMessage.setTextColor(Color.DKGRAY);
            //O's turn
            newButton.setText("O");
            newButton.setTextColor(Color.DKGRAY);
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

            //Creates the shared pref to send over the name.
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MainActivity.PREFERENCES_KEY, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //because turn is reversed up at the top, you have to reverse the boolean, hence the !
            if (!turn) {
                toast("Play Again? ..Click New Game!");
                gameMessage.setText(getIntent().getExtras().getString("player1Name") + " " + "Wins!");
                player1won = getIntent().getExtras().getString("player1Name") + " " + "won previous game!!";
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.win);
                mediaPlayer.start();
                editor.putString("WinningPlayer", player1won);
                editor.commit();

            } else {
                toast("Play Again? ..Click New Game!");
                gameMessage.setText(getIntent().getExtras().getString("player2Name") + " " + "Wins!");
                player2won = getIntent().getExtras().getString("player2Name") + " " + "won previous game!!";
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.win);
                mediaPlayer.start();
                editor.putString("WinningPlayer", player2won);
                editor.commit();
            }
            //generic method call
            resetGame(false);
        } else if (turn_count == 9) {
            //when all buttons are used and no longer useable
            toast("Play Again? ..Click New Game!");
            gameMessage.setText("It's a draw! New Game?");
            String draw = new String("Last game was a draw");

            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MainActivity.PREFERENCES_KEY, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.sad);
            mediaPlayer.start();
            editor.putString("WinningPlayer", draw);
            editor.commit();
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
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        return message;
    }
}
