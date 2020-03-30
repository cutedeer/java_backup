package Adapter.impl;

import Adapter.AdapterInter;
import org.springframework.stereotype.Service;

/**
 * 实现方法1
 *
 * @author shuzhuang.su
 * @date 2020-03-30 21:49
 */
@Service
public class Adapter1 implements AdapterInter {


    private static final String support="1";

    @Override
    public boolean support(String key) {
        return support.equals(key);
    }

    @Override
    public String query() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String update() {
        return null;
    }
}
