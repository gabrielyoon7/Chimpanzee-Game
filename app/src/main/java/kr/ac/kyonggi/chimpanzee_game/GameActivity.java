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
    static int stage = 1;

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
                buttons = new BlockButton[4][5];
                break;
            case "chimpanzee":
                buttons = new BlockButton[5][8];
                break;
        }

        table = (TableLayout) findViewById(R.id.tableLayout);
        y = buttons.length;
        x = buttons[0].length;

        createStage();
    }

    public void createStage(){
        /**
         * 맵 생성 시작
         * */

        int num = 1;
        int[][] map = new int[x][y];

        while (num <= x*y) {
            int i = (int) (Math.random() * y);//시드값 필
            int j = (int) (Math.random() * x);//시드값 필
//            System.out.println(i+" and "+j);
            if (map[j][i] > 0) { // 중복 시 재시도
                continue;
            } else {
                map[j][i] = num; // 숫자 배정
            }
            num++;
        }

        /**
         * 맵 생성 끝
         * */

        for (int i =0 ; i <y ; i++){
            for(int j=0; j<x ; j++){
                System.out.printf("\t"+map[j][i]);
            }
             System.out.println();
        }


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


                /**
                 * 버튼 활성화 / 비활성화 여부를 여기서 결정
                 * */
                
            }
            table.addView(tableRow);
        }
    }
}
