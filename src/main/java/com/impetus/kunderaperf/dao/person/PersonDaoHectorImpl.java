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

import java.util.Arrays;
import java.util.List;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.MutationResult;
import me.prettyprint.hector.api.mutation.Mutator;

import com.impetus.kunderaperf.dao.HectorBaseDao;
import com.impetus.kunderaperf.dto.PersonDTO;

/**
 * @author amresh.singh
 *
 */
public class PersonDaoHectorImpl extends HectorBaseDao implements PersonDao {

	@Override
	public void init() {
		startup();
	}

	@Override
	public void insertPersons(List<PersonDTO> persons) {
		long t1 = System.currentTimeMillis();
		// Add rows
		for (int i = 0; i < persons.size(); i++) {
			PersonDTO person = persons.get(i);
			
			insertPerson(person);
		}
		
		long t2 = System.currentTimeMillis();		
		System.out.println("Hector Performance: insertUsers(" + persons.size()
				+ ")>>>\t" + (t2 - t1));
	}

	@Override
	public void cleanup() {
		shutdown();		
	}
	
	private void insertPerson(PersonDTO person) {
		Mutator<String> mutator = HFactory.createMutator(keyspace,
				StringSerializer.get());
		
		mutator.addInsertion(
				person.getPersonId(),
				COLUMN_FAMILY_PERSON,
				HFactory.createSuperColumn(SUPER_COLUMN_PERSONAL_DATA,
						Arrays.asList(
								HFactory.createStringColumn("login_id", person.getPersonalData().getLoginId()),
								HFactory.createStringColumn("first_name", person.getPersonalData().getFirstName()),
								HFactory.createStringColumn("last_name", person.getPersonalData().getLastName()),
								HFactory.createStringColumn("country", person.getPersonalData().getCountry())						
						),
						stringSerializer, stringSerializer, stringSerializer))
				.addInsertion(
						person.getPersonId(),
						COLUMN_FAMILY_PERSON,
						HFactory.createSuperColumn(SUPER_COLUMN_PROFESSIONAL_DATA,
								Arrays.asList(
										HFactory.createStringColumn("degree", person.getProfessionalData().getDegree()),
										HFactory.createStringColumn("experience", person.getProfessionalData().getExperience()),
										HFactory.createStringColumn("company", person.getProfessionalData().getCompany()),
										HFactory.createStringColumn("primary_skill", person.getProfessionalData().getPrimarySkill())						
								),
								stringSerializer, stringSerializer, stringSerializer)
				);
		MutationResult me = mutator.execute();		
	}
}
