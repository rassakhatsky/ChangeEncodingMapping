/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changeencoding;

//Импорт SAP библиотек
import com.sap.aii.mapping.api.*;

//Импорт библиотек для работы с файлами (для тестирования)
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rassakhatsky
 */
public class ChangeEncoding extends AbstractTransformation {

    public static void main(String[] args) throws FileNotFoundException, StreamTransformationException {
        String folder = "/Users/rassakhatsky/Desktop/";
        String file_1 = "11.xsd";
        String file_2 = "12.xml";
        com.sap.aii.mapping.api.TransformationInput ti = buildTransformationInput(folder + file_1);
        com.sap.aii.mapping.api.TransformationOutput to = buildTransformationOutput(folder + file_2);
        ChangeEncoding ss = new ChangeEncoding();
        ss.transform(ti, to);
    }

    @Override
    public void transform(TransformationInput ti, TransformationOutput to) throws StreamTransformationException {
        //getTrace().addInfo("File Reading started ");
        //String strData = convertStreamToString(ti.getInputPayload().getInputStream());
        //String result = new String((ti.getInputPayload().getInputStream(), "cp1258");
        StringBuilder preSource = new StringBuilder("");
        preSource = convertStreamToString(ti.getInputPayload().getInputStream());
        String strData = null;
        try {
            strData = new String(preSource.toString().getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ChangeEncoding.class.getName()).log(Level.SEVERE, null, ex);
        }

        getTrace().addInfo("File Reading successfully completed ");

        try {
            getTrace().addInfo("Generating XML started");
            // Specify the Encoding type you would like to have in Below line(un comment one you like).
            to.getOutputPayload().getOutputStream().write(strData.getBytes("windows-1251"));

        } catch (IOException e) {
        }
    }

    public StringBuilder convertStreamToString(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            int ch;
            while ((ch = in.read()) > -1) {
                sb.append((char) ch);
            }
        } catch (IOException exception) {
        }
        return sb;
    }

    private static TransformationInput buildTransformationInput(String file) {
        MyTransformationInput ti = new MyTransformationInput();
        ti.setFileName(file);
        InputPayload inputPayload = ti.getInputPayload();
        return ti;
    }

    private static TransformationOutput buildTransformationOutput(String file) {
        MyTransformationOutput to = new MyTransformationOutput();
        to.setFileName(file);
        return to;
    }
}
