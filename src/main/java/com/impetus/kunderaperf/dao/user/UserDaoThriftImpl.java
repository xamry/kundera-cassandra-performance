/**
 * Copyright 2012 Impetus Infotech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.impetus.kunderaperf.dao.user;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnOrSuperColumn;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.Mutation;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.thrift.TException;
import org.scale7.cassandra.pelops.Bytes;
import org.scale7.cassandra.pelops.Pelops;
import org.scale7.cassandra.pelops.pool.IThriftPool.IPooledConnection;

import com.impetus.client.cassandra.pelops.PelopsUtils;
import com.impetus.kunderaperf.dao.ThriftBaseDao;
import com.impetus.kunderaperf.dto.UserDTO;

/**
 * Thrift implementation of {@link UserDao}
 * @author amresh.singh
 */
public class UserDaoThriftImpl extends ThriftBaseDao implements UserDao
{

    @Override
    public void init()
    {
        startup();
    }

    @Override
    public void insertUsers(List<UserDTO> users, boolean isBulk)
    {
        for (UserDTO user : users)
        {
            insertUser(user);
        }
    }

    @Override
    public void insertUser(UserDTO user)
    {
        
        IPooledConnection conn = null;
        Cassandra.Client cassandra_client = null;
        try
        {
            Map<ByteBuffer, Map<String, List<Mutation>>> mutationMap = new HashMap<ByteBuffer, Map<String, List<Mutation>>>();
            
            List<Mutation> insertion_list = new ArrayList<Mutation>();
            long ts = System.currentTimeMillis();
            
            
            Column nameColumn = new Column();
            nameColumn.setName("user_name".getBytes());
            nameColumn.setValue(user.getUserName().getBytes());
            nameColumn.setTimestamp(ts);            
            
            Column passwordColumn = new Column();
            passwordColumn.setName("password".getBytes());
            passwordColumn.setValue(user.getPassword().getBytes());
            passwordColumn.setTimestamp(ts);           
            
            Column relStatusColumn = new Column();
            relStatusColumn.setName("relation".getBytes());
            relStatusColumn.setValue(user.getRelationshipStatus().getBytes());
            relStatusColumn.setTimestamp(ts);
            
            insertion_list.add(new Mutation().setColumn_or_supercolumn(new ColumnOrSuperColumn().setColumn(nameColumn)));
            insertion_list.add(new Mutation().setColumn_or_supercolumn(new ColumnOrSuperColumn().setColumn(passwordColumn)));
            insertion_list.add(new Mutation().setColumn_or_supercolumn(new ColumnOrSuperColumn().setColumn(relStatusColumn)));
            
            // Create Mutation Map
            Map<String, List<Mutation>> columnFamilyValues = new HashMap<String, List<Mutation>>();
            columnFamilyValues.put(COLUMN_FAMILY, insertion_list);            
            Bytes rowKey = Bytes.fromByteArray(user.getUserId().getBytes());           
            mutationMap.put(rowKey.getBytes(), columnFamilyValues);            
            
            // Write Mutation map to database
            conn = Pelops.getDbConnPool(getPoolName()).getConnection();               
            cassandra_client = conn.getAPI();
            cassandra_client.set_keyspace(KEYSPACE);

            cassandra_client.batch_mutate(mutationMap, ConsistencyLevel.ONE);

        }
        catch (InvalidRequestException e)
        {
            e.printStackTrace();
        }
        catch (TException e)
        {
            e.printStackTrace();
        }
        catch (UnavailableException e)
        {
            e.printStackTrace();
        }
        catch (TimedOutException e)
        {
            e.printStackTrace();
        }
        finally
        {
            PelopsUtils.releaseConnection(conn);
            cassandra_client = null;
        }
    }

    @Override
    public void updateUser(UserDTO userDTO)
    {
    }

    @Override
    public UserDTO findUserById(String userId)
    {
        return null;
    }

    @Override
    public void findAllUsers()
    {
    }

    @Override
    public void findUsersByIds(String[] userIds)
    {
        for (String userId : userIds)
        {
            findUserById(userId);
        }
    }

    @Override
    public void findUserByUserName(String userName)
    {
    }

    @Override
    public void deleteUser(String userId)
    {
    }

    @Override
    public void cleanup()
    {
        shutdown();
    }

}
