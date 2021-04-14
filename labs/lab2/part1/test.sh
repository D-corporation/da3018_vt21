cat test_data.txt | while read line 
do
   echo $line | java -jar lab2.jar
done