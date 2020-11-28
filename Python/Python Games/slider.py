import turtle
import random
import math
import time
import winsound

#adjust these
sideHeight = 600
sideWidth  = 600
margin     =  50
sliderLength = 4
sliderSpeed = 12;
ballSpeed = 4;
ballSpeedLowest=ballSpeed;
totalHitsAndMisses = 2;

#variables that should stay the same
misses=0
hits=0
#Set up screen
turtle.setup(sideWidth, sideHeight)   
wn = turtle.Screen()
wn.title("Slide!")
wn.bgcolor("lightblue")
wn.tracer(1)


#draw border
borderPen = turtle.Turtle()
borderPen.hideturtle()
borderPen.penup()
borderPen.setposition(margin-(sideWidth/2), margin - (sideHeight/2))
borderPen.pendown()
borderPen.pensize(1)
for side in range(2):
   borderPen.forward(sideWidth- 2*margin)
   borderPen.left(90)
   borderPen.forward(sideHeight - 2*margin)
   borderPen.left(90)



# create the slider
sliderPen = turtle.Turtle()
sliderPen.penup()
sliderPen.setposition(0,margin -(sideHeight/2) + 20)
sliderPen.shape("square")
sliderPen.shapesize(0.1,sliderLength,0)
sliderPen.color("darkgreen")

# create the ball
ballPen = turtle.Turtle()
ballPen.penup()
ballPen.setposition(0,margin -(sideHeight/2) + 470)
ballPen.shape("circle")
ballPen.color("orange")
ballPen.setheading(random.randint(-170, -10))
ballPen.forward(ballSpeed);

def turnleft():
   if (sliderPen.xcor() - 40) > (margin - (sideWidth/2)): 
      sliderPen.backward(sliderSpeed)
   
def turnright():
   if (sliderPen.xcor() + 40) < ((sideWidth/2) - margin):
      sliderPen.forward(sliderSpeed)
   
def leave() :
   wn.bye()

   


#keyboard bindings
turtle.listen()
turtle.onkey(turnleft, "Left")
turtle.onkey(turnright, "Right")
turtle.onkey(leave, "q")


while True:
   ballPen.forward(ballSpeed)
   sliderPen.color("darkgreen")

   #collision with slider
   if (ballPen.ycor() < sliderPen.ycor() + 12) and (ballPen.ycor() > sliderPen.ycor()) :
      if (ballPen.xcor() < sliderPen.xcor() + 20*(sliderLength/2)) and (ballPen.xcor() > sliderPen.xcor() - 20*(sliderLength/2)):
         if ballPen.heading()>0 and ballPen.heading()<180:
            sliderPen.color("lightblue")
            time.sleep(0.15)
         else:
            ballPen.setheading(  - ballPen.heading() )
            hits=hits+1
            ballSpeed=ballSpeed+1
            borderPen.undo()
            scorestring = "Score: %d" %hits
            borderPen.penup()
            borderPen.setposition(-240,260)
            borderPen.write(scorestring,False, align ="left", font=("Arial",14, "normal"))
   
   #x cordinate bounce
   if ballPen.xcor() > (sideWidth/2)- margin- 10 -ballSpeed/2:
      ballPen.setheading( 180 - ballPen.heading() )
   if ballPen.xcor() < - (sideWidth/2)+ margin + 10 + ballSpeed/2:
      ballPen.setheading( 180 - ballPen.heading() )
   #y cordinate bounce
   if ballPen.ycor() > (sideHeight/2)- margin - 10 - ballSpeed/2:
      ballPen.setheading( - ballPen.heading() )
   if ballPen.ycor() < - (sideHeight/2)+ margin + 10 + ballSpeed/2:
      ballPen.setheading(  - ballPen.heading() )
      ballPen.setposition(ballPen.xcor() , - (sideHeight/2)+ margin + 10)
      misses=misses+1
      if ballSpeed >= ballSpeedLowest :
         ballSpeed = ballSpeed -1




   if hits+misses == totalHitsAndMisses:
      scorestring = "Final Score : %d" %hits
      borderPen.penup()
      borderPen.undo()
      borderPen.hideturtle()
      borderPen.setposition(-240,260)
      borderPen.write(scorestring,False, align ="left", font=("Arial",14, "normal"))
      time.sleep(10)
      wn.bye()





"""
##
##wn.bye()
##
### draw border
##mypen = turtle.Turtle()
##mypen.penup()
##mypen.setposition(-250,-250)
##mypen.pendown()
##mypen.pensize(3)
##
##for side in range(4):
##   mypen.forward(500)
##   mypen.left(90)
##mypen.hideturtle()
##
### create player turtle
##
##player = turtle.Turtle()
##player.color("green")
##player.shape("turtle")
##player.penup()
##player.speed(0)
##
##
###Create score variable
##score=0
###creat goalSSS
##
##goals = []
##for count in range(maxGoals):
##   goals.append(turtle.Turtle());
##   goals[count].color("red")
##   goals[count].shape("circle")
##   goals[count].penup()
##   goals[count].speed(0)
##   goals[count].setposition(random.randint(-240,240),random.randint(-240,240) )
##   
###create bombsSSS
##
##bombs = []
##for count in range(maxBombs):
##   bombs.append(turtle.Turtle());
##   bombs[count].color("black")
##   bombs[count].shape("triangle")
##   bombs[count].shapesize(1,1,1)
##   bombs[count].penup()
##   bombs[count].speed(0)
##   bombs[count].setposition(random.randint(-240,240),random.randint(-240,240) )
##   
### set speed variable
##
##speed = 1
### define funtions
##def turnleft():
##   player.left(30)
##   
##def turnright():
##   player.right(30)
##   
##def increasespeed() :
##   global speed
##   speed +=1
##   
##def decreasespeed() :
##   global speed
##   if speed > 0:
##      speed -= 1
##
##def boundaryBump(t1):
##   if int(t1.xcor()) > 235 or int(t1.xcor()) < -235 :
##      angleIncidence = t1.heading()
##      t1.setheading(180 - angleIncidence)
##   #Boundary Checking
##   if int(t1.ycor()) > 235 or int(t1.ycor()) < -235 :
##      angleIncidence = t1.heading()
##      t1.setheading( 360 - angleIncidence)
##
##def isCollision(t1,t2) :
##   d = math.sqrt(math.pow(t1.ycor()-t2.ycor(),2)+math.pow(t1.xcor()-t2.xcor(),2))
##   if d <15:
##      return True;
##   else:
##      return False;
##
##def leave():
##   wn.bye()
##   
###Set keyboard bindings
##turtle.listen()
##turtle.onkey(turnleft, "Left")
##turtle.onkey(turnright, "Right")
##turtle.onkey(increasespeed, "Up")
##turtle.onkey(decreasespeed, "Down")
##turtle.onkey(leave, "q")
##
##timePen = turtle.Turtle()
##timePen.penup()
##timePen.setposition(100,260)
##timePen.pendown()
##timePen.pensize(3)
##timePen.hideturtle();
##
##start = time.time()
##while True :
##   player.forward(speed)
##
##   boundaryBump(player)
##
##   for ind in range(maxGoals): 
##      boundaryBump(goals[ind])
##      #Collision Checking
##      if isCollision(goals[ind],player) :
##         goals[ind].setposition(random.randint(-240, 240),random.randint(-240, 240))
##         goals[ind].right(random.randint(0,360))
##         score +=1
##         player.shapesize(2,1.5,1.5)
##         time.sleep(0.2)
##         player.shapesize(1,1,1)
##         # sraw score on screen
##         mypen.undo()
##         mypen.penup()
##         mypen.hideturtle()
##         mypen.setposition(-240,260)
##         scorestring = "Score: %s" %score
##         mypen.write(scorestring,False, align ="left", font=("Arial",14, "normal"))
##      # move the goal
##      goals[ind].forward(ballSpeed)
##
##
##        
##   
##
##   for ind in range(maxBombs): 
##      boundaryBump(bombs[ind])
##        #Collision Checking
##      if isCollision(bombs[ind],player) :
##         bombs[ind].setposition(random.randint(-240, 240),random.randint(-240, 240))
##         bombs[ind].right(random.randint(0,360))
##         player.color("orange")
##         time.sleep(0.15);
##         player.color("green")
##         player.setposition(random.randint(-240, 240),random.randint(-240, 240))
##         player.right(random.randint(0,360))
##         score -=1
##         # Draw score on screen
##         mypen.undo()
##         mypen.penup()
##         mypen.hideturtle()
##         mypen.setposition(-240,260)
##         scorestring = "Score: %s" %score
##         mypen.write(scorestring,False, align ="left", font=("Arial",14, "normal"))
##      # move the goal
##      if score>0:
##         bombs[ind].forward(score)
##
##
##
##
##   now = int(time.time())
##   timeRemaining = int(maxTime - ( now - start))
##   timestring = "Time Left (s): %d" %timeRemaining
##   timePen.undo()
##   timePen.hideturtle();
##   timePen.write(timestring,False, align ="left", font=("Arial",14, "normal"))
##   if now - start > maxTime:
##       time.sleep(5);
##       leave()

"""








