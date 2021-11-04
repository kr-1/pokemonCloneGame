////////////////////////////
//Riley Kollman           //
//Pokemon Clone Game      //
//Last modified: 05/02/18 //
////////////////////////////

import java.util.*;
import java.io.*;
import java.math.*;

public class pokemonClone
{
   public static void main (String args[])
   {
      String name;
      String response;
      boolean loop;
      
      Scanner scan = new Scanner(System.in);
      
      /*System.out.println("Welcome to the world of Pokemon!");
      
      loop = true;
      while(loop)
      {
         System.out.println("What is your name?");
         name = scan.nextLine();
         
         System.out.println("Oh? your name is " +name+"?");
         response = (scan.nextLine()).toLowerCase();
         if(response.equals("no"))
         {
            System.out.println("My apologies, my hearing isn't what it used to be!");
         }
         else
         {
            Player player = new Player(name);
            loop = false;
         }
      }*/
      Player player = new Player();
      Pokemon starter = new Pikachu();
      Pokemon second = new Bulbasaur();
      player.pokemon.add(starter);
      player.pokemon.add(second);
      
      Pokemon wildPokemon = new Bulbasaur();
      battle(player, wildPokemon);
      /*wildPokemon.HP = 100;
      System.out.println(wildPokemon.HP);
      useMove(pokemon1.moves[0], pokemon1, wildPokemon);
      System.out.println(wildPokemon.HP);*/
     
   }//END OF MAIN
   
//-------------------------------------------------------------------------------------------------------
//METHODS

   //BATTLE METHOD
   public static void battle(Player player, Pokemon enemy)
   {  
      Scanner scan = new Scanner(System.in);
      
      String response;
      int enemyMove;
      int p = 0;
      boolean userTurn;
      boolean enemyTurn;
      boolean battling = true;
      boolean loop;
      
      System.out.println("A wild "+enemy.name+" appeared!");
      while(battling)
      {
         userTurn = true;
         enemyTurn = false;
         while(userTurn)
         {
            System.out.println(player.pokemon.get(p).name+" HP: "+player.pokemon.get(p).HP);
            System.out.println(enemy.name+" HP: "+enemy.HP);
            System.out.println("What will "+player.pokemon.get(p).name+" do?");
            response = scan.nextLine().toLowerCase();
            
            if(response.equals("attack"))
            {               
               loop = true;
               while (loop)
               {
                  System.out.println("Which attack will "+player.pokemon.get(p).name+" use?");
                  response = scan.nextLine().toLowerCase();
                  if(response.equals("1") || response.equals("2") || response.equals("3") || response.equals("4"))
                  {
                     int m = Integer.parseInt(response) - 1;
                     useMove(m, player.pokemon.get(p), enemy);
                     userTurn = false;
                     enemyTurn = true;
                     loop = false;
                  }
                  else
                  {
                     System.out.println("That's not a move "+player.pokemon.get(p).name+" can use!");
                  }
               }
            }           
            if(response.equals("q"))
            {
               userTurn = false;
               battling = false;
            }
         }
         if(enemyTurn)
         {            
            double r = Math.random()*10;
            if(r > 5)
            {
               enemyMove = 0;
            }
            else
            {
               enemyMove = 1;
            }
            useMove(enemyMove, enemy, player.pokemon.get(p));
         }
      }   
   }
   
   public static void useMove(int m, Pokemon attacker, Pokemon victim)
   {
      String move = attacker.moves[m].name;
      double critical = Math.random()*10;
      
      int damageOutput = ((attacker.attack * attacker.moves[m].power)/2) + (int) Math.round(Math.random()*100);
      int damage = damageOutput / victim.defense;
     
      victim.HP = victim.HP - damage;
      
      System.out.println(attacker.name+" used "+move+"!");
      System.out.println(move+" dealt "+damage+" damage.\n");
   }
}
//-------------------------------------------------------------------------------------------------------
//CLASSES

//PLAYER CLASS
class Player
{
   String name;
   ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
   
   public Player()
   {
      
   }
}

//POKEMON CLASSES
class Pokemon
{  
   String name;
   String type;
   int level;
   int HP;
   int PP;
   int attack;
   int defense; 
   Move[] moves = new Move[4];
}

class Pikachu extends Pokemon
{
   public Pikachu()
   {
      name = "Pikachu";
      type = "Electric";
      HP = 100;
      attack = 5;
      defense = 5;
      moves[0] = new Tackle();
      moves[1] = new Thunder();
   }
}

class Bulbasaur extends Pokemon
{
   public Bulbasaur()
   {
      name = "Bublasaur";
      type = "Grass";
      HP = 100;
      attack = 10;
      defense = 10;
      moves[0] = new Tackle();
      moves[1] = new VineWhip();
   }
}


//MOVE CLASSES
class Move
{
   String name;
   int power;
}

class Tackle extends Move
{
   public Tackle()
   {
      name = "Tackle";
      power = 10;
   }
}

class Thunder extends Move
{
   public Thunder()
   {
      name = "Thunder";
      power = 20;
   }
}

class VineWhip extends Move
{
   public VineWhip()
   {
      name = "Vine Whip";
      power = 20;
   }
}