/**
 * ===========================================
 * Java Pdf Extraction Decoding Access Library
 * ===========================================
 *
 * Project Info :  http://www.jpedal.org
 *
 * (C) Copyright 2014, IDRsolutions and Contributors.
 *
 * 	This file is part of JPedal
 *
@LICENSE@
 *
 * ---------------
 * PDFFilter.java
 * ---------------
 * (C) Copyright 2008, by IDRsolutions and Contributors.
 *
 *
 * --------------------------
 */
package org.jpedal.netbeans;

import java.io.File;

/**
 * standard Java method to file File window to just PDF files
 */
public class PdfFilter extends javax.swing.filechooser.FileFilter{

    public boolean accept(File file) {
        
        //display (return true if directory or PDF
        return (file.isDirectory() || file.getName().endsWith("pdf"));
        
    }
    public String getDescription() {
        return "*.pdf";
    }

}
