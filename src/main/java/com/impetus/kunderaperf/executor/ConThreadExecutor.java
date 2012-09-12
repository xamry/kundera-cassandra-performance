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
package com.impetus.kunderaperf.executor;

import java.util.ArrayList;
import java.util.List;

import com.impetus.kunderaperf.dao.user.UserDao;
import com.impetus.kunderaperf.dto.UserDTO;

/**
 * @author vivek.mishra
 * 
 */
public class ConThreadExecutor implements Runnable
{
    private UserDao userDao;

    private int noOfRecs;

    private int counter;

    public Thread t;

    public ConThreadExecutor(UserDao userDao, int noOfRecs, int counter)
    {
        this.userDao = userDao;
        this.noOfRecs = noOfRecs;
        this.counter = counter;
        t = new Thread(this);
        t.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        userDao.insertUsers(prepareDataSet(noOfRecs), false);
    }

    private List<UserDTO> prepareDataSet(final Integer rangeValue)
    {
        List<UserDTO> users = new ArrayList<UserDTO>();
        for (int i = 0; i <= rangeValue; i++)
        {
            int key = rangeValue == 1 ? counter : i;
            UserDTO user = new UserDTO();
            user.setUserId(getString("userId_", key));
            user.setUserName(getString("Amry_", key));
            user.setPassword(getString("password_", key));
            user.setRelationshipStatus(getString("relation_", key));
            users.add(user);
        }
        return users;
    }

    private String getString(String fieldName, int key)
    {
        StringBuilder strBuild = new StringBuilder(fieldName);
        strBuild.append(key);
        return strBuild.toString();
    }
}
