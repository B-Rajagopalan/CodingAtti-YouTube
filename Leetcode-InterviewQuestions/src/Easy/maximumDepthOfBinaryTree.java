package Easy;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode insertNode(TreeNode root, int num) {
        if (root == null) return new TreeNode(num);
        if (num < root.val) root.left = insertNode(root.left, num);
        else root.right = insertNode(root.right, num);
        return root;
    }
}

public class maximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.insertNode(root, 2);
        System.out.println(maxDepth(root ));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
