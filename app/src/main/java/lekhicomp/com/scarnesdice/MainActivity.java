package lekhicomp.com.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    @InjectView(R.id.imageDice)
    ImageView imgDice;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = 0;
                while (num != 1) {
                    num = getRandomValue();
                    Toast.makeText(MainActivity.this, "Number: " + num, Toast.LENGTH_SHORT).show();
                    user_turnScore += num;
                    updateUserScore();
                }
                if (num == 1) {
                    Toast.makeText(MainActivity.this, "Computer's Turn", Toast.LENGTH_LONG).show();
                    user_turnScore = 0;
                    computerTurn();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_turnScore = 0;
                user_totalScore = 0;
                comp_turnScore = 0;
                comp_totalScore = 0;
                updateUserScore();
                updateCompScore();
            }
        });


    }

    //Method for Comp turn
    public void computerTurn() {
        btnRoll.setClickable(false);
        btnHold.setClickable(false);

        while (turn == 1) {
            int num = 0;

            while (num != 1) {
                num = getRandomValue();
                Toast.makeText(MainActivity.this, "Number: " + num, Toast.LENGTH_SHORT).show();
                comp_turnScore += num;
                updateCompScore();
            }
            if (num == 1) {
                Toast.makeText(MainActivity.this, "Player's Turn", Toast.LENGTH_LONG).show();
                comp_turnScore = 0;
            }
        }
    }

    //Method to get Random Dice value
    public int getRandomValue() {
        int num = random.nextInt(6) + 1;
        switch (num) {
            case 1:
                imgDice.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imgDice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imgDice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imgDice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imgDice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imgDice.setImageResource(R.drawable.dice6);
                break;
        }
        return num;
    }

    public void updateUserScore() {
        user_totalScore += user_turnScore;
        txtUser.setText("Your Score: " + user_totalScore);
    }

    public void updateCompScore() {
        comp_totalScore += comp_turnScore;
        txtComp.setText("Computer Score: " + comp_totalScore);
    }
}
