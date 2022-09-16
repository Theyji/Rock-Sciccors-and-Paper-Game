/**
 * class RSPgame for game of "ROCK SCISSORS AND PAPER"
 * this class contains model and methods for the gameplay
 * @author Ayodeji Eniabire Student ID: 000878946
 */

public class RSPgame {

        /** initiate variable for player's pick **/
        private String playerPick;

        /** initiate variable for computer's pick **/
        private String computerPick;

        /** initiate variable for decision **/
        private String decision;


    /** constructor **/
        public RSPgame(String playerPick){

            this.playerPick = playerPick;
        }


    /**
     * set value for variable playerPick
      * @param playerPick
     */
    public void setPlayerPick(String playerPick) {
            this.playerPick = playerPick;
        }

    /**
     * method returns playerPick variable when called
     * @return playerPick
     */
    public String getPlayerPick() {
            return playerPick;
        }

    /**
     * method returns variable computerPick
     * @return computerPick
     */
    public String getComputerPick() {
            return computerPick;
        }


    /**
     * method retuns variable decision
     * @return decision
     */
    public String getDecision() {
            return decision;
        }

    /**
     * this method generates random number and assign its value to variable computer pick
     * based on random generated number
     */
    public void play(){

            int pick = (int) (Math.random() * 15 );

            if (pick <= 5){
                computerPick = "Rock";
            } else if (pick <= 10) {
                computerPick = "Paper";
            } else {
                computerPick = "Scissors";
            }
        }


    /**
     * this method compares values of variable playerPick and computerPick
     * assign value to variable decision based
     * return decision
     * @return decision
     */
    public String decide (){
            if ((playerPick.equalsIgnoreCase("Rock")) && (computerPick.equalsIgnoreCase("Scissors"))){
                decision = "You Won";
            } else if ((playerPick.equalsIgnoreCase("Paper")) && (computerPick.equalsIgnoreCase("Rock"))) {
                decision = "You Won";
            } else if ((playerPick.equalsIgnoreCase("Scissors")) && (computerPick.equalsIgnoreCase("Paper"))) {
                decision = "You Won";
            } else if (playerPick.equalsIgnoreCase(computerPick)) {
                decision = "Draw";
            }
            else {
                decision = "You Lost";
            }
            return decision;
        }

    /**
     * return String value of variable playerPick, computerPick and decision
     * @return String
     */
    @Override
        public String toString() {
            return "Playerpick: " + playerPick + ", " + "ComputerPick: " + computerPick + ", " + "Decission: " + decide();
        }
}
