import pygame

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
ground=(222,184,135)
stone_gray=(139,141,122)

sunrise=(254, 213, 113)
sunset=(253, 94, 83)
noon=(135,206,235)
#Setting Values
FPS=75
display_width = 800
display_height = 600


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

def getVert(x,y,width,height):
    Vert2x=width+x
    Vert3x=width+x
    Vert3y=y+height
    Vert4y=y+height
    Centerx=x+width/2
    Centery=y+height/2
    return [x,y],[Vert2x,y],[Vert3x,Vert3y],[x,Vert4y],[Centerx,Centery]

def MoveEntity(x,y,width,height,EntitySpeed):
    Vert1, Vert2, Vert3, Vert4, Center = getVert(x,y,width,height)
    
    slope1, b1=getLines(Vert1,Center)
    slope2, b2=getLines(Vert2,Center)

    Vert1y=slope1*(x-EntitySpeed)
    return [x-EntitySpeed,Vert1y]


    

def getLines(Vert,Center):
    slope=(Vert[1]-Center[1])/(Vert[0]-Center[0])
    b=slope*(Vert[0])+Vert[1]
    return slope, b
    
    
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
def message_to_screen(msg,color,y_displace=0,size="small",x_displace=0):
    textSurf, textRect= text_objects(msg,color, size)
    textRect.center= (display_width/2+x_displace),((display_height/2)+y_displace)
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
def snipemode():
    gameDisplay.fill(red)
def gameLoop():
    enemy_decrement_y = 0
    
    click=pygame.mouse.get_pressed()
    snipetoggle=False
    world_size=5400
    topleftx=0
    toplefty=0

    gameOver = False
    gameExit = False

        
    lead_y=display_height/2
    lead_x=display_width/2
    
    lead_x_change=0
    lead_y_change=0
    click=pygame.mouse.get_pressed()
    pygame.mouse.set_visible(False)

    EntitySpeed=1/5
    Vert1=[]
    Vert1.append(50)
    Vert1.append(50)
    Entity_height= 150
    Entity_width=125
    Forward=False
    Backward=False
    
    shoot=False
    circle_counter=0
    shootFrame_counter=0
    shoot_stagecount=10

    solHeight=0
    skyColors=[sunrise]
    skycolor1=True

    morning=True
    afternoon=False
    morn1=False
    aft1=False
    
    sunSpeed=2

    map_posx=int(display_height/10 - display_height/150)
    map_posy=int(display_height/10 - display_height/150)

    wallSize=0
# High Score Ending 
    while not gameExit:
        gameDisplay.fill(white)

        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    pygame.quit()
                    quit()
                if event.key == pygame.K_SPACE:
                    if snipetoggle==False:
                        snipetoggle=True
                    else:
                        snipetoggle=False

                if event.key == pygame.K_w:
                    Forward=True
                if event.key == pygame.K_s:
                    Backward=True
            if event.type == pygame.MOUSEBUTTONUP:
                if snipetoggle==True:
                    shoot=True
            if event.type == pygame.KEYUP:
                if event.key == pygame.K_w:
                    Forward=False
                if event.key == pygame.K_s:
                    Backward =False
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()   
        mouse_pos = pygame.mouse.get_pos()
        
        if shoot==False:
            topleftx=(mouse_pos[0]-display_width/2)+topleftx
            toplefty=(mouse_pos[1]-display_height/2)+toplefty


        Vert1=MoveEntity(Vert1[0]-topleftx,Vert1[1]-toplefty,Entity_width,Entity_height,1)


        
        pygame.mouse.set_pos([display_width/2,display_height/2])

        if Forward==True and snipetoggle==False and shoot==False:
            moveSpeed=1
            map_posy-=7/13
        elif Backward==True and snipetoggle==False and shoot==False:
            moveSpeed=-1
            map_posy+=7/13
        else:
            moveSpeed=0

        
        if morning==True:
            solHeight=solHeight-1/sunSpeed
        elif afternoon==True:
            solHeight=solHeight+1/sunSpeed
            
        if -25+display_height/2+solHeight >= -(world_size-display_height)/4 and morning==True:
            if skycolor1==True:
                change1=(noon[0]-sunrise[0])/((-25+display_height/2-toplefty+solHeight+(world_size-display_height)/4)*sunSpeed)
                change2=(noon[1]-sunrise[1])/((-25+display_height/2-toplefty+solHeight+(world_size-display_height)/4)*sunSpeed)
                change3=(noon[2]-sunrise[2])/((-25+display_height/2-toplefty+solHeight+(world_size-display_height)/4)*sunSpeed)
                tchange1=change1
                tchange2=change2
                tchange3=change3

                skycolor1=False
                
            if (change1+skyColors[0][0])> 256 or (change1+skyColors[0][0])<0:
                change1=0
            if (change2+skyColors[0][1])> 256 or (change2+skyColors[0][1])<0:
                change2=0
            if (change3+skyColors[0][2])> 256 or (change3+skyColors[0][2])<0:
                change3=0

            skyColors.insert(0,((change1+skyColors[0][0]),(change2+skyColors[0][1]),(change3+skyColors[0][2])))

            morn1=True
        else:
      
            morning=False
            afternoon=True

            if morn1==True:
                skycolor1=True
                morn1=False
                

            

        if -25+solHeight <= 0 and afternoon==True:
            if skycolor1==True:
                change1=-tchange1
                change2=-tchange2
                change3=-tchange3

                skycolor1=False
                
            if (change1+skyColors[0][0])> 256 or (change1+skyColors[0][0])<0:
                change1=0
            if (change2+skyColors[0][1])> 256 or (change2+skyColors[0][1])<0:
                change2=0
            if (change3+skyColors[0][2])> 256 or (change3+skyColors[0][2])<0:
                change3=0
            
            skyColors.insert(0,((change1+skyColors[0][0]),(change2+skyColors[0][1]),(change3+skyColors[0][2])))

            aft1=True
        else:
                    
            morning=True
            afternoon=False

            if aft1==True:
                skycolor1=True
                aft1=False

        if map_posy > 10 + enemy_decrement_y:
            enemy_decrement_y += 1/100

        wallSize=wallSize+moveSpeed 
        if map_posy>2:
            wallSize=wallSize+moveSpeed
        else:
            if Forward == True:
                wallSize = wallSize-moveSpeed
            map_posy=2
        if  map_posy>110:
            Backward = False

        pygame.draw.rect(gameDisplay,skyColors[0],(0,0,world_size,world_size))
        if morning==True:
            pygame.draw.rect(gameDisplay,light_orange,(display_width/2-topleftx-12.5,-37.5+display_height/2-toplefty+solHeight,75,75))
            pygame.draw.rect(gameDisplay,orange,(display_width/2-topleftx,-25+display_height/2-toplefty+solHeight,50,50))
        if afternoon==True:
            pygame.draw.rect(gameDisplay,light_orange,(display_width/2-topleftx-12.5+world_size/2,-37.5+display_height/2-toplefty+solHeight,75,75))
            pygame.draw.rect(gameDisplay,orange,(display_width/2-topleftx+world_size/2,-25+display_height/2-toplefty+solHeight,50,50))

        pygame.draw.rect(gameDisplay,ground,(-topleftx,2*display_height/3-toplefty+wallSize,world_size,world_size))   #The Ground
        
        pygame.draw.rect(gameDisplay,stone_gray,(-topleftx,-display_height/2-toplefty-2*wallSize,world_size,display_height/6+3*wallSize+display_height))   #The Wall
        pygame.draw.polygon(gameDisplay,green,((0,0),(5,7),(10,12)))
        
        pygame.draw.rect(gameDisplay,red,(display_width/2-topleftx-display_height/32-Entity_width/3,+2*display_height/3-toplefty-display_height/16-Entity_height/3+wallSize,2*Entity_width/3+display_height/16,2*Entity_height/3+display_height/8))     # The Entity
        pygame.draw.rect(gameDisplay,black,(3*display_width/4,2*display_height/3,display_width/6,display_height/3))

              
        if snipetoggle==True:
            pygame.draw.circle(gameDisplay,black,(int(display_width/2),int(display_height/2)),int(display_width*2/3),int(display_width*2/3-0.25*display_width))
            pygame.draw.line(gameDisplay,blue,(int(display_width/2),int(display_height/2)-0.25*display_width),((int(display_width/2),int(display_height/2+0.25*display_width))),1)
            pygame.draw.line(gameDisplay,blue,(int(display_width/2-0.25*display_width),int(display_height/2)),((int(display_width/2+0.25*display_width),int(display_height/2))),1)


        pygame.draw.circle(gameDisplay,ground,(int(display_height/10),int(display_height/10)),int(display_height/10-1),int(display_height/10-1))     # THe Map
        pygame.draw.circle(gameDisplay,black,(int(display_height/10),int(display_height/10)),int(display_height/10-1),1)        #The Map

        
        pygame.draw.rect(gameDisplay,blue,(map_posx,map_posy,display_height/75,display_height/75)) # Player in the Map
        pygame.draw.rect(gameDisplay,red,(map_posx,10 + enemy_decrement_y,display_height/75,display_height/75)) # Enemy in Map

        
        pygame.draw.line(gameDisplay,black,(-topleftx+world_size/4,display_height/2-toplefty),(-topleftx+world_size/4,display_height/2-toplefty+display_height/6),1)
        pygame.draw.line(gameDisplay,black,(-topleftx+2*world_size/4,display_height/2-toplefty),(-topleftx+2*world_size/4,display_height/2-toplefty+display_height/6),1)
        pygame.draw.line(gameDisplay,black,(-topleftx+3*world_size/4,display_height/2-toplefty),(-topleftx+3*world_size/4,display_height/2-toplefty+display_height/6),1)

        print(topleftx)

        
        if shoot==True:
            pygame.draw.circle(gameDisplay,gray,(int(display_width/2),int(display_height/2)),int(shoot_stagecount-circle_counter),int(shoot_stagecount-circle_counter))
            if shootFrame_counter%4==0:            
                circle_counter+=1
            shootFrame_counter+=1
            if shoot_stagecount-circle_counter==2:
                shoot=False
                circle_counter=0

                

        
        pygame.draw.rect(gameDisplay,black,(-topleftx+world_size,-1000,display_width,world_size))
        message_to_screen("Blind Spot", white,-toplefty,"large",-topleftx+world_size)
        
        Entity_height+=2*(EntitySpeed+moveSpeed)
        Entity_width+=(EntitySpeed+moveSpeed)

        
        if toplefty<=-(world_size-display_height)/4:
            toplefty=-(world_size-display_height)/4
        elif toplefty>=(world_size-display_height)/4 + display_height:
            toplefty=(world_size-display_height)/4 + display_height

        if topleftx>=world_size:
            topleftx=0
        if topleftx<=-1:
            topleftx=world_size-1


        
        #Resetting Screen
        pygame.display.update()
        clock.tick(FPS)
    #Endgame
    pygame.quit()
    quit()

#Calling the Program
game_intro()

gameLoop()


