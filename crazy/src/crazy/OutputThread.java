package crazy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @title: OutputThread.java 
 * @package crazy 
 * @description: 放水线程
 * @author ThinkPad
 * @date: 2018年11月18日 
 * @version: V1.0  
 */
public class OutputThread implements Callable<Object>
{
	private BlockingQueue<String> queue;

    public OutputThread(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public Object call() throws InterruptedException {
        while(true){
        	//放入队列的字符串（总字符串还是“注水5立方米，当前水量：”）的长度不超过10000
            if(queue.size()==100){
                break;
            }
            for(int cnt=0;cnt<3;cnt++){
            	//在给定的时间：10秒 里，从队列中获取值，时间到了直接调用普通的poll方法，为null则直接返回null。
                queue.poll(10,TimeUnit.SECONDS);
            }
            System.out.println("放水3立方米，当前水量："+queue.size());
            TimeUnit.SECONDS.sleep(1);
        }
        return "success";
    }

}
