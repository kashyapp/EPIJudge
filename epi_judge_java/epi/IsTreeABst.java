package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
//    printp(tree);
    if (tree == null) return true;
    else return helper(tree).isGood;
  }

  private static Pair helper(BinaryTreeNode<Integer> tree) {
    if (tree == null) return null;
    Pair left = helper(tree.left);
    Pair right = helper(tree.right);
    Pair result = new Pair();

    if (left == null) {
      result.min = tree.data;
    } else if (left.isGood && left.max <= tree.data) {
      result.min = left.min;
    } else {
      result.isGood = false;
    }

    if (right == null) {
      result.max = tree.data;
    } else if (right.isGood && tree.data <= right.min) {
      result.max = right.max;
    } else {
      result.isGood = false;
    }

    return result;
  }

  private static void printp(BinaryTreeNode<Integer> tree) {
    System.out.print("(");
    if (tree != null) {
      System.out.print(tree.data);
      if (tree.left != null || tree.right != null) {
        printp(tree.left);
        printp(tree.right);
      }
    }
    System.out.print(")");
  }

  private static class Pair {
    boolean isGood = true;
    int min;
    int max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
