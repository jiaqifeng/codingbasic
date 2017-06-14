clean:
	rm *.class
	-rm -rf copyclass/

compile: clean
	javac *.java

PrintDefaultClassloader: compile
	java $@

moveclass:
	-@mkdir copyclass
	-mv Persion.class copyclass/

TestDiskClassLoader: compile moveclass
	java $@

TestDiffClassloaderSameClass: compile moveclass
	java -verbose $@
