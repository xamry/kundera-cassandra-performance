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

//import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
//import net.sourceforge.groboutils.junit.v1.TestRunnable;

import org.junit.Test;

import com.impetus.kunderaperf.dto.UserDTO;

/**
 * 
 * @author vivek.mishra@ impetus.co.in
 *
 */
public class MultithreadedUserDaoTest
{

      @Test
      public void testDummy()
{
}
}/*    @Test
    public void testPersistenceOnThreads()
    {
        UserTestRunner[] testRunners = new UserTestRunner[4000];

        String client = System.getProperty("client");
        System.out.println("<<<<<<<"+ client + ">>>>>>>>>>>>>");

        for(int i=0 ; i<4000; i++)
        {
//            testRunners[i] = new UserTestRunner(System.getProperty("client"),i);
            testRunners[i] = new UserTestRunner(client,i);

        }
        
        MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(testRunners);
		long t1 = System.currentTimeMillis();
  
      try
        {
            System.out.println("Test on concurrent threads");
//            long t1 = System.currentTimeMillis();
            mttr.runTestRunnables();
        }
        catch (Throwable e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
//            ail();
        }
		long t2 = System.currentTimeMillis();
		System.out.println("Kundera Performance: MaxinsertUsers(" + 10000 + ")>>>\t" + (t2 - t1));


    }
*/
    /**
     * Test runner
     * @author vivek.mishra
     *
     */
  /*  private class UserTestRunner extends TestRunnable
    {
        private String clientName;
        private int value;

        private UserTestRunner(String clientName, int value)
        {
            this.clientName = clientName;
            this.value = value;
        }

        @Override
        public void runTest() throws Throwable
        {
//            System.out.println("I am called");  
          UserDao  userDao = UserDaoFactory.getUserDao(clientName);
          userDao.insertUser(prepareDataSet(value));
            
        }
*/
        /**
         * On user persist
         * 
         * @param rangeValue
         *            range value.
         */
 /*       private UserDTO prepareDataSet(final Integer rangeValue)
        {
            UserDTO user = new UserDTO();
            user.setUserId("userId_" + rangeValue);
            user.setUserName("Amry_" + rangeValue);
            user.setPassword("password_" + rangeValue);
            user.setRelationshipStatus("relation_" + rangeValue);
            return user;

        }

    }
}*/
