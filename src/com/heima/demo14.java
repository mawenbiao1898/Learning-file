package com.heima;

import java.util.Scanner;

public class demo14 {
    public static void main(String[] args) {
        int[] c=select();
        System.out.print("[");
        for (int i = 0; i < c.length; i++) {
            System.out.print(i < c.length - 1 ? c[i] + "," : c[i]);
        }
        System.out.print("]");
    }




    public static int[] select() {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[7];
        for (int i = 0; i < nums.length-1; i++) {


            while (true) {
                System.out.println("输入您的第" + (i + 1) + "个红球");
                int a = sc.nextInt();

                //for (int j = 0; j < nums.length; j++) {

                    if (a<1||a>=33) {
                        System.out.println("输入错误");
                        //break;
                    } else {
                        if (chongfu(nums, a)) {
                            //nums[i] = a;
                            System.out.println("号码重复");

                        }else{
                            nums[i] = a;
                            break;
                        }
                    }
            }
        }

        while (true) {
            System.out.println("请输入一个篮球号码");
            int i = sc.nextInt();
            if (i > 0 && i <= 16) {

                nums[nums.length - 1] = i;
                break;
            } else {
                System.out.println("您的输入有误");
            }
        }
        return nums;
    }




    private static boolean chongfu(int[] strs,int b) {
        for (int i = 0; i < strs.length; i++) {
            if(strs[i]==b){
                return true;
            }
        }
        return false;
    }
}
