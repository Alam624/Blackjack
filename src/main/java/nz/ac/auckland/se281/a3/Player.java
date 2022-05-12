package nz.ac.auckland.se281.a3;

/**
 * 
 * You can (and should) add new fields and/or methods
 *
 */
public abstract class Player extends Participant {

	private int wins = 0;
	private int losses = 0;

	public void addWin() {
		wins += 1;
	}

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

	public Player(String name) {
		super(name);
	}

	public abstract int makeABet();

}
