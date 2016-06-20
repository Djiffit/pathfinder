package com.kerberuskaahaaja.pathfinder.datastructures;

import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
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
        nodes = new Node[3000000];
    }

    /**
     * Poistaa jonon kärjessä olevan ruudun
     * @return
     */
    public Tile poll() {
        Tile tile = nodes[1].getTile();
        Node reserve = nodes[1];
        nodes[1] = nodes[tail];
        if (size() > 1) {
            if (tail % 2 == 0) {
                nodes[tail / 2].setLeft(new Node(new NormalTile(1, 2), -900001));
            } else {
                nodes[tail / 2].setRight(new Node(new NormalTile(1, 2), -900001));
            }
        }
        nodes[1].setRight(reserve.getRight());
        nodes[1].setLeft(reserve.getLeft());
        nodes[tail] = null;
        tail -= 1;
        if (size() > 1) {
            treeCheck(nodes[1], 1, true);
        }
        return tile;
    }

    /**
     * Lisää alkion jonoon ja varmistaa sen oikean paikan 
     * @param tile
     * @param priority
     */
    public void enqueue(Tile tile, int priority) {
        tail += 1;
        nodes[tail] = new Node(tile, priority);

        if (tail > 1) {
            makeNewNodeAchild(nodes[tail]);
            treeCheck(nodes[tail / 2], tail / 2, false);
            treeCheck(nodes[1], 1, false);
        }

    }

    /**
     * Palauttaa jonon alkioiden lukumäärän
     * @return
     */
    public int size() {
        return tail;
    }

    /**
     * Tekee uudesta alkiosta toisen lapsen
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
     * Varmistaa että puun säännöt ovat vielä voimassa
     * @param node
     * @param index
     * @param down
     */
    private void treeCheck(Node node, int index, boolean down) {
        if (node.getLeft().priority() > node.priority() && node.getRight().priority() <= node.getLeft().priority()) {
            swap(node, node.getLeft(), index*2, false, down);
        } else if (node.getRight().priority() > node.priority() && node.getRight().priority() >= node.getLeft().priority()) {
            swap(node, node.getRight(), index*2+1, true, down);
        } else {
            if (index > 1 && !down) {
                treeCheck(nodes[index/2], index/2, down);
            }
        }
    }

    /**
     * Vaihtaa kahden alkion lapset
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
        recursiveTreeCheckCall(index, down);
    }

    /**
     * Päivittää vaihdetun alkion vanhemman lapseksi oikean alkion
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
     * Kutsuu TreeCheckiä, kun sille on tarvetta ja varmistaa että kutsut ovat oikeaan suuntaan
     * @param index
     * @param down
     */
    private void recursiveTreeCheckCall(int index, boolean down) {
        if (down) {
            if (index < tail) {
                treeCheck(nodes[index], index, true);
            }
        } else {
            if (index > 1) {
                treeCheck(nodes[index / 2], index / 2, false);
            } else {
                treeCheck(nodes[1], 1, false);
            }
        }
    }

    /**
     * Varmistaa että vaihdossa vaihdetaan oikeanpuoleiseen solmuun
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
        for (int i = 1; i <= tail; i++) {
            System.out.print(nodes[i].priority()+" ");
        }
        System.out.println("");
        return ("");
    }
}
