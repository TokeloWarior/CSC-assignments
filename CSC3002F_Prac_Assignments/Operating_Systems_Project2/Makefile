#Source and Binary Directories
#SRCDIR=src
#$BINDIR=bin

#compile and java executable paths
JAVAC=/usr/bin/javac
JAVA=/usr/bin/java

#complie flags
JFLAGS=-g

#Compile java Source Files into Binary Classes
bin/%.class: src/%.java
	$(JAVAC) $(JFALGS) -d bin/ -cp src/ $<

#List of java classes to compile
CLASSES=bin/barScheduling/Barman.class bin/barScheduling/Patron.class bin/barScheduling/DrinkOrder.class bin/barScheduling/SchedulingSimulation.class

#CLASSES2=$(CLASSES:%.class=$(BINDIR)/**/%.class)

default: $(CLASSES)

run: $(CLASSES)
	$(JAVA) -cp bin/ barScheduling.SchedulingSimulation $(ARGS)
		
clean:
	rm -f bin/**/*.class