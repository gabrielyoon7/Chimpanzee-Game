package kr.ac.kyonggi.chimpanzee_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GoodEndActivity extends AppCompatActivity {

    TextView name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_end);

        name = (TextView) findViewById(R.id.name);
        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        name.setText(nickName);

    }
}