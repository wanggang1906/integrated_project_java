package javaSource.test.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        HashMapTest hmt = new HashMapTest();
        hmt.creatHashMap();

    }


    /**
     * hashMap的赋值的测试用例
     * */
    public void creatHashMap(){
        Map<String,Integer> hashMap = new HashMap<>();
        hashMap.put("ha0",1);
        hashMap.put("ha1",1);
        hashMap.put("ha2",1);
        hashMap.put("ha3",1);
        System.out.println(hashMap);
    }
}
