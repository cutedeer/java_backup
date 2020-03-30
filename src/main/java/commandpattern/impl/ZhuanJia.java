package commandpattern.impl;

import commandpattern.Action;
import commandpattern.Query;

/**
 * 专家
 *
 * @author: shuzhuang.su
 * @create: 2019-10-12 20:48
 **/
public class ZhuanJia implements Query {

    private Action a;

    public ZhuanJia(Action a) {
        this.a = a;
    }

    @Override
    public String execute(String key) {
        return a.doQuery(key);
    }
}
