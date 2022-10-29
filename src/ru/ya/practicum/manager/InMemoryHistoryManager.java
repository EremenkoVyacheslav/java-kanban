package ru.ya.practicum.manager;

import ru.ya.practicum.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final HashMap<Integer, Node<Task>> nodeMap = new HashMap<>();
    private Node<Task> head;
    private Node<Task> tail;


    @Override
    public void add(Task task) {
        if (task != null) {
            remove(task.getId());
            linkLast(task);
        }
    }

    @Override
    public void remove(int id) {
        removeNode(nodeMap.get(id));
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    private void linkLast(Task task) {
        if (task != null) {
            final Node<Task> oldTail = tail;
            final Node<Task> newNode = new Node<>(task, oldTail, null);
            tail = newNode;
            nodeMap.put(task.getId(), newNode);
            if (oldTail == null)
                head = newNode;
            else
                oldTail.setNext(newNode);
        }
    }


    private List<Task> getTasks() {
        List<Task> newNode = new ArrayList<>();
        Node<Task> oldNode = head;
        while (oldNode != null) {
            newNode.add(oldNode.getData());
            oldNode = oldNode.getNext();
        }
        return newNode;
    }

    private void removeNode(Node<Task> node) {
        if (node != null) {
            Node<Task> next = node.getNext();
            Node<Task> prev = node.getPrev();

            node.setData(null);

            if (head == node) {
                head = node.getNext();
            }
            if (tail == node) {
                tail = node.getPrev();
            }
            if (prev != null) {
                prev.setNext(next);
            }
            if (next != null) {
                next.setPrev(prev);
            }
        }
    }
}

