package kr.ac.kyonggi.chimpanzee_game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class BlockButton extends Button {
    int x;
    int y;
    int blockNumber=0;
    static int blockCount=1;
    public BlockButton(Context context, int x, int y, int num) {
        super(context);
        this.x=x;
        this.y=y;
        this.blockNumber = num;
        setText(blockNumber+"");
    }

    public boolean breakBlock(View view){
        if(blockNumber==blockCount){ //정답
            blockCount++;
            setText(blockNumber+"");
            setEnabled(false);
            return true; // 내가 맞았는지
        }
        else{ // 오답
            GameActivity.life--;
            return false; //틀린 걸 선택 했을 때
        }
    }
}
