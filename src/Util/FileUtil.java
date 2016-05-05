package Util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {
		
	public static void PrintToTxt(String file, String conent) {     
        BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));     
            out.write(conent+"\r\n");     
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }     
    }     
}
