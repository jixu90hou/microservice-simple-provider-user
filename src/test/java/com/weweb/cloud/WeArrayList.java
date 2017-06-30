package com.weweb.cloud;

import java.util.*;

/**
 * Created by jackshen on 2017/6/11.
 */
public class WeArrayList<T> implements List<T> {
    private Object[] initArray;
    private Object[] handleArray=new String[0];
    public WeArrayList() {
        initArray = new Object[0];

    }
    public WeArrayList(int capacity){
        initArray=new String[capacity];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
    @Override
    public boolean add(T element) {
        //initArray
        int index=findArrayIndex();
        initArray[index]=element;
        handleArray=Arrays.copyOf(initArray,index+1);
        return handleArray[index]!=null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public void print(){
        for(int i=0;i<handleArray.length;i++){
            System.out.println("i--->"+handleArray[i]);
        }
    }
    private class WeArrayListIterator<T> implements ListIterator<T>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(T t) {

        }

        @Override
        public void add(T t) {

        }
    }
    private int findArrayIndex(){
        if(initArray.length==0){
            increaseCapacity();
        }
        for(int i=initArray.length-1;i>=0;i--){
            if(initArray[i]!=null){
                if(i==initArray.length-1)//todo need increase capacity
                {
                    increaseCapacity();
                }
                return i+1;
            }
        }
        return 0;
    }
    private void increaseCapacity(){
        initArray=Arrays.copyOf(initArray,initArray.length*2+1);

    }
    public static void main(String[] args) {
        WeArrayList<String> weArrayList=new WeArrayList(16);
        weArrayList.add("zhang");
        weArrayList.add("li");
        weArrayList.add("mang");
        weArrayList.add("mang222");
        weArrayList.add("mang222");
        weArrayList.add("mang222");
    /*    for(String weEntity:weArrayList){
            System.out.println("content:"+weEntity);
        }*/

        weArrayList.print();
       /* String[] aa=new String[1];
        aa[0]="00";
        Arrays.stream(aa).forEach(s -> System.out.println(s));
        String[] bb=Arrays.copyOf(aa,aa.length+2);
        bb[1]="11";
        bb[2]="22";
        Arrays.stream(bb).forEach(s -> System.out.println(s));*/
    }

}