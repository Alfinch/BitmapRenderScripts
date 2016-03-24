#pragma version(1)
#pragma rs java_package_name(com.foundbite.rs)

rs_allocation input;
int left;
int top;

uchar4 __attribute__ ((kernel)) crop (uchar4 in, uint32_t x, uint32_t y)
{
    uint32_t inX  = left + x;
    uint32_t inY = top + y;
    return rsGetElementAt_uchar4(input, inX, inY);
}