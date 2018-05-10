/*
Kashf Mashrafi, Mohtasim Howlader
APCS2 pd8
HW #46: Arrr, There Be Priorities Here Matey
2018-5-9
 */

import java.util.ArrayList;
public class ArrayPriorityQueue
{
    ArrayList<String> _queue = new ArrayList<String>();

    //default constructor
    public ArrayPriorityQueue()
    {
         _queue = new ArrayList<String>();
    }

    //add method
    public void add(String input)
    {
	//adds to the end of array
	_queue.add(input);
    }

    public boolean isEmpty()
    {
	//true of size is 0
	return _queue.size() == 0;
    }

    public String peekMin()
    {
	String min = _queue.get(0);
	//traverses through the array to find min value
	for (String e: _queue)
	    {
		if (min.compareTo(e) > 0)
		    min = e;
	    }
	return min;
    }

    public String removeMin()
    {
	String min = _queue.get(0);
	int index = -1;
	//traverses through the array to find min value
	for (String e: _queue)
	    {
		if (min.compareTo(e) > 0)
		    {min = e;}
	    }
	//traverses through the array to find index of min value
	for (String e: _queue)
	    {
		index ++;
		if (min == e)
		    {
			break;
		    }
	    }
	//removes min value
	_queue.remove(index);
	return min;
    }

    //for visualizing array
    public String toString()
    {
	String rtn = "[";

	for (String e: _queue)
	    rtn += e + " ,";
	return rtn + "]";
    }

    public static void main (String [] args)
    {
	ArrayPriorityQueue a = new ArrayPriorityQueue();

	//adding to queue [working]
	a.add("a");
	a.add("b");
	a.add("d");
	a.add("c");
	a.add("f");
	a.add("a");
	//[a ,b ,d ,c ,f ,a ,]

	//removing and peeking min from queue [working]
	while (!a.isEmpty())
	    {
		System.out.println(a.toString());
		System.out.println("Peek: " + a.peekMin());
		a.removeMin();
	    }
	/*
	  output results:
	  [a ,b ,d ,c ,f ,a ,]
	  Peek: a
	  [b ,d ,c ,f ,a ,]
	  Peek: a
	  [b ,d ,c ,f ,]
	  Peek: b
	  [d ,c ,f ,]
	  Peek: c
	  [d ,f ,]
	  Peek: d
	  [f ,]
	  Peek: f
	*/
    }
}
