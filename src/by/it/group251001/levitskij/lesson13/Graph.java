package by.it.group251001.levitskij.lesson13;

import by.it.group251001.levitskij.lesson09.ListC;
public class Graph {
    private ListC<Node> edgelist = new ListC<>();

    static private class Node {
        String vertexName;
        int state;
        ListC<Node> linkvertexlist;
        Node(String Name){
            this.vertexName = Name;
            this.state = 0;
            this.linkvertexlist = new ListC<Node>();
        }
        boolean isvisited(){
            return state != 0;
        }
        void visit(){
            state = 2;
        }
        void unvisit(){
            state = 0;
        }
        void paintGray(){
            state = 1;
        }
        void paintBlack(){
            state = 2;
        }

        boolean isGray(){
            return state == 1;
        }
        boolean isWhite(){
            return state == 0;
        }
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        String delimeter = "";
        int n1 = edgelist.size();
        for(int i = 0; i < n1; i++) {
            Node currnode = edgelist.get(i);
            int n2 = currnode.linkvertexlist.size();
            for (int j = 0; j < n2; j++) {
                sb.append(delimeter+currnode.vertexName + " -> " + currnode.linkvertexlist.get(j).vertexName);
                delimeter = ", ";
            }
        }
        return sb.toString();
    }
    private Node nodeOf(String s) {
        if(s == null){
            for(int i = 0; i < edgelist.size(); i++){
                if(edgelist.get(i).vertexName==null){
                    return edgelist.get(i);
                }
            }
        } else {
            for(int i = 0; i < edgelist.size(); i++){
                if(edgelist.get(i).vertexName.equals(s)){
                    return edgelist.get(i);
                }
            }
        }
        return null;
    }
    public void addEdge(String from, String to){
        Node fromnode = nodeOf(from);
        if(fromnode == null){
            fromnode = new Node(from);
            edgelist.add(fromnode);
        }
        Node tonode = nodeOf(to);
        if(tonode == null){
            tonode = new Node(to);
            edgelist.add(tonode);
        }
        fromnode.linkvertexlist.add(tonode);
    }
    public Graph reverse(){
        Graph result = new Graph();
        int n1 = edgelist.size();
        for(int i = 0; i < n1; i++) {
            Node currnode = edgelist.get(i);
            int n2 = currnode.linkvertexlist.size();
            for (int j = 0; j < n2; j++) {
                result.addEdge(currnode.linkvertexlist.get(j).vertexName, currnode.vertexName);
            }
        }
        return result;
    }
    private void setUnviseted(){
        for(int i = 0; i < edgelist.size(); i++)
            edgelist.get(i).unvisit();
    }

    private boolean checkCycle(Node node){
        node.paintGray();
        boolean result = false;
        for(int i = 0; i < node.linkvertexlist.size(); i++){
            Node checking = node.linkvertexlist.get(i);
            if (checking.isWhite())
                result |= checkCycle(checking);
            if (checking.isGray())
                return true;
        }
        node.paintBlack();
        return result;
    }
    public boolean hasCycles(){
        if (!edgelist.isEmpty()){
            setUnviseted();
            for(int i = 0; i<edgelist.size();i++)
                if(edgelist.get(i).isWhite())
                    if(checkCycle(edgelist.get(i)))
                        return true;
        }
        return false;

    }

    private Node findmax(ListC<Node> list){///ну такое себе
        Node max = null;
        for(int i = 0; i < list.size(); i++){
            Node checking = list.get(i);
            if(!checking.isvisited() && (max==null || max.vertexName.compareTo(checking.vertexName) < 0))
                max = checking;
        }
        return max;
    }
    private ListC<String> sDFSwithMax(Node node){
        ListC<String> res = new ListC<String>();
        node.visit();
        Node checking;
        while ((checking = findmax(node.linkvertexlist))!=null)
                res.addAll(0, sDFSwithMax(checking));
        res.add(0, node.vertexName);
        return res;
    }
    private ListC<String> sDFSwithoutMax(Node node){
        ListC<String> res = new ListC<String>();
        node.visit();
        for(int i = 0; i < node.linkvertexlist.size(); i++) {
            Node checking = node.linkvertexlist.get(i);
            if (!checking.isvisited())
                res.addAll(0, sDFSwithMax(checking));
        }
        res.add(0, node.vertexName);
        return res;
    }
    public String toposort(){
        if (edgelist.isEmpty())
            return "Unable to sort: Graph is Empty";
        if (hasCycles())
            return "Unable to sort: Graph has cycles";
        StringBuilder result = new StringBuilder();
        setUnviseted();
        Node checking;
        ListC<String> dfs = new ListC<>();
        while ((checking = findmax(edgelist))!=null)
            dfs.addAll(0, sDFSwithMax(checking));
        for(int i = 0; i < dfs.size(); i++)
            result.append(dfs.get(i)+" ");
        result.delete(result.length()-1, result.length());
        return result.toString();

    }
    private void sDFS(Node node, ListC<Node> list){
        node.visit();
        for(int i = 0; i < node.linkvertexlist.size(); i++){
            Node checking = node.linkvertexlist.get(i);
            if (!checking.isvisited())
                sDFS(checking, list);

        }
        list.add(node);
    }

    public String[] findComponents(){
        if (edgelist.isEmpty()){
            String[] result = {"No Components"};
            return result;
        }
        setUnviseted();
        ListC<Node> dfs = new ListC<Node>();
        for(int i = 0; i < edgelist.size(); i++)
            if(!edgelist.get(i).isvisited())
                sDFS(edgelist.get(i), dfs);
        Graph rgraph = reverse();
        ListC<ListC<String>> res = new ListC<ListC<String>>();
        for(int i = dfs.size()-1; i > -1; i--){
            Node checking = rgraph.nodeOf(dfs.get(i).vertexName);
            if (!checking.isvisited())
                res.add(rgraph.sDFSwithoutMax(checking));
        }
        String[] result = new String[res.size()];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < res.size(); i++){
            ListC<String> currentline = res.get(i);
            for(int j = 0; j < currentline.size(); j++) {
                for(int z = currentline.size()-1; z > j; z--)
                    if (currentline.get(z).compareTo(currentline.get(z-1)) < 0){
                        String temp = currentline.get(z);
                        currentline.set(z, currentline.get(z-1));
                        currentline.set(z-1, temp);
                    }
                sb.append(currentline.get(j));
            }
            result[i] = sb.toString();
            sb.delete(0, sb.length());
        }
        return result;
    }

}
