package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.BlackJack;
import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetHighestBidder implements DealerStrategy {

	private List<Player> players;

	public TargetHighestBidder(BlackJack currentGame) {
		this.players = currentGame.getPlayers();

	}

	@Override
	public Action action(Hand dealerHand) {

		Player target = getMaxBidder(players.get(0), players.get(1), players.get(2));
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

	private Player getMaxBidder(Player p1, Player b1, Player b2) {
		Player maxBidder = p1;

		if (maxBidder.getHand().getBet() < b1.getHand().getBet()) {
			maxBidder = b1;
		}

		if (maxBidder.getHand().getBet() < b2.getHand().getBet()) {
			maxBidder = b2;
		}

		return maxBidder;
	}

}
