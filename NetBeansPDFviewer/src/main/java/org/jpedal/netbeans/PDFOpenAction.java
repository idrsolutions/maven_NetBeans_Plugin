
package org.jpedal.netbeans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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

    private final PDFDataObject context;

    public PDFOpenAction(PDFDataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
            
        String path="";
        // Get the path of the PDF
        if(context != null){
            FileObject f = context.getPrimaryFile();
            path = FileUtil.toFile(f).getPath();
        }

        //this gives me a new window each time
        TopComponent tc=new PDFDisplayTopComponent(path);
        tc.open();
        tc.requestActive();
        
    }
}
