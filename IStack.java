/*
A data structure is a container for strong related items
undr a single name.  
 
Different data structures have different rules for things such as
item access, insertion, and removal
 
Places you might see stacks:
     HTML:  For an open tag, push the tag onto the tag stack
     for a close tag, peek at the tag stack.  If the close tag matches
     the tage onthe top, pop.  Otherwise abort and report error.
     If the tag stack is empty at the end, the documetn is well-formed.
      
     Call Stack:  when a function is called, a stack frame is created and pushed
     onto teh call stack.  when the function returns, its frame is popped.
      
     Check paren balance.  
         read an expression 
         push each ( onto the stack
         pop the top when you see a ).
         If you attempt to pop from an emoty stack, expression is unbalanced
         if there are leftover (s at the end, expression is unbalanced.
          
*/


import java.util.EmptyStackException;

public interface IStack<T> extends Iterable<T>
{
    /**
     * This evaluates to true if the stack is empty
     * @return true if the stack is empty and false otherwise
     */
    public boolean isEmpty();
    /**
     * This places newItem onto the top of the stack
     * @param newItem the item to be placed on the stack
     */
    public void push(T newItem);
    /**
     * This accessor returns a reference to the item atop the stack
     * @return the item on the top of teh stack
     * @throws EmptyStackException if the stack is empty
     */
    public T peek();
    /**
     * This mutator removes the top item from the stack and returns it.
     * @return the item onthe top of the stack
     * @throws EmptyStackException if the stack is empty.
     */
    public T pop();
    /**
     * This returns the number of items on  this stack.
     * @return the number of items on this stack
     */
    public int size();
    /**
    * This empties the stack of items.
    */
    public void clear();
            
}
