package me.wiliam.deadlock;

/**
 * 项目名称：javaconcurrent<br>
 * *********************************<br>
 * <P>类名称：DeadLockDemo</P>
 * *********************************<br>
 * <P>类描述：死锁示例</P>
 *
 * @version 1.0<br>
 * @author：huhailong1<br>
 * @date：2019/07/03 <br>
 * 修改备注：<br>
 */
public class DeadLockDemo {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void lockObject1() throws InterruptedException {
        synchronized (lock1){
            System.out.println("lock1");
            Thread.sleep(3000);//获取lock1后先等一会儿，让Lock2有足够的时间锁住lock2
            synchronized (lock2){
                System.out.println("lock object 1");
            }
        }
    }
    public void lockObject2() throws InterruptedException {
        synchronized (lock2){
            System.out.println("lock2");
            Thread.sleep(3000);//获取lock2后先等一会儿，让Lock1有足够的时间锁住lock1
            synchronized (lock1){
                System.out.println("lock object 2");
            }
        }
    }

    public static void deadLockRun(){
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        Thread th1 = new Thread(()->{
            try {
                deadLockDemo.lockObject1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread th2 = new Thread(()->{
            try {
                deadLockDemo.lockObject2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        deadLockRun();
    }
}