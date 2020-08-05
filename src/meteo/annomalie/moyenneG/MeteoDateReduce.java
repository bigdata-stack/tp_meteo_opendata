package meteo.annomalie.moyenneG;
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MeteoDateReduce extends Reducer<Text, Text, Text, DoubleWritable> {
	public void reduce(Text key, Iterable<Text> values, Context sortie) throws IOException, InterruptedException 
	{
		Iterator<Text> it = values.iterator();
		double moy = 0;
		double currentcount=0;
		while (it.hasNext()) 
		{
			String valPlusTot = it.next().toString();
			moy+= Double.valueOf(valPlusTot.split(" ")[0]);
			currentcount+= Double.valueOf(valPlusTot.split(" ")[1]);
		}
		sortie.write(new Text("Moyenne globale "), new DoubleWritable(moy/currentcount));
	}
}
