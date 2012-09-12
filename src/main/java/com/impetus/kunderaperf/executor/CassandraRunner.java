/**
 * 
 */
package com.impetus.kunderaperf.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.ColumnDef;
import org.apache.cassandra.thrift.IndexType;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.KsDef;
import org.apache.cassandra.thrift.NotFoundException;
import org.apache.cassandra.thrift.SchemaDisagreementException;
import org.apache.cassandra.thrift.TBinaryProtocol;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * The Class RuntimeProcessExmp.
 * 
 * @author Kuldeep.mishra
 * @version 1.0
 */
public class CassandraRunner
{
    private static Runtime runtime = Runtime.getRuntime();
    private static String kundera_pelops_delta;
    private static String kundera_hector_delta;
    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws InterruptedException 
     */
    public static void main(String[] argss) throws IOException, InterruptedException
    {
//    	postBuild();
        int i;
//        String b[] = { "1"/*, "1000", "4000", "40000", "100000", "1000000"*/ };
//        String c[] = { "1"/*, "10", "100", "1000", "10000", "40000", "50000", "100000"*/ };
//        String cb[] = { "10"/*, "100", "1000" */};

   	  String b[] = { "1", "1000", "4000", "40000", "100000", "1000000" };
      String c[] = { "1", "10", "100", "1000", "10000", "40000", "50000", "100000" };
      String cb[] = { "10", "100", "1000" };

        String[] clients = {"kundera", "pelops","hector"};
        String runType[] = {"b","c","cb"};
        
        Map<String, String> keySpaceMapper = new HashMap<String, String>(3);
        keySpaceMapper.put("pelops", "PelopsKeyspace");
        keySpaceMapper.put("hector", "HectorKeyspace");
        keySpaceMapper.put("kundera", "KunderaKeyspace");

//        startCassandraServer("/home/impadmin/vivek/apache-cassandra-1.0.6/bin/cassandra");
        startCassandraServer("/root/software/apache-cassandra-1.0.6/bin/cassandra");
        for(String type : runType)
        {
       			for (String client : clients) {
       			
        if (type.equalsIgnoreCase("b"))
        {
            for (i = 0; i < b.length; i++)
            {
                try
                {
                    createKeysapce(keySpaceMapper.get(client));
                    KunderaPerformanceRunner.main(new String[] { new String(b[i]), client, type });
                    dropKeyspace(keySpaceMapper.get(client));
//                    stopCassandraServer();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if (type.equalsIgnoreCase("c"))
        {
            for (i = 0; i < c.length; i++)
            {

                try
                {
//                    startCassandraServer(args[2]);
                    createKeysapce(keySpaceMapper.get(client));
                    KunderaPerformanceRunner.main(new String[] { "1",  client, type , new String(c[i]) });
                    dropKeyspace(keySpaceMapper.get(client));
//                    stopCassandraServer();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if (type.equalsIgnoreCase("cb"))
        {

            for (i = 0; i < cb.length; i++)
            {

                try
                {
//                    startCassandraServer(args[2]);
                	createKeysapce(keySpaceMapper.get(client));
                    KunderaPerformanceRunner.main(new String[] { "1000",  client, type , new String(cb[i]) });
                    dropKeyspace(keySpaceMapper.get(client));
//                    stopCassandraServer();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            System.out.println("please give valid options ie b/c/cb");
        }
       			}
			}
        onGenerateDelta(KunderaPerformanceRunner.profiler);
        stopCassandraServer();
    }

    /**
     * Start mongo server.
     * 
     * @param args
     * 
     * @param runtime
     *            the runtime
     * @return the process
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws InterruptedException
     */
    private static void startCassandraServer(String args) throws IOException, InterruptedException
    {
        System.out.println("Starting casssandra server..");
        runtime.exec("rm -rf /var/lib/cassandra/*").waitFor();
        runtime.exec("mkdir /var/lib/cassandra/").waitFor();
        runtime.exec("chmod 777 -R /var/lib/cassandra/").waitFor();
        runtime.exec(args).waitFor();
       TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Stop mongo server.
     * 
     * @param runtime
     *            the runtime
     * @param br
     *            the br
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws InterruptedException
     */
    private static void stopCassandraServer() throws IOException, InterruptedException
    {
        Process process = runtime.exec("jps");
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String line = null;
        while ((line = br.readLine()) != null)
        {
            if (line.contains("CassandraDaemon"))
            {
                int idx;
                idx = line.indexOf("CassandraDaemon");

                runtime.exec("kill -9 " + line.substring(0, idx - 1));
                System.out.println("Killed process " + line.substring(0, idx - 1));
                TimeUnit.SECONDS.sleep(5);
                deleteCassandraFolders("/var/lib/cassandra/data/");
                deleteCassandraFolders("/var/lib/cassandra/data/system/");
                deleteCassandraFolders("/var/lib/cassandra/commitlog/");
                deleteCassandraFolders("/var/lib/cassandra/saved_caches/");
                deleteCassandraFolders("/var/log/cassandra/");
            }
        }
    }

    private static void deleteCassandraFolders(String dir)
    {
        System.out.println("Cleaning up folder " + dir);
        File directory = new File(dir);
        // Get all files in directory
        File[] files = directory.listFiles();
        for (File file : files)
        {
            // Delete each file
            if (!file.delete())
            {
                // Failed to delete file
                System.out.println("Failed to delete " + file);
            }
        }
    }

    private static Cassandra.Client cassandra_client;

    private static void createKeysapce(String keyspace)
    {
            if (cassandra_client == null)
            {
                initiateClient();
            }
            KsDef ksDef;
            try
            {
                ksDef = cassandra_client.describe_keyspace(keyspace);
                dropKeyspace(keyspace);
            }
            catch (NotFoundException e)
            {
                List<CfDef> cfDefs = new ArrayList<CfDef>();
                CfDef cfDef = new CfDef(keyspace, "User");
                List<ColumnDef> columnDefs = new ArrayList<ColumnDef>();
                ColumnDef name = new ColumnDef();
                name.setName("user_name".getBytes());
                name.setIndex_type(IndexType.KEYS);

                ColumnDef password = new ColumnDef();
                password.setName("password".getBytes());
                password.setIndex_type(IndexType.KEYS);

                ColumnDef relation = new ColumnDef();
                relation.setName("relation".getBytes());
                relation.setIndex_type(IndexType.KEYS);

                cfDef.column_metadata = columnDefs;

                cfDefs.add(cfDef);
                ksDef = new KsDef(keyspace, "org.apache.cassandra.locator.SimpleStrategy", cfDefs);
                ksDef.setReplication_factor(1);
                if (cassandra_client == null)
                {
                    initiateClient();
                }

                try
                {
                    cassandra_client.system_add_keyspace(ksDef);
                }
                catch (InvalidRequestException e1)
                {
                    e1.printStackTrace();
                }
                catch (SchemaDisagreementException e1)
                {
                    e1.printStackTrace();
                }
                catch (TException e1)
                {
                    e1.printStackTrace();
                }
            }

            catch (InvalidRequestException e)
            {

                e.printStackTrace();
            }
            catch (TException e)
            {

                e.printStackTrace();
            }
    }

    private static void dropKeyspace(String keyspace)
    {
   
            if (cassandra_client == null)
            {
                initiateClient();
            }
            try
            {
                cassandra_client.system_drop_keyspace(keyspace);
            }
            catch (InvalidRequestException e)
            {

                e.printStackTrace();
            }
            catch (SchemaDisagreementException e)
            {

                e.printStackTrace();
            }
            catch (TException e)
            {

                e.printStackTrace();
            }
    }

    private static void initiateClient()
    {
        TSocket socket = new TSocket("localhost", 9160);
        TTransport transport = new TFramedTransport(socket);
        TProtocol protocol = new TBinaryProtocol(transport);
        cassandra_client = new Cassandra.Client(protocol);
        try
        {
            if (!socket.isOpen())
            {
                socket.open();
            }
        }
        catch (TTransportException e)
        {
            e.printStackTrace();
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }

    private static void postBuild()
    {
        String host = "mail2.impetus.co.in";
        int port = 465;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.host", host);

        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost(host);
//        emailSender.setPort(port);
        emailSender.setUsername("vivek.mishra@impetus.co.in");
        emailSender.setPassword("Password~22");
        emailSender.setJavaMailProperties(props);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(new String[]{"vivek.mishra@impetus.co.in","amresh.singh@impetus.co.in","kuldeep.mishra@impetus.co.in","vivek.shrivastava@impetus.co.in"});
        mail.setFrom("vivek.mishra@impetus.co.in");

//        mail.se
        mail.setSubject("kundera-cassandra-performance Delta");
//        String url = "http://localhost:8080/HealthTrip/activate/activate.action?token="+model.getToken();
//        mail.setText("Thank you for registering with us, please confirm your activation by clicking link given below:"+"\n"
//                + url);
        
        mail.setText("Kundera/Pelops Performance Delta ==>" + kundera_pelops_delta + "\n" + "Kundera/Hector Performance Delta ==>" + kundera_hector_delta);
        emailSender.send(mail);
    }


    private static void onGenerateDelta(Map<String, Long> profiledData) throws FileNotFoundException, IOException
    {
//  	  String b[] = { "1", "1000", "4000", "40000", "100000", "1000000" };
//      String c[] = { "1", "10", "100", "1000", "10000", "40000", "50000", "100000" };
//      String cb[] = { "10", "100", "1000" };

//        String b[] = { "1"/*, "1000", "4000", "40000", "100000", "1000000"*/ };
//        String c[] = { "1"/*, "10", "100", "1000", "10000", "40000", "50000", "100000"*/ };
//        String cb[] = { "10"/*, "100", "1000" */};

	  String b[] = { "1", "1000", "4000", "40000", "100000", "1000000" };
      String c[] = { "1", "10", "100", "1000", "10000", "40000", "50000", "100000" };
      String cb[] = { "10", "100", "1000" };
        
    	String fileName = "performance_cassandra" + new Date() + ".xls";
       HSSFWorkbook workBook = new HSSFWorkbook();
       workBook = generateDelta(b, profiledData, workBook, "Bulk", "b");
       workBook = generateDelta(c, profiledData, workBook, "Concurrent", "c");
       workBook = generateDelta(cb, profiledData, workBook, "Concurrent-Bulk", "cb");
      
		 FileOutputStream fos = null;
	        try {
	            fos = new FileOutputStream(new File(fileName));
	            workBook.write(fos);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (fos != null) {
	                try {
	                    fos.flush();
	                    fos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	        postBuild();
    }
    private static  HSSFWorkbook generateDelta(String[] type, Map<String, Long> profiledData, HSSFWorkbook workBook, String sheetName, String keyType) throws FileNotFoundException, IOException
    {

//        String[] clients = {"pelops", "hector","kundera"};
          String[] clients = {"kundera", "pelops","hector"};

        int count = 2;
        HSSFSheet sheet = initSheet(workBook,sheetName);
        int nextRowCount = 2;
		for (String t : type) 
		{
	        int intnoOfthreads = 1;
	        String noOfRecord = t;
	        // set on no of record and threads.
	        
	        if(keyType.equals("c"))
	        {
	        	intnoOfthreads = Integer.parseInt(t);
	        	noOfRecord = 1+"";
	        } else if(keyType.equals("cb"))
	        {
	        	intnoOfthreads = Integer.parseInt(t);
	        	noOfRecord = 1000 + "";
	        }
	        
	        
	        String keySeperator = ":" + keyType; 
			
	        HSSFRow dataRow = sheet.createRow(nextRowCount);
			HSSFCell noOfThreadCell = dataRow.createCell(0);
			noOfThreadCell.setCellValue(intnoOfthreads);
			HSSFCell noOfRecordsCell = dataRow.createCell(1);
			noOfRecordsCell.setCellValue(noOfRecord);
			
			
			int clientCnt=2;
			
			// iterate for earch client.
			double[] cellValArr = new double[4];
			int cnt=0;
			for (String client : clients)
			{
				String key = client +keySeperator+ ":" + noOfRecord + ":" + intnoOfthreads;
//				System.out.println(key);
				Long timeTaken = profiledData.get(key);
//				System.out.println(timeTaken);
				HSSFCell clientCell = dataRow.createCell(clientCnt);
				clientCell.setCellValue(timeTaken);
				clientCnt++;
				cellValArr[cnt++] = timeTaken;
			}

			// populate delta.
			HSSFCell kundera_hector = dataRow.createCell(clientCnt++);
			kundera_hector_delta = ((cellValArr[0]-cellValArr[2])/cellValArr[2]) * 100 +"%";
			kundera_hector.setCellValue(((cellValArr[0]-cellValArr[2])/cellValArr[2]) * 100);
			
			HSSFCell kundera_pelops = dataRow.createCell(clientCnt);
			kundera_pelops_delta = ((cellValArr[0]-cellValArr[1])/cellValArr[1]) * 100 + "%";
			kundera_pelops.setCellValue(((cellValArr[0]-cellValArr[1])/cellValArr[1]) * 100);
			count++;
		}
		
		return workBook;
		
	}

    
    private static HSSFSheet initSheet(HSSFWorkbook workbook, String sheetName) {
		HSSFSheet sheet = workbook.createSheet(sheetName);
        
        HSSFRow row0 = sheet.createRow(0);
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue("Performance Analysis:");


        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("NoOfThreads");
        HSSFCell cell2 = row1.createCell(1);
        cell2.setCellValue("NoOfRecords");
        HSSFCell cell3 = row1.createCell(2);
        cell3.setCellValue("Kundera");
        HSSFCell cell4 = row1.createCell(3);

        cell4.setCellValue("Pelops");
        
        HSSFCell cell5 = row1.createCell(4);
        cell5.setCellValue("Hector");
        
        HSSFCell cell6 = row1.createCell(5);
        cell6.setCellValue("kundera-hector(%)");

        HSSFCell cell7 = row1.createCell(6);
        cell7.setCellValue("kundera-pelops(%)");
		return sheet;
	}
}
