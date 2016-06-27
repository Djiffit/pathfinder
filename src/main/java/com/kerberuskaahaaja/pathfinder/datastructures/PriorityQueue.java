package com.kerberuskaahaaja.pathfinder.datastructures;

import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

/**
 * Priorisoiva jono
 */
public class PriorityQueue {
    private Node[] nodes;
    private int tail;
    private int size;

    /**
     * Konstruktori alustaa 10 kokoisen jonon
     */
    public PriorityQueue() {
        this.tail = 0;
        this.size = 10;
        nodes = new Node[size];
    }

    /**
     * Poistaa jonon kärjessä olevan ruudun ja asettaa viimeisen solmun kärkeen, jonka jälkeen alkaa treecheck kunnes puu on jälleen järjestyksessä
     * @return
     */
    public Tile poll() {
        Tile tile = nodes[1].getTile();
        Node reserve = nodes[1];
        nodes[1] = nodes[tail];
        if (size() > 1) { //Aseta siirretyn solmun vanhemman lapsi paikanpitäjiksi
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
     * Varmistaa myös, että jonossa on tilaa, ja tarpeen vaatiessa luo uuden jonon kokonaan, jonka koko on kaksinkertainen
     * @param tile
     * @param priority
     */
    public void enqueue(Tile tile, int priority) {
        tail += 1;
        if (tail+2 == size) {
            this.size = size*2;
            Node[] newnodes = new Node[size*2];
            copyArray(newnodes);
        }
        nodes[tail] = new Node(tile, priority);

        if (tail > 1) {
            makeNewNodeAchild(nodes[tail]);
            treeCheck(nodes[tail / 2], tail / 2, false);
            treeCheck(nodes[1], 1, false);
        }

    }

    /**
     * Kopioi alkiot uuteen tualukkoon
     * @param newnodes uusi taulukko
     */
    private void copyArray(Node[] newnodes) {
        for (int i = 1; i <= tail ; i++) {
            newnodes[i] = nodes[i];
        }
        this.nodes = newnodes;
    }

    /**
     * Palauttaa jonon alkioiden lukumäärän
     * @return määrä
     */
    public int size() {
        return tail;
    }

    /**
     * Tekee uudesta alkiosta toisen lapsen
     * @param node lapsi
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
     * @param node haluttu solmu
     * @param index mikä sen indeksi
     * @param down mennäänkö alaspäin
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
     * @param parent vanhempi
     * @param child lapsi
     * @param index indeksi
     * @param right oikealapsi vai vasen
     * @param down mennäänkö alaspäin
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
     * @param index mikä solmu
     * @param child lapsi
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
     * @param index mille indeksille kutsutaan
     * @param down mennäänkö alaspäin
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
     * @param parent vanhempi
     * @param leftnode vasenlapsi
     * @param rightnode oikealapsi
     * @param child lapsi
     * @param right oikea vai vasen
     * @return solmu
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
