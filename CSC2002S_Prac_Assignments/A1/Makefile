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

#List of java classes to compile along with the ForkJoinTask inner class
CLASSES=bin/MonteCarloMini/TerrainArea.class bin/MonteCarloMini/SearchParallel.class bin/MonteCarloMini/SearchParallel$$ForkJoinTask.class bin/MonteCarloMini/MonteCarloMinimizationParallel.class

#CLASSES2=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASSES)

run: $(CLASSES)
	$(JAVA) -cp bin/ MonteCarloMini.MonteCarloMinimizationParallel $(ARGS)
		
clean:
	rm -f bin/**/*.class