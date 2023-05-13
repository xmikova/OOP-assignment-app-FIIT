package characters;

public class BadOgre extends Ogre {
	void revenge(Knight knight) {
		if (hungry)
			eat(knight);
	}
	void eat(Knight knight) {
		knight.setEnergy(0);
	}
	
	public BadOgre(int energy) {
		super(energy);
	}
}
