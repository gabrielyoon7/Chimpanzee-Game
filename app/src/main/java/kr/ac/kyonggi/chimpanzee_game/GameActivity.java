package kr.ac.kyonggi.chimpanzee_game;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {

    BlockButton[][] buttons = null;
    int[][] map = null;
    TableLayout table = null;
    int x;
    int y;
    static int stage = 1;
    static int life = 3;
    int round ;
    TextView lifeView;
    TextView stageView;
    TextView roundView;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        name = intent.getStringExtra("nickName");

        lifeView = (TextView) findViewById(R.id.life);
        stageView = (TextView) findViewById(R.id.stage);
        roundView = (TextView) findViewById(R.id.round);
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
        roundView.setText(x*y+"");
        round = x*y;
        createStage();
    }

    public void createMap(){

        /**
         * 맵 생성 시작
         * */
        map = new int[x][y];
        int num = 1;

        while (num <= x*y) {
            int i = (int) (Math.random() * y);//시드값 필
            int j = (int) (Math.random() * x);//시드값 필

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
    }

    public void createStage(){
        if(stage==1) {
            new AlertDialog.Builder(this)
                    .setTitle("게임방법")
                    .setMessage("번호를 기억해두었다가 순서대로 클릭해주세요!\n1을 클릭할시 모든 번호가 사라집니다.")
                    .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dlg, int sumthin) {
                        }
                    })
                    .show(); // 팝업창 보여줌
        }
        table.removeAllViews();
        BlockButton.blockCount=1;
        createMap();
        for (int i = 0; i < y; i++) { // 버튼 생성
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < x; j++) {
                buttons[i][j] = new BlockButton(this, i, j,map[j][i]);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f);
//                buttons[i][j].setBackgroundColor(Color.parseColor("#ff33b5e5"));
                buttons[i][j].setLayoutParams(layoutParams);
                tableRow.addView(buttons[i][j]);

                buttons[i][j].x = i;
                buttons[i][j].y = j;


                /**
                 * 버튼 활성화 / 비활성화 여부를 여기서 결정
                 * */
                if(buttons[i][j].blockNumber > stage){ // 버튼 비활성화
                    buttons[i][j].setVisibility(View.INVISIBLE);
                }

                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean game = false;
                       game = ((BlockButton) view).breakBlock(view);

                       if(BlockButton.blockCount==1){ // hide가 안됨
                           for (int i = 0; i <y ; i++) {
                               for (int j = 0; j < x; j++) {
                                   buttons[i][j].hideNumber();
                               }
                           }
                       }
                       if(life == 0) {
                           if(stage > (x*y)/2 ){ // good 종료 화면
                               Intent intent = new Intent(getApplicationContext(),GoodEndActivity.class);
                               intent.putExtra("stage",(stage-1));
                               intent.putExtra("total",(x*y));
                               intent.putExtra("nickName",name);
                               startActivity(intent);
                           }
                           else { // bad 종료화면으로 이동
                               Intent intent = new Intent(getApplicationContext(),BadEndActivity.class);
                               intent.putExtra("stage",(stage-1));
                               intent.putExtra("total",(x*y));
                               intent.putExtra("nickName",name);
                               startActivity(intent);
                           }
                       } else if(life > 0 ){
                           lifeView.setText(life+"");
                           if( stage == BlockButton.blockCount){
                               if(stage < x*y){ // 다음 스테이지로 진행시
                                   stage ++;
                                   stageView.setText(stage+"");
                                   round --;
                                   roundView.setText(round+"");
                                   createStage();
                               } else if(stage == x*y){ // 결과 화면으로 이동
                                   Intent intent = new Intent(getApplicationContext(),GoodEndActivity.class);
                                   intent.putExtra("stage",stage);
                                   intent.putExtra("total",(x*y));
                                   intent.putExtra("nickName",name);
                                   startActivity(intent);
                               }
                           }
                           else{ // 동일 스테이지에서 버튼 누른 후 블록 카운트 증가
                               if(game){
                                   ((BlockButton) view).addBlockCount(); // 블록 카운트 증가
                               }
                           }
                       }
                    }
                });
                
            }
            table.addView(tableRow);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item ){

        switch (item.getItemId()){
            case R.id.toMain:
                restart(getApplicationContext());
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.toEnd:
                finishAffinity();
                System.runFinalization();
                System.exit(0);
                return true;

        }
        return super.onContextItemSelected(item);
    }
    private void restart(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }
}
