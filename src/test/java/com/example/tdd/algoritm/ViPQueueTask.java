package com.example.tdd.algoritm;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ViPQueueTask {
    @Test
    void should_add_and_remove() {
        var vipQueue = new VipQueue();
        vipQueue.add("1", true);
        assertEquals(1, vipQueue.size);
        assertEquals("1", vipQueue.get());
        assertEquals(0, vipQueue.size);
    }

    @Test
    void should_add_two_and_remove() {
        var vipQueue = new VipQueue();
        vipQueue.add("1", true);
        vipQueue.add("2", true);
        assertEquals(2, vipQueue.size);
        assertEquals("1", vipQueue.get());
        assertEquals(1, vipQueue.size);
        assertEquals("2", vipQueue.get());
        assertEquals(0, vipQueue.size);
    }

    @Test
    void should_add_three_and_remove() {
        var vipQueue = new VipQueue();
        vipQueue.add("1", true);
        vipQueue.add("2", true);
        vipQueue.add("3", true);

        //1
        //21
        //231
        assertEquals("1", vipQueue.get());
        assertEquals(2, vipQueue.size);
        assertEquals("3", vipQueue.get());
        assertEquals(1, vipQueue.size);
        assertEquals("2", vipQueue.get());
        assertEquals(0, vipQueue.size);

        assertEquals(0, vipQueue.size);
    }

    @Test
    void should_add_four_and_remove() {
        var vipQueue = new VipQueue();
        vipQueue.add("1", true);
        vipQueue.add("2", true);
        vipQueue.add("3", true);
        vipQueue.add("4", true);

        //1
        //21
        //231
        //2431

        //123 m2
        //4123 m2
        //54123 m1
        //654123 m1
        assertEquals("1", vipQueue.get());
        assertEquals(3, vipQueue.size);
        assertEquals("3", vipQueue.get());
        assertEquals(2, vipQueue.size);
        assertEquals("4", vipQueue.get());
        assertEquals(1, vipQueue.size);
        assertEquals("2", vipQueue.get());
        assertEquals(0, vipQueue.size);
        assertEquals(0, vipQueue.size);
    }

    @Test
    void should_add_vip_in_middle() {
        var vipQueue = new VipQueue();
        vipQueue.add("1", true);
        vipQueue.add("2", true);
        vipQueue.add("3", true);
        vipQueue.add("4", true);
        // 2431
        assertEquals("1", vipQueue.get());
        assertEquals("3", vipQueue.get());
        assertEquals("4", vipQueue.get());
        assertEquals("2", vipQueue.get());
    }

    @Test
    void should_add_nonvip_in_begin() {
        var vipQueue = new VipQueue();
        vipQueue.add("1", true);
        vipQueue.add("2", true);
        vipQueue.add("3", true);
        vipQueue.add("4", true);
        vipQueue.add("5", false);
        vipQueue.add("6", false);
        // 2431
        assertEquals("1", vipQueue.get());
        assertEquals("3", vipQueue.get());
        assertEquals("4", vipQueue.get());
        assertEquals("2", vipQueue.get());
        assertEquals("5", vipQueue.get());
        assertEquals("6", vipQueue.get());
    }

    @Test
    void should_add_nonvip_in_begin2() {
        var vipQueue = new VipQueue();
        vipQueue.add("1", true);
        System.out.println(vipQueue.toString());

        vipQueue.add("2", true);
        System.out.println(vipQueue.toString());

        vipQueue.add("3", true);
        System.out.println(vipQueue.toString());

        vipQueue.add("4", true);
        System.out.println(vipQueue.toString());

        vipQueue.add("5", false);
        System.out.println(vipQueue.toString());

        vipQueue.add("6", false);
        System.out.println(vipQueue.toString());

        vipQueue.add("7", true);

        System.out.println(vipQueue.toString());
        // 6527431
        //13
        //124   m2
        //1234 m3
        // 12345 m3

        assertEquals("1", vipQueue.get());
        assertEquals("3", vipQueue.get());
        assertEquals("4", vipQueue.get());
        assertEquals("7", vipQueue.get());
        assertEquals("2", vipQueue.get());
        assertEquals("5", vipQueue.get());
        assertEquals("6", vipQueue.get());
    }


    @Test
    void should_null_if_empty() {
        var vipQueue = new VipQueue();
        assertNull(vipQueue.get());
    }
}

class VipQueue{

    Node middle;
    Node begin;
    Node end;
    int size = 0;




    public void add(String id, boolean isVip) {
        if(isVip){
            addToMiddle(new Node(id, isVip));
        } else {
            addToBegin(new Node(id,isVip));
        }
    }

    public String get() {
        if(size==0){
            return null;
        }
        return removeFromEnd().id;
    }

    private Node removeFromEnd() {
        Node res = null;
        if(begin==null){
            return res;
        } else if(end==null){
             res= begin;
            begin = null;
         } else if(middle==null){
             res = end;
            end = end.previous;
        } else {
            //1234567 m4
            //123456 m4
            //12345 m3
            //1234 m3
             res = end;
            end = end.previous;
            if(size%2==0){
                middle = middle.previous;
            }
        }
        size--;
        return res;
    }

    private Node removeFromBegin() {
        if(size==0){
            return null;
        }
        var removed = begin;
        begin = begin.next;
        size--;
        return removed;
    }

    private void addToBegin(Node newNode){
     if(begin==null){
         begin=newNode;
     } else if(end ==null){
         end = begin; end.previous=newNode;
         begin = newNode;
         begin.next = end;
     } else if(middle==null){
         //2
         middle=begin;
         middle.previous = newNode;
         begin = newNode;
         begin.next = middle;
        } else {
         // 123 m2   in before 2
         // 4123 m2
         // 54321 m3
         if(size%2==0){
             middle = middle.previous;
         }
         var temp = begin;
         begin = newNode;
         newNode.next = temp;
         temp.previous = newNode;
     }
        size++;
    }

    private void addToMiddle(Node newNode) {
       if(begin==null || end==null){
           addToBegin(newNode);
       } else if(middle==null){
           middle = newNode;
           newNode.previous = begin;
           newNode.next = end;
           end.previous = newNode;
           begin.next = newNode;
           size++;
       } else {
           //123 m2   in before 2
           //1423 m2 in before 2
           //always brfore 2
           //15423 m4
           //m shift size%2==0;
           var temp = middle.previous;
           temp.next=newNode;
           middle.previous = newNode;
           newNode.next = middle;
           newNode.previous = temp;
           if(size%2==0){
               middle = middle.previous;
           }
           size++;

       }

    }

    private void addToEnd(Node newNode){
        newNode.previous=end;
        end.next = newNode;
        middle = middle.next;
        size++;
    }

    public String toString() {
        var stringJoiner = new StringJoiner(",");
        var cur = begin;
        while (cur != null) {
            stringJoiner.add(cur.id);
            cur = cur.next;
        }
        return stringJoiner.toString();
    }
}

class Node{
    Node previous;
    Node next;
    String id;
    boolean isVip;

    public Node(String id, boolean isVip, Node next, Node previous) {
        this.id = id;
        this.isVip = isVip;
        this.next = next;
        this.previous = previous;
    }

    public Node(String id, boolean isVip) {
        this.id = id;
        this.isVip = isVip;
    }
}
