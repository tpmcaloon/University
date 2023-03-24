class Node{

    constructor(data,next = null){
        this.data = data
        this.next = next
    }
}

class LinkedList
{   
    constructor()
    {   
        this.head = null
    }
    AlternatingSplit(a, b){
        let first = this.head
        let second = first.next

        while (first != null &&
            second != null &&
            first.next != null){
                // Move a node to list 'a'
                this.MoveNode(a, first)

                // Move a node to list 'b'
                this.MoveNode(b, second)
                
                first = first.next.next
                if(first == null)
                break
                second = first.next
            }
        }

    // Pull off the front node of the
    // source and put it in dest
    MoveNode(dest, node){
        // Make the new node
        let new_node = new Node(node.data)
        
        if(dest.head == null)
        dest.head = new_node
        else{

            // Link the old dest off the new node
            new_node.next = dest.head
            
                // Move dest to point to the new node
                dest.head = new_node
            }
        }

    // Function to insert a node at the beginning of the linked list
    push(data){

        // 1 & 2 allocate the Node & put the data
        let new_node = new Node(data)
        // Make the next of new Node as head
        new_node.next = this.head
        // Move the head to point to new Node
        this.head = new_node
    }
    // Function to print nodes in a given linked list
    printList(){
        let temp = this.head
        while(temp){
            document.write(temp.data," ");
            temp = temp.next
        }
        document.write("</br>")
    }
}
// Driver Code

// Start with empty list
let llist = new LinkedList()
let a = new LinkedList()
let b = new LinkedList()

// Created linked list will be
// 0->1->2->3->4->5
llist.push(5)
llist.push(4)
llist.push(3)
llist.push(2)
llist.push(1)
llist.push(0)

llist.AlternatingSplit(a, b)

document.write("Original Linked List: ");
llist.printList()

document.write("Resultant Linked List 'a' : ");
a.printList()

document.write("Resultant Linked List 'b' : ");
b.printList()