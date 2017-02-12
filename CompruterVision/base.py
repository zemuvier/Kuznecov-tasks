import cv2


def read_image(image_name, image_mode=1):
    """
    :param image_name: name of image to read
    :param image_mode: mode to read image (-1, 0 or 1)
    :return: image object
    """
    img = cv2.imread(image_name, image_mode)
    return img


def display_image(image_name, image_title):
    """
    :param image_name: name of image to display
    :param image_title: title of image to show in window
    :return: image window
    """
    cv2.imshow(image_title, image_name)
    key = cv2.waitKey(0)
    if key == ord('q'):
        cv2.destroyAllWindows()


def save_image(image, image_name, image_format='jpg'):
    """
    :param image: image object
    :param image_name: how to save image
    :param image_format: format of image (default: jpg)
    :return: True if image saved successfully
    """
    file_name = '%s.%s' % (image_name, image_format)
    cv2.imwrite(file_name, image)


def split_into_rgb_channels(image):
    """
    :param image: image object
    :return: 3 channels of image
    """
    red = image[:, :, 2]
    green = image[:, :, 1]
    blue = image[:, :, 0]
    return red, green, blue
