
n = int(input())

if (n >= 1 & n <= 10):
    p_array = []

    for i in range(n):

        p_array.append(int(input()))

    X = 0 

    for num in p_array:
        num = str(num)
        len_num = len(num)
        X += int(num[0:len_num - 1])**int(num[-1])

    print(X)