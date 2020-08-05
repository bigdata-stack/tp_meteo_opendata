package meteo.annomalie;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MeteoDateMapper extends Mapper<LongWritable, Text, Text, Text> {
	public void map(LongWritable cle, Text valeur, Context sortie) 	throws IOException 
	{
		try 
		{	
			double somme=0;
			int currentCount = 0;
			double min = 300;
			double max = -300;
			String line = valeur.toString();			
			StringTokenizer itr = new StringTokenizer(line, " \t");				
			if(itr.hasMoreElements()) {
				String keys = itr.nextToken();				
				if(cle.get()!=0) {				
					while (itr.hasMoreElements()) 
					{
						double valeur_tmp=Double.parseDouble(itr.nextToken());
						min = (min < valeur_tmp)?min:valeur_tmp;
						max = (max > valeur_tmp)?max:valeur_tmp;
						somme+=valeur_tmp;
			            ++currentCount;	
			        }						
					if(currentCount!=0) {
						sortie.write(new Text(keys.toString()+" Moy :"), new Text(somme + " " + currentCount));
						sortie.write(new Text(keys.toString()+" Min :"), new Text(min+" "));
						sortie.write(new Text(keys.toString()+" Max :"), new Text(max+" "));
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

