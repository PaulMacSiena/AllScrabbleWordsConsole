/**
 * Console Version of All Scrabble words, found at 
 * http://www.allscrabblewords.com/.
 * Reads a set of words from a text file use as input into the main method.
 * Prints all words (from the file) consisting of the same characters and length n, 
 * then length n-1, and so on up to and including length 2.
 *
 * @author Paul Macfarlane
 * @version 1.0
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
public class Unscrambler
{
    private ArrayList<String> wordsList;
    /**
     * One param consturctor for Unscrambler Object
     * @param fileName a string representing the name of the file to read words from
     */
    public Unscrambler(String fileName) throws FileNotFoundException 
    {

        File wordsFile = new File(fileName);
        Scanner inFile = new Scanner(wordsFile);
        wordsList = new ArrayList<String>();
        while (inFile.hasNextLine()){
            wordsList.add(inFile.nextLine());
        }

    }

    /**
     * Takes a target String (of length n) as the input parameter.  
     * This class then prints out the words of length n found in the string, 
     * followed by words of length n-1, and so on, up to and including length 2.
     * @param word the word to check for strings of lengths n to 2 
     * @return an Array List of Strings that exist in an input String given the letters of the word entered
     */
    public ArrayList<String> findWords(String word){
        ArrayList<String> retList = new ArrayList<String>();
        WordFinder use = new WordFinder();
        // add all strings that exist in the input word to an ArrayList 
        // using contains method that was originally written for the wordfinder class.
        for (String perm: wordsList){
            if (use.contains(word, perm) && perm.length()>1){
                retList.add(perm.toLowerCase());
            }

        }
        //use API version of sort to sort in alphabetical order. Does a merge sort for large
        //arrayLists, insertion for small
        // using teresco's natural comparator that he gave us in Data Structures
        retList.sort(new NaturalComparator());

        return retList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        /* 
         * This main method admitedly looks a bit overly complicated. However, it is 
         * just taking care of the different scenarios in which the user could interact with
         * the program. It also formats the output.
         */
        boolean done = false;
        boolean firstTime = true;
        while(!done){
            if (args.length != 1 || !firstTime){ //executes if user does not enter file name directly
                try{
                    Scanner scan = new Scanner(System.in);
                    if (firstTime){
                        System.out.print("Please enter the name of the text file you wish to read words from: ");
                    }
                    else{
                        System.out.print("Please enter the name of the text file you wish to read words from (or type 'Q' to quit): ");
                    }
                    String fileName = scan.next();
                    if ((fileName.equals("Q") || fileName.equals("q")) && !firstTime){ // user stops
                        done = true;
                    }
                    else {
                        Unscrambler test = new Unscrambler(fileName);

                        System.out.println();
                        System.out.println("Unscramble and Scrabble Word Finder.");
                        System.out.print("Enter a word: "  );
                        String wordToFind = scan.next();

                        ArrayList<String> rawList = new ArrayList<String>();
                        // add all words to an Array List of Strings
                        rawList = test.findWords(wordToFind);

                        System.out.println();

                        String output = ""; 
                        for (int i = wordToFind.length(); i>1;i--){
                            System.out.println(i + " letter words made by unscrambling the letters in " + wordToFind + "\n");
                            for (String words: rawList){

                                if (words.length() == i){

                                    if (output.length() + words.length()>40){ 
                                        System.out.println(output); // print, then reset the output string
                                        System.out.println();
                                        output =""; //reset the output string

                                    }
                                    output = output + words;
                                    if (output.length() + 1 <= 40){ // add a space if there is room
                                        output = output + " ";
                                    }
                                }

                            }
                            System.out.print(output);
                            output = ""; //reset the output string
                            System.out.println();
                            System.out.println();
                        }
                        done = true;

                    }
                }
                catch(IOException e){
                    System.out.println("The file was not found in the directory.");
                }
                firstTime = false;
            }

            else{ // if user enters a filename into args array
                try{
                    Unscrambler test = new Unscrambler(args[0]);

                    Scanner input = new Scanner(System.in);
                    System.out.println("Unscramble and Scrabble Word Finder.");
                    System.out.print("Enter a word: "  );
                    String wordToFind = input.next();

                    ArrayList<String> rawList = new ArrayList<String>();
                    // add all words to an Array List of Strings
                    rawList = test.findWords(wordToFind);

                    System.out.println();
                    String output = ""; 
                    for (int i = wordToFind.length(); i>1;i--){
                        System.out.println(i + " letter words made by unscrambling the letters in " + wordToFind + "\n");
                        for (String words: rawList){

                            if (words.length() == i){

                                if (output.length() + words.length()>40){ 
                                    System.out.println(output); // print, then reset the output string
                                    System.out.println();
                                    output =""; //reset the output string

                                }
                                output = output + words;
                                if (output.length() + 1 <= 40){ // add a space if there is room
                                    output = output + " ";
                                }
                            }

                        }
                        System.out.print(output);
                        output = ""; //reset the output string
                        System.out.println();
                        System.out.println();

                    }
                    done = true;

                }
                catch(IOException e){
                    System.out.println("The file '" + args[0] + "' was not found in the directory.");
                }
                firstTime = false;
            }

        }
    }
}

