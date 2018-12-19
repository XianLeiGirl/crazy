package com.zl;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @title: WaterTimeTask.java 
 * @package crazy 
 * @description: 定期放水类
 * @author ThinkPad
 * @date: 2018年11月18日 
 * @version: V1.0  
 */
public class WaterTimeTask extends TimerTask
{

	@Override
    public void run() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
        //共享100还是分别100？？
        InputThread thread1 = new InputThread(queue);
        InputThread thread2 = new InputThread(queue);
        InputThread thread3 = new InputThread(queue);
        OutputThread thread4 = new OutputThread(queue);
        OutputThread thread5 = new OutputThread(queue);
        //Callable写的任务,有返回值
        List<Callable<Object>> threadList = new ArrayList<>(5);
        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
        threadList.add(thread4);
        threadList.add(thread5);
        List<Object> returnValue = null;
        //return Lobject后执行
        returnValue = ThreadUtil.runCheckCallable(threadList,true);
        
        //结束语总是输不出来？？
        System.out.println("泳池已经注满");
        System.out.println(returnValue.get(0));
        System.out.println(returnValue.get(1));
        System.out.println(returnValue.get(2));
        System.out.println(returnValue.get(3));
        System.out.println(returnValue.get(4));
    }
	


	
	
	/*public void run() {
		Object lock = new Object();
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10000);
        InputThread thread1 = new InputThread(queue);
        OutputThread thread2 = new OutputThread(queue);
        try {
        synchronized (lock) {
        	thread1.call();
        	thread2.call();
           }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("泳池已经注满");
	}*/
}
