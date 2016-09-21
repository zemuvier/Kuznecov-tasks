import time


def distance(a, b):
    n, m = len(a), len(b)
    if n > m:
        # Make sure n <= m
        a, b = b, a
        n, m = m, n
    current_row = range(n + 1)  # Keep current and previous row, not entire
    # matrix
    for i in range(1, m + 1):
        previous_row, current_row = current_row, [i] + [0] * n
        for j in range(1, n + 1):
            add, delete, change = (previous_row[j] + 1, current_row[j - 1] + 1,
                                   previous_row[j - 1])
            if a[j - 1] != b[i - 1]:
                change += 1
            current_row[j] = min(add, delete, change)
    return current_row[n]


def read_the_word():
    word_distance = {}
    input_word = raw_input('Enter the word: ').lower()
    #  354937 words.txt
    with open('words.txt', 'r') as words:
        for line in words:
            line = line[:-1]
            word_distance[line] = distance(line, input_word)
            if word_distance[line] == 0:
                print 'Word exists in dict'
                return
    word_distance_sorted = sorted(word_distance.items(), key=lambda t: t[1])
    i = 0
    for word in word_distance_sorted:
        i += 1
        if i < 11:
            print word[0]
        else:
            break

start = time.time()
read_the_word()
done = time.time()
print done - start
