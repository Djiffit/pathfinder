package com.kerberuskaahaaja.pathfinder.datastructures;

import com.kerberuskaahaaja.pathfinder.tiles.Tile;

/**
 *
 */
public class PriorityQueue {
    private Node[] nodes;
    private int tail;

    /**
     *
     */
    public PriorityQueue() {
        this.tail = 0;
        nodes = new Node[10000];
    }

    /**
     *
     * @return
     */
    public Tile poll() {
        Tile tile = nodes[1].getTile();
        Node reserve = nodes[1];
        nodes[1] = nodes[tail];
        nodes[tail].setRight(reserve.getRight());
        nodes[tail].setLeft(reserve.getLeft());
        heapify(nodes[1], 1, true);
        tail -= 1;
        return tile;
    }

    /**
     *
     * @param tile
     * @param priority
     */
    public void enqueue(Tile tile, int priority) {
        tail += 1;
        nodes[tail] = new Node(tile, priority);

        if (tail > 1) {
            makeNewNodeAchild(nodes[tail]);
            heapify(nodes[tail / 2], tail / 2, false);
        }
    }

    /**
     *
     * @return
     */
    public int size() {
        return tail;
    }

    /**
     *
     * @param node
     */
    private void makeNewNodeAchild(Node node) {
        if (tail%2 == 0) {
            nodes[tail/2].setLeft(node);
        } else {
            nodes[tail/2].setRight(node);
        }
    }

    /**
     *
     * @param node
     * @param index
     * @param down
     */
    private void heapify(Node node, int index, boolean down) {
        if (node.getLeft().priority() > node.priority() && node.getRight().priority() <= node.getLeft().priority()) {
            swap(node, node.getLeft(), index*2, false, down);
        } else if (node.getRight().priority() > node.priority() && node.getRight().priority() >= node.getLeft().priority()) {
            swap(node, node.getRight(), index*2+1, true, down);
        }
    }

    /**
     *
     * @param parent
     * @param child
     * @param index
     * @param right
     * @param down
     */
    private void swap(Node parent, Node child, int index, boolean right, boolean down) {
        Node leftnode = parent.getLeft();
        Node rightnode = parent.getRight();
        parent.setLeft(child.getLeft());
        parent.setRight(child.getRight());
        child = determineChildren(parent, leftnode, rightnode, child, right);
        updateParentOfParent(index/2, child);
        nodes[index] = parent;
        nodes[index/2] = child;
        recursiveHeapifyCall(index/2, down);
    }

    /**
     *
     * @param index
     * @param child
     */
    private void updateParentOfParent(int index, Node child) {
        if (index > 1) {
            if (index % 2 == 0) {
                nodes[index/2].setLeft(child);
            } else {
                nodes[index/2].setRight(child);
            }
        }
    }

    /**
     *
     * @param index
     * @param down
     */
    private void recursiveHeapifyCall(int index, boolean down) {
        if (down) {
            if (index*2 < tail) {
                heapify(nodes[index*2], index*2, true);
            }
        } else {
            if (index > 1) {
                heapify(nodes[index / 2], index / 2, false);
            } else {
                heapify(nodes[1], 1, false);
            }
        }
    }

    /**
     *
     * @param parent
     * @param leftnode
     * @param rightnode
     * @param child
     * @param right
     * @return
     */
    private Node determineChildren(Node parent, Node leftnode, Node rightnode, Node child, boolean right) {
        if (right) {
            child.setLeft(leftnode);
            child.setRight(parent);
        } else {
            child.setLeft(parent);
            child.setRight(rightnode);
        }
        return child;
    }

    public String toString() {
        System.out.println(nodes[1].priority()+" ");
        System.out.print(nodes[2].priority()+" ");
        System.out.println(nodes[3].priority()+" ");
        System.out.print(nodes[4].priority()+" ");
        System.out.print(nodes[5].priority()+" ");
        System.out.print(nodes[6].priority()+" ");
        System.out.print(nodes[7].priority()+" ");
        return ("");
    }
}
