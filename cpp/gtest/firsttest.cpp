#include "gtest/gtest.h"

int add(int a, int b) {
  return a+b;
}

TEST(AddTest, AddTest1)
{
  EXPECT_EQ(3, add(1,2));
  EXPECT_EQ(1, add(3,-2));
}

TEST(AddTest, AddTest2)
{
  EXPECT_EQ(3, add(1,1)) << "see it";
}
