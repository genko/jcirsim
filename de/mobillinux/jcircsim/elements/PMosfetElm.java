package de.mobillinux.jcircsim.elements;
public class PMosfetElm extends MosfetElm {
	public PMosfetElm(int xx, int yy) { super(xx, yy, true); }
	public Class<MosfetElm> getDumpClass() { return MosfetElm.class; }
    }
