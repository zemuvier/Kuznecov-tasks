import numpy as np
import cv2

import base

cmyk_scale = 100


def plot_rgb_histogram(image):
    """Plot histogram of RGB image.

    Steps:
    1. Take RGB image
    2. Plot the histogram
    """
    color = ('b', 'g', 'r')
    base.plot_histogram(image=image, color=color)


def plot_lab_histogram(image):
    """Plot histogram of LAB image.

    Steps:
    1. Take RGB image
    2. Convert it to LAB
    3. Plot the histogram
    """
    color = ('b', 'g', 'r')
    lab_image = cv2.cvtColor(image, cv2.COLOR_RGB2LAB)
    base.plot_histogram(image=lab_image, color=color)


def plot_cmyk_histogram(image):
    """Plot histogram of CMYK image.

    Steps:
    1. Take RGB image
    2. Create c, m, y channels with formulas
    3. Get min of them
    4. Do more formulas
    5. Convert image to CMYK with these channels
    6. Plot the histogram
    """
    r, g, b = cv2.split(image)
    c = r / 255.
    m = g / 255.
    y = b / 255.
    min_cmy = _get_min_cmyk(c, m, y)
    c = (1 - c - min_cmy) / (1 - min_cmy) * cmyk_scale
    m = (m - min_cmy) / (1 - min_cmy) * cmyk_scale
    y = (y - min_cmy) / (1 - min_cmy) * cmyk_scale
    k = min_cmy * cmyk_scale
    cmyk_image = cv2.merge((c.astype(np.uint8), m.astype(np.uint8),
                            y.astype(np.uint8), k.astype(np.uint8)))
    color = ('blue', 'green', 'red', 'black')
    base.plot_histogram(image=cmyk_image, color=color)


def _get_min_cmyk(c, m, y):
    min_c = np.amin(c)
    min_m = np.amin(m)
    min_y = np.amin(y)
    min_cmy = min(min_c, min_m, min_y)
    if min_cmy == min_c:
        min_cmy = c
    elif min_cmy == min_m:
        min_cmy = m
    else:
        min_cmy = y
    return min_cmy


if __name__ == '__main__':
    image = base.read_image(image_name='test.jpg')
    model = raw_input('Choose color model[r/l/c]: ')
    if model == 'r':
        plot_rgb_histogram(image)
    elif model == 'l':
        plot_lab_histogram(image)
    else:
        plot_cmyk_histogram(image)
