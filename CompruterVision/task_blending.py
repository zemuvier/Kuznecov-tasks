import cv2
import numpy as np

import base


def do_blending(image_src, image_dst):
    src_mask = np.zeros(image_src.shape, image_src.dtype)
    poly = np.array([[118, 23], [75, 7], [44, 12], [29, 26], [12, 46],
                     [6, 67], [6, 79], [13, 98], [23, 112], [36, 126],
                     [48, 132], [76, 136], [95, 131], [109, 124],
                     [119, 115], [130, 101], [135, 86], [134, 64],
                     [132, 51], [101, 14], [93, 11], [88, 9], [126, 38],
                     [129, 44]], np.int32)
    cv2.fillPoly(src_mask, [poly], (255, 255, 255))
    pos = (290, 150)
    output = cv2.seamlessClone(image_src, image_dst, src_mask, pos,
                               cv2.NORMAL_CLONE)
    cv2.imwrite("final.jpg", output)


if __name__ == '__main__':
    image_src = base.read_image("logo.png")
    image_dst = base.read_image("apple.jpg")
    do_blending(image_src, image_dst)
