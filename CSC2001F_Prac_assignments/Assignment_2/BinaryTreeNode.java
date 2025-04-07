public class BinaryTreeNode{
        public BinaryTreeNode left;   //this will be an object that will assign a node a left link with another node
        public BinaryTreeNode right;  //this will be an object that will assign a node a right link with another node
        public String data;
		public int height = 0;

        public BinaryTreeNode(String data){this.data = data;}

        public BinaryTreeNode getLeft(){return left;}
        public BinaryTreeNode getRight(){return right;} 
    }
