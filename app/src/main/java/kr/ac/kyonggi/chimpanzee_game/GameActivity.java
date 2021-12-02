package kr.ac.kyonggi.chimpanzee_game;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    BlockButton[][] buttons = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(ModeActivity.mode);
        switch (ModeActivity.mode) {
            case "monkey":
                buttons = new BlockButton[3][3];
                break;
            case "gorilla":
                buttons = new BlockButton[5][4];
                break;
            case "chimpanzee":
                buttons = new BlockButton[8][5];
                break;
        }

    }
}
