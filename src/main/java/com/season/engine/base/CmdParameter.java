package com.season.engine.base;


/**
 * 对外提供的方法的参数信息
 * Parameter information of methods provided to the outside world
 *
 * @author tangyi
 * @date 2018/11/19
 */

public class CmdParameter {
    /**
     * 参数名称
     * Parameter name
     */

    private String ParameterName;

    /**
     * 参数类型（可以是任何类型，包括基础类型或者自定义类型）
     * Parameter type(can be any type, including base type or custom type)
     */

    private String ParameterType;

    /**
     * 数值类型参数的范围，格式为：[(\[]\d+,\d+[)\]] 。例如：(1,100]表示1< x <=100
     * The range of numerical type parameters, format: [(\[] d+, \ d+ [)]]. For example, (1,100] means 1 < x <=100
     */

    private String ParameterValidRange;

    /**
     * 字符类型参数的格式，值为正则表达式
     * Format of character type parameters with regular expression
     */

    private String ParameterValidRegExp;

    public CmdParameter() {
    }

    public CmdParameter(String parameterName, String parameterType, String parameterValidRange, String parameterValidRegExp){
        this.ParameterName = parameterName;
        this.ParameterType = parameterType;
        this.ParameterValidRange = parameterValidRange;
        this.ParameterValidRegExp = parameterValidRegExp;
    }


    public String getParameterName() {
        return ParameterName;
    }

    public void setParameterName(String parameterName) {
        ParameterName = parameterName;
    }

    public String getParameterType() {
        return ParameterType;
    }

    public void setParameterType(String parameterType) {
        ParameterType = parameterType;
    }

    public String getParameterValidRange() {
        return ParameterValidRange;
    }

    public void setParameterValidRange(String parameterValidRange) {
        ParameterValidRange = parameterValidRange;
    }

    public String getParameterValidRegExp() {
        return ParameterValidRegExp;
    }

    public void setParameterValidRegExp(String parameterValidRegExp) {
        ParameterValidRegExp = parameterValidRegExp;
    }
}
