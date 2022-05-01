#include "rect.h"
#include <stdio.h>
#include <stdlib.h>

typedef struct Rect{
  int height, width, x, y;
}Rect;

Rect* rect_new(int width, int height, int x, int y){
  Rect* this = malloc(sizeof(Rect));
  this->width = width;
  this->height = height;
  this->x = x;
  this->y = y;
}

void rect_print(Rect* this){
  printf("Quadrilatero\n largura: %d \n altura: %d \n posicao: (%d,%d)",this->width,this->height,this->x,this->y); 
}

void rect_drag(Rect* this, int x, int u){
  this->x = x;
  this->y = y;
}