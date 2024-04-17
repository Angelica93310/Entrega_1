import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class GenerateInfoFiles {
    private Map<String, String> namesMap;
    private Map<String, Integer> salesMap;
    private Map<String, Integer> productsMap;

    public GenerateInfoFiles() {
        this.namesMap = new HashMap<>();
        this.salesMap = new HashMap<>();
        this.productsMap = new HashMap<>();
    }

    public void read(String fileName) {
        Path filePath = Paths.get(fileName);
        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (fileName.equals("Names.csv")) {
                    namesMap.put(parts[1], parts[3]);
                } else if (fileName.equals("Products.csv")) {
                    productsMap.put(parts[0], Integer.parseInt(parts[2]));
                } else if (fileName.equals("Sales.csv")) {
                    salesMap.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void analyzeSales() {
        for (Map.Entry<String, Integer> entry : salesMap.entrySet()) {
            String productCode = entry.getKey();
            int quantitySold = entry.getValue();
            if (productsMap.containsKey(productCode)) {
                int productPrice = productsMap.get(productCode);
                int totalCost = productPrice * quantitySold;
                System.out.println("Product: " + productCode + ", Quantity Sold: " + quantitySold + ", Total Cost: $" + totalCost);
            }
        }
    }

    public String getSalesPerson(String salesPersonID) {
        return namesMap.get(salesPersonID);
    }

    public static void main(String[] args) {
        GenerateInfoFiles analyzer = new GenerateInfoFiles();
        analyzer.read("Names.csv");
        analyzer.read("Products.csv");
        analyzer.read("Sales.csv");

        String salesPersonID = "2467456"; // Sales person ID to find
        String salesPerson = analyzer.getSalesPerson(salesPersonID);
        if (salesPerson != null) {
            System.out.println("Salesperson: " + salesPerson);
            System.out.println("Sales Details:");
            analyzer.analyzeSales();
        } else {
            System.out.println("Sales person with ID " + salesPersonID + " not found.");
        }
    }
}
