package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class HighRiskStrategy implements Strategy {

	private Random rand = new Random();

	@Override
	public Action action(Hand hand) {
		// only hold if score is 19 or greater
		if (hand.getScore() >= 19) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}

	}

	@Override
	public int bet() {
		// bet randomly between 50 and 100
		return (rand.nextInt(51) + 50);
	}

}
