package de.mobillinux.jcircsim.elements;
public class PushSwitchElm extends SwitchElm {
	public PushSwitchElm(int xx, int yy) { super(xx, yy, true); }
	public Class<SwitchElm> getDumpClass() { return SwitchElm.class; }
    }
