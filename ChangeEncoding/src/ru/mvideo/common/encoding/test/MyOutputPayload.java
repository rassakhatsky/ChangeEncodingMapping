package ru.mvideo.common.encoding.test;

import com.sap.aii.mapping.api.OutputPayload;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rassakhatsky
 */
class MyOutputPayload extends OutputPayload {

    String filename;

    public void setFileName(String filename) {
        this.filename = filename;
    }

    @Override
    public OutputStream getOutputStream() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyOutputPayload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fos;
    }
}
