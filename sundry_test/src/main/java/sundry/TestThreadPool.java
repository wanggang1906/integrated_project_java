package sundry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池测试
 *
 * 1、线程池： 提供一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁的额外开销，提高了响应的速度。
 *
 * 2、线程池的体系结构：
 * java.util.concurrent.Executor 负责线程的使用和调度的根接口
 * 		|--ExecutorService 子接口： 线程池的主要接口
 * 				|--ThreadPoolExecutor 线程池的实现类
 * 				|--ScheduledExceutorService 子接口： 负责线程的调度
 * 					|--ScheduledThreadPoolExecutor : 继承ThreadPoolExecutor，实现了ScheduledExecutorService
 *
 *
 * 3、工具类 ： Executors
 * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * ExecutorService newSingleThreadExecutor() : 创建单个线程池。 线程池中只有一个线程
 *
 * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务
 * */

public class TestThreadPool {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //1. 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Future<Integer>> list=new ArrayList<Future<Integer>>();

        for (int i = 0; i < 10; i++) {
            Future<Integer>  future=pool.submit(new Callable<Integer>() {
                // public interface Future<V>
                // 表示异步计算的结果，提供检查计算是否完成的方法，以等待计算完成，并获取，检查计算结果
                // 计算完成后只能用get获取结果，若有必要，可以阻塞此方法
                public Integer call() throws Exception {
                    int sum=0;
                    for (int i = 0; i <=100; i++) {
                        System.out.println(sum);
                        sum+=i;
                    }
                    return sum;
                }
            });
            list.add(future);
        }

        pool.shutdown();

        for (Future<Integer> future : list) {
            System.out.println(future.get());
        }

//		Future<Integer>  future=pool.submit(new Callable<Integer>() {
//			public Integer call() throws Exception {
//				int sum=0;
//				for (int i = 0; i <=100; i++) {
//					sum+=i;
//				}
//				return sum;
//			}
//		});
//		System.out.println(future.get());
//		pool.shutdown();


//		ThreadPoolDemo threadPoolDemo=new ThreadPoolDemo();
//
//		//2. 为线程池中的线程分配任务
//		for (int i = 0; i < 10; i++) {
//			pool.submit(threadPoolDemo);
//		}
//
//		//3. 关闭线程池
//		pool.shutdown();
//

//		new Thread(threadPoolDemo).start();
//		new Thread(threadPoolDemo).start();

    }
}

class ThreadPoolDemo implements Runnable{

    private int i=0;

    public void run() {
        while(i<=100){
            System.out.println(Thread.currentThread().getName()+" "+ i++);
        }
    }
}

