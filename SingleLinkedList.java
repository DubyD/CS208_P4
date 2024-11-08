/**
 * Author Will Duby
 */

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedList {


    private RoomNode head;
    private int size;

    public SingleLinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public RoomNode getHead() {
        return head;
    }

    public RoomNode getTail(){
        RoomNode temp = head;
        while(temp.hasNext() != null){
            temp = temp.getNextNode()
        }
        return temp;
    }

    public void insertAtTail(RoomNode newNode) {

        if (isEmpty()) {
            head = newNode;
            size++;
        }
        RoomNode temp = head;
        while(temp.hasNext()){
            temp = temp.getNextNode();
        }
        temp.setNext(newNode);
        size++;
    }

    public List<RoomNode> toList() {
        List<RoomNode> list = new ArrayList<RoomNode>();
        RoomNode temp = head;
        while(temp != null){
            list.add(temp);
            temp = temp.getNextNode();
        }
        return list;
    }

    public boolean inList(RoomNode node) {
        RoomNode temp = head;
        while(temp != null){
            if(temp.equals(node)) {
                return true;
            }
            temp = temp.getNextNode();
        }
        return false;
    }

    @Override
    public String toString(){
        String reply = "";
        RoomNode temp = head;
        while(temp != null){
            reply += temp.toString() + "\n";
            temp = temp.getNextNode();
        }

        return  "SingleLinkedList\n"+
                "Contents:\n"+ reply;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SingleLinkedList that = (SingleLinkedList) obj;

        if(this.getSize() != that.getSize()){
            return false;
        }

        for(int i = 0; i < toList().size(); i++){
            if(this.toList().get(i) != that.toList().get(i)){
                return false;
            }
        }

        return true;
    }

}
