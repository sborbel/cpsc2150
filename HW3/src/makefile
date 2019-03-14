JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Connect4Game.java \
        GameBoard.java \
        IGameBoard.java

MAIN = Connect4Game

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
run: $ classes
	$(JVM) $(MAIN)

