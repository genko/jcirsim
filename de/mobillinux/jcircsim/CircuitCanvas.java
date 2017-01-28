package de.mobillinux.jcircsim;
import java.awt.*;

public class CircuitCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 516098090418362896L;
	CirSim pg;

	public CircuitCanvas(CirSim p) {
		pg = p;
	}

	public Dimension getPreferredSize() {
		return new Dimension(300, 400);
	}

	public void update(Graphics g) {
		pg.updateCircuit(g);
	}

	public void paint(Graphics g) {
		pg.updateCircuit(g);
	}
};
