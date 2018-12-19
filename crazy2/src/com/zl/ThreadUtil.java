package com.zl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @title: ThreadUtil.java 
 * @package crazy 
 * @description: TODO
 * @author ThinkPad
 * @date: 2018年11月18日 
 * @version: V1.0  
 */
public class ThreadUtil
{
	public static List<Object> runCheckCallable(List<Callable<Object>> l, Boolean b)
	{
		//Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
		//(l.get(0))代表什么？ 第一个List
		//FutureTask(Callable<V> callable) 将Callable写的任务封装到一个由执行者调度的FutureTask对象
		FutureTask<Object> futureTask1 = new FutureTask<Object>(l.get(0));  
		FutureTask<Object> futureTask2 = new FutureTask<Object>(l.get(1));  
		FutureTask<Object> futureTask3 = new FutureTask<Object>(l.get(1));
		FutureTask<Object> futureTask4 = new FutureTask<Object>(l.get(1));
		FutureTask<Object> futureTask5 = new FutureTask<Object>(l.get(1));

		// 创建线程池并返回ExecutorService实例  
		ExecutorService executor = Executors.newFixedThreadPool(5);      

		// 执行任务  
		executor.execute(futureTask1);  
		executor.execute(futureTask2);
		executor.execute(futureTask3);
		executor.execute(futureTask4);
		executor.execute(futureTask5);

		while (true) 
		{  
			try {  
				//怎样才叫两个任务都完成？
				if(futureTask1.isDone() && futureTask2.isDone() && futureTask3.isDone() && futureTask4.isDone() && futureTask5.isDone()){//  两个任务都完成  
					System.out.println("Done");  

					// 关闭线程池和服务   
					executor.shutdown();

					List<Object> Lobject = null;
					//
					Lobject.add(l.get(0));
					Lobject.add(l.get(1));
					Lobject.add(l.get(2));
					Lobject.add(l.get(3));
					Lobject.add(l.get(4));
					//要一直运行到两个任务都完成才停止吗？？（只有此处有返回值）
					return Lobject;

				}  
				//任务1是什么？要完成什么？？进水到队列
				if(!futureTask1.isDone()){ // 任务1没有完成，会等待，直到任务完成 
					//futureTask1.get() get到什么？
					//get()方法用来获取执行结果 success ，这个方法会产生阻塞，会一直等到任务执行完毕才返回success
					System.out.println("FutureTask1 output="+futureTask1.get());  
				}  
				if(!futureTask2.isDone()){
					System.out.println("FutureTask2 output="+futureTask2.get());  
				}  
				if(!futureTask3.isDone()){
					System.out.println("FutureTask3 output="+futureTask3.get());  
				}  
				if(!futureTask4.isDone()){
					System.out.println("Waiting for FutureTask4 to complete");  
				}  
				if(!futureTask5.isDone()){
					System.out.println("Waiting for FutureTask5 to complete");  
				}  

				
				//得到什么？
				//get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内 200毫秒 ，还没获取到结果，就直接返回null。
				String s1 = (String) futureTask4.get(200L, TimeUnit.MILLISECONDS);  
				if(s1 !=null){  
					System.out.println("FutureTask4 output="+s1);  
				}  
				String s2 = (String) futureTask5.get(200L, TimeUnit.MILLISECONDS);  
				if(s1 !=null){  
					System.out.println("FutureTask5 output="+s2);  
				}  
			} 
			catch (InterruptedException | ExecutionException e) 
			{  
				e.printStackTrace();  
			}
			catch(TimeoutException e)
			{  
				//do nothing  
			}  
		}

	}

}
