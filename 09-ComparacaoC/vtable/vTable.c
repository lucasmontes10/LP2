#include <stdio.h>
#include <stdlib.h>
#include<math.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);
typedef float (* Figure_Area) (struct Figure*);
typedef void (* Figure_Rotation) (struct Figure*);
typedef float (* Figure_Perimetro) (struct Figure*);

typedef struct{
	void (* print) (struct Figure*);
	float (* area) (struct Figure*);
	void (* rotation) (struct Figure*);
	float (* perimetro) (struct Figure*);
} Figure_vtable;

typedef struct Figure {
    int x, y;
    Color line, bg;
    Figure_vtable* vtable;
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
    printf("A cor do retangulo em rgb: Line (%d, %d, %d) Back(%d, %d, %d)\n",
	sup->line.r, sup->line.g, sup->line.b, sup->bg.r, sup->bg.g, sup->bg.b);
}

float rect_area(Rect* this){
	Figure* sup = (Figure*) this;
	float area = (float) this->w * this->h;
	return area;
}

float rect_perimetro(Rect* this){
	Figure* sup = (Figure*) this;
	float perimetro = (float) (2 * this->w) + (2 * this->h);
	return perimetro;
}

void rect_rotation(Rect* this){
	Figure* sup = (Figure*) this;
	int aux;
	
	printf("Atuais coordenadas do canto superior esquerdo: (%d, %d) e com w = %d e h = %d", sup->x, sup->y, this->w, this->h);
	
	aux = sup->y;
	sup->x = -sup->y;
	sup->y = aux;
	
	aux = this->h;
	this->h = this->w;
	this->w = aux;
	
	printf("Apos a rotacao: (%d, %d) e com w = %d e h = %d", sup->x, sup->y, this->w, this->h);
}

Figure_vtable rect_vtable ={
	(Figure_Print) rect_print,
	(Figure_Area) rect_area,
	(Figure_Perimetro) rect_perimetro,
	(Figure_Rotation) rect_rotation
};

Rect* rect_new (int x, int y, int w, int h, int r_line, int g_line, int b_line, int r_back, int g_back, int b_back ) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->vtable = &rect_vtable;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    sup->line.r = r_line;
    sup->line.g = g_line;
	sup->line.b = b_line;
    sup->bg.r = r_back;
    sup->bg.g = g_back;
    sup->bg.b = b_back;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct{
	Figure super;
	int x1, x2;
	int y1, y2;
	int w, h;
}Triangulo;

void triangulo_print (Triangulo * this){
	Figure* sup = (Figure*)	this;
	printf("Coordenadas do triangulo: (%d, %d), (%d, %d), (%d, %d)\n", sup->x, sup->y, this->x1, this->y1, this->x2, this->y2);
	printf("A cor do triangulo em rgb: Line (%d, %d, %d) Back(%d, %d, %d)\n",
	sup->line.r, sup->line.g, sup->line.b, sup->bg.r, sup->bg.g, sup->bg.b);
		
}

float triangulo_area(Triangulo * this){
	Figure* sup = (Figure*) this;
	float area = (this->w * this->h) / 2;
	return area;
}

float triangulo_perimetro(Triangulo * this){
	Figure* sup  = (Figure*) this;
	float l1 = (float) sqrt( pow((sup->x - this->x1), 2) + pow((sup->y - this->y1), 2));
	float l2 = (float) sqrt( pow((this->x1 - this->x2), 2) + pow((this->y1 - this->y2), 2));
	float l3 = (float) sqrt( pow((sup->x - this->x2), 2) + pow((sup->y - this->y2), 2));
	return l1 + l2 + l3;
}

void triangulo_rotation(Triangulo* this){
	Figure* sup = (Figure*) this;
	int aux;
	
	printf("Atuais coordenadas do canto superior esquerdo: (%d, %d) e com w = %d e h = %d", sup->x, sup->y, this->w, this->h);
	
	aux = sup->y;
	sup->x = -sup->y;
	sup->y = aux;
	
	aux = this->h;
	this->h = this->w;
	this->w = aux;
	
	printf("Apos a rotacao: (%d, %d) e com w = %d e h = %d", sup->x, sup->y, this->w, this->h);
}

Figure_vtable triangulo_vtable ={
	(Figure_Print) triangulo_print,
	(Figure_Area) triangulo_area,
	(Figure_Perimetro) triangulo_perimetro,
	(Figure_Rotation) triangulo_rotation
};

Triangulo* triangulo_new (int x, int y, int w, int h, int r_line, int g_line, int b_line, int r_back, int g_back, int b_back) {
    Triangulo* this = malloc(sizeof(Triangulo));
    Figure* sup = (Figure*) this;
    sup->vtable = &triangulo_vtable;
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
    int a, b;
} Ellipse;

void ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

float ellipse_area (Ellipse* this){
	//Formula da area da Ellipse : a * b * pi
	return this->a * this->b * 3.14;
}

float ellipse_perimetro(Ellipse* this){
	float c = pow(this->a, 2) - pow(this->b, 2);
	float e = c / this->a;
	float termo1 = pow(e, 2);
	float termo2 = pow(e, 4);
	float termo3 = pow(e, 6);
	float perimetro = 3.14 * (25 * (2 - (termo1 / 2) - 3 * (termo2 / 32) - 5 * (termo3 / 128)));
	return perimetro;
}

void ellipse_rotation(Ellipse* this){
	Figure* sup = (Figure*) this;
	int aux;
	
	printf("Atuais coordenadas do centro: (%d, %d) e com w = %d e h = %d", sup->x, sup->y, this->w, this->h);
	
	aux = sup->y;
	sup->x = -sup->y;
	sup->y = aux;
	
	aux = this->h;
	this->h = this->w;
	this->w = aux;
	
	printf("Apos a rotacao: (%d, %d) e com w = %d e h = %d", sup->x, sup->y, this->w, this->h);
}

Figure_vtable ellipse_vtable ={
	(Figure_Print) ellipse_print,
	(Figure_Area) ellipse_area,
	(Figure_Perimetro) ellipse_perimetro,
	(Figure_Rotation) ellipse_rotation
};

Ellipse* ellipse_new (int x, int y, int w, int h, int a, int b) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->vtable = &ellipse_vtable;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    this->a = a;
    this->b = b;
}

///////////////////////////////////////////////////////////////////////////////
typedef struct {
    Figure super;
    int w, h;
    int xPoints[4];
    int yPoints[4];
} Losangulo;

//Apresentando um erro em adicionar cor de linha e back - Dúvida

void losangulo_print (Losangulo* this) {
    Figure* sup = (Figure*) this;
    printf("Losangulo de tamanho (%d,%d) nas posicoes (%d,%d), (%d, %d), (%d, %d), (%d, %d).\n",
           this->w, this->h, this->xPoints[0], this->yPoints[0], this->xPoints[1], 
		   this->yPoints[1], this->xPoints[2], this->yPoints[2], this->xPoints[3],
		   this->yPoints[3]);
}

float losangulo_area (Losangulo* this){
	Figure* sup = (Figure*) this;
	return (this->h + this->w) / 2;
}

float losangulo_perimetro (Losangulo* this){
	float l1 = sqrt(pow(this->h / 2, 2) + pow(this->w / 2, 2));
	return l1 * 4;  
}

void losangulo_rotation(Losangulo* this){
	Figure* sup = (Figure*) this;
	int aux;
	
	printf("Atuais coordenadas do ponto: (%d, %d) e com w = %d e h = %d", sup->x, sup->y, this->w, this->h);
	
	aux = sup->y;
	sup->x = -sup->y;
	sup->y = aux;
	
	aux = this->h;
	this->h = this->w;
	this->w = aux;
	
	printf("Apos a rotacao: (%d, %d) e com w = %d e h = %d", sup->x, sup->y, this->w, this->h);
}

Figure_vtable losangulo_vtable ={
	(Figure_Print) losangulo_print,
	(Figure_Area) losangulo_area,
	(Figure_Perimetro) losangulo_perimetro,
	(Figure_Rotation) losangulo_rotation
};

Losangulo* losangulo_new (int x, int y, int w, int h) {
    Losangulo* this = malloc(sizeof(Losangulo));
    Figure* sup = (Figure*) this;
    sup->vtable = &losangulo_vtable;
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
        (Figure*) rect_new(10,10,100,100, 240, 140, 52, 120, 24, 65),
        (Figure*) ellipse_new(40,10,140,300, 20, 30),
        (Figure*) rect_new(10,10,100,100, 220, 235,200, 120, 152, 155),
        (Figure*) ellipse_new(210,110,305,130, 50, 80), 
        (Figure*) triangulo_new(240, 120, 30, 40, 240, 200, 190, 155, 200, 220),
        (Figure*) triangulo_new(150, 300, 40, 40, 210, 150, 110, 70, 200, 120),
        (Figure*) losangulo_new(100, 200, 50, 70),
        (Figure*) losangulo_new(50, 100, 25, 30)
        
    };

    ///
	int i;
    for (i = 0; i < 8; i++) {
        figs[i]->vtable->print(figs[i]);
        printf("O perimetro e igual a: %.2f  \n", figs[i]->vtable->perimetro(figs[i]));
        printf("A area e igual a: %.2f\n", figs[i]->vtable->area(figs[i]));
        figs[i]->vtable->rotation(figs[i]);
        printf("\n");
    }

    ///

    for (i=0; i<8; i++) {
        free(figs[i]);
    }
}