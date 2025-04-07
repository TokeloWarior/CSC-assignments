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
CLASSES=bin/clubSimulation/Clubgoer.class bin/clubSimulation/ClubGrid.class bin/clubSimulation/ClubSimulation.class bin/clubSimulation/ClubView.class bin/clubSimulation/CounterDisplay.class bin/clubSimulation/GridBlock.class bin/clubSimulation/PeopleCounter.class bin/clubSimulation/PeopleLocation.class

#CLASSES2=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASSES)

run: $(CLASSES)
	$(JAVA) -cp bin/ clubSimulation.ClubSimulation $(ARGS)
		
clean:
	rm -f bin/**/*.class