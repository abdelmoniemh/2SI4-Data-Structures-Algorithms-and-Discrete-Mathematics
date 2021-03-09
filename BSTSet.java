import java.lang.Math;
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
		
		if (v > reference.element){
			BSTSet right= new BSTSet();
			right.root = reference.right;
			right.remove(v);
			reference.right = right.root;
			root = reference;
			return true;
		} else if (v< reference.element){
			BSTSet left = new BSTSet();
			left.root = reference.left;
			left.remove(v);
			reference.left = left.root;
			root = reference;
			return true;
		} else {
			if (reference.element == v && reference.right == null && reference.left == null){
				reference = null;
				root = reference;
				return true;
			}else if (reference.element == v && reference.right == null && reference.left != null){ // case of one child
				reference = reference.left;
				root = reference;
				return true;
			} else if (reference.element == v && reference.right != null && reference.left == null){
				reference = reference.right;
				root = reference;
				return true;
			} else {
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
		
		
		return new BSTSet();
	}
	
	public BSTSet intersection(BSTSet s) {
		
		
		return new BSTSet();
	}
	
	public BSTSet difference(BSTSet s) {
		
		
		return new BSTSet();
	}

	public int size() {
		
		int length = 0;
		
		return length;
	}
	
	public int height() {
		
		int depth = 0;
		
		return depth;
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
		
	}
	
	public void printLevelOrder() {
		
		
	}
	
	
}
