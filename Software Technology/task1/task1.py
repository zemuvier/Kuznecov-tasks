#!/usr/bin/python
# -*- coding: utf-8 -*-

import psycopg2
import sys
import timeit
con = None
def init_one():
    cur = con.cursor()
    for i in range(1,50):
      cur.execute("INSERT INTO WeatherCities VALUES('London', 23), ('New-York', 12), ('Paris', 12), ('Ankara', 30), ('Saratov', 12), ('Engels', 13), ('Ekaterinburg', 10), ('Moscow', 9), ('Samara', 15), ('Saransk', 11)")
    con.commit()
    return

def init_msny():
  cur = con.cursor()
  cur.execute("INSERT INTO WeatherCities  VALUES ('London', 23), ('New-York', 12), ('Paris', 12), ('Ankara', 30), ('Saratov', 12), ('Engels', 13), ('Ekaterinburg', 10), ('Moscow', 9), ('Samara', 15), ('Saransk', 11), ('London', 23), ('New-York', 12), ('Paris', 12), ('Ankara', 30), ('Saratov', 12), ('Engels', 13), ('Ekaterinburg', 10), ('Moscow', 9), ('Samara', 15), ('Saransk', 11), ('London', 23), ('New-York', 12), ('Paris', 12), ('Ankara', 30), ('Saratov', 12), ('Engels', 13), ('Ekaterinburg', 10), ('Moscow', 9), ('Samara', 15), ('Saransk', 11), ('London', 23), ('New-York', 12), ('Paris', 12), ('Ankara', 30), ('Saratov', 12), ('Engels', 13), ('Ekaterinburg', 10), ('Moscow', 9), ('Samara', 15), ('Saransk', 11), ('London', 23), ('New-York', 12), ('Paris', 12), ('Ankara', 30), ('Saratov', 12), ('Engels', 13), ('Ekaterinburg', 10), ('Moscow', 9), ('Samara', 15), ('Saransk', 11)")
  con.commit()
  return

try:
  con = psycopg2.connect("dbname='weather' user='zemuvier' host='localhost' password='test'")
  cur = con.cursor()
  start_time_1 = timeit.default_timer()
  init_one()
  elapsed_1 = timeit.default_timer() - start_time_1
  print elapsed_1
  start_time_2 = timeit.default_timer()
  init_msny()
  elapsed_2 = timeit.default_timer() - start_time_2
  print elapsed_2

except psycopg2.DatabaseError, e:

  if con:
    con.rollback()
  print 'Error %s' % e
  sys.exit(1)

finally:

  if con:
    con.close() 
