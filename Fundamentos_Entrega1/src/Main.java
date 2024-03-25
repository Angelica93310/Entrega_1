public class Main {

    public static void main(String[] args) {
        GenerateInfoFiles generateInfoFiles = new GenerateInfoFiles();
        generateInfoFiles.read("Names.csv");
        generateInfoFiles.read("Products.csv");
        generateInfoFiles.read("Sales.csv");
  
    }
}