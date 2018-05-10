/*
  Joan Chirinos, Mohtasim Howlader, Kaitlin Wan
  Lab03
  APCS2 pd08
*/

import java.util.ArrayList;
import java.lang.RuntimeException;

public class ArrayPriorityQueue implements PriorityQueue {

  ArrayList<String> _data;

  public ArrayPriorityQueue() {
    _data = new ArrayList<String>();
  }


  //sorts queue based on priority upon adding, sort of like binary search
  //O(logn)
  public void add(String toAdd) {
    if (size() == 0 || _data.get(size() - 1).compareTo(toAdd) < 0) {
      _data.add(toAdd);
    }
    else _data.add(whereToAdd(toAdd), toAdd);
  }

  private int whereToAdd(String toAdd) {
    int left = 0;
    int right = size();
    int mid = (left + right) / 2;
    while (left < right) {
      mid = (left + right) / 2;
      if (_data.get(mid).compareTo(toAdd) < 0 && _data.get(mid + 1).compareTo(toAdd) > 0) {
        return mid + 1;
      }
      if (_data.get(mid).compareTo(toAdd) < 0) {
        left = mid + 1;
      }
      else right = mid;
    }
    return mid;
  }

  //O(1)
  public boolean isEmpty() {
    return this.size() == 0;
  }

  //O(1)
  public String peekMin() {
    if (size() == 0) {
      throw new RuntimeException();
    }
    return _data.get(size() - 1);
  }

  //O(1)
  public String removeMin() {
    if (size() == 0) {
      throw new RuntimeException();
    }
    return _data.remove(size() - 1);
  }

  public int size() {
    return _data.size();
  }

  public String toString() {
    return "END: " + _data.toString() + " :FRONT";
  }

  public static void main(String[] args) {
    ArrayPriorityQueue apq = new ArrayPriorityQueue();
    System.out.println("apq: " + apq);
    apq.add("hoo");
    System.out.println("Adding hoo");
    System.out.println("apq: " + apq);
    apq.add("boo");
    System.out.println("Adding boo");
    System.out.println("apq: " + apq);
    apq.add("doo");
    System.out.println("Adding doo");
    System.out.println("apq: " + apq);
    System.out.println("removing min: " + apq.peekMin());
    apq.removeMin();
    System.out.println("apq: " + apq);
    apq.add("zoo");
    System.out.println("Adding zoo");
    System.out.println("apq: " + apq);
    apq.add("aoo");
    System.out.println("Adding aoo");
    System.out.println("apq: " + apq);
  }

}//end class
