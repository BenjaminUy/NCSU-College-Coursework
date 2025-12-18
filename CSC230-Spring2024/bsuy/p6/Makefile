CFLAGS += -fprofile-arcs -ftest-coverage
LDLIBS += -lgcov

driver: driver.o value.o map.o input.o
	gcc driver.o value.o map.o input.o $(LDLIBS) -o driver

driver.o: driver.c input.h map.h value.h
	gcc -Wall -std=c99 $(CFLAGS) -g -c -o driver.o driver.c

value.o: value.c value.h
	gcc -Wall -std=c99 $(CFLAGS) -g -c -o value.o value.c

map.o: map.c map.h value.h
	gcc -Wall -std=c99 $(CFLAGS) -g -c -o map.o map.c

input.o: input.c input.h
	gcc -Wall -std=c99 $(CFLAGS) -g -c -o input.o input.c

doubleTest: doubleTest.o value.o
	gcc doubleTest.o value.o $(LDLIBS) -o doubleTest

doubleTest.o: doubleTest.c value.h
	gcc -Wall -std=c99 $(CFLAGS) -g -c -o doubleTest.o doubleTest.c

stringTest: stringTest.o value.o
	gcc stringTest.o value.o $(LDLIBS) -o stringTest

stringTest.o: stringTest.c value.h
	gcc -Wall -std=c99 $(CFLAGS) -g -c -o stringTest.o stringTest.c

mapTest: mapTest.o map.o value.o
	gcc mapTest.o map.o value.o $(LDLIBS) -o mapTest

mapTest.o: mapTest.c map.h value.h
	gcc -Wall -std=c99 $(CFLAGS) -g -c -o mapTest.o mapTest.c

clean:
	rm -f *.o
	rm -f driver
	rm -f *.gcda
	rm -f *.gcno
	rm -f *.gcov