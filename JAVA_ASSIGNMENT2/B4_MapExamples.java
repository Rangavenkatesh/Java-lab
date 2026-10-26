import java.util.*;
import java.util.stream.Collectors;

class MapStudent {
    String rollNo;
    String name;

    MapStudent(String rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MapStudent)) {
            return false;
        }
        MapStudent other = (MapStudent) obj;
        return rollNo.equals(other.rollNo);
    }

    public int hashCode() {
        return rollNo.hashCode();
    }

    public String toString() {
        return rollNo + " - " + name;
    }
}

public class B4_MapExamples {
    public static void main(String[] args) {
        try {
            HashMap<String, Integer> marks = new HashMap<String, Integer>();
            marks.put("S102", 92);
            marks.put("S104", 88);
            marks.put("S101", 76);
            marks.put("S103", 81);
            marks.put("S105", 67);

            System.out.println("Marks of S102: " + marks.get("S102"));
            System.out.println("Contains S104: " + marks.containsKey("S104"));
            marks.remove("S105");
            System.out.println("Map size after remove: " + marks.size());
            System.out.println("Keys: " + marks.keySet());
            System.out.println("Values: " + marks.values());

            System.out.println("Entries using Iterator:");
            Iterator<Map.Entry<String, Integer>> iterator = marks.entrySet().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

            String sentence = "Java is easy and Java is useful";
            HashMap<String, Integer> frequency = new HashMap<String, Integer>();
            String[] words = sentence.toLowerCase().split("\\s+");
            for (String word : words) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
            System.out.println("Word frequency greater than one:");
            for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
                if (entry.getValue() > 1) {
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                }
            }

            List<Map.Entry<String, Integer>> meritList = marks.entrySet().stream()
                    .sorted((x, y) -> Integer.compare(y.getValue(), x.getValue()))
                    .collect(Collectors.toList());
            System.out.println("Merit list (descending):");
            for (Map.Entry<String, Integer> entry : meritList) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            LinkedHashMap<String, Integer> insertionOrder = new LinkedHashMap<String, Integer>();
            insertionOrder.put("S102", 92);
            insertionOrder.put("S104", 88);
            insertionOrder.put("S101", 76);
            insertionOrder.put("S103", 81);
            System.out.println("LinkedHashMap insertion order: " + insertionOrder);

            TreeMap<String, Integer> rollOrder = new TreeMap<String, Integer>(insertionOrder);
            System.out.println("TreeMap roll-number order: " + rollOrder);
            System.out.println("TreeMap is suitable for roll-number-wise reports because its keys are sorted.");

            Map<MapStudent, Integer> studentMap = new HashMap<MapStudent, Integer>();
            MapStudent first = new MapStudent("S101", "Anil");
            studentMap.put(first, 76);
            studentMap.put(new MapStudent("S102", "Ravi"), 92);
            System.out.println("Student key lookup: " + studentMap.get(new MapStudent("S101", "Anil")));
            // equals() and hashCode() are both overridden so equal keys are treated consistently by HashMap.
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}