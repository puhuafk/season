package com.season.engine.base;



/**
 * 对外提供的方法的具体信息
 * Detail information on methods provided to the outside world
 *
 * @author tangyi
 * @date 2018/10/15
 */

public class CmdDetail {


    private String MethodName;

    private String MethodDescription;

    private String MethodMinEvent;

    private String MethodMinPeriod;

    private String MethodScope;



    private double version;


    private String invokeClass;


    private String invokeMethod;

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String methodName) {
        this.MethodName = methodName;
    }

    public String getMethodDescription() {
        return MethodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.MethodDescription = methodDescription;
    }

    public String getMethodMinEvent() {
        return MethodMinEvent;
    }

    public void setMethodMinEvent(String methodMinEvent) {
        this.MethodMinEvent = methodMinEvent;
    }

    public String getMethodMinPeriod() {
        return MethodMinPeriod;
    }

    public void setMethodMinPeriod(String methodMinPeriod) {
        this.MethodMinPeriod = methodMinPeriod;
    }

    public String getMethodScope() {
        return MethodScope;
    }

    public void setMethodScope(String methodScope) {
        this.MethodScope = methodScope;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getInvokeClass() {
        return invokeClass;
    }

    public void setInvokeClass(String invokeClass) {
        this.invokeClass = invokeClass;
    }

    public String getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(String invokeMethod) {
        this.invokeMethod = invokeMethod;
    }

}
