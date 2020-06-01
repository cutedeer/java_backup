import util.JacksonHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * test
 *
 * @author shuzhuang.su
 * @date 2020-05-25 22:46
 */
public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<KV> list = new ArrayList<>();
        while (in.next().charAt(0) == 13) {
            list.add(new KV(in.nextInt()));
        }
        getAll(list,new KV(0,1));

        int all=0;
        for(KV kv:list){
            System.out.println(kv.v);
            all+=kv.v;
        }
        System.out.println(all);
    }

    private static void getAll(List<KV> list, KV kv) {
        int size = list.size();
        int v = kv.v;
        for (int i = kv.k; i < size - 1; i++) {
            list.get(i).v=v;
            if (list.get(i).k > list.get(i + 1).k) {
                if (v - 1 > 0) {
                    v = v - 1;
                } else {
                    getAll(list, new KV(i, v + 1));
                    break;
                }
            } else if (list.get(i).k < list.get(i + 1).k) {
                v = 1;
            }
            list.get(i+1).v = v;
        }
    }


    static class KV {
        private int k;

        private int v;

        public KV(int k, int v) {
            this.k = k;
            this.v = v;
        }

        public KV(int k) {
            this.k = k;
        }

    }


}
