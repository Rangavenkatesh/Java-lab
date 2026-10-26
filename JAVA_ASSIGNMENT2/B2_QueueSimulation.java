import java.util.*;

class Patient {
    String name;
    int emergencyLevel;

    Patient(String name, int emergencyLevel) {
        this.name = name;
        this.emergencyLevel = emergencyLevel;
    }

    public String toString() {
        return name + "(level " + emergencyLevel + ")";
    }
}

public class B2_QueueSimulation {
    public static void main(String[] args) {
        try {
            Queue<String> customers = new ArrayDeque<String>();
            customers.offer("Ravi");
            customers.offer("Sita");
            customers.offer("Anil");

            System.out.println("Ticket counter queue: " + customers);
            System.out.println("Next customer: " + customers.peek());

            while (!customers.isEmpty()) {
                System.out.println("Serving: " + customers.poll());
                System.out.println("Queue now: " + customers);
            }

            String emptyCustomer = customers.poll();
            if (emptyCustomer == null) {
                System.out.println("Queue is empty; nobody to serve.");
            }

            Queue<Patient> patients = new PriorityQueue<Patient>(new Comparator<Patient>() {
                public int compare(Patient a, Patient b) {
                    return Integer.compare(b.emergencyLevel, a.emergencyLevel);
                }
            });
            patients.offer(new Patient("Charan", 1));
            patients.offer(new Patient("Bala", 2));
            patients.offer(new Patient("Amit", 3));

            System.out.println("\nPatients served by emergency level:");
            while (!patients.isEmpty()) {
                System.out.println(patients.poll());
            }

            Queue<String> linkedQueue = new LinkedList<String>();
            linkedQueue.offer("Ravi");
            linkedQueue.offer("Sita");
            linkedQueue.offer("Anil");
            System.out.println("\nLinkedList FIFO queue: " + linkedQueue);
            while (!linkedQueue.isEmpty()) {
                System.out.println("Serving: " + linkedQueue.poll());
            }

            System.out.println("Final ArrayDeque queue size: " + customers.size());
            System.out.println("Final PriorityQueue size: " + patients.size());
            System.out.println("LinkedList implements Queue and also supports deque operations.");
            System.out.println("ArrayDeque also supports insertion and removal at both ends.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}