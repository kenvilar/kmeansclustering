package programs;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

 
public class IpHash {
    
    private static Map ipMap = new HashMap();
    private int counter;
    File database = new File("/home/hduser/Documents/mahouts/mahout-distribution-0.8/core/src/main/java/programs/GeoLite2-Country.mmdb");
    DatabaseReader reader;
    CountryResponse response;
    
    public IpHash() throws IOException{
        this.reader = new DatabaseReader.Builder(database).build();
        this.counter = 5;
    }
    
    public String getLocation(String ip) throws IOException, GeoIp2Exception{
        
        try{
        response = reader.country(InetAddress.getByName(ip));
        return response.getCountry().getName().toString();
        }
        
        catch(GeoIp2Exception e){
            return null;
        }
    }
    
    public Boolean hasLocation(String location){
        return ipMap.containsKey(location);
    }
 
    public void putLocation(String location){
        ipMap.put(location, counter);
        counter = counter + 5;
    }
    
    public Object getValue(String location){
        return ipMap.get(location);
    }
    
    
    public void printMap() throws IOException, GeoIp2Exception{
       //Create the new File
        File vectorFile = new File("IPMapping.csv");
        if (!vectorFile.exists())
            vectorFile.createNewFile();
        
        
        //Initialize the writer
        FileWriter fw = new FileWriter(vectorFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        
        StringBuilder sb;
        Iterator iter = ipMap.entrySet().iterator();
        
        while(iter.hasNext()){
            sb = new StringBuilder();
            Map.Entry mEntry = (Map.Entry) iter.next();
            
            sb.append(mEntry.getKey()).append("----").append(mEntry.getValue()).append("\n");
            bw.write(sb.toString());
            
            
        }
        
        bw.close();
        
    }
        
    }
 
  
