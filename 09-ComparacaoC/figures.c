#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);

typedef struct Figure {
    int x, y;
    Color line, bg;
    void (* print) (struct Figure*);
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

Rect* rect_new (int x, int y, int w, int h) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) rect_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////
typedef struct{
	Figure super;
	int x1, x2;
	int y1, y2;
	int w, h;
}Triangulo;

void Triangulo_print (Triangulo * this){
	Figure* sup = (Figure*)	this;
	printf("Coordenadas do triangulo: (%d, %d), (%d, %d), (%d, %d)\n", sup->x, sup->y, this->x1, this->y1, this->x2, this->y2);
	printf("A cor do triangulo em rgb: Line (%d, %d, %d) Back(%d, %d, %d)\n",
	sup->line.r, sup->line.g, sup->line.b, sup->bg.r, sup->bg.g, sup->bg.b);
		
}

Triangulo* triangulo_new (int x, int y, int w, int h, int r_line, int g_line, int b_line, int r_back, int g_back, int b_back) {
    Triangulo* this = malloc(sizeof(Triangulo));
    
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Triangulo_print;
    sup->x = x;
    sup->y = y;
    sup->line.r = r_line;
    sup->line.g = g_line;
	sup->line.b = b_line;
    sup->bg.r = r_back;
    sup->bg.g = g_back;
    sup->bg.b = b_back;
    this->x1 = x-w;
	this->y1 = y+w;
	this->x2 = x+w;
	this->y2 = y+w;
    this->w = w;
    this->h = h;
}
/////////////////////////////////////////////////////////////////////////////
typedef struct {
    Figure super;
    int w, h;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

Ellipse* ellipse_new (int x, int y, int w, int h) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Ellipse_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////
typedef struct {
    Figure super;
    int w, h;
    int xPoints[4];
    int yPoints[4];
} Losangulo;

//Apresentando um erro em adicionar cor de linha e back - Dúvida

void Losangulo_print (Losangulo* this) {
    Figure* sup = (Figure*) this;
    printf("Losangulo de tamanho (%d,%d) nas posicoes (%d,%d), (%d, %d), (%d, %d), (%d, %d).\n",
           this->w, this->h, this->xPoints[0], this->yPoints[0], this->xPoints[1], 
		   this->yPoints[1], this->xPoints[2], this->yPoints[2], this->xPoints[3],
		   this->yPoints[3]);
}

Losangulo* losangulo_new (int x, int y, int w, int h) {
    Losangulo* this = malloc(sizeof(Losangulo));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Losangulo_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    this->xPoints[0] = sup->x;
    this->xPoints[1] = sup->x + this->w / 2;
    this->xPoints[2] = sup->x;
    this->xPoints[3] = sup->x - this->w / 2;
    this->yPoints[0] = sup->y;
    this->yPoints[1] = sup->y + this->h / 2;
    this->yPoints[2] = sup->y + this->h;
    this->yPoints[3] = sup->y + this->h/2;
}


///////////////////////////////////////////////////////////////////////////////
void main (void) {
    Figure* figs[8] = {
        (Figure*) rect_new(10,10,100,100),
        (Figure*) ellipse_new(40,10,140,300),
        (Figure*) rect_new(10,10,100,100),
        (Figure*) ellipse_new(210,110,305,130), 
        (Figure*) triangulo_new(240, 120, 30, 40, 240, 200, 190, 155, 200, 220),
        (Figure*) triangulo_new(150, 300, 40, 40, 210, 150, 110, 70, 200, 120),
        (Figure*) losangulo_new(100, 200, 50, 70),
        (Figure*) losangulo_new(50, 100, 25, 30)
        
    };

    ///
	int i;
    for (i = 0; i < 8; i++) {
        figs[i]->print(figs[i]);
    }

    ///

    for (i=0; i<8; i++) {
        free(figs[i]);
    }
}