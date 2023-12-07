import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
     HashMap<String, ArrayList<Integer>> haveMap = new HashMap<>();
        HashMap<String, ArrayList<Integer>> needMap = new HashMap<>();

        try {
            
        String projektPfad = System.getProperty("user.dir");
        System.out.println("projektPfad: " + projektPfad);

        File projektDatei = new File(projektPfad);
        // File advent2Ordner = projektDatei.getParentFile();
        File file = new File(projektDatei.getAbsolutePath() + "\\input.txt");
            // File file = new File("input.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                ArrayList<Integer> haveList = new ArrayList<>();
                ArrayList<Integer> needList = new ArrayList<>();

                String[] haveStickers = scanner.nextLine().split(" ");
                for (String sticker : haveStickers) {
                    haveList.add(Integer.parseInt(sticker));
                }

                String[] needStickers = scanner.nextLine().split(" ");
                for (String sticker : needStickers) {
                    needList.add(Integer.parseInt(sticker));
                }

                haveMap.put(name, haveList);
                needMap.put(name, needList);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

// Determine who can receive needed stickers from whom
        for (String person : needMap.keySet()) {
            ArrayList<Integer> neededStickers = needMap.get(person);
            for (String otherPerson : haveMap.keySet()) {
                if (!person.equals(otherPerson)) {
                    ArrayList<Integer> availableStickers = haveMap.get(otherPerson);
                    if (availableStickers.containsAll(neededStickers)) {
                        System.out.println(person + " can receive needed stickers from " + otherPerson);
                    }
                }
            }
        }

        // Determine which stickers each person can receive from which other person
        for (String person : needMap.keySet()) {
            ArrayList<Integer> neededStickers = needMap.get(person);
            for (String otherPerson : haveMap.keySet()) {
                if (!person.equals(otherPerson)) {
                    ArrayList<Integer> availableStickers = haveMap.get(otherPerson);
                    ArrayList<Integer> canReceive = new ArrayList<>(neededStickers);
                    canReceive.retainAll(availableStickers);
                    if (!canReceive.isEmpty()) {
                        System.out.println(person + " can receive stickers " + canReceive + " from " + otherPerson);
                    }
                }
            }
        }
    }
}