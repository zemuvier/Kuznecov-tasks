import cv2
from matplotlib import pyplot as plt

import base


def create_gaus_blur(image):
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    img = cv2.GaussianBlur(gray, (15, 15), 0)
    plt.subplot(2, 2, 1), plt.imshow(gray, cmap='gray')
    plt.title('Gray'), plt.xticks([]), plt.yticks([])
    plt.subplot(2, 2, 2), plt.imshow(img, cmap='gray')
    plt.title('Gaus'), plt.xticks([]), plt.yticks([])
    plt.show()


def create_laplasian_blur(image):
    img = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    gr = cv2.GaussianBlur(img, (5, 5), 0)
    laplacian = cv2.Laplacian(gr, cv2.CV_64F, 5, 3, 1)
    plt.subplot(2, 2, 1), plt.imshow(image, cmap='gray')
    plt.title('Gray'), plt.xticks([]), plt.yticks([])
    plt.subplot(2, 2, 2), plt.imshow(laplacian, cmap='gray')
    plt.title('Laplas'), plt.xticks([]), plt.yticks([])
    plt.show()


if __name__ == '__main__':
    blur = raw_input('Choose blur [g/l]: ')
    if blur == 'g':
        image = base.read_image(image_name='test.jpg')
        create_gaus_blur(image)
    else:
        image = base.read_image(image_name='paper.jpg')
        create_laplasian_blur(image)
