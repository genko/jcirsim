package de.mobillinux.jcircsim;
import java.util.Vector;

public class CircuitNode {
	int x, y;
	public Vector links;
	boolean internal;

	public CircuitNode() {
		links = new Vector();
	}
}
