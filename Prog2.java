import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Prog2 {
    private static int KruskalCost = 0;
    private static int PrimCost = 0;
    private static Set PrimMCST = new HashSet();
    private static Set KruskalMCST = new HashSet();

    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Please enter file path/name : ");
        String fileName = myScanner.nextLine();


        try{
            File file = new File(fileName);
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);
            /*String line = bReader.readLine();
            String[] strArray = line.split("\\s+");
            int vertices = Integer.parseInt(strArray[0]);
            int edges = Integer.parseInt(strArray[1]);
             */

            long startTime = System.nanoTime();
            Prim(bReader);
            long endTime = System.nanoTime();
            double durationPrim = (double) ((endTime - startTime) / 1000000.0);

            fReader.close();
            fReader = new FileReader(file);
            bReader = new BufferedReader(fReader);

            startTime = System.nanoTime();
            Kruskal(bReader);
            endTime = System.nanoTime();

            double durationKruskal = (double) ((endTime - startTime) / 1000000.0);

            System.out.println(durationKruskal);


        }

        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void Prim(BufferedReader reader){
        int u, v, w;
        String line;
        String[] lineArr;
        EdgeNode[] AdjList = new EdgeNode[0];
        HashSet S = null;
        HashSet VminusS = null;
        int vertices = 0;
        int edges;


       /* S.add(AdjList[0]);
        for(int i = 1; i < vSize; i++){
            VminusS.add(AdjList[i]);
        }*/

        try{
            line = reader.readLine();
            lineArr = line.split("\\s+");
            vertices = Integer.parseInt(lineArr[0]);
            edges = Integer.parseInt(lineArr[1]);

            AdjList = new EdgeNode[vertices];
            for(int i = 0; i < vertices; i++){
                AdjList[i] = null;
            }
            S = new HashSet<>();
            VminusS = new HashSet<>();

            for(int i = 0; i < edges; i++) {
                line = reader.readLine();
                lineArr = line.split("\\s+");
                u = Integer.parseInt(lineArr[0]);
                v = Integer.parseInt(lineArr[1]);
                w = Integer.parseInt(lineArr[2]);
                EdgeNode temp = new EdgeNode(u, v, w);
                temp.setNext(AdjList[u]);
                AdjList[u] = temp;
                EdgeNode temp2 = new EdgeNode(v, u, w);
                temp2.setNext(AdjList[v]);
                AdjList[v] = temp2;

                if(u == 0){
                    S.add(u);
                }
                else {
                    VminusS.add(u);
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }



        PriorityQueue<EdgeNode> PQ = new PriorityQueue<>();
        int curvertex = 0;

        EdgeNode temp = AdjList[curvertex];
        while(temp != null){
            PQ.add(temp);
            temp = temp.getNext();
        }

        EdgeNode min;

        while((PQ.size() > 0) && (PrimMCST.size() < vertices-1)){
            min = PQ.poll();
            int x = min.getVertex1();
            if(!S.contains(x)){
                S.add(x);
                PrimMCST.add(min);
                PrimCost += min.getWeight();
                VminusS.remove(min);

                temp = AdjList[x];
                while(temp != null){
                    if(!S.contains(temp.getVertex1())){
                        PQ.add(temp);
                    }
                    temp = temp.getNext();
                }
            }
        }



    }

    public static void Kruskal(BufferedReader reader){
        int u, v, w;
        String line;
        String[] lineArr;
        UnionFind UF = null;
        int vertices = 0;
        int edges = 0;
        PriorityQueue<EdgeNode> PQ = new PriorityQueue<>();

        try{
            line = reader.readLine();
            lineArr = line.split("\\s+");
            vertices = Integer.parseInt(lineArr[0]);
            edges = Integer.parseInt(lineArr[1]);

            UF = new UnionFind(vertices);

            for(int i = 0; i < edges; i++) {
                line = reader.readLine();
                lineArr = line.split("\\s+");
                u = Integer.parseInt(lineArr[0]);
                v = Integer.parseInt(lineArr[1]);
                w = Integer.parseInt(lineArr[2]);
                EdgeNode temp = new EdgeNode(u, v, w);
                PQ.add(temp);
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        while((PQ.size() > 0) && (KruskalMCST.size() < vertices-1)){
            EdgeNode E = PQ.poll();
            int x = E.getVertex1();
            int y = E.getVertex2();

            if(UF.Find(x) != UF.Find(y)){
                KruskalMCST.add(E);
                KruskalCost += E.getWeight();
                UF.Union(x,y);
            }
        }
    }
}
