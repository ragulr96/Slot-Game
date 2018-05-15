package mainPackage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.IOException;

public class Main extends Application {

    // creating the windows
    private Stage mainWindow, gameWindow, statisticWindow, payoutWindow;

    // creating the scenes
    private Scene mainScene, gameScene, statisticScene, payoutScene;

    // creating the labels
    protected static Label creditLeftLabel, betCreditLabel;

    // creating the text fields
    protected static TextField creditLeft, betCredit;

    // creating ImageView to store to the image symbols
    protected static ImageView reelImage1, reelImage2, reelImage3;

    // Button functions
    public static Button addCoinBtn, betOneBtn, betMaximumBtn, resetBtn, statisticBtn, spinBtn;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // setting the primary window
        mainWindow = primaryStage;

        // button to go to game window
        Button goToGameBtn = new Button("GO TO GAME");
        goToGameBtn.setOnAction(event -> {
            mainWindow.close();
            createGameWindow();
        });

        //setting up the path of welcome logo to ImageView
        ImageView welcomeImage = new ImageView("/Images/logo.png");

        // setting the welcome logo and game button
        VBox welcomeFunc = new VBox(150);
        welcomeFunc.setAlignment(Pos.CENTER);
        welcomeFunc.setPadding(new Insets(10, 10, 100, 10));

        // adding the welcome image and game button to VBox
        welcomeFunc.getChildren().addAll(welcomeImage, goToGameBtn);

        // adding the VBox to main scene
        mainScene = new Scene(welcomeFunc, 700, 800);

        // giving an external css stylesheet for main scene
        mainScene.getStylesheets().add("/Style/welcomeWindowSS.css");

        // setting the main scene to main window
        mainWindow.setScene(mainScene);
        mainWindow.setTitle("The Slot Machine");
        mainWindow.show();
    }

    private void createGameWindow() {

        // creating the game window
        gameWindow = new Stage();
        gameWindow.setTitle("SLOT MACHINE GAME");

        // introducing text fields for credit details
        creditLeft = new TextField("10");
        betCredit = new TextField("00");

        // set size of creditLeft
        creditLeft.setPrefHeight(40);
        creditLeft.setPrefWidth(60);

        // set size of betCredit
        betCredit.setPrefHeight(40);
        betCredit.setPrefWidth(60);

        //  button for add coin
        addCoinBtn = new Button("Add Coin");
        addCoinBtn.setOnAction(event -> Controller.addCredit());

        //  button to bet one coin
        betOneBtn = new Button("Bet One");
        betOneBtn.setOnAction(event -> Controller.betOneCredit());

        //  button to bet maximum coins
        betMaximumBtn = new Button("Bet Max");
        betMaximumBtn.setOnAction(event -> Controller.betMaximumCredit());

        //  button to reset coins
        resetBtn = new Button("Reset");
        resetBtn.setOnAction(event -> Controller.resetCredit());

        // button to save statistics
        statisticBtn = new Button("Statistics");
        statisticBtn.setOnAction(event -> createStatisticWindow());

        //  button to spin reels
        spinBtn = new Button("Spin");
        spinBtn.setOnAction(event -> Controller.spin());

        // introducing ImageView to set the image path
        reelImage1 = new ImageView("/Images/redseven.png");
        reelImage2 = new ImageView("/Images/cherry.png");
        reelImage3 = new ImageView("/Images/redseven.png");

        // setting the size of image symbols
        reelImage1.setFitWidth(130);
        reelImage1.setFitHeight(130);
        reelImage2.setFitWidth(130);
        reelImage2.setFitHeight(130);
        reelImage3.setFitWidth(130);
        reelImage3.setFitHeight(130);

        // ----------------------------- Top area of the BorderPane -----------------------------

        // setting image reel positions
        HBox imageReel = new HBox(35);
        imageReel.setPrefSize(550, 200);
        imageReel.setAlignment(Pos.CENTER);
        imageReel.setId("imageReel");

        // stop image symbols spinning when clicked
        imageReel.setOnMouseClicked(event -> Controller.stopReel());

        // adding all three reel images to imageReel
        imageReel.getChildren().addAll(reelImage1, reelImage2, reelImage3);

        // setting imageReel to new VBox 
        VBox borderTop = new VBox(25);
        borderTop.setAlignment(Pos.CENTER);
        borderTop.getChildren().addAll(imageReel);
        borderTop.setPadding(new Insets(10, 10, 10, 10));

        // ----------------------------- Center area of the BorderPane -----------------------------

        // buttons in first line
        HBox buttons1 = new HBox(100);
        buttons1.setAlignment(Pos.CENTER);
        buttons1.getChildren().addAll(betOneBtn, spinBtn, resetBtn);

        // buttons in second line
        HBox buttons_2 = new HBox(100);
        buttons_2.setAlignment(Pos.CENTER);
        buttons_2.getChildren().addAll(addCoinBtn, betMaximumBtn);

        // statisticBtn will automatically set in third line
        // introducing a VBox to add all buttons
        VBox borderCenter = new VBox(50);
        borderCenter.setAlignment(Pos.CENTER);
        borderCenter.getChildren().addAll(buttons1, buttons_2, statisticBtn);
        borderCenter.setPadding(new Insets(10, 10, 10, 10));

        // ----------------------------- Bottom area of the BorderPane -----------------------------

        // introducing new Labels for credit details
        creditLeftLabel = new Label("CREDIT LEFT ");
        creditLeftLabel.setId("creditLeftLabel");

        betCreditLabel = new Label("BET VALUE ");
        betCreditLabel.setId("betCreditLabel");

        // setting creditLeftLabel and creditLeft in a HBox
        HBox labelSet = new HBox(20);
        labelSet.setAlignment(Pos.CENTER);
        labelSet.getChildren().addAll(creditLeftLabel, creditLeft);

        // setting betCreditLabel and betCredit in a HBox
        HBox labelSet2 = new HBox(38);
        labelSet2.setAlignment(Pos.CENTER);
        labelSet2.getChildren().addAll(betCreditLabel, betCredit);

        // introducing a VBox to set the labels and text area
        VBox borderBottom = new VBox(20);
        borderBottom.setAlignment(Pos.CENTER);
        borderBottom.getChildren().addAll(labelSet, labelSet2);

        // setting all functions using a Bottom pane
        BorderPane gameBorderPane = new BorderPane();
        gameBorderPane.setTop(borderTop);
        gameBorderPane.setCenter(borderCenter);
        gameBorderPane.setBottom(borderBottom);
        gameBorderPane.setPadding(new Insets(5, 15, 15, 15));
        gameBorderPane.setId("gameBorderPane");

        // setting the gameBorderPane to gameScene
        gameScene = new Scene(gameBorderPane);

        // giving an external css stylesheet for main scene
        gameScene.getStylesheets().add("/Style/gameWindowSS.css");

        // setting the gameScene to gameWindow
        gameWindow.setScene(gameScene);
        gameWindow.setWidth(700);
        gameWindow.setHeight(800);
        gameWindow.show();
    }

    private void createStatisticWindow() {

        // creating a new stage for statistics
        statisticWindow = new Stage();
        statisticWindow.setTitle("STATISTICS FOR THE GAME");
        statisticWindow.setWidth(830);
        statisticWindow.setHeight(850);

        // label for win count
        Label winLabel;

        // label for loss count
        Label lossLabel;

        // label for average credits per game
        Label averagePerGameLabel;

        // label for game count
        Label gameCountLabel;

        // Button to Save
        Button saveStatsToFileBtn;

        // Button to check payout
        Button payoutBtn;

        // create a pie chart
        ObservableList<PieChart.Data> pieChartProgress =
                FXCollections.observableArrayList(

                        // introducing data
                        new PieChart.Data("WINS", Controller.getGameWins()),
                        new PieChart.Data("LOSSES", Controller.getGameLoss())
                );

        // creating pie chart progress from PieChart object
        final PieChart pie = new PieChart(pieChartProgress);
        pie.setTitle("STATISTICS");

        // setting win count label
        winLabel = new Label("WIN COUNT || " + Controller.getGameWins());
        winLabel.setId("winLabel");

        // setting loss count label
        lossLabel = new Label("LOST COUNT ||  " + Controller.getGameLoss());
        lossLabel.setId("lossLabel");

        // setting average credit per game
        averagePerGameLabel = new Label("CREDITS / GAME ||  " + Controller.getAverageCreditsPerGame());
        averagePerGameLabel.setId("averagePerGameLabel");

        // setting game count label
        gameCountLabel = new Label("GAME COUNT ||  " + Controller.getGameCount());
        gameCountLabel.setId("gameCountLabel");

        // setting save to file button
        saveStatsToFileBtn = new Button("SAVE TO FILE");
        saveStatsToFileBtn.setAlignment(Pos.CENTER);
        saveStatsToFileBtn.setOnAction(event -> PrintToFile.saveToFile());

        // setting payout button
        payoutBtn = new Button("CALCULATE PAYOUT");
        payoutBtn.setAlignment(Pos.CENTER);
        payoutBtn.setOnAction(event -> createPayoutWindow());

        // adding winLabel and lossLabel to a HBox
        HBox gameDetails = new HBox(120);
        gameDetails.setAlignment(Pos.CENTER);
        gameDetails.getChildren().addAll(winLabel, gameCountLabel, lossLabel);

        VBox gameDetails2 = new VBox(30);
        gameDetails2.setAlignment(Pos.CENTER);
        gameDetails2.getChildren().addAll(gameDetails, averagePerGameLabel);


        // adding saveStatsToFileBtn and payoutBtn to a HBox
        HBox gameDetails3 = new HBox(120);
        gameDetails3.setAlignment(Pos.CENTER);
        gameDetails3.getChildren().addAll(saveStatsToFileBtn, payoutBtn);

        // adding all the functions to a BorderPane
        // Center BorderPane
        VBox center = new VBox(100);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(0, 10, 15, 10));
        center.getChildren().addAll(pie, gameDetails2, gameDetails3);

        BorderPane statsBorderPane = new BorderPane();
        statsBorderPane.setCenter(center);
        statsBorderPane.setPadding(new Insets(5, 15, 15, 15));
        statsBorderPane.setId("statsBorderPane");

        // adding statistics to statisticScene
        statisticScene = new Scene(statsBorderPane);

        // giving an external css stylesheet for statistics scene
        statisticScene.getStylesheets().add("/Style/statisticsWindowSS.css");

        // adding statisticScene to statisticWindow
        statisticWindow.setScene(statisticScene);
        statisticWindow.show();
    }

    private void createPayoutWindow() {

        // creating a new stage for statistics
        payoutWindow = new Stage();
        payoutWindow.setTitle("PAYOUT");
        payoutWindow.setWidth(850);
        payoutWindow.setHeight(400);

        // label for no of game count
        Label gameCountLabel;

        // Label for total bet amount
        Label totalBetCreditLabel;

        // Label for win count
        Label winLabel;

        // Label for loss count
        Label lossLabel;

        // Label for profit
        Label profitLabel;

        // Label for payout ratio
        Label payoutRatioLabel;

        // setting game count label
        gameCountLabel = new Label("GAME COUNT || " + Controller.getGameCount());
        gameCountLabel.setId("gameCountLabel");

        // setting the bet credit label
        totalBetCreditLabel = new Label("TOTAL BET AMOUNT || " + Controller.getTotalBetCredits());
        totalBetCreditLabel.setId("totalBetCreditLabel");

        // setting win count label
        winLabel = new Label("WIN CREDITS || " + Controller.getTotalWinCredits());
        winLabel.setId("winLabel");

        // setting loss count label
        lossLabel = new Label("LOSS CREDITS || " + Controller.getTotalLossCredits());
        lossLabel.setId("lossLabel");

        // setting loss profit label
        profitLabel = new Label("PROFIT || " + Controller.profitValue());
        profitLabel.setId("profitLabel");

        // setting Payout ratio label
        payoutRatioLabel = new Label("PAYOUT RATIO || " + (Controller.profitValue() / Controller.getGameCount()) * 100 + "%");
        payoutRatioLabel.setId("payoutRatioLabel");

        // adding winLabel, gameCountLabel and lossLabel to a HBox
        HBox payoutDetails = new HBox(120);
        payoutDetails.setAlignment(Pos.CENTER);
        payoutDetails.getChildren().addAll(winLabel, gameCountLabel, lossLabel);

        // adding totalBetCreditLabel, payoutRatioLabel and profitLabel to a HBox
        HBox payoutDetails1 = new HBox(120);
        payoutDetails1.setAlignment(Pos.CENTER);
        payoutDetails1.getChildren().addAll(totalBetCreditLabel, profitLabel);

        // adding both HBox to a VBox
        VBox finalPayoutDetails = new VBox(100);
        finalPayoutDetails.setAlignment(Pos.CENTER);
        finalPayoutDetails.setPadding(new Insets(0, 10, 15, 10));
        finalPayoutDetails.getChildren().addAll(payoutDetails, payoutDetails1, payoutRatioLabel);

        // adding all details to a BorderPane
        BorderPane payoutBorderPane = new BorderPane();
        payoutBorderPane.setCenter(finalPayoutDetails);
        payoutBorderPane.setPadding(new Insets(5, 15, 15, 15));
        payoutBorderPane.setId("payoutBorderPane");

        // adding payout details to payoutScene
        payoutScene = new Scene(payoutBorderPane);

        // giving an external css stylesheet for payout scene
        payoutScene.getStylesheets().add("/Style/payoutWindowSS.css");

        // adding payoutScene to payoutWindow
        payoutWindow.setScene(payoutScene);
        payoutWindow.show();

    }

    // creating a sound thread
    public static synchronized void playBgSound(final String sound) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // creating a new Clip object
                    Clip newClip = AudioSystem.getClip();

                    // getting resource for the sound
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/" + sound));
                    newClip.open(inputStream);
                    newClip.start();

                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}