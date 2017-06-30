package com.weweb.cloud;

/**
 * Created by jackshen on 2017/6/11.
 */
public class Node {
    private Node previous;
    private Node next;
    private Object data;

 /*   public void add(Object data){
        next.data=data;
        next=new Node();
    }*/
    public Node(Object data){
        this.data=data;
    }
    public void printNext(Node node){
        System.out.println(node.data);
        if(node.next!=null)
            printNext(node.next);
    }
    public void printPrevious(Node node){
        System.out.println(node.data);
        if(node.previous!=null)
            printPrevious(node.previous);
    }

    public Node addNext(Node node){
        this.next=node;
        return this.next;
    }
    public Node addPrevious(Node node){
        this.previous=node;
        return this.previous;
    }

    public static void main(String[] args) {
        Node root=new Node("火车头");
        Node node1=new Node("第一节火车");
        Node node2=new Node("第二节火车");
        Node node3=new Node("第三节火车");
        Node root1=new Node("火车头1");
        Node root2=new Node("火车头2");
        Node root3=new Node("火车头3");

        root.addNext(node1).addNext(node2).addNext(node3);
        root.addPrevious(root1).addPrevious(root2).addPrevious(root3);

        root.printNext(root);
        root.printPrevious(root);

     /*  Node root2= new Node("火车头").addNext(new Node("第一节火车")).addNext(new Node("第二节火车")).addNext(new Node("第三节火车"));
        root2.print(root2);*/
    }
}
