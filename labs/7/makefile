JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        ArraySet.java \
        ISet.java \
        ListSet.java \
        MultiSetApp.java \
        DoubleSetApp.java \
        TestArraySet.java \
        TestListSet.java \
        SetAbs.java

INT = MultiSetApp

DOUBLE = DoubleSetApp

ARR = TestArraySet

LST = TestListSet

default: classes

classes: $(CLASSES:.java=.class)

test:
	javac /src/*.java
	javac /test/*.java
testArr:
	java /test/TestArraySet.class
testList: $ classes
	$(JVM) $(LST)
clean:
	$(RM) *.class
runInt: $ classes
	$(JVM) $(INT)
runDouble: $ classes
	$(JVM) $(DOUBLE)

