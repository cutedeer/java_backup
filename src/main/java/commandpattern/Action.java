package commandpattern;



/**
 * 动作
 *
 * @author: shuzhuang.su
 * @create: 2019-10-12 20:49
 **/
public class Action {

    public String doQuery(String key){
        return "专家"+key;
    }

    public String doQuery1(String key){
       return  "顾问"+key;
    }


}
