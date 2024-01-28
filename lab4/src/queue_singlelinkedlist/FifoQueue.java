package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible
	 * post: The specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element
	 *         to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<>(e);
		// no elements
		if (last == null) {
			last = n;
			last.next = last; // Reads: last.next shall reference to itself
			size++;
			return true;
		} else {
			n.next = last.next; // The new node's next should point at the old node's next.
			last.next = n; // the old node should point at the new node
			last = n; // last is the new node we just inserted
			size++;
			return true;
		}
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue,
	 * returning null if this queue is empty
	 * 
	 * @return the head element of this queue, or null
	 *         if this queue is empty
	 */
	public E peek() {
		if (last == null) {
			return null;
		} else {
			return last.next.element;
		}
	}

	/**
	 * Retrieves and removes the head of this queue,
	 * or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (last == null) {
			return null;
		}

		else if (size == 1) {
			QueueNode<E> tempNode = last;
			last = null;
			size--;
			return tempNode.element;
		}

		else {
			QueueNode<E> tempNode = last.next;
			last.next = last.next.next;
			size--;
			return tempNode.element;
		}

	}

	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call.
	 * 
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		// They are the same
		if (this == q) {
			throw new IllegalArgumentException();
		}

		// this q is empty but not q.
		else if (last == null && q.last != null) {
			this.last = q.last;
			size += q.size();
			q.last = null;
			q.size = 0;
		}
		//
		else if (q.last != null) {
			QueueNode<E> thisHead = this.last.next;
			this.last.next = q.last.next;

			this.last = q.last;
			this.last.next = thisHead;

			size += q.size();
			q.last = null;
			q.size = 0;
		}

	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;

		private QueueIterator() {
			pos = last;
		}

		@Override
		public boolean hasNext() {
			return pos != null;
		}

		@Override
		public E next() {
			// If the q is empty I guess
			if (pos == null) {
				throw new NoSuchElementException();
			}

			// one or more element
			pos = pos.next;
			E e = pos.element;

			if (pos == last) {
				pos = null;
			}
			return e;
		}
	}

}
