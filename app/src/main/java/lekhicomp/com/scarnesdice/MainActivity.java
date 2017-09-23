package lekhicomp.com.scarnesdice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
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
    int comp_roundScore=0;
    int turn = 0;   //0 for User & 1 for Comp
    int num = 0;

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
    @InjectView(R.id.textNotify)
    TextView txtNotify;

    Random random = new Random();
    Handler handler = new Handler();
    Bitmap bitmap;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        userTurn();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_turnScore = 0;
                user_totalScore = 0;
                comp_turnScore = 0;
                comp_totalScore = 0;
                updateUserScore();
                updateCompScore();
                bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.dice);
                imgDice.setImageBitmap(bitmap);
                userTurn();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 100, 0, "Two-Dice");
        menu.add(1, 101, 0, "Fast Mode");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        Intent intent;
        switch(id){
            case 100:
                intent=new Intent(MainActivity.this,TwoDice.class);
                startActivity(intent);
                break;

            case 101:
                intent=new Intent(MainActivity.this,FastDice.class);
                startActivity(intent);
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    public void userTurn() {
        btnRoll.setClickable(true);
        btnHold.setClickable(false);
        txtNotify.setText("Press ROLL to Roll the Dice or RESET to Reset the Game");
        num = 0;
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = getRandomValue();

                if (num != 1) {
                    user_turnScore = num;
                    updateUserScore();

                } else if (num == 1) {
                    user_turnScore=0;
                    updateUserScore();
                    computerTurn();
                }

                txtNotify.setText("Press ROLL to Roll the Dice or HOLD to End your Turn");
                btnHold.setClickable(true);
                btnHold.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user_turnScore=0;
                        updateUserScore();
                        computerTurn();
                    }
                });


            }
        });
    }


    //Method for Comp turn
    public void computerTurn() {
        btnRoll.setClickable(false);
        btnHold.setClickable(false);
        txtNotify.setText("");
        num = 0;
        comp_roundScore=0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                num = getRandomValue();

                if (num != 1){
                    Toast.makeText(MainActivity.this, "Number: " + num, Toast.LENGTH_SHORT).show();
                    comp_turnScore = num;
                    updateCompScore();
                    if (comp_totalScore >= 100) {
                        txtNotify.setText("Computer Won!!  Score:" + comp_totalScore);
                        btnRoll.setClickable(false);
                        btnHold.setClickable(false);
                    }
                    else
                        computerTurn();

                } else if (num == 1){
                    Toast.makeText(MainActivity.this, "Player's Turn", Toast.LENGTH_LONG).show();
                    comp_turnScore = 0;
                    updateCompScore();
                    userTurn();
                }
            }
        }, 2000);
    }


    //Method to get Random Dice value
    public int getRandomValue() {
        int num = random.nextInt(6) + 1;
        switch (num) {
            case 1:
                bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.dice1);
                findViewById(R.id.imageDice).startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate));
                imgDice.setImageBitmap(bitmap);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.dice2);
                findViewById(R.id.imageDice).startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate));
                imgDice.setImageBitmap(bitmap);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.dice3);
                findViewById(R.id.imageDice).startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate));
                imgDice.setImageBitmap(bitmap);
                break;
            case 4:
                bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.dice4);
                findViewById(R.id.imageDice).startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate));
                imgDice.setImageBitmap(bitmap);
                break;
            case 5:
                bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.dice5);
                findViewById(R.id.imageDice).startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate));
                imgDice.setImageBitmap(bitmap);
                break;
            case 6:
                bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.dice6);
                findViewById(R.id.imageDice).startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate));
                imgDice.setImageBitmap(bitmap);
        }
        return num;
    }

    public void updateUserScore() {
        if (user_totalScore >= 100) {
            txtNotify.setText("User Won!!  Score:" + user_totalScore);
            btnRoll.setClickable(false);
            btnHold.setClickable(false);

        }

        if(user_turnScore!=1)
            user_totalScore += user_turnScore;
        else {
            user_turnScore=0;
            btnRoll.setClickable(false);
            Toast.makeText(MainActivity.this, "Computer's Turn", Toast.LENGTH_LONG).show();
        }
        txtUser.setText("Your Score: " + user_totalScore);


    }

    public void updateCompScore() {

        if(comp_turnScore!=1)
            comp_totalScore += comp_turnScore;
        else{
            comp_turnScore=0;
        }
        txtComp.setText("Computer Score: " + comp_totalScore);
    }

}