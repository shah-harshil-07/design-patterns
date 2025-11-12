import java.util.*;

class TreeType {
    private final String name;
    private final String color;
    private final String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public String getTexture() {
        return this.texture;
    }
}

class Tree {
    // Attributes that keep on changing 
    private final int x;
    private final int y;

    // Attributes that remain constant
    private final TreeType treeType;

    public Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void draw() {
        System.out.println("Drawing tree at (" + x + ", " + y + ") with type " + this.treeType.getName());
    }
}

class TreeTypeFactory {
    static Map<String, TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String treeTypeKey = name + "-" + color + "-" + texture;

        if (treeTypeMap.containsKey(treeTypeKey)) {
            return treeTypeMap.get(treeTypeKey);
        }

        TreeType treeType = new TreeType(name, color, texture);
        treeTypeMap.put(treeTypeKey, treeType);
        return treeType;
    }
}

class Forest {
    private final List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        Tree tree = new Tree(x, y, TreeTypeFactory.getTreeType(name, color, texture));
        trees.add(tree);
    }

    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Forest forest = new Forest();

        for(int i = 0; i < 10; i++) {
            forest.plantTree(i, i, "Oak", "Green", "Rough");
        }

        forest.draw();
        System.out.println("Planted 10 trees.");
    }
}
