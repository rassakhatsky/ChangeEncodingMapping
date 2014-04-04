package ru.mvideo.common.encoding;

import com.sap.aii.mapping.api.*;
import ru.mvideo.common.encoding.test.TestMessage;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rassakhatsky on 05.02.14.
 */
public class IA_ChangeEncoding extends AbstractTransformation {
    private Map param;
    TransformationInput transformationInput = null;
    InputAttachments inputAttachments = null;
    InputStream in = null;

    private final String cyr = "WINDOWS-1251";
    private final String utf = "UTF-8";

    public static void main(String[] args) throws FileNotFoundException, StreamTransformationException {
        String folder, file1, file2;
        TestMessage test = new TestMessage();
        folder = "/Users/rassakhatsky/Desktop/";
        file1 = "13.xml";
        file2 = "12.xml";
        TransformationInput tinput = test.buildTransformationInput(folder + file1);
        TransformationOutput toutput = test.buildTransformationOutput(folder + file2);
        IA_ChangeEncoding changeEncoding = new IA_ChangeEncoding();
        changeEncoding.transform(tinput, toutput);
    }

    @Override
    public void transform(TransformationInput transformationInput, TransformationOutput transformationOutput) throws
            StreamTransformationException {
        try {
            DataInputStream stdin = new DataInputStream(transformationInput.getInputPayload().getInputStream());
            int length = getLengthFromStream(stdin);
            //stdin.reset();
            byte[] buffer = getBytesFromStream(stdin);
            String str = new String(buffer, utf);
            str = str.replaceAll(utf, cyr);
            transformationOutput.getOutputPayload().getOutputStream().write(str.getBytes(cyr));
        } catch (IOException e) {
            int s = 1;
        }
    }

    private int getLengthFromStream(InputStream is) throws
            IOException {
        int i = 0;
        int length = 0;
        try {
            while ((i = is.read()) > 0) {
                length++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return length;
    }

    private byte[] getBytesFromStream(InputStream is) throws
            IOException {
        // Create the byte array to hold the data
        byte[] bytes = new byte[getLengthFromStream(is)];
        //is.reset();
        // Read in the bytes
        int offset = 0;
        int tmp = 0;
        while (offset < bytes.length && (is.read(bytes, offset, offset + (tmp = is.available()))) > 0) {
            offset += tmp;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read Inputstream ");
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }


    public void setParameter(Map map)


    {
        param = map;
        if (param == null)
            param = new HashMap();
    }
}
