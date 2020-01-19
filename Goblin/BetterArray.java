/**
 *  Makes a  array called BetterArray that is use for Goblin Game.
 * 
 *  @author Abdikarim Abdirahman
 *  @param <T> the Generic T for the type 
 * 
 */

public class BetterArray<T> {
	/**
	 *  The DEFAULT_CAPACITY is the size when the capacity at the start.
	 *  the minimum capacity.
	 */
	private static final int DEFAULT_CAPACITY = 2; //default initial capacity / minimum capacity
	/**
	 *  The orginal array used to make the BetterArray.
	 *  
	 */
	private T[] data; //underlying array, you MUST use this for full credit
	/**
	 *  A instance varaible I made to keep count of the size.
	 *  
	 */
	private int size=0;

	/**
	 *  default constructor sets the capacity to the default capacity.
	 */
	@SuppressWarnings("unchecked")
	public BetterArray() 
	{
		this.data= (T[]) new Object[DEFAULT_CAPACITY];
	}

	/**
	 *   constructor takes in the capacity. 
	 *   @param  initialCapacity the capacity in the start if do not use the default constructor
	 */
	@SuppressWarnings("unchecked")
	public BetterArray(int initialCapacity) 
	{

		this.data= (T[]) new Object[initialCapacity];  
		if (initialCapacity<1)
		{
			throw new IllegalArgumentException();
		}

	}

	/**
	 *  returns the size.
	 *  @return size it returns the size of the BetterArray
	 */

	public int size() 
	{ 
		return size;
	}  


	/**
	 *  returns the capacity.
	 *  @return this.data.length it returns the length of the array same as  capacity
	 */


	public int capacity() 
	{ 
		return this.data.length;
	} 


	/**
	 *  adds something at the end of BetterArray.
	 *  @param value what you want to append
	 *  @return true it returns the length of the array same as  capacity
	 */
	@SuppressWarnings("unchecked")
	public boolean append(T value) 
	{
		if (size()==capacity())
		{
			if (ensureCapacity(capacity()))
			{
				T[] one= (T[]) new Object[capacity()*2];

				for (int a=0;a<size;a++)
				{
					one[a]=this.data[a]; 
				}
				this.data=one;
			}
		}
		this.data[size]=value;
		size++;

		return true;
	}

	/**
	 * adds something at the at a certain index of BetterArray.
	 * @param index where you want to append
	 * @param value what you want to append
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value) 
	{  

		if (index<0 || index>capacity())
		{

			throw new IndexOutOfBoundsException();
		}


		if (size==capacity())
		{
			if (ensureCapacity(capacity()))
			{
				T[] one= (T[]) new Object[capacity()*2];

				for (int a=0;a<size;a++)
				{
					one[a]=this.data[a]; 
				}
				this.data=one;
			}
		}

		for (int i=size;i>index;i--)
		{
			this.data[i]=this.data[i-1];  
		}
		this.data[index]=value;
		size++;
	}


	/**
	 *  returns the T at that index.
	 *  @param index where the T is
	 *  @return this.data[index] it returns the T at that index
	 */

	public T get(int index)
	{
		if (index<0 || index>size)
		{
			throw new IndexOutOfBoundsException();
		}


		return this.data[index];
	}

	/**
	 *  replace the T at that index with value.
	 *  @param index where you want to replace
	 *  @param value what you want to replace
	 *  @return old what was there before it was replaced
	 */

	public T replace(int index, T value){

		if (index<0 || index>=size)
		{
			throw new IndexOutOfBoundsException();
		}

		T old=this.data[index];
		this.data[index]=value;

		return old;
	}

	/**
	 *  deletes the T at that index.
	 *  @param index where you want to delete
	 *  @return old what was deleted
	 */
	@SuppressWarnings("unchecked")
	public T delete(int index){
		if (index<0 || index>=size)
		{
			throw new IndexOutOfBoundsException();
		}

		T old= this.data[index];

		for (int i=index;i<size;i++)
		{

			this.data[i]=this.data[i+1];  
		}

		if ((double)(size)/(double)(capacity())<(0.25))
		{
			T[] one= (T[]) new Object[capacity()/2];

			for (int a=0;a<size;a++)
			{
				one[a]=this.data[a];

			}
			this.data=one;
		}
		size--;

		return old;
	}  

	/**
	 *  returns the first index of what is the value.
	 *  @param value what you are looking for
	 *  @return index the index where it is found first 
	 */

	public int firstIndexOf(T value){
		int index=-1;
		for (int i=0;i<size;i++)
		{
			if (this.data[i]==value) 
			{
				index=i;
				break;
			}
		}


		return index;
	}


	/**
	 *  makes the capaity is meant to change.
	 *  @param newCapacity what you are looking for
	 *  @return capacity()==newCapacity makes sure the newCapacity is new capacity.
	 */

	@SuppressWarnings("unchecked")
	public boolean ensureCapacity(int newCapacity){

		if( newCapacity>DEFAULT_CAPACITY && newCapacity>size)
		{
			T[] one= (T[]) new Object[newCapacity];

			for (int a=0;a<size;a++)
			{
				one[a]=this.data[a]; 
			}

			this.data=one;

		}

		return capacity()==newCapacity;
	}

	/**
	 *  make a clone of a betterArray.
	 * 
	 *  @return same the clone  
	 */
	public BetterArray<T> clone() {
		BetterArray<T> same= new BetterArray<T>(capacity());
		for (int i=0;i<size;i++)
		{
			same.append(this.data[i]);
		}
		return same;
	}


	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	/**
	 *  main method.
	 *  @param args main method
	 */
	public static void main(String args[]) {
		//create a smart array of integers
		BetterArray<Integer> nums = new BetterArray<>();


		if ((nums.size() == 0) && (nums.capacity() == 2)){
			System.out.println("Yay 1");
		}

		//append some numbers 
		for (int i=0; i<3;i++)
			nums.add(i,i*2);



		if (nums.size()==3 && nums.get(2) == 4 && nums.capacity() == 4 ){
			System.out.println("Yay 2");
		}

		//create a smart array of strings
		BetterArray<String> msg = new BetterArray<>();


		//insert some strings
		msg.add(0,"world");
		msg.add(0,"hello");
		msg.add(1,"new");
		msg.append("!");



		//replace and checking
		if (msg.get(0).equals("hello") && msg.replace(1,"beautiful").equals("new") 
				&& msg.size() == 4 && msg.capacity() == 4 ){
			System.out.println("Yay 3");
		}

		//change capacity


		if (!msg.ensureCapacity(0) && !msg.ensureCapacity(3) && msg.ensureCapacity(20)
				&& msg.capacity() == 20){
			System.out.println("Yay 4");
		}  

		//delete and shrinking



		if (msg.delete(1).equals("beautiful") && msg.get(1).equals("world")  
				&& msg.size() == 3 && msg.capacity() == 10 ){
			System.out.println("Yay 5");
		}

		//firstIndexOf and clone
		//remember what == does on objects... not the same as .equals()
		BetterArray<String> msgClone = msg.clone();


		if (msgClone != msg && msgClone.get(1) == msg.get(1)
				&& msgClone.size() == msg.size()
				&& msgClone.capacity() == msg.capacity()
				&& msgClone.firstIndexOf("world") == 1
				&& msgClone.firstIndexOf("beautiful") == -1) {
			System.out.println("Yay 6");
		}
	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------


	/**
	 *  helps the check if the code is working right.
	 *  @return sb.toString()
	 */

	public String toString() {
		if(size() == 0) return "";

		StringBuffer sb = new StringBuffer();
		sb.append(get(0));
		for(int i = 1; i < size(); i++) {
			sb.append(", ");
			sb.append(get(i));
		}
		return sb.toString();

	}

}