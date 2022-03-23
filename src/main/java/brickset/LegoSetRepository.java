package brickset;
import repository.Repository;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * 1.Prints names of first 3 LEGO sets where number of pieces is 0
     */
    public void printNamesWith0pieces() {
        getAll().stream().
                filter(legoset -> legoset.getPieces() == 0)
                .map(LegoSet::getName)
                .limit(3)
                .forEach(System.out::println);
    }
    /**
     * 2.Prints all themes that start with A in ascending order
     */
    public void printThemesStartWithA() {
        getAll().stream()
                .map(LegoSet::getTheme)
                .filter(legoset -> legoset.startsWith("A"))
                .distinct()
                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }
    /**
     * 3.Returns boolean if there is at least one LEGO set within the specified theme
     * @param theme
     * @return boolean if there is at least one LEGO set within the specified theme
     */
    public boolean boolLegoSetsWithinTheme(String theme) {
        return getAll().stream()
                .anyMatch(legoSet -> legoSet.getTheme().contains(theme));

    }
    /**
     * 4.Returns average length (as double) of names of LEGO sets within the specified theme
     * @param theme
     * @return average length (as double) of names of LEGO sets within the specified theme
     */
    public double avgLenOfNamesInTheme(String theme) {
       return getAll().stream()
                .filter(legoSet -> legoSet.getTheme().contains(theme))
                .map(LegoSet::getName)
                .mapToInt(String::length)
                .average()
                .getAsDouble();
    }
    /**
     * 5.Returns a single string containing 5 names with length > 15 (in ascending order)
     * @return a single string containing 5 names with length > 15
     */
    public String namesWithLengthOver15() {
            return getAll().stream()
                .map(LegoSet::getName)
                    .filter(name -> name.length() > 15)
                    .sorted()
                    .limit(5)
                    .collect(Collectors.joining(", "));

    }

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println("1.Prints names of first 3 LEGO sets where number of pieces is 0:");
        repository.printNamesWith0pieces();

        System.out.println("2.Prints all themes that start with A in ascending order:");
        repository.printThemesStartWithA();

        System.out.println("3.Returns boolean if there is at least one LEGO set within the specified theme:");
        System.out.println(repository.boolLegoSetsWithinTheme("Miscellaneous"));

        System.out.println("4.Returns average length (as double) of names of LEGO sets within the specified theme:");
        System.out.println(repository.avgLenOfNamesInTheme("Books"));

        System.out.println("5.Returns a single string containing 5 names with length > 15 (in ascending order):");
        System.out.println(repository.namesWithLengthOver15());
    }

}
