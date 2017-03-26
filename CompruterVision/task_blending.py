import cv2
import numpy as np

import base


def do_blending(image_src, image_dst):
    src_mask = np.zeros(image_src.shape, image_src.dtype)
    poly = np.array([[9,91],[133,90],[134,58],[119,25],[96,13],[69,5],
                     [40,14],[17,30],[5,59]], np.int32)
    cv2.fillPoly(src_mask, [poly], (255, 255, 255))
    pos = (535, 490)
    output = cv2.seamlessClone(image_src, image_dst, src_mask, pos,
                               cv2.NORMAL_CLONE)
    base.save_image(output, "final")


if __name__ == '__main__':
    image_src = base.read_image("logo.png")
    image_dst = base.read_image("apple.jpg")
    do_blending(image_src, image_dst)
