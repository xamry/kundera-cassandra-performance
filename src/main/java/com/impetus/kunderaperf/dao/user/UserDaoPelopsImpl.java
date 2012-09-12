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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.scale7.cassandra.pelops.Mutator;
import org.scale7.cassandra.pelops.Pelops;
import org.scale7.cassandra.pelops.exceptions.PelopsException;

import com.impetus.kunderaperf.dao.PelopsBaseDao;
import com.impetus.kunderaperf.dto.UserDTO;

/**
 * @author amresh.singh
 *
 */
public class UserDaoPelopsImpl extends PelopsBaseDao implements UserDao {
	
	

	@Override
	public void init() {
		startup();
        
	}

	@Override
	public void insertUsers(List<UserDTO> users, boolean isBulk) {
		long t1 = System.currentTimeMillis();		
		
	
		for(UserDTO user : users) {
			insertUser(user);
		}	
		
		
//		long t2 = System.currentTimeMillis();
//		System.out.println("Pelops Performance: insertUsers(" + users.size()
//				+ ")>>>\t" + (t2 - t1));
	}
	
	public void insertUser(UserDTO user) {
		try {
			Mutator mutator = Pelops.createMutator(getPoolName());
			List<Column> columns = new ArrayList<Column>();
			
			long currentTime = System.currentTimeMillis();
			
			Column nameColumn = new Column();
			nameColumn.setName("user_name".getBytes("utf-8"));
			nameColumn.setValue(user.getUserName().getBytes("utf-8"));
			nameColumn.setTimestamp(currentTime);
			columns.add(nameColumn);
			
			Column passwordColumn = new Column();
			passwordColumn.setName("password".getBytes("utf-8"));
			passwordColumn.setValue(user.getPassword().getBytes("utf-8"));
			passwordColumn.setTimestamp(currentTime);
			columns.add(passwordColumn);
			
			Column relationColumn = new Column();
			relationColumn.setName("relation".getBytes("utf-8"));
			relationColumn.setValue(user.getRelationshipStatus().getBytes("utf-8"));
			relationColumn.setTimestamp(currentTime);
			columns.add(relationColumn);				
			
			
			mutator.writeColumns(COLUMN_FAMILY, user.getUserId() , columns);
			mutator.execute(ConsistencyLevel.ONE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (PelopsException e) {
			e.printStackTrace();
		}
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
		shutdown();
	}
	
}
