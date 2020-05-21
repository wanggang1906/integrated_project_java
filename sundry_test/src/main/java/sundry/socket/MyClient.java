package sundry.socket;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient
{
    Socket client;

    boolean flag = true;

    // 数据输入流类在java.io包中，提供一种与机器无关的输入/出类
    FileInputStream fis;// 此输入流负责读取本机上要传输的文件

    DataOutputStream dos;// 此输出流负责向另一台电脑(服务器端)传输数据

    DataInputStream dis;// 此输入流负责读取另一台电脑的回应信息

    String fileStr;

    public static void main(String[] args)
    {
        new MyClient().ClientStart();
    }

    public void ClientStart()
    {
        try
        {
            client = new Socket("127.0.0.1", 8888);
            // 服务器端的IP,(这个只是在局域网内的)我的是这个,你的根据实际而定
            System.out.println("已连接");
            dos = new DataOutputStream(client.getOutputStream());
            dis = new DataInputStream(client.getInputStream());
            System.out.println("输入源文件夹路径");
            fileStr = (new Scanner(System.in)).next().toString();
            transmit(new File(fileStr));
            String s = "/]00";// 提示传输完毕的标记
            byte b[] = s.getBytes();
            dos.write(b, 0, s.length());
            dos.flush();
        }
        catch (IOException e)
        {
            System.out.println("MyClient Error");
        }
    }

    public void transmit(File f)
            throws IOException// 这是传输的核心,而且将被递归
    {
        byte b[];
        String ts;
        int ti;
        for (File f1 : f.listFiles())
        { // 首先通过if语句判断f1是文件还是文件夹
            if (f1.isDirectory()) // fi是文件夹,则向服务器端传送一条信息
            {
                ts = "/]0f" + (f1.getPath().replace(fileStr, ""));
                // "/]0f"用于表示这条信息的内容是文件夹名称
                b = ts.getBytes();
                dos.write(b);
                dos.flush();
                dis.read();
                transmit(f1);
                // 由于f1是文件夹(即目录),所以它里面很有可能还有文件或者文件夹,所以进行递归
            }
            else
            {
                fis = new FileInputStream(f1);
                ts = "/]0c" + (f1.getPath().replace(fileStr, ""));// 同上,表示这是一个文件的名称
                b = ts.getBytes();
                dos.write(b);
                dos.flush();
                dis.read();
                dos.writeInt(fis.available());// 传输一个整型值,指明将要传输的文件的大小
                dos.flush();
                dis.read();
                b = new byte[1024 * 4];
                while (fis.available() > 0)// 开始传送文件
                {
                    ti = fis.read(b);
                    dos.write(b, 0, ti);
                    dos.flush();
                }
                dos.flush();
                fis.close();
                dis.read();
            }
        }

    }
}
