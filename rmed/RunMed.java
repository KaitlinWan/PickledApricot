/*
PickledApricot - Joan Chirinos, Mohtasim Howlader, Kaitlin Wan
APCS2 pd08
HW50 -- Run Run Run
2018-05-15
*/

import java.util.NoSuchElementException;

public class RunMed {

  //instance vars
  ALHeapMin lilvals;
  ALHeapMax bigvals;

  public RunMed() {
    lilvals = new ALHeapMin();
    bigvals = new ALHeapMax();
  }

  public double getMedian() {

    //if both heaps are empty, no element was added yet
    if (lilvals.isEmpty() && bigvals.isEmpty()) throw new NoSuchElementException();

    //if one heap is larger, the root of that heap is the median
    if (lilvals.size() > bigvals.size()) return lilvals.peekMin();
    else if (bigvals.size() > lilvals.size()) return bigvals.peekMax();

    //if both heaps are the same size, the mean of the roots is the median
    return (lilvals.peekMin() + bigvals.peekMax()) / 2.0;

  }

  public void add(Integer newVal) {

    //if both heaps are empty, defaults to adding to lilvals
    if (lilvals.isEmpty() && bigvals.isEmpty()) {
      lilvals.add(newVal);
      return;
    }

    if (bigvals.isEmpty()) {
      if (newVal > lilvals.peekMin()) lilvals.add(newVal);
      else bigvals.add(newVal);
    }

    else if (lilvals.isEmpty()) {
      if (newVal < bigvals.peekMax()) bigvals.add(newVal);
      else lilvals.add(newVal);
    }

    //else if newVal < the largest number in bigvals, add it to bigvals
    else if (newVal < bigvals.peekMax()) bigvals.add(newVal);
    //else add it to lilvals
    else lilvals.add(newVal);

    //then...
    //while the size difference >= 2, remove from larger heap and add to smaller heap
    while (Math.abs(lilvals.size() - bigvals.size()) >= 2) {

      //if lilvals is the larger heap, remove from there and add to bigvals
      if (lilvals.size() > bigvals.size()) {
        bigvals.add(lilvals.removeMin());
      }

      //else do it vice versa
      else lilvals.add(bigvals.removeMax());

    }

  }

  public static void main(String[] args) {
    RunMed r = new RunMed();

    for (int i = 0; i < 11; i++) {
      r.add(i);
      System.out.println("lilvals\n" + r.lilvals);
      System.out.println("bigvals\n" + r.bigvals);
      System.out.println("median: " + r.getMedian());
    }

  }

}//end class
