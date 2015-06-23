/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpedal.netbeans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@ActionID(
        category = "File",
        id = "org.jpedal.netbeans.OracleGuideAction"
)
@ActionRegistration(
        displayName = "#CTL_OracleGuideAction"
)
@ActionReference(path = "Menu/Help", position = -200, separatorBefore = -250, separatorAfter = -150)
@Messages("CTL_OracleGuideAction=Oracle Guide")
public final class OracleGuideAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        
        TopComponent tc;
        //this gives me a new window each time
       // tc = new PDFDisplayTopComponent("http://docs.oracle.com/cd/E50453_01/doc.80/e50452.pdf", "C:\\Users\\Sylwia\\Desktop\\OracleDoc.xml",PDFViewerTypes.INTERNAL_OPENVIWERFX);
        tc = new PDFDisplayTopComponent("http://docs.oracle.com/cd/E50453_01/doc.80/e50452.pdf", PDFViewerTypes.INTERNAL_OPENVIWERFX);

        tc.open();
        tc.requestActive();

    }
}
