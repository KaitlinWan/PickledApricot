# PickledApricot
Lab #3

Joan Chirinos, Mohtasim Howlader, Kaitlin Wan

APCS2 PD08

## TO-DO


## METHODS   &    VARIABLES (in v2)

* ArrayList<String> _data

  * Underlying data storage container

* add(String input)

  * Based on the priority of the input, the add method will traverse from the center of the array. According to the value, if it is lower, it will traverse to the left. If it is higher, it will traverse to the right. This is a similar procedure to Binary Search. We chose to do this because although it has the same Big Oh notation as using an insertion sort-type algorithm, the binary insertion is faster
  * Time complexity: O(n)
  
* whereToAdd(String input)

  * Helper method for add which finds the index of insertion
  * Time complexity: O(logn)
  
* isEmpty()
  * Returns TRUE if size variable is 0. False otherwise.
  * Time complexity: O(1)
  
* peekMin()
  * Returns the highest priority by returning the object at index size() - 1
  * Time complexity: O(1)
  
* removeMin()
  * Removes and returns the highest priority, which is at index size() - 1
  * Time complexity: O(1)

* size()
  * returns size of underlying ArrayList container
  * Time complexity: O(1)
