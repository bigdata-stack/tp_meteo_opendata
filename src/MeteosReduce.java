import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MeteosReduce extends
		Reducer<Text, DoubleWritable, Text, DoubleWritable> 
{
	public void reduce(Text key, Iterable<DoubleWritable> values, Context sortie) throws IOException, InterruptedException 
	{
		Iterator<DoubleWritable> it = values.iterator();
		double result = 0;
		double min=0;
		double max=0;
		int currentcount=0;		
		if(key.toString().split(" ")[1].equalsIgnoreCase("moy")) {
			while (it.hasNext()) 
			{
				double val=it.next().get();				
				result=result+val;
				currentcount++;
			}
			result = result/currentcount;	

		}else if (key.toString().split(" ")[1].equalsIgnoreCase("min")) {
			min = it.next().get();	
			while (it.hasNext()) 
			{
				double val=it.next().get();				
				if(val<min) {
					min=val;
				}
			}
			result=min;
			
		}else if (key.toString().split(" ")[1].equalsIgnoreCase("max")) {
			max = it.next().get();	
			while (it.hasNext()) 
			{
				double val=it.next().get();				
				if(val>max) {
					max=val;
				}
			}
			result=max;
		}
		sortie.write(new Text(key.toString()), new DoubleWritable(result));
	}
}
 