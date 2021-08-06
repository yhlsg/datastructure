package com.yhl.graph;

import sun.misc.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图的创建
 * @author yhl
 * @create 2021-08-06 9:35
 */
public class graph {

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻结矩阵
    private int numOfEdge;//边的个数
    private boolean[] isVisited;//记录结点是否访问过

    public static void main(String[] args) {
        //创建图
        String[] vertexArr = {"A", "B", "C", "D", "E"};
        int n = 5;//图顶点的个数
        graph graph = new graph(5);
        //向图中添加顶点
        for (String vertex : vertexArr){
            graph.addVertex(vertex);
        }

        //向图中添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        //输出邻结矩阵
        graph.showGraph();

        //深度优先遍历图
        graph.dfs();
        System.out.println();
        graph.bfs();
    }

    public graph(int n) {
        vertexList = new ArrayList<String>(n);
        edges = new int[n][n];
        numOfEdge = 0;
    }

    //广度优先遍历的重载
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        //遍历所有顶点，对于没有访问过的顶点进行广度优先遍历
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }

    //广度优先遍历
    private void bfs(boolean[] isVisited, int v1){
        int u;//当前顶点
        int w;//该顶点的邻接点

        //创建一个队列来存储要遍历邻接点的顶点
        LinkedList<Integer> queue = new LinkedList<>();

        //将当前顶点添加到队列中
        queue.addLast(v1);

        //输出当前顶点
        System.out.print(getVertex(v1) + "->");
        //标记该顶点已经访问过了
        isVisited[v1] = true;

        //将队列中的所有数据遍历
        while (!queue.isEmpty()){
            //从队列中取出顶点
            u = queue.removeFirst();
            //获取该顶点的第一个邻接点
            w = getFirstNeighbor(v1);

            //遍历该顶点的所有邻接点
            while (w != -1){
                //没有被访问过
                if (!isVisited[w]){
                    //输出该邻接点
                    System.out.print(getVertex(w) + "->");
                    //标记该邻接点
                    isVisited[w] = true;
                    //将该邻接点入队列
                    queue.addLast(w);
                }

                //输出u顶点的其他邻接点
                w = getNextNeighbor(u, w);
            }
        }


    }

    //深度优先遍历的重载
    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        //由于可能存在非连通图，所以要堆每个顶点进行深度优先遍历
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]){
                dfs(isVisited, i);
            }
        }

    }

    /**
     * 深度优先遍历图
     * @param isVisited 记录各个顶点是否被访问过的数组
     * @param v1 当前顶点
     */
    private void dfs(boolean[] isVisited, int v1){
        //先输出当前顶点
        System.out.print(vertexList.get(v1) + "->");

        //将访问的顶点标记
        isVisited[v1] = true;

        //寻找第一个邻接点
        int fv = getFirstNeighbor(v1);
        //顶点有第一个邻接点，就一直向下递归
        while (fv != -1){
            //判断v1的第一个邻接点是否被访问过
            if (!isVisited[fv]){
                dfs(isVisited, fv);
            }

            //上一个邻接点被访问后或者如果上一个邻接点已经被访问过了，继续遍历后面的邻接点
            fv = getNextNeighbor(v1, fv);
        }

    }

    /**
     * 根据前一个邻接点来获取下一个邻接点
     * @param v1 当前顶点的下标
     * @param v2 前一个邻接点的下标
     * @return 找到返回下一个邻接点的下标，没找到返回-1
     */
    public int getNextNeighbor(int v1, int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            //不为0，则代表两个顶点之间有连接线
            if (edges[v1][i] > 0){
                return i;
            }
        }

        return -1;
    }

    /**
     * 获取第一个邻接结点的下标
     * @param index 该结点的下标
     * @return 找到返回第一个邻接点的下标，没找到返回-1
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0){
                return i;
            }
        }

        return -1;
    }

    //返回v1 v2 的权值
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    //获取指定下标所对应的结点
    public String getVertex(int i){
        return vertexList.get(i);
    }

    //获取边的个数
    public int getNumOfEdge(){
        return numOfEdge;
    }

    //显示整个邻阶矩阵
    public void showGraph(){
        for (int[] arr : edges){
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 添加边
     * @param v1 一个顶点在邻阶矩阵所对应的下标
     * @param v2 另一个顶点在邻阶矩阵所对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdge++;
    }

    //添加顶点
    public void addVertex(String vertex){
        vertexList.add(vertex);
    }

}
