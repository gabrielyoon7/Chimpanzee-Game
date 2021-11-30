package kr.ac.kyonggi.chimpanzee_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

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

