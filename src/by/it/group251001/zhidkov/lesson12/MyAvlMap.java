package by.it.group251001.zhidkov.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {
    private Node root;
    private Node DeletedNode;
    private static class Node {
        int key;
        String value;
        Node left, right;
        int height;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }
    private Node recoverAvlMap(Node node){
        // Обновление высоты текущего узла
        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Левый случай
        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        // Правый случай
        if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("{");
        if (root != null) {
            toStringHelper(root, res);
            res.replace(res.length() - 2, res.length(), "}");
        }
        else
            res.append("}");
        return res.toString();
    }

    //Рекурсивно создаём строку по возрастанию элементов дерева при помощи симметричного обхода дерева
    private void toStringHelper(Node node, StringBuilder res) {
        if (node != null) {
            if (node.left != null) {
                toStringHelper(node.left, res);
            }
            res.append(node.key).append("=").append(node.value).append(", ");
            if (node.right != null) {
                toStringHelper(node.right, res);
            }
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        else
            return 1 + size(node.left) + size(node.right);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        int intKey = (Integer) key;
        return search(root, intKey) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        int intKey = (Integer) key;
        Node resultNode = search(root, intKey);
        return (resultNode != null) ? resultNode.value : null;
    }

    private Node search(Node node, int key) {
        if (node == null || key == node.key) {
            return node;
        }

        if (key < node.key) {
            return search(node.left, key);
        }

        return search(node.right, key);
    }

    @Override
    public String put(Integer key, String value) {
        StringBuilder oval = new StringBuilder();
        root = put(root, key, value, oval);
        if (oval.isEmpty())
            return null;
        else
            return oval.toString();
    }

    private Node put(Node node, int key, String value, StringBuilder oval) {
        if (node == null) {
            return new Node(key, value);
        }

        if (key < node.key) {
            node.left = put(node.left, key, value, oval);
        } else if (key > node.key) {
            node.right = put(node.right, key, value, oval);
        } else {
            // Если ключ существует, обновляем значение
            oval.append(node.value);
            node.value = value;
            return node;
        }

        return recoverAvlMap(node);
    }


    private Node remove(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = remove(node.left, key);
        } else if (key > node.key) {
            node.right = remove(node.right, key);
        } else {
            DeletedNode = node;
            // Узел с одним или без поддеревьев
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;

                // Узел без потомков
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                // Узел с одним потомком
                    node = temp;
                }
            } else {
                // Узел с двумя потомками, найдем наименьший узел в правом поддереве
                Node temp = findMin(node.right);

                // Скопируем данные найденного узла в текущий узел
                node.key = temp.key;
                node.value = temp.value;

                // Удалим найденный узел
                Node copy = node;
                node.right = remove(node.right, temp.key);
                DeletedNode = copy;
            }
        }

        // Если узла не существует после удаления, просто вернем null
        if (node == null) {
            return null;
        }

        return recoverAvlMap(node);
    }

    @Override
    public String remove(Object key) {
        int intKey = (Integer) key;
        Node removedNode = remove(root, intKey);
        if (DeletedNode != null) {
            Node res = DeletedNode;
            DeletedNode = null;
            return res.value;
        }
        else
            return null;
    }
    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        //За счёт "уборщика мусора" java могу не освобождать память от всех узлов дерева вручную
        root = null;
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
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node rotate(Node x, boolean isLeftRotation) {
        Node y;
        Node T;

        if (isLeftRotation) {
            y = x.right;
            T = y.left;
            y.left = x;
            x.right = T;
        } else {
            y = x.left;
            T = y.right;
            y.right = x;
            x.left = T;
        }

        // Обновление высот
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    private Node rightRotate(Node y) {
        return rotate(y, false);
    }

    private Node leftRotate(Node x) {
        return rotate(x, true);
    }


    private Node findMin(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
}