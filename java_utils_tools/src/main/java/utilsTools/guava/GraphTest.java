package utilsTools.guava;

import com.google.common.graph.*;
import org.springframework.stereotype.Component;
import utilsTools.module.GraphNode;

import java.util.Set;


/**
 * guava是google基于jdk类库集合开发的扩展项目，是属于java类库的一部分
 * 包括包括 collections, caching, primitives support, concurrency libraries,
 * common annotations, string processing, I/O, 等等高效API.
 * Guava简介：  https://www.cnblogs.com/senlinyang/p/8092742.html
 */

@Component
public class GraphTest {

    private Integer NODE_COUNT = 10;

    public void creatGraph() {
        GraphNode gn = new GraphNode();
        GraphNode gn1 = gn;
        GraphNode gn2 = gn;

        Integer gI1 = 1;
        Integer gI2 = 2;

        MutableGraph<GraphNode> graph = GraphBuilder.directed().build(); // 创建图
        //graph.putEdge(gn1,gn2);
        //System.out.println(graph);


        MutableGraph<Integer> graph1 = GraphBuilder.directed() //指定为有向图
                .nodeOrder(ElementOrder.<Integer>insertion()) //节点按插入顺序输出
                //(还可以取值无序unordered()、节点类型的自然顺序natural())，指定会影响节点的输出
                .expectedNodeCount(NODE_COUNT) //预期节点数
                .allowsSelfLoops(true) //允许自环
                .build(); // 构建图，返回ConfigurableMutableGraph(可配置的可变图)的子图，不一定是连通图
        /*
         * 使用Builder类构建的实例都是Mutable类型的，表示这个Graph可以增删节点和边，与之对应的是Immutable类型，
         * 一般通过copyOf()的静态函数实现，表示该类型是不可变类型（不能增加/删除节点和边）
         * */

        graph1.putEdge(gI1, gI2);
        graph1.putEdge(3, 4);
        graph1.putEdge(4, 3); // 有环
        graph1.putEdge(4, 8);
        graph1.putEdge(4, 4); // 有自环
        System.out.println(graph1);

        graph1.addNode(5);

        // 返回图中所有的节点集合(顺序依赖nodeOrder)
        Set<Integer> nodes = graph1.nodes(); // 按节点的插入顺序
        System.out.println(nodes);

        // 返回图中所有的边集合
        Set<EndpointPair<Integer>> edges = graph1.edges();
        System.out.println(edges);

        // 获取图的传递闭包
        Graph<Integer> graph2 = Graphs.transitiveClosure(graph1);
        System.out.println("图的传递闭包---");
        System.out.println(graph2);

        // 有向图的反转图
        Graph<Integer> graph3 = Graphs.transpose(graph1);
        System.out.println("有向反转图----边的指向反转---");
        System.out.println(graph3);

/*        //深度优先-后序
        Iterable<Integer> dfs = Traverser.forGraph(graph1).depthFirstPostOrder(N1);
        System.out.println(dfs);

        //深度优先-前序
        Iterable<Integer> dfsPre =Traverser.forGraph(graph1).depthFirstPreOrder(N1);
        Log.d(TAG, "dfs pre traverser: " + format(dfsPre));

        //广度优先
        Iterable<Integer> bfs =Traverser.forGraph(graph1).breadthFirst(N1);
        Log.d(TAG, "bfs traverser: " + format(bfs));*/


    }


/*        //UndirectedGraphConnections无向图
        public void addPredecessor(Integer node, Integer value) { //添加前趋
                Integer unused = addSuccessor(node, value); //直接调用了添加后继方法
        }

        public Integer addSuccessor(Integer node, Integer value) { //添加后继
                return adjacentNodeValues.put(node, value); //直接将node和value的映射关系添加到Map中
        }*/




    /*// Creating mutable graphs
    MutableGraph<Integer> graph = GraphBuilder.undirected().build();

    MutableValueGraph<City, Distance> roads = ValueGraphBuilder.directed()
            .incidentEdgeOrder(ElementOrder.stable())
            .build();

    MutableNetwork<Webpage, Link> webSnapshot = NetworkBuilder.directed()
            .allowsParallelEdges(true)
            .nodeOrder(ElementOrder.natural())
            .expectedNodeCount(100000)
            .expectedEdgeCount(1000000)
            .build();

    // Creating an immutable graph
    ImmutableGraph<Country> countryAdjacencyGraph =
            GraphBuilder.undirected()
                    .<Country>immutable()
                    .putEdge(FRANCE, GERMANY)
                    .putEdge(FRANCE, BELGIUM)
                    .putEdge(GERMANY, BELGIUM)
                    .addNode(ICELAND)
                    .build();*/


    public static void main(String[] args) {
        GraphTest gt = new GraphTest();
        gt.creatGraph();
    }


}

