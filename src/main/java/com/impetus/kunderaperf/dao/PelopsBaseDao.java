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
package com.impetus.kunderaperf.dao;

import org.scale7.cassandra.pelops.Cluster;
import org.scale7.cassandra.pelops.IConnection;
import org.scale7.cassandra.pelops.Pelops;

/**
 * @author amresh.singh
 * 
 */
public class PelopsBaseDao
{
    protected static final String HOST = "localhost";

    protected static final String PORT = "9160";

    protected static final String KEYSPACE = "PelopsKeyspace";

    protected static final String COLUMN_FAMILY = "User";

    public void startup()
    {
        if (Pelops.getDbConnPool(getPoolName()) == null)
        {
            String[] contactNodes = new String[] { HOST };
            Cluster cluster = new Cluster(contactNodes, new IConnection.Config(Integer.parseInt(PORT), true, -1), false);
            Pelops.addPool(getPoolName(), cluster, KEYSPACE);
        }
    }

    public void shutdown()
    {
        // Pelops.shutdown();

    }

    protected String getPoolName()
    {
        return HOST + ":" + PORT + ":" + KEYSPACE;
    }

}
