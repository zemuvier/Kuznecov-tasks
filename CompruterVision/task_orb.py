import cv2
import matplotlib.pyplot as plt

import base


def get_orb(image1, image2):
    orb = cv2.ORB_create()
    kp1, des1 = orb.detectAndCompute(image1, None)
    kp2, des2 = orb.detectAndCompute(image2, None)

    bf = cv2.BFMatcher(cv2.NORM_HAMMING, crossCheck=True)
    matches = bf.match(des1, des2)
    matches = sorted(matches, key=lambda x: x.distance)

    image3 = cv2.drawMatches(img1, kp1, img2, kp2, matches[:50], None, flags=2)
    plt.imshow(image3), plt.show()

if __name__ == '__main__':
    img1 = base.read_image('first.jpg')
    img2 = base.read_image('second.jpg')
    get_orb(img1, img2)
