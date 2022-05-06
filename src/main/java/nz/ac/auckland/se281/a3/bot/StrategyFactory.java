package nz.ac.auckland.se281.a3.bot;

public class StrategyFactory {

	public static Strategy createStrategy(String type) {
		switch (type) {
		case "R":
			return new RandomStrategy();
		case "LR":
			return new LowRiskStrategy();
		case "HR":
			return new HighRiskStrategy();
		default:
			System.out.println("Wrong strategy type.");

		}
		return null;
	}
}
