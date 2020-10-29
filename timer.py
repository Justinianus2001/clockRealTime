#!/usr/bin/env python3
import math
import os
import time

try:
        import pygame
except ImportError:
        os.system('start cmd /c pip3 install pygame')
        import pygame

pygame.init()
pygame.display.set_caption('Timer')

screen = pygame.display.set_mode((500, 600))

GREY = (150, 150, 150)
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)

running = True
start = False
totalSecs = 0
totalTime = 0

font = pygame.font.SysFont('sans', 50)

textPlus = font.render('+', True, BLACK)
textMinus = font.render('-', True, BLACK)
textStart = font.render('Start', True, BLACK)
textReset = font.render('Reset', True, BLACK)

clock = pygame.time.Clock()

while running:
	clock.tick(60)
	screen.fill(GREY)

	sound = pygame.mixer.Sound('alarm.wav')

	mouseX, mouseY = pygame.mouse.get_pos()

	pygame.draw.rect(screen, BLACK, (95 , 45 , 60 , 60))
	pygame.draw.rect(screen, WHITE, (100, 50 , 50 , 50))

	pygame.draw.rect(screen, BLACK, (95 , 195, 60 , 60))
	pygame.draw.rect(screen, WHITE, (100, 200, 50 , 50))

	pygame.draw.rect(screen, BLACK, (195, 45 , 60 , 60))
	pygame.draw.rect(screen, WHITE, (200, 50 , 50 , 50))

	pygame.draw.rect(screen, BLACK, (195, 195, 60 , 60))
	pygame.draw.rect(screen, WHITE, (200, 200, 50 , 50))

	pygame.draw.rect(screen, BLACK, (295, 45 , 160, 60))
	pygame.draw.rect(screen, WHITE, (300, 50 , 150, 50))

	pygame.draw.rect(screen, BLACK, (295, 145, 160, 60))
	pygame.draw.rect(screen, WHITE, (300, 150, 150, 50))

	pygame.draw.rect(screen, BLACK, (55 , 525, 390, 40))
	pygame.draw.rect(screen, WHITE, (60 , 530, 380, 30))

	pygame.draw.circle(screen, BLACK, (250, 400), 100)
	pygame.draw.circle(screen, WHITE, (250, 400), 95 )
	pygame.draw.circle(screen, BLACK, (250, 400), 5  )

	screen.blit(textPlus, (115, 45))
	screen.blit(textPlus, (215, 45))

	screen.blit(textMinus, (120, 190))
	screen.blit(textMinus, (220, 190))

	screen.blit(textStart, (330, 50 ))
	screen.blit(textReset, (320, 150))

	for event in pygame.event.get():
		if event.type == pygame.QUIT:
			running = False
		elif event.type == pygame.MOUSEBUTTONDOWN:
			if event.button == 1:
				pygame.mixer.pause()
				if start == False:
					if 100 < mouseX < 150 and 50  < mouseY < 100:
						totalSecs += 60
					if 100 < mouseX < 150 and 200 < mouseY < 250:
						totalSecs -= 60
					if 200 < mouseX < 250 and 50  < mouseY < 100:
						totalSecs += 1
					if 200 < mouseX < 250 and 200 < mouseY < 250:
						totalSecs -= 1
					if 300 < mouseX < 450 and 50  < mouseY < 100:
						totalTime = totalSecs
						start = True
					if 300 < mouseX < 450 and 150 < mouseY < 200:
						totalSecs = 0

	if start:
		totalSecs -= 1
		time.sleep(1 / 1000)
		if totalSecs <= 0:
			pygame.mixer.Sound.play(sound)
			start = False

	if totalSecs < 0:
		totalSecs = 0

	if totalSecs == 0:
		totalTime = 0

	mins = totalSecs // 60
	secs = totalSecs %  60

	curTime = str(mins).zfill(2) + " : " + str(secs).zfill(2)
	textTime = font.render(curTime, True, BLACK)
	screen.blit(textTime, (110, 120))

	xSec = int(250 + 90 * math.sin(6 * secs * math.pi / 180))
	ySec = int(400 - 90 * math.cos(6 * secs * math.pi / 180))
	pygame.draw.line(screen, BLACK, (250, 400), (xSec, ySec), 2)

	xMin = int(250 + 60 * math.sin(6 * mins * math.pi / 180))
	yMin = int(400 - 60 * math.cos(6 * mins * math.pi / 180))
	pygame.draw.line(screen, RED  , (250, 400), (xMin, yMin), 3)

	if totalTime != 0:
		pygame.draw.rect(screen, RED, (60, 530, int(totalSecs / totalTime * 380), 30))

	pygame.display.flip()

pygame.quit()
