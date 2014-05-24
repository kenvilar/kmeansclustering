package programs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PortHash {
    private static Map portMap = new HashMap();
    private int counter;
    
    public PortHash(){
        this.counter = 5;
    }
    
    
    public Boolean hasPort(String port) {
        return portMap.containsKey(port);
    }
    
    public void putPort(String port){
        portMap.put(port, counter);
        counter = counter + 5;
    }
    
    public Object getValue(String port){
        return portMap.get(port);
    }
    
    public void printMap() throws IOException{
        //Create the new File
        File vectorFile = new File("PortMapping.csv");
        if (!vectorFile.exists())
            vectorFile.createNewFile();
        
        
        //Initialize the writer
        FileWriter fw = new FileWriter(vectorFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        
        StringBuilder sb;
        Iterator iter = portMap.entrySet().iterator();
        
        while(iter.hasNext()){
            sb = new StringBuilder();
            Map.Entry mEntry = (Map.Entry) iter.next();
            
            sb.append(mEntry.getKey()).append("----").append(mEntry.getValue()).append("\n");
            bw.write(sb.toString());
            
            
        }
        
        bw.close();
        
    }
    
}
