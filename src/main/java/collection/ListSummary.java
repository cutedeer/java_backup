package collection;

import bean.DemoBean;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * list使用小技巧总结
 *
 * @author shuzhuang.su
 * @date 2020-03-26 13:38
 */
public class ListSummary {

    public static <T> List<T> listAddAll(List<T> v1, List<T> v2) {
        // addAll操作v2不允许为空
        // 结合自身，是否需要判空，避免空指针
        v1.addAll(v2);
        return v1;
    }


    /**
     * 一些collect方法
     */
    public void  streamSummary(){
        List<DemoBean> list = Lists.newArrayList();

        // 1.分组
        Map<String, List<DemoBean>> collect1 = list.stream().collect(Collectors.groupingBy(DemoBean::getValue1));

        // 2.list to map (o,n)->n 存在相同key，用新值覆盖旧值,不传此方法必须保证key唯一，否则抛异常
        Map<String,String> r = list.stream().collect(Collectors.toMap(DemoBean::getValue1,DemoBean::getValue2,(o, n)->n));

        // 3. Function.identity() 表示list中的一个元素本身
        Map<String, DemoBean> collect = list.stream().
                collect(Collectors.toMap(x -> x.getValue1() + x.getValue3(), Function.identity(), (o, n) -> n));

    }



//    public
}
