package thread;

import bean.DemoBean;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.concurrent.*;

/**
 * 常用的多线程
 * <p>
 * 除了最基础两种实现
 *
 * @author shuzhuang.su
 * @date 2020-03-26 22:24
 */
public class ThreadPool {

    private final static Logger logger = LoggerFactory.getLogger(ThreadPool.class);

    /**
     * 并行流
     */
    public void pool1() {

        /**
         * 1.ArrayList并不是线程安全的，这样使用就会出现线程安全问题，所以使用parallerStream必须确保线程安全问题。
         *
         * 2.并行流运行时，内部使用的fork-join线程池是整个JVM进程全局唯一的线程池。而这个线程池其默认线程数为处理器核心数。
         *
         * 3.由于所有使用并行流parallerStream的地方都是使用同一个Fork-Join线程池，而线程池线程数仅为cpu的核心数
         * 虽然对你当前的业务逻辑来说，算是调优了，但对于项目中其它地方只是用来做非耗时的并行流运算，性能就差了。
         *
         */
        Lists.newArrayList("1", "2").
                parallelStream().forEach(System.out::println);

    }


    /**
     * 线程池
     */
    public void pool2() {

        /**
         * 开启一个核心线程为5的线程池
         */
        ExecutorService execService = Executors.newFixedThreadPool(5);

        try {

            // 创建一个返回值为Boolean值的任务集合
            List<Callable<Boolean>> tasks = Lists.newArrayList();
            // 将任务放至集合中
            for (int i = 0; i < 100; i++) {
                int finalI = i;
                tasks.add(() -> pool2Tasks(finalI));
            }

            // 开始执行任务
            List<Future<Boolean>> futures = execService.invokeAll(tasks);

            // 处理结果
            for (Future<Boolean> future : futures) {
                System.out.println(future.get());
            }

        } catch (Exception ex) {
            logger.error("addSimilarityDegreeDiseaseTask error", ex);
        } finally {
            // 关闭线程池
            // 线程池不会立即关闭，只是不在接受新的任务，等所有任务结束后参会真的关闭
            execService.shutdown();

            // 立即关闭线程池
            execService.shutdownNow();
        }
    }

    // 具体任务逻辑
    private boolean pool2Tasks(int i) {

        // do something
        System.out.println(i);
        return true;
    }


    /**
     * CompletableFuture  异步执行程序
     * <p>
     * <p>
     * 此方法适用于程序中 多个串行的逻辑互不影响可以单独运行，切执行时间较长。
     * 遇到这种情况，即可将多个串行的程序利用CompletableFuture异步去执行，等并行的执行完再将结果装配在一起即可
     * <p>
     * 如果每个操作都很简单的话（比如：按照id去查库）没有必要用这种多线程异步的方式，因为创建线程还需要时间，还不如直接同步执行来得快。
     * <p>
     * 事实证明，只有当每个操作很复杂需要花费相对很长的时间（比如，调用多个其它的系统的接口；比如，商品详情页面这种需要从多个系统中查数据显示的）的时候用CompletableFuture才合适，不然区别真的不大，还不如顺序同步执行
     */
    public void pool3() {

        // 假设获取value1和value2互不影响，且较为耗时，将两部分开来操作


        // 流程1
        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> {
            // 拿到所有value1
            return getValue1(100);
        }).thenAccept(x -> {
            // 处理value1
            x.forEach(y -> y.setSum1(Integer.parseInt(y.getValue1())));
        });

        // 流程2
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            // 拿到所有value2
            return getValue2(100);
            //消费
        }).thenAccept(x ->
                // 处理value2
                x.forEach(y -> y.setSum2(Integer.parseInt(y.getValue2())))
        );


        // 同步返回结果
        CompletableFuture<Void> allJoiner = CompletableFuture.allOf(future1, future2)
                .thenAccept(aVoid -> logger.info("all finish fetch"));

        // 最后 value1和value2都正常拿到
        allJoiner.join();
    }

    private List<DemoBean> getValue1(int time) {
        List<DemoBean> list = Lists.newArrayList();
        try {
            for (int i = 1; i < time; i++) {
                Thread.sleep(100);
                DemoBean demoBean = new DemoBean();
                demoBean.setValue1(i + "");
                list.add(demoBean);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<DemoBean> getValue2(int time) {
        List<DemoBean> list = Lists.newArrayList();
        try {
            for (int i = 1; i < 100; i++) {
                Thread.sleep(100);
                DemoBean demoBean = new DemoBean();
                demoBean.setValue2(i + "");
                list.add(demoBean);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }


}
