package by.it.group251003.kapinskiy.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {



    private static class Node<Integer, String> {
        Integer key;
        String value;

        Node left;
        Node right;
        Node parent;

        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root = null;
    private int size = 0;

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

    private String insert(Node<Integer, String> node, Integer key, String value) {
        if (root == null) {
            root = new Node(key, value);
            node = root;
        } else if (key.compareTo(node.key) == 0) {
            String prev = node.value;
            node.value = value;
            splay(node);
            return prev;
        } else if (key.compareTo(node.key) < 0) {
            if (node.left == null) {
                node.left = new Node(key, value);
                node.left.parent = node;
                splay(node.left);
            } else return insert(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null) {
                node.right = new Node(key, value);
                node.right.parent = node;
                splay(node.right);
            } else return insert(node.right, key, value);
        }
        size++;
        return null;
    }

    Node findMax(Node node) {
        if (node == null) return null;
        if (node.right == null) return node;
        return findMax(node.right);
    }



    private void splay(Node<Integer, String> node) {
        while (node != root) {
            Node parent = node.parent;
            if (node.parent.parent == null) {
                if (isLeftChild(node)) {
                    rotateRight(parent);
                } else {
                    rotateLeft(parent);
                }
            } else if (isLeftChild(node) && isLeftChild(parent)) {
                rotateRight(node.parent.parent);
                rotateRight(parent);
            } else if (isRightChild(node) && isRightChild(parent)) {
                rotateLeft(node.parent.parent);
                rotateLeft(parent);
            } else if (isLeftChild(node) && isRightChild(parent)) {
                rotateRight(parent);
                rotateLeft(node.parent);
            } else {
                rotateLeft(parent);
                rotateRight(node.parent);
            }
        }
    }

    private boolean isLeftChild(Node node) {
        if (node == null || node.parent == null) return false;
        if (node.parent.right == null) return true;
        return node.parent.left == node;
    }

    private boolean isRightChild(Node node) {
        if (node == null || node.parent == null) return false;
        if (node.parent.left == null) return true;
        return node.parent.right == node;
    }

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

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }


    @Override
    public Integer lowerKey(Integer key) {
        Node<Integer, String> lower = null;
        Node<Integer, String> tmp = root;
        while (tmp != null){
            if (key.compareTo(tmp.key) > 0){
                lower = tmp;
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
        }
        if (lower != null)
            splay(lower);
        return  lower.key;
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        Node<Integer, String> res = find (root, key);
        if (res != null){
            splay(res);
            return res.key;
        }
        return  lowerKey(key);
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        Node<Integer, String> res = find (root, key);
        if (res != null){
            splay(res);
            return res.key;
        }
        return  higherKey(key);
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Integer higherKey(Integer key) {
        Node<Integer, String> higher = null;
        Node<Integer, String> tmp = root;
        while (tmp != null){
            if (key.compareTo(tmp.key) < 0){
                higher = tmp;
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        if (higher != null)
            splay(higher);
        return  higher.key;
    }

    @Override
    public Entry<Integer, String> firstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> lastEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollFirstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<Integer> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<Integer> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> subMap(Integer fromKey, boolean fromInclusive, Integer toKey, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> headMap(Integer toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    private void headMap(Node<Integer, String> node, SortedMap<Integer, String> headMap, Integer toKey) {
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

    private void tailMap(Node<Integer, String> node, SortedMap<Integer, String> tailMap, Integer fromKey) {
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
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public Integer firstKey() {
        Node<Integer, String> first = findMinimum(root);
        return first.key;
    }

    private Node<Integer, String> findMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        splay(node);
        return node;
    }


    @Override
    public Integer lastKey() {
        Node<Integer, String> last = root;
        while (last.right != null)
            last = last.right;
        splay(last);
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
        Node<Integer, String> result = find(root, (Integer)key);
        return (result != null)? true : false;
    }

    @Override
    public boolean containsValue(Object value) {
        return containsValue(root, value);
    }

    private boolean containsValue(Node node, Object value){
        if (node == null) return false;
        if (node.value.equals(value)) {
            splay(node);
            return true;
        }
        return (containsValue(node.left, value) || containsValue(node.right, value));
    }
    @Override
    public String get(Object key) {
        Node<Integer, String> result = find(root, (Integer)key);
        return (result == null)? null : result.value;
    }

    @Override
    public String put(Integer key, String value) {
        return insert(root, key, value);
    }


    Node find(Node<Integer, String> node, Integer key) {
        if (node == null) return null;
        if (key.equals(node.key)) {
            splay(node);
            return node;
        }
        if (key > node.key) return find(node.right, key);
        return find(node.left, key);
    }


    private void delete(Node node) {
        if (node.left == null) {
            root = node.right;
            if (root != null)
                root.parent = null;
        } else {
            Node rightSubtree = node.right;
            root = node.left;
            root.parent = null;
            Node maxLeft = findMax(root);
            splay(maxLeft);
            root.right = rightSubtree;
            if (rightSubtree != null)
                rightSubtree.parent = root;
        }
    }

    @Override
    public String remove(Object key) {
        Node toRemove = find(root, (Integer) key);
        if (toRemove != null) {
            size--;
            String save = (String) toRemove.value;
            delete(toRemove);
            return save;
        }
        return null;
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
