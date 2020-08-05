  package meteo.annomalie.moyenneG;
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
			String line = valeur.toString();			
			StringTokenizer itr = new StringTokenizer(line, " \t");				
			if(itr.hasMoreElements()) {
				itr.nextToken();				
				if(cle.get()!=0) {				
					while (itr.hasMoreElements()) 
					{
						double valeur_tmp= Double.parseDouble(itr.nextToken());
						somme+=valeur_tmp;
			            ++currentCount;	
			        }						
					if(currentCount!=0) {
						sortie.write(new Text("MOYGENERALE"), new Text(somme + " " + currentCount));
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

