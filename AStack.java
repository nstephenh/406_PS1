import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Arrays;
public class AStack<T> implements IStack<T>
{
    private Object[] items;
    private int capacity;
    private int sz;
    public AStack()
    {
        this(10);
    }
    public AStack(int capacity)
    {
         this.capacity = capacity;
         sz = 0;
         items = new Object[capacity];
    }
      /**
     * This evaluates to true if the stack is empty
     * @return true if the stack is empty and false otherwise
     */
    public boolean isEmpty()
    {
        return sz == 0;
    }
    /**
     * This places newItem onto the top of the stack
     * @param newItem the item to be placed on the stack
     */
    public void push(T newItem)
    {
        //what if there is no room at the inn?
        if(sz >= capacity)
        {
            resizeArray();
        }

        items[sz] = newItem;
        sz++;
        
    }
    /**
     * This accessor returns a reference to the item atop the stack
     * @return the item on the top of teh stack
     * @throws EmptyStackException if the stack is empty
     */ 
    @SuppressWarnings("unchecked")
    public T peek()
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        return (T) items[sz - 1];
    }
    /**
     * This mutator removes the top item from the stack and returns it.
     * @return the item onthe top of the stack
     * @throws EmptyStackException if the stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T pop()
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        T out = (T)items[sz - 1];
        sz--;
        return out ;
    }
    /**
     * This returns the number of items on  this stack.
     * @return the number of items on this stack
     */
    public int size()
    {
        return sz;
    }
   
    @Override
    public String toString()
    {
        if(isEmpty())
        {
            return "[]";
        }
       
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        for(int k = sz - 1; k > 0; k--)
        {
            sb.append(items[k] + ", ");
        }
        sb.append(items[0] + "]");
        return sb.toString();
    }
    public Iterator<T> iterator()
    {
        //TODO:  implement this!!!
        return new Navigator<T>();
    }
    public void clear()
    {
        sz = 0;
    }
    /************poopsmiths ***********************************/
    private int resizePolicy(int oldSize)
    {
        return oldSize < 1000? 2*oldSize: 3*oldSize/2;
    }
    private void resizeArray()
    {
        //make a bigger array using the resize policy to size it
        Object[] newHouse = new Object[resizePolicy(items.length)];
        //move contents of old array in
        System.arraycopy(items, 0, newHouse, 0, items.length);
        //orphan old array
        items = newHouse;
        capacity = newHouse.length;
    }
    /************* Alien*****************************************/
    class Navigator<T> implements Iterator<T>
    {
        private int loc;
        public Navigator()
        {
            loc = sz;
        }
        public boolean hasNext()
        {
            return loc > 0;
        }
        @SuppressWarnings("unchecked")
        public T next()
        {
            loc--;                 //go to next
            return (T)items[loc];  //grab next
        }
    }
    public static void main(String[] args)
    {
        final int TEST  = 20;
        IStack<String> theStack = new AStack<String>();
        for(int k = 0; k < TEST; k++)
        {
            theStack.push("" + k);
        }
        System.out.println(theStack.size() == TEST? "PASS":"FAIL");
        for(String s: theStack)
        {
            System.out.print(s  + " ");
        }
        System.out.println("");
        System.out.println("McDow dies now " + theStack);
        for(int k = 0; k < TEST; k++)
        {
            theStack.pop();
        }
        System.out.println(theStack.size() == 0? "PASS":"FAIL");
        System.out.println(theStack);
    }
}

