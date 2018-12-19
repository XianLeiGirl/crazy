package com.zl;
import java.util.Calendar;
import java.util.Timer;
/**
 * @title: MainDrive.java 
 * @package crazy 
 * @description: TODO
 * @author ThinkPad
 * @date: 2018年11月18日 
 * @version: V1.0  
 */
//假设泳池的水总共为100立方米
public class MainDrive
{
	public static void main(String[] args)
	{
		//Calendar提供了一个类方法getInstance，以获得此类型的一个通用的对象，getInstance方法返回一个Calendar对象
		 Calendar calendar = Calendar.getInstance();
	        calendar.set(
	                calendar.get(Calendar.YEAR),
	                calendar.get(Calendar.MONTH),
	                calendar.get(Calendar.DATE),
	                19,37,0
	        );//年月日20点
	        
	        WaterTimeTask task = new WaterTimeTask();
	        Timer timer = new Timer();
	        
	        //调度一个task，传入的第二个参数为第一次调度的时间 每天20点 ，每次调度完后，最少等待period（ms） 1天 后才开始调度。
	        timer.schedule(task,calendar.getTime(),1000*60*60*24);
	        //timer.schedule(task,1);
	    }

	}


