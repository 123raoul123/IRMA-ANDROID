#include "relic.h"

typedef struct {
  int bn_size;
  int bn_digs;
  int fp_prime;
  int fp_digit;
  int fp_digs;
  int digit;
  int integer;
  int bn_st_size;
  int fp12_size;
  int ep_st_size;
  int ep2_st_size;
} relic_sizes;

static relic_sizes sizes = {
  BN_SIZE,
  BN_DIGS,
  FP_PRIME,
  FP_DIGIT,
  FP_DIGS,
  DIGIT,
  sizeof(int),
  sizeof(bn_st),
  12*sizeof(fp_st),
  sizeof(ep_st),
  sizeof(ep2_st)
};

relic_sizes get_relic_sizes() {
  return sizes;
}