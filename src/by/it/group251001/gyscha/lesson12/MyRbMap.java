package by.it.group251001.gyscha.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    RbTNode Root;

    enum Color {
        RED, BLACK
    }

    class RbTNode {
        Integer key;
        String value;
        RbTNode parent, left, right;
        Color color;
        public RbTNode (Color color, Integer key, String value) {
            this.color = color;
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public String toString() {
        if (Root == null) return "{}";
        StringBuilder sb = new StringBuilder().append("{");
        inOrderTraversal(Root, sb);
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("}");
        return sb.toString();
    }

    private void inOrderTraversal(RbTNode node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.left, sb);
            sb.append(node.key + "=" + node.value + ", ");
            inOrderTraversal(node.right, sb);
        }
    }

    @Override
    public int size() {return size(Root);}

    int size(RbTNode node) {
        if (node == null) {return 0;}
        return size(node.left) + size(node.right) + 1;
    }

    @Override
    public boolean isEmpty() {return Root == null;}

    @Override
    public boolean containsKey(Object key) {return SearchKey((Integer) key, Root) != null;}

    @Override
    public String remove(Object key) {
        RbTNode node = SearchKey((Integer) key, Root);
        if (node != null) {
            String oldValue = node.value;
            deleteNode(node);
            return oldValue;
        }
        return null;
    }

    Color getColor(RbTNode node) {return (node == null) ? Color.BLACK : node.color;}

    void setColor(RbTNode node, Color color) {
        if (node != null) {node.color = color;}
    }

    @Override
    public void clear() {Root = clear(Root);}

    RbTNode clear(RbTNode node) {
        if (node == null) return null;
        node.left = clear(node.left);
        node.right = clear(node.right);
        return null;
    }

    @Override
    public Integer firstKey() {
        RbTNode first = firstNode(Root);
        return (first != null) ? first.key : null;
    }

    RbTNode firstNode(RbTNode node) {
        while (node != null && node.left != null) {node = node.left;}
        return node;
    }

    @Override
    public Integer lastKey() {
        RbTNode last = lastNode(Root);
        return (last != null) ? last.key : null;
    }

    RbTNode lastNode(RbTNode node) {
        while (node != null && node.right != null) {node = node.right;}
        return node;
    }

    void RotateLeft(RbTNode node) {
        RbTNode right = node.right;
        node.right = right.left;
        if (right.left != null) right.left.parent = node;
        right.parent = node.parent;
        if (node.parent == null) Root = right;
        else if (node == node.parent.left) node.parent.left = right;
        else node.parent.right = right;
        right.left = node;
        node.parent = right;
    }

    void RotateRight(RbTNode node) {
        RbTNode left = node.left;
        node.left = left.right;
        if (left.right != null) left.right.parent = node;
        left.parent = node.parent;
        if (node.parent == null) Root = left;
        else if (node == node.parent.right) node.parent.right = left;
        else node.parent.left = left;
        left.right = node;
        node.parent = left;
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap subMap = new MyRbMap();
        headMap(Root, toKey, subMap);
        return subMap;
    }

    void headMap(RbTNode node, Integer toKey, MyRbMap subMap) {
        if (node == null) {return;}
        if (node.key.compareTo(toKey) < 0) {
            subMap.put(node.key, node.value);
            headMap(node.right, toKey, subMap);
        }
        headMap(node.left, toKey, subMap);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap subMap = new MyRbMap();
        tailMap(Root, fromKey, subMap);
        return subMap;
    }

    void tailMap(RbTNode node, Integer fromKey, MyRbMap subMap) {
        if (node == null) {return;}
        if (node.key.compareTo(fromKey) >= 0) {
            subMap.put(node.key, node.value);
            tailMap(node.left, fromKey, subMap);
        }
        tailMap(node.right, fromKey, subMap);
    }

    RbTNode SearchKey(Integer key, RbTNode node) {
        if (node == null) return null;
        int comparison = key.compareTo(node.key);
        if (comparison == 0) return node;
        return SearchKey(key, comparison < 0 ? node.left : node.right);
    }

    @Override
    public boolean containsValue(Object value) {return containsValue(Root, value);}

    boolean containsValue(RbTNode node, Object value) {
        if (node == null) {return false;}
        if (value.equals(node.value)) {return true;}
        return containsValue(node.left, value) || containsValue(node.right, value);
    }

    @Override
    public String get(Object key) {
        RbTNode node = SearchKey((Integer) key, Root);
        return (node != null) ? node.value : null;
    }

    @Override
    public String put(Integer key, String value) {
        if (Root == null) {Root = new RbTNode(Color.BLACK, key, value);}
        else {
            RbTNode newNode = new RbTNode(Color.RED, key, value);
            RbTNode current = Root;
            while (true) {
                newNode.parent = current;
                if (key.compareTo(current.key) < 0) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    } else {current = current.left;}
                } else if (key.compareTo(current.key) > 0) {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    } else {current = current.right;}
                } else {
                    String oldValue = current.value;
                    current.value = value;
                    return oldValue;
                }
            }
            balanceAfterInsert(newNode);
        }
        return null;
    }

    void balanceAfterInsert(RbTNode node) {
        while (node != Root && node.color == Color.RED && node.parent.color == Color.RED) {
            RbTNode parent = node.parent;
            RbTNode grandparent = parent.parent;

            if (parent == grandparent.left) {
                RbTNode uncle = grandparent.right;
                if (uncle != null && uncle.color == Color.RED) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        node = parent;
                        RotateLeft(node);
                    }
                    parent.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    RotateRight(grandparent);
                }
            } else {
                RbTNode uncle = grandparent.left;
                if (uncle != null && uncle.color == Color.RED) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        RotateRight(node);
                    }
                    parent.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    RotateLeft(grandparent);
                }
            }
        }
        Root.color = Color.BLACK;
    }

    RbTNode getSuccessor(RbTNode node) {
        RbTNode successor = null;
        RbTNode current = node.right;
        while (current != null) {
            successor = current;
            current = current.left;}
        return successor;
    }

    void deleteNode(RbTNode node) {
        RbTNode replacement;
        if (node.left != null && node.right != null) {
            RbTNode successor = getSuccessor(node);
            node.key = successor.key;
            node.value = successor.value;
            node = successor;
        }
        replacement = (node.left != null) ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {Root = replacement;}
            else if (node == node.parent.left) {node.parent.left = replacement;}
            else {node.parent.right = replacement;}
            if (node.color == Color.BLACK) {balanceDeletion(replacement);}}
            else if (node.parent == null) {Root = null;}
            else {
            if (node.color == Color.BLACK) {balanceDeletion(node);}
            if (node.parent != null) {
                if (node == node.parent.left) {node.parent.left = null;}
                else if (node == node.parent.right) {node.parent.right = null;}
                node.parent = null;
            }
        }
    }

    void balanceDeletion(RbTNode node) {
        while (node != Root && getColor(node) == Color.BLACK) {
            if (node == node.parent.left) {
                RbTNode sibling = node.parent.right;
                if (getColor(sibling) == Color.RED) {
                    setColor(sibling, Color.BLACK);
                    setColor(node.parent, Color.RED);
                    RotateLeft(node.parent);
                    sibling = node.parent.right;
                }
                if (getColor(sibling.left) == Color.BLACK && getColor(sibling.right) == Color.BLACK) {
                    setColor(sibling, Color.RED);
                    node = node.parent;
                } else {
                    if (getColor(sibling.right) == Color.BLACK) {
                        setColor(sibling.left, Color.BLACK);
                        setColor(sibling, Color.RED);
                        RotateRight(sibling);
                        sibling = node.parent.right;
                    }
                    setColor(sibling, getColor(node.parent));
                    setColor(node.parent, Color.BLACK);
                    setColor(sibling.right, Color.BLACK);
                    RotateLeft(node.parent);
                    node = Root;
                }
            } else {
                RbTNode sibling = node.parent.left;
                if (getColor(sibling) == Color.RED) {
                    setColor(sibling, Color.BLACK);
                    setColor(node.parent, Color.RED);
                    RotateRight(node.parent);
                    sibling = node.parent.left;
                }
                if (getColor(sibling.right) == Color.BLACK && getColor(sibling.left) == Color.BLACK) {
                    setColor(sibling, Color.RED);
                    node = node.parent;
                } else {
                    if (getColor(sibling.left) == Color.BLACK) {
                        setColor(sibling.right, Color.BLACK);
                        setColor(sibling, Color.RED);
                        RotateLeft(sibling);
                        sibling = node.parent.left;
                    }
                    setColor(sibling, getColor(node.parent));
                    setColor(node.parent, Color.BLACK);
                    setColor(sibling.left, Color.BLACK);
                    RotateRight(node.parent);
                    node = Root;
                }
            }
        }
        setColor(node, Color.BLACK);
    }


    @Override
    public Set<Integer> keySet() {return null;}

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {}

    @Override
    public Collection<String> values() {return null;}

    @Override
    public Set<Entry<Integer, String>> entrySet() {return null;}

    @Override
    public Comparator<? super Integer> comparator() {return null;}

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {return null;}
}
