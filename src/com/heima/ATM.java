package com.heima;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    ArrayList<Account> accounts=new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    Random r=new Random();

   public void addaccount(){
       Account a=new Account();
       System.out.println("输入您的名字");
       a.setName(sc.next());

       while (true) {
           System.out.println("输入您的性别");
           char sex = sc.next().charAt(0);
           if (sex=='男'||sex=='女'){
               a.setSex(sex);
               break;
           }else{
               System.out.println("输入错误");
           }
       }

       while (true) {
           System.out.println("输入您的密码");
           int code = sc.nextInt();
           System.out.println("确认您的密码");
           int OKcode = sc.nextInt();
           if (code==OKcode){
               a.setCode(code);
               break;
           }else {
               System.out.println("输入错误");
           }
       }

       System.out.println("设置您的每次提现额度");
       double limit = sc.nextInt();
       a.setLimit(limit);

       String creid = creatid();
       a.setId(creid);
       accounts.add(a);
       System.out.println("恭喜您开卡成功"+a.getName()+"您的卡号为"+creid);

   }

    private String creatid() {

       String id="";

        //int[] nums=new int[8];


        while (true) {
            for (int i = 0; i < 8; i++) {
               // nums[i]=num;
                int num = r.nextInt(10);
                id+=num;
            }

            Account acc = accountByCardId(id);
            if(acc==null){
                return id;
            }
        }

        //String id= nums.toString();
            // a.setId(id);
            // accounts.add(a);
            //没有提交怎么可以遍历？？
//            for (int i = 0; i < accounts.size(); i++) {
//                if (!check(id)){
//                    System.out.println("您的卡号为："+id);
//                    return;
//                }
//            }


    }

    private Account accountByCardId(String ID) {
       for (Account a:accounts) {

           if (a.getId().equals(ID)){
               return a;
           }
       }
       return null;

    }


//    private boolean check(String ID) {
//        for (Account account : accounts) {
//            if (account.getId().equals(ID)) {
//                return true;
//            }
//        }
//       return false;
//    }

    public void load(){
//       for (Account a:accounts) {
//           if (a==null){
//               System.out.println("无~~~");
//               return;
//           }
//       }
        if (accounts.size()==0){
            System.out.println("无~~~");
            return;
        }

        while (true) {
            System.out.println("输入您的卡号：");
            String ID = sc.next();
            Account acc = accountByCardId(ID);
            if (acc == null) {
                System.out.println("卡号错误");
            } else {
                while (true) {
                    System.out.println("输入您的密码：");
                    int code = sc.nextInt();
                    if (acc.getCode() == code) {
                        System.out.println("恭喜您" + acc.getName() + "登陆成功" + "您的卡号为" + acc.getId());
                        operator(acc);
                        return;
                    } else {
                        System.out.println("密码错误");
                    }
                }
            }
        }

   }

    private void operator(Account acc) {
        while (true) {
            System.out.println("=="+acc.getName()+",您可以办理一下业务==");
            System.out.println("1、查询账户");//
            System.out.println("2、存款");//
            System.out.println("3、取款");//
            System.out.println("4、转账");
            System.out.println("5、修改密码");
            System.out.println("6、退出");//
            System.out.println("7、注销账户");
            System.out.println("请选择");
            String comment = sc.next();
            switch (comment){
                case "1":
                    selectAccount(acc);//
                    break;
                case "2":
                    savings(acc);//
                    break;
                case "3":
                    withdrawal(acc);
                    break;
                case "4":
                   transfer(acc);
                    break;
                case "5":
                    changeYourPassword(acc);
                    return;
                case "6":
                   if(quit()){
                       System.out.println("您即将退出");//
                       return;
                   }
                   break;

                case "7":
                    if (CancelYourAccount(acc)){
                        if (acc.getBalance()==0){
                            System.out.println("您的账户即将注销");
                            accounts.remove(acc);
                            System.out.println("您的账户已经注销");
                            return;
                        }else{
                            System.out.println("您的余额大于零元,不允许注销");
                        }
                    }
                    break;
                default:
                    System.out.println("输入错误");
            }
        }

    }

    private boolean CancelYourAccount(Account acc) {
        while (true) {
            System.out.println("确定注销账户吗?---y/n");
            String comment = sc.next();
            if(comment.equals("y")){
                return true;
            }else if(comment.equals("n")){
                return false;
            }else{
                System.out.println("您的输入有误");
            }
        }
    }

    private boolean quit() {
        while (true) {
            System.out.println("您确认退出账户吗?--y/n");
            String comment = sc.next();
            if (comment.equals("n")) {
                return false;
            }else if (comment.equals("y")) {
                return true;
            }else {
                System.out.println("输入错误");
            }
        }
    }

    private void changeYourPassword(Account acc) {
        while (true) {
            System.out.println("输入您当前的密码");
            int a = sc.nextInt();
            if(acc.getCode()==a){
                System.out.println("输入您的新密码");
                int a2 = sc.nextInt();
                System.out.println("确认您的新密码");
                int a3 = sc.nextInt();
                if (a2==a3){
                    acc.setCode(a);
                    System.out.println("您的密码更改成功");
                    return;
                }else {
                    System.out.println("两次密码不一致,重新输入");
                }
            }else {
                System.out.println("您的密码输入错误,重新输入");
            }
        }

    }

    private void transfer(Account acc) {
       if(acc.getBalance()>0){
           if (accounts.size()>1){
               while (true) {
                   System.out.println("请输入对方卡号:");
                   String a = sc.next();
                   Account account = accountByCardId(a);
                   if(account!=null){
                       String name="*"+account.getName().substring(1);
                       System.out.println("请输入对方姓氏"+name);
                       String a1 = sc.next();
                       if (account.getName().startsWith(a1)){
                           System.out.println("输入您转账的金额");
                           int a11 = sc.nextInt();
                           if(acc.getBalance()>=a11){
                               account.setBalance(account.getBalance()+a11);
                               acc.setBalance(acc.getBalance()-a11);
                               System.out.println("转账成功");
                               return;
                           }else{
                               System.out.println("您的余额不足");
                           }

                       }else{
                           System.out.println("您输入的名字不存在");
                       }
                   }else {
                       System.out.println("您输入的账户不存在");
                   }
               }
           }else {
               System.out.println("只有你一个人,不能转账");
           }
       }else {
           System.out.println("您的账户余额不足,不能转账");
       }
    }

    private void withdrawal(Account acc) {

        if (acc.getBalance() >= 100) {
            while (true) {
                System.out.println("输入您要取款的金额:");
                double a = sc.nextDouble();
                if(acc.getLimit()>a){
                    if (acc.getBalance()>=a){
                        System.out.println("您已取款"+a+"元");
                        acc.setBalance(acc.getBalance()-a);
                        System.out.println("您的余额为"+acc.getBalance()+"元");
                       return;
                    }

                }else {
                    System.out.println("您的当日限额,请重新输入!");
                }
            }
        }else{
            System.out.println("不足以取出100元~~,请先存款~~");
        }
    }

    private void savings(Account acc) {
        System.out.println("输入您的存款金额");
        double a = sc.nextDouble();
        acc.setBalance(acc.getBalance()+a);
        System.out.println("您已经存款"+a+"元,余额为"+acc.getBalance());

    }

    private void selectAccount(Account acc) {
        System.out.println("您的名字"+acc.getName());
        System.out.println("您的卡号"+acc.getId());
        System.out.println("您的性别"+acc.getSex());
        System.out.println("您的提现额度"+acc.getLimit());
        System.out.println("您的余额"+acc.getBalance());
        System.out.println("当前有"+accounts.size()+"个,账户");

    }


    public void start(){
       while (true) {
           System.out.println("==欢迎进入ATM系统==");
           System.out.println("1、用户登陆");
           System.out.println("2、用户开户");
           System.out.println("3、退出");
           System.out.println("请选择您的操作命令：");
           Scanner sc=new Scanner(System.in);
           String comment = sc.next();
           switch (comment){
               case "1":
                   load();
                   break;
               case "2":
                   addaccount();
                   break;
               case "3":
                   return;
               default:
                   System.out.println("输入有误，请重新输入！");
           }
       }


   }
}
