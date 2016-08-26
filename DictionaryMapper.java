package Dictionary;

import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.StringTokenizer;

public class DictionaryMapper  extends Mapper <LongWritable,Text,Text,Text> {
        private static String englishWord;
		private static String partSpeech;
		private static String translationWord;
		private static String language;

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			language = ((FileSplit) context.getInputSplit()).getPath().getName();
			int languageIndex = language.toString().indexOf(".");
			language = language.substring(0, languageIndex); 
			 
			char firstChar = value.toString().charAt(0); 
			if (Character.toString(firstChar).matches("#"))
			  {
				  return;
			  }
		   int indexOfLeftP = value.toString().indexOf("[");
		   int indexOfRightP = value.toString().indexOf("]");
		   int indexOfSpace = value.toString().indexOf("\t");
		  
		   if (indexOfLeftP > -1){
			englishWord = value.toString().substring(0, indexOfSpace);
			partSpeech = value.toString().substring(indexOfLeftP + 1, indexOfRightP);
			translationWord = value.toString().substring(indexOfSpace, indexOfLeftP); 
		   }
		   else
		   {
				englishWord = value.toString().substring(0, indexOfSpace);
			   translationWord = value.toString().substring(indexOfSpace, value.toString().length()); 
			   partSpeech = "NONE"; 
		   }

			context.write(new Text(englishWord + ": [" + partSpeech + "]"), new Text(language + ":" + translationWord));
			}
}
