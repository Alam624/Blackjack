package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetHighestBidder implements DealerStrategy {

	@Override
	public Action Action(Player Player1, Player Bot1, Player Bot2, Dealer dealer) {

		Player target = getMaxBidder(Player1, Bot1, Bot2);
		if (dealer.getHand().getScore() > target.getHand().getScore()) {
			return Action.HOLD;
		} else {
			return Action.HIT;
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
