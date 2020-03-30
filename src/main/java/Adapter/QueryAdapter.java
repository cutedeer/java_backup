package Adapter;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取方法
 *
 * @author shuzhuang.su
 * @date 2020-03-30 21:51
 */
@Service
public class QueryAdapter {


    @Resource
    private List<AdapterInter> adapterInters;

    /**
     * 根据key来找可用的adapter
     *
     * @param key
     * @return
     */
    public AdapterInter query(String key) {
        return adapterInters.stream().filter(x -> x.support(key)).findFirst().orElse(null);
    }

}
