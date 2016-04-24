package ly.generalassemb.drewmahrt.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener; //Imports the ability to implement OnClick
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Creating new instances of EditText and String to references below
    EditText player1, player2;
    String player1GetText, player2GetText;
    TextView player1tv, player2tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Creates and intent to sending the user from this activity onto the next.
    //OnClick created in the XML for ease ;)

    public void whenStartPlayingClicked(View view) {

        //Referencing the EditText fields.
        player1 = (EditText) findViewById(R.id.player_one_name);
        player2 = (EditText) findViewById(R.id.player_two_name);
        //Getting the text from the editText field.
        player1GetText= new String(player1.getText().toString());
        player2GetText = new String(player2.getText().toString());

        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("player1Name", player1.getText().toString());
        intent.putExtra("player2Name", player2.getText().toString());
        startActivity(intent);




        //Creating a reference for the textViews which show the players name
        player1tv =  (TextView) findViewById(R.id.player1tv);
        player2tv =  (TextView) findViewById(R.id.player2tv);
        //Setting the text of the EditText fields to reflect the two players input
        player1tv.setText(player1GetText);
        player2tv.setText(player2GetText);
        //Calling both function to send over using SP
//        sendingOverNameFromSP();
//        storeNamesToSP();

    }

//    private String sendingOverNameFromSP() {
//        SharedPreferences prefs = getSharedPreferences("PlayersNames", MODE_PRIVATE);
//        String player1Coming = prefs.getString(player1GetText, "No name sent");
//       // String player2Coming = prefs.getString(player2GetText, null);
//        return player1Coming;
//    }
//
//    private void storeNamesToSP(){
//        SharedPreferences prefs = getSharedPreferences("PlayersNames", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("Player1Name", player1GetText);
//        editor.commit();
//
//    }
}