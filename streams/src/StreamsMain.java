import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsMain {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("lorem");
        items.add("ipsum");
        items.add("dolar");
        items.add("sit");
        items.add("ahmet");

//        for (ListIterator<String> it = items.listIterator(); it.hasNext()) {
//            String item = it.next();
//
//            if (item.startsWith("a")) {
//                it.remove();
//            }
//        }

        items.removeIf(item -> item.startsWith("a"));

        List<String> filteredList = items
            .stream()
            .filter(e -> !e.startsWith("a"))
            .collect(Collectors.toList());

        Optional<String> firstItem = items.stream()
            .filter(e -> e.startsWith("a"))
            .findFirst();
    }
}
