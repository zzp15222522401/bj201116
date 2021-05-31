package xiangmu;

import java.util.Scanner;
/*public void enterMainMenu()
用途：显示主菜单，响应用户输入，根据用户操作分别调用其他相应的成员方法（如addNewCustomer），以完成客户信息处理。
    private void addNewCustomer()
	private void modifyCustomer()
	private void deleteCustomer()
	private void listAllCustomers()
用途：这四个方法分别完成“添加客户”、“修改客户”、“删除客户”和“客户列表”等各菜单功能。
	这四个方法仅供enterMainMenu()方法调用。
public static void main(String[] args)
用途：创建CustomerView实例，并调用 enterMainMenu()方法以执行
* */
public class CustomerView {
    private CustomerList customerList=new CustomerList();
    Scanner input=new Scanner(System.in);
    Customer[] customer=new Customer[10];
    int count=0;
    public static void main(String[] args) {
        new CustomerView().enterMainMenu();
       /* CustomerView view=new CustomerView();
        view.enterMainMenu();用来进入菜单界面，简写上述方法，把进入菜单界面构造成方法，
        让这个方法执行，不要在main中写太多*/
    }

    public void enterMainMenu() {
        CustomerView customerView = new CustomerView();
        char b;
        do {
            System.out.println("---------------客户信息管理软件--------------\n\t\t\t" +
                    "1.添加客户\n\t\t\t2.修改客户\n\t\t\t" +
                    "3.删除客户\n\t\t\t4.客户列表\n\t\t\t5.退出\n\t\t\t请选择（1-5）");
            b = TiShi.readMenuSelection();
            switch (b) {
                case '1':
                    customerView.add();
                    break;
                case '2':
                    customerView.change();
                    break;
                case '3':
                    customerView.delete();
                    break;
                case '4':
                    customerView.listAllCustomers();
                    break;
                case '5':
                    customerView.exit();
                    break;
            }
        }
        while (true);
    }
    public void add(){
        System.out.println("---------------添加客户----------------");
        System.out.print("姓名：");
        String name=TiShi.readString(20,"无名氏");
        System.out.print("性别：");
        char gender=TiShi.readChar('男');
        System.out.print("年龄：");
        int age=TiShi.readInt(0);
        System.out.print("电话：");
        int phone=TiShi.readInt();
        System.out.print("邮箱：");
        String email=TiShi.readString(20,"1234567890@qq.com");
        Customer customer=new Customer(name,gender,age,phone,email);
        if(customerList.addCustomer(customer)){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }
    public void list() {
        Customer[] customerlist =customerList.List();
        System.out.println("---------------------修改客户---------------------");
        System.out.println("编号\t姓名\t性别\t年龄\t电话\t邮箱");
        for (int s = 0; s < customerlist.length && customerlist[s] != null; s++) {
            System.out.println(customerlist[s].getinfo());
        }
        System.out.println("---------------------添加完成---------------------");
        return;
    }
    public void change(){
        int index=-1;
        System.out.println("---------------修改客户----------------");
        //先定义一个变量客户编号为空，空值的话就结束，不是就把编号给定义的变量，然后循环让客户输入，不输对就不结束
        Customer customersid=null;
        while (true){
        System.out.println("输入您要修改客户的编号(-1退出)");
        int a1=TiShi.readInt();
        if(a1==-1){
            return;
        }else {
            //输入不等于-1时也不一定存在客户编号，有可能客户编号为null。
            customersid=customerList.findid(a1);

            if(customersid!=null){
                break;
                }
            }
        }
                    System.out.println("姓名"+(customersid.getName())+"：");
                    String name=TiShi.readString(6,customersid.getName());
                    System.out.print("性别"+(customersid.getGender())+"：");
                    char gender=TiShi.readChar(customersid.getGender());
                    System.out.print("年龄"+(customersid.getAge())+"：");
                    int age=TiShi.readInt(customersid.getAge());
                    System.out.print("电话"+(customersid.getPhone())+"：");
                    int phone=TiShi.readInt(customersid.getPhone());
                    System.out.print("邮箱"+(customersid.getEmail())+"：");
                    String email=TiShi.readString(20,customersid.getEmail());
                    Customer customer1=new Customer(customersid.getId(),name,gender,age,phone,email);


                    //完成修改操作,可以画内存图看看赋值操作
                    customersid.setName(name);
                    customersid.setAge(age);
                    customersid.setEmail(email);
                    customersid.setGender(gender);
                    customersid.setPhone(phone);
                    System.out.println("修改完成");
    }
       /* //System.out.println("姓名：\n性别：\n年龄：\n电话：\n邮箱：");
        String name=TiShi.readString(6,"无名氏");
        char gender=TiShi.readChar('男');
        int age=TiShi.readInt(0);
        int phone=TiShi.readInt();
        String email=TiShi.readString(20,"1234567890@qq.com");
        Customer customer1=new Customer(name,gender,age,phone,email);*/

    public void delete(){
        System.out.println("---------------删除客户----------------");
        Customer customersid=null;
        while (true){
            System.out.println("输入您要删除客户的编号(-1退出):");
            int a1=TiShi.readInt();
            if(a1==-1){
                return;
            }else {
                //输入不等于-1时也不一定存在客户编号，有可能客户编号为null。
                customersid=customerList.findid(a1);

                if(customersid!=null){
                    break;
                }
            }
        }
        if(customersid==null)
            return;
        System.out.println("确认是否删除（Y/N）?");
        //char a2=input.next().charAt(0);
        char a=TiShi.readConfirmSelection();
        if(a=='Y') {

            boolean b=customerList.deleteCustomer(customersid.getId());
            if(b)
                System.out.println("删除完成");
        }
        }

    public void listAllCustomers(){
        Customer[] allcustomer=customerList.getAllCustomers();
        System.out.println("---------------客户列表----------------");
        System.out.println("编号：\t"+"姓名:\t"+"性别：\t"+"年龄：\t"+"电话：\t"+"邮箱：");
        for (Customer customer1 : allcustomer) {
            System.out.println(customer1.getinfo());
        }
        //customer1.在客户信息不全时数组会有null值，会出现空指针。
        System.out.println("---------------客户列表完成----------------");
    }
    public void exit(){
        System.out.println("确认是否退出(Y/N)?");
        char a4=TiShi.readConfirmSelection();
        while(a4=='Y')
            System.out.println("退出成功");
        return;
    }
}





