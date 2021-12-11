package kr.ac.kyonggi.chimpanzee_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NickNameActivity extends AppCompatActivity {


    Button nickNameButton ;
    EditText nickName ;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);
        nickNameButton = (Button) findViewById(R.id.nickNameButton);
        nickName = (EditText) findViewById(R.id.nickName);

        nickNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nickName.getText().toString();

                if(name.length()==0){

                    AlertDialog.Builder dlg = new AlertDialog.Builder(NickNameActivity.this);
                    dlg.setMessage("닉네임을 입력해 주세요"); // 메시지
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //토스트 메시지
                            Toast.makeText(NickNameActivity.this, "확인을 누르셨습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dlg.show();

                }else{
                    Intent intent = new Intent(getApplicationContext(), ModeActivity.class);
                    intent.putExtra("nickName",name);
                    startActivity(intent);
                }
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