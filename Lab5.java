import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class Lab5 extends Trie{
    public static void main(String[]args){
        Trie t = new Trie();
        try {
            Scanner scan = new Scanner(new FileInputStream(args[args.length - 1]));//takes file argument
            while (scan.hasNext()) {
                String line = scan.nextLine();
                StringTokenizer st = new StringTokenizer(line);//creates tokens of commands
                int count = st.countTokens();  
                ArrayList <String>tokens = new ArrayList<String>();
                for (int i = 0; i <count; i++) 
                    tokens.add(st.nextToken());
                if (tokens.get(0).equals("insert")) {
                    String num = tokens.get(1);
                    insert(t, num);
                }
                else if(tokens.get(0).equals("print")) {
                    ArrayList<String> list = trieToList(t);
                    for(int i = 0; i < list.size(); i ++)
                        System.out.print(list.get(i) + " ");
                    System.out.println();
                }
                else if(tokens.get(0).equals("smallest")) {
                    System.out.println(smallest(t));
                }
                else if(tokens.get(0).equals("largest")) {
                    System.out.println(largest(t));
                }
                else if(tokens.get(0).equals("size")) {
                    System.out.println(size(t));
                }
                else if(tokens.get(0).equals("height")) {
                    System.out.println(height(t));
                }
                else if (tokens.get(0).equals("search")) {
                    String num = tokens.get(1);
                    System.out.println(search(t, num));
                }
            }
            // read next line
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
}
