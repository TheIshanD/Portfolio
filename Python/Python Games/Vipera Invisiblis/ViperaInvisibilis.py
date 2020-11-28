import pygame
import random

pygame.init()

white=(255,255,255)
black=(0,0,0)
red=(255,0,0)
green=(0,155,0)
blue=(0,0,255)

GrowthRate=1
img = pygame.image.load("snakehead2.png")
appleimg = pygame.image.load("mousey2.png")

block_size=20
applethickness = 30
direction="right"

display_width = 800
display_height = 600

smallfont = pygame.font.SysFont("comicsansms", 20)
medfont = pygame.font.SysFont("comicsansms", 40)
largefont = pygame.font.SysFont("comicsansms", 55)

gameDisplay = pygame.display.set_mode((display_width,display_height))
pygame.display.set_caption("ViperaInvisibilis")

clock = pygame.time.Clock()


    
def text_objects(text,color,size):
    if size=="small":
        textSurface=smallfont.render(text, True,color)
    elif size=="medium":
        textSurface=medfont.render(text, True,color)
    elif size=="large":
        textSurface=largefont.render(text, True,color)
        
    return textSurface, textSurface.get_rect()
    
def message_to_screen(msg,color,y_displace=0,size="small"):
    textSurf, textRect= text_objects(msg,color, size)
    textRect.center= (display_width/2),((display_height/2)+y_displace)
    gameDisplay.blit(textSurf, textRect)
def randAppleGen():    
    randApplex=round(random.randrange(0,display_width-applethickness))
    randAppley=round(random.randrange(0,display_height-applethickness))
    return randApplex, randAppley
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
def speed(speed):
    text = smallfont.render("Speed: "+str(speed), True,black)
    gameDisplay.blit(text,[display_width-200,0])
def score(score):
    text = smallfont.render("Score: "+str(score), True,black)
    gameDisplay.blit(text,[0,0])
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
        message_to_screen("Welcome to Vipera Invisibilis!", green, -150, "large")
        message_to_screen("The objective of the game is to eat mice",black, -80)
        message_to_screen("The more mice you eat the longer you get",black, -40)
        message_to_screen("If you run into yourself or the edges you die",black)
        message_to_screen("Press P to play, F to pause, or Q to quit",black, 130)
        message_to_screen("To speed up or slow down press I and O respectivly",black, 170)
        message_to_screen("Coded by: Ishan Dasgupta on September 4th 2018", black,270,"small")
        pygame.display.update()
        clock.tick(15)

        
def snake(block_size, snakelist):
    global snakeLength
    if direction=="right":
        head = pygame.transform.rotate(img,270)
    if direction=="left":
        head = pygame.transform.rotate(img,90)
    if direction=="up":
        head = pygame.transform.rotate(img,0)
    if direction=="down":
        head = pygame.transform.rotate(img,180)
    gameDisplay.blit(head, (snakelist[-1][0],snakelist[-1][1]))
    for XnY in snakelist[:-1]:
        if snakeLength%2==0:
            pygame.draw.rect(gameDisplay,green, [XnY[0],XnY[1],block_size,block_size])
        else:
            pygame.draw.rect(gameDisplay,white, [XnY[0],XnY[1],block_size,block_size])

def gameLoop():
    global direction
    
    FPS=15
    
    gameOver = False
    gameExit = False
    
    lead_y=display_height/2
    lead_x=display_width/2
    
    lead_x_change=10
    lead_y_change=0

    global snakeLength
    snakeList =[]
    
    
    
    snakeLength=1
    randApplex,randAppley = randAppleGen()
    while not gameExit:
        gameDisplay.fill(white)
        while gameOver==True:
            message_to_screen("Game Over", red, -125, size="large")
            message_to_screen(" Press P to play again or Q to quit.",black,size="medium")
            message_to_screen("Your Score:", blue,125)
            message_to_screen(str(snakeLength-1), green,165)
            message_to_screen("Coded by: Ishan Dasgupta on September 4th 2018", black,270,"small")
            pygame.display.update()

            for event in pygame.event.get():
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_q:
                        gameExit=True
                        gameOver=False
                    if event.key == pygame.K_p:
                        gameLoop()
                if event.type == pygame.QUIT:
                    gameExit=True
                    gameOver=False
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                gameExit = True
                gameOver=False
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    lead_x_change=-block_size
                    lead_y_change=0
                    direction = "left"
                elif event.key == pygame.K_RIGHT:
                    lead_x_change=block_size
                    lead_y_change=0
                    direction = "right"
                elif event.key == pygame.K_UP:
                    lead_y_change=-block_size
                    lead_x_change=0
                    direction = "up"
                elif event.key == pygame.K_DOWN:
                    lead_y_change=block_size
                    lead_x_change=0
                    direction = "down"
                elif event.key == pygame.K_a:
                    lead_x_change=-block_size
                    lead_y_change=0
                    direction = "left"
                elif event.key == pygame.K_d:
                    lead_x_change=block_size
                    lead_y_change=0
                    direction = "right"
                elif event.key == pygame.K_w:
                    lead_y_change=-block_size
                    lead_x_change=0
                    direction = "up"
                elif event.key == pygame.K_s:
                    lead_y_change=block_size
                    lead_x_change=0
                    direction = "down"
                elif event.key == pygame.K_f:
                    pause()
                elif event.key == pygame.K_i:
                    FPS+=1
                elif event.key == pygame.K_o:
                    if FPS>1:
                        FPS-=1
                    
                
        if lead_y >= display_height or lead_y < 0 or lead_x>=display_width or lead_x<0:
            gameOver=True

        lead_x+=lead_x_change
        lead_y+=lead_y_change

        
        gameDisplay.fill(white)
        
        
        snakeHead=[]
        snakeHead.append(lead_x)
        snakeHead.append(lead_y)
        snakeList.append(snakeHead)

        gameDisplay.blit(appleimg, (randApplex, randAppley))
        
        if len(snakeList) > snakeLength:
            del snakeList[0]
        for eachSegment in snakeList[:-1]:
            if eachSegment == snakeHead:
                gameOver= True
            

        score(snakeLength-1)
        speed(FPS)
        snake(block_size,snakeList)
        
        pygame.display.update()

        if lead_x>randApplex and lead_x < randApplex+applethickness or lead_x + block_size > randApplex and lead_x + block_size < randApplex + applethickness:
            if lead_y>randAppley and lead_y < randAppley+applethickness or lead_y + block_size > randAppley and lead_y + block_size < randAppley + applethickness:        
                randApplex,randAppley = randAppleGen()
                snakeLength+=GrowthRate
            elif lead_y+block_size > randAppley and lead_y+block_size<randAppley+applethickness:
                randApplex,randAppley = randAppleGen()
                snakeLength+=GrowthRate
        clock.tick(FPS)
    pygame.quit()
    quit()

game_intro()
gameLoop()
