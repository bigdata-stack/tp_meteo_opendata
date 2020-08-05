package meteo.annomalie;
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MeteoDateReduce extends Reducer<Text, Text, Text, DoubleWritable> {
	public void reduce(Text key, Iterable<Text> values, Context sortie) throws IOException, InterruptedException 
	{
		Iterator<Text> it = values.iterator();
		double result = 0;
		double moy=0;
		double min=200;
		double max=-200;
		double currentcount=0;
		if(key.toString().split(" ")[1].equalsIgnoreCase("moy")) {
			while (it.hasNext()) 
			{
				String moyPlusTot = it.next().toString();
				moy+= Double.valueOf(moyPlusTot.split(" ")[0]);
				currentcount+= Double.valueOf(moyPlusTot.split(" ")[1]);
			}
			result = moy/currentcount;	
		}else if (key.toString().split(" ")[1].equalsIgnoreCase("min")) {
			min = Double.valueOf(it.next().toString());	
			while (it.hasNext()) 
			{
				double valeur_tmp=Double.parseDouble(it.next().toString());
				min = (min < valeur_tmp)?min:valeur_tmp;
			}			
			result=min;
		}else if (key.toString().split(" ")[1].equalsIgnoreCase("max")) {
			max = Double.valueOf(it.next().toString());	
			while (it.hasNext()) 
			{
				double valeur_tmp=Double.parseDouble(it.next().toString());
				max = (max > valeur_tmp)?max:valeur_tmp;				
			}		
			result=max;
		}		
		sortie.write(new Text(key.toString()), new DoubleWritable(result));
	}
}
 