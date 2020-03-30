package collection;

import bean.DemoBean;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
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

    List<DemoBean> list = Lists.newArrayList();

    /**
     * 一些collect方法
     */
    public void streamSummary() {

        // 1.分组
        Map<String, List<DemoBean>> collect1 = list.stream().collect(Collectors.groupingBy(DemoBean::getValue1));

        // 2.list to map (o,n)->n 存在相同key，用新值覆盖旧值,不传此方法必须保证key唯一，否则抛异常
        Map<String, String> r = list.stream().collect(Collectors.toMap(DemoBean::getValue1, DemoBean::getValue2, (o, n) -> n));

        // 3. Function.identity() 表示list中的一个元素本身
        Map<String, DemoBean> collect = list.stream().
                collect(Collectors.toMap(x -> x.getValue1() + x.getValue3(), Function.identity(), (o, n) -> n));

    }


    /**
     * 集合去重
     */
    public void distinct() {

        // 根据某个字段去除
        ArrayList<DemoBean> demoBeans = Lists.newArrayList(list.stream().collect(Collectors.toMap(DemoBean::getValue1, Function.identity(), (o, n) -> n)).values());

        // 根据某个字段（Value1）去重，保留某值（sum1）最大的哪个
        ArrayList<DemoBean> demoBeans1 = Lists.newArrayList(list.stream().collect(Collectors.toMap(DemoBean::getValue1, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(DemoBean::getSum1)))).values());

        // 自定义去重逻辑
        ArrayList<DemoBean> demoBeans2 = Lists.newArrayList(list.stream().collect(Collectors.toMap(DemoBean::getValue1, Function.identity(),
                (o, n) -> {
                    if (o.getValue1().length() > n.getValue1().length()) {
                        return o;
                    }
                    return n;
                })).values());
    }


    public void sort() {

        // 按某字段排序   reversed为倒序
        list.sort(Comparator.comparing(DemoBean::getValue1).reversed());

        // 先按sum1,再按 sum2 排序   倒序只在最后加reversed方法即可
        list.sort(Comparator.comparing(DemoBean::getSum1).thenComparing(DemoBean::getSum2));

        // 自定义排序规则
        list.sort(Comparator.comparing(x -> x.getSum1() + x.getValue1().length()));
    }
}
