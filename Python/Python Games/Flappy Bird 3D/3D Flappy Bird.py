import pygame
import random
import time

pygame.init()

#Defining Colors
white=(255,255,255)
black=(0,0,0)
red=(255,0,0)
green=(0,155,0)
blue=(230,255,255)
orange=(255,140,0)
gray=(192,192,192)
yellow=(255,255,0)

#Set Values
FPS=50

countStarter=0.0000000000000000001
count = 1 + countStarter
acceleration=1

display_width = 800
display_height = 600

origin_x = display_width/2
origin_y = display_height/2

CarSize=70
scoreValue=0
EngineSpeed=1


#Setting Screen Size
gameDisplay = pygame.display.set_mode((display_width,display_height))

#Naming the Program
pygame.display.set_caption("Flappy Bird")

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

#Text Functionality  
def message_to_screen(msg,color,y_displace=0,size="small"):
    textSurf, textRect= text_objects(msg,color, size)
    textRect.center= (display_width/2),((display_height/2)+y_displace)
    gameDisplay.blit(textSurf, textRect)
gamemove=0

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
    
#Writing the Score
def score(score):
    text = smallfont.render("Score: "+str(score), True,black)
    gameDisplay.blit(text,[0,0])

# Fuctionality in Intro
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
                


# Intro
        gameDisplay.fill(white)
        message_to_screen("Welcome to Flappy Bird!", orange, -150, "large")
        message_to_screen("The objective of the game is avoid obstacles",black, -80)
        message_to_screen("The more obstacles you avoid the faster you go",black, -40)
        message_to_screen("If you hit the wall, the game ends!",black)
        message_to_screen("Press P to play, F to pause, or Q to quit",black, 130,"medium")
        message_to_screen("Coded by: Ishan Dasgupta on September 4th 2018", black,270)
        pygame.display.update()
        clock.tick(15)
        
def barrier(display_width,display_height,origin_y,origin_x,box_x_pos,total_y_change,count):

    pygame.draw.rect(gameDisplay,green,(1999*display_width/4000 - count, - display_width/4000 -2*count ,display_width/2000+2*count,display_height/2-total_y_change))
    pygame.draw.rect(gameDisplay,green,(1999*display_width/4000-count,display_height/2 + display_width/4000+2*count-total_y_change ,display_width/2000+2*count,display_height/2+total_y_change))
    pygame.display.update()
        
# The Game Itself
def gameLoop():
    global logimg
    global FPS
    global scoreValue
    global gamemove
    global EngineSpeed
    global origin_x
    global origin_y
    global acceleration
    global count
    global countStarter
    
    x_change = 0
    y_change = 0
    
    appleheight=25
    applewidth=60

    total_y_change = 0
    box_x_pos = 1999*display_width/4000
    
    gameOver = False
    gameExit = False
    

    randApplex=round(random.randrange(display_width/4,display_width*3/4-CarSize))

# High Score Ending 
    while not gameExit:
        gameDisplay.fill(blue)
        score(scoreValue)
        while gameOver==True:
            if EngineSpeed>= 60:
                message_to_screen("YOU BEAT THE GAME", red, -125, size="large")
                message_to_screen("Xx60xX", orange,165,"large")
            else:
                message_to_screen("Game Over", red, -125, size="large")
                message_to_screen(str(scoreValue), green,165)
            message_to_screen(" Press P to play again or Q to quit.",black,size="medium")
            message_to_screen("Your Score:", blue,125)
            message_to_screen("Coded by: Ishan Dasgupta on September 4th 2018", black,270,"small")
            pygame.display.update()
            
# Pause and Quit Functionality
            for event in pygame.event.get():
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_q:
                        gameOver=False
                        gameExit=True
                    if event.key == pygame.K_p:
                        gamemove=0
                        EngineSpeed=4.5
                        scoreValue=-1
                        randApplex=round(random.randrange(display_width/4+10,display_width*3/4-applewidth-10))
                        gameLoop()
                if event.type == pygame.QUIT:
                    gameExit=True
                    
#Car Movement
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                gameExit = True
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE:
                    y_change=-EngineSpeed
                elif event.key == pygame.K_DOWN:
                    pass
                elif event.key == pygame.K_w:
                    y_change=-EngineSpeed
                elif event.key == pygame.K_s:
                    y_change=EngineSpeed
                elif event.key == pygame.K_f:
                    pause()
            if event.type==pygame.KEYUP:
                if event.key== pygame.K_SPACE:
                    y_change=EngineSpeed
                elif event.key== pygame.K_DOWN:
                    y_change=0
                elif event.key== pygame.K_w:
                    y_change=0
                elif event.key== pygame.K_s:
                    y_change=0


        total_y_change=total_y_change + y_change
    
        count=count + count/50
        
        barrier(display_width,display_height,origin_y,origin_x,box_x_pos,total_y_change,count)

        if (((origin_y - display_height/2 + display_width/4000)/(origin_x-box_x_pos)*(1999*display_width/4000-count) - display_width/2 + display_height/2)-total_y_change)+display_width/2000+2*count+200 > display_height:
            if ((origin_y - display_height/2 + display_width/4000)/(origin_x-box_x_pos)*(1999*display_width/4000-count) - display_width/2 + display_height/2)-total_y_change-200 < 0:
                count =  1 + countStarter
                scoreValue=scoreValue+1
                countStarter=countStarter*2
                total_y_change=0
                barrier(display_width,display_height,origin_y,origin_x,box_x_pos,total_y_change,count)
                EngineSpeed = EngineSpeed + acceleration
                score(scoreValue)
                
        count = count + count/50
        total_y_change=total_y_change + y_change
    
        if 1999*display_width/4000 - 2*count < 0:
            if 1999*display_width/4000 + count + display_width/2000 > display_width/2:
                if origin_y > display_height/2 - (display_height/2+total_y_change):
                    if origin_y < display_height/2 + (display_height/2-total_y_change):
                        pass
                    else:
                        gameOver = True
                else:
                    gameOver = True
                    



        #Resetting Screen   
        pygame.display.update()
        clock.tick(FPS)
    #Endgame
    pygame.quit()
    quit()

#Calling the Program
game_intro()

gameLoop()

