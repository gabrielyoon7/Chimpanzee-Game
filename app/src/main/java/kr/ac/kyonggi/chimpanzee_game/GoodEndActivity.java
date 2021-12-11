package kr.ac.kyonggi.chimpanzee_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GoodEndActivity extends AppCompatActivity {

    TextView name ;
    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_end);

        name = (TextView) findViewById(R.id.name);
        score = (TextView) findViewById(R.id.goodScore);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        int gotScore =  intent.getIntExtra("stage",1);
        int total = intent.getIntExtra("total",1);
        name.setText(nickName+" 님");
        score.setText(total+" stage 중 "+gotScore+" stage Clear!");

        Button backButton = (Button) findViewById(R.id.goToMain2);
        Button replayButton = (Button) findViewById(R.id.replay2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart(getApplicationContext());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart(getApplicationContext());
                Intent intent = new Intent(getApplicationContext(), ModeActivity.class);
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
                finishAffinity();
                System.runFinalization();
                System.exit(0);
                return true;
            case R.id.toStart:
                Intent intent3 = new Intent(getApplicationContext(), ModeActivity.class);
                startActivity(intent3);
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