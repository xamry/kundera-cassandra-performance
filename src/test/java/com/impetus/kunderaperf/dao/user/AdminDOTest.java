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


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.impetus.kunderaperf.dto.AdminDO;

/**
 * @author vivek.mishra
 *
 */
public class AdminDOTest
{
	 @Test
     public void testDummy()
{
}
	/*

    private EntityManagerFactory emf;
    *//**
     * @throws java.lang.Exception
     *//*
    @Before
    public void setUp() throws Exception
    {
        emf = Persistence.createEntityManagerFactory("twingo");
    }

    *//**
     * @throws java.lang.Exception
     *//*
    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testAll()
    {
        EntityManager em = emf.createEntityManager();
        
        List<AdminDO> objCol = new ArrayList<AdminDO>();
        AdminDO obj = new AdminDO();
        obj.setAccess("access");
        obj.setAdminType(1);
        obj.setEmailId("vivek.mishra@impetus.co.in");
        obj.setId("1");
        obj.setUserName("vivek");
        obj.setPassword("pwd");
        objCol.add(obj);
        AdminDO obj1 = new AdminDO();
        obj1.setAccess("access1");
        obj1.setAdminType(2);
        obj1.setEmailId("vivek.mishra1@impetus.co.in");
        obj1.setId("2");
        obj1.setUserName("vivek1");
        obj1.setPassword("pwd1");
        objCol.add(obj1);
        for(AdminDO o : objCol)
        {
            em.persist(o);
        }
        
        Query query = em.createQuery("Select c from AdminDO c");
        List<AdminDO> results = query.getResultList();
        Assert.assertEquals(2, results.size());
//        Assert.assertEquals("access1", actual)

    }
*/}
