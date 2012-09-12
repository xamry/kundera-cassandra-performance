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

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author amresh.singh
 * 
 */
public class KunderaBaseDao
{
    protected EntityManagerFactory emf;

    protected static final String PERSISTENCE_UNIT = "perfcassandra";

    // protected static final String HOST = "localhost";
    // protected static final String PORT = "9160";
    // protected static final String KEYSPACE = "KunderaKeyspace";
    // protected static final String COLUMN_FAMILY = "User";

    protected void startup()
    {
        // Create Schema
        // createSchema();

        // Initialize EMF
        // if (emf == null)
        // {
        try
        {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // }
        // System.out.println("EMF Created successfully" + emf);
    }

    protected void shutdown()
    {
        if (emf != null)
        {
            emf.close();
        }
    }

    /*
     * private void createSchema() { //Initialize Cassandra Client TSocket
     * socket = new TSocket(HOST, Integer.parseInt(PORT)); TTransport transport
     * = new TFramedTransport(socket); TProtocol protocol = new
     * TBinaryProtocol(transport); Cassandra.Client client = new
     * Cassandra.Client(protocol);
     * 
     * try { socket.open(); } catch (TTransportException e) {
     * e.printStackTrace(); } catch (Exception ex) { ex.printStackTrace(); }
     * 
     * //Create Schema Class<? extends AbstractReplicationStrategy> simple =
     * SimpleStrategy.class; Map<String, String> ret = new HashMap<String,
     * String>(); ret.put("replication_factor", "1");
     * 
     * CfDef users_Def = new CfDef(KEYSPACE, COLUMN_FAMILY);
     * users_Def.setComparator_type("UTF8Type");
     * users_Def.setSubcomparator_type("UTF8Type");
     * users_Def.setDefault_validation_class("UTF8Type");
     * 
     * List<CfDef> cfDefs = new ArrayList<CfDef>();
     * 
     * cfDefs.add(users_Def);
     * 
     * KsDef ksDef = new KsDef(KEYSPACE, simple.getCanonicalName(), cfDefs);
     * ksDef.setReplication_factor(1);
     * 
     * try { client.send_system_add_keyspace(ksDef); } catch (TException e) {
     * e.printStackTrace(); } }
     */
}
