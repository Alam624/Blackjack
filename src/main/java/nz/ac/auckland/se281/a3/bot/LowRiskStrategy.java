package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class LowRiskStrategy implements Strategy {

	private Random rand = new Random();

	@Override
	public Action action(Hand hand) {
		// only hold if score is 17 or greater
		if (hand.getScore() >= 17) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}

	}

	@Override
	public int bet() {
		// randomly bets bewteen 10 and 50
		return (rand.nextInt(41) + 10);
	}

}
