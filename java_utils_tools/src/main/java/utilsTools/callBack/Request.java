package utilsTools.callBack;


import java.util.concurrent.TimeUnit;

/**
 * java中的回调方法
 * */

public class Request {

    public static void main(String[] args) {
        // 起一个线程，调用Response的handle方法
        // lambda表达式
        new Thread(() ->
                new Response().handle("handle something",
                        data -> System.out.println("回调方法，收到数据 ：" + data))
        ).start();
        System.out.println("异步回调，先做其他事情");
    }

}

class Response {

    public void handle(String msg, CallBack callBack) {
        System.out.println("接收到的msg = " + msg);
        try {
            // sleep 3 秒，模拟满足某些条件
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 调用回调方法
        callBack.onResponse("请求完成，响应success");
    }

}

// 回调接口
interface CallBack {
    void onResponse(String data);
}
