long size = new File(basedir,"sample1.txt").length()+new File(basedir,"sample2.txt").length();
File target = new File( basedir, "target/targetdir/cat.txt" )
assert target.isFile();
assert target.length() == size