package com.impetus.kunderaperf.dao.user;

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

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.TestCase;

import com.googlecode.jeeunit.concurrent.Concurrent;
import com.googlecode.jeeunit.concurrent.ConcurrentRunner;
import com.googlecode.jeeunit.concurrent.Repeat;
import com.impetus.kunderaperf.dto.UserDTO;
import com.impetus.kunderaperf.executor.CassandraRunner;

/**
 * @author amresh.singh
 * 
 */
//@RunWith(ConcurrentRunner.class)
// @Concurrent(threads=40000)
//@Repeat(times=40000)
public class UserDaoTest /*extends TestCase */{
	
	@Test
	public void testRun() throws IOException, InterruptedException
	{
		CassandraRunner.main(null);
	}
	/*
	UserDao userDao;
	List<UserDTO> users = new ArrayList<UserDTO>();;

	private void init() 
	{
		String clientName = System.getProperty("client");
//		System.out.println(client);
		userDao = UserDaoFactory.getUserDao("kundera");
		userDao.init();
		
//		super.setUp();
		userDao = UserDaoFactory.getUserDao("kundera");
		// hector and pelops
		userDao.init();

		users = new ArrayList<UserDTO>();
	}

//`:wq
//         @Test
  //       @Repeat(times = 1)
	public void testInsertWithOneUser()
	{
		System.out.println("<<<<<<On Single user Insert>>>>>>");
		init();
		userDao.insertUsers(prepareDataSet(1), true);
	}
	
	
	*//**
	 * Shorter transaction for Batch of objects. (Worst case scenario).
	 *//*
        //  @Test 
         public void m_testInsertWithMaxUsrs()
	{
		System.out.println("<<<<<<On Max user Insert>>>>>>");
		init();
		userDao.insertUsers(prepareDataSet(40000), true);
	}

	*//**
	 * Concurrent Users for Batch of objects. (Worst case scenario).
	 *//*
        @Test
//        @Repeat(times = 400000)
	public void testInsertWithMaxConcurUsrs() throws Exceptionthrows InterruptedException 
	{
		System.out.println("<<<<<<On Max Concurrent users Insert>>>>>>");
		long t1 = System.currentTimeMillis();
             //   init();
                int cnt=1;
		for(final UserDTO user : prepareDataSet(40000))
		{
			new Thread(new Runnable() {				
				@Override
				public void run() {
					init();
					userDao.insertUser(user);
			}
			}).start();
//                        Thread.sleep(1);
			//long t2 = System.currentTimeMillis();
		//	System.out.println("Kundera Performance: MaxinsertUsers(" + 40000 + ")>>>\t" + cnt++);
		}
                long t2 = System.currentTimeMillis();
                System.out.println("Kundera Performance: MaxinsertUsers(" + 40000 + ")>>>\t" + (t2 - t1));

	}

	public void testInsertWithMaxConcurUsrKundera()
	{thread
		prepareDataSet(40000)
	}
	
	*//**
	 * Case 0: Boundary condition (1-40000 records)
	 * Case 1: Insert batch 
	 * Case 2: Concurrent Users 
	 * Case 3: Shorter transaction for Batch of objects. (Worst case scenario).
	 * TODO: lucene performance enhancement.
	 *//*
	public void testInsertUser() 
	{
		for (int i = 0; i < 20000; i++) {
			UserDTO user = new UserDTO();
			user.setUserId("userId_" + i);
			user.setUserName("Amry_" + i);
			user.setPassword("password_" + i);
			user.setRelationshipStatus("relation_" + i);

			users.add(user);
		}

		userDao.insertUsers(users);
	}

	*//**
	 * Test method for
	 * {@link com.impetus.hectorpoc.dao.UserDao#updateUser(com.impetus.hectorpoc.dto.UserDTO)}
	 * .
	 *//*
	
	 * public void testUpdateUser() { fail("Not yet implemented"); }
	 *//**
	 * Test method for
	 * {@link com.impetus.hectorpoc.dao.UserDao#findUserById(java.lang.String)}.
	 *//*
	
	 * public void testFindUserById() { fail("Not yet implemented"); }
	 *//**
	 * Test method for
	 * {@link com.impetus.hectorpoc.dao.UserDao#findUserByUserName(java.lang.String)}
	 * .
	 *//*
	
	 * public void testFindUserByUserName() { fail("Not yet implemented"); }
	 *//**
	 * Test method for
	 * {@link com.impetus.kunderaperf.dao.user.hectorpoc.dao.UserDao#deleteUser(java.lang.String)}
	 * .
	 *//*
	
	 * public void testDeleteUser() { fail("Not yet implemented"); }
	 

	protected void tearDown() throws Exception {
//		super.tearDown();
		userDao.cleanup();
	}

	
	*//**
	 * On user persist
	 * @param rangeValue range value.
	 *//*
	private List<UserDTO> prepareDataSet(final Integer rangeValue)
	{
		for(int i=0;i<=rangeValue;i++)
		{
			UserDTO user = new UserDTO();
			user.setUserId("userId_" + i);
			user.setUserName("Amry_" + i);
			user.setPassword("password_" + i);
			user.setRelationshipStatus("relation_" + i);
			users.add(user);			
		}
		return users;
		
	}
*/}
