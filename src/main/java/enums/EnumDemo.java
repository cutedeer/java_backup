package enums;

import bean.DemoBean;
import org.apache.commons.lang3.StringUtils;
import util.JacksonHandler;

/**
 * 枚举可用来代替
 * <p>
 * switchcase 和 大量的if-else 程序
 *
 * @author shuzhuang.su
 * @date 2020-02-05 11:48
 */
public enum EnumDemo {


    enum1("枚举1") {
        @Override
        public DemoBean apply(DemoBean bean) {
            // do something
            bean.setSum2(1);
            return bean;
        }
    },

    enum2("枚举2") {
        @Override
        public DemoBean apply(DemoBean bean) {
            // do something
            bean.setSum2(2);
            return bean;
        }
    },

    other("其他") {
        @Override
        public DemoBean apply(DemoBean bean) {
            // do something
            bean.setSum2(3);
            return bean;
        }
    },
    ;

    private String desc;

    // 每个枚举对应的相应方法
    public abstract DemoBean apply(DemoBean bean);

    EnumDemo(String desc) {
        this.desc = desc;
    }


    /**
     * 此功能与  Enum.valueOf() 功能类似，
     * <p>
     * 但为了避免 非法枚举 而抛异常重写了一个方法
     *
     * @param name
     * @return
     */
    public static EnumDemo fromName(String name) {
        for (EnumDemo demo : EnumDemo.values()) {
            if (StringUtils.equals(demo.name(), name)) {
                return demo;
            }
        }
        return EnumDemo.other;
    }

    public static void main(String[] args) {

        // 传入非法枚举，会抛异常
        try {
            EnumDemo demo1 = EnumDemo.valueOf("45");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 根据不同的枚举类型调用不同的方法
        EnumDemo demo = EnumDemo.fromName("2");
        DemoBean apply = demo.apply(new DemoBean());

        System.out.println(JacksonHandler.toSerialize(apply));
    }
}
