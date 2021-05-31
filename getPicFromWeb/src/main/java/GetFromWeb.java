import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;



//        =====================爬虫代码==========================

        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;

        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.URL;

public class GetFromWeb {

    //TODO 报错unable to find valid certification path to requested target 安全证书
    //MAVEN 设置中 添加 -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true

    public static void main(String[] args) throws IOException {
        //1. 读取HTML的内容，找到图片的地址链接
        //1.1 建立连接并获得一个Document对象
        //Jsoup类的connect静态方法，传入一个String类型的参数 “网址” 返回值为Connection
        //这个Connection是该网址的HTML字符串--String，字符串不好操作，所以用get()方法把Connection构建成以每个标签为对象的Document
        Document document = Jsoup.connect("https://pvp.qq.com/web201605/herolist.shtml").get();
        //System.out.println(document);
        //1.2 解析网站里展示的所有HTML文字 ---> 就是找图片的url地址
        //document的select方法利用class属性 找到包含英雄列表的ul标签   返回值为Element对象--每个Element对象对应一个标签   通过操作对象来操作标签
        Element elementUL = document.select("[class=herolist clearfix]").first();
        //System.out.println(elementUL);
        //再通过ul标签找到ul内所有的li     得到一个Element的集合
        Elements lis = elementUL.select("li");
        //循环遍历这个集合
        for (Element li : lis) {//对每一个li进行操作
            //获取每个英雄的名字
            String heroName = li.text();
            //获取li标签中的a标签
            Elements a = li.select("a");
            //获取a标签中href的属性
            String href = a.attr("href");
            //拼接为全路径
            String path = "https://pvp.qq.com/web201605/"+href;
            //打印输出一下
            //System.out.println(heroName+"的页面是"+path);

            //用拼好的路径再创建一个连接
            Document newdocument = Jsoup.connect(path).get();
            //用select方法查找 class=zk-con1 zk-con 的div标签
            Element newelement = newdocument.select("[class=zk-con1 zk-con]").first();
            //System.out.println(newelement);
            //获取该div标签的style属性的值
            String url = newelement.attr("style");
            //截取地址
            String substring = url.substring(url.indexOf("'") + 1, url.lastIndexOf("'"));
            //拼接为完整的img下载地址
            String imgurl = "https:"+substring;
            //打印输出一下
            //System.out.println(heroName+"的图片地址为："+imgurl);

            //2. 通过地址链接下载图片I/O
            //System.out.println("正在下载-"+heroName+"-"+imgurl);
            //通过url地址构建一个输入流，通过这个输入流就可以读取到文件的字节信息
            InputStream inputStream = new URL(imgurl).openStream();
            //构建一个本地的文件输出流用于写文件
            FileOutputStream fileOutputStream = new FileOutputStream("E://aaa//img//"+heroName+".jpg");
            //创建一个byte数组
            byte[] bytes = new byte[1024];
            //将读取的数据放入临时数组bytes里    返回值为读取的有效字节个数
            int count = inputStream.read(bytes);
            //while循环来写
            while (count!=-1){//=-1说明读到了数据
                //写入本地文件
                fileOutputStream.write(bytes,0,count);
                //清空缓存
                fileOutputStream.flush();
                //bytes数组接着读
                count = inputStream.read(bytes);
            }
            //关闭
            fileOutputStream.close();
            inputStream.close();
        }
    }

}