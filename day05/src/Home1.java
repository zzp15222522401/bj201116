/*
-----------------家庭收支记账软件-----------------

                   1 收支明细
                   2 登记收入
                   3 登记支出
                   4 退    出
假设家庭起始的生活基本金为10000元。
每次登记收入（菜单2）后，收入的金额应累加到基本金上，并记录本次收入明细，以便后续的查询。
每次登记支出（菜单3）后，支出的金额应从基本金中扣除，并记录本次支出明细，以便后续的查询。
查询收支明细（ 菜单1）时，将显示所有的收入、支出名细列表
收支明细记录可以使用Sting类型的变量来实现，其初始值为明细表的表头。例如：
	String details = "收支\t账户金额\t收支金额\t说    明\n";
	details+=”收入\t11000\t1000\t奖金\n”;
在登记收支时，将收支金额与balance相加或相减，收支记录直接串接到details后面即可。
*/

import com.sun.media.sound.SoftTuning;
import org.jcp.xml.dsig.internal.SignerOutputStream;

import javax.sql.rowset.serial.SQLOutputImpl;
import javax.xml.transform.SourceLocator;
import java.util.Scanner;
public class Home1{
    public static void main(String[] args){
        int balance=10000;
        String details="收支\t账户金额\t收支金额\t说明\n";
        Scanner input=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            System.out.println("---------------家庭收支记账软件----------------\n");
            System.out.println("\t\t1收支明细\n\t\t2登记收入\n\t\t3登记支出\n\t\t4退出\n");
            System.out.println("\t\t请选择（1-4）：");
            int a=input.nextInt();
            switch(a){
                case 1:
                    System.out.println(details);
                    break;
                case 2:
                    System.out.println("本次收入金额：");
                    double money=input.nextDouble();
                    System.out.println("本次收入说明：");
                    String info=input.next();
                    balance+=money;
                    details+="收入\t"+balance+"\t\t"+money+"\t\t"+info+"\n";
                    break;
                case 3:
                    System.out.println("本次支出金额：");
                    double money1=input.nextDouble();
                    System.out.println("本次支出说明：");
                    String info1=input.next();
                    balance-=money1;
                    details+="支出\t"+balance+"\t\t"+money1+"\t\t"+info1+"\n";
                   break;
                case 4:
                    System.out.println("确认是否退出(Y/N):");
                    Scanner input4=new Scanner(System.in);
                    String y1="y";
                    String y12="Y";
                    String n1="n";
                    String n12="N";
                    String y2=input4.next();
                    if(y2.equals(y1)||y2.equals(y12)){
                        System.out.println("请按任意键继续...");
                        flag=false;
                    }
                    else if(y2.equals(n1)||y2.equals(n12)){
                        	System.out.println(" ");

                    }
                    else //(!y2.equals(y1)&&!y2.equals(y12)&&!y2.equals(n1)&&!y2.equals(n12))
                    {
                    System.out.println("输入错误，请重新输入：");
                      }

                    }

            }

        }


    }






