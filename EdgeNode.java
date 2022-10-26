public class EdgeNode implements Comparable<EdgeNode>{
    public int vertex1, vertex2, weight;
    public EdgeNode next;

    public EdgeNode(int vertex1, int vertex2, int weight){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
        this.next = null;
    }

    public void setNext(EdgeNode n){
        this.next = n;
    }

    public EdgeNode getNext(){
        return this.next;
    }

    public void setVertex1(int vertex1){
        this.vertex1 = vertex1;
    }

    public int getVertex1(){
        return this.vertex1;
    }

    public void setVertex2(int vertex2){
        this.vertex2 = vertex2;
    }

    public int getVertex2(){
        return this.vertex2;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getWeight(){
        return this.weight;
    }

    @Override
    public int compareTo(EdgeNode n) {
        if(this.weight < n.weight){
            return -1;
        }
        else if(this.weight > n.weight){
            return 1;
        }
        else{
            return 0;
        }
    }
}
