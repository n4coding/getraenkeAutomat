package de.nataliia.automat;

public class Getraenk {
	
	private String name;
	
	private double preis;
	private double menge;
	
	public Getraenk(String aktName, double aktPreis,double aktMenge ){
		name = aktName;
		preis = aktPreis;
		menge = aktMenge;
	}
	
	

	public void setMenge(double menge) {
		this.menge = menge;
	}



	public String getName() {
		return name;
	}

	public double getPreis() {
		return preis;
	}
	
	public double getMenge(){
		return menge;
	}
	@Override
	public String toString() {
		return name + ", " + preis + ", "+ menge;
	}

	
}
