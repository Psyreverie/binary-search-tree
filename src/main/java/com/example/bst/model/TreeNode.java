package com.example.bst.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode {
    @JsonProperty("value")
    private int value;

    @JsonProperty("left")
    private TreeNode left;

    @JsonProperty("right")
    private TreeNode right;


    public TreeNode() {
    }

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }


    public static TreeNode insert(TreeNode root, int value) {
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


    public TreeNode insertValue(int value) {
        return TreeNode.insert(this, value);
    }


    public boolean contains(int value) {
        if (this.value == value) {
            return true;
        } else if (value < this.value && left != null) {
            return left.contains(value);
        } else if (value > this.value && right != null) {
            return right.contains(value);
        }
        return false;
    }


    public int getHeight() {
        int leftHeight = (left != null) ? left.getHeight() : 0;
        int rightHeight = (right != null) ? right.getHeight() : 0;

        return 1 + Math.max(leftHeight, rightHeight);
    }


    public int getNodeCount() {
        int count = 1; // Current node

        if (left != null) {
            count += left.getNodeCount();
        }
        if (right != null) {
            count += right.getNodeCount();
        }

        return count;
    }


    public List<Integer> inOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        inOrderTraversalHelper(result);
        return result;
    }

    private void inOrderTraversalHelper(List<Integer> result) {
        if (left != null) {
            left.inOrderTraversalHelper(result);
        }
        result.add(value);
        if (right != null) {
            right.inOrderTraversalHelper(result);
        }
    }


    public List<Integer> preOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        preOrderTraversalHelper(result);
        return result;
    }

    private void preOrderTraversalHelper(List<Integer> result) {
        result.add(value);
        if (left != null) {
            left.preOrderTraversalHelper(result);
        }
        if (right != null) {
            right.preOrderTraversalHelper(result);
        }
    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", left=" + (left != null ? left.value : "null") +
                ", right=" + (right != null ? right.value : "null") +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TreeNode treeNode = (TreeNode) obj;

        if (value != treeNode.value) return false;
        if (!Objects.equals(left, treeNode.left)) return false;
        return Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}