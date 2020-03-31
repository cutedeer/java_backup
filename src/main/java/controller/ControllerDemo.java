package controller;

import bean.DemoBean;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.Validator;
import util.ValidateUtil;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * mvc  的一些小技巧
 *
 * @author shuzhuang.su
 * @date 2020-03-31 12:25
 */


// 包含 @Controller 和 @ResponseBody，加了此注解就不需要在每个方法上加ResponseBody了
@RestController
public class ControllerDemo {


    @Resource
    private Validator validator;

    /**
     * 若参数校验过多，可以 在参数前面加@Valid 做参数校验
     * <p>
     * demo若是json传参 需在前面加 @RequestBody 注解
     *
     * @param demoBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/demo1")
    public String demo1(@RequestBody @Valid DemoBean demoBean, BindingResult bindingResult) {
        return ValidateUtil.getErrorMsg(bindingResult);
    }

    /**
     * 若参数校验过多，不想使用@Valid注解
     * 也可以使用如下方式校验
     * <p>
     * demo若是json传参 需在前面加 @RequestBody 注解
     *
     * @param demoBean
     * @return
     */
    @RequestMapping("/demo2")
    public String demo2(@RequestBody DemoBean demoBean) {
        return ValidateUtil.validateObject(demoBean,validator);
    }

}
