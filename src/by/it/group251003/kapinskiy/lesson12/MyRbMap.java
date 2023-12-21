package by.it.group251003.kapinskiy.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node {
        Integer key;
        String value;

        Node left;
        Node right;
        Node parent;

        boolean color;

        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class NilNode extends Node {
        private NilNode() {
            super(0, "");
            this.color = BLACK;
        }
    }

    Node root = null;

    private int size = 0;

    private void rotateRight(Node node) {
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

    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    private void rotateLeft(Node node) {
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

    private Node searchNode(Integer key) {
        Node node = root;
        while (node != null) {
            if (key.equals(node.key)) {
                return node;
            } else if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return null;
    }

    private String insertNode(Integer key, String value) {
        Node node = root;
        Node parent = null;

        while (node != null) {
            parent = node;
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                String save = node.value;
                node.value = value;
                return save;
            }
        }

        Node newNode = new Node(key, value);
        newNode.color = RED;
        if (parent == null) {
            root = newNode;
        } else if (key.compareTo(parent.key) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        fixRedBlackPropertiesAfterInsert(newNode);
        size++;
        return null;
    }

    private void fixRedBlackPropertiesAfterInsert(Node node) {
        Node parent = node.parent;

        if (parent == null) {
            return;
        }

        if (parent.color == BLACK) {
            return;
        }

        Node grandparent = parent.parent;

        if (grandparent == null) {
            parent.color = BLACK;
            return;
        }

        Node uncle = getUncle(parent);

        if (uncle != null && uncle.color == RED) {
            parent.color = BLACK;
            grandparent.color = RED;
            uncle.color = BLACK;

            fixRedBlackPropertiesAfterInsert(grandparent);
        }

        else if (parent == grandparent.left) {
            if (node == parent.right) {
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(grandparent);

            parent.color = BLACK;
            grandparent.color = RED;
        }
        else {
            if (node == parent.left) {
                rotateRight(parent);
                parent = node;
            }

            rotateLeft(grandparent);

            parent.color = BLACK;
            grandparent.color = RED;
        }
    }

    private Node getUncle(Node parent) {
        Node grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    public String deleteNode(Integer key) {
        Node node = root;

        while (node != null && !node.key.equals(key)) {
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if (node == null) {
            return null;
        }

        String save = node.value;
        Node movedUpNode;
        boolean deletedNodeColor;

        if (node.left == null || node.right == null) {
            movedUpNode = deleteNodeWithZeroOrOneChild(node);
            deletedNodeColor = node.color;
        }

        else {
            Node inOrderSuccessor = findMinimum(node.right);

            node.key = inOrderSuccessor.key;
            node.value = inOrderSuccessor.value;

            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
            deletedNodeColor = inOrderSuccessor.color;
        }

        if (deletedNodeColor == BLACK) {
            fixRedBlackPropertiesAfterDelete(movedUpNode);

            if (movedUpNode.getClass() == NilNode.class) {
                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
            }
        }

        size--;
        return save;
    }

    private Node deleteNodeWithZeroOrOneChild(Node node) {
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left;
        }


        else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right;
        }

        else {
            Node newChild = node.color == BLACK ? new NilNode() : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private void fixRedBlackPropertiesAfterDelete(Node node) {
        if (node == root) {
            return;
        }

        Node sibling = getSibling(node);

        if (sibling.color == RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node);
        }

        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;

            if (node.parent.color == RED) {
                node.parent.color = BLACK;
            }

            else {
                fixRedBlackPropertiesAfterDelete(node.parent);
            }
        }

        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
        }
    }

    private void handleRedSibling(Node node, Node sibling) {
        sibling.color = BLACK;
        node.parent.color = RED;

        if (node == node.parent.left) {
            rotateLeft(node.parent);
        } else {
            rotateRight(node.parent);
        }
    }

    private void handleBlackSiblingWithAtLeastOneRedChild(Node node, Node sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;

        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.color = BLACK;
            sibling.color = RED;
            rotateRight(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.color = BLACK;
            sibling.color = RED;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }

        sibling.color = node.parent.color;
        node.parent.color = BLACK;
        if (nodeIsLeftChild) {
            sibling.right.color = BLACK;
            rotateLeft(node.parent);
        } else {
            sibling.left.color = BLACK;
            rotateRight(node.parent);
        }
    }

    private Node getSibling(Node node) {
        Node parent = node.parent;
        if (node == parent.left) {
            return parent.right;
        } else if (node == parent.right) {
            return parent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    private boolean isBlack(Node node) {
        return node == null || node.color == BLACK;
    }


    private Node findMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    void inOrder(StringBuilder sb, Node node) {
        if (node == null) return;
        inOrder(sb, node.left);
        sb.append(node.key.toString() + "=" + node.value.toString() + ", ");
        inOrder(sb, node.right);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (size > 0) {
            inOrder(sb, root);
            sb.setLength(sb.length() - 2);
        }
        return sb.append('}').toString();
    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }


    private void headMap(Node node, SortedMap<Integer, String> headMap, Integer toKey) {
        if (node == null) return;
        headMap(node.left, headMap, toKey);
        if (node.key.compareTo(toKey) < 0) {
            headMap.put(node.key, node.value);
            headMap(node.right, headMap, toKey);
        }
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap headMap = new MyRbMap();
        headMap(root, headMap, toKey);
        return headMap;
    }


    private void tailMap(Node node, SortedMap<Integer, String> tailMap, Integer fromKey) {
        if (node == null) return;
        tailMap(node.right, tailMap, fromKey);
        if (node.key.compareTo(fromKey) >= 0) {
            tailMap.put(node.key, node.value);
            tailMap(node.left, tailMap, fromKey);
        }
    }
    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap tailMap = new MyRbMap();
        tailMap(root, tailMap, fromKey);
        return tailMap;
    }

    @Override
    public Integer firstKey() {
        Node first = findMinimum(root);
        return first.key;
    }

    @Override
    public Integer lastKey() {
        Node last = root;
        while (last.right != null)
            last = last.right;
        return last.key;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return searchNode((Integer) key) != null;
    }


    private boolean containsValue(Node node, Object value){
        if (node == null) return false;
        if (node.value.equals(value)) return true;
        return (containsValue(node.left, value) || containsValue(node.right, value));
    }
    @Override
    public boolean containsValue(Object value) {
        return containsValue(root, value);
    }

    @Override
    public String get(Object key) {
        Node result = searchNode((Integer) key);
        return (result != null)? result.value : null;
    }

    @Override
    public String put(Integer key, String value) {
        return insertNode(key, value);
    }

    @Override
    public String remove(Object key) {
        return deleteNode((Integer) key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }


    private void postOrderTraversal(Node node) {
        if (node == null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
               node.parent.right = null;
            }
        } else {
            root = null;
        }
    }

    @Override
    public void clear() {
        postOrderTraversal(root);
        size = 0;
        root = null;
    }
}
