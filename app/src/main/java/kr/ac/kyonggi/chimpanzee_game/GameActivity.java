package kr.ac.kyonggi.chimpanzee_game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    BlockButton[][] buttons = null;
    TableLayout table = null;
    int x;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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

        table = (TableLayout) findViewById(R.id.tableLayout);
        y = buttons.length;
        x = buttons[0].length;

        for (int i = 0; i < y; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < x; j++) {
                buttons[i][j] = new BlockButton(this, i, j);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f);
                buttons[i][j].setLayoutParams(layoutParams);
                tableRow.addView(buttons[i][j]);

                buttons[i][j].x = i;
                buttons[i][j].y = j;
                System.out.println(table);
                System.out.println(tableRow);

            }
            table.addView(tableRow);
        }
    }
}
