package game;

public class ClashWinners implements ClashFollower {
    Game game;

    public ClashWinners(Game game) {
        this.game = game;
    }

    public void inform() {
        int knightsEnergy = 0;
        int ogresEnergy = 0;

        for (int i = 0; i < game.numberOfWarriors; ++i) {
            knightsEnergy += game.knights[i].getEnergy();
            ogresEnergy += game.ogres[i].getEnergy();
        }

        if (knightsEnergy < ogresEnergy)
            System.out.println("*** Knights are losing. ***");
        else
            System.out.println("*** Ogres are losing. ***");
    }
}
