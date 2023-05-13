package characters;

public class Ogre implements Energy {
	boolean hungry;
	int energy;
	
	public Ogre(int energy) {
		setEnergy(energy);
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public void increaseEnergy(int energy) {
		this.energy += energy;
	}
	public void decreaseEnergy(int energy) {
		this.energy -= energy;
	}
	public int getEnergy() {
		return energy;
	}
	
	public void setValues(int energy) {
		this.energy = energy;
	}

	public void setValues(boolean hungry) {
		this.hungry = hungry;
	}
	
	public void setValues(int energy, boolean hungry) {
		this.energy = energy;
		this.hungry = hungry;
	}
	
	void revenge(Knight knight) {
		if (energy > knight.getEnergy())
			knight.setEnergy((int) (0.9 * knight.getEnergy()));
	}
}
