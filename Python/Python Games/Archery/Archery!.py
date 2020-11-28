# Parts of the code such as the message to screen function are from the youtube hannel: Sentdex
import pygame
from random import randint
from math import hypot
from time import time
pygame.init()
totalTime = 30
song = pygame.mixer.Sound('ArcheryMusic.wav')
clock = pygame.time.Clock()
song.play(-1)
#Defining Colors
white=(255,255,255)
black=(0,0,0)
red=(255,0,0)
green=(0,155,0)
blue=(100,150,255)
orange=(255,140,0)
gray=(192,192,192)
yellow=(255,255,0)
brown = (165,42,42)
silver = (192,192,192)
dark_blue = (0,0,255)
#Set Values
FPS=30
display_width = 1400
display_height = 700
scoreValue=0
targetDimens=[]
# Loading Images
#Setting Screen Size
gameDisplay = pygame.display.set_mode((display_width,display_height))
#Naming the Program
pygame.display.set_caption("Target Practice")
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
def pause(timeLeft):
global start
global totalTime
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
start = time()
totalTime = timeLeft
pause=False
elif event.key==pygame.K_q:
pygame.quit()
quit()
clock.tick(0)
#Writing the Score
def score(score,addScore = 0):
score += addScore
text = largefont.render("Score: "+str((score)), True, silver)
gameDisplay.blit(text,[0,0])
def pointsToAdd(targetDimens, landMark, topLeftX, topLeftY):
x1 = int(targetDimens[0]-topLeftX)
y1 = int(display_height/2+100-topLeftY)
x2 = landMark[0]
y2 = landMark[1]
dist = hypot(x1-x2, y1-y2)
if(dist < int(targetDimens[2]*1/12)):
points = 10
elif(dist < int(targetDimens[2]*1/3)):
points = 5
elif(dist < int(targetDimens[2]*2/3)):
points = 2
elif(dist < targetDimens[2]):
points = 1
else:
points = 0
return points
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
message_to_screen("Target Practice", orange, -200, "large")
message_to_screen("The objective of the game is shoot the target and rack up points",black,
-80)
message_to_screen("Press Space to enter and exit scope mode",black, -40)
message_to_screen("In scope mode, click the mouse to shoot ",black)
message_to_screen("Move the mouse to look around",black, 40)
message_to_screen("Press P to start, F to pause, or Q to quit!",black, 170,"medium")
pygame.display.update()
clock.tick(15)
# The Game Itself
def gameLoop():
global FPS
global scoreValue
global targetDimens
global start
global totalTime
totalTime = 30
timeLeft = 30
landMark = []
snipeToggle = False
gameOver = False
gameExit = False
getNewTarget=True
dist = 0
arrowSize = 6
arrowFrameCount = 0
topLeftX=0
topLeftY=0
bulletGrav = 0
topCapY=100
botCapY=0
topCapX=400
botCapX=-400
shooting=False
scoreValue = 0
start = time()
pygame.mouse.set_pos([display_width/2,display_height/2])
pygame.mouse.set_visible(False)
# High Score Ending
while not gameExit:
gameDisplay.fill(white)
while gameOver==True:
pygame.mouse.set_visible(True)
message_to_screen("Game Over", red, -150, size="large")
message_to_screen(" Press P to play again or Q to quit.",black,size="medium")
message_to_screen("Your Score:", blue,125, size = "large")
message_to_screen(str(scoreValue), green,250, size = "large")
pygame.display.update()
# Pause and Quit Functionality
for event in pygame.event.get():
if event.type == pygame.KEYDOWN:
if event.key == pygame.K_q:
gameOver=False
gameExit=True
if event.key == pygame.K_p:
gameOver=False
gameLoop()
if event.type == pygame.QUIT:
gameExit=True
#Key Handling
for event in pygame.event.get():
if event.type == pygame.QUIT:
gameExit = True
if event.type == pygame.KEYDOWN:
if event.key == pygame.K_LEFT:
getNewTarget=True
if event.key == pygame.K_q:
pygame.quit()
quit()
elif event.key == pygame.K_SPACE:
if(shooting==False):
snipeToggle=(not snipeToggle)
elif event.key == pygame.K_f:
pause(timeLeft)
if event.type==pygame.MOUSEBUTTONDOWN:
if(snipeToggle):
shooting=True
arrowSize=10
mouse_pos = pygame.mouse.get_pos()
if(not snipeToggle):
topLeftX=(mouse_pos[0]-display_width/2)+topLeftX
topLeftY=(mouse_pos[1]-display_height/2)+topLeftY
if(topLeftY > topCapY):
topLeftY = topCapY
elif(topLeftY < botCapY):
topLeftY = botCapY
if(topLeftX > topCapX):
topLeftX=topCapX
elif(topLeftX < botCapX):
topLeftX = botCapX
pygame.mouse.set_pos([display_width/2,display_height/2])
if(getNewTarget):
dist = 100+10*randint(-7,7)
targetDimens=[int(display_width/2)+randint(-400,400),int(display_height/2)+randint(-
100,100),dist]
getNewTarget=False
dist = int(200/dist)
if(arrowSize != -1):
arrowFrameCount+=1
if(arrowFrameCount%2):
arrowSize-=1
if(arrowSize == -1):
landMark = [int(display_width/2) ,int(display_height/2 + bulletGrav)]
scoreValue+=pointsToAdd(targetDimens, landMark, topLeftX, topLeftY)
arrowFrameCount = 0
getNewTarget=True
topLeftX=0
topLeftY=0
snipeToggle=False
shooting=False
bulletGrav = 0
bulletGrav+=dist*1.5
#drawing shapes
pygame.draw.rect(gameDisplay, blue, (0,0,1600,1600)) #sky
pygame.draw.rect(gameDisplay, green, (0,int(display_height/2+100-topLeftY),1600,1200))
#grass
pygame.draw.circle(gameDisplay, red, (int(targetDimens[0]-
topLeftX),int(display_height/2+100-topLeftY)),targetDimens[2]) #target
pygame.draw.circle(gameDisplay, dark_blue, (int(targetDimens[0]-
topLeftX),int(display_height/2+100-topLeftY)),int(targetDimens[2]*2/3)) #target
pygame.draw.circle(gameDisplay, yellow, (int(targetDimens[0]-
topLeftX),int(display_height/2+100-topLeftY)),int(targetDimens[2]*1/3)) #target
pygame.draw.circle(gameDisplay, black, (int(targetDimens[0]-
topLeftX),int(display_height/2+100-topLeftY)),int(targetDimens[2]*1/12)) #target
if(snipeToggle):
pygame.draw.line(gameDisplay, 
brown,(display_width/2,0),(display_width/2,display_height))
pygame.draw.line(gameDisplay,
brown,(0,display_height/2),(display_width,display_height/2))
pygame.draw.circle(gameDisplay,black,(int(display_width/2),int(display_height/2)),int(d
isplay_width*2/3),int(display_height/2+0.20*display_width))
if(shooting):
pygame.draw.circle(gameDisplay, silver,(int(display_width/2) ,int(display_height/2 +
bulletGrav)),int(5*arrowSize+5))#bullet\
now = time()
timeLeft= totalTime - round(now-start)
textColor = silver
if((timeLeft) <= 10):
if(timeLeft<=0):
gameOver=True
elif(timeLeft%2 ==0):
textColor = red
text = largefont.render("Time Left: "+str(timeLeft), True, textColor)
gameDisplay.blit(text,[900,0])
#Resetting Screen
score(scoreValue)
pygame.display.update()
clock.tick(FPS)
#Endgame
pygame.quit()
quit()
#Calling the Program
game_intro()
gameLoop()
