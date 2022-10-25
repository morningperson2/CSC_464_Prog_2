public class UnionFind {
    private int[] parent;

    public UnionFind(int size){
        parent = new int[size];
        for(int i = 0; i < size; i++){
            parent[i] = i;
        }
    }

    public int Find(int x){
        if(x == parent[x]){
            return x;
        }
        return parent[x] = Find(parent[x]);
    }

    public void Union(int x, int y){
        int px = Find(x);
        int py = Find(y);
        if(px != py){
            parent[px] = py;
        }
    }

    public int Size(){
        int ans = 0;
        for(int i = 0; i < parent.length; i++){
            if(i == parent[i])ans++;
        }
        return ans;
    }
}
