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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Referencing the EditText fields.
        EditText player_one_name = (EditText) findViewById(R.id.player_one_name);
        EditText getPlayer_two_name= (EditText) findViewById(R.id.player_two_name);

        //Getting the text from the editText field.
        String playerOnegetText= new String(player_one_name.getText().toString());
        String playerTwoGetText = new String(getPlayer_two_name.getText().toString());


        //Creating the sharedPreferences to send over the string to the other activity and store them
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("austin_is_Awesom", Context.MODE_PRIVATE);

        //To read information for share preferences
        String person1Name = sharedPref.getString("person1Name",playerOnegetText);
        String person2Name = sharedPref.getString("person2Name",playerTwoGetText);

        //To save string
        SharedPreferences.Editor person1 = sharedPref.edit();
        SharedPreferences.Editor person2 = sharedPref.edit();
        //Creating a key and assigning the value for each of the editText fields to store the names so they can be used in the Game activity
        person1.putString(person1Name, playerOnegetText);
        person2.putString(person2Name, playerTwoGetText);
        //Commiting the changes to shared preferences
        person1.commit();
        person2.commit();



    }

    //Creates and intent to sending the user from this activity onto the next.

    public void whenStartPlayingClicked(View view) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);


    }

}