import java.util.*;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return name + " - " + marks;
    }
}

public class B3_SetOperations {
    public static void main(String[] args) {
        try {
            String[] values = {"Java", "C", "Python", "Java", "DBMS",
                               "C", "HTML", "Python", "SQL", "Java"};

            Set<String> hashSet = new HashSet<String>();
            Set<String> linkedSet = new LinkedHashSet<String>();
            Set<String> treeSet = new TreeSet<String>();

            for (String value : values) {
                hashSet.add(value);
                linkedSet.add(value);
                treeSet.add(value);
            }

            // HashSet has no guaranteed order; LinkedHashSet keeps insertion order; TreeSet sorts elements.
            System.out.println("HashSet: " + hashSet);
            System.out.println("LinkedHashSet: " + linkedSet);
            System.out.println("TreeSet: " + treeSet);

            List<String> names = Arrays.asList("C", "Java", "Python", "C", "Java", "C");
            Set<String> seen = new HashSet<String>();
            Set<String> duplicates = new LinkedHashSet<String>();
            for (String name : names) {
                if (!seen.add(name)) {
                    duplicates.add(name);
                }
            }
            System.out.println("Duplicates in list: " + duplicates);

            Set<Integer> a = new LinkedHashSet<Integer>(Arrays.asList(2, 4, 6, 8, 10));
            Set<Integer> b = new LinkedHashSet<Integer>(Arrays.asList(4, 8, 12));

            Set<Integer> union = new LinkedHashSet<Integer>(a);
            union.addAll(b);
            Set<Integer> intersection = new LinkedHashSet<Integer>(a);
            intersection.retainAll(b);
            Set<Integer> difference = new LinkedHashSet<Integer>(a);
            difference.removeAll(b);

            System.out.println("Union: " + union);
            System.out.println("Intersection: " + intersection);
            System.out.println("A - B: " + difference);

            TreeSet<String> studentNames = new TreeSet<String>(
                    Arrays.asList("Ravi", "Sita", "Anil", "Kiran"));
            System.out.println("Names ascending: " + studentNames);
            System.out.println("Names descending: " + studentNames.descendingSet());

            TreeSet<Student> students = new TreeSet<Student>(new Comparator<Student>() {
                public int compare(Student x, Student y) {
                    int result = Integer.compare(y.marks, x.marks);
                    if (result == 0) {
                        return x.name.compareTo(y.name);
                    }
                    return result;
                }
            });
            students.add(new Student("Ravi", 85));
            students.add(new Student("Sita", 92));
            students.add(new Student("Anil", 78));
            System.out.println("Students by marks: " + students);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}