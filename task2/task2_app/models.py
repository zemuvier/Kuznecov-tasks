from django.db import models

# Create your models here.
class Task2 (models.Model):
    name = models.CharField (max_length = 100, blank = True, default = '')
    temp = models.IntegerField ()
