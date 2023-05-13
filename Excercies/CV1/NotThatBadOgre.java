
public class NotThatBadOgre extends BadOgre { // new class extending Ogre
	void revenge(Knight knight) {
		if (hungry)
			knight.energy = (int)(0.5 * knight.energy);
	}
}