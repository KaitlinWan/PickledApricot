/*
PickledApricot - Joan Chirinos, Mohtasim Howlader, Kaitlin Wan
APCS2 pd08
HW50 -- Run Run Run
2018-05-15
*/

/*****************************************************
* class ALHeapMin
* Implements a min heap using an ArrayList as underlying container
*****************************************************/

import java.util.ArrayList;

public class ALHeapMin
{

  //instance vars
  private ArrayList<Integer> _heap;

  /*****************************************************
  * default constructor  ---  inits empty heap
  *****************************************************/
  public ALHeapMin()
  {
    _heap = new ArrayList<Integer>();
  }



  /*****************************************************
  * toString()  ---  overrides inherited method
  * Returns either
  * a) a level-order traversal of the tree (simple version)
  * b) ASCII representation of the tree (more complicated, more fun)
  *****************************************************/
  public String toString()
  {
    //if empty return ""
    if (isEmpty()) return "";

    //if _heap.size() == 1 just return the el (padded)
    if (_heap.size() == 1) {
      Integer[] temp = {0};
      return "\n=======\n" + pad(temp, 7) + "\n=======\n";
    }

    //how many possible leaves in heap dictates how wide each level should be
    int maxWidth = (int)(Math.pow(2, Math.floor( Math.log(_heap.size()) / Math.log(2) )) * 7);

    Integer[] oldEls = {0};
    String ret = "\n";
    for (int i = 0; i < maxWidth; i++) {
      ret += "=";
    }
    ret += "\n" + pad(oldEls, maxWidth);
    for (int level = 0; level < Math.floor(Math.log(_heap.size()) / Math.log(2)); level++) {
      Integer[] els = new Integer[(int)(Math.pow(2, level + 1))];
      int elsIndex = 0;
      for (Integer i : oldEls) {
        els[elsIndex] = ((2 * i + 1) < _heap.size()) ? 2 * i + 1 : -1;
        els[elsIndex + 1] = ((2 * i + 2) < _heap.size()) ? 2 * i + 2 : -1;
        elsIndex += 2;
      }
      ret += "\n\n" + pad(els, maxWidth);
      oldEls = els;
    }

    ret += "\n";
    for (int i = 0; i < maxWidth; i++) {
      ret += "=";
    }

    return ret;
  }//O(?)

  public String pad(Integer[] nums, int width) {
    int numSpaces = width / nums.length;
    String ret = "";
    for (Integer i : nums) {
      if (i == -1) {
        for (int x = 0; x < numSpaces; x++) {
          ret += " ";
        }
      }
      else {
        String toAdd = _heap.get(i) + "";
        int padSize = numSpaces - toAdd.length();
        int padStart = toAdd.length() + padSize / 2;
        toAdd = String.format("%" + padStart + "s", toAdd);
        toAdd = String.format("%-" + numSpaces + "s", toAdd);
        ret += toAdd;
      }
    }
    return ret;
  }


  /*****************************************************
  * boolean isEmpty()
  * Returns true if no meaningful elements in heap, false otherwise
  *****************************************************/
  public boolean isEmpty()
  {
    return _heap.size() == 0;
  }//O(1)


  /*****************************************************
  * Integer peekMin()
  * Returns min value in heap
  * Postcondition: Heap remains unchanged.
  *****************************************************/
  public Integer peekMin()
  {
    return _heap.get(0);
  }//O(1)


  /*****************************************************
  * add(Integer)
  * Inserts an element in the heap
  * Postcondition: Tree exhibits heap property.
  *****************************************************/
  public void add( Integer addVal )
  {
    //add addVal to end of heap
    _heap.add(addVal);

    //if this is the first val added to heap, leave it
    if (isEmpty()) return;

    //otherwise, store vals of addVal and parent
    int index = _heap.size() - 1;
    int pIndex = (index - 1) / 2;

    //while addVal < parent, swap them
    while (index != 0 && addVal < _heap.get(pIndex)) {
      swap(index, pIndex);

      //redefine the index and parent to restart the comparison/swap process
      index = pIndex;
      pIndex = (index - 1) / 2;
    }
  }//O(logn)


  /*****************************************************
  * removeMin()  ---  means of removing an element from heap
  * Removes and returns least element in heap.
  * Postcondition: Tree maintains heap property.
  *****************************************************/
  public Integer removeMin()
  {
    //if heap is empty, throw exception
    if (isEmpty()) return null;

    //if only 1 el, you don't have to do the rest of the method. just remove and return
    if (_heap.size() == 1) {
      return _heap.remove(0);
    }

    //smallest is at index 0, so replace it with last element in heap
    Integer toReturn = _heap.get(0);
    swap(0, _heap.size() - 1);
    _heap.remove(_heap.size() - 1);

    //now we're in a state that the "min" element might not be min.
    //swap until it's in the right place

    int index = 0;

    //while the element has children
    while (minChildPos(index) != -1) {

      //store the minChildPos so you don't have to call it a lot
      int mcp = minChildPos(index);

      //if the smallest child is less than the parent, shouldSwap = true. Else shouldSwap = false;
      boolean shouldSwap = (minOf(_heap.get(index), _heap.get(mcp)) == _heap.get(mcp)) ? true : false;

      //if the smallest child is less than the parent, swap them and go again
      if (shouldSwap) {
        swap(index, mcp);
        index = mcp;
      }

      //if not, you're done
      else break;
    }

    //return the removed value
    return toReturn;
  }//O(logn)


  /*****************************************************
  * minChildPos(int)  ---  helper fxn for removeMin()
  * Returns index of least child, or
  * -1 if no children, or if input pos is not in ArrayList
  * Postcondition: Tree unchanged
  *****************************************************/
  private int minChildPos( int pos )
  {
    if (pos >= _heap.size()) return -1;

    //store pos of children
    int lIndex = 2 * pos + 1;
    int rIndex = 2 * pos + 2;

    //if no children, return -1
    if (lIndex >= _heap.size() && rIndex >= _heap.size()) return -1;

    //if only 1 child, return the other's index
    if (lIndex >= _heap.size()) return rIndex;
    if (rIndex >= _heap.size()) return lIndex;

    //if 2 children, find and return the min
    int l = _heap.get(lIndex);
    int r = _heap.get(rIndex);

    //if min(l, r) == r, minPos = rIndex. Else, minPos = lIndex
    int minPos = (minOf(l, r) == r) ? rIndex : lIndex;

    //return the pos of smallest child
    return minPos;

  }//O(1)

  public int size() {
    return _heap.size();
  }


  //************ aux helper fxns ***************
  private Integer minOf( Integer a, Integer b )
  {
    if ( a.compareTo(b) < 0 )
      return a;
    else
      return b;
  }

  //swap for an ArrayList
  private void swap( int pos1, int pos2 )
  {
    _heap.set( pos1, _heap.set( pos2, _heap.get(pos1) ) );
  }
  //********************************************



  //main method for testing
  public static void main( String[] args )
  {
    ALHeapMin pile = new ALHeapMin();

    pile.add(2);
    System.out.println(pile);
    pile.add(4);
    System.out.println(pile);
    pile.add(6);
    System.out.println(pile);
    pile.add(8);
    System.out.println(pile);
    pile.add(10);
    System.out.println(pile);
    pile.add(1);
    System.out.println(pile);
    pile.add(3);
    System.out.println(pile);
    pile.add(5);
    System.out.println(pile);
    pile.add(7);
    System.out.println(pile);
    pile.add(9);
    System.out.println(pile);


    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    System.out.println("removing " + pile.removeMin() + "...");
    System.out.println(pile);
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
  }//end main()

}//end class ALHeap
