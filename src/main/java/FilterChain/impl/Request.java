package FilterChain.impl;

import FilterChain.IRequest;

/**
 * 入参
 *
 * @author shuzhuang.su
 * @date 2020-04-20 15:04
 */
public class Request implements IRequest {

    private String v;


    public Request(String v) {
        this.v = v;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
