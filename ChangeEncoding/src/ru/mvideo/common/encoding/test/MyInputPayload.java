package ru.mvideo.common.encoding.test;

import com.sap.aii.mapping.api.InputPayload;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rassakhatsky
 */
class MyInputPayload extends InputPayload {

    private String filename;

    public void setFileName(String filename) {
        this.filename = filename;
    }

    @Override
    public InputStream getInputStream() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyInputPayload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fis;
    }

}
