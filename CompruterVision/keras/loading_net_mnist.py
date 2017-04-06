from keras.datasets import mnist
from keras.utils import np_utils
from keras.models import model_from_json
import numpy as np
from PIL import Image

im = Image.open('/Users/alina/anaconda/keras/src/saving_models/1.png')
im_grey = im.convert('L')
im_array = np.array(im_grey)
im_array=np.reshape(im_array, (1, 1, 28, 28)).astype('float32')

# Инвертируем изображение
x = 255 - im_array
# Нормализуем изображение
x /= 255

print("Download the neural network from file")
# Загружаем данные об архитектуре сети
json_file = open("mnist_model.json", "r")
loaded_model_json = json_file.read()
json_file.close()

# Создаем модель
print("Create the model")
loaded_model = model_from_json(loaded_model_json)

# Загружаем сохраненные веса в модель
loaded_model.load_weights("mnist_model.h5")
print("The neural network was successfuly installed")

# Загружаем данные
print("Download data")
(X_train, y_train), (X_test, y_test) = mnist.load_data()

# Преобразование размерности изображений
X_train = X_train.reshape(60000, 1, 28, 28)
X_test = X_test.reshape(10000, 1, 28, 28)

# Нормализация данных
X_train = X_train.astype('float32')
X_test = X_test.astype('float32')
X_train /= 255
X_test /= 255

# Преобразуем метки в категории
Y_train = np_utils.to_categorical(y_train, 10)
Y_test = np_utils.to_categorical(y_test, 10)

# Компилируем загруженную модель
print("Compile the model")
loaded_model.compile(loss="categorical_crossentropy", optimizer="SGD",
                     metrics=["accuracy"])

# Оцениваем качество обучения сети загруженной сети на тестовых данных
scores = loaded_model.evaluate(X_test, Y_test, verbose=0)
print("Accuracy of the neural network is: %.2f%%" % (scores[1] * 100))

# Нейронная сеть предсказывает класс изображения
prediction = loaded_model.predict(x)

def categorical_probas_to_classes(p):
    return np.argmax(p, axis=1)

# Преобразуем ответ из категориального представления в метку класса
prediction = categorical_probas_to_classes(prediction)

print("The result is...")

# Печатаем результат
print(prediction)
