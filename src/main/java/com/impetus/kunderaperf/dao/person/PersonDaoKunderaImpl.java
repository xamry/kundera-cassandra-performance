/*******************************************************************************
 * * Copyright 2011 Impetus Infotech.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 ******************************************************************************/
package com.impetus.kunderaperf.dao.person;

import java.util.List;

import javax.persistence.EntityManager;

import com.impetus.kunderaperf.dao.KunderaBaseDao;
import com.impetus.kunderaperf.dto.PersonDTO;

/**
 * @author amresh.singh
 *
 */
public class PersonDaoKunderaImpl extends KunderaBaseDao implements PersonDao{

	@Override
	public void init() {
		startup();
	}

	@Override
	public void insertPersons(List<PersonDTO> persons) {
		System.out.println("Inserting persons");
		long t1 = System.currentTimeMillis();
			
		for (int i = 0; i < persons.size(); i++) { 
			PersonDTO person = persons.get(i);
			insertPerson(person);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("Kundera Performance: insertUsers(" + persons.size() + ")>>>\t" + (t2 - t1));
		
	}

	@Override
	public void cleanup() {
		shutdown();
	}
	
	private void insertPerson(PersonDTO person) {
		EntityManager em = emf.createEntityManager();	
		em.persist(person);
		em.close();
	}
	
	

}
