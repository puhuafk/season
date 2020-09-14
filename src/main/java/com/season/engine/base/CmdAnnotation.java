package com.season.engine.base;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拥有该注解的方法会被认为：对外提供的接口
 * The methods which contain this annotation would be considered: the interface provided to the outside world
 *
 * @author tangyi
 * @date 2018/12/5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CmdAnnotation {
    /**
     * 调用接口的字符串
     * The string used to invoke method
     *
     * @return String
     */
    String cmd();

    /**
     * 接口的版本号
     * The version of the method
     *
     * @return double
     */
    double version();

    /**
     * 接口权限级别，参考Constants.PUBLIC的注释，默认为PRIVATE
     * Interface permission level, refer to Constants.PUBLIC's annotation, default value is PRIVATE
     *
     * @return String
     */

    /**
     * 返回结果的改变次数
     * Number of changes of return value
     *
     * @return int
     */
    int minEvent() default 0;

    /**
     * 调用最小间隔，单位是秒
     * Minimum interval of call, unit is seconds
     *
     * @return int
     */
    int minPeriod() default 0;

    /**
     * 接口优先级
     * interface priority
     * */

    /**
     * 方法描述信息
     * Description information of method
     *
     * @return String
     */
    String description();
}
