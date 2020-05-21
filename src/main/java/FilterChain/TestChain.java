package FilterChain;

import FilterChain.impl.Request;
import FilterChain.impl.Response;
import org.springframework.stereotype.Service;
import util.JacksonHandler;

import javax.annotation.Resource;

/**
 * 测试Chain
 *
 * @author shuzhuang.su
 * @date 2020-04-20 15:18
 */
@Service
public class TestChain {

    @Resource
    private ChainFactory chainFactory;

    private void test() {

        FilterChain<IRequest, IResponse> filterChain = chainFactory.getChain();

        Request request = new Request("1");
        Response response = new Response();
        filterChain.doExecute(request, response);
        System.out.println(
                JacksonHandler.toSerialize(request)
        );
    }


    public static void main(String[] args) {

    }
}
