JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		TestArraySet.java \
		TestListSet.java

ARR = TestArraySet

LST = TestListSet

default: classes

classes: $(CLASSES:.java=.class)

test:
	$(JC) *.java
testArr: $ classes
	$(JVM) $(ARR)
testList: $ classes
	$(JVM) $(LST)
clean:
	$(RM) *.class