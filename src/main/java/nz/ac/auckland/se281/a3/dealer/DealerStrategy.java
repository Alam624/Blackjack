package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public interface DealerStrategy {

	public Action Action(Player Player1, Player Bot1, Player Bot2, Dealer dealer);
}
