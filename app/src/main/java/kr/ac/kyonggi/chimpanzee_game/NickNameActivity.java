package kr.ac.kyonggi.chimpanzee_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                Intent intent = new Intent(getApplicationContext(), ModeActivity.class);
                name = nickName.getText().toString();
                intent.putExtra("nickName",name);
                startActivity(intent);
            }
        });
    }
}