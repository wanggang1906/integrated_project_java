package sundry.socket;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyServer
{
    ServerSocket server=null; // java中专门用来建立服务器的类，传入端口号
    Socket client=null; // 定义服务端创建出的客户端
    boolean flag=true;
    DataInputStream dis;
    DataOutputStream dos;
    FileOutputStream fos;

    public static void main(String[] args)
    {
        new MyServer().ServerStart();
    }
    public void ServerStart()
    {
        try
        {
            server=new ServerSocket(8888);
            System.out.println("端口号:"+server.getLocalPort());
            // 当客户端程序建立一个socket连接时，服务器的server便响应这个连接，并且server.accept()方法会创建一个socket对象
            // 服务端便可以利用这个socket对象与客户端进行通讯
            client=server.accept();
            if (client !=null){
                System.out.println("连接完毕");
            }
            System.out.println ("输入目标地址");
            String fileStr = (new Scanner(System.in)).next().toString();

            // getInputStream 得到一个输入流，客户端的socket对象上的getInputStream方法得到的输入流其实是从服务端发回的数据流
            // getOutputStream 得到一个输出流，客户端socket对象上的getOutputStream方法的输出流就是将要发送到服务端的数据流
            // 其本质是一个缓存区，暂存将要发送过去的数据
            // 使用inputStream.readLine()方法读取客户端的输入，out.println()向客户端发送数据
            dis=new DataInputStream(client.getInputStream());
            dos=new DataOutputStream(client.getOutputStream());
            String answer="gaaasdf";
            byte ans[]=answer.getBytes(); // 把字符串转为字符数组
            byte b[]=new byte[1024 * 4];
            int ti;
            new File(fileStr).mkdirs();
            while(flag)
            {
                ti=dis.read(b);
                dos.write(ans);
                String select=new String(b,0,ti);
                if(select.contains("/]0f"))
                {
                    File f=new File(fileStr+(select.replace("/]0f","")));
                    System.out.println("creat directory");
                    f.mkdirs();
                }
                else if(select.contains("/]0c"))
                {
                    fos=new FileOutputStream(fileStr+(select.replace("/]0c","")));
                    String cs;
                    boolean cflag=true;
                    int tip=dis.readInt();
                    dos.write(ans);
                    while(tip>0)
                    {
                        ti=dis.read(b,0,(tip>1024*4 ? 1024*4:tip));
                        tip=tip-ti;
                        cs=new String(b,0,4);
                        fos.write(b,0,ti);
                    }
                    fos.flush();
                    fos.close(); // 关闭流
                    dos.write(ans);
                }
                else if(select.contains("/]00"))
                {
                    flag=false;
                }
            }
            dis.close();
            client.close(); // 关闭连接
            server.close();
        }
        catch(IOException e)
        {
            System.out.println("MyServer Error");
        }
    }
}



/**
 * 多线程服务端的思路
 * 循环检测有无客户端连接到服务端
 * 若有，则创建一个线程来服务这个客户端，线程名为ServerThread，这个类扩展了Thread类
 * 编写方法与单线程类似
 * 在服务端和客户端单独建一个线程来查看有无输入流，若有，则实时显示出来*/