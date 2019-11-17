package ua.nure.leonov.practice2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

public class Demo {
    public static void main(String[] args) {
        ArrayImpl list = new ArrayImpl();
// [A, A2]
        list.add("A");
        list.add("A2");
        System.out.println(list);
// []
        list.clear();
        System.out.println(list);
// [A, A3]
        list.add("A");
        list.add("A2");
        list.add("A3");
        list.remove(2);
        System.out.println(list);
// AA3

// 2
        System.out.println(list.size());


        System.out.println("==== Part2");
        list = new ArrayImpl();
        list.add(1);
        list.add(2);
        list.add(3);
// 1 2 3 4
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
// [1, 3, 4]
        it = list.iterator();
        it.next();
        it.next();
        it.remove();
        System.out.println(list);
// 3
        System.out.println(it.next());
// [1, 4]
        it.remove();
        System.out.println(list);
// class java.lang.IllegalStateException
        try {
            it.remove();
        } catch (IllegalStateException ex) {
            System.out.println(ex.getClass());
        }

        System.out.println("==== Part3");
        list = new ArrayImpl();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
// 1 2 3 4
////        ListIterator lit = (ListIterator) (list).iterator();
////        while (lit.hasNext()) {
////            System.out.print(lit.next() + " ");
////        }
////        System.out.println();
////// 4 3 2 1
////        while (lit.hasPrevious()) {
////            System.out.print(lit.previous() + " ");
////        }
////        System.out.println();
////        list = new ArrayImpl();
////        lit = (ListIterator) list.iterator();
////// false
////        System.out.println(lit.hasNext());
//// false
//        System.out.println(lit.hasPrevious());
//// Elemenet
//        list.add("Element");
//        System.out.println(lit.next());
//// false
//        System.out.println(lit.hasNext());
//// true
//        System.out.println(lit.hasPrevious());


        ListImpl linked = new ListImpl();


        linked.addLast(3);
        linked.addLast(4);
        linked.addLast(5);

        System.out.println("LINKED");


        System.out.println("FOREACH");
        for (Object aList : linked) {
            System.out.print(aList);
        }
        System.out.println();

        System.out.println("ITERATOR");

//        ListIterator itLinked = (ListIterator) (linked).iterator();
//        while (itLinked.hasNext()) {
//            System.out.print(itLinked.next() + " ");
//        }
        System.out.println();


        QueueImpl queue = new QueueImpl();

        queue.enqueue(1);
        queue.enqueue(2);

        System.out.println("QUEUE");
        for (Object el: queue) {
            System.out.print(el);
        }
        System.out.println();

        Iterator<Object> itQue = queue.iterator();
        while (itQue.hasNext()) {
            System.out.print(itQue.next() + " ");
        }
        System.out.println();


        System.out.println("STACK");
        StackImpl stack = new StackImpl();

        stack.push(1);
        stack.push(2);
        stack.push(3);



        System.out.println(stack);

        Iterator<Object> itSt = stack.iterator();
        while (itSt.hasNext()) {
            System.out.print(itSt.next() + " ");
        }
        System.out.println();

        Stack<Object> objects = new Stack<>();
        System.out.println(objects);

        itSt = stack.iterator();
        itSt.next();
        itSt.remove();

        itSt.next();
        itSt.remove();

        itSt.next();
        itSt.remove();


    }
}
