import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MeteosMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
	public void map(LongWritable cle, Text valeur, Context sortie) throws IOException {
		try 
		{	
			double somme=0;
			int currentCount = 0;
			double max=-300;
			double min=300;
			String line = valeur.toString();
			StringTokenizer itr = new StringTokenizer(line, "\t");	
			
			if(itr.hasMoreElements()) {
				String keys = itr.nextToken();				
				if(cle.get()!=0) {				
					while (itr.hasMoreElements()) 
					{
						double mot = Double.parseDouble(itr.nextToken());
						if(mot>max) {
							max=mot;
						}else if (min>mot) {
							min=mot;
						}
						somme+=mot;
			            ++currentCount;	
			        }						
					if(currentCount!=0) {
						sortie.write(new Text(keys+" Min :"), new DoubleWritable(min));
						sortie.write(new Text(keys+" Moy :"), new DoubleWritable(somme/currentCount));
						sortie.write(new Text(keys+" Max :"), new DoubleWritable(max));
					}
				}
			}
		} 
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}

