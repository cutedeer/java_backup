package bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * demo
 *
 * @author shuzhuang.su
 * @date 2020-02-29 14:37
 */
public class DemoBean {


    @NotBlank(message = "不允许为空")
    @Length(max = 20,min = 3,message = "长度有误")
    private String value1;

    private String value2;

    private String value3;

    @Min(value = 2,message = "必须大于2")
    @Max(value = 10,message = "必须小于10")
    private int sum1;

    private int sum2;


    private List<String> list;


    private Map<String,Object> map;

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public int getSum1() {
        return sum1;
    }

    public void setSum1(int sum1) {
        this.sum1 = sum1;
    }

    public int getSum2() {
        return sum2;
    }

    public void setSum2(int sum2) {
        this.sum2 = sum2;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
