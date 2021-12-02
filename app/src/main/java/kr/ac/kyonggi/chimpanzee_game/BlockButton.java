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
    public BlockButton(Context context, int x, int y) {
        super(context);
        this.x=x;
        this.y=y;
    }
    public boolean breakBlock(View view){
        if(blockNumber==blockCount){ //정답
            blockCount++;
            setText(blockNumber+"");
            setEnabled(false);
            return true; //game continue
        }
        else{ // 오답
            return false; //game over
        }
    }
}
