package brickset;
import repository.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    // Working with Streams (2)

    /**
     * 1.Returns boolean if there are sets that have more than given value of pieces (1000)
     * @param num of pieces
     * @return boolean if there are sets that contain more than given number
     */
    public boolean boolSetsWithPiecesOver(int num) {
        return getAll().stream()
                .anyMatch(legoSet -> legoSet.getPieces() >= num);
    }

    /**
     * 2.Prints the distinct tags of the set with the given name
     * @param name of the set
     */
    public void printTagsOfTheSetWithName(String name) {
        getAll().stream()
                .filter(legoset -> legoset.getName().equals(name))
                .flatMap(legoset -> legoset.getTags().stream())
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 3.Returns string of subthemes in a given theme
     * @param theme
     * @return string of subthemes
     */
    public String returnStringOfSubthemesInsideTheme(String theme) {
        return getAll().stream()
                .filter(legoset -> legoset.getTheme().equals(theme))
                .map(legoSet -> legoSet.getSubtheme())
                .distinct()
                .reduce("Subthemes in the theme:", (x,y) -> x + " | " + y);

    }

    /**
     * 4.Returns a number of a legoset and its name that contains given number of pieces
     * @param num of pieces
     * @return Map (Number=Name pair)
     */
    public Map<String, String> returnMapNumberAndNameContainNumOfPieces(int num) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getPieces() == num)
                .collect(Collectors.toMap(LegoSet::getNumber, LegoSet::getName));
    }

    /**
     * 5.Returns all themes and list of names of legosets within them
     * @return Map (Theme=List of names of legosets pair)
     */
    public Map<String, List<String>> returnMapThemeWithListOfNames() {
        return getAll().stream()
                .collect(Collectors.groupingBy(LegoSet::getTheme,
                        Collectors.mapping(LegoSet::getName, Collectors.toList())));
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

        System.out.println("1.Returns boolean if there are sets that have more than given value of pieces:");
        System.out.println(repository.boolSetsWithPiecesOver(1000));

        System.out.println("2.Prints the distinct tags of the set with the given name:");
        repository.printTagsOfTheSetWithName("Star Wars Magnet Set");

        System.out.println("3.Returns string of subthemes in a given theme:");
        System.out.println(repository.returnStringOfSubthemesInsideTheme("Bionicle"));

        System.out.println("4.Returns a number of a legoset and its name that contains given number of pieces:");
        System.out.println(repository.returnMapNumberAndNameContainNumOfPieces(49));

        System.out.println("5.Returns all themes and list of names of legosets within them:");
        System.out.println(repository.returnMapThemeWithListOfNames());

        /*
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
        */
    }

}
