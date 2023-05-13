package characters;

public class TerryfyingOgre extends BadOgre {

	public TerryfyingOgre(int energy) {
		super(energy); //from superclass BadOgre
	}
	
	void revenge(Knight knight) {
		if (hungry)
			super.eat(knight); //from superclass BadOgre
	}
	
	void roar() {
		System.out.println("rooooooarrrrr"); //added the roar method
	}
	
	void eat(Knight knight){
		knight.setEnergy(knight.getEnergy() - 2*knight.getEnergy());
	}
}
