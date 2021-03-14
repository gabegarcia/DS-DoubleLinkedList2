/**
 * Project - DoubleLinkedList JUnit Testing - implement a double linked list
 * Author - Gabe Garcia
 * Date - 3/14/2021
 * version 1
 *  
 */

package edu.miracosta.cs113;
//import java.util.*;
import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;



public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;    // the number of items in the list
  
  @Override
  public void add(int index, E obj)
  { // Fill Here 
	listIterator(index).add(obj);
	
   }
  public void addFirst(E obj) { // Fill Here 
	  add(0, obj); //Professor suggestion
	  //head = new Node<E>(obj);
	  //size++;
  }
  public void addLast(E obj) { // Fill Here
	  add(size, obj); //Professor code
  }
  
@Override
  public E get(int index) 
  { 	ListIterator iter = new ListIter(index); 
      	if(!(iter.hasNext())) {
      		throw new IndexOutOfBoundsException();
      	}
	  return listIterator(index).next();
      	
  }
    
  public E getFirst() { return head.data;  }
  public E getLast() { return tail.data;  }

  public int size() {  return size;  } // Fill Here

  public E remove(int index)
  {     E returnValue = null;
        ListIterator<E> iter = listIterator(index);
        if (iter.hasNext())
        {   returnValue = iter.next();
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;
  }
  
  /*
  @Override
  public String toString() {
	  if(size == 0) {
		  return "[]";
	  } else {
		  Node<E> nodeRef = head;
		  StringBuilder result = new StringBuilder();
		  while(nodeRef != null) {
			  result.append(nodeRef.data);
			  if(nodeRef.next != null) {
				  result.append(", ");
			  }
			  nodeRef = nodeRef.next;
		  }
		  		  
		 return "["+result.toString()+"]"; //Adding brackets [] because the test expects additional set
		 }
	
  }
  */
/*
  public void clear() {
	  	  
	  while(!isEmpty()){
		  
		  remove(size - 1);
		  size--;
	  }
  }*/
  //Added <E> based on Professor's code
  public Iterator<E> iterator() { return new ListIter(0);  }
  public ListIterator<E> listIterator() { return new ListIter(0);  }
  public ListIterator<E> listIterator(int index){return new ListIter(index);}
  public ListIterator<E> listIterator(ListIterator iter)
  {     return new ListIter( (ListIter) iter);  }

  // Inner Classes
  private static class Node<E>
  {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)  //constructor
        {   data = dataItem;   }
  }  // end class Node

  public class ListIter implements ListIterator<E> 
  {
        private Node<E> nextItem;      // reference to the next node
        private Node<E> lastItemReturned;   // reference to the last item returned
        private int index = 0;   // 

    public ListIter(int i)  // constructor for ListIter class
    {   if (i < 0 || i > size)
        {     throw new IndexOutOfBoundsException("Invalid index " + i); }
        lastItemReturned = null;
 
        if (i == size)     // Special case of last item
        {   index = size;     
        	nextItem = null;      
        } else          // start at the beginning
        {   nextItem = head;
            for (index = 0; index < i; index++)  nextItem = nextItem.next;   
        }// end else
    }  // end constructor

    public ListIter(ListIter other)
    {   nextItem = other.nextItem;
        index = other.index;    }
    
    @Override
    public boolean hasNext() {   
    	//System.out.println(nextItem);
    	if(nextItem != null) {
    		return true;
    	} else
    		return false;    
    	//Professor Code...
    	
    	//return nextItem != null;
    }
    // Fill Here
    @Override
    public boolean hasPrevious(){
    	if(size == 0) 
    		return false;
    	   	
    	return (nextItem == null && size != 0)
    			|| nextItem.prev != null;   
    	} // Fill Here
    	

    public int previousIndex() {  
    	if(!hasPrevious()) {
    		//throw new NoSuchElementException();
    		return -1;
    		}
    	if(nextItem == null) {
    		nextItem = tail;
    	} else {
    		nextItem = nextItem.prev;
    	}
    	lastItemReturned = nextItem;
    	index--;
    	return index;
    	}
    	
    	//return 0;    } // Fill Here
    public int nextIndex() {  //Why is this called nextIndex? Is it returning the ListIter index?
    	/*if(!hasNext()) {
    		throw new NoSuchElementException();
    	} else 
    		index++;*/
    	return index; 
    	
    	//return 0;
    	} // Fill here
    @Override
    public void set(E o)  {
    	/*if(isEmpty()) {
    		add(o);
    	}*/
    	if (lastItemReturned == null) {
            throw new IllegalStateException();
        } else
        	lastItemReturned.data = o;
    	
    	
    }  // not implemented
    @Override
    public void remove(){
    	if (lastItemReturned == null) {
            throw new IllegalStateException();
        }
        // Unlink this item from its next neighbor
        if (lastItemReturned.next != null) {
            lastItemReturned.next.prev = lastItemReturned.prev;
        } else { // Item is the tail
            tail = lastItemReturned.prev;
            if (tail != null) {
                tail.next = null;
            } else { // list is now empty //But the head could be equal to tail?
                head = null;
            }
        }
        // Unlink this item from its prev neighbor
        if (lastItemReturned.prev != null) {
            lastItemReturned.prev.next = lastItemReturned.next;
        } else { // Item is the head
            head = lastItemReturned.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        }
        // Invalidate lastItemReturned
        lastItemReturned = null;
        // decrement both size and index
        size--;
        index--;
    	
    }      // not implemented

    @Override
    public E next()
    {  /*if(this.nextItem != null)
        return nextItem.data; // Fill Here 
     else
    	return nextItem.data;*/
    	//Professor Code...
    	if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastItemReturned = nextItem;
        nextItem = nextItem.next;
        index++;
        return lastItemReturned.data;
  	}
    
    public E previous() 
    {  
    	if(!hasPrevious()) {
    		throw new NoSuchElementException();
    	} 
    	if(nextItem == null) {
    		nextItem = tail;
    	} else {
    		nextItem = nextItem.prev;
    	}
    	lastItemReturned = nextItem;
    	index--;
    	return lastItemReturned.data; // Fill Here 
    }

    public void add(E obj) {

    // Fill Here
    	if(head == null) {
    		head = new Node<E>(obj);
    		tail = head;
    		//size++;
    	
    	} else if (nextItem == head) {
    		Node<E> newNode = new Node<E>(obj); //Create a new Node
    		newNode.next = nextItem; //Link it to the nextItem
    		nextItem.prev = newNode; //Link nextItem to the new Node
    		head = newNode; //The new Node is now the head
    		//size++;
    	} else if(nextItem == null) {
    		Node<E> newNode = new Node<E>(obj); //Create a new Node
    		tail.next = newNode; //Link the tail to the new Node
    		newNode.prev = tail; //Link the new Node to the tail
    		tail = newNode; //The new Node is now the tail
    		//size++;
    	} else {
    		Node<E> newNode = new Node<E>(obj);//Create a new Node
    		newNode.prev = nextItem.prev; //link it to nextItem.prev
    		nextItem.prev.next = newNode;
    		newNode.next = nextItem; //link it to the nextItem
    		nextItem.prev = newNode;
    		//size++;
    	}
    	size++;
    	index++; //Professor code
    	lastItemReturned = null; //Professor code
    	
    }
  }// end of inner class ListIter
}// end of class DoubleLinkedList
