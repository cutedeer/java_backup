package commandpattern.impl;

import commandpattern.Action;
import commandpattern.Query;

/**
 * guwen
 *
 * @author: shuzhuang.su
 * @create: 2019-10-12 20:55
 **/
public class GuWen implements Query {

    private Action a;

    public GuWen(Action a) {
        this.a = a;
    }
    @Override
    public String execute(String key) {
       return a.doQuery1(key);
    }
}
