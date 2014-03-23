package de.nataliia.automat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Automat {

	private List<Getraenk> getraenke;
	private File getraenkeDatei;
	private File einnahmenDatei;

	public Automat() {
		getraenke = new ArrayList<Getraenk>();
		getraenkeDatei = new File("getraenke.txt");
		einnahmenDatei = new File("einnahme.txt");
	}

	public void initialisiere() {
		BufferedReader br;
		try {

			String daten = "";
			br = new BufferedReader(new FileReader(getraenkeDatei));

			while ((daten = br.readLine()) != null) {

				// daten -> "Wasser; 1.29"
				String[] teile = daten.split(";");
				String name = teile[0];
				double preis = Double.parseDouble(teile[1]);
				double menge = Double.parseDouble(teile[2]);

				Getraenk neuesGetraenk = new Getraenk(name, preis, menge);

				getraenke.add(neuesGetraenk);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Fehler beim lesen!");
			e.printStackTrace();
		}

	}

	public void gibGetraenkeAus() {
		int i = 0;
		for (Getraenk aktuellesGetraenk : getraenke) {
			System.out.println(++i + " - " + aktuellesGetraenk);
		}
	}

	public void getraenkHinzufuegen(Getraenk neuesGetraenk) {
		// Prüfen ob menge <= 10

		double anzahl = neuesGetraenk.getMenge();

		for (Getraenk aktuellesGetraenk : getraenke) {
			if (aktuellesGetraenk.getName().equals(neuesGetraenk.getName())) {
				anzahl += aktuellesGetraenk.getMenge();
			}
		}

		if (anzahl > 10) {
			System.out.println("Zu viele Getränke!!!");
			return;
		}

		// Neues Element addieren oder hinzufügen
		boolean gefunden = false;
		for (Getraenk aktuellesGetraenk : getraenke) {

			if (aktuellesGetraenk.getName().equals(neuesGetraenk.getName())) {
				aktuellesGetraenk.setMenge(neuesGetraenk.getMenge() + aktuellesGetraenk.getMenge());
				gefunden = true;

			}
		}
		if (!gefunden) {
			getraenke.add(neuesGetraenk);
		}

		// Komplette Liste in Datei schreiben
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(getraenkeDatei));

			int i = 0;
			for (Getraenk getraenk : getraenke) {
				if (i != 0) {
					bw.newLine();
				}
				i++;
				bw.append(getraenk.getName() + "; " + getraenk.getPreis() + "; " + getraenk.getMenge());
			}

			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Getraenk getraenkHolen(int auswahl) {
		return getraenke.get(--auswahl);
	}

	public void getraenkKaufen(int auswahl) {

		Getraenk aktuellesGetraenk = getraenke.get(--auswahl);

		try {
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(einnahmenDatei, true));
			bw1.newLine();
			bw1.append(Double.toString(aktuellesGetraenk.getPreis()));

			bw1.flush();
			bw1.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		double b = (aktuellesGetraenk.getMenge() - 1);

		if (b == 0) {
			getraenke.remove(auswahl);
		} else {
			getraenke.get(auswahl).setMenge(b);
		}

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(getraenkeDatei));

			int i = 0;
			for (Getraenk getraenk : getraenke) {
				if (i != 0) {
					bw.newLine();
				}
				i++;
				bw.append(getraenk.getName() + "; " + getraenk.getPreis() + "; " + getraenk.getMenge());
			}

			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Double einlesen() {
		BufferedReader br;

		String daten = "";
		double gesamtGeld = 0;

		try {
			br = new BufferedReader(new FileReader(einnahmenDatei));
			while ((daten = br.readLine()) != null) {

				double preis = Double.parseDouble(daten);
				gesamtGeld = gesamtGeld + preis;

			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gesamtGeld;
	}
}
