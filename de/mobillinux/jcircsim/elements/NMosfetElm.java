package de.mobillinux.jcircsim.elements;
public class NMosfetElm extends MosfetElm {
	public NMosfetElm(int xx, int yy) { super(xx, yy, false); }
	public Class<MosfetElm> getDumpClass() { return MosfetElm.class; }
    }
