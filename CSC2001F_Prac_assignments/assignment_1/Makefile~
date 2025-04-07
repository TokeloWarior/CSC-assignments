.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
JAVAC=/usr/bin/javac
JAVA=/usr/bin/java

$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
	
CLASSES2=BinaryTreeNode.class CountryBSTApp.class VaccineBSTApp.class FileBSTApp.class VaccineArrayApp.class 

CLASSES=$(CLASSES2:%.class=$(BINDIR)/%.class)

default: $(CLASSES)

run1: $(CLASSES)
	$(JAVA) -cp $(BINDIR) VaccineArrayApp
	
run2: $(CLASSES)
	$(JAVA) -cp $(BINDIR) VaccineBSTApp
	
clean:
	rm $(BINDIR)/*.class