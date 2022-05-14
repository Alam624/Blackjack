package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.BlackJack;
import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetTopWinner implements DealerStrategy {

	private Player target;

	public TargetTopWinner(BlackJack currentGame) {
		target = currentGame.getNetWinner();
	}

	@Override
	public Action action(Hand dealerHand) {

		// if the targeted player doesn't have a BlackJack
		if (!target.getHand().isBlackJack()) {
			// dealer plays to beat target
			if (dealerHand.getScore() >= target.getHand().getScore() || target.getHand().isBust()) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		} else {
			// dealer will play safely
			if (dealerHand.getScore() >= 17) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		}
	}

}
