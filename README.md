sbt assembly

java -jar target/scala-2.10/main-assembly-0.1.jar

mdfind -onlyin files -name pst

java -jar target/scala-2.10/main-assembly-0.1.jar files/1999.pst files/2000.pst files/2001.pst files/2002.pst files/2003.pst files/200401-06JanJun.pst files/200407-12JulDec.pst files/200501-06JanJun.pst files/200507-12JulDec.pst files/200601-06JanJun.pst files/200607-12JulDec.pst files/2003\ New/12\ December\ 2003\ Public\ 2.pst files/2003\ New/11\ November\ 2003\ Public\ 2.pst files/2003\ New/10\ October\ 2003\ Public\ 2.pst files/2003\ New/09\ September\ 2003\ Public\ 2.pst files/2003\ New/08\ August\ 2003\ Public\ 2.pst files/2003\ New/07\ July\ 2003\ Public\ 2.pst files/2003\ New/06\ June\ 2003\ Public\ 2.pst files/2003\ New/05\ May\ 2003\ Public\ 2.pst files/2003\ New/04\ April\ 2003\ Public\ 2.pst files/2003\ New/03\ March\ 2003\ Public\ 2.pst files/2003\ New/02\ February\ 2003\ Public\ 2.pst files/2002\ New/12\ December\ 2002\ Public.pst files/2003\ New/01\ January\ 2003\ Public\ 2.pst files/2002\ New/08\ August\ 2002\ Public.pst files/2002\ New/10\ October\ 2002\ Public.pst files/2002\ New/07\ July\ 2002\ Public.pst files/2002\ New/06\ June\ 2002\ Public.pst files/2002\ New/05\ May\ 2002\ Public.pst 


sort emails | uniq --count > unique
