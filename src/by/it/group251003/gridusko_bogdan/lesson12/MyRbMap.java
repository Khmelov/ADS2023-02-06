package by.it.group251003.gridusko_bogdan.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {
    enum Color {
        red, black
    }

    static class Node<Integer, String> {
        Integer key;
        String value;

        Node<Integer, String> left, right, parent;

        Color color;

        public Node(Color color, Integer key, String value) {
            this.color = color;
            this.key = key;
            this.value = value;
        }
    }

    Node<Integer, String> root;

    @Override
    public String toString() {
        if (root == null) {
            return "{}";
        }

        StringBuilder res = new StringBuilder().append("{");
        inorder(root, res);
        return res.replace(res.length() - 2, res.length(), "}").toString();
    }

    private void inorder(Node<Integer, String> node, StringBuilder res) {
        if (node == null)
            return;

        inorder(node.left, res);
        res.append(node.key).append("=").append(node.value).append(", ");
        inorder(node.right, res);
    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap subMap = new MyRbMap();
        headMap(root, toKey, subMap);
        return subMap;
    }

    void headMap(Node<Integer, String> node, Integer toKey, MyRbMap subMap) {
        if (node == null) {
            return;
        }

        if (node.key.compareTo(toKey) < 0) {
            subMap.put(node.key, node.value);
            headMap(node.right, toKey, subMap);
        }

        headMap(node.left, toKey, subMap);
    }


    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap subMap = new MyRbMap();
        tailMap(root, fromKey, subMap);
        return subMap;
    }

    void tailMap(Node<Integer, String> node, Integer fromKey, MyRbMap subMap) {
        if (node == null) {
            return;
        }

        if (node.key.compareTo(fromKey) >= 0) {
            subMap.put(node.key, node.value);
            tailMap(node.left, fromKey, subMap);
        }

        tailMap(node.right, fromKey, subMap);
    }

    @Override
    public Integer firstKey() {
        Node<Integer, String> node = firstNode(root);
        return node != null ? node.key : null;
    }

    private Node<Integer, String> firstNode(Node<Integer, String> node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public Integer lastKey() {
        Node<Integer, String> node = lastNode(root);
        return node != null ? node.key : null;
    }

    Node<Integer, String> lastNode(Node<Integer, String> node) {
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node<Integer, String> node) {
        if (node == null)
            return 0;

        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return containsKey((Integer) key, root) != null;
    }

    public Node<Integer, String> containsKey(Integer key, Node<Integer, String> node) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        if (cmp < 0)
            return containsKey(key, node.left);

        return containsKey(key, node.right);
    }

    @Override
    public boolean containsValue(Object value) {
        return containsValue(value.toString(), root);
    }

    private boolean containsValue(String value, Node<Integer, String> node) {
        if (node == null)
            return false;

        if (value.equals(node.value))
            return true;

        return containsValue(value, node.left) || containsValue(value, node.right);
    }

    @Override
    public String get(Object key) {
        Node<Integer, String> node = containsKey((Integer) key, root);
        if (node != null)
            return node.value;

        return null;
    }

    void smallRotateLeft(Node<Integer, String> node)
    {
        Node<Integer, String> right = node.right;
        node.right = right.left;
        if (right.left != null)
            right.left.parent = node;
        right.parent = node.parent;
        if (node.parent == null)
            root = right;
        else if (node == node.parent.left)
            node.parent.left = right;
        else
            node.parent.right = right;
        right.left = node;
        node.parent = right;
    }

    void smallRotateRight(Node<Integer, String> node)
    {
        Node<Integer, String> left = node.left;
        node.left = left.right;
        if (left.right != null)
            left.right.parent = node;
        left.parent = node.parent;
        if (node.parent == null)
            root = left;
        else if (node == node.parent.right)
            node.parent.right = left;
        else
            node.parent.left = left;
        left.right = node;
        node.parent = left;
    }

    void fixAfterInsert(Node<Integer, String> node) {
        while (node != root && node.color == Color.red && node.parent.color == Color.red) {
            Node<Integer, String> parent = node.parent;
            Node<Integer, String> grandparent = parent.parent;

            if (parent == grandparent.left) {
                Node<Integer, String> uncle = grandparent.right;
                if (uncle != null && uncle.color == Color.red) {
                    parent.color = Color.black;
                    uncle.color = Color.black;
                    grandparent.color = Color.red;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        node = parent;
                        smallRotateLeft(node);
                    }
                    parent.color = Color.black;
                    grandparent.color = Color.red;
                    smallRotateRight(grandparent);
                }
            } else {
                Node<Integer, String> uncle = grandparent.left;
                if (uncle != null && uncle.color == Color.red) {
                    parent.color = Color.black;
                    uncle.color = Color.black;
                    grandparent.color = Color.red;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        smallRotateRight(node);
                    }
                    parent.color = Color.black;
                    grandparent.color = Color.red;
                    smallRotateLeft(grandparent);
                }
            }
        }

        root.color = Color.black;
    }

    @Override
    public String put(Integer key, String value) {
        if (root == null) {
            root = new Node(Color.black, key, value);
        } else {
            Node<Integer, String> newNode = new Node(Color.red, key, value);
            Node<Integer, String> current = root;
            while (true) {
                newNode.parent = current;
                if (key.compareTo(current.key) < 0) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    } else {
                        current = current.left;
                    }
                } else if (key.compareTo(current.key) > 0) {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    } else {
                        current = current.right;
                    }
                } else {
                    String oldValue = current.value;
                    current.value = value;
                    return oldValue;
                }
            }

            fixAfterInsert(newNode);
        }
        return null;
    }

    Node<Integer, String> getSuccessor(Node<Integer, String> node) {
        Node<Integer, String> successor = null;
        Node<Integer, String> current = node.right;

        while (current != null) {
            successor = current;
            current = current.left;
        }

        return successor;
    }

    void deleteNode(Node<Integer, String> node) {
        Node<Integer, String> replacement;
        if (node.left != null && node.right != null) {
            Node<Integer, String> successor = getSuccessor(node);
            node.key = successor.key;
            node.value = successor.value;
            node = successor;
        }

        replacement = (node.left != null) ? node.left : node.right;

        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

            if (node.color == Color.black) {
                fixDeletion(replacement);
            }
        } else if (node.parent == null) {
            root = null;
        } else {
            if (node.color == Color.black) {
                fixDeletion(node);
            }

            if (node.parent != null) {
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else if (node == node.parent.right) {
                    node.parent.right = null;
                }
                node.parent = null;
            }
        }
    }

    Color getColor(Node<Integer, String> node) {
        return (node == null) ? Color.black : node.color;
    }

    void setColor(Node<Integer, String> node, Color color) {
        if (node != null) {
            node.color = color;
        }
    }
    void fixDeletion(Node<Integer, String> node) {
        while (node != root && getColor(node) == Color.black) {
            if (node == node.parent.left) {
                Node<Integer, String> sibling = node.parent.right;
                if (getColor(sibling) == Color.red) {
                    setColor(sibling, Color.black);
                    setColor(node.parent, Color.red);
                    smallRotateLeft(node.parent);
                    sibling = node.parent.right;
                }
                if (getColor(sibling.left) == Color.black && getColor(sibling.right) == Color.black) {
                    setColor(sibling, Color.red);
                    node = node.parent;
                } else {
                    if (getColor(sibling.right) == Color.black) {
                        setColor(sibling.left, Color.black);
                        setColor(sibling, Color.red);
                        smallRotateRight(sibling);
                        sibling = node.parent.right;
                    }
                    setColor(sibling, getColor(node.parent));
                    setColor(node.parent, Color.black);
                    setColor(sibling.right, Color.black);
                    smallRotateLeft(node.parent);
                    node = root;
                }
            } else {
                Node<Integer, String> sibling = node.parent.left;
                if (getColor(sibling) == Color.red) {
                    setColor(sibling, Color.black);
                    setColor(node.parent, Color.red);
                    smallRotateRight(node.parent);
                    sibling = node.parent.left;
                }
                if (getColor(sibling.right) == Color.black && getColor(sibling.left) == Color.black) {
                    setColor(sibling, Color.red);
                    node = node.parent;
                } else {
                    if (getColor(sibling.left) == Color.black) {
                        setColor(sibling.right, Color.black);
                        setColor(sibling, Color.red);
                        smallRotateLeft(sibling);
                        sibling = node.parent.left;
                    }
                    setColor(sibling, getColor(node.parent));
                    setColor(node.parent, Color.black);
                    setColor(sibling.left, Color.black);
                    smallRotateRight(node.parent);
                    node = root;
                }
            }
        }

        setColor(node, Color.black);
    }

    @Override
    public String remove(Object key) {
        Node<Integer, String> node = containsKey((Integer) key, root);
        if (node != null) {
            String oldValue = node.value;
            deleteNode(node);
            return oldValue;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        root = clear(root);
    }

    private Node<Integer, String> clear(Node<Integer, String> node) {
        if (node == null)
            return null;
        node.left = clear(node.left);
        node.right = clear(node.right);
        return null;
    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<String> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return null;
    }
}
