public class TreeFactory {
    // Metodo que genera un nuevo arbol
    public static final String SPLAY_TREE = "SplayTree";
    public static final String RED_BLACK_TREE = "RedBlackTree";

    public static Object generateTree(String type)
    {
        switch(type){
            case SPLAY_TREE:
                return new SplayTree<>();
            case RED_BLACK_TREE:
                return new BSTRedBlackTree<>();
            default:
                return null;
        }
    }
}
