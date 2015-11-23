package JsExec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CasperExec {
	public  static List<String> ExecCommand(String  shellcommand,String[] env)
	{
        Process process = null;  
        List<String> processList = new ArrayList<String>();  
        try {  
            process = Runtime.getRuntime().exec(shellcommand,env);  
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));  
            String line = "";  
            while ((line = input.readLine()) != null) {  
                processList.add(line);  
            }  
            input.close(); 
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;
        }  
        
       return processList;
	}
	
}
