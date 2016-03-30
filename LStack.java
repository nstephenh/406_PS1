import java.util.Iterator;
public class LStack<T> implements IStack<T>
{
    private Link<T> top;
    //private int sz;
    public LStack()
    {
        top = null;
        //sz = 0;
    }
      /**
     * This evaluates to true if the stack is empty
     * @return true if the stack is empty and false otherwise
     */
    public boolean isEmpty()
    {
        return top == null;
    }
    /**
     * This places newItem onto the top of the stack
     * @param newItem the item to be placed on the stack
     */
    public void push(T newItem)
    {
        top = new Link<T>(newItem, top);
        //sz++;
    }
    /**
     * This accessor returns a reference to the item atop the stack
     * @return the item on the top of teh stack
     * @throws EmptyStackException if the stack is empty
     */ 
    public T peek()
    {
        if(isEmpty())
            throw new EmptyStackException();
               
        return top.getDatum();
    }
    /**
     * This mutator removes the top item from the stack and returns it.
     * @return the item onthe top of the stack
     * @throws EmptyStackException if the stack is empty.
     */
    public T pop()
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        T out = top.getDatum();
        top = top.getNext();
        //sz--;
        return out;
    }
    /**
     * This returns the number of items on  this stack.
     * @return the number of items on this stack
     */
    public int size()
    {
        //return sz;
        return toEnd(top);
    }
    public Iterator<T> iterator()
    {
        //TODO: implement this!!
        return new Navigator();
    }
    private int toEnd(Link<T> link)
    {
        return link == null?  0: 1 + toEnd(link.getNext());
    }
    @Override
    public String toString()
    {
        Link<T> nav = top;
        StringBuffer sb = new StringBuffer();
        while(nav != null)
        {
            System.out.print(nav.getDatum());
            nav = nav.getNext();
            
        }
        return sb.toString();
    }
    /*********************Aliens***********************/
    @SuppressWarnings("unchecked")
    class Navigator implements Iterator<T>
    {
        Link<T> loc;
        public Navigator()
        {
            loc = top;
        }
        public boolean hasNext()
        {
            return loc != null;
        }
        public T next()
        {
            T out = loc.getDatum();
            loc = loc.getNext();
            return out;
        }
    }
    public static void main(String[] args)
    {
        IStack<String> theStack = new LStack<String>();
        final int TEST = 2000;
        for(int k = 0; k < TEST; k++)
        {
            theStack.push("" + k);
        }
        for(String s: theStack)
        {
            System.out.print(s  + " ");
        }
        for(int k = 0; k < TEST; k++)
        {
            theStack.pop();
        }
        System.out.println(theStack.isEmpty()? "PASS":"FAIL");
    }
}
class Link<T>
{
    private T datum;  
    private Link<T> next;
    public Link(T datum, Link<T> next)
    {
        this.datum = datum;
        this.next = next;
    }
    public Link(T datum)
    {
        this(datum, null);
    }
    public T getDatum()
    {
        return datum;
    }
    public Link<T> getNext()
    {
        return next;
    }
}
