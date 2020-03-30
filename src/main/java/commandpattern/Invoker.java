package commandpattern;

import com.google.common.collect.ImmutableMap;
import commandpattern.impl.*;

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

class go {


    private final static Map<String, Query> QUERY = ImmutableMap.<String, Query>builder().
            put("Query1", new Query1(new Action())).
            put("Query2", new Query2(new Action())).build();


    private static String query(String key) {
        Invoker invoker = new Invoker();
        return invoker.go(QUERY.get(key), key);
    }

    public static void main(String[] args) {
        System.out.println(query("Query1"));
        System.out.println(query("Query2"));
    }
}