package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.BlackJack;
import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetTopWinner implements DealerStrategy {

	private List<Player> players;
	private Player target;

	public TargetTopWinner(BlackJack currentGame) {
		this.players = currentGame.getPlayers();
		target = currentGame.getNetWinner();
	}

	@Override
	public Action Action(Hand dealerHand) {

		if (!target.getHand().isBlackJack()) {
			if (dealerHand.getScore() >= target.getHand().getScore() || target.getHand().isBust()) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		} else {
			if (dealerHand.getScore() >= 17) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		}
	}

}
