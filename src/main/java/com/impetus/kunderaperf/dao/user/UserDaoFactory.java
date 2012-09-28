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

/**
 * @author amresh.singh
 * 
 */
public class UserDaoFactory
{
    public static UserDao getUserDao(String provider)
    {
        if (provider.equalsIgnoreCase("kundera"))
        {
            return new UserDaoKunderaImpl();
        }
        else if (provider.equalsIgnoreCase("pelops"))
        {
            return new UserDaoPelopsImpl();
        }
        else if (provider.equalsIgnoreCase("thrift"))
        {
            return new UserDaoThriftImpl();
        }
        else if (provider.equalsIgnoreCase("hector"))
        {
            return new UserDaoHectorImpl();
        }
        else
        {
            return null;
        }
    }
}
