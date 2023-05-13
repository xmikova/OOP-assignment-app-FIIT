
public class StrongKnight extends Knight {
	
	void attack(Ogre ogre) {
		ogre.energy = (int) (0.7 * ogre.energy); // makes ogre lose more energy in the clash
		ogre.revenge(this); // this represents a reference to the current knight object 
	}
}
