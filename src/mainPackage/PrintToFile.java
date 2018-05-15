package mainPackage;

import javafx.scene.control.Alert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PrintToFile {

    /**
     * method to save details to a file
     */
    public static void saveToFile() {

        // get current date and time from SimpleDateFormat
        String currentDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());

        // creating a statistics directory
        File fileDirectory = new File("src/Statistics");
        fileDirectory.mkdirs();

        // setting the file name to current date and time as mentioned in the requirements
        String fName = currentDate + ".txt";

        // creating file object
        File file = new File(fileDirectory, fName);
        PrintWriter printWrite = null;
        FileWriter fileWrite = null;

        try {
            fileWrite = new FileWriter(file);
            printWrite = new PrintWriter(fileWrite, true);

            // print statistics in the file
            printWrite.println("--------------------------- *** GAME STATISTICS *** ---------------------------\n\n" + "Date & Time : " + currentDate + "\n" + "Wins : " + Controller.getGameWins() + "\n" + "Losses : " + Controller.getGameLoss() + "\n" + "Games Played : " + Controller.getGameCount() + "\n" + "Average Credits per Game : " + Controller.getAverageCreditsPerGame());

            // throwing the alert message
            Alert wonAlert = setAlertMessages("SUCCESSFUL", "File saved successfully!", Alert.AlertType.INFORMATION);
            wonAlert.show();

            printWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            printWrite.close();

            try {
                fileWrite.close();
            } catch (Exception e) {
                // throwing the alert message
                Alert wonAlert = setAlertMessages("ERROR", "Trouble saving file!", Alert.AlertType.ERROR);
                wonAlert.show();
            }
        }
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
}
