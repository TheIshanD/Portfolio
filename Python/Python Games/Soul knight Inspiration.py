import pygame
from random import randrange
from math import sqrt
from time import sleep
from time import time

pygame.init()

#Defining Colors
white=(255,255,255)
black=(0,0,0)
red=(200,0,0)
green=(0,155,0)
blue=(0,0,255)
orange=(255,140,0)
gray=(192,192,192)
yellow=(200,200,0)
light_green=(0,255,0)
light_yellow=(255,255,0)
light_red=(255,0,0)
light_orange=(255,165,0)

#Setting Values
FPS=75

lok = 1
Apple_Health=1
display_width = 800
display_height = 600
room_width=display_height
apples = 1
original_apples=apples
round_num = 1

CarSize=20
scoreValue=0
EngineSpeed=4
AppleSpeed=0.1
weaponpower=1


#Setting Screen Size
gameDisplay = pygame.display.set_mode((display_width,display_height))

#Naming the Program
pygame.display.set_caption("Blocky Road")

#Naming Time/Speed
clock = pygame.time.Clock()

#Text Size Choices
smallfont = pygame.font.SysFont("comicsansms", 20)
medfont = pygame.font.SysFont("comicsansms", 35)
largefont = pygame.font.SysFont("comicsansms", 70)

#Text Size Functionality  
def text_objects(text,color,size):
    if size=="small":
        textSurface=smallfont.render(text, True,color)
    elif size=="medium":
        textSurface=medfont.render(text, True,color)
    elif size=="large":
        textSurface=largefont.render(text, True,color)
        
    return textSurface, textSurface.get_rect()
def text_to_button(msg, color,buttonx,buttony,buttonwidth,buttonheight,size="small",action="none"):
    textSurf, textRect= text_objects(msg,color, size)
    textRect.center=(buttonx+(buttonwidth/2)),(buttony+(buttonheight/2))
    gameDisplay.blit(textSurf, textRect)

#Text Functionality  
def message_to_screen(msg,color,y_displace=0,size="small"):
    textSurf, textRect= text_objects(msg,color, size)
    textRect.center= (display_width/2),((display_height/2)+y_displace)
    gameDisplay.blit(textSurf, textRect)
#Button Functionality
def button(text,x,y,width,height,inactivecolor,activecolor,action=None):
    global gold_num
    global EngineSpeed
    global weaponpower
    cur = pygame.mouse.get_pos()
    click=pygame.mouse.get_pressed()

    if x + width > cur[0] > x and y+height> cur[1] > y:
        if click[0]==1 and action != None:
            if action == "quit":
                pygame.quit()
                quit()
            elif action == "play":
                gameLoop()
            elif action == "weapon":
                if gold_num >= 50:
                    weaponpower+=1
                    pygame.draw.rect(gameDisplay,white,(600,100,200,100))
                    pygame.draw.rect(gameDisplay,white,(600,0,200,100))
                    gold_num = gold_num - 50
                    score("Gold: ", gold_num, 600 ,0)
                    score("Damage per shot: ", weaponpower, 600,100)
                    pygame.display.update()
                for event in pygame.event.get():
                    if event.type == pygame.QUIT:
                        pygame.quit()
                        quit()
                    if event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_b:
                            shop=False
                        elif event.key==pygame.K_q:
                            pygame.quit()
                            quit()
            elif action == "speed":
                if gold_num >=50:
                    pygame.draw.rect(gameDisplay,white,(600,200,200,100))
                    EngineSpeed+=0.5
                    pygame.draw.rect(gameDisplay,white,(600,0,200,100))
                    gold_num = gold_num-50
                    score("Gold: ", gold_num, 600 ,0)
                    score("Your Speed: ", EngineSpeed,600,200)
                    pygame.display.update()
                for event in pygame.event.get():
                    if event.type == pygame.QUIT:
                        pygame.quit()
                        quit()
                    if event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_b:
                            shop=False
                        elif event.key==pygame.K_q:
                            pygame.quit()
                            quit()
            elif action == "controls":
                controls()
        pygame.draw.rect(gameDisplay,activecolor,(x,y,width,height))
    else:
        pygame.draw.rect(gameDisplay,inactivecolor,(x,y,width,height))
    text_to_button(text,black,x,y,width,height)
#Pause Functionality
def pause():
    
    pause=True
    message_to_screen("Paused",black,-100,"large")
    message_to_screen("Press P to Continue or Q to quit",black,25)
    pygame.display.update()
    while pause:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_p:
                    pause=False
                elif event.key==pygame.K_q:
                    pygame.quit()
                    quit()

    clock.tick(0)
#Explosion Animation
def explosion(x,y):

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            quit()
    explode=True

    while explode:

        startPoint=x,y
        colorChoices=[red,light_red,yellow,light_yellow,orange,light_orange]
        magnitude=1

        while magnitude < 30:
            exploding_bit_x = x+randrange(-1*magnitude,magnitude)
            exploding_bit_y = y+randrange(-1*magnitude,magnitude)

            pygame.draw.circle(gameDisplay,colorChoices[randrange(0,6)],(int(exploding_bit_x),int(exploding_bit_y)),2)
            magnitude +=1
            pygame.display.update()
            clock.tick(100)
        explode=False
#Shooting Logic
def fireshell(x,y,applex,appley,applewidth,appleheight,randApplexs,randAppleys,Apple_move_x,Apple_move_y,AppleHealth):
    global apples
    global gold_num
    gold_num = gold_num - 1
    for count in range (apples):
        Apple_x_cor=randApplexs[count] + Apple_move_x[count]
        Apple_y_cor=Apple_move_y[count] + randAppleys[count]
        pygame.draw.rect(gameDisplay,red,(Apple_x_cor , Apple_y_cor,applewidth,appleheight))
    fire=True
    score("Zombies Left: ",apples, 0, 0)
    score("Gold: ", gold_num, 700 ,0)
    
    cur=pygame.mouse.get_pos()
    click=pygame.mouse.get_pressed()
    startingShellx=x
    startingShelly=y
    if x == cur[0]:
        x=x+1
    slope=(y-cur[1])/(x-cur[0])
    deltax=10*1/sqrt(1+(slope*slope))
    deltay=10*slope/sqrt(1+(slope*slope))
    if cur[0] < x:
        deltax=-deltax
        if cur[1] < y or cur[1]>y:
            deltay=-deltay
        
    while fire:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
        if startingShelly != y:
            pygame.draw.circle(gameDisplay, red,(int(startingShellx),int(startingShelly)),5)
            if startingShelly-deltay != y:
                pygame.draw.circle(gameDisplay, white,(int(startingShellx-deltax),int(startingShelly-deltay)),5)
        startingShelly +=deltay
        startingShellx +=deltax
        
        pygame.display.update()
        clock.tick(50)
#Shell Collision Logic
        if startingShelly < display_height/2-5*room_width/12:
            explosion(startingShellx,startingShelly)
            fire=False
        elif startingShelly >5*room_width/12 + display_height/2:
            explosion(startingShellx,startingShelly)
            fire=False
        elif startingShellx < display_width/2 - 5*room_width/12:
            explosion(startingShellx,startingShelly)
            fire=False
        elif startingShellx > display_width/2 + 5*room_width/12  -CarSize*3/5:
            explosion(startingShellx,startingShelly)
            fire=False
        for count in range(apples):
            if (randApplexs[count]+Apple_move_x[count])<startingShellx<randApplexs[count]+Apple_move_x[count]+applewidth:
                if randAppleys[count]+Apple_move_y[count]<startingShelly<randAppleys[count]+appleheight+Apple_move_y[count]:
                    explosion(startingShellx,startingShelly)
                    AppleHealth[count]=AppleHealth[count] - weaponpower
                    if AppleHealth[count] <= 0:
                        
                        AppleHealth.pop(count)
                        randAppleys.pop(count)
                        randApplexs.pop(count)
                        Apple_move_x.pop(count)
                        Apple_move_y.pop(count)
                        apples = apples-1
                        gold_num = gold_num + 10
                        break
                    fire=False

#making the controls page
def controls():
    controls=True
    gameDisplay.fill(white)
    message_to_screen("Use WASD or ARROW KEYS to move", black,-200,"medium")
    message_to_screen("Use mouse to aim and left click to fire", black,-100,"medium")
    message_to_screen("Press F to pause", black,0,"medium")
    message_to_screen("Press B for the shop", black,100,"medium")
    message_to_screen("Coded by: Ishan Dasgupta on September 4th 2018", black,270)
    while controls:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
        
        button("Play",150,450,100,50, green, light_green,action="play")
        button("Quit",550,450,100,50,red, light_red,action="quit")
        pygame.display.update()
        
#Making The Shop        
def shop():
    global Apple_Health
    global round_num
    global lok

    gameDisplay.fill(white)
    shop= True
    
    if (round_num+1) % 5 == 0:
         lok = lok +1
         
    score("Zombies Coming: ",round_num + 1, 0, 0)
    score("Gold: ", gold_num, 600 ,0)
    score("Level Coming: ",round_num + 1, 0, 100)
    score("Damage per shot: ", weaponpower, 600,100)
    score("Your Speed: ", EngineSpeed,600,200)
    score("Zombie Speed Coming: ", AppleSpeed, 0, 200)
    score("Zombie Health Coming: ", lok,0 , 300)

    message_to_screen("Shop",black,-100,"large")
    while shop:
        clock.tick(15)
        pygame.display.update()
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_b:
                    shop=False
                elif event.key==pygame.K_q:
                    pygame.quit()
                    quit()
        button("Upgrade Weapon",500,420,175,50,red, light_red,action="weapon")
        button("Upgrade Speed",300,420,175,50, yellow, light_yellow,action="speed")
        score("Cost: 50 Gold","", 320, 520, yellow)
        score("Cost: 50 Gold","", 520, 520, yellow)
        clock.tick(15)
        pygame.display.update()
                    


        
#Writing the Score
def score(message ,score, xcoord, ycoord, color = black):
    text = smallfont.render(str(message)+str(score), True, color)
    gameDisplay.blit(text,[xcoord,ycoord])
#Making the Inrto
def game_intro():

    intro= True
    while intro:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            if event.type==pygame.KEYDOWN:
                if event.key == pygame.K_p:
                    intro=False
                if event.key == pygame.K_q:
                    pygame.quit()
                    quit()

        gameDisplay.fill(white)
        message_to_screen("Welcome to Blocky Road!", orange, -150, "large")
        
        button("Play",150,450,100,50, green, light_green,action="play")
        button("Controls",350,450,100,50, yellow, light_yellow,action="controls")
        button("Quit",550,450,100,50,red, light_red,action="quit")
        message_to_screen("Coded by: Ishan Dasgupta on September 4th 2018", black,270)
        pygame.display.update()
        clock.tick(15)

        
# The Game Itself
def gameLoop():
    l=1
    v=1
    AppleHealth = []
    Apple_move_x=[]
    randApplexs=[]
    randAppleys=[]
    Apple_move_y=[]
# calling and defining set values
    global lok
    global round_num
    global gold_num
    global Apple_Health
    global AppleSpeed
    global original_apples
    global apples
    global logimg
    global FPS
    global scoreValue
    global EngineSpeed
    global weaponpower

    lok = 1
    
    appleheight=CarSize
    applewidth=appleheight*3/5
    
    gameOver = False
    gameExit = False

    round_num = 1
    gold_num = 10
    Apple_Health = 1
    weapon_power = 1
    EngineSpeed = 4 
    AppleSpeed = 0.1
    original_apples = 1
    apples = original_apples
        
    lead_y=display_height/2
    lead_x=display_width/2
    
    lead_x_change=0
    lead_y_change=0
    click=pygame.mouse.get_pressed()
    

# High Score Ending 
    while not gameExit:
        gameDisplay.fill(white)
        message_to_screen("Game Over", red, -125, size="large")
        message_to_screen(str(scoreValue), green,165)
        message_to_screen("Your Score:", blue,125)
        message_to_screen("Coded by: Ishan Dasgupta on September 4th 2018", black,270,"small")
        while gameOver==True:
            Apple_move_x=0
            Apple_move_y=0
            button("Play Again",50,420,175,50, green, light_green,action="play")
            button("Exit Game",575,420,175,50, green, light_green,action="quit")
            pygame.display.update()
            
            
# Pause and Quit Functionality
            for event in pygame.event.get():
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_q:
                        pygame.quit()
                        quit()
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()                    
#Developing Tests

##                elif event.key == pygame.K_i:
##                    FPS+=1
##                elif event.key == pygame.K_o:
##                    if FPS>1:
##                        FPS-=1



            
        lead_x+=lead_x_change
        lead_y+=lead_y_change
        
        #Drawing Fixed Non-interactable Shapes
        
                
        pygame.draw.rect(gameDisplay,green,((display_width-room_width)/2,(display_height-room_width)/2,room_width,room_width))
        pygame.draw.rect(gameDisplay,white,(display_width/2 - 5*room_width/12,display_height/2-5*room_width/12,5*room_width/6,5*room_width/6))
        pygame.draw.rect(gameDisplay,blue,(lead_x,lead_y,CarSize*3/5,CarSize))

    #Car Movement
        for event in pygame.event.get():
            if event.type == pygame.MOUSEBUTTONDOWN:
                fireshell(lead_x,lead_y,Apple_x_cor,Apple_y_cor,applewidth,appleheight,randApplexs,randAppleys, Apple_move_x, Apple_move_y,AppleHealth)
            if event.type == pygame.QUIT:
                gameExit = True
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    lead_x_change=-EngineSpeed
                    lead_y_change=0
                elif event.key == pygame.K_RIGHT:
                    lead_x_change=EngineSpeed
                    lead_y_change=0
                elif event.key == pygame.K_UP:
                    lead_y_change=-EngineSpeed
                    lead_x_change=0
                elif event.key == pygame.K_DOWN:
                    lead_y_change=EngineSpeed
                    lead_x_change=0
                elif event.key == pygame.K_a:
                    lead_x_change=-EngineSpeed
                    lead_y_change=0
                elif event.key == pygame.K_d:
                    lead_x_change=EngineSpeed
                    lead_y_change=0
                elif event.key == pygame.K_w:
                    lead_y_change=-EngineSpeed
                    lead_x_change=0
                elif event.key == pygame.K_s:
                    lead_y_change=EngineSpeed
                    lead_x_change=0
                elif event.key == pygame.K_f:
                    pause()
                elif event.key == pygame.K_b:
                    shop()
            if event.type==pygame.KEYUP:
                if event.key== pygame.K_RIGHT:
                    lead_x_change=0
                elif event.key== pygame.K_LEFT:
                    lead_x_change=0
                elif event.key== pygame.K_UP:
                    lead_y_change=0
                elif event.key== pygame.K_DOWN:
                    lead_y_change=0
                elif event.key== pygame.K_w:
                    lead_y_change=0
                elif event.key== pygame.K_a:
                    lead_x_change=0
                elif event.key== pygame.K_s:
                    lead_y_change=0
                elif event.key== pygame.K_d:
                    lead_x_change=0
        

#Making Enemy follow me
        if apples == 0:
            AppleSpeed = AppleSpeed+0.5
            l=1
            v=1
            original_apples = original_apples + 1
            shop()
            gameDisplay.fill(white)
            pygame.draw.rect(gameDisplay,green,((display_width-room_width)/2,(display_height-room_width)/2,room_width,room_width))
            pygame.draw.rect(gameDisplay,white,(display_width/2 - 5*room_width/12,display_height/2-5*room_width/12,5*room_width/6,5*room_width/6))
            pygame.draw.rect(gameDisplay,blue,(lead_x,lead_y,CarSize*3/5,CarSize))
            pygame.display.update()
            apples = original_apples
            round_num = round_num + 1
            AppleHealth = []
            Apple_move_x=[]
            randApplexs=[]
            randAppleys=[]
            Apple_move_y=[]
            if round_num % 5 == 0:
                Apple_Health = Apple_Health + 1
        score("Zombies Left: ",apples, 0, 0)
        score("Gold: ", gold_num, 700 ,0)
        for count in range(apples):
            if l==1:
                AppleHealth.append(Apple_Health)
                Apple_move_x.append(0)
                Apple_move_y.append(0)
                randApplexs.append(round(randrange(display_width/2 - 5*room_width/12,display_width/2+5*room_width/12 - applewidth)))
                randAppleys.append(round(randrange(display_height/2 - 5*room_width/12,display_height/2+5*room_width/12 - appleheight)))
                if apples -1 == count:
                    l=2
            
            Apple_x_cor=randApplexs[count] + Apple_move_x[count]
            Apple_y_cor=Apple_move_y[count] + randAppleys[count]
            pygame.draw.rect(gameDisplay,red,(Apple_x_cor , Apple_y_cor,applewidth,appleheight))
            
            if apples >= count:
                if v==1:
                    pygame.display.update()
                    sleep(1/apples)
                    if apples -1 == count:
                        v=2
                        
            if lead_x > Apple_x_cor and lead_y > Apple_y_cor:
                Apple_move_y[count] +=AppleSpeed
                Apple_move_x[count] +=AppleSpeed
            elif lead_x < Apple_x_cor and lead_y < Apple_y_cor:
                Apple_move_y[count] -=AppleSpeed
                Apple_move_x[count] -=AppleSpeed
            elif lead_x < Apple_x_cor and lead_y > Apple_y_cor:
                Apple_move_y[count] +=AppleSpeed
                Apple_move_x[count] -=AppleSpeed
            elif lead_x > Apple_x_cor and lead_y < Apple_y_cor:
                Apple_move_y[count] -=AppleSpeed
                Apple_move_x[count] +=AppleSpeed
            elif lead_x==Apple_x_cor and lead_y < Apple_y_cor:
                Apple_move_y[count] -=AppleSpeed
            elif lead_x==Apple_x_cor and lead_y > Apple_y_cor:
                Apple_move_y[count] +=AppleSpeed
            elif lead_y==Apple_y_cor and lead_x < Apple_x_cor:
                Apple_move_x[count] -=AppleSpeed
            elif lead_y==Apple_y_cor and lead_x > Apple_x_cor:
                Apple_move_x[count] +=AppleSpeed
                
                
                
    ##    #Creating random obstacles
    ##        brickorlog=int(random.randrange(0,2))
    ##        if gamemove-appleheight>display_height:
    ##            gamemove=0
    ##            randApplex=round(random.randrange(display_width/4+10,display_width*3/4-applewidth-10))
    ##            EngineSpeed+=1
    ##            scoreValue+=1
    ##
    ##            if brickorlog==1:
    ##                logimg = pygame.image.load("brick.png")
    ##            else:
    ##              logimg = pygame.image.load("log.png")
                    
        #Collision Detection
            if lead_x>Apple_x_cor and lead_x < Apple_x_cor+applewidth or lead_x < Apple_x_cor and lead_x + CarSize *3/5 > Apple_x_cor:
               if lead_y>Apple_y_cor and lead_y -CarSize < Apple_y_cor or lead_y<Apple_y_cor and lead_y  > Apple_y_cor - appleheight:
                    gameOver=True
         
        #Boundrys
            
            if lead_y + CarSize>5*room_width/12 + display_height/2:
                lead_y=5*room_width/12 + display_height/2 - CarSize
                
            if lead_y<display_height/2-5*room_width/12:
                lead_y=display_height/2-5*room_width/12
                
            if lead_x<display_width/2 - 5*room_width/12:
                lead_x=display_width/2 - 5*room_width/12
                
            if lead_x+CarSize*3/5>display_width/2 + 5*room_width/12 :
                lead_x=display_width/2 + 5*room_width/12  -CarSize*3/5
                
            if Apple_y_cor + CarSize>5*room_width/12 + display_height/2:
                Apple_y_cor=5*room_width/12 + display_height/2 - CarSize
                
                
            if Apple_y_cor<display_height/2-5*room_width/12:
                Apple_y_cor=display_height/2-5*room_width/12
                
                
            if Apple_x_cor<display_width/2 - 5*room_width/12:
                Apple_x_cor=display_width/2 - 5*room_width/12
                
            if Apple_x_cor+CarSize*3/5>display_width/2 + 5*room_width/12 :
                Apple_x_cor=display_width/2 + 5*room_width/12  -CarSize*3/5
            
        
        #Resetting Screen
        pygame.display.update()
        clock.tick(FPS)
    #Endgame
    pygame.quit()
    quit()

#Calling the Program
game_intro()

gameLoop()


