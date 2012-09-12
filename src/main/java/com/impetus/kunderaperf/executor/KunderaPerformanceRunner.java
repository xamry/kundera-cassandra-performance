/**
 * 
 */
package com.impetus.kunderaperf.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.impetus.kunderaperf.dao.user.UserDao;
import com.impetus.kunderaperf.dao.user.UserDaoFactory;
import com.impetus.kunderaperf.dto.UserDTO;

/**
 * @author impadmin
 * 
 */
public class KunderaPerformanceRunner
{
    UserDao userDao;

    List<UserDTO> users = new ArrayList<UserDTO>();
    public static Map<String, Long> profiler = new java.util.HashMap<String, Long>();

    private void init(String client)
    {
        if(userDao ==null)
        {
            userDao = UserDaoFactory.getUserDao(client);
        }
        userDao.init();

    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        KunderaPerformanceRunner runner = new KunderaPerformanceRunner();
        if(args[2].equalsIgnoreCase("c"))
        {
            runner.run(Integer.parseInt(args[0]),args[1], false, Integer.parseInt(args[3]));
        }else if(args[2].equalsIgnoreCase("cb"))
        {
            runner.bulkLoadOnConcurrentThreads(Integer.parseInt(args[0]),args[1],Integer.parseInt(args[3]));
            
        } else if(args[2].equalsIgnoreCase("b"))
        {
            runner.onBulkLoad(Integer.parseInt(args[0]),args[1]);
        }
//        runner.run(Integer.parseInt(args[0]),args[1]);
//        runner.close();
    }

    public void run(final int noOfRecords, String client, final boolean isBulkLoad, int noOfThreads)
    {
        try
        {
        	String type = "cb";
         init(client);
         System.out.println("<<<<<<On Max Concurrent users Insert>>>>>>");
         long t1 = System.currentTimeMillis();
         if(!isBulkLoad)
         {
        	 type="c";
             onConcurrentTest(noOfThreads);
         }
         else
         {
             onConcurrentBulkLoad(noOfThreads,noOfRecords);
         }
             
        //TODO add Task executor to control threads and close data
        long t2 = System.currentTimeMillis();
        System.out.println("Kundera Performance: MaxinsertUsers(" + noOfRecords + "), total number of records/thread(" + noOfThreads + ")>>>\t" + (t2 - t1) + " For: " + client);
        profiler.put(client+":"+type+":"+noOfRecords+":"+noOfThreads, (t2-t1));

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private void onConcurrentBulkLoad(final int num,final int noOfRecs)
    {
//        ExecutorService executor = Executors.newFixedThreadPool(num);
//        List<Future> tasks = new ArrayList<Future>();
//        Future  task = null;
        for(int i =1 ; i<=num ; i++)
        {
            ConThreadExecutor c = new ConThreadExecutor(userDao, noOfRecs, i);
            try
            {
                c.t.join();
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    private void onConcurrentTest(int num)
    {
       
        for(int i =1 ; i<=num ; i++)
        {
            ConThreadExecutor c = new ConThreadExecutor(userDao, 1, i);
            try
            {
                c.t.join();
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public void onBulkLoad(int rangeValue, String client)
    {
        init(client);
        System.out.println("<<<<<<On Max Single Insert>>>>>>");
        long t1 = System.currentTimeMillis();
        userDao.insertUsers(prepareDataSet(rangeValue), true);
        long t2 = System.currentTimeMillis();
        System.out.println("Kundera Performance: MaxinsertUsers(" + 1 + "), total number of records(" + rangeValue + ")>>>\t" + (t2 - t1) + " For: " + client);
        
        profiler.put(client+":"+"b"+":"+rangeValue+":"+"1", (t2-t1));
        close();
    }

    
    public void bulkLoadOnConcurrentThreads(int rangeValue, String client, int noOfRecs)
    {
        run(rangeValue,client,true,noOfRecs);
    }
    
    
    public void close()
    {
        userDao.cleanup();
    }
    /**
     * On user persist
     * 
     * @param rangeValue
     *            range value.
     */
    private List<UserDTO> prepareDataSet(final Integer rangeValue)
    {
        for (int i = 0; i <rangeValue; i++)
        {
            UserDTO user = new UserDTO();
            user.setUserId(getString("userId_", i));
            user.setUserName(getString("Amry_" , i));
            user.setPassword(getString("password_" , i));
            user.setRelationshipStatus(getString("relation_" , i));
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
