import java.util.Scanner;

//Class data structure for storing binary search tree elements
class Node { 
	int data; 
	Node left;
	Node right;
	static int spacingBuffer = 0;

	//Enum for identifying node type when printing
	public enum NodeType {
		LeftNode,
		RightNode,
		RootNode
	}

	/**
	 * Constructs a new node.
	 * @param d The data value of the new node.
	 */
	Node(int d){
		data = d; 
		left=null;
		right=null;
	}
	
	//Prints spacing based on the current spacing buffer. Used to make printed nodes easier to read.
	void printSpacing(){
		for (int i = 0; i < spacingBuffer; i++) {
			System.out.print("  ");
		}
	}

	//Helper function for printing nodes in a readable format
	void PrintNodeType(NodeType ntype){
		switch(ntype){
			case LeftNode:
				System.out.print("Left:");
				break;
			case RightNode:
				System.out.print("Right:");
				break;
			case RootNode:
				System.out.print("Root:");
				break;
		}
	}
	
	/**
	 * Inserts a new node into the correct place in the node hierarchy
	 * @param newNode The node to be inserted
	 */
	public void insert(Node newNode) 
	{ 
		if(data>=newNode.data)
		{
			if(left != null){
				//If the new node should be inserted to the left and there is already a node to the left then call insert recursively on that left node
				left.insert(newNode);
			}
			else{
				//If the new node should be inserted to the left and the left is empty then insert it there
				left = newNode;
			}
		}
		else
		{
			if(right != null){
				//If the new node should be inserted to the right and there is already a node to the left then call insert recursively on that right node
				right.insert(newNode);
			}
			else{
				//If the new node should be inserted to the right and the right is empty then insert it there
				right = newNode;
			}
		}
	} 
  
	//Display the content of the tree in pre-order format
	public void preOrder(NodeType ntype) 
	{
		PrintNodeType(ntype);
		//Print data before children
		System.out.println(data);
		//Increment spacing buffer for nicer printing when printSpacing is called
		spacingBuffer++;
		//If there is a node to the left then print its contents
		if(left!=null)
		{
			printSpacing();
			left.preOrder(NodeType.LeftNode);
		}
		//If there is a node to the right then print its contents
		if(right != null)
		{
			printSpacing();
			right.preOrder(NodeType.RightNode);
		}
		spacingBuffer--;
	} 
	
	//Display the content of the tree in post-order format
	public void postOrder(NodeType ntype) 
	{
          //WRITE YOUR CODE HERE
		
	} 
	
	//Display the content of the tree in in-order format
	public void inOrder(NodeType ntype) 
	{
		//WRITE YOUR CODE HERE
	}

	/**
	 * Removes a node with a specific data value from the node hierarchy.
	 * @param x	The data value of the node we want to remove.
	 * @return This node if this node should not be removed, left if this node should be removed.
	 */
	Node remove(int x){

		//WRITE YOUR CODE HERE
	}
	
	/**
	 * Gets the leftmost child of the right node
	 * @return 	The leftmost child node.
	 * 			Will be this.right if this.right has no children to the left, or it will be null if this.right is null.
	 */
	Node getRMin()
	{
		//WRITE YOUR CODE HERE
        }

}

//An implementation of a binary search tree data structure
class BinarySearchTree{ 

	Node root;
	
	/**
	 * Inserts a new value into the binary search tree.
	 * @param data The data value of the new node to be inserted.
	 */
	public void insert(int data){
		// We create the new node 
		Node newNode = new Node(data); 
		//if root is null then we can insert it into the root
		if(root==null)
		{
			root=newNode;
			System.out.println("adding to an empty tree");
		}
		else
		{
			//if tree is not empty then we need to navigate deeper to place it in the correct position in the tree
			root.insert(newNode);
		}
	}
	
	//Displays the content of the tree in pre-order format
	public void preOrder() { 
		System.out.println();
		if(root!=null)
		{
			root.preOrder(Node.NodeType.RootNode);
		}
		else
		{
			System.out.println("\nThe tree is empty.");	
		}  
		System.out.println();
	}
	
	//Displays the content of the tree in post-order format
	public void postOrder() {
		System.out.println();
		if(root!=null)
		{
			root.postOrder(Node.NodeType.RootNode);
		}
		else
		{
			System.out.println("\nThe tree is empty.");	
		}
		System.out.println();
	}
	
	//Displays the content of the tree in in-order format
	public void inOrder() { 
		System.out.println();
		if(root!=null)
		{
			root.inOrder(Node.NodeType.RootNode);
		}
		else
		{
			System.out.println("\nThe tree is empty.");
		}
		System.out.println();
	}
	
	/**
	 * Removes a node with a specific data value from the node hierarchy.
	 * @param x	The data value of the node we want to remove.
	 */
	public void remove(int x)
	{
		//Handle empty tree
		if(root == null){
			System.out.println("The tree is empty.");
		}
		//If tree is not empty then we can do removal via the node class
		else{
			root = root.remove(x);
		}
		System.out.println();
	}

    public static void main(String[] args) 
    { 
        //create a new bst
        BinarySearchTree bst = new BinarySearchTree(); 
        
        //Create variable to read from keyboard
        Scanner in=new Scanner(System.in);
		int option, x;
		
		int[] testNodes = {
			5,
			2,
			4, 
			9, 
			11,
			6,
			8
		};

		for (int i = 0; i < testNodes.length; i++) {
			bst.insert(testNodes[i]);
		}

		//bst.remove(5); not implemented yet
		bst.preOrder();
		
        do
		{
			System.out.println("Select your option:");
			System.out.println("0: Quit the programe");
			System.out.println("1: Insert number in the bst");
			System.out.println("2: Traverse pre order");
			System.out.println("3: Traverse in order");
			System.out.println("4: Traverse post order");
			System.out.println("5: Remove a number in the bst");
			
			option=in.nextInt();
			switch(option){
				case 0:
					System.out.println("Good bye!");
					break;
				case 1:
					System.out.println("What number do you want to insert?");
					x=in.nextInt();
					bst.insert(x);
					break;
				case 2:
					bst.preOrder();
					break;
				case 3:
					bst.inOrder(); 
					break;
				case 4:
					bst.postOrder();
					break;
				case 5:
					System.out.println("What number do you want to remove?");
					x=in.nextInt();
					bst.remove(x);
					break;
				default:
					System.out.println("That is not an option");
					break;
			}
			
       } while(option!=0);

	   in.close();
    } 
} 
