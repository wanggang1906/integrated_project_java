package utilsTools.guava;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;

public class NetworkTest {

    /**
     * Network(网络)与Graph以及ValueGraph有很大的不同性，最大的不同点是Network中允许并行边（即两个节点间可以有多条同向边，
     * 如：节点A和节点B可以有两条同向边：A->B: a-b-1，a-b-2），这就导致了前面介绍的使用节点作为Map的key的数据结
     * 构GraphConnections的逻辑走不下去了，因为节点不唯一了。使得设计者重新设计了另一套使用边为Key的机制来实现节点的连接关系
     **/

    private Integer NODE_COUNT = 5;
    private Integer EDGE_COUNT = 5;

    // 创建网
    public void creatNetwork(){
        MutableNetwork<Integer, String> network1 = NetworkBuilder.directed() //有向网
                .allowsParallelEdges(true) //允许并行边
                .allowsSelfLoops(true) //允许自环
                .nodeOrder(ElementOrder.<Integer>insertion()) //节点顺序
                .edgeOrder(ElementOrder.<String>insertion()) //边顺序
                .expectedNodeCount(NODE_COUNT) //期望节点数
                .expectedEdgeCount(EDGE_COUNT) //期望边数
                .build();

        network1.addEdge(1,2,"1");
        network1.addEdge(2,3,"2");
        network1.addEdge(3,2,"3");
        network1.addEdge(1,4,"4");
        network1.addEdge(2,4,"5");
        network1.addEdge(3,5,"6");
        network1.addEdge(1,5,"7");
        network1.addEdge(1,5,"8");
        network1.addEdge(1,5,"9");
        network1.addEdge(1,5,"10");

        System.out.println("新建网络---");
        System.out.println(network1);

    }


    public static void main(String[] args) {
        NetworkTest nt = new NetworkTest();
        nt.creatNetwork();
    }

}
