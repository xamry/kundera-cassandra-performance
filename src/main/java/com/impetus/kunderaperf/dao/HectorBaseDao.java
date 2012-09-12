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

import java.util.Arrays;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.ThriftKsDef;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.ddl.ColumnFamilyDefinition;
import me.prettyprint.hector.api.ddl.ComparatorType;
import me.prettyprint.hector.api.ddl.KeyspaceDefinition;
import me.prettyprint.hector.api.factory.HFactory;

/**
 * @author amresh.singh
 *
 */
public class HectorBaseDao {
	protected static StringSerializer stringSerializer;

	protected Cluster cluster;
	protected Keyspace keyspace;

	protected static final String CLUSTER = "hectorpoccluster";
	protected static final String HOST = "localhost";
	protected static final String PORT = "9160";
	protected static final String KEYSPACE = "HectorKeyspace";
	protected static final String COLUMN_FAMILY_USER = "User";
	protected static final String COLUMN_FAMILY_PERSON = "Person";
	protected static final String SUPER_COLUMN_PERSONAL_DATA = "personal_data";
	protected static final String SUPER_COLUMN_PROFESSIONAL_DATA = "professional_data";
	

	protected static final int REPLICATION_FACTOR = 1;
	
	protected void startup() {
		if (cluster == null) {
			cluster = HFactory.getOrCreateCluster(CLUSTER, HOST + ":" + PORT);
			keyspace = HFactory.createKeyspace(KEYSPACE, cluster);
		}/*
		stringSerializer = StringSerializer.get();
		cluster = HFactory.getOrCreateCluster(CLUSTER, HOST + ":" + PORT);

		KeyspaceDefinition keyspaceDef = cluster.describeKeyspace(KEYSPACE);

		// If keyspace does not exist, the CFs don't exist either. => create
		// them.
		if (keyspaceDef == null) {
			createSchema();
		}

		keyspace = HFactory.createKeyspace(KEYSPACE, cluster);
	*/}
	
	protected void shutdown() {
//		cluster.getConnectionManager().shutdown();
	}
	
	private void createSchema() {/*

		ColumnFamilyDefinition cfDef = HFactory.createColumnFamilyDefinition(
				KEYSPACE, COLUMN_FAMILY_USER, ComparatorType.BYTESTYPE);

		KeyspaceDefinition newKeyspace = HFactory.createKeyspaceDefinition(
				KEYSPACE, ThriftKsDef.DEF_STRATEGY_CLASS, REPLICATION_FACTOR,
				Arrays.asList(cfDef));

		// Add the schema to the cluster.
		// "true" as the second param means that Hector will block until all
		// nodes see the change.
		cluster.addKeyspace(newKeyspace, true);
	*/}
}
