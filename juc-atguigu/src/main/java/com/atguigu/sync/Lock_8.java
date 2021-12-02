package com.atguigu.sync;

import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2021/12/2 22:17
 */
class Phone {
    public static synchronized void sendSMS() throws Exception {
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

/**
 * 8锁
 * // synchronized加到普通方法上，t1访问a普通方法、t2访问b普通方法，它们是互斥的
 * 1.两个普通同步方法，先打印短信还是邮件
 * ------sendSMS
 * ------sendEmail
 *
 * 2.停4秒在短信方法内，先打印短信还是邮件
 * ------sendSMS
 * ------sendEmail
 *
 * // synchronized加到a普通方法上，b普通方法没有加synchronized，t1访问a普通方法、t2访问b普通方法，它们不是互斥的
 * 3.新增普通的hello方法，是先打短信还是hello
 * ------getHello
 * ------sendSMS
 *
 * // synchronized加到普通方法上，t1用实例phone访问a普通方法、t2用实例phone2访问b普通方法，它们不是互斥的
 * 4.现在有两部手机，先打印短信还是邮件
 * ------sendEmail
 * ------sendSMS
 *
 * // synchronized加到static上，t1访问a静态方法、t2访问b静态方法，它们是互斥的
 * 5.两个静态同步方法，1部手机，先打印短信还是邮件
 * ------sendSMS
 * ------sendEmail
 *
 * 6.两个静态同步方法，2部手机，先打印短信还是邮件
 * ------sendSMS
 * ------sendEmail
 *
 * // synchronized加到static和普通方法上，t1访问a静态方法、t2访问b普通方法，它们不是互斥的
 * 7.1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 * ------sendEmail
 * ------sendSMS
 *
 * 8.1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 * ------sendEmail
 * ------sendSMS
 */
public class Lock_8 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();
        Thread.sleep(100);

        new Thread(()->{
            try {
                // phone.sendEmail();
                // phone.getHello();
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
