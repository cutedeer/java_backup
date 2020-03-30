package commandpattern;

import com.google.common.collect.ImmutableMap;
import commandpattern.impl.GuWen;
import commandpattern.impl.ZhuanJia;

import java.util.Map;

/**
 * go
 *
 * @author: shuzhuang.su
 * @create: 2019-10-12 20:52
 **/
public class Invoker {

    public String go(Query query, String key) {
        return query.execute(key);
    }
}

class g {


    private final static Map<String, Query> QUERY = ImmutableMap.<String, Query>builder().
            put("zhuanjia", new ZhuanJia(new Action())).
            put("guwen", new GuWen(new Action())).build();


    private static String query(String key) {
        Invoker invoker = new Invoker();
        return invoker.go(QUERY.get(key), key);
    }

    public static void main(String[] args) {
        System.out.println(query("zhuanjia"));
        System.out.println(query("guwen"));
    }
}