package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class BotRandomStrategy implements Strategy {

	private Random rand = new Random();

	@Override
	public Action action(Hand hand) {
		// randomly chooses between HIT or HOLD
		if (rand.nextBoolean()) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}

	}

	@Override
	public int bet() {
		// randomly bets between 1 and 100
		return (rand.nextInt(100) + 1);
	}

}
