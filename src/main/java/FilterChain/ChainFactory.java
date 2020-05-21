package FilterChain;

import FilterChain.impl.Filter1;
import FilterChain.impl.Filter2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 责任链工厂
 *
 * @author shuzhuang.su
 * @date 2020-04-20 15:11
 */
@Service
public class ChainFactory {

    private FilterChain<IRequest, IResponse> chain;

    private FilterChain<IRequest,IResponse> chainT;

    @Resource(name = "filter1")
    private IFilter filter1;

    @Resource(name = "filter2")
    private IFilter filter2;

    private IFilter filter1T = new Filter1();

    private IFilter filter2T = new Filter2();

    @PostConstruct
    public void init() {

        this.chain = FilterChain.Builder.newBuilder().
                addFilter(filter1).
                addFilter(filter2).
                setChainName("chain").build();




    }

    public FilterChain<IRequest, IResponse> getChain() {
        return this.chain;
    }

    public FilterChain<IRequest, IResponse> getChainT() {
        return  FilterChain.Builder.newBuilder().
                addFilter(filter1T).
                addFilter(filter2T).
                setChainName("chainT").build();
    }
}
