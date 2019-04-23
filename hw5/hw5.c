#include <stdio.h>

static int x = 3;

#define p(t) (t+x)

void func1(void) {
    x = 30;
    printf("%d\n", p(5));
}

int main(void) {
    func1();
    printf("%d\n", p(5));
    return 0;
}
