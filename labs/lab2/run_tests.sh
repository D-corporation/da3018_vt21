cat test_data.txt | while read line;
do
    echo "$line" | java -jar Rpn.jar;
done