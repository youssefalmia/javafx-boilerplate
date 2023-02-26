package org.example.utils;

import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;

import java.io.*;


/**
 *
 * @author Bilel
 */
public class UploadAPI {
    public static String UPLOAD_PATH = "http://localhost/uploads/images/";

    public static String upload(File x) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String fileName = "";
        try {
            HttpPost httppost = new HttpPost("http://localhost/uploads/savetofile.php");

            FileBody bin = new FileBody(x);

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("myFile", bin)
                    .build();

            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {                                        
                    String responseXml = EntityUtils.toString(resEntity);
                    fileName+= responseXml;
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return fileName;
    }

}
