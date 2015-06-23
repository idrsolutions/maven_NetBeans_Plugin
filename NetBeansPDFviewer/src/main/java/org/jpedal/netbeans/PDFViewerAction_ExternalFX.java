/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpedal.netbeans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jpedal.examples.viewer.FXStartup;
import org.jpedal.examples.viewer.OpenViewerFX;
import org.jpedal.utils.JavaFXHelper;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "org.jpedal.netbeans.PDFViewerAction_ExternalFX"
)
@ActionRegistration(
        displayName = "#CTL_PDFViewerAction_ExternalFX"
)
@ActionReferences({
    @ActionReference(path = "Menu/File/PDF Viewer", position = 1075, separatorBefore = 1025, separatorAfter = 1125),
    @ActionReference(path = "Menu/Window/PDF Viewer", position = 1075, separatorBefore = 1025, separatorAfter = 1125),
    @ActionReference(path = "Editors/text/x-java/Popup/PDFiewer Options")
})

@Messages("CTL_PDFViewerAction_ExternalFX=ExternalFX")
public final class PDFViewerAction_ExternalFX implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        boolean isFXAvailable = JavaFXHelper.isJavaFXAvailable();

        if (isFXAvailable) {

            if (!OpenViewerFX.exitOnClose) {
                //message in NetBeans to tell user, they can't run the external viewer more than one
               NotifyDescriptor.Message msg = new NotifyDescriptor.Message("You need to restart NetBeans to re-open External Viewer ");
               DialogDisplayer.getDefault().notify(msg);
            } else {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        // Platform.setImplicitExit(false);
                        FXStartup.main(null);

                    }
                };

                Runtime.getRuntime().addShutdownHook(thread);
                OpenViewerFX.exitOnClose = false; //stops our viewer calling System.exit()
                thread.start();
            }

        }
    }
}
