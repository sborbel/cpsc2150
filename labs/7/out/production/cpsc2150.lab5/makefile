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
        SetAbs.java

INT = MultiSetApp

DOUBLE = DoubleSetApp

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
runInt: $ classes
	$(JVM) $(INT)
runDouble: $ classes
	$(JVM) $(DOUBLE)

