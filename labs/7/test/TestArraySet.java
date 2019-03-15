
import org.junit.Test;
import static org.junit.Assert.*;

public class TestArraySet {


    private ISet<Integer> MakeASet(){
        ISet<Integer> newSet = new ArraySet<>();
        return newSet;
    }

    // add

    @Test
    public void testAdd_42() {
        ISet<Integer> a = MakeASet();
        a.add(42);
        assertTrue(a.contains(42));
        assertTrue(a.getSize() == 1);
    }

    @Test
    public void testAdd_negativeNum_35() {
        ISet<Integer> a = MakeASet();
        a.add(-35);
        assertTrue(a.contains(-35));
        assertTrue(a.getSize() == 1);
    }

    @Test
    public void testAdd_multipleNumbers_23_49_90() {
        ISet<Integer> a = MakeASet();

        a.add(23);
        a.add(49);
        a.add(90);

        assertTrue(a.contains(23));
        assertTrue(a.contains(49));
        assertTrue(a.contains(90));
        assertTrue(a.getSize() == 3);
    }

    // equals

    @Test
    public void test_contains_numNotPresent(){
        ISet<Integer> a = MakeASet();

        a.add(36);

        assertFalse(a.contains(42));
    }

    @Test
    public void test_contains_numPresent(){
        ISet<Integer> a = MakeASet();

        a.add(36);

        assertTrue(a.contains(36));
    }

    @Test
    public void test_contains_numInLastPosition(){
        ISet<Integer> a = MakeASet();

        a.add(36);
        a.add(-14);
        a.add(44);
        a.add(26);

        assertTrue(a.contains(26));
    }

    // union

    @Test
    public void test_union_withEmptySet(){
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();

        b.add(23);
        b.add(56);
        b.add(35);
        b.add(15);

        a.union(b);

        assertTrue(a.contains(23));
        assertTrue(a.contains(56));
        assertTrue(a.contains(35));
        assertTrue(a.contains(15));

    }

    @Test
    public void test_union_withNoLikeNumbers(){
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();
        a.add(45);
        a.add(20);
        a.add(12);
        a.add(17);

        b.add(23);
        b.add(56);
        b.add(35);
        b.add(15);

        a.union(b);

        assertTrue(a.contains(45));
        assertTrue(a.contains(20));
        assertTrue(a.contains(12));
        assertTrue(a.contains(17));
        assertTrue(a.contains(23));
        assertTrue(a.contains(56));
        assertTrue(a.contains(35));
        assertTrue(a.contains(15));
        assertTrue(a.getSize() == 8);
    }

    @Test
    public void test_union_differentSizes(){
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();
        a.add(45);
        a.add(20);

        b.add(23);
        b.add(56);
        b.add(35);
        b.add(15);

        a.union(b);

        assertTrue(a.contains(45));
        assertTrue(a.contains(20));
        assertTrue(a.contains(23));
        assertTrue(a.contains(56));
        assertTrue(a.contains(35));
        assertTrue(a.contains(15));
        assertTrue(a.getSize() == 6);
    }

    @Test
    public void test_union_likeNumbers(){
        int count = 0;
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();
        a.add(23);
        a.add(56);

        b.add(23);
        b.add(56);
        b.add(35);
        b.add(15);

        a.union(b);

        assertTrue(a.contains(23));
        assertTrue(a.contains(56));
        assertTrue(a.contains(35));
        assertTrue(a.contains(15));
        assertTrue(a.getSize() == 4);
    }

    // Intersect

    @Test
    public void test_intersect_withEmptySet(){
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();

        b.add(23);
        b.add(56);
        b.add(35);
        b.add(15);

        a.intersect(b);

        assertFalse(a.contains(23));
        assertFalse(a.contains(56));
        assertFalse(a.contains(35));
        assertFalse(a.contains(15));
        assertTrue(a.getSize() == 0);
    }

    @Test
    public void test_intersect_matchingValAtLastPos(){
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();

        a.add(45);
        a.add(20);
        a.add(12);
        a.add(17);

        b.add(23);
        b.add(56);
        b.add(35);
        b.add(17);

        a.intersect(b);

        assertTrue(a.contains(17));
        assertTrue(a.getSize() == 1);
    }

    @Test
    public void test_intersect_setsAreEqual(){
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();

        a.add(45);
        a.add(20);
        a.add(12);
        a.add(17);

        b.add(45);
        b.add(20);
        b.add(12);
        b.add(17);

        a.intersect(b);

        assertTrue(a.contains(45));
        assertTrue(a.contains(20));
        assertTrue(a.contains(12));
        assertTrue(a.contains(17));
        assertTrue(a.getSize() == 4);
    }

    // difference

    @Test
    public void test_difference_setsAreEqual(){
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();

        a.add(45);
        a.add(20);
        a.add(12);
        a.add(17);

        b.add(45);
        b.add(20);
        b.add(12);
        b.add(17);

        a.difference(b);

        assertTrue(a.getSize() == 0);
    }

    @Test
    public void test_difference_oneValSame(){
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();

        a.add(45);
        a.add(20);
        a.add(12);
        a.add(17);

        b.add(44);
        b.add(19);
        b.add(11);
        b.add(17);

        a.difference(b);

        assertTrue(a.contains(45));
        assertTrue(a.contains(20));
        assertTrue(a.contains(12));
        assertFalse(a.contains(17));
        assertTrue(a.getSize() == 3);
    }

    @Test
    public void test_difference_allValsDifferent(){
        ISet<Integer> a = MakeASet();
        ISet<Integer> b = MakeASet();

        a.add(45);
        a.add(20);
        a.add(12);
        a.add(17);

        b.add(44);
        b.add(19);
        b.add(11);
        b.add(16);

        a.difference(b);

        assertTrue(a.contains(45));
        assertTrue(a.contains(20));
        assertTrue(a.contains(12));
        assertTrue(a.contains(17));
        assertTrue(a.getSize() == 4);
    }
}
