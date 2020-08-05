package meteo.services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class ServicesMapper {
	
	private ArrayList<Double> value=new ArrayList<Double>();
	private String keys;
	private double somme;
	private double min;
	private double max;
	private double moyenne;
	
	
	public ServicesMapper(String new_list) {
		String line = new_list.toString();
		StringTokenizer itr = new StringTokenizer(line, "\t");	
		if(itr.hasMoreElements()) {
			keys = itr.nextToken();				
			while (itr.hasMoreElements()) 
			{
				this.value.add(Double.parseDouble(itr.nextToken()));	
	        }	
		}
		this.value.removeAll(null);
		Collections.sort(this.value);
		this.min=this.value.get(0);
		this.max=this.value.get(this.value.size()-1);
	}
	public ArrayList<Double> getValue() {
		return value;
	}
	public void setValue(ArrayList<Double> value) {
		this.value = value;
	}
	
	public double getSomme() {
		return somme;
	}
	public void setSomme(double somme) {
		this.somme = somme;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}
	
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public void calculSomme() {
		for (Iterator<Double> iterator = value.iterator(); iterator.hasNext();) {
			this.somme+=(Double) iterator.next();
		}
	}
	public void calculMoyen() {
		if(!this.value.isEmpty()) {
			this.moyenne= (Double)this.somme/this.value.size();
		}
	}
		
}
