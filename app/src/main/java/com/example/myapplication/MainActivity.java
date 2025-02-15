package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean playerXturn = true;
    private int roundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this::onButtonClick);
            }
        }
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(v -> resetGame());
    }
    private void onButtonClick(View v){
        Button clickedButton = (Button) v;

        if(!clickedButton.getText().toString().isEmpty()){
            return ;
        }
        if(playerXturn){
            clickedButton.setText("X");
        }else{
            clickedButton.setText("O");
        }
        roundCount++;
        if(checkForWin()){
            if(playerXturn){
                showWinner("Player X Wins!");
            }else{
                showWinner("Player O Wins!");
            }
        }else if(roundCount == 9){
            showWinner("Draw!");
        }else{
            playerXturn = !playerXturn;
        }
    }
    private boolean checkForWin(){
        String [][] board = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i = 0; i < 3; i++){
//            Column Check
            if(!board[i][0].isEmpty() && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])){
                return true;
            }
            if(!board[0][i].isEmpty() && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])){
//                Row Check
                return true;

            }
        }
        if(!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) ){
            return true;
        }
        if(!board[0][2].isEmpty() && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) ){
            return true;
        }
        return false;
    }
    private void showWinner(String winnerMessage){
        Toast.makeText(this,winnerMessage, Toast.LENGTH_SHORT).show();
        resetGame();
    }
    private void resetGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;

    }

}