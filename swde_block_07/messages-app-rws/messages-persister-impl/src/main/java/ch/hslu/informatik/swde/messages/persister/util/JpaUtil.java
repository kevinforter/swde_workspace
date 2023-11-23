

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
package ch.hslu.informatik.swde.messages.persister.util;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Helper class.
 * 
 * @author jsucur
 *
 */
public class JpaUtil {

	private static String persistenceUnitName = "JpaDemosPU";

	private JpaUtil() {
		// nichts machen
	}


	private static EntityManagerFactory entityManagerFactory = null;

	static {
		try {
			/* EntityManagerFactory erzeugen */
			entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns a new entity manager instace.
	 * @return  entity manager
	 */
	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
