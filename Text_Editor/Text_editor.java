package project1;

import java.util.*;

class Nodee {
    String text;

    Nodee(String text) {
        this.text = text;
    }
}

public class Text_editor {

    static Nodee current;
    static Stack<Nodee> undoStack = new Stack<>();
    static Stack<Nodee> redoStack = new Stack<>();

    static void addText(String newText) {
        String updatedText = current.text + newText;
        Nodee newNode = new Nodee(updatedText);

        undoStack.push(current);
        current = newNode;

        redoStack.clear();
        System.out.println("Text added!");
    }

    static void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(current);
            current = undoStack.pop();
            System.out.println("Undo done!");
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    static void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(current);
            current = redoStack.pop();
            System.out.println("Redo done!");
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    static void showHistory() {
        System.out.println("\nUndo Stack:");
        for (Nodee n : undoStack) {
            System.out.println(n.text);
        }

        System.out.println("\nCurrent Text:");
        System.out.println(current.text);

        System.out.println("\nRedo Stack:");
        for (Nodee n : redoStack) {
            System.out.println(n.text);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        current = new Nodee("");

        while (true) {
            System.out.println("\n===== TEXT EDITOR =====");
            System.out.println("Current Text: " + current.text);
            System.out.println("1. Add Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Show History");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice;

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input!");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter new text: ");
                    String text = sc.nextLine();
                    addText(text);
                    break;

                case 2:
                    undo();
                    break;

                case 3:
                    redo();
                    break;

                case 4:
                    showHistory();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}