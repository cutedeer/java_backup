package FilterChain.impl;

import FilterChain.IResponse;

/**
 * 出参
 *
 * @author shuzhuang.su
 * @date 2020-04-20 15:06
 */
public class Response implements IResponse {

    private String v;

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
