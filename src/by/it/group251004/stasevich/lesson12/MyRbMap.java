package by.it.group251004.stasevich.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {
     Node root;

    class Node {
        Integer key;
        String value;
        Node parent, left, right;
        boolean color;
        public Node(boolean color, Integer key, String value) {
            this.color = color;
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    int size( Node node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + 1;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return searchNode((Integer) key) != null;
    }

    Node searchNode(int key) {
        Node node = root;
        while (node != null) {
            if (key == node.key) {
                return node;
            } else if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public boolean containsValue(Object value) {
        return containsValue(root, value);
    }

    boolean containsValue( Node node, Object value) {
        if (node == null) {
            return false;
        }
        if (value.equals(node.value)) {
            return true;
        }
        return containsValue(node.left, value) || containsValue(node.right, value);
    }

    @Override
    public String get(Object key) {
         Node node = searchNode((Integer) key);
        return (node != null) ? node.value : null;
    }

    @Override
    public String put(Integer key, String value) {
        if (root == null) {
            root = new Node(true, key, value);
        } else {
            Node newNode = new Node(false, key, value);
            Node current = root;
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
                    String old = current.value;
                    current.value = value;
                    return old;
                }
            }
            balanceInsert(newNode);
        }
        return null;
    }

    Node getUncle(Node parent) {
        Node grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    void balanceInsert( Node node) {
        Node parent = node.parent;
        if (parent == null) {
            return;
        }

        if (parent.color == true) {
            return;
        }

        Node grandparent = parent.parent;

        if (grandparent == null) {

            parent.color = true;
            return;
        }

        Node uncle = getUncle(parent);

        if (uncle != null && uncle.color == false) {
            parent.color = true;
            grandparent.color = false;
            uncle.color = true;

            balanceInsert(grandparent);
        }

        else if (parent == grandparent.left) {

            if (node == parent.right) {
                rotateLeft(parent);

                parent = node;
            }

            rotateRight(grandparent);

            parent.color = true;
            grandparent.color = false;
        }

        else {
            if (node == parent.left) {
                rotateRight(parent);

                parent = node;
            }
            rotateLeft(grandparent);
            parent.color = true;
            grandparent.color = false;
        }
    }

     Node getSuccessor( Node node) {
         Node successor = null;
         Node current = node.right;
        while (current != null) {
            successor = current;
            current = current.left;
        }
        return successor;
    }

    void deleteNode( Node node) {
         Node replace;
        if (node.left != null && node.right != null) {
             Node successor = getSuccessor(node);
            node.key = successor.key;
            node.value = successor.value;
            node = successor;
        }
        replace = (node.left != null) ? node.left : node.right;
        if (replace != null) {
            replace.parent = node.parent;
            if (node.parent == null) {
                root = replace;
            } else if (node == node.parent.left) {
                node.parent.left = replace;
            } else {
                node.parent.right = replace;
            }

            if (node.color ==  true) {
                balanceDeletion(replace);
            }
        } else if (node.parent == null) {
            root = null;
        } else {
            if (node.color ==  true) {
                balanceDeletion(node);
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

    void rotateLeft( Node node)
    {
        Node parent = node.parent;
        Node rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    void rotateRight( Node node)
    {
        Node parent = node.parent;
        Node leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    void balanceDeletion( Node node) {
        if (node == root) {
            return;
        }

        Node sibling = getSibling(node);

        // Case 2: Red sibling
        if (sibling.color == false) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node);
        }

        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = false;

            if (node.parent.color == false) {
                node.parent.color = true;
            }
            else {
                balanceDeletion(node.parent);
            }
        }
        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
        }
    }

    void handleBlackSiblingWithAtLeastOneRedChild(Node node, Node sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;
        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.color = true;
            sibling.color = false;
            rotateRight(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.color = true;
            sibling.color = false;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }
        sibling.color = node.parent.color;
        node.parent.color = true;
        if (nodeIsLeftChild) {
            sibling.right.color = true;
            rotateLeft(node.parent);
        } else {
            sibling.left.color = true;
            rotateRight(node.parent);
        }
    }

    boolean isBlack(Node node) {
        return node == null || node.color == true;
    }

    void handleRedSibling(Node node, Node sibling) {
        sibling.color = true;
        node.parent.color = false;
        if (node == node.parent.left) {
            rotateLeft(node.parent);
        } else {
            rotateRight(node.parent);
        }
    }

    Node getSibling(Node node) {
        Node parent = node.parent;
        if (node == parent.left) {
            return parent.right;
        } else if (node == parent.right) {
            return parent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    @Override
    public String remove(Object key) {
         Node node = searchNode((Integer) key);
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

     Node clear( Node node) {
        if (node == null)
            return null;
        node.left = clear(node.left);
        node.right = clear(node.right);
        return null;
    }

    @Override
    public Integer firstKey() {
         Node first = firstNode(root);
        return (first != null) ? first.key : null;
    }

     Node firstNode( Node node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public Integer lastKey() {
         Node last = lastNode(root);
        return (last != null) ? last.key : null;
    }

     Node lastNode( Node node) {
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node;
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
        by.it.group251004.krutko.lesson12.MyRbMap subMap = new by.it.group251004.krutko.lesson12.MyRbMap();
        headMap(root, toKey, subMap);
        return subMap;
    }

    void headMap( Node node, Integer toKey, by.it.group251004.krutko.lesson12.MyRbMap subMap) {
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
        by.it.group251004.krutko.lesson12.MyRbMap subMap = new by.it.group251004.krutko.lesson12.MyRbMap();
        tailMap(root, fromKey, subMap);
        return subMap;
    }

    void tailMap(Node node, Integer fromKey, by.it.group251004.krutko.lesson12.MyRbMap subMap) {
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
    public String toString() {
        if (root == null)
            return "{}";
        StringBuilder sb = new StringBuilder().append("{");
        print(root, sb);
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("}");
        return sb.toString();
    }

    void print( Node node, StringBuilder sb) {
        if (node != null) {
            print(node.left, sb);
            sb.append(node.key + "=" + node.value + ", ");
            print(node.right, sb);
        }
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
