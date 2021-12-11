package kr.ac.kyonggi.chimpanzee_game;

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

import androidx.appcompat.app.AppCompatActivity;


public class ModeActivity extends AppCompatActivity {

    static String mode = "";

    Button monkeyButton ;
    Button gorillaButton ;
    Button chimpanzeeButton;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        monkeyButton = (Button) findViewById(R.id.monkeyMode);
        gorillaButton = (Button) findViewById(R.id.gorillaMode);
        chimpanzeeButton = (Button) findViewById(R.id.chimpanzeeMode);

        Intent intent = getIntent();
        name = intent.getStringExtra("nickName");

        monkeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode="monkey";
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("nickName",name);// 클래스 새로 만들어서 변경할 것
                startActivity(intent);
            }
        });
        gorillaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode="gorilla";
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("nickName",name);// 클래스 새로 만들어서 변경할 것
                startActivity(intent);
            }
        });
        chimpanzeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode="chimpanzee";
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("nickName",name);// 클래스 새로 만들어서 변경할 것
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

