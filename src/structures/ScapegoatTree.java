package structures;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;

  @Override
  public void add(T t) {
    // TODO: Implement the add() method
    upperBound++;
    BSTNode <T> node= new BSTNode<T>(t, null, null);
    root = addToSubtree(root, node);
    if(height() > (Math.log(upperBound)/Math.log(1.5))){
      BSTNode <T> addedNode = node;
      while (3 * subtreeSize(node) <= 2 * subtreeSize(getParent(node))){
        node = getParent(node);
      }
      BSTNode<T> w = getParent(node); 

      //Check position to insert
      BSTNode<T> parent = getParent(w);
      boolean left = node.getData().compareTo(w.getData()) < 0;

      ScapegoatTree <T> subTree = new ScapegoatTree<>();
      getSubTree(subTree, w, addedNode);
      subTree.balance(); //balance 
      BSTNode<T> newRoot = subTree.getRoot();
      if (left){
        parent.setLeft(newRoot); 
      }
      else {
        parent.setRight(newRoot);
      }
    }
  }

  private void getSubTree(ScapegoatTree <T> tree, BSTNode<T> node, BSTNode<T> end) {
    if (node != null) {
      getSubTree(tree, node.getLeft(), end);
      tree.add(node.getData());
      getSubTree(tree, node.getRight(), end);
    }
  }

  private BSTNode<T> getParent(BSTNode<T> key) {
   return BSTGetParentRecursive(root, key, null);
  }

  private BSTNode<T> BSTGetParentRecursive(BSTNode<T> node, BSTNode<T> key, BSTNode <T> parent) {
    if (node == null){
        return null;
    }
    if (node.getData().compareTo(key.getData()) == 0){
      return parent;
    }
    if (node.getData().compareTo(key.getData()) < 0) {
        return BSTGetParentRecursive(node.getRight(), key, node);
    }
    return BSTGetParentRecursive(node.getLeft(), key, node);
  }

  @Override
  public boolean remove(T element) {
    // TODO: Implement the remove() method
    boolean result = contains(element);
    // System.out.println(upperBound);
    // System.out.println(size());
    if (result) {
      root = removeFromSubtree(root, element);
    }
    if (upperBound > 2 * size()){
      balance();
      upperBound = size();
    }
    return true;
  }

  public static void main(String[] args) {
    BSTInterface<Integer> tree = new ScapegoatTree<Integer>();
    //You can test your Scapegoat tree here.
    /*for (String r : new String[] {"0", "1", "2", "3", "4"}) {
      tree.add(r);
    }*/
    for (int i : new int[] {3, 1, 5, 0, 4, 2, 6}) {
      tree.add(i);
    }

    for (int i : new int[] {1, 2, 0, 4}) {
      tree.remove(i);
    }
  }
}
