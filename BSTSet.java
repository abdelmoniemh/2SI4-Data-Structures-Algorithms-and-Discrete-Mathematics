import java.lang.Math;
import java.util.Queue;
import java.util.Stack;
public class BSTSet {

	private TNode root;
		
	public BSTSet() {
        root = null;
    }
	
	public BSTSet(int[] input){
		int[] SortedSet = this.ArraytoSortedSet(input);
		root = BSTSet(SortedSet);
	}

	public TNode BSTSet(int[] SortedSet) {
		if (SortedSet ==null)
			return null;
		int mid = (int)Math.ceil(SortedSet.length/2);
		TNode Node = new TNode(SortedSet[mid], null, null);
		Node.left = BSTSet(this.substring(SortedSet, 0, mid-1));
		Node.right = BSTSet(this.substring(SortedSet, mid+1, SortedSet.length-1));
		
		return Node;
		
    }

	public int[] ArraytoSortedSet(int[] input){ //O(n^2)
		int[] sorted = input;
		if (sorted == null)
			return null;
		int length = sorted.length;


		for (int i = 0;i<length-1;i++){ // bubble sort O(n^2)
			for (int j = 0;j<length-1-i;j++){
				if (sorted[j]>sorted[j+1]){
					int temp = sorted[j];
					sorted[j] = sorted[j+1];
					sorted[j+1] = temp;
				}
			}
		}

		int[] set = sorted;
		int index = 1;
		
		for (int i = 0;i<set.length-1;i++){ // O(n) to remove duplicates
			if (set[i] != set[i+1]){
				set[index++] = set[i+1];
			}
		}
		return set;
	}

	public int[] substring(int[] array, int start, int end){ //O(n)
		
		int[] substring = new int[end-start+1];
		if (start>end)
			return null;
		if (start == end){
			substring[0] = array[start];
			return substring;
		}
		for (int i = 0; i<substring.length;i++){
			substring[i] = array[i+start];
		}
		return substring;
	}
	
	public TNode getRoot(){
		return root;
	}

	public boolean isIn(int v) {
		TNode reference = root;
		if (reference == null){
			return false;
		}else if (v==reference.element){
			return true;
		} else if (v>reference.element){
			BSTSet right= new BSTSet();
			right.root = reference.right;
			return right.isIn(v);
		}else if (v<reference.element){
			BSTSet left= new BSTSet();
			left.root = reference.left;
			return left.isIn(v);
		} else {
			return false;
		}
		
	}

	public boolean isIn(int[] array, int v){
		boolean found = false;

		for (int i = 0;i<array.length;i++){
			if (v==array[i]){
				found = true;
			}
		}

		return found;
	}

	public void add(int v) {
		TNode reference = root;
		if (!isIn(v)){
			if (reference == null)
				reference = new TNode(v, null, null);
			else if (v>reference.element){
				BSTSet right= new BSTSet();
				right.root = reference.right;
				right.add(v);
				reference.right = right.root;
			} else {
				BSTSet left = new BSTSet();
				left.root = reference.left;
				left.add(v);
				reference.left = left.root;
			}
		}
		root = reference;
	}
	
	public boolean remove(int v) {
		TNode reference = root;
		if (isIn(v)){
		
		if (v > reference.element){ // recursivly solve for right side if v> root
			BSTSet right= new BSTSet();
			right.root = reference.right;
			right.remove(v);
			reference.right = right.root;
			root = reference;
			return true;
		} else if (v< reference.element){ // recursively solve for left side if v< root
			BSTSet left = new BSTSet();
			left.root = reference.left;
			left.remove(v);
			reference.left = left.root;
			root = reference;
			return true;
		} else { //arrived at node to be removed
			if (reference.element == v && reference.right == null && reference.left == null){ // leaf case
				reference = null;
				root = reference;
				return true;
			}else if (reference.element == v && reference.right == null && reference.left != null){ // case of one child
				reference = reference.left;
				root = reference;
				return true;
			} else if (reference.element == v && reference.right != null && reference.left == null){ // case of one child
				reference = reference.right;
				root = reference;
				return true;
			} else { // case of two childs
				BSTSet right = new BSTSet();
				right.root = reference.right;
				root.element = right.MinValue();
				BSTSet rightChild = new BSTSet();
				rightChild.root = right.root;
				rightChild.remove(right.MinValue());
				right.root.right = rightChild.root;
				reference.right = right.root;
				root = reference;
				return true;
			}
		}
			

		}
		return false;
		
	}

	public int MinValue(){
		TNode reference = root;
		while(reference.left != null){
			reference = reference.left;
		}
		return reference.element;
	}
	
	public BSTSet union(BSTSet s) {
		int[] input = InOrderTransversal(s);
		BSTSet Union = new BSTSet(input);
		int[] self = InOrderTransversal(this);

		for (int i = 0;i<size(root);i++){
			Union.add(self[i]);
		}

		int[] result = InOrderTransversal(Union);
		result = ArraytoSortedSet(result);
		BSTSet finalSet = new BSTSet(result);

		return finalSet;
	}

	public int[] InOrderTransversal(BSTSet set){
		int[] result = new int[set.size(set.root)];
		int i = 0;
		MyStack<TNode> Stack = new MyStack<TNode>();
		TNode node = set.root;

		while(node != null || Stack.size() > 0){

			while (node != null){
				Stack.push(node);
				node = node.left;
			}

			node = Stack.pop();
			result[i] = node.element;
			node = node.right;
			i++;
		}

		return result;
	}
	
	public BSTSet intersection(BSTSet s) {
		int[] self = InOrderTransversal(this); //returns sorted array
		int[] input = InOrderTransversal(s);
		String result = "";
		int i = 0, j = 0;
		
		while (i < self.length && j < input.length) { 
            if (self[i] < input[j])  // if j is greater increase i 
                i++; 
            else if (input[j] < self[i]) // if i is greater increase j
                j++; 
            else { // both same add to string storing intersection and increase both
                result += Integer.toString(self[i]) + " ";
				j++;
                i++; 
            } 
        }
		if (result == "" ){
			return new BSTSet();
		}


		String[] Intersection = result.split(" ");
		int[] finalSet = new int[Intersection.length];
		
		for (int x = 0;x<finalSet.length;x++){
			finalSet[x] = Integer.parseInt(Intersection[x]);
		}

		
		BSTSet newSet = new BSTSet(finalSet);
		return newSet;
	}
	
	public BSTSet difference(BSTSet s) {
		int[] self = InOrderTransversal(this); //returns sorted array
		int[] input = InOrderTransversal(s);
		String result = "";
		int i = 0, j = 0;
		
		while (i < self.length && j < input.length) { 
            if (self[i] < input[j] && !isIn(input, self[i])){// if j is greater increase i
				result += Integer.toString(self[i]) + " "; 
                i++; 
			}  else if (input[j] < self[i] && !isIn(self, input[j])) {// if i is greater increase j
				result += Integer.toString(input[j]) + " ";
                j++; 
			}	
            else { // both same add to string storing intersection and increase both
                
				j++;
                i++; 
            } 
        }
		if (result == "" ){
			return new BSTSet();
		}


		String[] Difference = result.split(" ");
		int[] finalSet = new int[Difference.length];
		
		for (int x = 0;x<finalSet.length;x++){
			finalSet[x] = Integer.parseInt(Difference[x]);
		}

		BSTSet newSet = new BSTSet(finalSet);
		return newSet;
	}

	public int size(TNode node) {
		TNode reference = node;
		if (reference == null)
			return 0;
		else{
			return (size(node.left) + size(node.right) + 1);
		}
	}

	public int size() {
		TNode reference = root;
		if (reference == null)
			return 0;
		else{
			return (size(reference.left) + size(reference.right) + 1);
		}
	}
	
	public int height() {
		TNode reference = root;
		if (reference == null)
			return -1;
		else {
			BSTSet left = new BSTSet();
			left.root = reference.left;
			BSTSet right = new BSTSet();
			right.root = reference.right;
			
			if (left.height()>right.height()){
				return (1+left.height());
			} else {
				return (1+right.height());
			}
		}
	}
	
	public void printBSTSet() {
        if (root == null)
            System.out.println("The set is empty");
        else{
            System.out.print("The set elements are: ");
            printBSTSet(root);
            System.out.print("\n");
        }
		
	}
	
	public void printBSTSet(TNode t) {
        if (t!=null){
            printBSTSet(t.left);
            System.out.print(" " + t.element +", ");
            printBSTSet(t.right);
        }
			
	}
	public String BSTSetString(TNode t){
		String string = "";
		if (t!=null){
            string += BSTSetString(t.left);
            string += (" " + t.element +", ");
            string += BSTSetString(t.right);
        }
		return string;
	}	
	
	public void printNonRec() {
		int[] output = InOrderTransversal(this);
		for (int i = 0;i<output.length;i++){
			System.out.printf(" %d, ",output[i]);
		}
	}
	
	public void printLevelOrder() {
		TNode reference = root;
		MyQueue<TNode> Queue = new MyQueue<TNode>();

		if (reference == null)
			return;

		Queue.enqueue(reference);
		while(!Queue.isEmpty()){
			//int size = Queue.getSize();
			
			TNode current = Queue.dequeue();
			System.out.printf(" %d, ",current.element);
			if (current.left != null){
				Queue.enqueue(current.left);
			}
			if (current.right != null){
				Queue.enqueue(current.right);
			}
			
		}
		return;
		
	}
	
	
}
