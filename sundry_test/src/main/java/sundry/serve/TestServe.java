package sundry.serve;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TestServe {

    public String testTryCache(){
        String path = "E:\\FileFolder\\1234.txt";
        //File file = new File(path);


        byte[] buff = new byte[1024];
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));){
            bis.read(buff);
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "faile";
        }


/*        try {
            System.out.println("try");
            return path;
        }catch (Exception e){
            System.out.println(e);
            return path;
        }*/


    }

    public void printStr(){
        System.out.println("TestServe中的printStr方法");
    }
}
