
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MeteoDateReduce extends
		Reducer<Text, DoubleWritable, Text, DoubleWritable> 
{
	public void reduce(Text key, Iterable<DoubleWritable> values, Context sortie) throws IOException, InterruptedException 
	{
		Iterator<DoubleWritable> it = values.iterator();
		double moy = 0;
		int currentcount=0;		
		while (it.hasNext()) 
		{
			moy+=it.next().get();
			currentcount++;
		}
		moy = moy/(double)currentcount;
		sortie.write(new Text(key.toString()), new DoubleWritable(moy));
	}
}
 