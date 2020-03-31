package function;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 可用来代替
 * 逻辑代码中大量  if-else 的方法
 * <p>
 * <p>
 * 简单好用
 *
 * @author shuzhuang.su
 * @date 2020-03-25 17:32
 */
public class FunctionSummary {


    public static void main(String[] args) {
        FunctionSummary replaceIfElse = new FunctionSummary();
        Param param = new Param();
        // 参数
        param.setChooseFunction("2");
        param.setStr("do something");
        // 得到目的函数
        Function<Param, Integer> paramIntegerFunction = replaceIfElse.functionMap.get(param.chooseFunction);
        // 执行函数，得到结果
        Integer apply = paramIntegerFunction.apply(param);
        System.out.println(apply);

    }


    /**
     * 有返回值,单参数
     * <p>
     * Function<Param,Integer>   param是参数，Integer是返回值
     */
    public Map<String, Function<Param, Integer>> functionMap = ImmutableMap.<String, Function<Param, Integer>>builder().
            put("1", this::function1).
            put("2", this::function2).build();


    /**
     * 有返回值，双参数
     * <p>
     * Function<String,Param,Integer>  String, param是参数，Integer是返回值
     */
    public Map<String, BiFunction<String, Param, Integer>> biFunctionMap = ImmutableMap.<String, BiFunction<String, Param, Integer>>builder().
            put("1", this::iFunction1).
            put("2", this::iFunction2).build();


    /**
     * 无返回值,单参数
     */
    public Map<String, Consumer<Param>> consumerMap = ImmutableMap.<String, Consumer<Param>>builder().
            put("1", this::function1).
            put("2", this::function2).build();


    /**
     * 无返回值,双参数
     */
    public Map<String, BiConsumer<String,Param>> iConsumerMap = ImmutableMap.<String, BiConsumer<String,Param>>builder().
            put("1", this::iFunction1).
            put("2", this::iFunction2).build();


    private int function1(Param param) {
        System.out.println("function1 run:" + param.getStr());
        return 1;
    }


    private int function2(Param param) {
        System.out.println("function2 run:" + param.getStr());
        return 2;
    }


    private int iFunction1(String i, Param param) {
        System.out.println("bi function1 run:" + param.getStr());
        return 1;
    }


    private int iFunction2(String i, Param param) {
        System.out.println("bi function2 run:" + param.getStr());
        return 2;
    }


    public static class Param {

        private String chooseFunction;

        private String str;


        public String getChooseFunction() {
            return chooseFunction;
        }

        public void setChooseFunction(String chooseFunction) {
            this.chooseFunction = chooseFunction;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

}
