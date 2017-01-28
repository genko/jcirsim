package de.mobillinux.jcircsim.elements;

public class NJfetElm extends JfetElm {
	public NJfetElm(int xx, int yy) {
		super(xx, yy, false);
	}

	public Class<JfetElm> getDumpClass() {
		return JfetElm.class;
	}
}
