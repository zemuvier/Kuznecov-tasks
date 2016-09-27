Correct mistakes in the word
============================

What is it?
-----------

There are two different algorithms that do the same work.

``task_1_gramms.py`` contains the most useful algorithm for this kind of
search. You can read about n-gramms, what is it and why you should them (not
 anything else) here: http://www.cairn.info/revue-francaise-de-linguistique-appliquee-2008-1-page-9.html

``task_1_easy_way.py`` is the simplest algorithm you can imagine to correct
word' mistake, but it's really slowler (15-15 seconds against 3-5 seconds if
 you use n-gramms).

How to run
----------

This task is simple CLI utility which provide to check if you write the word
with any mistakes or not. You can easy run it, just type in your terminal:

.. sourcecode:: console

    $ python task_1_gramms.py
..

Or:

.. sourcecode:: console

    $ python task_1_easy_way.py
..

There you just type the word, which you like to check for mistakes. Then you
 see the most common word for your word and the time of executing. If your
 word exists in the dictionary, you should see the message ``Word exists in
 dict``.

Also the project include the file ``words.txt``, which contain more than
300k of words, so it is the main dictionary for us.
