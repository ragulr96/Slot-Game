package mainPackage;

import javafx.scene.control.Alert;

import java.util.Random;


public class Controller {

    // credit left
    private static int creditLeft = 10;

    // bet amount
    private static int betValue = 00;

    // maximum bet amount
    static int maxBetCredits = 3;

    // total bet amount
    static int totalBetCredits = 0;

    // game status
    private static boolean isGamePlayed;

    // maximum bet status
    static boolean isBetMaxPressed = false;

    // total no of games played;
    static int gameCount = 0;

    // average credits
    static double averageCredits;

    // average credits per game
    static double averageCreditsPerGame;

    // profit
    static double profit;

    // total win credits
    static int totalWinCredits;

    // total loss credits
    static int totalLossCredits;

    // Reels
    private static Reel r1;
    private static Reel r2;
    private static Reel r3;

    // Symbol object
    private static Symbol r1Symbol, r2Symbol, r3Symbol;

    // win count
    private static int gameWin;

    // loss count
    private static int gameLoss;

    public static void spin() {

        if (isGamePlayed == true) {

            // checking for reel status
            if (!r1.isAlive() && !r2.isAlive() && !r3.isAlive()) {
                // make reels spin
                spinReel();
            }
        } else {
            // make reels spins
            spinReel();
        }
    }

    /**
     * method to spin the reels
     */
    public static void spinReel() {

        // checking for bet value status
        if (betValue > 0) {

            // checking game status
            isGamePlayed = true;

            // play background sound while spinning
            Main.playBgSound("spin.wav");

            // disabling the buttons
            Main.addCoinBtn.setDisable(true);
            Main.betOneBtn.setDisable(true);
            Main.betMaximumBtn.setDisable(true);
            Main.resetBtn.setDisable(true);
            Main.statisticBtn.setDisable(true);

            r1 = new Reel(Main.reelImage1);
            r2 = new Reel(Main.reelImage2);
            r3 = new Reel(Main.reelImage3);

            // starting the reels
            r1.start();
            r2.start();
            r3.start();

            // incrementing the game count
            gameCount++;

        } else {
            // throwing an alert message
            Alert alertMessage = setAlertMessages("ERROR", "Please place a bet to start the game!", Alert.AlertType.WARNING);
            alertMessage.showAndWait();
        }

    }

    /**
     * method to add more credits
     */
    protected static void addCredit() {

        // incrementing the credits left by one
        creditLeft += 1;

        // play add coin sound when button is clicked
        Main.playBgSound("addcoin.wav");

        // calling the method to update credits status
        updateCredits();
    }

    /**
     * method to bet one credit
     */
    protected static void betOneCredit() {

        // checking whether credits in hand is greater than zero
        if (creditLeft > 0) {

            // incrementing bet value by one
            betValue += 1;

            // decrementing credit left by one
            creditLeft -= 1;

            // incrementing the total bet credits
            totalBetCredits++;

            // play background sound when button is  clicked
            Main.playBgSound("addcoin.wav");

            // calling the method to update credits status
            updateCredits();

        } else {
            // throwing an error message
            Alert alertMessage = setAlertMessages("ERROR", "You don't have enough credits to bet", Alert.AlertType.WARNING);
            alertMessage.showAndWait();
        }

    }

    /**
     * method to bet maximum credits
     */
    protected static void betMaximumCredit() {

        // check the status of bet max button
        if (!isBetMaxPressed) {

            // checking whether credits in hands is sufficient to place max bet
            if (creditLeft > maxBetCredits - 1) {

                // incrementing bet value by three
                betValue += maxBetCredits;

                // decrementing credit left by three
                creditLeft -= maxBetCredits;

                // incrementing the total bet credits
                totalBetCredits += 3;

                // play background sound when button is  clicked
                Main.playBgSound("addcoin.wav");

                // setting isBetMaxPressed to true
                isBetMaxPressed = true;

                // calling the method to update credits status
                updateCredits();

            } else {
                // throwing an alert message
                Alert alertMessage = setAlertMessages("ERROR", "You don't have enough credits to bet maximum amount of coins!", Alert.AlertType.WARNING);
                alertMessage.showAndWait();
            }

        } else {
            // throwing an error message
            Alert alertMessage = setAlertMessages("ERROR", "You can place a maximum bet only once!", Alert.AlertType.ERROR);
            alertMessage.showAndWait();
        }

    }

    /**
     * method to reset credits
     */
    protected static void resetCredit() {

        // checking whether coins are bet
        if (betValue > 0) {

            // reset credits
            creditLeft += betValue;

            // decrementing the total bet credits
            totalBetCredits = totalBetCredits - betValue;

            // setting bet bet value to zero
            betValue = 0;

            // play background sound when button is  clicked
            Main.playBgSound("reset.wav");

            // setting isBetMaxPressed to false again
            isBetMaxPressed = false;

            // decrementing credit left by three
            updateCredits();

        } else {
            // throwing an alert message
            Alert alertMessage = setAlertMessages("ERROR", "Place a bet first!", Alert.AlertType.WARNING);
            alertMessage.showAndWait();
        }
    }

    /**
     * method to stop the reels spinning
     */
    protected static void stopReel() {

        // checking for bet value
        if (betValue > 0) {

            // checking for thread status
            if ((r1.isAlive() && r2.isAlive() && r3.isAlive())) {

                r1.stop();
                r2.stop();
                r3.stop();
                r1Symbol = r1.getReelObject();
                r2Symbol = r2.getReelObject();
                r3Symbol = r3.getReelObject();

                // enabling the buttons
                Main.addCoinBtn.setDisable(false);
                Main.betOneBtn.setDisable(false);
                Main.betMaximumBtn.setDisable(false);
                Main.resetBtn.setDisable(false);
                Main.statisticBtn.setDisable(false);

                // calling the method to calculate credit details
                calculateScore();

                // setting isBetMaxPressed to false again
                isBetMaxPressed = false;
            }
        }
    }

    /**
     * method to calculate score
     */
    protected static void calculateScore() {

        // checking for matching image symbols
        boolean case1 = (r1Symbol.equals(r2Symbol));
        boolean case2 = (r2Symbol.equals(r3Symbol));
        boolean case3 = (r1Symbol.equals(r3Symbol));


        if (case1 || case3) {
            winValue(r1Symbol);
        } else if (case2) {
            winValue(r2Symbol);
        } else {
            lostValue();
        }

        // reset the bet value to zero
        betValue = 0;

        // calling the method to update credits status
        updateCredits();
    }

    /**
     * method to calculate the win values
     */
    private static void winValue(Symbol symbol) {

        // setting the won value
        int winCredits = betValue * symbol.getValue();

        // incrementing the credits left
        creditLeft += winCredits;

        // setting the totalWinCredits
        totalWinCredits += winCredits;

        // incrementing the games won by one
        gameWin++;

        // setting average net credits
        averageCredits += (winCredits - betValue);

        // play background sound when game is won
        Main.playBgSound("win.wav");

        // throwing the alert message
        Alert wonAlert = setAlertMessages("GAME WON ", "Congratulations! You got " + winCredits + " free credits.", Alert.AlertType.INFORMATION);
        wonAlert.show();
    }

    /**
     * method to calculate the lost value
     */
    private static void lostValue() {

        // incrementing the games lost by one
        gameLoss++;

        // setting average net credits
        averageCredits -= betValue;

        // setting the totalLossCredits
        totalLossCredits += betValue;

        // play background sound when game is lost
        Main.playBgSound("lose.wav");

        // throwing the alert message
        Alert wonAlert = setAlertMessages("GAME LOST", "You Lost! Try Again.", Alert.AlertType.INFORMATION);
        wonAlert.show();
    }

    /**
     * method to calculate profit
     */
    public static double profitValue() {

        profit = ((totalWinCredits) - (totalBetCredits + totalLossCredits));

        return profit;
    }

    /**
     * method to set alert messages
     *
     * @param alertTitle   || title of the alert message
     * @param alertContent || content of the alert message
     * @param alertType    || type of the alert message
     */
    private static Alert setAlertMessages(String alertTitle, String alertContent, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertTitle);
        alert.setHeaderText(null);
        alert.setContentText(alertContent);
        return alert;
    }


    /**
     * method to generate a random value while spinning
     */
    protected static int generateRandomValue(int l) {

        // calling the Random object
        Random generator = new Random();
        int x = generator.nextInt(l);
        return x;
    }

    /**
     * method to update credit values
     */
    private static void updateCredits() {

        // setting up the bet value
        Main.betCredit.setText(String.format("%02d", betValue));
        // setting up the credits left
        Main.creditLeft.setText(String.format("%02d", creditLeft));
    }

    /**
     * getter method to get game wins
     */
    public static int getGameWins() {
        return gameWin;
    }

    /**
     * getter method to get game loss
     */
    public static int getGameLoss() {
        return gameLoss;
    }

    /**
     * getter method to get average credits/game
     */
    public static double getAverageCreditsPerGame() {

        // setting the value for average credits per game
        averageCreditsPerGame = averageCredits / gameCount;
        return averageCreditsPerGame;
    }

    /**
     * getter method to get game count
     */
    public static int getGameCount() {
        return gameCount;
    }

    /**
     * getter method to get total bet credits
     */
    public static int getTotalBetCredits() {
        return totalBetCredits;
    }

    /**
     * getter method to get total win credits
     */
    public static int getTotalWinCredits() {
        return totalWinCredits;
    }

    /**
     * getter method to get total loss credits
     */
    public static int getTotalLossCredits() {
        return totalLossCredits;
    }
}

