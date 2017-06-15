File touchFile = new File( basedir, "target/targetdir/result.txt" );

assert touchFile.isFile()
assert new File( basedir, "target/targetdir/pom.xml" ).isFile();