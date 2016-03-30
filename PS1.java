/**
*   Use for a stack the implmeentations we developed in class.
*   Place this code in the same diirectory as your copies of AStack,
*   IStack, and LStack.  
*/
public class PS1
{
    /**
    *   Easy Problem.
    *   Use a stack to check and see if the parens in the 
    *   expression are correctly formed.  
    *   @return true if the parens are well-formed
    */
    
    public boolean parensRight(String expr)
    {
        IStack<Character> s= new AStack<>();
        return false;
    }
    /**
    *   Annoying but not too difficult problem
    *   This is to wake up your fileIO skills
    *   Open an HTML file for reading
    *   Disregard the <!doctype> line
    *   Make a dictionary (Map) whose keys are HTML tags and whose
    *   values are the list of lines on which that tag occurs
    *
    */   
    public Map<String, ArrayList<Integer>> extractTagTypes(String fileName)
    {
        
    }
    
    /**
    *   Medium Problem
    *   Use a stack to see if the array of tag types was
    *   extracted from a well-formed document. 
    *   @return true if the tags came from a well-formed document
    *   @param moo is a list of tag types (including / for closing tags)
    *   extracted from an HTML file.
    */ 
    public boolean isWellFormedTagSet(String[] moo)
    {
        IStack<String> s= new AStack<>();
        return false;   
    }
}
