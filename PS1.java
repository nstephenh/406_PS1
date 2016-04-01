import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

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
    
    public static boolean parensRight(String expr) {
        IStack<Character> s= new AStack<>();
        //Doing this with a stack is horribly inefficient,
        // because we have to iterate through both the string and stack
        int i = 0;
        while (i < expr.length()){
            s.push(expr.charAt(i));
            i++;
        }
        int parencount = 0;
        while (!(s.isEmpty())){
            Character cur = s.pop();
            if (cur.equals('(')){
                parencount++;
            }
            if (cur.equals(')')){
                parencount--;
            }
        }
        return (parencount == 0);
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
    public static Map<String, ArrayList<Integer>> extractTagTypes(String fileName) {
        //File IO First
        ArrayList<String> page = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String buf = "";
            while ((buf = br.readLine()) != null) {
                page.add(buf + "\n");
            }
        } catch(FileNotFoundException ex){
            System.out.println("That's no file!");
        } catch(IOException ex){
            System.out.println("IO Exception. Not My fault. Probably");
        }
        //Lets make a map
        Map<String, ArrayList<Integer>> dict = new HashMap<>();

        //Parse this (not the best way to do html parsing)
        int i = 0;
        while (page.size() > i){
            String line = page.get(i);
            if (!(line.contains("<!doctype"))){
                int j = 0;
                int start = -1;
                while (line.length() > j){
                    char cur = line.charAt(j);
                    if (cur =='<'){
                        start = j;
                    }
                    if ((start != -1) && (cur == '>')){
                        String tag = line.substring(start +1, j);
                        if (dict.containsKey(tag)) {
                            //If the tag aready exists append the line number to the array
                            ArrayList<Integer> oldcount = dict.get(tag);
                            ArrayList<Integer> newcount = oldcount;
                            newcount.add(i);
                            dict.replace(tag, oldcount, newcount);
                        }else{
                            ArrayList<Integer> newcount = new ArrayList<>();
                            newcount.add(i);
                            dict.put(tag, newcount);
                        }
                    }
                    j++;
                }


            }
            i++;
        }

        return dict;
    }
    
    /**
    *   Medium Problem
    *   Use a stack to see if the array of tag types was
    *   extracted from a well-formed document. 
    *   @return true if the tags came from a well-formed document
    *   @param moo is a list of tag types (including / for closing tags)
    *   extracted from an HTML file.
    */ 
    public static boolean isWellFormedTagSet(String[] moo)
    {
        IStack<String> s= new AStack<>();
        return false;   
    }
    public static void main(String[] args){
        System.out.println(parensRight("(()"));
        System.out.println(parensRight("(())"));
        System.out.println(extractTagTypes("PS1.html"));
    }
}
