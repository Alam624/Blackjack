package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class LowRiskStrategy implements Strategy {

	Random rand = new Random();

	@Override
	public nz.ac.auckland.se281.a3.Participant.Action Action(Hand hand) {
		if (hand.getScore() >= 17) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}

	}

	@Override
	public int Bet() {
		return (rand.nextInt(30) + 1);
	}

}
