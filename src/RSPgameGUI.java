import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Use this template to create Apps with Graphical User Interfaces.
 *
 * @author Ayodeji Eniabire, Student ID: 000878946
 */
public class RSPgameGUI extends Application {

    // TODO: Instance Variables for View Components and Model

    /** output labels **/
    Label decision, playerPick, computerPick, display, helpinfo;

    /** class RSPgame model **/
    private RSPgame model;

    /** input text field **/
    TextField playerPickIn;

    /** buttons **/
    Button play, reset, help;

    /** text areas **/
    TextArea title, yourPick, yourPickTitle, computerPickTitle ;


    // create object of Timer class to set timer
    Timer setTimer = new Timer();


    // create objects of Image class to load images for playerPick
    Image rockimg = new Image("images/Rock.png");
    Image paperimg = new Image("images/Paper.png");
    Image scissors = new Image("images/Scissors.png");


    // create objects of ImageView class to view loaded images for playerPick
    ImageView imgRock = new ImageView(rockimg);
    ImageView imgPaper = new ImageView(paperimg);
    ImageView imgScissors = new ImageView(scissors);

    // create objects of Image class to load images for computerPick //
    Image rockimgC = new Image("images/Rockcopy.png");
    Image paperimgC = new Image("images/Papercopy.png");
    Image scissorsC = new Image("images/Scissorscopy.png");


    // create objects of ImageViewr to view images for computerPick
    ImageView imgRockC = new ImageView(rockimgC);
    ImageView imgPaperC = new ImageView(paperimgC);
    ImageView imgScissorsC = new ImageView(scissorsC);


    // TODO: Private Event Handlers and Helper Methods

    /**
     * method takes user input and set it as value to palyerPikIn
     * validates user input
     * displays and clears error or welcome message within a set timer
     * @param e
     */
    private void setPlayerPickIn(ActionEvent e){

        model.setPlayerPick(playerPickIn.getText()); // sets user input value to playerPickIn

        if((playerPickIn.getText().equalsIgnoreCase("rock")) // validates user input
                ||(playerPickIn.getText().equalsIgnoreCase("paper")
                || (playerPickIn.getText().equalsIgnoreCase("scissors")))){

            display.setText("Welcome! click play button to continue"); // display welcome message if input is valid

            display.setTextFill(Color.GREEN); // set welcome display color

            TimerTask errorDisplay = new TimerTask() { // set timer to clear welcome message off display
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        display.setText("");
                        display.setTextFill(Color.RED);
                    });
                }
            };
            setTimer.schedule(errorDisplay, 1500L);

            play.setDisable(false); // activate play button

            playerPickIn.setEditable(false); // deactivate player input text field
            
        }else {
            display.setText("Invalid input, click on help button to see valid inputs"); // display error message

            display.setTextFill(Color.RED); // set error message color

            help.requestFocus(); // put focus on help button

            TimerTask errorDisplay = new TimerTask() { // set timer to clear error message off display
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        display.setText("");
                    });
                }
            };
            setTimer.schedule(errorDisplay, 1500L);

            playerPickIn.setEditable(true); // set user input text field live and editable
        }
    }

    /**
     * method calls play method of class RSPgame model
     * call getPlayerPick and getComputerPick method from class RSPgame model
     * compare their values
     * set images for playerPic and ComputerPick
     * set timers to control computerPick display
     * @param e
     */
    private void playGame (ActionEvent e){

        model.play(); // calls play method of class RSPgame model

        play.setDisable(true);

        if (model.getPlayerPick().equalsIgnoreCase("rock")){  // comparison and image display decision
            playerPick.setGraphic(imgRock);
        } else if (model.getPlayerPick().equalsIgnoreCase("paper")) {
            playerPick.setGraphic(imgPaper);
        }
        else if (model.getPlayerPick().equalsIgnoreCase("scissors")){
            playerPick.setGraphic(imgScissors);
        }

        // computerPick image display timer
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    // comparison and inage display dicision
                    if (model.getComputerPick().equalsIgnoreCase("rock")){
                        computerPick.setGraphic(imgRockC);
                    } else if (model.getComputerPick().equalsIgnoreCase("paper")) {
                        computerPick.setGraphic(imgPaperC);
                    }
                    else if (model.getComputerPick().equalsIgnoreCase("scissors")){
                        computerPick.setGraphic(imgScissorsC);
                    }
                });
            }
        };
        setTimer.schedule(task, 2000L);

        // final gane dicision display
        TimerTask task2 = new TimerTask() { // create timer task
            @Override
            public void run() {
                Platform.runLater(() -> {

                    decision.setText(model.decide()); // assign return value from decide method of class RSPgame model

                    // comparison and inage display decision
                    if (model.decide().equalsIgnoreCase("you won")) {
                        decision.setTextFill(Color.GREEN);
                    }else if (model.decide().equalsIgnoreCase("you lost")){
                        decision.setTextFill(Color.RED);
                    } else if (model.decide().equalsIgnoreCase("draw")) {
                        decision.setTextFill(Color.CHOCOLATE);
                    }else {
                        decision.setTextFill(Color.BLACK);
                    }
                });
            }
        };
        setTimer.schedule(task2, 3000L);
    }

    /**
     * resets the game
     * @param e
     */
    private void setReset(ActionEvent e){
        playerPickIn.setText(null);
        playerPickIn.setEditable(true);
        display.setText(null);
        computerPick.setGraphic(null);
        playerPick.setGraphic(null);
        decision.setGraphic(null);
        helpinfo.setText(null);
        play.setDisable(true);
        decision.setText(null);
    }

    /**
     * displays help information
     * @param e
     */
    private void setHelpinfo(ActionEvent e){
        helpinfo.setText("""
                Welcome to the game of ROCK, SCISSORS AND PAPER\s
                 How to Play:\s
                 Type in rock or scissors or paper as your pick\s
                 click play to compete with computer's pick\s
                 Game Rules:\s
                 Rock beats Scissors\s
                 Paper beats Rock\s
                 Scissors beats Paper\s
                 Click Reset button to start again at any stage of gameplay\s
                 Click Reset button to close this Help-info\s""");
    }

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 400); // set the size here
        stage.setTitle("GameOfRockScissorsAndPaper"); // set the window title here
        stage.setScene(scene);

        // TODO: Add your GUI-building code here

        // 1. Create the model
        model = new RSPgame("");


        // 2. Create the GUI components
        computerPick = new Label(""); // computerPick output display

        computerPickTitle = new TextArea("Computer's Pick"); // title for computerPick display

        computerPickTitle.setEditable(false); // set text area not editable

        decision = new Label(""); // game final decision output dispaly

        decision.setFont(Font.font("Papyrus", 30)); // set font and size of decision output display

        display = new Label(); // welcome and error messages output display

        help = new Button("Help"); // help button

        help.setTextFill(Color.ORANGE); // set help button color

        help.requestFocus(); // set focus on help button

        helpinfo = new Label(); // help info output label

        helpinfo.setTextFill(Color.INDIANRED); // set help information text color



        playerPickIn = new TextField(); // user input text-field tittle

        playerPickIn.setPromptText("Enter Your Pick"); // set a descriptive place holder

        playerPickIn.setFocusTraversable(false); // remove default from input text field

        playerPick = new Label(""); // playerPick output display

        play = new Button("Play"); // play button

        play.setDisable(true); // disable play button

        play.setTextFill(Color.GREEN); // set play button collor

        reset = new Button("Reset"); // reset button

        reset.setTextFill(Color.RED); // set reset button color

        title = new TextArea("Game of Rock, Scissors and Paper"); // game title text area

        title.setBackground(Background.fill(Color.BLUE)); // set background for game title

        title.setEditable(false); // set game title text area not editable

        yourPick = new TextArea("Your Pick:"); // text area that provide title for playerPickIn

        yourPick.setEditable(false); // set text area not editable

        yourPickTitle = new TextArea("Your Pick"); // title for playerPick display

        yourPickTitle.setEditable(false); // set text area not editable


        // 3. Add components to the root
        root.getChildren().addAll(playerPickIn, playerPick, computerPick, help, title, play,
                reset,decision, yourPick, display, helpinfo, yourPickTitle, computerPickTitle);


        // 4. Configure the components (colors, fonts, size, location)
        playerPickIn.relocate(90,105);

        yourPick.relocate(5, 100);

        yourPick.setPrefSize(80, 5);

        yourPickTitle.setPrefSize(80,10);

        computerPickTitle.setPrefSize(120, 10);

        computerPickTitle.relocate(370,160);

        yourPickTitle.relocate(370,90);

        title.relocate(190,20);

        title.setPrefSize(220,10);

        play.relocate(80,150);

        reset.relocate(135, 150);

        help.relocate(200, 150);

        helpinfo.relocate(20, 200);

        playerPick.relocate(500, 80);

        computerPick.relocate(500, 150);

        decision.relocate(430,210);

        display.relocate(5, 80);

        // 5. Add Event Handlers and do final setup

        play.setOnAction(this::playGame); // activates playGame method when play button is clicked

        playerPickIn.setOnAction(this::setPlayerPickIn); // activates playerPickIn method when input is entered

        reset.setOnAction(this::setReset); // activates setRest method when reset button is clicked

        help.setOnAction(this::setHelpinfo); // activates setHelpInfo nethod when help button is clicked


        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}