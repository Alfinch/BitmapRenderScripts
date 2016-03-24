#pragma version(1)
#pragma rs java_package_name(com.foundbite.rs)

rs_allocation input;
int inWidth;
int inHeight;

uchar4 __attribute__ ((kernel)) rotate_90_cw (uchar4 in, uint32_t x, uint32_t y)
{
    uint32_t inX = y;
    uint32_t inY = inHeight - 1 - x;
    return rsGetElementAt_uchar4(input, inX, inY);
}

uchar4 __attribute__ ((kernel)) rotate_180_cw (uchar4 in, uint32_t x, uint32_t y)
{
    uint32_t inX = inWidth - 1 - x;
    uint32_t inY = inHeight - 1 - y;
    return rsGetElementAt_uchar4(input, inX, inY);
}

uchar4 __attribute__ ((kernel)) rotate_270_cw (uchar4 in, uint32_t x, uint32_t y)
{
    uint32_t inX  = inWidth - 1 - y;
    uint32_t inY = x;
    return rsGetElementAt_uchar4(input, inX, inY);
}