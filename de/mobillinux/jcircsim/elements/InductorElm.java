package de.mobillinux.jcircsim.elements;
import java.awt.*;
import java.util.StringTokenizer;

import de.mobillinux.jcircsim.EditInfo;
import de.mobillinux.jcircsim.Inductor;

public class InductorElm extends CircuitElm {
	Inductor ind;
	public double inductance;
	public InductorElm(int xx, int yy) {
	    super(xx, yy);
	    ind = new Inductor(sim);
	    inductance = 1;
	    ind.setup(inductance, current, flags);
	}
	public InductorElm(int xa, int ya, int xb, int yb, int f,
		    StringTokenizer st) {
	    super(xa, ya, xb, yb, f);
	    ind = new Inductor(sim);
	    inductance = new Double(st.nextToken()).doubleValue();
	    current = new Double(st.nextToken()).doubleValue();
	    ind.setup(inductance, current, flags);
	}
	public int getDumpType() { return 'l'; }
	public String dump() {
	    return super.dump() + " " + inductance + " " + current;
	}
	
	Point ps3, ps4;
	
	public void setPoints() {
	    super.setPoints();
	    calcLeads(32);
	    ps3 = new Point();
	    ps4 = new Point();
	}
	public void draw(Graphics g) {
	    double v1 = volts[0];
	    double v2 = volts[1];
	    int i;
	    int hs = 8;
	    setBbox(point1, point2, hs);
	    draw2Leads(g);
	    setPowerColor(g, false);
	    int segments = 16;
	    double segf = 1./segments;
	    
	    if (!sim.euroInductorCheckItem.getState()) {
		// draw coil
	    	drawCoil(g, 8, lead1, lead2, v1, v2);
	    } else {
	    	// draw filled rectangle
	    	setVoltageColor(g, v1);
	    	interpPoint2(lead1, lead2, ps1, ps2, 0, hs);
	    	drawThickLine(g, ps1, ps2);
	    	for (i = 0; i != segments; i++) {
	    		double v = v1+(v2-v1)*i/segments;
	    		setVoltageColor(g, v);
	    		interpPoint2(lead1, lead2, ps1, ps2, i*segf, hs);
	    		interpPoint2(lead1, lead2, ps3, ps4, (i+1)*segf, hs);
	    		//draw horizontal lines in different voltage colors
	    		drawThickLine(g, ps1, ps4);
	    	}
	    	interpPoint2(lead1, lead2, ps1, ps2, 1, hs);
			drawThickLine(g, ps1, ps2);
	    }
	    if (sim.showValuesCheckItem.getState()) {
		String s = getShortUnitText(inductance, "H");
		drawValues(g, s, hs);
	    }
	    doDots(g);
	    drawPosts(g);
	}
	public void reset() {
	    current = volts[0] = volts[1] = curcount = 0;
	    ind.reset();
	}
	public void stamp() { ind.stamp(nodes[0], nodes[1]); }
	public void startIteration() {
	    ind.startIteration(volts[0]-volts[1]);
	}
	public boolean nonLinear() { return ind.nonLinear(); }
	void calculateCurrent() {
	    double voltdiff = volts[0]-volts[1];
	    current = ind.calculateCurrent(voltdiff);
	}
	public void doStep() {
	    double voltdiff = volts[0]-volts[1];
	    ind.doStep(voltdiff);
	}
	public void getInfo(String arr[]) {
	    arr[0] = "inductor";
	    getBasicInfo(arr);
	    arr[3] = "L = " + getUnitText(inductance, "H");
	    arr[4] = "P = " + getUnitText(getPower(), "W");
	}
	public EditInfo getEditInfo(int n) {
	    if (n == 0)
		return new EditInfo("Inductance (H)", inductance, 0, 0);
	    if (n == 1) {
		EditInfo ei = new EditInfo("", 0, -1, -1);
		ei.checkbox = new Checkbox("Trapezoidal Approximation",
					   ind.isTrapezoidal());
		return ei;
	    }
	    return null;
	}
	public void setEditValue(int n, EditInfo ei) {
	    if (n == 0)
		inductance = ei.value;
	    if (n == 1) {
		if (ei.checkbox.getState())
		    flags &= ~Inductor.FLAG_BACK_EULER;
		else
		    flags |= Inductor.FLAG_BACK_EULER;
	    }
	    ind.setup(inductance, current, flags);
	}
    }
