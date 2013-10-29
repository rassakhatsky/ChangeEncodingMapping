/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package changeencoding;

import com.sap.aii.mapping.api.InputPayload;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rassakhatsky
 */
class MyInputPayload extends InputPayload {
    private String filename;
    
    public void setFileName(String filename) {
        this.filename = filename;
    }

    @Override
    public InputStream getInputStream() {
        FileInputStream fis=null;
        try {
            fis = new FileInputStream(filename);
            return fis;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChangeEncoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fis;
    }
    
    
}
