package xiangmu;
//用户的增删改查都在这里进行 对外只有方法。
//此类中没有输入和输出语句；
/*
Customer[] customers：用来保存客户对象的数组
int total = 0                 ：记录已保存客户对象的数量
该类至少提供以下方法：
public CustomerList(int totalCustomer)
public boolean addCustomer(Customer customer)
public boolean replaceCustomer(int index, Customer cust)
public boolean deleteCustomer(int index)
public Customer[] getAllCustomers()
public Customer getCustomer(int index)

public CustomerList(int totalCustomer)
        用途：构造器，用来初始化customers数组
        参数：totalCustomer：指定customers数组的最大空间
public boolean addCustomer(Customer customer)
        用途：将参数customer添加组中最后一个客户对象记录之后
        参数：customer指定要添加的客户对象
        返回：添加成功返回true；false表示数组已满，无法添加
public boolean replaceCustomer(int index, Customer cust)
        用途：用参数customer替换数组中由index指定的对象
        参数：customer指定替换的新客户对象
        index指定所替换对象在数组中的位置
        返回：替换成功返回true；false表示索引无效，无法替换
public boolean deleteCustomer(int index)
        用途：从数组中删除参数index指定索引位置的客户对象记录
        参数： index指定所删除对象在数组中的索引位置
        返回：删除成功返回true；false表示索引无效，无法删除
public Customer[] getAllCustomers()
        用途：返回数组中记录的所有客户对象
        返回： Customer[] 数组中包含了当前所有客户对象，该数组长度与对象个数相同。
public Customer getCustomer(int index)
        用途：返回参数index指定索引位置的客户对象记录
        参数： index指定所要获取的客户对象在数组中的索引位置
        返回：封装了客户信息的Customer对象

*/

import java.util.Arrays;

public class CustomerList {
    Customer[] customer=new Customer[5];
    int count=0;

    //对customer判断客户数量，数组进行扩容。
    public Customer[] List(){
        //新建一个Customer用来存储用户信息 （）里为客户基本信息，是有参构造。
        Customer customers=new Customer();
        if(count==customer.length){
            Customer[] newcustomer=new Customer[count+1];
            for (int i = 0; i <customer.length ; i++) {
                newcustomer[i]=customer[i];
            }
            customer=newcustomer;
        }
        customer[count]=customers;
        //totalCustomer++;
        count++;
        return customer;
    }
    //用于判断数组容量，为扩容做准备；
    //添加用户方法
    public boolean addCustomer(Customer customers){
        if(count>=customer.length){
            customer= Arrays.copyOf(customer,customer.length+1);

        }
        customer[count++]=customers;
        customers.setId(count);
       return true;
    }
    //修改用户信息方法
    public boolean replaceCustomer(int index, Customer cust){
        //int a=this.findid(index);
        if(index<0){
            return false;
        }else{
            customer[index]=cust;
        }return true;
    }
    //删除用户信息方法
    public boolean deleteCustomer(int index){
            int id=id(index);
        for (int i = id; i <count-1 ; i++) {
                customer[i]=customer[i+1];
        }
        customer[--count]=null;
       /* if(index>=customer.length||index<0){
            return false;
        }else{
        System.arraycopy(customer,index+1,customer,index,customer.length-index-1);
        customer[customer.length-1]=null;}*/
            //删完客户信息后会出现数组为空，Customer数组为引用的会有null值，显示信息就会有空指针。
            //我们可以将删完的数组信息重新赋值到新数组中 长度就为含有客户信息的长度

        return true;
    }
    //Customer[] 数组中包含了当前所有客户对象，该数组长度与对象个数相同。
    //显示用户信息方法
    public Customer[] getAllCustomers(){
        return Arrays.copyOf(customer,count);
           //  return customer;        在返回时如果数组（客户信息数量不满数组），
                                     //  会出现空指针现象（见View中的customer.getInfo）;

    }
    //获取客户信息位置方法
    public Customer getCustomer(int index){
        return customer[index];

    }
    public Customer findid(int id){
        //int index=-1;
        for (int i = 0; i <count; i++) {
            //i<customer.length时会有空
            if(customer[i].getId()==id){

                return customer[i];

            }
        }return null;
    }
    public int  id(int id){
        for (int i = 0; i <count; i++) {
            //i<customer.length时会有空
            if (customer[i].getId() == id) {

                return i;
            }
        }return -1;
    }
    public CustomerList() {
    }
}
