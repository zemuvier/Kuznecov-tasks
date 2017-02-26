import numpy as np
import cv2

import base

alpha = 2.0


def change_image_contrast():
    """Change contrast of image.

    Steps:
    1. Take RGB image
    2. Convert in to LAB
    3. Change channels via formulas
    4. Convert in to RGB
    5. Save new image
    """
    image = base.read_image(image_name='s.jpg')
    lab_image = cv2.cvtColor(image, cv2.COLOR_RGB2LAB)
    l, a, b = cv2.split(lab_image)
    a = (a - 128) * alpha + 128
    b = (b - 128) * alpha + 128
    new_image = cv2.merge((l.astype(np.uint8), a.astype(np.uint8),
                           b.astype(np.uint8)))
    try_image = cv2.cvtColor(new_image, cv2.COLOR_LAB2RGB)
    base.save_image(image=try_image, image_name='final')


if __name__ == '__main__':
    change_image_contrast()
