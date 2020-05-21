package FilterChain.impl;

import FilterChain.IFilter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * 链方法2
 *
 * @author shuzhuang.su
 * @date 2020-04-20 15:07
 */
@Service
public class Filter2 implements IFilter<Request, Response> {


    @Override
    public void doFilter(@NotNull Request request, Response response) {
        if (request.getV().equals("2")) {
            response.setV("2");
        }
    }
}
