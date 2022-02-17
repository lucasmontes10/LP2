#include <stdio.h>
struct Ellipse{
	float eixoMaior, eixoMenor;
	float F1, F2; //Focos da elipse
	float x0, y0; //Centro da elipse
};
void print(struct Ellipse* e1){
	printf("Desenhando uma elipse de centro (%.2f, %.2f) com o eixo maior %.2f e eixo menor %.2f", e1->x0, e1->y0, e1->eixoMaior, e1->eixoMenor);
}
void main(){
	struct Ellipse e1;
	printf("Entre com o centro da elipse: x0 espaco y0)");
	scanf("%f%f", &e1.x0, &e1.y0);
	printf("Entre com o eixo maior: ");
	scanf("%f", &e1.eixoMaior);
	printf("Entre com o eixo menor:");
	scanf("%f", &e1.eixoMenor);
	print(&e1);
}