package kr.ac.kyonggi.chimpanzee_game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BadEndActivity extends AppCompatActivity {

    TextView name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_end);

        name = (TextView) findViewById(R.id.name2);
        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        name.setText(nickName);

    }
}
