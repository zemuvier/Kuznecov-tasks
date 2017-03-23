import cv2
from matplotlib import pyplot as plt

import base


def get_canny_edges(image):
    edges = cv2.Canny(image, 100, 200)
    plt.imshow(edges, cmap='gray')
    plt.title('Canny'), plt.xticks([]), plt.yticks([])
    plt.show()

if __name__ == '__main__':
    image = base.read_image(image_name='house.jpg')
    get_canny_edges(image)
