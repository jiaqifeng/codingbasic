#include <iostream>
#include "gtest/gtest.h"
//#include "gmock/gmock.h"

using namespace std;

class MyFixtureTest: public ::testing::Test {
public:
  virtual void SetUp() {
    cout << "Seting up fixture..." << endl;
  }

  virtual void TearDown() {
    cout << "Tearing down fixture ..." << endl;
  }
};

int add(int a, int b) {
  return a+b;
}

// first arg should be the fixture class name
TEST_F(MyFixtureTest, Add1) {
  EXPECT_EQ(3, add(1,2));
}

