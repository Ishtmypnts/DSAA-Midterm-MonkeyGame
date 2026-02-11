import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Set;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Unggoy {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    static List<String> deck = new ArrayList<>();
    static List<String> playerDeck = new ArrayList<>();
    static List<String> kennDeck = new ArrayList<>();
    static List<String> totoyDeck = new ArrayList<>();
    static List<String> jiroDeck = new ArrayList<>();

    static String excluded;

    static int first;

    static final int consoleWidth = 160;
    static final int consoleWidthB = 165;
    static final int consoleWidthC = 170;

    public static String monkey;

    private static final String RED = "\u001B[31m";
    private static final String ORANGE = "\u001B[38;5;214m";
    private static final String GREEN = "\u001B[32m";
    private static final String YO = "\u001B[38;5;178m";
    private static final String WHITE = "\u001B[37m";
    private static final String YELLOW = "\u001B[33m";
    private static final String DG = "\u001B[90m";
    private static final String BY = "\u001B[38;5;94m";
    private static final String BLUE = "\u001B[38;5;24m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        String titleArt = """
                                    
        
                        ███╗░░░███╗░█████╗░███╗░░██╗██╗░░██╗███████╗██╗░░░██╗   ███╗░░░███╗░█████╗░███╗░░██╗██╗░░██╗███████╗██╗░░░██╗    
                        ████╗░████║██╔══██╗████╗░██║██║░██╔╝██╔════╝╚██╗░██╔╝   ████╗░████║██╔══██╗████╗░██║██║░██╔╝██╔════╝╚██╗░██╔╝    
                        ██╔████╔██║██║░░██║██╔██╗██║█████═╝░█████╗░░░╚████╔╝░   ██╔████╔██║██║░░██║██╔██╗██║█████═╝░█████╗░░░╚████╔╝░    
                        ██║╚██╔╝██║██║░░██║██║╚████║██╔═██╗░██╔══╝░░░░╚██╔╝░░   ██║╚██╔╝██║██║░░██║██║╚████║██╔═██╗░██╔══╝░░░░╚██╔╝░░    
                        ██║░╚═╝░██║╚█████╔╝██║░╚███║██║░╚██╗███████╗░░░██║░░░   ██║░╚═╝░██║╚█████╔╝██║░╚███║██║░╚██╗███████╗░░░██║░░░    
                        ╚═╝░░░░░╚═╝░╚════╝░╚═╝░░╚══╝╚═╝░░╚═╝╚══════╝░░░╚═╝░░░   ╚═╝░░░░░╚═╝░╚════╝░╚═╝░░╚══╝╚═╝░░╚═╝╚══════╝░░░╚═╝░░░   
                                                                                                                                        
                                                    \u001B[37m┌─█████████    ┌─████████     ┌─██    ┌─██     ┌██████████                           
                                                    │██─────┐██    │██────┐ ██    │████  ┌┘████    │██─────┐██                             
                                                    │██     └──    │██    │ ██    │██│██ │██ ██    │██     └──                           
                                                    │██            │██████████    │██└┐████ │██    │██                                   
                                                    │██   █████    │██────┐ ██    │██ └┐██  │██    │████████                             
                                                    │██   ──┐██    │██    │ ██    │██  └──  │██    │██──────                             
                                                    │██     │██    │██    │ ██    │██       │██    │██                                   
                                                    │██     │██    │██    │ ██    │██       │██    │██      ██                           
                                                    └┐████████     │██    │ ██    │██       │██    │██████████                           
                                                     └────────     └──    └───    └──       └─     └──────────\u001B[0m                           

""";

        System.out.println(RED + titleArt + RESET);
        System.out.println(centerText("Type 'enter' to start", consoleWidth));
        
        while (true) {
            System.out.print(" ".repeat(consoleWidthB / 2 - 5));  
            String enter = sc.nextLine().trim().toLowerCase();

            if (enter.equals("enter")) {
                System.out.println(" ");
                loadingScreen("Game starting");
                showMenu(sc);
                break; 
            } else {
                System.out.println(" ");
                System.out.println(centerText(RED + "Invalid input. Please type 'enter'." + RESET, consoleWidthC));
                
            }
        }

    }

    public static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    public static void showMenu(Scanner sc) {
        String menuArt = """


                                                                                   \u001B[38;5;178m██████████████          
                                                                                 ██▓▓▓▓▓▓▓▓▓▓▓▓▓▓████      
                                                                                ████▓▓▓▓▓▓░░░░▓▓▓▓░░██      
                                                                            ██▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░██    
                                                                            ██▓▓▓▓▓▓░░░░░░██░░░░██░░██    
                                                                            ██▓▓██▓▓░░░░░░██░░░░██░░██    
                                                                            ██▓▓██▓▓░░██░░░░░░░░░░░░░░██  
                                                                                ██▓▓▓▓██░░░░░░░  ░  ░░░░░░██
                                                                    ██████       ██▓▓██░░░░░░░░████░░░░░░██
                                                                ██▓▓▓▓▓▓██        ██░░████████░░░░██████  
                                                              ██▓▓▓▓▓▓▓▓▓▓██      ████░░░░░░░░░░░░░░██    
                                                              ██▓▓▓▓██▓▓▓▓██    ██▓▓▓▓██████████████      
                                                              ██▓▓▓▓▓▓██████████▓▓▓▓▓▓▓▓▓▓██▓▓▓▓██\u001B[0m        
                                                            \u001B[33m████████████████████████████████████████
                                                            █▄─▀█▀─▄█─▄▄─█▄─▀█▄─▄█▄─█─▄█▄─▄▄─█▄─█─▄█
                                                            ██─█▄█─██─██─██─█▄▀─███─▄▀███─▄█▀██▄─▄██
                                                            ▀▄▄▄▀▄▄▄▀▄▄▄▄▀▄▄▄▀▀▄▄▀▄▄▀▄▄▀▄▄▄▄▄▀▀▄▄▄▀▀\u001B[0m   

                                                            ┌───────────────────────────────────────┐ 
                                                            │                                       │ 
                                                            │             [1] PLAY                  │ 
                                                            │             [2] ABOUT THE GAME        │ 
                                                            │             [3] QUIT                  │ 
                                                            │_______________________________________│ 
                                                                        

""";
        System.out.println(ORANGE + menuArt + RESET);

        int choice = 0;
    while (true) {
        try {
            System.out.print(centerText("Choose an option: ", consoleWidth));
            choice = sc.nextInt();
            if (choice == 1) {
                System.out.println(" ");
                loadingScreen("Loading");
                chooseBot();
                break; 
            }else if(choice == 2) {
                System.out.println(" ");
                loadingScreen("Opening Settings");
                aboutGame();
            }
             else if (choice == 3) {
                System.out.println(" ");
                if (confirmExit()) {
                    System.out.println(" ");
                    loadingScreen("Exiting the game");
                    Exit();
                    System.exit(0);
                } else {
                    System.out.println(" ");
                    loadingScreen("Returning to the menu");
                    showMenu(sc); 
                    return;
                }
            } else {
                System.out.println(centerText(RED + "Invalid choice. Please try again." + RESET, consoleWidthC));
            }
        } catch (InputMismatchException e) {
            System.out.println(centerText(RED + "Please enter a valid number." + RESET, consoleWidthC));
            sc.next(); 
        }
    }
    }
    public static void loadingScreen() {
        loadingScreen("Loading");
    }

    public static void loadingScreen(String message) {
        String[] loadingMessages = {".", "..", "..."};

        for (int i = 0; i < 10; i++) {
            String loadingText = message + loadingMessages[i % loadingMessages.length];
            System.out.print("\r" + centerText(loadingText, consoleWidth));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted.");
                break;
            }
        }
        System.out.println();
    }
    public static boolean confirmExit() {
        while (true) {
            System.out.println("\n" + centerText("Are you sure you want to exit?", consoleWidth));
            System.out.println(centerText("[1] Confirm", consoleWidth));
            System.out.println(centerText("[2] Cancel", consoleWidth));
            System.out.print(centerText("> ", consoleWidth));  
            System.out.flush();  

            if (!sc.hasNextInt()) {
                System.out.println(centerText(RED+"Invalid input. Please enter [1] or [2]."+RESET, consoleWidth));
                sc.next(); 
                continue;
            }

            int input = sc.nextInt();
            sc.nextLine(); 

            if (input == 1) {
                return true;
            } else if (input == 2) {
                return false;
            } else {
                System.out.println(centerText(RED+"Invalid input. Please enter [1] or [2]."+RESET, consoleWidth));
            }
        }
    }
    public static void Exit() {
        String exitArt = """


                                         ████████│   ████████└┐  ████████└┐ ████████└┐ ████████└┐ ██│     ██│ ████████│ ██│      
                                        ██┌──────┘  ██│     ██│ ██│     ██│ ██│    ██│ ██│    ██│ ██│     ██│ ██├─────┘ ██│      
                                        ██│         ██│     ██│ ██│     ██│ ██│    ██│ ██│    ██│ ██│     ██│ ██│       ██│      
                                        ██│  █████│ ██│     ██│ ██│     ██│ ██│    ██│ ████████─┤  ████████┌┘ ██████│   ██│      
                                        ██│     ██│ ██│     ██│ ██│     ██│ ██│    ██│ ██│    ██│     ██┌──┘  ██├───┘   ██│      
                                        ██│     ██│ ██│     ██│ ██│     ██│ ██│    ██│ ██│    ██│     ██│     ██│       ──┤      
                                         ████████┌┘  ████████┌┘  ████████┌┘ ████████┌┘ ████████┌┘     ██│     ████████│ ██│      
                                         ────────┘   ────────┘   ────────┘  ────────┘  ────────┘      ──┘     ────────┘ ──┘    

""";
        System.out.println(BLUE + exitArt + RESET);
    }
    public static void aboutGame(){
        String ATGTArt = """

                                         ██████   ██████    █████   ██   ██  ██████                      █████    ██████   ███    ███  ██████
                                        ██    ██  ██   ██  ██   ██  ██   ██    ██     ▄▄▄▄▄ ▄  ▄ ▄▄▄▄   ██       ██    ██  ████  ████  ██    
                                        ██    ██  ███████  ██   ██  ██   ██    ██       █   █  █ █      ██  ███  ██    ██  ██ ████ ██  ████  
                                        ████████  ██   ██  ██   ██  ██   ██    ██       █   ████ ███    ██   ██  ████████  ██  ██  ██  ██    
                                        ██    ██  ██████    █████    █████     ██       █   █  █ █▄▄▄    █████   ██    ██  ██      ██  ██████

        """;

        System.out.println(RED + ATGTArt + RESET);
        String lineArt = """
                ████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████
""";
    System.out.println(lineArt);

        String ATGDArt = """

                                            Monkey Monkey Game is a classic card game typically played with a standard deck of playing cards. 
                                            The objective is to form pairs and discard them until no cards are left. One card is discarded 
                                            and the goal is to avoid being left with the card with no pair at the end of the game. Players
                                            take turns drawing cards from each other's hands, attempting to make pairs and discard them. 
                                            The game continues until all pairs are formed, and the player with the card with no pair loses.

            """;
    System.out.println(ATGDArt);
        
        String PTGArt = """

                                                                         _______________________________________
                                                                        |                                       |
                                                                        |            \u001B[37mPlay the game?\u001B[0m             \u001B[38;5;178m|
                                                                        |\u001B[0m           \u001B[37m[1] YES OR [2] NO\u001B[0m           \u001B[38;5;178m|
                                                                        |_______________________________________|
    
            """;
        System.out.println(YO + PTGArt + RESET);
        while(true){
            System.out.print(centerText("Enter here: ", consoleWidth));

            if (!sc.hasNextInt()) {
                System.out.println(centerText(RED+"Invalid input. Please enter a number."+RESET, consoleWidthC));
                sc.next(); 
                continue;
            }

            int yesno = sc.nextInt();
            sc.nextLine();

            if (yesno == 1){
                System.out.println(" ");
                loadingScreen("Loading");
                chooseBot();
                break;
            }else if (yesno == 2){
                System.out.println(" ");
                loadingScreen("Exiting the game");
                Exit();
                System.exit(0);
            }else {
                System.out.println(centerText(RED+"Invalid input. Please enter the given options."+RESET, consoleWidthC));
            }  
    }
    }
    public static void chooseBot(){
      String optionBotArt = """
                                                    
                                                                                   ▐█▌               
                                                                                   ▐░▌               
                                                                                   ▐░▌               
                                                                                ▄▄▀░░░▀▄▄            
                                                                              ▄▀░░░░░░░░░▀▄          
                                                                            ▄▀░░░░░░░░░░░░░▀▄        
                                                                            ░░░░░░░░░░░░░░░░░▌       
                                                                          ▐░░░░░░░░░░░░░░░░░░░▌      
                                                                         ▄███████████████████████▄    
                                                                        ███▀───────█████────────███   
                                                                        ███───███───███───███───███   
                                                                        ███───▀▀▀───███───▀▀▀───███   
                                                                        ▀███▄─────▄█████▄─────▄███▀   
                                                                         ████████████████████████▀    
                                                                          ▐░░░░░░░░░░░░░░░░░░░░░▌     
                                                                          ▐░▄▀▀█▀▀█▀▀█▀▀█▀▀█▀▀▄░▌     
                                                                          ▐░█▄▄█▄▄█▄▄█▄▄█▄▄█▄▄█░▌     
                                                                          ▐░░░░░░░░░░░░░░░░░░░░░▌     
                                                                      ┌────▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀────┐ 
                                                                      │░░░░░░░░░░░░░░░░░░░░░░░░░░░░░│ 
                                                                      │░░░░░░░░░░░░░░░░░░░░░░░░░░░░░│ 
                                                                       ───────────────────────────── 
                                                                                                    
                                                                               \u001B[37mChoose mode:\u001B[0m         
                                                                                                   
                                                                           \u001B[37m[1] Player vs A.I.\u001B[0m      
                                                                           \u001B[37m[2] Player vs 3  A.I.\u001B[0m   


    """;
          System.out.println(DG + optionBotArt + RESET);

          int botchoice = 0;
    while (true) {
            System.out.print(centerText("Choose an option: ", consoleWidth));
            botchoice = sc.nextInt();
            if (botchoice == 1) {
                System.out.println("");
                loadingScreen("Starting Game");
                PlayGame();
                break; 
            }else if(botchoice == 2) {
                System.out.println(" ");
                loadingScreen("Starting Game");
                PlayGame2();
            }
        }
    }
    public static void PlayGame(){
        generateDeck(); 

        // display deck
        System.out.println(RED + 
        """

          █ █▀▀▄ █ ▀█▀ █ █▀▀█ █     █▀▀▄ █▀▀ █▀▀ █ █
          █ █  █ █  █  █ █▄▄█ █     █  █ █▀▀ █   █▀▄
          ▀ ▀  ▀ ▀  ▀  ▀ ▀  ▀ ▀▀▀   ▀▀▀  ▀▀▀ ▀▀▀ ▀ ▀ 
""" + RESET);
        display(deck);

        // removes a random card from the deck
        chosenCard();

        // shuffle
        shuffleDeck(deck);

        // display shuffle
        System.out.println(BLUE + """
            

            █▀▀ █  █ █  █ █▀▀ █▀▀ █   █▀▀ █▀▀▄  █▀▀▄ █▀▀ █▀▀ █ █
            ▀▀█ █▀▀█ █  █ █▀  █▀  █   █▀  █  █  █  █ █▀  █   █▀▄
            ▀▀▀ ▀  ▀  ▀▀  ▀   ▀   ▀▀▀ ▀▀▀ ▀▀▀   ▀▀▀  ▀▀▀ ▀▀▀ ▀ ▀
""" + RESET);
        display(deck);

        //allocation of cards to the players
        allocateDeck();

        // display the players decks
        displayPlayer();
        removePairs(playerDeck);

        // display ai
        displayKenn();
        removePairs(kennDeck);

        // display updated deck
        displayPlayer();
        displayKenn();
        
        //flip coin
        coinFlip();

        game();
    }

    private static void generateDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
    }

    private static void shuffleDeck(List<String> deck) {
        Collections.shuffle(deck);

    }

    public static void chosenCard(){

      int chosen = rand.nextInt(52)+1;

      System.out.println("===============================================");
      System.out.println("\tThe removed card is "+ deck.get(chosen-1));
      System.out.println("===============================================");

      excluded = deck.get(chosen-1);
      deck.remove(chosen-1);
    }

    public static void display(List<String> hand){
        int count = 0;
        for (String card : hand) {
          
            System.out.print(card+" | ");
            count++;
            if (count==13) {
                System.out.println("\n");
                count=0;
            }
        }

        System.out.println("\nHERE IS THE TOTAL CARDS: "+hand.size()+"\n");
    }

    public static void displayPlayer(){
      String playerCArt = """

███████  ██      ██████  ██   ██ ██████ ███████  ██ ███████   ██████  ██████  █████ ██   ██ 
██    ██ ██     ██    ██ ██   ██ ██     ██    ██  █ ██        ██   ██ ██     ██     ██  ██  
██    ██ ██     ██    ██ ███████ ████   ██    ██    ███████   ██   ██ ████   ██     █████   
███████  ██     ████████   ███   ██     ███████          ██   ██   ██ ██     ██     ██  ██  
██       ██████ ██    ██   ███   ██████ ██    ██    ███████   ██████  ██████  █████ ██   ██ 
              
""";
              System.out.println(ORANGE + playerCArt + RESET);
      display(playerDeck);
    }

    public static void displayKenn(){
      String kennCArt = """

██   ██ ██████ ███  ██ ███  ██ ██ ███████   ██████  ██████  █████ ██   ██ 
██  ██  ██     ████ ██ ████ ██  █ ██        ██   ██ ██     ██     ██  ██  
█████   ████   ██ ████ ██ ████    ███████   ██   ██ ████   ██     █████   
██  ██  ██     ██  ███ ██  ███         ██   ██   ██ ██     ██     ██  ██  
██   ██ ██████ ██   ██ ██   ██    ███████   ██████  ██████  █████ ██   ██ 
              
""";
              System.out.println(RED + kennCArt + RESET);
      display(kennDeck);
    }

    public static void allocateDeck(){
      System.out.println("Do you want to allocate? \n     [1] Yes [2] No");
      System.out.print("Enter here: ");
      int decision = sc.nextInt();
      int same = rand.nextInt(2)+1;
      int count = 0;
      while(decision<1||decision>2) {
        System.out.println(RED + "Invalid input. Please choose from the options." + RESET);
        decision = sc.nextInt();
      }
      switch (decision) {
        case 1:
          while (count < 51) {
            if(same == 1){
              playerDeck.add(deck.get(count));
              count++;
              same = 2;
            }
            else if(same == 2){
              kennDeck.add(deck.get(count));
              count++;
              same = 1;
            }
          }
          break;
      }
    }

    public static void removePairs(List<String> deck) {
      List<String> pairsToRemove = new ArrayList<>();
      List<String> pairsFound = new ArrayList<>(); // To store the pairs found
      Set<String> alreadyPaired = new HashSet<>(); // To track already paired cards

      // Iterate through the deck to find pairs
      for (int i = 0; i < deck.size(); i++) {
          String currentCard = deck.get(i);
          String[] currentCardDetails = currentCard.split(" of ");
          String currentRank = currentCardDetails[0];

          // Check if the current card is already paired
          if (alreadyPaired.contains(currentCard)) {
              continue; // Skip this card if it's already paired
          }

          for (int j = i + 1; j < deck.size(); j++) {
              String nextCard = deck.get(j);
              String[] nextCardDetails = nextCard.split(" of ");
              String nextRank = nextCardDetails[0];

              // Check if the ranks of the two cards match (pair found)
              if (currentRank.equals(nextRank)) {
                  // Add the pair to the list of cards to remove
                  pairsToRemove.add(currentCard);
                  pairsToRemove.add(nextCard);

                  // Add the pair to the list of pairs found
                  pairsFound.add(currentCard);
                  pairsFound.add(nextCard);

                  // Add both cards to the set of already paired cards
                  alreadyPaired.add(currentCard);
                  alreadyPaired.add(nextCard);

                  break; // Exit the inner loop since we found a pair
              }
          }
      }

      // Display the pairs found
      if (!pairsFound.isEmpty()) {
          System.out.println("\nPairs Found: ");
          for (int k = 0; k < pairsFound.size(); k += 2) {
              System.out.println(pairsFound.get(k) + " and " + pairsFound.get(k + 1));
              System.out.println(" ");
          }
      } else {
          System.out.println("\nNo pairs found.\n");
      }

      // Remove the pairs from the deck
      deck.removeAll(pairsToRemove);
  }
    public static void coinFlip(){
      System.out.println("Flip a coin. To determine who goes first.\n");
    System.out.println("""
              [1]HEADS
                 OR
              [2]TAILS
""");
    System.out.print("Enter here: ");

      int chosen = sc.nextInt(2);
        while(chosen <1||chosen>2){
          System.out.println(RED + "Invalid input. Please choose from the options." + RESET);
        chosen = sc.nextInt();
       }
       int coin = rand.nextInt();

       if(chosen == coin){
        System.out.println(" ");
        System.out.println(BLUE + "========================================================\r\n" + //
                    "========== \u001B[37mYou go first, you luckky bastard\u001B[0m   \u001B[38;5;24m==========\r\n" + //
                    "========================================================\u001B[0m" + RESET);
        first = 1;
       }
      else{
        System.out.println(" ");
        System.out.println(BLUE + "========================================================\r\n" + //
                    "========== \u001B[37mKenn go first, you unlucky bastard\u001B[0m \u001B[38;5;24m==========\r\n" + //
                    "========================================================\u001B[0m" + RESET);
        first = 2;
    }
  }

  public static void game(){
    boolean result = true;
    if (playerDeck.size() == 0|| kennDeck.size()==0) {
      if (playerDeck.size()==0) {
        String playerWinArt = """
                                                  
              ██████████████████                  
            ███▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█████              
          ██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██            
        ██▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒██            
        ██▒▒▒▒████▒▒▒▒████████▒▒▒▒▒▒▒▒██          
      ██▒▒▒▒██░░░░██████░░░░░░██▒▒▒▒▒▒██          
      ██▒▒██░░░░░░░░██░░░░░░░░░░██▒▒▒▒▒▒██        
      ██▒▒██░░░░░░░░░░░░░░░░░░░░░░██▒▒▒▒██        
    ████▒▒██░░░░░░░░░░░░░░░░░░░░░░██▒▒██████      
  ██░░██▒▒██░░░░██░░░░░░██░░░░░░░░██▒▒██░░░░██    
  ██░░██▒▒██░░░░██░░░░░░██░░░░░░██▒▒▒▒██░░░░██    
  ██░░██▒▒▒▒██    ░░░░░░    ░░██▒▒▒▒▒▒██░░░░██    
    ████▒▒██░░░░░░▒▒▒▒▒▒░░░░░░░░██▒▒▒▒██████      
      ██████░░░░░░░░██░░░░░░░░░░░░██▒▒████        
        ████░░░░██░░██░░░░██░░░░░░██████          
          ██░░░░░░████████░░░░░░████              
            ██░░░░░░░░░░░░░░░░████                
              ██████████████████                  
                                                  
          \u001B[37m█ █ █▀▀█ █  █   █   █ █ █▀▀▄            
           █  █  █ █  █   █ █ █ █ █  █            
           ▀  ▀▀▀▀  ▀▀     ▀ ▀  ▀ ▀  ▀            
       ██████████████████████████████████\u001B[0m          
""";
          System.out.println(YO + playerWinArt + RESET);
        printLastTwoCards(kennDeck.get(0));
        result = false;
      }else if (kennDeck.size() == 0) {
        String botWinArt = """

                                   ███████                      
                                  █████████                     
                                 ███████████                    
                                ███░░███░░███                   
                               ███░░░░█░░░░░██                  
                           █████░░░███████░░░████               
                          █░░░████░█░█░█░█░███░░░█              
                          █░░░███░░███░███░░██░░░█              
                        ███░░░███░░░░░░░░░░░██░░░███            
                        █████░░░░░░░░███░░░░░░░█████            
                      ██████░░░░░░░░░░░░░░░░░░░░██████          
                     ███████░░░░█████████████░░░███████         
                  ██████████░░░█░░░░░░░░░░░░░█░░████████        
                 ██████████████░░░░░░░░░░░░░░░██████████        
                ███████████████░░░░░░░░░░░░░░░███████████       
                █████████████████░░░░░░░░░░░░█████████████      
                ██████████████████░░░░░░░░░░██████████████      
                ██████████████████████████████████████████      
                ██████████████░░░██████████░░░████████████      
                 █████████████░░░░░░████░░░░░░████████████      
                  █████████████░█░░░░██░░░░█░████████████       
                   █████████████░░░░░██░░░░░████████████        
                     ██████████░█░░░░██░░░░█░██████████         
                       ████████░█░░░░░░░░░░█░████████           
                      ████████░░░░░░█░░█░░░░░░████████          
                      ██████████████░░░░██████████████          
                     ███████████░░░░░░░░░░░░███████████         
                   █████████████░░░░█░█░░░░░████████████        
                  ███████████████░░█░█░█░░░██████████████       
                  ███████████████████████████████████████       
                  ███████████████████████████████████████       
                  ████████████████        ███████████████       
                   █████████████            ████████████        
                   ████████████              ███████████        
                ████░░███░░░██               ██░░░███░░████     
               █░░░░░█░░░░██░█               █░██░░░░█░░░░░█    
               ██████████████                 ██████████████    
                                                                
                    \u001B[37m█ █ █▀▀ █▀▀▄ █▀▀▄  █   █ █ █▀▀▄ █▀▀         
                    █▀▄ █▀▀ █  █ █  █  █ █ █ █ █  █ ▀▀█         
                    ▀ ▀ ▀▀▀ ▀  ▀ ▀  ▀   ▀ ▀  ▀ ▀  ▀ ▀▀▀         
                   █████████████████████████████████████\u001B[0m        
                                                                
""";
          System.out.println(RED + botWinArt + RESET);
        printLastTwoCards(playerDeck.get(0));
        result = false;
      }
    }
    if (result != false) {
      displayPlayer();
      removePairs(playerDeck);
      System.out.println(playerDeck.toString());
  
      displayKenn();
      removePairs(kennDeck);
      System.out.println(kennDeck.toString());
  
      if (first == 1) {
          playerPick();
      } else if (first == 2) {
          kennPick();
      }
  }
  }
  public static void kennPick() {
    int pick = rand.nextInt(playerDeck.size())+1;

    System.out.println("The bot chose the " + playerDeck.get(pick-1));
    kennDeck.add(playerDeck.get(pick-1));
    playerDeck.remove(pick-1);

    System.out.print("\nType '1' to continue: ");
    int proceed = sc.nextInt();

    while (proceed != 1) {
        System.out.println(RED + "Invalid input. Please try again. " + RESET);
        proceed = sc.nextInt();
    }
    if (proceed == 1) {
        first = 1;
        game();
    }
}
  public static void playerPick(){
    System.out.println("\nChoose a card to take 1 - " + kennDeck.size());
    System.out.print("Enter here: ");
    int pick =  sc.nextInt();

    while (pick < 1||pick > kennDeck.size()) {
      System.out.print("Invalid input. Please choose from the given choices: ");
      pick =  sc.nextInt();
    }

    System.out.println("You choose the "+ kennDeck.get(pick-1));
    playerDeck.add(kennDeck.get(pick-1));
    kennDeck.remove(pick-1);

    System.out.print("\nType '1' to continue: ");
    int proceed = sc.nextInt();

    while (proceed!=1) {
      System.out.println(RED + "Invalid input. Please try again. " + RESET);
      proceed = sc.nextInt();
    }
    if (proceed == 1) {
      first = 2;
      game();
    }
  }
  public static void printLastTwoCards(String card){
    System.out.println(YELLOW + "==========================================" + RESET);
    System.out.println(" The remaining card was the "+card);
    System.out.println(" The removed card was the "+excluded);
    System.out.println(YELLOW + "==========================================" + RESET);
  }
  public static void PlayGame2(){
    generateDeck2(); 

    // display deck
    System.out.println(RED + 
    """

      █ █▀▀▄ █ ▀█▀ █ █▀▀█ █     █▀▀▄ █▀▀ █▀▀ █ █
      █ █  █ █  █  █ █▄▄█ █     █  █ █▀▀ █   █▀▄
      ▀ ▀  ▀ ▀  ▀  ▀ ▀  ▀ ▀▀▀   ▀▀▀  ▀▀▀ ▀▀▀ ▀ ▀ 
""" + RESET);
    display2(deck);

    // removes a random card from the deck
    chosenCard2();

    // shuffle
    shuffleDeck2(deck);

    // display shuffle
    System.out.println(BLUE + """
        

        █▀▀ █  █ █  █ █▀▀ █▀▀ █   █▀▀ █▀▀▄  █▀▀▄ █▀▀ █▀▀ █ █
        ▀▀█ █▀▀█ █  █ █▀  █▀  █   █▀  █  █  █  █ █▀  █   █▀▄
        ▀▀▀ ▀  ▀  ▀▀  ▀   ▀   ▀▀▀ ▀▀▀ ▀▀▀   ▀▀▀  ▀▀▀ ▀▀▀ ▀ ▀
""" + RESET);
    display2(deck);

    //allocation of cards to the players
    allocateDeck2();

    // display the players decks
    displayPlayer2();
    removePairs2(playerDeck);

    // display ai
    displayKenn2();
    removePairs2(kennDeck);

    displayTotoy();
    removePairs2(totoyDeck);

    displayJiro();
    removePairs2(jiroDeck);

    // display updated deck
    displayPlayer2();
    displayKenn2();
    displayTotoy();
    displayJiro();
    
    //flip coin
    coinFlip2();

    game2();
}

private static void generateDeck2() {
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    for (String suit : suits) {
        for (String rank : ranks) {
            deck.add(rank + " of " + suit);
        }
    }
}

private static void shuffleDeck2(List<String> deck) {
    Collections.shuffle(deck);

}

public static void chosenCard2(){

  int chosen = rand.nextInt(deck.size()); 

  System.out.println("===============================================");
  System.out.println("\tThe removed card is "+ deck.get(chosen-1));
  System.out.println("===============================================");

  excluded = deck.get(chosen-1);
  deck.remove(chosen-1);
}

public static void display2(List<String> hand){
    int count = 0;
    for (String card : hand) {
      
        System.out.print(card+" | ");
        count++;
        if (count==13) {
            System.out.println("\n");
            count=0;
        }
    }

    System.out.println("\nHERE IS THE TOTAL CARDS: "+hand.size()+"\n");
}

public static void displayPlayer2(){
  String playerCArt = """

███████  ██      ██████  ██   ██ ██████ ███████  ██ ███████   ██████  ██████  █████ ██   ██ 
██    ██ ██     ██    ██ ██   ██ ██     ██    ██  █ ██        ██   ██ ██     ██     ██  ██  
██    ██ ██     ██    ██ ███████ ████   ██    ██    ███████   ██   ██ ████   ██     █████   
███████  ██     ████████   ███   ██     ███████          ██   ██   ██ ██     ██     ██  ██  
██       ██████ ██    ██   ███   ██████ ██    ██    ███████   ██████  ██████  █████ ██   ██ 
          
""";
          System.out.println(ORANGE + playerCArt + RESET);
  display2(playerDeck);
}

public static void displayKenn2(){
  String kennCArt = """

██   ██ ██████ ███  ██ ███  ██ ██ ███████   ██████  ██████  █████ ██   ██ 
██  ██  ██     ████ ██ ████ ██  █ ██        ██   ██ ██     ██     ██  ██  
█████   ████   ██ ████ ██ ████    ███████   ██   ██ ████   ██     █████   
██  ██  ██     ██  ███ ██  ███         ██   ██   ██ ██     ██     ██  ██  
██   ██ ██████ ██   ██ ██   ██    ███████   ██████  ██████  █████ ██   ██ 
          
""";
          System.out.println(RED + kennCArt + RESET);
  display2(kennDeck);
}
public static void displayTotoy(){
  String kennCArt = """

██████ ███████ ██████ ███████ ██   ██ ██ ███████  ██████  ██████  █████ ██   ██  
  ██   ██   ██   ██   ██   ██ ██   ██  █ ██       ██   ██ ██     ██     ██  ██   
  ██   ██   ██   ██   ██   ██ ███████    ███████  ██   ██ ████   ██     █████    
  ██   ██   ██   ██   ██   ██   ███           ██  ██   ██ ██     ██     ██  ██   
  ██   ███████   ██   ███████   ███      ███████  ██████  ██████  █████ ██   ██  
""";
          System.out.println(BLUE + kennCArt + RESET);
  display2(totoyDeck);
}
public static void displayJiro(){
  String kennCArt = """

                                                                      
     ██ ██ ███████  ███████ ██ ███████  ██████  ██████  █████ ██   ██   
     ██ ██ ██    ██ ██   ██  █ ██       ██   ██ ██     ██     ██  ██    
     ██ ██ ██    ██ ██   ██    ███████  ██   ██ ████   ██     █████     
██   ██ ██ ███████  ██   ██         ██  ██   ██ ██     ██     ██  ██    
███████ ██ ██    ██ ███████    ███████  ██████  ██████  █████ ██   ██   
          
""";
          System.out.println(GREEN + kennCArt + RESET);
  display2(jiroDeck);
}

public static void allocateDeck2() {
  System.out.println("Do you want to allocate cards? \n     [1] Yes [2] No");
  System.out.print("Enter here: ");
  int decision = sc.nextInt();
  int count = 0;

  while (decision < 1 || decision > 2) {
      System.out.println(RED + "Invalid input. Please choose from the options." + RESET);
      decision = sc.nextInt();
  }

  if (decision == 1) {
      // Randomly allocate cards to player, Kenn, and Totoy
      while (count < deck.size()) {
          int playerOrBot = rand.nextInt(4); // Randomly choose between player (0), Kenn (1), or Totoy (2)

          if (playerOrBot == 0) {
              playerDeck.add(deck.get(count)); // Allocate to player
          } else if (playerOrBot == 1) {
              kennDeck.add(deck.get(count)); // Allocate to Kenn
          } else if (playerOrBot == 2){
              totoyDeck.add(deck.get(count)); // Allocate to Totoy
          }else { 
              jiroDeck.add(deck.get(count));
          }
          count++;
      }
  }
}

public static List<String> removePairs2(List<String> deck) {
List<String> pairsToRemove = new ArrayList<>();
Map<String, Integer> rankCount = new HashMap<>();


for (String card : deck) {
    String[] cardDetails = card.split(" of ");
    String rank = cardDetails[0];
    rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
}


for (Map.Entry<String, Integer> entry : rankCount.entrySet()) {
    String rank = entry.getKey();
    int count = entry.getValue();

  
    while (count >= 2) {
        int removed = 0;
        Iterator<String> iterator = deck.iterator();
        while (iterator.hasNext()) {
            String card = iterator.next();
            if (card.startsWith(rank)) {
                pairsToRemove.add(card);
                iterator.remove();
                removed++;
                if (removed == 2) break;
            }
        }
        count -= 2; 
    }
}


if (!pairsToRemove.isEmpty()) {
    System.out.println("\nPairs Found: ");
    for (int i = 0; i < pairsToRemove.size(); i += 2) {
        System.out.println(pairsToRemove.get(i) + " and " + pairsToRemove.get(i + 1));
    }
} else {
    System.out.println("\nNo pairs found.");
}

return pairsToRemove; 
}

public static void removedPairs(List<String> deck) {
if (deck.isEmpty()) {
    System.out.println("The deck is empty. No pairs to remove.");
    return; 
}

List<String> pairsToRemove = new ArrayList<>();
Map<String, Integer> rankCount = new HashMap<>(); 

for (String card : deck) {
    String[] cardDetails = card.split(" of ");
    String rank = cardDetails[0];
    rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
}


for (Map.Entry<String, Integer> entry : rankCount.entrySet()) {
    String rank = entry.getKey();
    int count = entry.getValue();

   
    while (count >= 2) {
        
        for (int i = 0; i < deck.size(); i++) {
            String card = deck.get(i);
            if (card.startsWith(rank)) {
                pairsToRemove.add(card);
                deck.remove(i);
                count--;
                i--; 
                if (count < 2) break;
            }
        }
    }
}


deck.removeAll(pairsToRemove);


if (!pairsToRemove.isEmpty()) {
    System.out.println("\nPairs Found: ");
    for (int k = 0; k < pairsToRemove.size(); k += 2) {
        System.out.println(pairsToRemove.get(k) + " and " + pairsToRemove.get(k + 1));
    }
} else {
    System.out.println("\nNo pairs found.");
}
}
public static void coinFlip2(){
  System.out.println("Flip a coin. To determine who goes first. You or one of the bots.\n");
System.out.println("""
          [1]HEADS
             OR
          [2]TAILS
""");
System.out.print("Enter here: ");

  int chosen = sc.nextInt();
    while(chosen <1||chosen>2){
      System.out.println(RED + "Invalid input. Please choose from the options." + RESET);
    chosen = sc.nextInt();
   }
   int coin = rand.nextInt(2) + 1; 

   if(chosen == coin){
    System.out.println(" ");
    System.out.println(BLUE + "========================================================\r\n" + //
                "========== \u001B[37mYou go first, you luckky bastard\u001B[0m   \u001B[38;5;24m==========\r\n" + //
                "========================================================\u001B[0m" + RESET);
    first = 1;
   }
  else{
    System.out.println(" ");
    System.out.println(BLUE + "========================================================\r\n" + //
                "========== \u001B[37mKenn go first, you unlucky bastard\u001B[0m \u001B[38;5;24m==========\r\n" + //
                "========================================================\u001B[0m" + RESET);
    first = 2;
}
}

public static void game2() {
while (true) {
    // Player's turn
    if (first == 1) {
        displayPlayer2();
        playerPickFromTotoy();
        System.out.println("\nPlayer's deck after picking from Totoy: " + playerDeck);
        removePairs2(playerDeck);
        System.out.println("Player's deck after removal: " + playerDeck);

        
        if (playerDeck.isEmpty()) {
            System.out.println(YELLOW + "\nPlayer has paired all their cards! " + RESET);
            playerWin();
            break;
        }

        // Kenn's turn
        displayKenn2();
        kennPickFromJiro();
        System.out.println("\nKenn's deck after picking from Jiro: " + kennDeck);
        removePairs2(kennDeck);
        System.out.println("Kenn's deck after removal: " + kennDeck);

        
        if (kennDeck.isEmpty()) {
            System.out.println(YELLOW + "\nKenn has paired all their cards!" + RESET);
            botWin();
            break;
        }

        // Totoy's turn
        displayTotoy();
        if (!kennDeck.isEmpty()) {
            totoyPickFromKenn();
            System.out.println("\nTotoy's deck after picking from Kenn: " + totoyDeck);
            removePairs2(totoyDeck);
        }

        System.out.println("Totoy's deck after removal: " + totoyDeck);
        if (totoyDeck.isEmpty()) {
            System.out.println(YELLOW + "\nTotoy has paired all their cards!" + RESET);
            botWin();
            break;
        }

        // Jiro's turn
        displayJiro();
        if (!playerDeck.isEmpty()) {
            jiroPickFromPlayer();
            System.out.println("\nJiro's deck after picking from Player: " + jiroDeck);
            removePairs2(jiroDeck);
        }

        System.out.println("Jiro's deck after removal: " + jiroDeck);
        if (jiroDeck.isEmpty()) {
            System.out.println(YELLOW + "\nJiro has paired all their cards!" + RESET);
            botWin();
            break;
        }
    } else {
        // Kenn's turn first
        displayKenn2();
        kennPickFromJiro();
        System.out.println("\nKenn's deck after picking from Jiro: " + kennDeck);
        removePairs2(kennDeck);
        System.out.println("Kenn's deck after removal: " + kennDeck);

        
        if (kennDeck.isEmpty()) {
            System.out.println(YELLOW + "\nKenn has paired all their cards!" + RESET);
            botWin();
            break;
        }

        // Player's turn
        displayPlayer2();
        playerPickFromTotoy();
        System.out.println("\nPlayer's deck after picking from Totoy: " + playerDeck);
        removePairs2(playerDeck);
        System.out.println("Player's deck after removal: " + playerDeck);

        
        if (playerDeck.isEmpty()) {
            System.out.println(YELLOW + "\nThe player has paired all their cards!" + RESET);
            playerWin();
            break;
        }

        // Totoy's turn
        displayTotoy();
        if (!kennDeck.isEmpty()) {
            totoyPickFromKenn();
            System.out.println("\nTotoy's deck after picking from Kenn: " + totoyDeck);
            removePairs2(totoyDeck);
        }

        System.out.println("Totoy's deck after removal: " + totoyDeck);
        if (totoyDeck.isEmpty()) {
            System.out.println(YELLOW + "\nTotoy has paired all their cards!" + RESET);
            botWin();
            break;
        }

        // Jiro's turn
        displayJiro();
        if (!playerDeck.isEmpty()) {
            jiroPickFromPlayer();
            System.out.println("\nJiro's deck after picking from Player: " + jiroDeck);
            removePairs2(jiroDeck);
        }

        System.out.println("Jiro's deck after removal: " + jiroDeck);
        if (jiroDeck.isEmpty()) {
            System.out.println(YELLOW + "\nJiro has paired all their cards!" + RESET);
            botWin();
            break;
        }
    }
}
// Show the removed/excluded card after the game ends
String lastCard = playerDeck.isEmpty() ? "None" : playerDeck.get(playerDeck.size() - 1); 
printLastTwoCards(lastCard, excluded);
}


public static void kennPickFromJiro() {
if (jiroDeck.isEmpty()) { 
  System.out.println("Jiro has no cards left for Kenn to pick from.");
  return; 
}
int pick = rand.nextInt(jiroDeck.size()); 

System.out.println("Kenn chooses the " + jiroDeck.get(pick));
kennDeck.add(jiroDeck.get(pick));
jiroDeck.remove(pick);
}

public static void jiroPickFromPlayer() {
if (playerDeck.isEmpty()) {
    System.out.println("Player has no cards left for Jiro to pick from.");
    return; 
}

int pick = rand.nextInt(playerDeck.size()); 

System.out.println("Jiro chooses the " + playerDeck.get(pick));
jiroDeck.add(playerDeck.get(pick));
playerDeck.remove(pick);
}
public static void playerPickFromTotoy() {
System.out.println("\nChoose a card to take from Totoy (1 - " + totoyDeck.size() + "): ");
System.out.print("Enter here: ");
int pick = sc.nextInt();

while (pick < 1 || pick > totoyDeck.size()) {
    System.out.print("Invalid input. Please choose from the given choices: ");
    pick = sc.nextInt();
}

System.out.println("You chose the " + totoyDeck.get(pick - 1));
playerDeck.add(totoyDeck.get(pick - 1));
totoyDeck.remove(pick - 1);
}

public static void totoyPickFromKenn() {
int pick = rand.nextInt(kennDeck.size()); 

System.out.println("Totoy chooses the " + kennDeck.get(pick));
totoyDeck.add(kennDeck.get(pick));
kennDeck.remove(pick);
}
public static void printLastTwoCards(String card, String excluded){
System.out.println(YELLOW + "==========================================" + RESET);
System.out.println(" The remaining card was the "+card);
System.out.println(" The removed card was the "+excluded);
System.out.println(YELLOW + "==========================================" + RESET);
}
public static void playerWin(){
String playerWinArt = """
                                              
          ██████████████████                  
        ███▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█████              
      ██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██            
    ██▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒██            
    ██▒▒▒▒████▒▒▒▒████████▒▒▒▒▒▒▒▒██          
  ██▒▒▒▒██░░░░██████░░░░░░██▒▒▒▒▒▒██          
  ██▒▒██░░░░░░░░██░░░░░░░░░░██▒▒▒▒▒▒██        
  ██▒▒██░░░░░░░░░░░░░░░░░░░░░░██▒▒▒▒██        
████▒▒██░░░░░░░░░░░░░░░░░░░░░░██▒▒██████      
██░░██▒▒██░░░░██░░░░░░██░░░░░░░░██▒▒██░░░░██    
██░░██▒▒██░░░░██░░░░░░██░░░░░░██▒▒▒▒██░░░░██    
██░░██▒▒▒▒██    ░░░░░░    ░░██▒▒▒▒▒▒██░░░░██    
████▒▒██░░░░░░▒▒▒▒▒▒░░░░░░░░██▒▒▒▒██████      
  ██████░░░░░░░░██░░░░░░░░░░░░██▒▒████        
    ████░░░░██░░██░░░░██░░░░░░██████          
      ██░░░░░░████████░░░░░░████              
        ██░░░░░░░░░░░░░░░░████                
          ██████████████████                  
                                              
      \u001B[37m█ █ █▀▀█ █  █   █   █ █ █▀▀▄            
       █  █  █ █  █   █ █ █ █ █  █            
       ▀  ▀▀▀▀  ▀▀     ▀ ▀  ▀ ▀  ▀            
   ██████████████████████████████████\u001B[0m          
""";
      System.out.println(YO + playerWinArt + RESET);
}
public static void botWin(){
 String botWinArt = """

                               ███████                      
                              █████████                     
                             ███████████                    
                            ███░░███░░███                   
                           ███░░░░█░░░░░██                  
                       █████░░░███████░░░████               
                      █░░░████░█░█░█░█░███░░░█              
                      █░░░███░░███░███░░██░░░█              
                    ███░░░███░░░░░░░░░░░██░░░███            
                    █████░░░░░░░░███░░░░░░░█████            
                  ██████░░░░░░░░░░░░░░░░░░░░██████          
                 ███████░░░░█████████████░░░███████         
              ██████████░░░█░░░░░░░░░░░░░█░░████████        
             ██████████████░░░░░░░░░░░░░░░██████████        
            ███████████████░░░░░░░░░░░░░░░███████████       
            █████████████████░░░░░░░░░░░░█████████████      
            ██████████████████░░░░░░░░░░██████████████      
            ██████████████████████████████████████████      
            ██████████████░░░██████████░░░████████████      
             █████████████░░░░░░████░░░░░░████████████      
              █████████████░█░░░░██░░░░█░████████████       
               █████████████░░░░░██░░░░░████████████        
                 ██████████░█░░░░██░░░░█░██████████         
                   ████████░█░░░░░░░░░░█░████████           
                  ████████░░░░░░█░░█░░░░░░████████          
                  ██████████████░░░░██████████████          
                 ███████████░░░░░░░░░░░░███████████         
               █████████████░░░░█░█░░░░░████████████        
              ███████████████░░█░█░█░░░██████████████       
              ███████████████████████████████████████       
              ███████████████████████████████████████       
              ████████████████        ███████████████       
               █████████████            ████████████        
               ████████████              ███████████        
            ████░░███░░░██               ██░░░███░░████     
           █░░░░░█░░░░██░█               █░██░░░░█░░░░░█    
           ██████████████                 ██████████████    
                                                            
                                                  
                \u001B[37m█▄▄▄ █▀▀█ ▀█▀   █   █ █ █▀▀▄ █▀▀               
                █  █ █  █  █    █ █ █ █ █  █ ▀▀█               
                ▀▀▀▀ ▀▀▀▀  ▀     ▀ ▀  ▀ ▀  ▀ ▀▀▀               
              ████████████████████████████████████\u001B[0m    
                                                            
""";
      System.out.println(RED + botWinArt + RESET);
}//
}
