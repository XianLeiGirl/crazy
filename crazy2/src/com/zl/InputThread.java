package com.zl;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @title: InputThread.java 
 * @package crazy 
 * @description: 注水线程,同时运行3个
 * @author ThinkPad
 * @date: 2018年11月18日 
 * @version: V1.0  
 */
//Callable接口代表一段可以调用并返回结果的代码;
public class InputThread implements Callable<Object>
{
	//BlockingQueue很好的解决了多线程中，如何高效安全“传输”数据的问题
	private BlockingQueue<String> queue;

    public InputThread(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    //V call()函数返回的类型就是传递进来的V类型。
    public Object call() throws InterruptedException {
        while(true){
            boolean flag = true;
            for(int cnt=0;cnt<5;cnt++){
            	//将给定的元素设置到队列中，如果设置成功返回true, 否则返回false. e的值不能为空，否则抛出空指针异常。
                flag = queue.offer("一立方米");
                //插不进去跳出for
                if(!flag){
                    break;
                }
            }
            System.out.println("注水5立方米，当前水量："+queue.size());
            if(!flag){
                break;
            }
            //延时1秒
            TimeUnit.SECONDS.sleep(1);
        }
        return "success";
    }

}
