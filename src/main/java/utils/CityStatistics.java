package utils;

import entityes.Address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CityStatistics {

    private final FileParser fileParser;

    public CityStatistics(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    private Map<String, Integer> countingNumberBuildings() {
        Map<String, Integer> map = new HashMap<>();
        List<Address> addressList = fileParser.getAddressList();

        for (Address address : addressList) {
            map.merge(address.getCity() + " " + address.getFloor(), 1, Integer::sum);
        }
        return map;
    }

    public void printCountingBuildingsCity() {
        Map<String, Integer> buildingCounts = countingNumberBuildings();
        buildingCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + "-этажное: " + entry.getValue() + " зданий"));
    }

    public void printAndCountDuplicates() {
        Map<Address, Integer> occurrences = fileParser.getAddressList().stream()
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));

        occurrences.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println("Дубликат - " + entry.getKey()
                        + " количество повторений - " + entry.getValue()));
    }
}
