#include <stdlib.h>
#include "rect.h"

void main (void) {
    Rect* r1 = rect_new(30, 30, 40, 45);
    rect_print(r1);

    Rect* r2 = rect_new(20, 10, 20, 30);
    rect_drag(r2, 200, 1000);
    rect_print(r2);

    free(r1);
    free(r2);
}