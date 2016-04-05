import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        for (int i = 0; i < expr.length(); i++){
            Character cur = expr.charAt(i);
            if (cur.equals(')') && s.peek().equals('(')){
                s.pop();
            }else if (cur.equals('(')){
                s.push(cur);
            }
        }
        return s.isEmpty();
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
                    //Since the end of the defining part of the tag is always > or whitespace,
                    //We can use that to define the end of our tags.
                    if ((start != -1) && ((cur == '>') || Character.isWhitespace(cur))){
                        String tag = line.substring(start +1, j);
                        if (dict.containsKey(tag)) {
                            //If the tag aready exists append the line number to the array
                            ArrayList<Integer> oldcount = dict.get(tag);
                            ArrayList<Integer> newcount = oldcount;
                            newcount.add(i);
                            dict.replace(tag, oldcount, newcount);
                        }else {
                            ArrayList<Integer> newcount = new ArrayList<>();
                            newcount.add(i);
                            dict.put(tag, newcount);
                        }
                        start = -1; //Reinitialize in case of another tag

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
        //Note that this only works if the tags come in the order of open tag followed by close tag
        IStack<String> s= new AStack<>();
        for (int i = 0; i < moo.length; i++){
            String tag = moo[i];
            //If the tag is something like <br/> we can ignore it
            if (!(tag.endsWith("/"))){
                if (tag.startsWith("/") && tag.substring(1).equals(s.peek())){
                    s.pop();
                }else{
                    s.push(tag);
                }
            }
        }
        return s.isEmpty();
    }
    public static void main(String[] args){
        System.out.println(parensRight("(()")); //Should be false
        System.out.println(parensRight("(())")); //Should be true
        System.out.println(extractTagTypes("PS1.html")); //See expected results.txt
        ArrayList<String> test = HTUtils.getAllTags("<table> <tr><th>Test</th><th>Table</th><th>total</th><th>abuse</th></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><th colspan = \"4\">Puke</th></tr></table>");
        String[] moretest = test.toArray(new String[0]);
        System.out.println(isWellFormedTagSet(moretest)); //Should be true
        ArrayList<String> test2 = HTUtils.getAllTags("<table> <th>Test</th><th>Table</th><th>total</th><th>abuse</th></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><th colspan = \"4\">Puke</th></tr></table>");
        String[] moretest2 = test2.toArray(new String[0]);
        System.out.println(isWellFormedTagSet(moretest2)); //Should be false
    }
}
