import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class MeteoDate 
{

  public static void main(String[] args) throws Exception 
  {
    Job job = new Job();
    
    job.setJobName("Calcul Temperature Moyenne Par Date");
    
    job.setJarByClass(MeteoDate.class);
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    job.setMapperClass(meteo.annomalie.MeteoDateMapper.class);        
    job.setReducerClass(meteo.annomalie.MeteoDateReduce.class);
    //job.setCombinerClass(meteo.annomalies.combiner.MeteoDateCombiner.class);

    FileInputFormat.setInputPaths(job, args[0]);
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    FileSystem fs = FileSystem.get(job.getConfiguration());
    fs.delete(new Path(args[1]));
				
    job.waitForCompletion(true);
  }
}
