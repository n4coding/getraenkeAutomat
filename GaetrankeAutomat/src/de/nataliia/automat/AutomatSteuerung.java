package de.nataliia.automat;

public class AutomatSteuerung {

	private static Automat automat;

	public static void main(String[] args) {

		// Automat inisialiesiren
		automat = new Automat();
		automat.initialisiere();

		// schleife für das Menü
		while (true) {
			System.out.println("***********************************");
			System.out.println("***********************************");
			System.out.println("[ 1 ] Getränke anzeigen");
			System.out.println("[ 2 ] Getränk kaufen");
			System.out.println("[ 3 ] Getränk hinzufügen");
			System.out.println("[ 4 ] Beenden");
			System.out.println("***********************************");
			System.out.println("***********************************");
			int eingabe = (int) IOTools.readDouble("Auswahl: ");
			System.out.println("***********************************");

			if (eingabe == 1) {
				getraenkeAnzeigen();
			} else if (eingabe == 2) {
				getraenkKaufen();
			} else if (eingabe == 3) {
				getraenkHinzufuegen();
			} else if (eingabe == 4) {
				System.out.println("Einnahme des Geschäfts:  " + automat.einlesen());
				break;
			} else {
				System.out.println("Eingabe ungültig.");
			}

		}

	}

	private static void getraenkKaufen() {
		automat.gibGetraenkeAus();
		int auswahl = 0;
		
		try {
			auswahl = (int) IOTools.readDouble("Auswahl: ");

		} catch (NumberFormatException e) {
			System.out.println("Versuchen Sie es noch ein mal!");
			auswahl = (int) IOTools.readDouble("Auswahl: ");
		}

		Getraenk getraenk = automat.getraenkHolen(auswahl);

		// geld eingabe und pruefen
		double geld = 0;
		double preis = getraenk.getPreis();

		while (geld < preis) {
			System.out.println("Bitte geben Sie das Geld ein.");
			System.out.println("Preis: " + preis + " --- Geld: " + geld);

			geld += IOTools.readDouble("Geld einwerfen: ");
		}

		double restgeld = geld - preis;

		// wenn alles okay
		automat.getraenkKaufen(auswahl);
		System.out.println("Gekauft:\t" + getraenk);
		System.out.println("Restgeld:\t" + restgeld);

	}

	private static void getraenkHinzufuegen() {
		String eingabeName = IOTools.readLine("Name: ");
		double eingabePreis = IOTools.readDouble("Preis: ");
		double eingabeMenge = IOTools.readDouble("Menge: ");

		Getraenk neuesGetraenk = new Getraenk(eingabeName, eingabePreis, eingabeMenge);

		automat.getraenkHinzufuegen(neuesGetraenk);
	}

	private static void getraenkeAnzeigen() {
		automat.gibGetraenkeAus();
	}
}
