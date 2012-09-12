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
package com.impetus.kunderaperf.dao.user;

import java.util.List;

import javax.persistence.EntityManager;

import com.impetus.kunderaperf.dao.KunderaBaseDao;
import com.impetus.kunderaperf.dto.UserDTO;

/**
 * @author amresh.singh
 * 
 */
public class UserDaoKunderaImpl extends KunderaBaseDao implements UserDao {

	@Override
	public void init() {
		startup();
	}

	@Override
	public void insertUsers(List<UserDTO> users, boolean isBulk) {
		// System.out.println("Inserting users");
		// long t1 = System.currentTimeMillis();

		if (isBulk) {
			EntityManager em = emf.createEntityManager();

			// int counter = 0;
			for (int i = 0; i < users.size(); i++) {
				UserDTO user = users.get(i);
				if (i % 4000 == 0) {
					em.clear();
				}
				em.persist(user);
			}
			em.close();
			em = null;
		} else {
			for (int i = 0; i < users.size(); i++) {
				UserDTO user = users.get(i);
				insertUser(user);
			}
		}
		users.clear();
		users = null;
		// long t2 = System.currentTimeMillis();
		// System.out.println("Kundera Performance: insertUsers(" + users.size()
		// + ")>>>\t" + (t2 - t1));
	}

	/**
	 * Inserts user table object.
	 * 
	 * @param user
	 *            user object.
	 */
	public void insertUser(UserDTO user) {
		// init();
		// System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+emf +
		// ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		EntityManager em = emf.createEntityManager();
		em.persist(user);
		em.close();
		em = null;
	}

	@Override
	public void updateUser(UserDTO userDTO) {
	}

	@Override
	public void findUserById(String userId) {
	}

	@Override
	public void findUserByUserName(String userName) {
	}

	@Override
	public void deleteUser(String userId) {
	}

	@Override
	public void cleanup() {
		System.out.println("<<<<<<< Shututdown called>>>>>");
		shutdown();
	}
}