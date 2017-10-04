package com.example.shalin.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    public int User_Total_Score=0;
    public int User_Turn_Score=0;
    public int ComputerTotalScore=0;
    public int ComputerTurnScore=0;
    public int TempComputerScore=0;
    public int TempUserScore=0;
    public int user_dice_number=0;
    public int computer_dice_number=0;
    public android.os.Handler handler=new android.os.Handler();
    public boolean buttonclick=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button roll_button=(Button)findViewById(R.id.roll);
        roll_button.setOnClickListener(roll_event);

        Button hold_button=(Button)findViewById(R.id.hold);
        hold_button.setOnClickListener(hold_event);

        Button reset_button=(Button)findViewById(R.id.reset);
        reset_button.setOnClickListener(reset_event);
    }

    private View.OnClickListener reset_event=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            reset();
             }
    };

    public void reset(){
        buttonclick=false;
        User_Total_Score=0;
        User_Turn_Score=0;
        ComputerTotalScore=0;
        ComputerTurnScore=0;
        TempComputerScore=0;
        TempUserScore=0;
        TextView forzero=(TextView)findViewById(R.id.dis_current_score_user);
        forzero.setText(String.valueOf(TempComputerScore));
        forzero=(TextView)findViewById(R.id.dis_current_score_computer);
        forzero.setText(String.valueOf(TempComputerScore));
        forzero=(TextView)findViewById(R.id.dis_yourscore);
        forzero.setText(String.valueOf(TempComputerScore));
        forzero=(TextView)findViewById(R.id.dis_computerscore);
        forzero.setText(String.valueOf(TempComputerScore));
        ImageView i=(ImageView)findViewById(R.id.imageView);
        i.setImageResource(R.drawable.dice1);
        computer_dice_number=0;
        user_dice_number=0;
        Button b=(Button)findViewById(R.id.hold);
        b.setVisibility(View.VISIBLE);
        b=(Button)findViewById(R.id.roll);
        b.setVisibility(View.VISIBLE);

    }
    private View.OnClickListener hold_event=new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            User_Total_Score=User_Total_Score+User_Turn_Score;
            TextView usertotalscore=(TextView)findViewById(R.id.dis_yourscore);
            usertotalscore.setText(String.valueOf(User_Total_Score));
            User_Turn_Score=0;
            usertotalscore=(TextView)findViewById(R.id.dis_current_score_user);
            usertotalscore.setText(String.valueOf(User_Turn_Score));
            computer_event();
        }
    };

    private View.OnClickListener roll_event = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonclick=true;
            Toast.makeText(MainActivity.this,"User Turn",Toast.LENGTH_SHORT).show();
            userroll();
        }
    };

    public void userroll(){
        if(User_Total_Score>=50){
            Button b3=(Button)findViewById(R.id.roll);
            b3.setVisibility(View.INVISIBLE);
            b3=(Button)findViewById(R.id.hold);
            b3.setVisibility(View.INVISIBLE);

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setMessage("User has won the game !!!");

            //noinspection deprecation
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    reset();
                }
            });
            alertDialog.show();
        }
else {
            TextView re1=(TextView)findViewById(R.id.dis_current_score_computer);
            re1.setText(String.valueOf(0));
            TextView fake=(TextView)findViewById(R.id.dis_computerscore);
            if(computer_dice_number!=1){
                ComputerTotalScore=ComputerTotalScore+ComputerTurnScore;
                fake.setText(String.valueOf(ComputerTotalScore));
            }
            ComputerTurnScore=0;

            TempUserScore=0;
       /* final Toast toast=Toast.makeText(MainActivity.this,"User Turn",Toast.LENGTH_SHORT);
        toast.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        },3000);*/
            if(buttonclick==true)
            {
                user_dice_number=new Random().nextInt(6)+1;
                TextView comp_score=(TextView)findViewById(R.id.dis_yourscore);
                ImageView image=(ImageView)findViewById(R.id.imageView);
                TempUserScore=user_dice_number;
                //long mili=1500;
                if(User_Total_Score<100){

                    switch (user_dice_number){
                        case 1:
                            image.setImageResource(R.drawable.dice1);
                            //User_Total_Score=User_Total_Score+User_Turn_Score;
                            TextView usertotalscore=(TextView)findViewById(R.id.dis_yourscore);
                            usertotalscore.setText(String.valueOf(User_Total_Score));
                            User_Turn_Score=0;
                            usertotalscore=(TextView)findViewById(R.id.dis_current_score_user);
                            usertotalscore.setText(String.valueOf(User_Turn_Score));
                            buttonclick=false;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    computer_event();

                                }
                            },500);
                            break;
                        case 2:
                            image.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            image.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            image.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            image.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            image.setImageResource(R.drawable.dice6);
                            break;
                    }

                }
                else {
                    Toast.makeText(MainActivity.this,"You have won the game",Toast.LENGTH_SHORT).show();
                }
                if(user_dice_number!=1)
                    User_Turn_Score=User_Turn_Score+TempUserScore;
                TextView userturnscore=(TextView)findViewById(R.id.dis_current_score_user);
                userturnscore.setText(String.valueOf(User_Turn_Score));
            }


        }


    }

    public void computer_event(){

        if(ComputerTotalScore>=50){
            Button b3=(Button)findViewById(R.id.roll);
            b3.setVisibility(View.INVISIBLE);
            b3=(Button)findViewById(R.id.hold);
            b3.setVisibility(View.INVISIBLE);

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setMessage("Computer has won the game !!!");

            //noinspection deprecation
            alertDialog.setButton("OK", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which) {
                    reset();
                }
            });
            alertDialog.show();
        }
        else {
            TextView fake=(TextView)findViewById(R.id.dis_yourscore);
            if(user_dice_number!=1){
                User_Total_Score=User_Total_Score+User_Turn_Score;
                fake.setText(String.valueOf(User_Total_Score));
            }
            User_Turn_Score=0;
            fake=(TextView)findViewById(R.id.dis_current_score_user);
            fake.setText(String.valueOf(User_Turn_Score));

            TempComputerScore=0;
            // Toast.makeText(MainActivity.this,"Computer Turn",Toast.LENGTH_SHORT).show();
            computer_dice_number=new Random().nextInt(6)+1;
            TextView your_score=(TextView)findViewById(R.id.dis_computerscore);
            ImageView image=(ImageView)findViewById(R.id.imageView);
            image.setImageResource(R.drawable.dice1);
            TempComputerScore=computer_dice_number;

            fake=(TextView)findViewById(R.id.dis_current_score_computer);
            if(ComputerTotalScore<100){

                switch (computer_dice_number){
                    case 1:
                        image.setImageResource(R.drawable.dice1);
                        //ComputerTotalScore=ComputerTotalScore+ComputerTurnScore;
                        TextView computertotalscore=(TextView)findViewById(R.id.dis_computerscore);
                        computertotalscore.setText(String.valueOf(ComputerTotalScore));
                        ComputerTurnScore=0;
                        computertotalscore=(TextView)findViewById(R.id.dis_current_score_computer);
                        computertotalscore.setText(String.valueOf(ComputerTurnScore));
                        buttonclick=false;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                userroll();
                            }
                        },500);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.dice2);
                        // ComputerTurnScore=ComputerTurnScore+TempComputerScore;
                        // fake.setText(String.valueOf(ComputerTurnScore));
                        computer_event();
                        break;
                    case 3:
                        image.setImageResource(R.drawable.dice3);
                        // ComputerTurnScore=ComputerTurnScore+TempComputerScore;
                        // fake.setText(String.valueOf(ComputerTurnScore));
                        break;
                    case 4:
                        image.setImageResource(R.drawable.dice4);
                        // ComputerTurnScore=ComputerTurnScore+TempComputerScore;
                        // fake.setText(String.valueOf(ComputerTurnScore));
                        break;
                    case 5:
                        image.setImageResource(R.drawable.dice5);
                        // ComputerTurnScore=ComputerTurnScore+TempComputerScore;
                        // fake.setText(String.valueOf(ComputerTurnScore));
                        break;
                    case 6:
                        image.setImageResource(R.drawable.dice6);

                        break;
                }
            }
            else {

                userroll();

                ///Toast.makeText(MainActivity.this,"You have won the game",Toast.LENGTH_SHORT).show();
            }
            if(computer_dice_number!=1)
                ComputerTurnScore=ComputerTurnScore+TempComputerScore;
            TextView computerturnscore=(TextView)findViewById(R.id.dis_current_score_computer);
            computerturnscore.setText(String.valueOf(ComputerTurnScore));
        }


    }
}
