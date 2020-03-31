package util;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * 请求参数对象校验 某些属性是否为空
 */
public class ValidateUtil {

    /**
     * 拼接错误信息
     */
    public static String getErrorMsg(BindingResult bindingResult) {
        List<FieldError> bindingResults = bindingResult.getFieldErrors();
        StringBuilder errorMsg = new StringBuilder();
        for (FieldError fieldError : bindingResults) {
            errorMsg.append(String.format("*%s\n", fieldError.getDefaultMessage()));
        }
        return errorMsg.toString();
    }

    public static String validateObject(Object o, Validator validator) {
        BindingResult bindingResult = new BeanPropertyBindingResult(o, o.getClass().getSimpleName());
        validator.validate(o, bindingResult);
        if (bindingResult.hasErrors()) {
            return getErrorMsg(bindingResult);
        }
        return null;
    }

}
