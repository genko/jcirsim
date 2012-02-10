package de.mobillinux.jcircsim;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import javax.swing.JFileChooser;

class ImportDialog extends Dialog implements ActionListener {
    CirSim cframe;
    Button importButton, closeButton, exportButton, importFileButton;
    TextArea text;
    boolean isURL;
	
    ImportDialog(CirSim f, String str, boolean url, boolean imp) {
	super(f, (str.length() > 0) ? "Export" : "Import", false);
	isURL = url;
	cframe = f;
	setLayout(new ImportDialogLayout());
	add(text = new TextArea(str, 10, 60, TextArea.SCROLLBARS_BOTH));
	importButton = new Button("Import");
	importFileButton = new Button("Import from File");
	exportButton = new Button("Export to File");
	if (imp)
	{
	    add(importButton);
	    add(importFileButton);
	}
	else
	    add(exportButton);
	importButton.addActionListener(this);
	importFileButton.addActionListener(this);
	exportButton.addActionListener(this);
	add(closeButton = new Button("Close"));
	closeButton.addActionListener(this);
	Point x = cframe.main.getLocationOnScreen();
	resize(400, 300);
	Dimension d = getSize();
	setLocation(x.x + (cframe.winSize.width-d.width)/2,
		    x.y + (cframe.winSize.height-d.height)/2);
	show();
	if (str.length() > 0)
	    text.selectAll();
    }

    public void actionPerformed(ActionEvent e) {
	int i;
	Object src = e.getSource();
	if (src == importButton) {
	    cframe.readSetup(text.getText());
	    setVisible(false);
	}
	if (src == closeButton)
	    setVisible(false);

	if (src == importFileButton) {
		String filename = File.separator+"tmp";
		JFileChooser fc = new JFileChooser(new File(filename));
		fc.showOpenDialog(cframe);
		File selFile = fc.getSelectedFile();
		try {
		  Scanner scanner = new Scanner(new FileInputStream(selFile));
	      while (scanner.hasNextLine()){
	        text.append(scanner.nextLine()+"\n");
	      }
		}
	    catch (Exception ex)
	    {
	    	  System.out.println("Error importing file");
	    }
	}	
	
	if (src == exportButton) {
		String filename = File.separator+"tmp";
		JFileChooser fc = new JFileChooser(new File(filename));
		int res = fc.showSaveDialog(cframe);
		File selFile = fc.getSelectedFile();
		Writer out;
	    try {
	      out = new OutputStreamWriter(new FileOutputStream(selFile));
	      out.write(text.getText());
	      out.close();
	    }
	    catch (Exception ex)
	    {
	      System.out.println("Error exporting file!");
	    }
	}
    }
    public boolean handleEvent(Event ev) {
	if (ev.id == Event.WINDOW_DESTROY) {
	    CirSim.main.requestFocus();
	    setVisible(false);
	    cframe.impDialog = null;
	    return true;
	}
	return super.handleEvent(ev);
    }
}
    
