package meteo.annomalies.combiner;
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MeteoDateCombiner extends 	Reducer<Text, Text, Text, Text> 
{
	public void reduce(Text key, Iterable<Text> values, Context sortie) throws IOException, InterruptedException 
	{
		Iterator<Text> it = values.iterator();
		double somme = 0;
		double currentcount=0;
		double min=200;
		double max=-200;
		while (it.hasNext()) 
		{
			String valeurTmp = it.next().toString();
			somme+= Double.valueOf(valeurTmp.split(" ")[0]);
			currentcount+= Double.valueOf(valeurTmp.split(" ")[1]);
			min=(min<Double.valueOf(valeurTmp.split(" ")[2]))?min:Double.valueOf(valeurTmp.split(" ")[2]);
			max=(max>Double.valueOf(valeurTmp.split(" ")[3]))?max:Double.valueOf(valeurTmp.split(" ")[3]);

		}
		sortie.write(new Text("GENERALE"), new Text(somme + " " + currentcount + " " +min + " "+max));
	}
}

