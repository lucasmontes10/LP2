typedef struct Rect Rect;
Rect* rect_new(int width, int height, int x, int y);
void rect_drag(Rect* this, int x, int y);
void rect_print(Rect* this);