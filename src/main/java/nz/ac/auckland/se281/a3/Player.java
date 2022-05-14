package nz.ac.auckland.se281.a3;

/**
 * 
 * You can (and should) add new fields and/or methods
 *
 */
public abstract class Player extends Participant {

	public Player(String name) {
		super(name);
	}

	private int wins = 0;
	private int losses = 0;

	/**
	 * Method that is called when the player beats the dealer in a round. Increments
	 * the wins counter variable by one.
	 */
	public void addWin() {
		wins += 1;
	}

	/**
	 * Method that is called when the player loses to the dealer in a round.
	 * Increments the losses counter variable by one.
	 */
	public void addLoss() {
		losses += 1;
	}

	public int getNetWins() {
		return wins - losses;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public abstract int makeABet();

}
