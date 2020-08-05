
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MeteoDateMapper extends
		Mapper<LongWritable, Text, Text, DoubleWritable> 
{
	public void map(LongWritable cle, Text valeur, Context sortie)
	throws IOException 
	{
		try 
		{	
			double somme=0;
			int currentCount = 0;
			String line = valeur.toString();
			StringTokenizer itr = new StringTokenizer(line, " \t");				
			if(itr.hasMoreElements()) {
				String keys = itr.nextToken();				
				if(cle.get()!=0) {				
					while (itr.hasMoreElements()) 
					{
						somme+=Double.parseDouble(itr.nextToken());
			            ++currentCount;	
			        }						
					if(currentCount!=0) {
						somme = somme/(double)currentCount;
						sortie.write(new Text(keys.toString()), new DoubleWritable(somme));
					}
				}
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e.getMessage());
		}
	}
}

