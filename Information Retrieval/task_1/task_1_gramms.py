import time


def distance(a, b):
    n, m = len(a), len(b)
    if n > m:
        a, b = b, a
        n, m = m, n
    current_row = range(n + 1)
    for i in range(1, m + 1):
        previous_row, current_row = current_row, [i] + [0] * n
        for j in range(1, n + 1):
            add, delete, change = (previous_row[j] + 1, current_row[j - 1] + 1,
                                   previous_row[j - 1])
            if a[j - 1] != b[i - 1]:
                change += 1
            current_row[j] = min(add, delete, change)
    return current_row[n]


def create_n_gramm(word):
    gramm = []
    for letter in range(len(word) - 2):
        gramm.append(word[letter:letter + 3])
    return gramm


def get_gramms():
    dict_alp = {}
    #  354937 words.txt
    with open('words.txt', 'r') as words:
        for line in words:
            line = line[:-1]
            n_gramm_line = create_n_gramm(line)
            for j in n_gramm_line:
                try:
                    dict_alp[j].append(line)
                except KeyError:
                    dict_alp[j] = [line]
    return dict_alp


def read_the_word_with_gramms():
    input_word = raw_input('Enter the word: ').lower()
    dict_alp = get_gramms()
    word_gramm = create_n_gramm(input_word)
    gramms_list = []
    minn = len(word_gramm)
    for i in word_gramm:
        try:
            gramms_list += (dict_alp[i])
        except Error:
            pass
    gramms_list = sorted(list(set(gramms_list)))
    words = {}
    for line in gramms_list:
        new_min = distance(input_word, line)
        if new_min == 0:
            print 'Word exists in dict'
            return
        if new_min <= minn:
            minn = new_min
            words[line] = new_min
    words = sorted(words.items(), key=lambda x: x[1])
    i = 0
    print "Can not find this word. Maybe you mean:"
    for word in words:
        i += 1
        if i < 2:
            print word[0]
        else:
            break


start = time.time()
read_the_word_with_gramms()
done = time.time()
print done - start
