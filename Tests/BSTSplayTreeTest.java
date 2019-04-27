import org.junit.Test;

import static org.junit.Assert.*;

public class BSTSplayTreeTest {

    @Test
    public void put() {
        BSTSplayTree splayTree = new BSTSplayTree();
        splayTree.put("Adam", "Adán");
        splayTree.put("Africa", "África");
        boolean singleTranslation = (boolean) splayTree.contains("Adam");
        assertEquals(singleTranslation, true);
    }

    @Test
    public void get() {
        BSTSplayTree splayTree = new BSTSplayTree();
        splayTree.put("Adam", "Adán");
        splayTree.put("Africa", "África");
        String singleTranslation = (String) splayTree.get("Adam");
        assertEquals(singleTranslation, "Adán");
    }
}