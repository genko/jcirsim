package de.mobillinux.jcircsim.elements;

public class DCVoltageElm extends VoltageElm {
	public DCVoltageElm(int xx, int yy) {
		super(xx, yy, WF_DC);
	}

	public Class getDumpClass() {
		return VoltageElm.class;
	}
}
