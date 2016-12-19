all:
	g++ -I ~/work/googletest/googletest/include firsttest.cpp ~/work/googletest/googletest/make/gtest_main.a -lpthread -o firsttest && ./firsttest

fixture:
	g++ -I ~/work/googletest/googlemock/include -I ~/work/googletest/googletest/include withfixture.cpp ~/work/googletest/googlemock/make/gmock_main.a -lpthread -o withfixture && ./withfixture
