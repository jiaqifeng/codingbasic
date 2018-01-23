#include <stdio.h>

class Abc {
public:
  Abc() {
    i_=0;
    printf("default constructor called on %d\n", i_);
  }

  Abc(int i) {
    i_=i;
    printf("int constructor called on %d\n", i_);
  }

  Abc(const Abc& b) : i_(b.i_) {
    printf("copy constructor called on %d\n", i_);
  }

  void operator=(const Abc& b) {
    printf("copy operator= called on %d\n", i_);
    i_=b.i_;
  }
  ~Abc() {
    printf("destructor called on %d\n", i_);
  }

private:
  int i_;
};

Abc get() {
  Abc a=Abc(2);
  return a;//Abc();
}

class Cc {
public:
  virtual void help() =0;
};

class CcD : public Cc {
public:
  void help() {
    printf("help of CcD\n");
    Cc::help();
  }
};

void Cc::help() {
  printf("help of Cc\n");
}

int main() {
  printf("hello\n");
  Abc a=get();
  Abc b=Abc();
  Abc c(5);

  int val=4;
  int val2=8;
  int * const d=&val;
  // d=&val2; error: assignment of read-only variable ‘d’
  *d=8; // ok

  CcD ccd;
  ccd.help();
}

