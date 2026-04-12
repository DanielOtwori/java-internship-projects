package level3.datastructures.bst;

public class BinarySearchTree {

    TreeNode root;

    //INSERT
    public TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }

        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        }

        return root;
    }

    // SEARCH
    public boolean search(TreeNode root, int value) {
        if (root == null) return false;

        if (value == root.value) return true;

        if (value < root.value)
            return search(root.left, value);
        else
            return search(root.right, value);
    }

    // FIND MIN (used in delete)
    private TreeNode findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    //DELETE
    public TreeNode delete(TreeNode root, int value) {

        if (root == null) return null;

        if (value < root.value) {
            root.left = delete(root.left, value);
        } else if (value > root.value) {
            root.right = delete(root.right, value);
        } else {
            // Node found

            // Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: One child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: Two children
            TreeNode minNode = findMin(root.right);
            root.value = minNode.value;
            root.right = delete(root.right, minNode.value);
        }

        return root;
    }

    // INORDER (Left, Root, Right)
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.value + " ");
            inorder(root.right);
        }
    }

    //PREORDER (Root, Left, Right)
    public void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    //POSTORDER (Left, Right, Root)
    public void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.value + " ");
        }
    }
}