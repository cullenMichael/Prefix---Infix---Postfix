import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;




// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Michael Cullen
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    public DLLNode head, tail;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic runtime cost: theta(1)
	 *
	 *         Justification: as this only checks if the head and tail = null.
	 *         it doesn't need to check through all the N elements of the list.
	 *         Therefore, its max runtime is Theta(1) which can be bound up to
	 *         O(1).
	 */
	public boolean isEmpty() {

		if ((this.head == null)) { // 1
			return true; // 1
		} else {
			return false; // 1
		}
	}

	public void push(T item) {
		DLLNode tmp = new DLLNode(item, null, head); // 1
		if (head != null) {
			DLLNode t = head;
			t.prev = tmp;
		}
		head = tmp;
		if (tail == null) {
			tail = tmp;
		}
	}

	public T pop() {

		DLLNode t1 = head; // 1
		DLLNode t2 = t1.next; // 1
		if (t2 == null) { // 1
			tail = null; // 1
			head = null; // 1
			return t1.data; // 1
		}
		t2.prev = null; // 1

		head = t2; // 1
		return t1.data; // 1

	}

	public void enqueue(T item) {
		DLLNode t1 = head; // 1
		DLLNode tmp = new DLLNode(item, null, head);// 1
		tail = tmp;
		head = tmp; // 1
	}
}
  