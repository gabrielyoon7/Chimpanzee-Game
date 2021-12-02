package kr.ac.kyonggi.chimpanzee_game;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    BlockButton[][] monkeyButtons = new BlockButton[3][3];
    BlockButton[][] gorillaButtons = new BlockButton[5][4];
    BlockButton[][] chimpanzeeButtons = new BlockButton[8][5];

           @Override
           protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
           }
}
