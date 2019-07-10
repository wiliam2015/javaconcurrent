package me.wiliam.threebughead;

/**
 * 项目名称：javaconcurrent<br>
 * *********************************<br>
 * <P>类名称：TestLong</P>
 * *********************************<br>
 * <P>类描述：并发java缓存性导致的并发问题测试</P>
 *
 * @version 1.0<br>
 * @author：huhailong1<br>
 * @date：2019/07/01 <br>
 * 修改备注：<br>
 */
public class TestLong {
    private  long count = 0;
    private void add10k(){
        int idx = 0;
        while(idx++ < 10000){
            count +=1;
        }
    }

    public static long calc(){
        final TestLong test = new TestLong();
        Thread th1 = new Thread(() ->{
           test.add10k();
        });
        Thread th2 = new Thread(() ->{
            test.add10k();
        });
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return test.count;
    }

    public static void main(String[] args) {
        System.out.println(calc());
    }
}