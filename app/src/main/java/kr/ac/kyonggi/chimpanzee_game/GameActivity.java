package kr.ac.kyonggi.chimpanzee_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity {

    BlockButton[][] monkeyButtons = new BlockButton[3][3];
    BlockButton[][] gorillaButtons = new BlockButton[5][4];
    BlockButton[][] chimpanzeeButtons = new BlockButton[8][5];


    Button monkeyButton = (Button) findViewById(R.id.monkeyMode);
    Button gorillaButton = (Button) findViewById(R.id.gorillaMode);
    Button chimpanzeeButton = (Button) findViewById(R.id.chimpanzeeMode);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        monkeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class); // 클래스 새로 만들어서 변경할 것
                startActivity(intent);
            }
        });

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
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.toEnd:

                return true;
            case R.id.toStart:
                Intent intent3 = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(intent3);
                return true;


        }
        return super.onContextItemSelected(item);
    }

    }

