
package org.jpedal.netbeans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javax.swing.JOptionPane;
import org.jpedal.examples.viewer.FXStartup;
import org.jpedal.examples.viewer.OpenViewerFX;
import org.jpedal.utils.JavaFXHelper;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@ActionID(
        category = "File",
        id = "org.jpedal.netbeans.PDFOpenAction"
)
@ActionRegistration(
        displayName = "#CTL_PDFOpenAction"
)
@ActionReference(path = "Loaders/application/PDF/Actions", position = -300)
@Messages("CTL_PDFOpenAction=Open in Viewer")
public final class PDFOpenAction implements ActionListener {

    private final PDFViewerTypes viewerType =PDFViewerTypes.INTERNAL_OPENVIWERFX;
    
    private final PDFDataObject context;

    public PDFOpenAction(PDFDataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        boolean isFXAvailable=JavaFXHelper.isJavaFXAvailable();
        
        if(isFXAvailable){
            
            String path="";
            // Get the path of the PDF
            if(context != null){
                FileObject f = context.getPrimaryFile();
                path = FileUtil.toFile(f).getPath();
            }
            
            if(viewerType.equals(PDFViewerTypes.EXTERNAL_OPENVIEWERFX)){
                
                if(!OpenViewerFX.exitOnClose){
                     //message in NetBeans to tell user, that they can't more than one ExternalFX at a time
                    NotifyDescriptor.Message msg = new NotifyDescriptor.Message("You need to restart NetBeans to re-open External Viewer");
                    DialogDisplayer.getDefault().notify(msg);       
                }else{
                    final String[] args=new String[]{path};
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            Platform.setImplicitExit(false);
                            FXStartup.main(args);
                           
                        }
                    };

                    Runtime.getRuntime().addShutdownHook(thread);
                    OpenViewerFX.exitOnClose=false; //stops our viewer calling System.exit()
                    thread.start();
                }
            }else{ //run in netbeans

                TopComponent tc;
                //this gives me a new window each time
                tc=new PDFDisplayTopComponent(path,viewerType);
                tc.open();
                tc.requestActive();
            }
        }else{
            JOptionPane.showMessageDialog(null, "JavaFX is not available");
        }
    }
}
