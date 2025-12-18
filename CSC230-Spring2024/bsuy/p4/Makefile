vinyl: vinyl.o inventory.o input.o
	gcc vinyl.o inventory.o input.o -o vinyl

vinyl.o: vinyl.c inventory.h input.h
	gcc -Wall -std=c99 -g -c -o vinyl.o vinyl.c

inventory.o: inventory.c inventory.h input.h
	gcc -Wall -std=c99 -g -c -o inventory.o inventory.c

input.o: input.c input.h
	gcc -Wall -std=c99 -g -c -o input.o input.c

clean:
	rm -f *.o
	rm -f vinyl
	rm -f output.txt
