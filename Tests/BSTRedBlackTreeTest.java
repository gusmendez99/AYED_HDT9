import org.junit.Test;

import static org.junit.Assert.*;

public class BSTRedBlackTreeTest {

    @Test
    public void put() {
        BSTRedBlackTree redBlackTree = new BSTRedBlackTree();
        redBlackTree.put("Adam", "Adán");
        redBlackTree.put("Africa", "África");
        boolean singleTranslation = (boolean) redBlackTree.contains("Adam");
        assertEquals(singleTranslation, true);
    }

    @Test
    public void get() {
        BSTRedBlackTree redBlackTree = new BSTRedBlackTree();
        redBlackTree.put("Adam", "Adán");
        redBlackTree.put("Africa", "África");
        String singleTranslation = (String) redBlackTree.get("Adam");
        assertEquals(singleTranslation, "Adán");

    }
}