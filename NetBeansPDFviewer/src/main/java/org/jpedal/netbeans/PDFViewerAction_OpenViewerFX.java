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
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@ActionID(
        category = "File",
        id = "org.jpedal.netbeans.PDFViewerAction_OpenViewerFX"
)
@ActionRegistration(
        displayName = "#CTL_PDFViewerAction_OpenViewerFX"
)
@ActionReferences({
    @ActionReference(path = "Menu/File/PDF Viewer", position = 1075, separatorBefore = 1025, separatorAfter = 1125),
    @ActionReference(path = "Menu/Window/PDF Viewer", position = 1075, separatorBefore = 1025, separatorAfter = 1125),
    @ActionReference(path = "Editors/text/x-java/Popup/PDFiewer Options")
})

@Messages("CTL_PDFViewerAction_OpenViewerFX=OpenViewerFX")
public final class PDFViewerAction_OpenViewerFX implements ActionListener {
   
    
    @Override
    public void actionPerformed(ActionEvent e) {

        TopComponent tc;
        //this gives me a new window each time
                tc=new PDFDisplayTopComponent(PDFViewerTypes.INTERNAL_OPENVIWERFX);

        tc.open();
        tc.requestActive();
    }
}
