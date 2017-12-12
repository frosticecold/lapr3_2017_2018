package lapr.project.model;


import java.util.ArrayList;
import java.util.List;

public abstract class Gearbox {

	private List<Gear> gears;
	private float m_final_drive_ration;

	public Gearbox() {
		gears = new ArrayList<>();
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param g
	 */
	public void addGear(Gear g) {
		// TODO - implement Gearbox.addGear
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nameOfGear
	 */
	public Gear getGear(String nameOfGear) {
		// TODO - implement Gearbox.getGear
		throw new UnsupportedOperationException();
	}

	public void getRerveseGear() {
		// TODO - implement Gearbox.getRerveseGear
		throw new UnsupportedOperationException();
	}

}