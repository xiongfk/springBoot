package com.xiongfk.springBooting.designMode.mediator;

/**
 * 功能描述 TODO
 * @Author xiongfk
 * @Date 2020/8/5
 * @Version 1.0
 **/
public class ClienterTest {
    public static void main(String[] args) {
        Mediator mediator = new Leader();
        String clerkNameA = "职员A";
        String clerkNameB = "职员B";
        String clerkNameC = "职员C";

        OfficeClerk officeClerkA = new OfficeClerkA(clerkNameA,mediator);
        OfficeClerk officeClerkB = new OfficeClerkB(clerkNameB,mediator);
        OfficeClerk officeClerkC = new OfficeClerkC(clerkNameC,mediator);

        //职员A的需求
        String messageA = "这些资料需要B职员操作";
        officeClerkA.call(messageA,officeClerkB,officeClerkA.name);

        //职员C的请求
        String messageC = "这些资料需要A职员签名";
        officeClerkC.call(messageC,officeClerkB,officeClerkC.name);
    }
}
