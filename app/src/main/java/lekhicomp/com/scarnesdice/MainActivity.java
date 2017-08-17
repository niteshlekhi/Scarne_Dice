package lekhicomp.com.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    int user_turnScore = 0;
    int user_totalScore = 0;
    int comp_turnScore = 0;
    int comp_totalScore = 0;
    int turn = 0;

    @InjectView(R.id.userScore)
    TextView txtUser;
    @InjectView(R.id.compScore)
    TextView txtComp;
    @InjectView(R.id.buttonRoll)
    Button btnRoll;
    @InjectView(R.id.buttonHold)
    Button btnHold;
    @InjectView(R.id.buttonReset)
    Button btnReset;
    Random random=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = getRandomValue();
                Toast.makeText(MainActivity.this, "Number: "+num, Toast.LENGTH_LONG).show();
              //  if (num != 1) {

               // }


            }
        });


    }

    //Method for Comp turn
    public void computerTurn() {
        btnRoll.setClickable(false);
        btnHold.setClickable(false);

        while (turn == 1) {
            int num = getRandomValue();
        }
    }

    //Method to get Random Dice value
    public int getRandomValue() {
        int num = random.nextInt();
        switch (num) {
            case 1:
                getResources().getDrawable(R.drawable.dice1);
                break;
            case 2:
                getResources().getDrawable(R.drawable.dice2);
                break;
            case 3:
                getResources().getDrawable(R.drawable.dice3);
                break;
            case 4:
                getResources().getDrawable(R.drawable.dice4);
                break;
            case 5:
                getResources().getDrawable(R.drawable.dice5);
                break;
            case 6:
                getResources().getDrawable(R.drawable.dice6);
                break;
        }
        return num;
    }
}
