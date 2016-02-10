from rest_framework import serializers
from task2_app.models import Task2

class Task2Serializer (serializers.Serializer):
    pk = serializers.IntegerField (read_only = True)
    name = serializers.CharField (required = True, allow_blank = True, max_length = 100)
    temp = serializers.IntegerField (required = False)
    def create (self, validated_data):
        return Task2.objects.create (**validated_data)
    def update (self, instance, validated_data):
        instance.name = validated_data.get ('name', instance.name)
        instance.temp = validated_data.get ('temp', instance.temp)
        instance.save()
        return instance
