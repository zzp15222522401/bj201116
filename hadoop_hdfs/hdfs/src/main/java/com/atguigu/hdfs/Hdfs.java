package com.atguigu.hdfs;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;

public class Hdfs {
    private FileSystem fs;

    /**
     * 检测FileSystem对象是否存在
     */
    @Test
    public void testCreateFs(){
        System.out.println("fs:"+fs.getClass().getName());
    }

    /**
     * 创建一个文件或者目录
     */
    @Test
    public void testmkdir() throws IOException {
        fs.mkdirs(new Path("/newopt"));
    }


    /**
     * 文件上传
     */
    @Test
    public void testcopyFromLocalFile() throws IOException {
        fs.copyFromLocalFile(false,true,
                new Path("E:\\banzhang.txt"),
                new Path("/newopt"));
    }
    /**
     * 文件下载
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testcopyToLocalFile() throws IOException {
        fs.copyToLocalFile(false,new Path("/newopt/banzhang.txt"),
                new Path("E:\\aaa"),false);
    }

    /**
     *删除文件和目录
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testdelete() throws IOException {
        fs.delete(new Path("/wcoutput"),true);
    }

    /**
     * 移动文件和更名
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testmove() throws IOException {
        fs.rename(new Path("/opt/atguigu/wcinput/wcinput.sh"),
                  new Path("/newopt/wcinput.txt"));

    }

    /**
     * 查看文件的详细信息
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testList() throws IOException {
        RemoteIterator<LocatedFileStatus> list = fs.listFiles(new Path("/"), true);
        if(list.hasNext()){
            LocatedFileStatus next = list.next();
            System.out.println(next.getAccessTime());//时间
            BlockLocation[] blockLocations = next.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);//块存储的主机节点
                }
            }
            System.out.println(next.getGroup());//分组
            System.out.println(next.getLen());//长度
            System.out.println(next.getOwner());//
            System.out.println(next.getPath());//路径
            System.out.println(next.getPermission());//权限
            System.out.println(next.getReplication());//存储个数
            System.out.println(next.getPath().getName());//名称
            //System.out.println(accessTime,blockLocations,group,len,owner,path, permission,replication,name);
        }
    }

    /**
     * 判断是文件还是目录
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testIsFile() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            if(fileStatus.isDirectory()){//如果是目录
                System.out.println("dir:"+fileStatus.getPath().getName());
            }
            else if(fileStatus.isFile()){//如果是文件
                System.out.println("file:"+fileStatus.getPath().getName());
            }
        }
    }


    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        // 创建URI对象
        URI uri = URI.create("hdfs://hadoop102:8020");
//        URI uri1 = new URI("");
        // 创建Configuration对象
        Configuration conf = new Configuration();
        conf.set("dfs.replication","1");
        // 指定操作用户
        String user = "atguigu";
        // 获取文件系统对象 FileSystem(获取NN)
        fs = FileSystem.get(uri, conf, user);
    }

    @After
    public void colseFs() throws IOException {
        // 关闭连接对象
        fs.close();
    }
}
