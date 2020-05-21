package FilterChain;

import javax.validation.constraints.NotNull;

/**
 * 抽象的链
 *
 * @author shuzhuang.su
 * @date 2020-04-20 14:47
 */
public interface IFilter<R extends IRequest, T extends IResponse> {


    /**
     * 责任链方法
     */
    void doFilter(@NotNull R request, T response);

}
