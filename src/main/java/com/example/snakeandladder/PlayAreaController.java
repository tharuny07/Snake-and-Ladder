package com.example.snakeandladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Random;

public class PlayAreaController {
    @FXML
    Label number;
    @FXML
    Label changeturn;
    @FXML
    ImageView player1,player2;
    int turn=1;
    int count=0;

    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeAndladderCoordinateChanges;
    @FXML
    public void roll(MouseEvent event) {
        getSnakeLadderCoordinates();
        Random random=new Random();
        int rolling=random.nextInt(6)+1;
        number.setText("<"+rolling+">");

        if(turn==1)
        {

           Pair<Double,Double> moveCoordinates=movement(player1.getTranslateX(),player1.getTranslateY(),rolling);
           player1.setTranslateX(moveCoordinates.getKey());
           player1.setTranslateY(moveCoordinates.getValue());
           if(snakeAndladderCoordinateChanges.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())))
           {
               player1.setTranslateX(snakeAndladderCoordinateChanges.get
                       (new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
               player1.setTranslateY(snakeAndladderCoordinateChanges.get
                       (new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
           }
           int won=checkWin(player1.getTranslateX(), player1.getTranslateY());
           if(won==1){
               player1.setTranslateX(0.0);
               player1.setTranslateY(0.0);
               player2.setTranslateX(0.0);
               player2.setTranslateY(0.0);
           }
        }
        else
        {
            Pair<Double,Double> moveCoordinates=movement(player2.getTranslateX(),player2.getTranslateY(),rolling);
            player2.setTranslateX(moveCoordinates.getKey());
            player2.setTranslateY(moveCoordinates.getValue());
            if(snakeAndladderCoordinateChanges.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())))
            {
                player2.setTranslateX(snakeAndladderCoordinateChanges.get
                        (new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player2.setTranslateY(snakeAndladderCoordinateChanges.get
                        (new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            int won=checkWin(player2.getTranslateX(), player2.getTranslateY());
            if(won==1){
                player1.setTranslateX(0.0);
                player1.setTranslateY(0.0);
                player2.setTranslateX(0.0);
                player2.setTranslateY(0.0);
            }

        }
        if(rolling!=6)
        {
            count=0;
            if (turn == 1)
            {

                turn = 2;
                changeturn.setText("Player 2s turn");  Alert turnChange=new Alert(Alert.AlertType.INFORMATION); turnChange.setContentText("Player2(BLUE) turn"); turnChange.show();
            } else
            {
                turn = 1;
                changeturn.setText("Player 1s turn");
                Alert turnChange=new Alert(Alert.AlertType.INFORMATION); turnChange.setContentText("Player 1(RED) turn"); turnChange.show();
            }
        }else{
            count++;
            if(count>=3){ Alert turnChange=new Alert(Alert.AlertType.INFORMATION); turnChange.setContentText("YOU got 6 three times. You will start from the start"); turnChange.show();
                if(turn==1) {
                    player1.setTranslateX(50.0);
                    player1.setTranslateY(0.0);
                }else{
                    player2.setTranslateX(50.0);
                    player2.setTranslateY(0.0);
                }
                count=0;
            }
        }
    }
    Pair<Double,Double> movement(double X,double Y,int rolling)
    {
        double moveX=X;
        double moveY=Y;

         if(moveY%100==0) {
             moveX+=rolling*50;
             if (moveX > 500) {
                 moveX = 2 * 500 - moveX + 50;
                 moveY -= 50;
             }
         }else{
             moveX-=rolling*50;
             if(moveX<50){
                 if(moveY==-450){
                     return new Pair<>(X,Y);
                 }
                 moveX=-1*(moveX-50);
                 moveY-=50;
             }

         }
        return new Pair<>(moveX,moveY);
    }

    int checkWin(Double X,Double Y){
        if(X ==50&&Y==-450){
            Alert winAlert=new Alert(Alert.AlertType.INFORMATION);
            if(turn==1)
            {
                winAlert.setContentText("Player 1 has the game");
            }
            else {
                winAlert.setContentText("Player 2 has the game");
            }
            winAlert.showAndWait();
          return 1;
        }
        return 0;
    }
    void getSnakeLadderCoordinates(){
        snakeAndladderCoordinateChanges=new HashMap<>();
        snakeAndladderCoordinateChanges.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(200.0,0.0),new Pair<>(350.0,-50.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(200.0,-50.0),new Pair<>(350.0,0.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(450.0,0.0),new Pair<>(500.0,-150.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(50.0,-100.0),new Pair<>(100.0,-200.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(400.0,-100.0),new Pair<>(200.0,-400.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(100.0,-300.0),new Pair<>(100.0,-50.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(500.0,-250.0),new Pair<>(350.0,-300.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(500.0,-350.0),new Pair<>(500.0,-450.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(50.0,-350.0),new Pair<>(50.0,-450.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(200.0,-300.0),new Pair<>(50.0,-250.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(350.0,-250.0),new Pair<>(350.0,-150.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(150.0,-450.0),new Pair<>(100.0,-350.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(300.0,-450.0),new Pair<>(300.0,-350.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(400.0,-450.0),new Pair<>(400.0,-350.0));
        snakeAndladderCoordinateChanges.put(new Pair<>(350.0,-400.0),new Pair<>(200.0,-100.0));
    }

}





