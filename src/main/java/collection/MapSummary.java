package collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * map 使用小计较总结
 *
 * @author shuzhuang.su
 * @date 2020-03-26 13:39
 */
public class MapSummary {


    /**
     * 常用的merge操作
     */
    public void mapMerge() {

        // 数字类型加和，  Integer::sum
        Map<Integer, Integer> mapInteger = Maps.newHashMap();
        for (Integer i = 0; i < 100; i++) {
            mapInteger.merge(i % 2, i, Integer::sum);
        }

        // 集合类型操作(set同理)，无需get->add->put操作
        Map<Integer, List<Integer>> mapList = Maps.newHashMap();
        for (Integer i = 0; i < 100; i++) {
            // 使用了ListSummary里提供的工具类，需自己实现
            mapList.merge(i % 2, Lists.newArrayList(i), ListSummary::listAddAll);

        }

    }

}
