package us.mattgreen;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private final static FileOutput outFile = new FileOutput("animals.txt");
    private final static FileInput inFile = new FileInput("animals.txt");

    public static void main(String[] args) {
        ArrayList<Talkable> zoo = new ArrayList<>();

        Scanner keyboard = new Scanner(System.in);

        var userInput = "";
        while (userInput.isEmpty() || (userInput.equals("1") && userInput.equals("2")))
        {
            System.out.println("What type of animal would you like to add? \n1) Dog \n2) Cat");
            userInput = keyboard.nextLine();
            System.out.println(userInput);
        }

        if(userInput.equals("1"))
        {
            System.out.println("Name of Dog: ");
            var dogName = keyboard.nextLine();

            var dogFriend = "";
            while (dogFriend == null || dogFriend.equals("") || (dogFriend.toLowerCase().equals("y") && dogFriend.toLowerCase().equals("n")))
            {
                System.out.println("Is the Dog Friendly (Y)/(N): ");
                dogFriend = keyboard.nextLine();
            }
            var isFriendly = dogFriend == "y" ? true : false;
            zoo.add(new Dog(isFriendly, dogName));
        }
        else
        {
            System.out.println("Name of Cat: ");
            var catName = keyboard.nextLine();

            var isNumeric = false;
            var mouseKills = "";
            var isInt = false;
            while (!(isInt))
            {
                System.out.println("Number of Mouse Kills: ");
                mouseKills = keyboard.nextLine();

                try {
                    Integer.parseInt(mouseKills);
                    isInt = true;
                } catch (NumberFormatException e) {
                    isInt = false;
                }
            }
            zoo.add(new Cat(Integer.parseInt(mouseKills), catName));
        }

        for (Talkable thing : zoo) {
            printOut(thing);
        }
        outFile.fileClose();
        inFile.fileRead();
        inFile.fileClose();

        FileInput indata = new FileInput("animals.txt");
        String line;
        while ((line = indata.fileReadLine()) != null) {
            System.out.println(line);
        }
    }

    public static void printOut(Talkable p) {
        System.out.println(p.getName() + " says=" + p.talk());
        outFile.fileWrite(p.getName() + " | " + p.talk());
    }
}
