/*******************************************************************************
 * Copyright 2021 Jordan Sucur, HSLU Informatik, Switzerland
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package ch.hslu.swde.vereinverwaltung.persister.orm;

import jakarta.persistence.EntityManager;

import ch.hslu.swde.vereinverwaltung.domain.Person;
import ch.hslu.swde.vereinverwaltung.domain.Kontakt;
import ch.hslu.swde.vereinverwaltung.domain.Adresse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Util {
	
	private Util() {
		
	}

	public static List<Adresse> createAdresseListe() {

		List<Adresse> liste = new ArrayList<>();

		liste.add(new Adresse("Pilatusstrasse 8", 6000, "Luzern"));
		liste.add(new Adresse("Lindenstrasse 24", 6048, "Horw"));
		liste.add(new Adresse("Amlehnstrasse 18", 6010, "Kriens"));
		liste.add(new Adresse("Bahnhofstrasse 10", 8000, "Zürich"));
		liste.add(new Adresse("Poststrasse 20", 5000, "Aarau"));
		liste.add(new Adresse("Schulstrasse 30", 4000, "Basel"));
		liste.add(new Adresse("Obergasse 40", 3000, "Bern"));
		liste.add(new Adresse("Hauptstrasse 50", 9000, "St. Gallen"));
		liste.add(new Adresse("Bahnhofplatz 60", 7000, "Chur"));
		liste.add(new Adresse("Marktplatz 70", 6850, "Dornach"));

		return liste;
	}

	public static List<Person> createPersonListe() {

		List<Person> liste = new ArrayList<>();

		liste.add(new Person("Meier", "Peter", LocalDate.of(9999, 12,1),
				new Kontakt("pmeier@gmx.ch", "079 999 99 99"),
				new Adresse("Kichrgasse 2", 6010, "Kriens")
				));
		liste.add(new Person("Müller", "Anna", LocalDate.of(1990, 5, 15),
				new Kontakt("amuller@gmail.com", "078 123 45 67"),
				new Adresse("Hauptstrasse 12", 8000, "Zurich")
		));

		liste.add(new Person("Schmidt", "Markus", LocalDate.of(1985, 8, 25),
				new Kontakt("mschmidt@yahoo.com", "076 555 44 33"),
				new Adresse("Feldweg 7", 3000, "Bern")
		));

		liste.add(new Person("Schneider", "Sabine", LocalDate.of(1977, 3, 7),
				new Kontakt("sschneider@hotmail.com", "079 888 77 66"),
				new Adresse("Birkenallee 3", 4050, "Basel")
		));

		liste.add(new Person("Wagner", "Thomas", LocalDate.of(1982, 11, 10),
				new Kontakt("twagner@outlook.com", "079 111 22 33"),
				new Adresse("Lindenstrasse 5", 7000, "Chur")
		));

		liste.add(new Person("Fischer", "Melanie", LocalDate.of(1995, 6, 20),
				new Kontakt("mfischer@web.de", "076 999 88 77"),
				new Adresse("Eschenweg 9", 8500, "Winterthur")
		));

		return liste;
	}

	public static void cleanDatabase() {

		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();

		em.createQuery("DELETE FROM Person e").executeUpdate();
		em.createQuery("DELETE FROM Adresse e").executeUpdate();
		em.createQuery("DELETE FROM Kontakt e").executeUpdate();

		em.getTransaction().commit();

		em.close();

	}
}
