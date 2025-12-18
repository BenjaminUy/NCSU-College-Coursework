vcalc: vcalc.o vigesimal.o check.o
	gcc vcalc.o vigesimal.o check.o -o vcalc

vcalc.o: vcalc.c vigesimal.h check.h
	gcc -Wall -std=c99 -g -c -o vcalc.o vcalc.c

vigesimal.o: vigesimal.c vigesimal.h check.h
	gcc -Wall -std=c99 -g -c -o vigesimal.o vigesimal.c

check.o: check.c check.h
	gcc -Wall -std=c99 -g -c -o check.o check.c

clean:
	rm -f *.o
	rm -f vcalc
	rm -f output.txt