public class BSTSet {

	private TNode root;
		
	public BSTSet() {
        root = null;
    }
	
	public BSTSet(int[] input) {
		
		root.element = 0;
    }
	
	public boolean isIn(int v) {
		
		return false;
	}

	public void add(int v) {
		
	}
	
	public void remove(int v) {
		
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
	
	public void printNonRec() {
		
	}
	
	public void printLevelOrder() {
		
		
	}
	
	
}
