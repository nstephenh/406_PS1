import java.util.ArrayList;
public class HTUtils
{
    private HTUtils(){}
    /**
    *  This finds the tag type of all tags in a line of text
    *  @param line a line of HTML text
    *  @return an array list of strings containing all tag 
    *  types on a line
    */

    public static ArrayList<String> getAllTags(String line)
    {
        ArrayList<String> out = new ArrayList<>();
        int open = line.indexOf('<');
        int end = 0;
        if(open  < 0)
            return out;
        //know that:  there is an open angle bracket.
        // to do:  search for end of tag type
        //  look for a whitespace, close bracket, end of string
        end = open;
        while(end < line.length())
        {
            open = line.indexOf('<', end);//find an open bracket past end
            if(open < 0)
                return out;  //out of tags on this line; done
            //know:  there is another tag
            end = open;
            while((end < line.length() &&(line.charAt(end) != '>')&&(!Character.isWhitespace(line.charAt(end)))))
            {   
                end++;
            }
        //System.out.printf("open = %s, end = %s\n", open, end);
        out.add(line.substring(open + 1, end));
        }
        return out;
    }
    public static void main(String[] args)
    {
        String s = "<html>";
        System.out.printf("%s: %s\n", s, getAllTags(s));
        s = "<tr><td>cows</td><td>pigs</td></tr>";
        System.out.printf("%s: %s\n", s, getAllTags(s));
        s = "This has <span class = \"foo\">a span in it.\n";
        System.out.printf("%s: %s\n", s, getAllTags(s));
        s = "This has <span class \n= \"foo\">a span in it.\n";
        System.out.printf("%s: %s\n", s, getAllTags(s));
        s = "Here is your link <a";
        System.out.printf("%s: %s\n", s, getAllTags(s));
        s = "<table> <tr><th>Test</th><th>Table</th><th>total</th><th>abuse</th></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><td>Test</td><td>Table</td><td>total</td><td>abuse</td></tr> <tr><th colspan = \"4\">Puke</th></tr>";
    
        System.out.printf("%s: %s\n", s, getAllTags(s));
    }
}
