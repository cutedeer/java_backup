package FilterChain.impl;

import FilterChain.IFilter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * 链方法1
 *
 * @author shuzhuang.su
 * @date 2020-04-20 15:04
 */
@Service
public class Filter1 implements IFilter<Request,Response> {


    public Filter1() {
    }

    @Override
    public void doFilter(@NotNull Request request, Response response) {
        if(request.getV().equals("1")){
            response.setV("1");
        }
    }
}
