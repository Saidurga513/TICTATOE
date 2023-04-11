package com.example.tictactoefeb;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Button;
public class TicTacToe extends Application {
    private Button buttons[][]=new Button[3][3];
    private  Label playerXScoreLabel,playerOScoreLabel ;
    private  int playerXscore=0,playerOscore=0;
    private  boolean playerXTurn=true;

    // chhose border layout for ui
    private BorderPane createContent()
    {
        BorderPane root=new BorderPane();
        // create the title
        Label titlelabel=new Label("Tic Tac Toe");
        titlelabel.setStyle("-fx-font-size:24pt;-fx-font-weight:bold");
        //set label in top
        root.setTop(titlelabel);
        BorderPane.setAlignment(titlelabel, Pos.CENTER);
        // board
        /*
        buttons00 buttons01 buttonso2
        buttons10 buttons11 buttons12
        buttons20 buttons21 buttons22
         */
        GridPane gridpane=new GridPane();
        // gap between buttions or grid
        gridpane.setHgap(10);
        gridpane.setVgap(10);
       gridpane.setAlignment(Pos.CENTER);
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                Button button=new Button("");
                buttons[i][j]=button;
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size:24pt;-fx-font-weight:bold");
                button.setOnAction(event ->buttonClicked(button));
                gridpane.add(button,i,j);
            }
        }
    root.setCenter(gridpane);
        BorderPane.setAlignment(gridpane,Pos.CENTER);
        // score Board
        //create aa horizontal box
        HBox scoreBoard=new HBox(20);
        // add the x,y playeers
       scoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLabel=new Label("Player X:0");
        playerOScoreLabel=new Label("Player O:0");
        playerXScoreLabel.setStyle("-fx-font-size:16pt;-fx-font-weight:bold");
        playerOScoreLabel.setStyle("-fx-font-size:16pt;-fx-font-weight:bold");
        scoreBoard.getChildren().addAll(playerXScoreLabel,playerOScoreLabel);
        root.setBottom(scoreBoard);
        return  root;
    }
    private  void buttonClicked(Button button)
    {
        //button is not clicked
        if (button.getText().equals(""))
        {
            if(playerXTurn)
            {
                button.setText("X");
            }
            else
            {
                button.setText("O");
            }
            playerXTurn=!playerXTurn;
          checkWinner();
        }

        return ;
    }
    private  void checkWinner()
    {
    // check the row
        for (int row = 0; row < 3; row++) {
            if(        buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty())
            {
                String win= buttons[row][0].getText();
                System.out.println(win);
                showWinnerD(win);
                updateScore(win);
                resetBoard();
            }
        }
        // column wise
        for (int col = 0; col < 3; col++) {
            if(        buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty())
            {
                String win= buttons[0][col].getText();
                System.out.println(win);
                showWinnerD(win);
                updateScore(win);
                resetBoard();
            }
        }
        // diagonal wise check the winner
        if(        buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty())
        {
            String win= buttons[0][0].getText();
            System.out.println(win);
            showWinnerD(win);
            updateScore(win);
            resetBoard();
        }
        if(        buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[2][0].getText().isEmpty())
        {
            String win= buttons[2][0].getText();
            System.out.println(win);
            showWinnerD(win);
            updateScore(win);
            resetBoard();
        }
        // check the tie condition
        boolean tie=true;
        for (Button row[]:buttons) {
            for(Button button:row)
            {
                if(button.getText().isEmpty())
                {
                    tie=false;
                    break;
                }
            }
            if(tie)
            {
                showTieD();
                resetBoard();
            }
        }
    }
    // display the winner
    private void showWinnerD(String winner){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulations "+winner+" ! You won the game");
        alert.showAndWait();

    }
    private void showTieD(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game over ! It's a tie");
        alert.showAndWait();

    }
    private void updateScore(String winner)
    {
        if(winner.equals("X"))
        {
            playerXscore++;
            playerXScoreLabel.setText("Player X: "+playerXscore);
        }
        else if(winner.equals("O"))
        {
            playerOscore++;
            playerOScoreLabel.setText("Player O : "+playerOscore);
        }
    }
    private  void resetBoard()
    {
        for (Button row[]:buttons) {
            for(Button button:row)
            {
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}