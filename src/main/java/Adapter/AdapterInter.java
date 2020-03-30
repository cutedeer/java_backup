package Adapter;

/**
 * 模板方法
 *
 * @author shuzhuang.su
 * @date 2020-03-30 21:48
 */
public interface AdapterInter {

    boolean support(String key);

    String query();

    String save();

    String update();

}
