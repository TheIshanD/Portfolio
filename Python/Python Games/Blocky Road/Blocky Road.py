import pygame
import random

pygame.init()

#Defining Colors
white=(255,255,255)
black=(0,0,0)
red=(255,0,0)
green=(0,155,0)
blue=(0,0,255)
orange=(255,140,0)
gray=(192,192,192)
yellow=(255,255,0)

#Set Values
FPS=30

display_width = 800
display_height = 600

CarSize=70
scoreValue=0
EngineSpeed=4.5

# Loading Images
carimg = pygame.image.load("car.png")
logimg = pygame.image.load("brick.png")

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
        message_to_screen("Welcome to Blocky Road!", orange, -150, "large")
        message_to_screen("The objective of the game is avoid obstacles",black, -80)
        message_to_screen("The more obstacles you avoid the faster you go",black, -40)
        message_to_screen("If you hit an obstacle you lose",black)
        message_to_screen("Press P to play, F to pause, or Q to quit",black, 130,"medium")
        message_to_screen("Coded by: Ishan Dasgupta on September 4th 2018", black,270)
        pygame.display.update()
        clock.tick(15)

        
# The Game Itself
def gameLoop():
    global logimg
    global FPS
    global scoreValue
    global gamemove
    global EngineSpeed
    appleheight=25
    applewidth=60
    
    
    gameOver = False
    gameExit = False
    
    lead_y=display_height/2
    lead_x=display_width/2
    
    lead_x_change=2
    lead_y_change=0

    randApplex=round(random.randrange(display_width/4,display_width*3/4-CarSize))

# High Score Ending 
    while not gameExit:
        gameDisplay.fill(white)
        while gameOver==True:
            if EngineSpeed>display_width/2:
                message_to_screen("YOU BEAT THE GAME", red, -125, size="large")
                message_to_screen("Xx400xX", orange,165,"large")
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
                        scoreValue=0
                        gamemove=0
                        EngineSpeed=4.5
                        randApplex=round(random.randrange(display_width/4+10,display_width*3/4-applewidth-10))
                        gameLoop()
                if event.type == pygame.QUIT:
                    gameExit=True
                    
#Car Movement
        for event in pygame.event.get():
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

            
        lead_x+=lead_x_change
        lead_y+=lead_y_change
        
        #Drawing Fixed Non-interactable Shapes
        
        lineMove=gamemove
        
        pygame.draw.rect(gameDisplay,gray,(display_width/4,0,display_width/2,display_height))
        pygame.draw.rect(gameDisplay,green,(0,0,display_width/4,display_height))
        pygame.draw.rect(gameDisplay,green,(display_width*3/4,0,display_width/4,display_height))
        pygame.draw.line(gameDisplay, yellow,(display_width/2,lineMove-600),(display_width/2,lineMove-500),20)
        pygame.draw.line(gameDisplay, yellow,(display_width/2,lineMove-400),(display_width/2,lineMove-300),20)
        pygame.draw.line(gameDisplay, yellow,(display_width/2,lineMove-200),(display_width/2,lineMove-100),20)
        pygame.draw.line(gameDisplay, yellow,(display_width/2,lineMove),(display_width/2,100+lineMove),20)
        pygame.draw.line(gameDisplay, yellow,(display_width/2,lineMove+200),(display_width/2,300+lineMove),20)
        pygame.draw.line(gameDisplay, yellow,(display_width/2,lineMove+400),(display_width/2,500+lineMove),20)
        pygame.draw.rect(gameDisplay,blue,(lead_x,lead_y,CarSize*3/5,CarSize))
        
        pygame.draw.rect(gameDisplay,red,[randApplex,gamemove-appleheight,applewidth,appleheight])
        gameDisplay.blit(carimg, (lead_x, lead_y))
        gameDisplay.blit(logimg, (randApplex,gamemove-appleheight))
        
        score(scoreValue)
        
        gamemove+=EngineSpeed
        
        if EngineSpeed>display_width/2:
            gameOver=True
            
    #Creating random obstacles
        brickorlog=int(random.randrange(0,2))
        if gamemove-appleheight>display_height:
            gamemove=0
            randApplex=round(random.randrange(display_width/4+10,display_width*3/4-applewidth-10))
            EngineSpeed+=1
            scoreValue+=1

            if brickorlog==1:
                logimg = pygame.image.load("brick.png")
            else:
                logimg = pygame.image.load("log.png")
                
    #Collision Detection
        if lead_x>randApplex and lead_x < randApplex+applewidth or lead_x + CarSize*3/5 > randApplex and lead_x + CarSize *3/5 < randApplex + applewidth:
            if lead_y>gamemove-appleheight and lead_y < gamemove or lead_y + CarSize > gamemove-appleheight and lead_y + CarSize < gamemove:
                gameOver=True
        
    #Boundrys

        if lead_y+CarSize*5/3>display_height:
            lead_y=display_height-CarSize*5/3
            
        if lead_y<display_height/2.5:
            lead_y=display_height/2.5
            
        if lead_x<display_width/4+10:
            lead_x=display_width/4+10
            
        if lead_x+CarSize*3/5>display_width*3/4-10:
            lead_x=display_width*3/4-CarSize*3/5-10
        
        
        #Resetting Screen   
        pygame.display.update()
        clock.tick(FPS)
    #Endgame
    pygame.quit()
    quit()

#Calling the Program
game_intro()

gameLoop()

