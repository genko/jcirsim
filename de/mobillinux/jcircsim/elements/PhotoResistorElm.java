package de.mobillinux.jcircsim.elements;
// stub PhotoResistorElm based on SparkGapElm

// FIXME need to uncomment PhotoResistorElm line from CirSim.java
// FIXME need to add PhotoResistorElm.java to srclist

import java.awt.*;
import java.util.StringTokenizer;

import de.mobillinux.jcircsim.CirSim;
import de.mobillinux.jcircsim.EditInfo;

public class PhotoResistorElm extends CircuitElm {
	double minresistance, maxresistance;
	double resistance;
	Scrollbar slider;
	Label label;

	public PhotoResistorElm(int xx, int yy) {
		super(xx, yy);
		maxresistance = 1e9;
		minresistance = 1e3;
		createSlider();
	}

	public PhotoResistorElm(int xa, int ya, int xb, int yb, int f, StringTokenizer st) {
		super(xa, ya, xb, yb, f);
		minresistance = new Double(st.nextToken()).doubleValue();
		maxresistance = new Double(st.nextToken()).doubleValue();
		createSlider();
	}

	public boolean nonLinear() {
		return true;
	}

	public int getDumpType() {
		return 186;
	}

	public String dump() {
		return super.dump() + " " + minresistance + " " + maxresistance;
	}

	Point ps3, ps4;

	void createSlider() {
		CirSim.main.add(label = new Label("Light Level", Label.CENTER));
		int value = 50;
		CirSim.main.add(slider = new Scrollbar(Scrollbar.HORIZONTAL, value, 1, 0, 101));
		CirSim.main.validate();
	}

	public void setPoints() {
		super.setPoints();
		calcLeads(32);
		ps3 = new Point();
		ps4 = new Point();
	}

	public void delete() {
		CirSim.main.remove(label);
		CirSim.main.remove(slider);
	}

	public void draw(Graphics g) {
		setBbox(point1, point2, 6);
		draw2Leads(g);
		// FIXME need to draw properly, see ResistorElm.java
		setPowerColor(g, true);
		doDots(g);
		drawPosts(g);
	}

	void calculateCurrent() {
		double vd = volts[0] - volts[1];
		current = vd / resistance;
	}

	public void startIteration() {
		// FIXME set resistance as appropriate, using slider.getValue()
		resistance = minresistance;
		// System.out.print(this + " res current set to " + current + "\n");
	}

	public void doStep() {
		sim.stampResistor(nodes[0], nodes[1], resistance);
	}

	public void stamp() {
		sim.stampNonLinear(nodes[0]);
		sim.stampNonLinear(nodes[1]);
	}

	public void getInfo(String arr[]) {
		// FIXME
		arr[0] = "spark gap";
		getBasicInfo(arr);
		arr[3] = "R = " + getUnitText(resistance, CirSim.ohmString);
		arr[4] = "Ron = " + getUnitText(minresistance, CirSim.ohmString);
		arr[5] = "Roff = " + getUnitText(maxresistance, CirSim.ohmString);
	}

	public EditInfo getEditInfo(int n) {
		// ohmString doesn't work here on linux
		if (n == 0)
			return new EditInfo("Min resistance (ohms)", minresistance, 0, 0);
		if (n == 1)
			return new EditInfo("Max resistance (ohms)", maxresistance, 0, 0);
		return null;
	}

	public void setEditValue(int n, EditInfo ei) {
		if (ei.value > 0 && n == 0)
			minresistance = ei.value;
		if (ei.value > 0 && n == 1)
			maxresistance = ei.value;
	}
}
