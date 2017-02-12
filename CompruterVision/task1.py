import cv2
import numpy as np

import base

alpha = 2.0


def change_image_contrast():
    """Change contrast of image.

    Steps:
    1. Take RGB image
    2. Change channels via formulas
    3. Save new image
    """
    image = base.read_image(image_name='test.jpg')
    new_image = cv2.multiply(image, np.array([alpha]))
    base.save_image(image=new_image, image_name='final')


if __name__ == '__main__':
    change_image_contrast()
