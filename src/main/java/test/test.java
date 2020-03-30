package test;

import bean.A;
import com.google.common.collect.*;
import util.JacksonHandler;


import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        List<String> sourceList= Lists.newArrayList();

        Map<String, A> map = Maps.newHashMap();
        String[] ss = new String[1000];
       // List<String> sourceList = new ArrayList<>();
        for (int i = 15;i<1000;i++) {
            map.put("第" + i + "条数据",new A("第" + i + "条数据"));
            sourceList.add("第" + i + "条数据");
            ss[i]="第" + i + "条数据";
        }

        //System.out.println(ss.);



         List<A> ss1 = map.keySet().stream().limit(100).map(o->{
             A a = new A();
             a.setName(null);
             return a;
        }).collect(Collectors.toList());

       // System.out.println(ss1.stream().count());

          ss1 = ss1.stream().filter(o -> o.getName() == null).collect(Collectors.toList());
        System.out.println(ss1);
          ss1 = ss1.stream().filter(s-> s.getName() != null).collect(Collectors.toList());

        System.out.println(ss1);
        //System.out.println(b);
//        System.out.println("数据条数：" + sourceList.size());
//        //for循环
//        long a1=System.currentTimeMillis();
//        for (int i = 0;i < sourceList.size();i++) {
//            doSome();
//        }
//        long a2=System.currentTimeMillis();
//        System.out.println("普通for循环用时：" + (a2-a1));
//
//        // 增强for循环
//        long b1=System.currentTimeMillis();
//        for (String t:sourceList) {
//            doSome();
//        }
//        long b2=System.currentTimeMillis();
//        System.out.println("增强for循环用时：" + (b2-b1));
//
//        // foreach
//        long c1=System.currentTimeMillis();
//        sourceList.forEach((t)-> doSome());
//        long c2=System.currentTimeMillis();
//        System.out.println("forEach循环用时：" + (c2-c1));
//
//        // 流
//        long d1=System.currentTimeMillis();
//        sourceList.stream().forEach((t)-> doSome());
//        long d2=System.currentTimeMillis();
//        System.out.println("forEach-Stream循环用时：" + (d2-d1));
//
//        // 流
//        long e1=System.currentTimeMillis();
//        sourceList.parallelStream().forEach((t)-> doSome());
//        long e2=System.currentTimeMillis();
//        System.out.println("forEach-parallelStream循环用时：" + (e2-e1));


    }
    private static void doSome() {
        try {
//            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class tt{
    public static void main(String[] args) {
//        differentBetweenMapAndPeek();

    }


     private void ser(){
         System.out.println(JacksonHandler.toSerialize(new A()));

         String name = "{\"name\":\"dfffref\",\"b\":false,\"o\":9,\"age\":\"ff\"}";
         String name1 = "{\"b\":false,\"o\":9,\"age\":\"ff\"}";
         A a = JacksonHandler.deSerialize(name, A.class);
         System.out.println(JacksonHandler.toSerialize(a));
         a= JacksonHandler.deSerialize(name1, A.class);
         System.out.println(JacksonHandler.toSerialize(a));
     }


     private static void differentBetweenMapAndPeek(){
         List<String> arr = new  ArrayList<String>();
         arr.add("A");
         arr.add("B");

         List<Integer> aa = Lists.newArrayList();
         aa.add(0);
         aa.add(1);




         List<String> a = arr.stream().peek(t->t=t.toLowerCase()).collect(Collectors.toList());
         System.out.println(a);
         System.out.println(
                 arr
         );
          a = arr.stream().map(t->t.toLowerCase()).collect(Collectors.toList());
         System.out.println(a);


         List<Integer> a1= aa.stream().peek(t->t = t+1).collect(Collectors.toList());
         System.out.println(a1);
       //  System.out.println(aa);
         a1 = aa.stream().map(t->t+1).collect(Collectors.toList());
         System.out.println(a1);


         Map<String, A> map = Maps.newHashMap();
         // List<String> sourceList = new ArrayList<>();
         for (int i = 1;i<10;i++) {
             map.put("第" + i + "条数据",new A("第" + i + "条数据"));
         }
         List<A> ss1 = map.keySet().stream().limit(100).map(o->{
             A as = new A();
             as.setName("map");
             return as;
         }).collect(Collectors.toList());
         System.out.println(ss1);
         ss1.stream().limit(100).map(o->{
             A as = new A();
             as.setName("map1");
             return as;
         }).collect(Collectors.toList());

         System.out.println(ss1);
         ss1.stream().limit(100).peek(o->{
             o.setName("peek");
         }).collect(Collectors.toList());
         System.out.println(ss1);

     }
}

