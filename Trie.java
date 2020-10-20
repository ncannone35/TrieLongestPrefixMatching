import java.util.*;
public class Trie{
    static class Node {
        String value;
        boolean isLeaf;
        Node cero, uno;//creates children nodes
        Node[]children = {cero, uno};//gets the left or the right child node

        public Node(String value){//constructor
            this.value = value;
            cero = null;
            uno = null;
        }

        public String getValue() {//getter 
            return value;
        }

        public void setValue(String v) {//setter
            value = v;
        }
    }
    Node root;
    static int tSize;

    Trie(Node n){root = n;}

    Trie() {root = null; }

    public void setRoot(Node r) {root = r;}

    public static boolean insert(Trie t, String binary) {//insert method
        if(binary == null) {//no string
            return false;
        }
        for(int i = 0; i < binary.length(); i ++) {
            String b = binary.substring(i, i+1);
            if(!b.equals("0") && !b.equals("1"))//string is not a binary number
                return false;
        }
        if(t.root == null) {//base case
            Node bin = new Node(binary);
            bin.isLeaf = true;
            t.setRoot(bin);
            return true;
        }
        Node curr = t.root;
        String toReinsert = null;
        for(int i = 0; i < binary.length(); i ++) {
            String check = binary.substring(i,i+1);
            int chck = Integer.parseInt(check);
            if(curr.isLeaf) {
                if(curr.getValue().equals(binary)) {//string has already been inputted
                    return false;
                }
                toReinsert = curr.value;
                curr.setValue(null);
                curr.isLeaf = false;
            }
            if(curr.children[chck] == null) {
                curr.children[chck] = new Node(binary);
                curr.children[chck].isLeaf = true;
                if(toReinsert != null) {
                    insert(t, toReinsert);//reinserts value
                }  				
                return true;	
            }

            else
                curr = curr.children[chck];  
        }
        return false;
    }

    public static ArrayList <String> trieToList(Trie t) {//turns trie into a list, excludes null values
        ArrayList <String> list = new ArrayList < > ();
        helper(t.root, list);
        return list;
    }

    public static void helper(Node root, ArrayList <String> list) {//inorder traversal over the trie
        if (root != null) {
            if (root.children[0] != null) {
                helper(root.children[0], list);
            }
            if(root.value != null)
                list.add(root.value);
            if (root.children[1] != null) {
                helper(root.children[1], list);
            }
        }
    }

    public static String largest(Trie t) {//gets the largest value in a trie
        ArrayList <String> list = trieToList(t);
        String largest = list.get(list.size()-1);
        return largest;
    }

    public static String smallest(Trie t) {//smallest value in a trie
        ArrayList <String> list = trieToList(t);
        String smallest = list.get(0);
        return smallest;
    }

    public static int height(Trie t)  {//calculates the height of a trie
        if (t.root == null) 
            return 0; 
        else 
        { 
            /* compute the depth of each subtree */
            int left = height(new Trie(t.root.children[0])); 
            int right = height(new Trie(t.root.children[1])); 
            /* use the larger one */
            if (left > right) 
                return (left + 1); 
            else 
                return (right + 1); 
        } 
    }

    public static int size(Trie t) {//size of a trie
        traverse(t.root);
        int size = tSize;
        tSize = 0;
        return size;
    }

    public static void traverse(Node root) {//inorder traversal over the trie
        if (root != null) {
            if (root.children[0] != null) {
                traverse(root.children[0]);
            }
            if(root.value != null)
                tSize++;
            if (root.children[1] != null) {
                traverse(root.children[1]);
            }
        }
    }

    public static String search(Trie t, String binary) {//search method
        String[] bin = new String[binary.length()];
        for(int i = 0; i < binary.length(); i ++)
            bin[i] = binary.substring(i, i+1);
        int cum = 0;
        int check = 0;
        if(t.root == null)
            return "";
        Node curr = t.root;
        while(!curr.isLeaf) {//goes to the next leaf
            check = Integer.parseInt(bin[cum++]);//gets individual binary digits
            if(curr.children[check] == null) 
                curr = curr.children[(int)(Math.pow((check-1), 2))];//opposite child node
            else
                curr = curr.children[check];
        }
        return curr.value;
    }
}

