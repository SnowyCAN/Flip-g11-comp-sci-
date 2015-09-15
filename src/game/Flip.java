

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game;



import static game.Flip.WIDTH;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.ERROR;
import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 *
 * @author tituo4996
 */



// make sure you rename this class if you are doing a copy/paste

public class Flip extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 1275;
    static final int HEIGHT = 955;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    
    //controls
        //booleans left, right, jump and jumping start false.
    boolean right = false, left=false,jump=false;
    boolean jumping = false;
    
    //world
    int jumpVel = -30; 
    int world =0;
    int q = 0;
        //array list for the levels gravity
    int[] gravity = new int[4];
        //array list for the level rectangle to be overwriten when each level is drawn
    Rectangle[] level = new Rectangle[0];
    
    //player variables
        //player's directionY and DirectionX bot start as zero and increase/decease as the player moves 
    int dy =0;
    int dx =0;
        //the players lastDirection faced, left is 1 and right is -1.
    int lastDirection = -1;
        //array list of the flip booleans
    boolean[] flip = new boolean[4];
        //array list of the playerX positions
    int[] playerX = new int[4];
        //array list of the playerY positions
    int[] playerY = new int[4];
        //array list of the spaceX positions
    int[] spaceX = new int[4];
        //array list of the spaceX positions
    int[] spaceY = new int[4];        

        
public void player()
   {
      //player x positions.
        //player X position for level 0, at 150.
        playerX[0] = 150;
        //player X position for level 1, at 75.
        playerX[1] = 75;
        //player X position for level 2, at 50.
        playerX[2] = 50;
        //player X position for level 3, at 50.
        playerX[3] = 50;
      
      //player y positions.
        //player Y position for level 0, at 805.
        playerY[0] = HEIGHT -150;
        //player Y position for level 1, at 55.
        playerY[1] = HEIGHT -900;
        //player Y position for level 2, at 428.
        playerY[2] = HEIGHT/2 -50;
        //player Y position for level 3, at 805.
        playerY[3] = HEIGHT -150;
      
      //level Zero
        //spaceX and spaceY at the players starting positon for level zero.
        spaceX[0] =150;
        spaceY[0] =HEIGHT -150;

      //level One
        //spaceX and spaceY at the players starting position for level one.
        spaceX[1] =75;
        spaceY[1] =HEIGHT -50;

      //level Two
        //spaceX and spaceY at the players starting position for level two.
        spaceX[2] =50;
        spaceY[2] =HEIGHT/2 -50;
 
      //level Three
        //spaceX and spaceY at the players starting position for level three.
        spaceX[3] =50;
        spaceY[3] =HEIGHT -150;
      
      
   }

    //Images
    //all the images used in the game ranging to the main screen, backgrounds and even a player sprite.
    BufferedImage logo = ImageHelper.loadImage("logo.png");
    BufferedImage gameCredits = ImageHelper.loadImage("gameCredits.png");
    BufferedImage mainMenu = ImageHelper.loadImage("mainMenu.png");
    BufferedImage controlsMenu = ImageHelper.loadImage("controlsMenu.png");
    BufferedImage levelBackground = ImageHelper.loadImage("levelBackground.png");
    BufferedImage howToPlay = ImageHelper.loadImage("howToPlay.png");
    BufferedImage levelPage = ImageHelper.loadImage("levelPage.png");
    BufferedImage gameGoal = ImageHelper.loadImage("gameGoal.png");
    BufferedImage gameGoalInverted = ImageHelper.loadImage("gameGoalInverted.png");
    BufferedImage playerStopRight = ImageHelper.loadImage("playerFiles/playerStillRight/playerStillRight2.png");
    BufferedImage playerStopRightInverted = ImageHelper.loadImage("playerFiles/playerInvertedStillRight/playerInvertedStillRight2.png");
    BufferedImage player90ClockStopRight = ImageHelper.loadImage("playerFiles/player90ClockStillRight/player90ClockStillRight2.png");
    BufferedImage player90ClockStopRightInverted = ImageHelper.loadImage("playerFiles/player90ClockInvertedStillRight/player90ClockInvertedStillRight2.png");
    BufferedImage levelZeroFrame = ImageHelper.loadImage("levelZero.png");
    BufferedImage levelOneFrame = ImageHelper.loadImage("levelOne.png");
    BufferedImage levelTwoFrame = ImageHelper.loadImage("levelTwo.png");   
    
    //initilize all the stardered animations.
    Animation runLeft;
    Animation runRight;
    Animation stopLeft;
    Animation stopRight;
    Animation runLeftInverted;
    Animation runRightInverted;
    Animation stopLeftInverted;
    Animation stopRightInverted;
    
    //initilize all the reoriented animations.
    Animation run90ClockLeft;
    Animation run90ClockRight;
    Animation stop90ClockLeft;
    Animation stop90ClockRight;
    Animation run90ClockLeftInverted;
    Animation run90ClockRightInverted;
    Animation stop90ClockLeftInverted;
    Animation stop90ClockRightInverted;

    //blocks
    //all level arrays for levels zero through 3.
    Rectangle[] levelZero = new Rectangle[5];
    Rectangle[] levelOne = new Rectangle[15];
    Rectangle[] levelTwo = new Rectangle[12];
    Rectangle[] levelThree = new Rectangle[18];

    public void gravity()
    {
        //the gavity for level zero through three, all start at 1.
        int gravityZero =1;
        int gravityOne =1;
        int gravityTwo =1;
        int gravityThree =1;
        
        //sets the value of the gravity variables to their proper place in the gravity array.
        gravity[0] = gravityZero;
        gravity[1] = gravityOne;
        gravity[2] = gravityTwo;
        gravity[3] = gravityThree;
    }
    
    public void flip()
    {
        //makes the flip booleans starting with the flipZero and ending with the flipThree.
        boolean flipZero = false;
        boolean flipOne = false;
        boolean flipTwo = false;
        boolean flipThree = false;
        
        //sets them in their proper place in the flip array.
        flip[0] = flipZero;
        flip[1] = flipOne;
        flip[2] = flipTwo;
        flip[3] = flipThree;
    }
    
    //rectangle positions for all the rectangles for levelZero
    public void levelZero()
    {
        //map walls
        Rectangle base = new Rectangle(0, HEIGHT -25, WIDTH, 100);
        Rectangle roof = new Rectangle(0, -75, WIDTH , 100);
        Rectangle leftWall = new Rectangle(-75, 0, 100, HEIGHT);
        Rectangle rightWall = new Rectangle(WIDTH -25, 0, 100, HEIGHT);
        
        //goal
        Rectangle goal = new Rectangle(WIDTH -125, 25, 50, 40);
        
        //sets the rectangles to their proper place in the levelZero array
        levelZero[0] = base;
        levelZero[1] = roof;
        levelZero[2] = leftWall;
        levelZero[3] = rightWall;
        levelZero[4] = goal;  
    }
    
    //rectangle positions for all the rectangles for levelOne
    public void levelOne()
    {
            //map top
        Rectangle mapTop = new Rectangle(0, HEIGHT -25, WIDTH, 100);
        
            //map bottom
        Rectangle mapBottom = new Rectangle(0, -75, WIDTH, 100);
            
            //map left
        Rectangle mapLeft = new Rectangle(-75, 0, 100, HEIGHT);
        
            //map right
        Rectangle mapRight = new Rectangle(WIDTH -25, 0, 100, HEIGHT);
            
            //middle blocks
        Rectangle middleBlock1 = new Rectangle(0, HEIGHT/2 -25, WIDTH -100, 50);
        Rectangle middleBlock2 = new Rectangle(WIDTH -25, HEIGHT/2 -25, 25, 50);
        
            //bottom blocks
        Rectangle bottomBlock1 = new Rectangle(200, HEIGHT -350, 50, 350);
        Rectangle bottomBlock2 = new Rectangle(450, HEIGHT /2 +25, 50, 350);
        Rectangle bottomBlock3 = new Rectangle(750, HEIGHT -350, 50, 350);
        Rectangle bottomBlock4 = new Rectangle(1050, HEIGHT /2 +25, 50, 350);
        
            //top blocks
        Rectangle topBlock1 = new Rectangle(200, HEIGHT/2 -350, 50, 350);
        Rectangle topBlock2 = new Rectangle(450, 25, 50, 350);
        Rectangle topBlock3 = new Rectangle(750, HEIGHT/2 -350, 50, 350);
        Rectangle topBlock4 = new Rectangle(1050, 25, 50, 350);
        

            //goal
        Rectangle goal = new Rectangle( 75, HEIGHT/2 - 75, 50, 40);
        
        //sets the rectangles to their proper place in the levelOne array
        levelOne[0] = mapTop;
        levelOne[1] = mapBottom;
        levelOne[2] = mapLeft;
        levelOne[3] = mapRight;
        levelOne[4] = middleBlock1;
        levelOne[5] = middleBlock2;
        levelOne[6] = bottomBlock1;
        levelOne[7] = bottomBlock2;
        levelOne[8] = bottomBlock3;
        levelOne[9] = bottomBlock4;
        levelOne[10] = topBlock1;
        levelOne[11] = topBlock2;
        levelOne[12] = topBlock3;
        levelOne[13] = topBlock4;
        levelOne[14] = goal;
    }
    
    //rectangle positions for all the rectangles for levelTwo
    public void levelTwo()
    {
        //map walls
            //outer walls
        Rectangle base = new Rectangle(0, HEIGHT -25, WIDTH, 100);
        Rectangle roof = new Rectangle(0, -75, WIDTH, 100);
        Rectangle leftWall = new Rectangle(-75, 0, 100, HEIGHT);
        Rectangle rightWall = new Rectangle(WIDTH -25, 0, 100, HEIGHT);
            
            //starting platform
        Rectangle start = new Rectangle(25, HEIGHT/2, 100, 1000);
            
            //walking platforms
        Rectangle middlePlat1 = new Rectangle (325, HEIGHT /2, 400, 50);
        Rectangle middlePlat2 = new Rectangle (925, HEIGHT /2, 200, 50);
        
        //spikes 
        Rectangle topSpike1 = new Rectangle (25, 25, 750, 50);
        Rectangle topSpike2 = new Rectangle(875, 25, 250, 50);
        Rectangle bottomSpike1 = new Rectangle(125, HEIGHT - 75, 100, 50);
        Rectangle bottomSpike2 = new Rectangle(325, HEIGHT -75, 1000, 50);
        
        //goal
        Rectangle goal = new Rectangle(WIDTH -112, 25, 50, 50);
        
        //sets the rectangles to their proper place in the levelTwo array
        levelTwo[0] = base;
        levelTwo[1] = roof;
        levelTwo[2] = leftWall;
        levelTwo[3] = rightWall;
        levelTwo[4] = start;
        levelTwo[5] = middlePlat1;
        levelTwo[6] = middlePlat2;
        levelTwo[7] = topSpike1;
        levelTwo[8] = topSpike2;
        levelTwo[9] =bottomSpike1;
        levelTwo[10] = bottomSpike2;
        levelTwo[11] = goal;
    }
    
    //rectangle positions for all the rectangles for levelThree
    public void levelThree()
    {
        //walls
            //map walls
        Rectangle base = new Rectangle(0, HEIGHT -75, WIDTH, 100);
        Rectangle roof = new Rectangle(0, -75, WIDTH, 100); 
        Rectangle leftWall = new Rectangle(-75, 100, 100, HEIGHT);
        Rectangle realRightWall = new Rectangle(WIDTH -25, 0, 100, HEIGHT);
        
            //building walls
        Rectangle highRightWall = new Rectangle(WIDTH -225, 25, 50, 125);
        Rectangle middleWalk = new Rectangle(25, HEIGHT /2 +75, WIDTH -200, 50);
        Rectangle highFlipWall = new Rectangle(100, HEIGHT /2 -50, WIDTH -275, 50);
        Rectangle secondLeftWall = new Rectangle(100, 100, 50, HEIGHT/2 -150);
        Rectangle highBase = new Rectangle(150, 100, WIDTH -475, 50);
        Rectangle middleLeftWall = new Rectangle(WIDTH - 625, 250, 50, HEIGHT /2 -275);
        Rectangle thirdLeftWall = new Rectangle(WIDTH -775, 150, 50, HEIGHT /2 -275);
        Rectangle goalPlat = new Rectangle(150, 250, 100, 50);
        Rectangle lowRightWall = new Rectangle(WIDTH -225, 250, 50, 180);
        
        
        //spike blocks
        Rectangle spike1 = new Rectangle(WIDTH -225, 150, 50, 100);
        Rectangle spike2 = new Rectangle(-25, 25, 50, 100);
        
        
        //flip points
        Rectangle flip1  = new Rectangle(WIDTH -125, HEIGHT /2 -25, 50, 50);
        Rectangle flip2 = new Rectangle(WIDTH -725, 150, 50, 50);
        
        //goal
        Rectangle goal = new Rectangle(175, 200, 50, 50);
        
        //sets the rectangles to their proper place in the levelThree array
        levelThree[0] = base;
        levelThree[1] = roof;
        levelThree[2] = leftWall;
        levelThree[3] = highRightWall;
        levelThree[4] = middleWalk;            
        levelThree[5] = highFlipWall;
        levelThree[6] = secondLeftWall;
        levelThree[7] = highBase;
        levelThree[8] = middleLeftWall;
        levelThree[9] = thirdLeftWall;
        levelThree[10] = goalPlat;
        levelThree[11] = lowRightWall;
        levelThree[12] = realRightWall;
        levelThree[13] = spike1;
        levelThree[14] = spike2;
        levelThree[15] = flip2;      
        levelThree[16] = flip1;
        levelThree[17] = goal;
        
        
    }
    
        public void createStandardAnimations()
    {
        //move right
        BufferedImage[] rightFrames = new BufferedImage[4];
        for(int i=0; i < rightFrames.length; i++)
        {
            rightFrames[i] = ImageHelper.loadImage("playerFiles/playerRight/playerRight" + (i+1) + ".png");
        }
        runRight = new Animation((rightFrames.length*3), rightFrames);
        
        //move left
        BufferedImage[] leftFrames = new BufferedImage[4];
        for(int i=0; i < leftFrames.length; i++)
        {
            leftFrames[i] = ImageHelper.loadImage("playerFiles/playerLeft/playerLeft" + (i+1) +".png");
        }
        runLeft = new Animation((leftFrames.length*3), leftFrames);
        
        //still right
        BufferedImage[] stopRightFrames = new BufferedImage[5];
        for (int i = 0; i < stopRightFrames.length; i++)
        {
            stopRightFrames[i] = ImageHelper.loadImage("playerFiles/playerStillRight/playerStillRight" +(i+1) +".png");
        }
        stopRight = new Animation(6, stopRightFrames);
        
        //still left
        BufferedImage[] stopLeftFrames = new BufferedImage[5];
        for (int i = 0; i < stopLeftFrames.length; i++)
        {
            stopLeftFrames[i] = ImageHelper.loadImage("playerFiles/playerStillLeft/playerStillLeft" +(i+1) +".png");
        }
        stopLeft= new Animation(6, stopLeftFrames);
        
        //upside down move right
        BufferedImage[] rightInvertedFrames = new BufferedImage[4];
        for(int i=0; i < rightInvertedFrames.length; i++)
        {
            rightInvertedFrames[i] = ImageHelper.loadImage("playerFiles/playerInvertedRight/playerInvertedRight" + (i+1) + ".png");
        }
        runRightInverted = new Animation((rightInvertedFrames.length*3), rightInvertedFrames);
        
        //upside down move left
        BufferedImage[] leftInvertedFrames = new BufferedImage[4];
        for(int i=0; i < leftInvertedFrames.length; i++)
        {
            leftInvertedFrames[i] = ImageHelper.loadImage("playerFiles/playerInvertedLeft/playerInvertedLeft" + (i+1) +".png");
        }
        runLeftInverted = new Animation((leftInvertedFrames.length*3), leftInvertedFrames);
        
        //upside down still right
        BufferedImage[] stopRightInvertedFrames = new BufferedImage[5];
        for (int i = 0; i < stopRightInvertedFrames.length; i++)
        {
            stopRightInvertedFrames[i] = ImageHelper.loadImage("playerFiles/playerInvertedStillRight/playerInvertedStillRight" +(i+1) +".png");
        }
        stopRightInverted = new Animation(6, stopRightInvertedFrames);
        
        //upside down still left
        BufferedImage[] stopLeftInvertedFrames = new BufferedImage[5];
        for (int i = 0; i < stopLeftInvertedFrames.length; i++)
        {
            stopLeftInvertedFrames[i] = ImageHelper.loadImage("playerFiles/playerInvertedStillLeft/playerInvertedStillLeft" +(i+1) +".png");
        }
        stopLeftInverted= new Animation(6, stopLeftInvertedFrames);
    }
    
        //creates and fills arrays for each diffrent animations with buffered images
            public void createFlippedAnimations()
    {
        //move right
        BufferedImage[] right90ClockFrames = new BufferedImage[4];
        for(int i=0; i < right90ClockFrames.length; i++)
        {
            right90ClockFrames[i] = ImageHelper.loadImage("playerFiles/player90ClockRight/player90ClockRight" + (i+1) + ".png");
        }
        run90ClockRight = new Animation((right90ClockFrames.length*3), right90ClockFrames);
        
        //move left
        BufferedImage[] left90ClockFrames = new BufferedImage[4];
        for(int i=0; i < left90ClockFrames.length; i++)
        {
            left90ClockFrames[i] = ImageHelper.loadImage("playerFiles/player90ClockLeft/player90ClockLeft" + (i+1) +".png");
        }
        run90ClockLeft = new Animation((left90ClockFrames.length*3), left90ClockFrames);
        
        //still right
        BufferedImage[] stop90ClockRightFrames = new BufferedImage[5];
        for (int i = 0; i < stop90ClockRightFrames.length; i++)
        {
            stop90ClockRightFrames[i] = ImageHelper.loadImage("playerFiles/player90ClockStillRight/player90ClockStillRight" +(i+1) +".png");
        }
        stop90ClockRight = new Animation(6, stop90ClockRightFrames);
        
        //still left
        BufferedImage[] stop90ClockLeftFrames = new BufferedImage[5];
        for (int i = 0; i < stop90ClockLeftFrames.length; i++)
        {
            stop90ClockLeftFrames[i] = ImageHelper.loadImage("playerFiles/player90ClockStillLeft/player90ClockStillLeft" +(i+1) +".png");
        }
        stop90ClockLeft= new Animation(6, stop90ClockLeftFrames);
        
        //upside down move right
        BufferedImage[] right90ClockInvertedFrames = new BufferedImage[4];
        for(int i=0; i < right90ClockInvertedFrames.length; i++)
        {
            right90ClockInvertedFrames[i] = ImageHelper.loadImage("playerFiles/player90ClockInvertedRight/player90ClockInvertedRight" + (i+1) + ".png");
        }
        run90ClockRightInverted = new Animation((right90ClockInvertedFrames.length*3), right90ClockInvertedFrames);
        
        //upside down move left
        BufferedImage[] left90ClockInvertedFrames = new BufferedImage[4];
        for(int i=0; i < left90ClockInvertedFrames.length; i++)
        {
            left90ClockInvertedFrames[i] = ImageHelper.loadImage("playerFiles/player90ClockInvertedLeft/player90ClockInvertedLeft" + (i+1) +".png");
        }
        run90ClockLeftInverted = new Animation((left90ClockInvertedFrames.length*3), left90ClockInvertedFrames);
        
        //upside down still right
        BufferedImage[] stopRight90ClockInvertedFrames = new BufferedImage[5];
        for (int i = 0; i < stopRight90ClockInvertedFrames.length; i++)
        {
            stopRight90ClockInvertedFrames[i] = ImageHelper.loadImage("playerFiles/player90ClockInvertedStillRight/player90ClockInvertedStillRight" +(i+1) +".png");
        }
        stop90ClockRightInverted = new Animation(6, stopRight90ClockInvertedFrames);
        
        //upside down still left
        BufferedImage[] stop90ClockLeftInvertedFrames = new BufferedImage[5];
        for (int i = 0; i < stop90ClockLeftInvertedFrames.length; i++)
        {
            stop90ClockLeftInvertedFrames[i] = ImageHelper.loadImage("playerFiles/player90ClockInvertedStillLeft/player90ClockInvertedStillLeft" +(i+1) +".png");
        }
        stop90ClockLeftInverted= new Animation(6, stop90ClockLeftInvertedFrames);
    }
    
    //the actual player
    Rectangle player = new Rectangle(playerX[q], playerY[q], 50, 50);
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE
        
        //if world is equal to 0, then draw the intro screen w/ controls
        if (world == 0)
        {
            displayIntroScreen(g);
        }
        
        //if world is equal to 1, then draw the main menu w/o controls
        if (world == 1)
        {
            mainMenu(g);
        }
        
        //if world is equal to 2, then draw the level select screen
        if (world == 2)
        {
            levelScreen(g);
        }
        
        //if world is equal to 3, then draw level zero
        if (world == 3)
        {
            levelZero(g);
        }
        
        //if world is equal to 4, then draw level one
        if (world == 4)
        {
            levelOne(g);
        }
        
        //if world is equal to 5, then draw level two
        if (world == 5)
        {
            levelTwo(g);
        }
        
        //of world is equal to 6, then draw level three
        if (world == 6)
        {
            levelThree(g);
        }
          
        //if world is equal to -1, then draw the game's credits
        if(world == -1)
        {
            gameCredits(g);
        }    
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        levelZero();
        levelOne();
        levelTwo();
        levelThree();
        createStandardAnimations();
        createFlippedAnimations();
        gravity();
        player();
        flip();
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
           
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE
            
            //trouble shooting variables 
            System.out.println("q: " +q);
            System.out.println("gravity: " +gravity[q]);
            System.out.println("right: " +right);
            System.out.println("left: " +left);
            System.out.println("jump: " +jump);
            System.out.println("playerX: " +playerX[q]);
            System.out.println("playerY: " +playerY[q]);
            System.out.println("world: " +world);
            System.out.println("level: " +level.length);
            System.out.println("Last Direction: " +lastDirection);
            System.out.println("flip: " +flip[q]);
            
            //if world is equal to 3, then: q equals 0, and the array level is equal to the array levelZero
            if(world == 3)
            {
                q =0;
                level = levelZero;   
            }
            
            //if world is equal to 4, then: q equals 1, and the array level is equal to the array levelOne
            if(world == 4)
            {
                q =1;
                level = levelOne;
            }
            
            //if world is equal to 5, then: q equals 2, and the array level is equal to the array levelTwo
            if(world == 5)
            {
                q =2;
                level = levelTwo;
            }
            
            //if world is equal to 6, then: q equals 3, and the array level is equal to the array levelThree
            if(world == 6)
            {
                q =3;
                level = levelThree;
            }
            
            //if world is not equal to 3, then: 
            //array playerX, space zero, is equal to the array spaceX, space zero.
            //array playerY, space zero, is equal to the array spaceY, space zero.
            //array gravity, space zero, is equal to 1.
            //array flip, space zero, is false.
            if(world != 3)
            {
                playerX[0] = spaceX[0];
                playerY[0] = spaceY[0];
                gravity[0] = 1;
                flip[0] = false;
            }
            
            
            //if world is not equal to 4, then: 
            //array playerX, space one, is equal to the array spaceX, space one.
            //array playerY, space one, is equal to the array spaceY, space one.
            //array gravity, space one, is equal to 1.
            //array flip, space one, is false.
            if(world != 4)
            {
                playerX[1] = spaceX[1];
                playerY[1] = spaceY[1];
                gravity[1] = 1;
                flip[1] = false;
            }
            
            
            //if world is not equal to 5, then: 
            //array playerX, space two, is equal to the array spaceX, space two.
            //array playerY, space two, is equal to the array spaceY, space two.
            //array gravity, space two, is equal to 1.
            //array flip, space two, is false.
            if(world != 5)
            {
                playerX[2] = spaceX[2];
                playerY[2] = spaceY[2];
                gravity[2] = 1;
                flip[2] = false;
            }
            
            //if world is not equal to 6, then: 
            //array playerX, space three, is equal to the array spaceX, space three.
            //array playerY, space three, is equal to the array spaceY, space three.
            //array gravity, space three, is equal to 1.
            //array flip, space three, is false.
            if(world != 6)
            {
                playerX[3] = spaceX[3];
                playerY[3] = spaceY[3];
                gravity[3] = 1;
                flip[3] = false;
            }           
            
            //if left is true, then lastDirection is equal to 1.
            if(left)
            {
                lastDirection =1;
            }
            
            //alternitly if left is false and right is true, then lastDirection is equal to -1.
            else if(right)
            {
                lastDirection =-1;
            }    
            
            //if world is greater than 2, then run levelLogic method.
            if(world > 2)
            {
                levelLogic();
            }    
            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if(deltaTime > desiredTime)
            {
                //took too much time, don't wait
            }else
            {
                try
                {
                    Thread.sleep(desiredTime - deltaTime);
                }catch(Exception e){};
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
       
        // creates an instance of my game
        Flip game = new Flip();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // adds the game to the window
        frame.add(game);
         
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        frame.addKeyListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        //intiger key is equal to the KeyCode value.    
        int key = e.getKeyCode();

        //if world is equal to 0, then:
        //if key enter is pressed, then world is equal to 1.
        if(world == 0)
        {
            if (key == KeyEvent.VK_ENTER)
            {
                world =1;
            }
        }
        //Home Screen
        //alternitivly if world is not equal to 0 and is equal to 1, then:
        else if(world == 1)
        {
            //go to home menu
            //if key enter is pressed, then world is equal to world plus 1.
            if(key == KeyEvent.VK_ENTER)
            {
                world++;
            }

            //go to credits
            //if key shift is pressed, then world is equal to -1.
            if(key == KeyEvent.VK_SHIFT)
            {
            world = -1;
            }
        }
        //Credits screen
        //alternitivly if world is not equal to 0 and 1, then:
        else if (world == -1)
        {   
            //Go back to home
            //if key enter is pressed, then world is equal to 1.
            if(key == KeyEvent.VK_ENTER)
            {
                world =1;
            } 
        }
        //alternitivly if world is not equal to 0, 1, and -1, then:
        else if (world == 2)
        {
            //Go back to the menu
            //if key shift is pressed, then world is equal to world minus 1.
            if(key == KeyEvent.VK_SHIFT)
            {
                world--;
            } 

            //level 1
            //if key #one is pressed, then world is equal to world plus 2.
            if(key == KeyEvent.VK_1)
            {
                world= world +2;
            }

            //level 2
            //if key #two is pressed, then world is equal to world plus 3.
            if(key == KeyEvent.VK_2)
            {
                world= world +3;
            }

            //level 3
            //if key #three is pressed, then world is equal to world plus 4.
            if(key == KeyEvent.VK_3)
            {
                world= world +4;
            }

            //How to play
            //if key #zero is pressed, then world is equal to world plus 1.
            if(key == KeyEvent.VK_0)
            {
                world++;
            }    
        }
        
        //alternitivly if world is not 1, 0 and -1, and is greater than 2, then:
        else if(world >2)
        {    

            //right
            //if key D is pressed, then right is true.
            if(key== KeyEvent.VK_D)
            {
                right=true;
            }

            //left
            //if key A is pressed, then left is true.
            if(key== KeyEvent.VK_A)
            {
                left=true;
            }

            //jump
            //if key space is pressed, then jump is true.
            if(key== KeyEvent.VK_SPACE)
            {
                jump=true;
            }

            //go to main menu
            //if key escape is pressed, then world is equal to 1.
            if (key == KeyEvent.VK_ESCAPE)
            {
                world =1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        //intiger key is equal to the KeyCode value. 
        int key = e.getKeyCode();
    //world is greater than 2, then:
    if(world >2)
    {
        //right
        //if key D was released, then right is false.
        if(key== KeyEvent.VK_D)
        {
            right=false;
        }
        //left
        //if key A was released, then left is false.
        if(key== KeyEvent.VK_A)
        {
            left=false;
        }
        //jump
        //if key space was released, then jump is false.
        if(key== KeyEvent.VK_SPACE)
        {
            jump=false;
        }
    }
}
    //the method to draw the intro screen image
    private void displayIntroScreen(Graphics g)
    {
        //draw the controlsMenu image at x 0, y 0, with a width of 1275 and a height of 955.
        g.drawImage(controlsMenu, 0, 0, 1275, 955, null);
    }
    
    //the method to draw the mainMenu
    private void mainMenu(Graphics g) 
    {
        //draw the main menu image
        g.drawImage(mainMenu, 0, 0, 1275, 955, null);
    }
    
    //method to draw the game credits screen
    private void gameCredits(Graphics g)
    {   
        //draw the gameCredits image
        g.drawImage(gameCredits, 0, 0, 1275, 955, null);
    }
    
    //method to draw the level select screen
    private void levelScreen(Graphics g) 
    {
        //draw the levelPage image
        g.drawImage(levelPage, 0, 0, 1275, 955, null);
    }

    private void levelZero(Graphics g)
    {
        //background image
        g.drawImage(howToPlay, 0, 0, 1275, 955, null);
        
        //draw the level rectangles from Rectangle array level
        for (int i = 0; i < level.length -1; i++) 
        {
            g.fillRect(level[i].x, level[i].y, level[i].width, level[i].height);
            g.drawImage(gameGoalInverted, WIDTH -125, 25, 50, 40, null);
        }
        
        //foreground image
        g.drawImage(levelZeroFrame, 0, 0, 1275, 955, this);
        
        //player animation
        if(flip[q] == false)
        {    
            if(gravity[q] == 1)
        {
                //move right
                if (right && !left) 
                {
                    g.drawImage(runRight.getFrame(), playerX[q], playerY[q], 50, 50, this);    
                }
                //move left
                else if(left && !right)
                {
                    g.drawImage(runLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }

                //face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stopRight.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stopLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRight, playerX[q], playerY[q], 50, 50, this);
                }
            }
            else if(gravity[q] == -1)
            {   
                //upside down move right
                if(right && !left)
                {
                    g.drawImage(runRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down move left
                else if(left && !right)
                {
                    g.drawImage(runLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stopRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stopLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRightInverted, playerX[q], playerY[q], 50, 50, this);
                }
            }
        }else
        {
            if(gravity[q] == 1)
            {
                //move right
                if (right && !left) 
                {
                    g.drawImage(run90ClockRight.getFrame(), playerX[q], playerY[q], 50, 50, this);    
                }
                //move left
                else if(left && !right)
                {
                    g.drawImage(run90ClockLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }

                //face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stop90ClockRight.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stop90ClockLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRight, playerX[q], playerY[q], 50, 50, this);
                }
            }
            else if(gravity[q] == -1)
            {   
                //upside down move right
                if(right && !left)
                {
                    g.drawImage(run90ClockRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down move left
                else if(left && !right)
                {
                    g.drawImage(run90ClockLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stop90ClockRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stop90ClockLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(player90ClockStopRightInverted, playerX[q], playerY[q], 50, 50, this);
                }
            }    
        }    
    }
    
    private void levelOne(Graphics g) 
    {
            //background
            g.drawImage(levelBackground, 0, 0, 1275, 955, null);

                //draw the level rectangles from Rectangle array level
                for (int i = 0; i < level.length -1; i++) 
                {
                    g.fillRect(level[i].x, level[i].y, level[i].width, level[i].height);
                    g.drawImage(gameGoal, 75, HEIGHT/2 -75, 50, 50, null);
                }
                g.drawImage(levelOneFrame, 0, 0, 1275, 955, this);
                
            //player animation
            if(flip[q] == false)
        {    
            if(gravity[q] == 1)
            {
                //move right
                if (right && !left) 
                {
                    g.drawImage(runRight.getFrame(), playerX[q], playerY[q], 50, 50, this);    
                }
                //move left
                else if(left && !right)
                {
                    g.drawImage(runLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }

                //face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stopRight.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stopLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRight, playerX[q], playerY[q], 50, 50, this);
                }
            }
            else if(gravity[q] == -1)
            {   
                //upside down move right
                if(right && !left)
                {
                    g.drawImage(runRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down move left
                else if(left && !right)
                {
                    g.drawImage(runLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stopRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stopLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRightInverted, playerX[q], playerY[q], 50, 50, this);
                }
            }
        }else
        {
            if(gravity[q] == 1)
            {
                //move right
                if (right && !left) 
                {
                    g.drawImage(run90ClockRight.getFrame(), playerX[q], playerY[q], 50, 50, this);    
                }
                //move left
                else if(left && !right)
                {
                    g.drawImage(run90ClockLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }

                //face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stop90ClockRight.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stop90ClockLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRight, playerX[q], playerY[q], 50, 50, this);
                }
            }
            else if(gravity[q] == -1)
            {   
                //upside down move right
                if(right && !left)
                {
                    g.drawImage(run90ClockRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down move left
                else if(left && !right)
                {
                    g.drawImage(run90ClockLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stop90ClockRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stop90ClockLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(player90ClockStopRightInverted, playerX[q], playerY[q], 50, 50, this);
                }
            }    
        }       
    }
    
    private void levelTwo(Graphics g) 
    {
        
        //background
        g.drawImage(levelBackground, 0, 0, 1275, 955, null);      

                //draw the level rectangles from Rectangle array level
                for (int i = 0; i < level.length -5; i++) 
                {

                    g.fillRect(level[i].x, level[i].y, level[i].width, level[i].height);
                    g.drawImage(gameGoalInverted, WIDTH -112, 25, 50, 50, null);
                }
                g.drawImage(levelTwoFrame, 0, 0, 1275, 955, this);
        
        //player animations
        if(flip[q] == false)
        {    
            if(gravity[q] == 1)
            {
                //move right
                if (right && !left) 
                {
                    g.drawImage(runRight.getFrame(), playerX[q], playerY[q], 50, 50, this);    
                }
                //move left
                else if(left && !right)
                {
                    g.drawImage(runLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }

                //face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stopRight.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stopLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRight, playerX[q], playerY[q], 50, 50, this);
                }
            }
            else if(gravity[q] == -1)
            {   
                //upside down move right
                if(right && !left)
                {
                    g.drawImage(runRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down move left
                else if(left && !right)
                {
                    g.drawImage(runLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stopRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stopLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRightInverted, playerX[q], playerY[q], 50, 50, this);
                }
            }
        }else
        {
            if(gravity[q] == 1)
            {
                //move right
                if (right && !left) 
                {
                    g.drawImage(run90ClockRight.getFrame(), playerX[q], playerY[q], 50, 50, this);    
                }
                //move left
                else if(left && !right)
                {
                    g.drawImage(run90ClockLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }

                //face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stop90ClockRight.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stop90ClockLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRight, playerX[q], playerY[q], 50, 50, this);
                }
            }
            else if(gravity[q] == -1)
            {   
                //upside down move right
                if(right && !left)
                {
                    g.drawImage(run90ClockRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down move left
                else if(left && !right)
                {
                    g.drawImage(run90ClockLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stop90ClockRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stop90ClockLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(player90ClockStopRightInverted, playerX[q], playerY[q], 50, 50, this);
                }
            }    
        }
    }
    
    private void levelThree(Graphics g) 
    {
        
        //background image
        g.drawImage(levelBackground, 0, 0, 1275, 955, null);        

        //draw the level rectangles from Rectangle array level
        for (int i = 0; i < level.length -1; i++) 
        {
            if(i == level.length -5)

            {
                g.setColor(Color.RED);
            }
            
            if(i == level.length -3)
            {
                g.setColor(Color.YELLOW);
            }
            
            g.fillRect(level[i].x, level[i].y, level[i].width, level[i].height);
            
            //goal image
            g.drawImage(gameGoal, 175, 200, 50, 50, null);
        }
        
        //player animation
        if(flip[q] == false)
        {    
            if(gravity[q] == 1)
            {
                //move right
                if (right && !left) 
                {
                    g.drawImage(runRight.getFrame(), playerX[q], playerY[q], 50, 50, this);    
                }
                //move left
                else if(left && !right)
                {
                    g.drawImage(runLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }

                //face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stopRight.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stopLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRight, playerX[q], playerY[q], 50, 50, this);
                }
            }
            else if(gravity[q] == -1)
            {   
                //upside down move right
                if(right && !left)
                {
                    g.drawImage(runRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down move left
                else if(left && !right)
                {
                    g.drawImage(runLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stopRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stopLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRightInverted, playerX[q], playerY[q], 50, 50, this);
                }
            }
        }else
        {
            if(gravity[q] == 1)
            {
                //move right
                if (right && !left) 
                {
                    g.drawImage(run90ClockRight.getFrame(), playerX[q], playerY[q], 50, 50, this);    
                }
                //move left
                else if(left && !right)
                {
                    g.drawImage(run90ClockLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }

                //face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stop90ClockRight.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stop90ClockLeft.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(playerStopRight, playerX[q], playerY[q], 50, 50, this);
                }
            }
            else if(gravity[q] == -1)
            {   
                //upside down move right
                if(right && !left)
                {
                    g.drawImage(run90ClockRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down move left
                else if(left && !right)
                {
                    g.drawImage(run90ClockLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face right
                else if(!left && !right && lastDirection == -1)
                {
                    g.drawImage(stop90ClockRightInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                //upside down face left
                else if(!left && !right && lastDirection == 1)
                {
                    g.drawImage(stop90ClockLeftInverted.getFrame(), playerX[q], playerY[q], 50, 50, this);
                }
                else if(left && right)
                {
                    g.drawImage(player90ClockStopRightInverted, playerX[q], playerY[q], 50, 50, this);
                }
            }    
        }
    }

    private void levelLogic()
    {
        //move left
        if(left)
        {
            dx = -5;
            if(flip[q] == false)
            {    
                if(gravity[q] == 1)
                {
                    runLeft.play(); 
                }else if(gravity[q] == -1)
                {
                    runLeftInverted.play();
                }
            }else
            {
                if(gravity[q] == 1)
                {
                    run90ClockLeft.play(); 
                }else if(gravity[q] == -1)
                {
                    run90ClockLeftInverted.play();
                }
            }           
        }else
        {   
            if(flip[q] == false)
            {
                if(gravity[q] == 1)
                {
                    runLeft.stop();
                }else if(gravity[q] == -1)
                {
                    runLeftInverted.stop();
                }
            }else
            {
                if(gravity[q] == 1)
                {
                    run90ClockLeft.stop();
                }else if(gravity[q] == -1)
                {
                    run90ClockLeftInverted.stop();
                }    
            }
        }    
            
        //move right
        if(right)
        {
            dx = +5;
            if(flip[q] == false)
            {   
                if(gravity[q] == 1)
                {
                    runRight.play();
                }else if(gravity[q] == -1)
                {
                    runRightInverted.play();
                }
            }else
            {
                if(gravity[q] == 1)
                {
                    run90ClockRight.play();
                }else if(gravity[q] == -1)
                {
                    run90ClockRightInverted.play();
                }    
            }    
        }else
        {
            if(flip[q] == false)
            {    
                if(gravity[q] == 1)
                {
                    runRight.stop();  
                }else if (gravity[q] == -1)
                {
                    runRightInverted.stop();
                }
            }else
            {
                if(gravity[q] == 1)
                {
                    run90ClockRight.stop();  
                }else if (gravity[q] == -1)
                {
                    run90ClockRightInverted.stop();
                }    
            }    
        }

        //stay on the left of the screen
        if(playerX[q] < 0)
        {   
            playerX[q]++;
            dx = 0;
        }

        if (playerX[q] + 50 >= WIDTH) 
        {
            playerX[q]--;
            dx = 0;
        }
        
        //if both or neither left or right are pressed 
        if((!right && !left) || (right && left))
        {
            dx=0;
            if(flip[q] == false)
            {
                if (lastDirection == -1)
                {
                    if(gravity[q] == 1)
                    {
                        stopRight.play();
                        stopLeft.stop();
                    }else if(gravity[q] == -1)
                    {
                        stopRightInverted.play();
                        stopLeftInverted.stop();
                    }
                }else if (lastDirection == 1)
                {
                    if(gravity[q] == 1)
                    {
                        stopLeft.play();
                        stopRight.stop();
                    }else if(gravity[q] == -1)
                    {
                        stopLeftInverted.play();
                        stopRightInverted.stop();
                    }
                }
            }else
            {
            if (lastDirection == -1)
                {
                    if(gravity[q] == 1)
                    {
                        stop90ClockRight.play();
                        stop90ClockLeft.stop();
                    }else if(gravity[q] == -1)
                    {
                        stop90ClockRightInverted.play();
                        stop90ClockLeftInverted.stop();
                    }
                }else if (lastDirection == 1)
                {
                    if(gravity[q] == 1)
                    {
                        stop90ClockLeft.play();
                        stop90ClockRight.stop();
                    }else if(gravity[q] == -1)
                    {
                        stop90ClockLeftInverted.play();
                        stop90ClockRightInverted.stop();
                    }
                }    
            }
        }
        if(flip[q] == false)
        {
            playerX[q] = playerX[q] + dx;
            playerY[q] = playerY[q] + dy;
        }else
        {
            playerX[q] = playerX[q] + dy;
            playerY[q] = playerY[q] + dx;
        }
        

        //jumping and or gravity switch
        if(!jumping && jump)
        { 
            jumping = true;
            gravity[q] *= -1;
        }

        if (playerY[q] <= 0)
        {
            dy = 0;
        }
        if(Math.abs(dy) <= Math.abs(jumpVel))
        {
            dy = dy + gravity[q];
        }

        player.y = playerY[q];
        player.x = playerX[q];
        
        for (int i = 0; i < level.length; i++)
        {
            if (player.intersects(level[i]))
            {
               
                System.out.println("stuff");
                if(q == 2)
                {   
                    if(player.intersects(level[7]) || player.intersects(level[8]) || player.intersects(level[9]) || player.intersects(level[10]))
                    {    
                        playerX[q] = spaceX[q];
                        playerY[q] = spaceY[q];
                        gravity[q] = 1;
                        flip[q] = false;
                        lastDirection = -1;
                        
                    }
                }
                
                if(q == 3)
                {
                    if(player.intersects(level[level.length -5]) || player.intersects(level[level.length -4]))
                    {
                        playerX[q] = spaceX[q];
                        playerY[q] = spaceY[q];
                        gravity[q] = 1;
                        flip[q] = false;
                        lastDirection = -1;
                    }
                }
                
                if(q == 3 && player.intersects(level[level.length -2]))
                {
                    if(flip[q] == true)
                    {
                        flip[q] = false;
                    }else
                    {
                        flip[q] = true;
                    }
                    
                } 
                
                if(q == 3 && player.intersects(level[level.length -3]))
                {
                if(flip[q] == true)
                    {
                        flip[q] = false;
                    }else
                    {
                        flip[q] = true;
                    }   
                } 
                if(world == 3)
                {    
                    if(player.intersects(level[level.length-1]))
                    {
                        world = 4;
                    }
                }else if(world == 4) 
                {
                    if(player.intersects(level[level.length-1]))
                    {
                        world = 5;
                    }
                }else if(world == 5)
                {
                    if(player.intersects(level[level.length-1]))
                    {
                        world = 6;
                    }
                }else if(world == 6)
                {
                    if(player.intersects(level[level.length-1]))
                    {
                        world = -1;
                    }
                }    
                    
                Rectangle overlap = player.intersection(level[i]);                
                //which part of the overlap is smaller
                if(overlap.width < overlap.height)//x
                {   
                    
                    dx = 0;
                    //player to the left of the block
                    if(playerX[q] < level[i].x)
                    {
                        playerX[q] = playerX[q] - overlap.width;
                        
                       if(flip[q] == true)
                       {
                           dy = 0;
                           if(gravity[q] > 0)
                           {
                               jumping = false;
                           }
                       }
                    }else if (playerX[q] > level[i].x)//player to the right
                    {
                        playerX[q] = playerX[q] + overlap.width;
                        if(flip[q] == true)
                        {   
                            dy = 0;
                            if(gravity[q] < 0)
                            {
                                jumping = false;
                            }
                        }
                    }
                }
                else//y
                {
                    if(playerY[q] < level[i].y)//above the block
                    {   
                        playerY[q] = playerY[q] - overlap.height;
                        if(flip[q] == false)
                        {
                            if (gravity[q] > 0)
                            {
                                jumping=false;
                            }
                            dy = 0;
                        }
                    }else//below the block
                    {
                        playerY[q] = playerY[q] + overlap.height;
                        if(flip[q] == false)
                        {
                            dy = 0;
                            if (gravity[q] < 0)
                            {
                                jumping = false;  
                            }
                        }    
                    }    
                }                
            }   
        }   
    }   
}