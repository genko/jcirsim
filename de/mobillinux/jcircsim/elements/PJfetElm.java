package de.mobillinux.jcircsim.elements;

public class PJfetElm extends JfetElm {
	public PJfetElm(int xx, int yy) {
		super(xx, yy, true);
	}

	public Class<JfetElm> getDumpClass() {
		return JfetElm.class;
	}
}