/**
 * This class has a static class method called contains, which takes 
 * two string arguments, text and target. The function will 
 * return true if the text (first argument) contains all of the letters 
 * (case insensitive, but distribution-sensitive) in the target string 
 * (second argument).  Otherwise, the function will return false.
 *
 * @author Paul Macfarlane 
 */
import java.util.ArrayList;
public class WordFinder
{
    /**
     * Takes two string arguments, returns true if first argument contains all of the letters in the second argument. Else, 
     * returns false
     * 
     * @param text first string argument
     * @param target second string argument
     * @return true if first argument contains all of the letters in the second argument. Else, returns false
     */
    public static boolean contains(String text, String target){
        //method 1, use an arraylist to store chars

        //turn all letters to lowercase, to avoid case sensitivity 
        String text1 = text.toLowerCase();
        String target1 = target.toLowerCase();

        ArrayList<Character> textList = new ArrayList<Character>();
        ArrayList<Character> targetList = new ArrayList<Character>();

        //insert each character in text into an arrayList 
        for (int i =0; i<text1.length();i++){
            textList.add(text1.charAt(i));
        }

        //insert each character in target into an arrayList 
        for (int i =0; i<target1.length();i++){
            targetList.add(target1.charAt(i));
        }

        //check to see if text contains all the letters in target

        for (int i = 0; i<targetList.size();i++){
            //temp boolean variable, is set to true if a letter in target is found in text, gets reset to after each 
            //letter in target is checked
            boolean contains = false;    

            for (int j =0; j<textList.size();j++){
                if (targetList.get(i)==textList.get(j)){
                    contains = true;                   

                }
            }
            if (!contains){
                return false;
            }
            //must remove the character in text, since letters that occur multiple times in target need to be 
            //checked for in text
            textList.remove(targetList.get(i));
        }
        return true;
    }
}
