package Dictionary;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import java.io.IOException;

public class DictionaryReducer  extends Reducer <Text,Text,Text,Text> {

	private static String tempString;
	private static String[] keyString;
	
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		String french = "N/A"; 
		String german = "N/A"; 
		String italian = "N/A"; 
		String portuguese = "N/A"; 
		String spanish = "N/A"; 
		
		for (Text value: values) {
         tempString = value.toString();
         keyString = tempString.split(":"); 
        if(keyString[0].equals("French")) {
			french = keyString[1];  
         } 
		 else if(keyString[0].equals("German")) {
			german = keyString[1];  
         }
		 else if(keyString[0].equals("Italian")) {
			italian = keyString[1];  
         }
		 else if(keyString[0].equals("Portuguese")) {
			portuguese = keyString[1];  
         }
		 else if(keyString[0].equals("Spanish")) {
			spanish = keyString[1];  
         }
		}
		context.write(key, new Text("french: " + french + ", german:" + german + ", italian: " + 
			italian + ", portuguese: " + portuguese + ", spanish: " + spanish));        
	}
}

