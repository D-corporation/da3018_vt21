# exercise 1

echo "How many lines do the two files contain?"

wc -l *.txt

#exercise 2
echo ""
echo "In how many pairs do we find the identifier “fp.2.Luci_02H12.ctg.ctg7180000019650”? "

grep -c "fp.2.Luci_02H12.ctg.ctg7180000019650" *.txt