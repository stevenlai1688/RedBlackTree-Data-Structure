// --== CS400 File Header Information ==--
// Name: Steven Lai
// Email: sylai2@wisc.edu
// Team: HG
// TA: Hang Yin
// Lecturer: Gary Dahl
// Notes to Grader: 
import org.junit.Test;
import static org.junit.Assert.*;

public class TestRedBlackTree {
  /**
   * JUnit Test to verify that case 1 of the Red-Black Tree works 
   * Case 1: inserted node's parent and
   * the newly inserted red node, node are on the opposite side (i.e. if parent is left child and
   * the inserted child is also leftChild), and parent's sibling is black. to resolve it, rotate and
   * color swap the parent with its grandparent.
   */
  @Test
  public void checkOppositeSideBothRed() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

    rbt.insert(5);

    rbt.insert(10);
    // 15 is going to be red rightChild of 10, so it will trigger case 1
    rbt.insert(15);

    // System.out.println(rbt.root.toString());
    // should rotate and color swap parent with grandparent, and make 10 black
    assertEquals(true, rbt.root.toString().equals("[10, 5, 15]"));

  }

  /**
   * JUnit Test to verify that case 2 of the Red-Black Tree works 
   * Case 2: inserted node's parent and
   * the newly inserted red node are on the same side, (i.e. the parent is left child of grandparent
   * and child is right child of parent), and parent's sibling is black to resolve it, first perform
   * a rotation on parent and child so that child is now the parent then recursively perform Case 1
   * method, rotate the now-parent with the grandparent, and perform color swap.
   */
  @Test
  public void checkSameSideBothRed() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    rbt.insert(24);

    rbt.insert(28);
    // 8 is going to be red leftChild of 10, so it will trigger case 2
    rbt.insert(26);

    // 8 and 10 will first rotate, so 10 becomes rightChild of 8, then it will
    // recursively call case 1 to now
    // System.out.println(rbt.root.toString());
    assertEquals(true, rbt.root.toString().equals("[26, 24, 28]"));
  }

  /**
   * JUnit Test to verify that case 3 of the Red-Black Tree works 
   * Case 3: inserted node's parent and
   * parent's sibling is red, to resolve it, color swap both parent and parent's siblings to black,
   * then color swap grandparent to red, then recursively push up to tree, now checking for
   * red-black tree violations from the grandparent node and its parents. If grandparent node is the
   * root, then nothing needs to be changed after color changing parent and its siblings. root will
   * stay black.
   */
  @Test
  public void checkBothSiblingsRed() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

    rbt.insert(23);

    rbt.insert(7);

    rbt.insert(41);
    // 13 is the red leftChild of 7, whose sibling, 41 is also red, so it will trigger case 3
    rbt.insert(13);

    // System.out.println(rbt.root.toString());
    // System.out.println(rbt.root.leftChild.isBlack);
    // since this is case 3, this should color change grandparent with parent and its siblings, but
    // since grandparent is root, it should remain black
 
    assertEquals(true,
        rbt.root.leftChild.isBlack && rbt.root.isBlack && rbt.root.rightChild.isBlack);
  }
  /**
   * verify that all parts work together
   */
  @Test
  public void checkWholeThingWorks() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    rbt.insert(32);
    rbt.insert(43);
    rbt.insert(12);
    // case 2, then case 3
    rbt.insert(22);
    rbt.insert(45);
    rbt.insert(35);
    rbt.insert(46);
    assertEquals(true, rbt.root.toString().equals("[32, 12, 43, 22, 35, 45, 46]"));
  }
}
